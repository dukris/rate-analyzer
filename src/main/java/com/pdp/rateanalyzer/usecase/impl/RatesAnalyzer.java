package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.domain.RateNotification;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.usecase.AnalyzeRatesUseCase;
import com.pdp.rateanalyzer.usecase.SendRateNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RatesAnalyzer implements AnalyzeRatesUseCase {

  private final ExecutorService executor;
  private final SendRateNotificationUseCase sender;
  private final PreferencePersistenceAdapter preferencePersistenceAdapter;

  @Override
  @Transactional(readOnly = true)
  public void analyze(List<Rate> rates) {
    Map<String, Rate> ratesByCurrency = rates.stream()
        .collect(Collectors.toMap(Rate::getCurrency, rate -> rate));

    List<CompletableFuture<Void>> futures = preferencePersistenceAdapter.getAll()
        .map(preference -> CompletableFuture.runAsync(() -> {
              Rate rate = ratesByCurrency.get(preference.getCurrency());
              if (rate != null && rate.getValue().compareTo(preference.getRate()) >= 0) {
                sender.send(new RateNotification(preference.getUserId(), preference.getCurrency(), rate.getValue()));
              }
            }, executor)
        ).toList();

    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
  }

}
