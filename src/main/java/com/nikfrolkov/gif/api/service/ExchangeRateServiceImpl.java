package com.nikfrolkov.gif.api.service;

import com.nikfrolkov.gif.api.client.ExchangeRateClient;
import com.nikfrolkov.gif.api.client.dto.ExchangeRateDto;
import com.nikfrolkov.gif.api.exception_handling.RateExchangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Value("${rates-api.app_id}")
    private String app_id;

    @Value("${rates-api.base_currency}")
    private String baseCurrency;

    private final ExchangeRateClient exchangeRateClient;

    public BigDecimal getRate(LocalDate date, String currency) {
        return getExchangeRate(date, currency);
    }

    private BigDecimal getExchangeRate(LocalDate date, String currency) {

        ExchangeRateDto exchangeRateDto = exchangeRateClient
                .getHistoricalRate(date.toString(), app_id, baseCurrency, currency);
        BigDecimal exchangeRate = exchangeRateDto.getRates().get(currency);
        if (exchangeRate == null) {
            throw new RateExchangeException("Can not get exchange rate");
        }
        return exchangeRate;
    }
}
