package com.pdp.rateanalyzer.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricsData {

  private Preference preference;
  private List<Rate> rates;

}
