package com.nikfrolkov.gif.api.service;

import com.nikfrolkov.gif.api.controller.dto.GifResponse;

public interface GifService {
    GifResponse getGif(String currency);
}
