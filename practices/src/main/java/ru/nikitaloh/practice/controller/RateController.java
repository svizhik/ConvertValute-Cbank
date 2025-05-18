package ru.nikitaloh.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikitaloh.practice.model.HistoryDto;
import ru.nikitaloh.practice.model.UserDto;
import ru.nikitaloh.practice.model.web.ConvertRequest;
import ru.nikitaloh.practice.service.HistoryService;
import ru.nikitaloh.practice.service.RateService;
import ru.nikitaloh.practice.service.UserService;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class RateController {

    private final ObjectMapper mapper;
    private final RateService rateService;
    private final UserService userService;
    private final HistoryService historyService;

    public RateController(ObjectMapper mapper, RateService rateService, UserService userService, HistoryService historyService, HistoryService historyService1) {
        this.mapper = mapper;
        this.rateService = rateService;
        this.userService = userService;
        this.historyService = historyService1;
    }

    @GetMapping(value = "/rates")
    public String getAllRates(
            @RequestHeader("User-Id") String userId
    ) throws IOException, URISyntaxException, InterruptedException {
        userService.addUser(new UserDto(userId));
        return mapper.writeValueAsString(rateService.getAllRates());
    }

    @PostMapping(value = "/converter/torus")
    public double convertValuteTorus(
            @RequestBody ConvertRequest convertRequest,
            @RequestHeader("User-Id") String userId,
            @RequestBody HistoryDto history
    ) throws Exception {
        historyService.saveHistory(
                new HistoryDto(
                userId, history.getFromValute(), history.getToValute(), history.getFirstSumm(), history.getResultSumm()
                )
        );
        return rateService.convertValuteToRus(
                convertRequest.getAmount(),
                convertRequest.getFromValute()
        );
    }

    @PostMapping(value = "/converter/fromrus")
    public double convertValute(@RequestBody ConvertRequest convertRequest) throws Exception {
        return rateService.convertValute(
                convertRequest.getAmount(),
                convertRequest.getToValute()
        );
    }

    @PostMapping(value = "/converter/valutetovalute")
    public double convertValuteToValute(@RequestBody ConvertRequest convertRequest,
                                        @RequestHeader("User-Id") UserDto userId,
                                        @RequestBody HistoryDto history
    ) throws Exception {
        historyService.saveHistory(
                new HistoryDto(
                        userId, history.getFromValute(), history.getToValute(), history.getFirstSumm(), history.getResultSumm()
                )
        );
        return rateService.convertValuteToValute(
                convertRequest.getAmount(),
                convertRequest.getFromValute(),
                convertRequest.getToValute()
        );
    }

}

// запихнуть здесь Convert Request и User Service с методом добавления user в query history??