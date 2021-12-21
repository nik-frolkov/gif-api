package com.nikfrolkov.gif.api.service;

import com.nikfrolkov.gif.api.client.GifClient;
import com.nikfrolkov.gif.api.controller.dto.GifResponse;
import com.nikfrolkov.gif.api.exception_handling.GifException;
import com.nikfrolkov.gif.api.service.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {

    private final GifClient gifClient;
    private final ExchangeRateService rateService;

    @Value("${gif-api.api_key}")
    private String api_key;

    @Value("${gif-api.rich_tag}")
    private String richTag;

    @Value("${gif-api.broke_tag}")
    private String brokeTag;

    public GifResponse getGif(String currency) {
        BigDecimal todayRate = rateService.getRate(DateUtils.today(), currency);
        BigDecimal yesterdayRate = rateService.getRate(DateUtils.yesterday(), currency);
        String tag = getGifTag(todayRate, yesterdayRate);

        GifResponse response;
        try {
            response = gifClient.getRandomGif(api_key, tag);
        } catch (Exception e) {
            throw new GifException("Can not get GIF");
        }
        response.setTag(tag);
        return response;
    }

    private String getGifTag(BigDecimal todayExchangeRate, BigDecimal yesterdayExchangeRate) {
        if (todayExchangeRate.compareTo(yesterdayExchangeRate) > 0) {
            return richTag;
        }
        return brokeTag;
    }
}
