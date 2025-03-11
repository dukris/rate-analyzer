package com.pdp.rateanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RateAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateAnalyzerApplication.class, args);
	}

}
