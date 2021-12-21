package com.nikfrolkov.gif.api.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDto {
    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private Map<String, BigDecimal> rates;
}
