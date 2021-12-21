package com.nikfrolkov.gif.api.controller;

import com.nikfrolkov.gif.api.controller.dto.GifResponse;
import com.nikfrolkov.gif.api.service.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gif")
@RequiredArgsConstructor
public class GifController {

    private final GifService gifService;

    @GetMapping("/{currency}")
    public GifResponse getGifByCurrency(@PathVariable("currency") String currency) {
        return gifService.getGif(currency);
    }
}
