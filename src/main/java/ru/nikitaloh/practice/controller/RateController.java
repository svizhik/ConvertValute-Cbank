package ru.nikitaloh.practice.controller;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nikitaloh.practice.model.web.ConvertRequest;
import ru.nikitaloh.practice.model.web.Response;
import ru.nikitaloh.practice.service.RateService;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@Validated
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(value = "/rates")
    public String getAllRates(
            @RequestHeader("User-Id") @NotEmpty(message = "Пустое поле логина.") String userId
    ) throws IOException, URISyntaxException, InterruptedException {
        return rateService.getAllRates(userId);
    }

    @PostMapping(value = "/converter")
    public Response convertValute(
            @RequestBody ConvertRequest convertRequest,
            @RequestHeader("User-Id") @NotEmpty(message = "Пустое поле логина.") String userId
    ) throws Exception {
        return new Response("OK", rateService.convert(convertRequest, userId));
    }
}

