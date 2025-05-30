package ru.nikitaloh.practice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.nikitaloh.practice.model.HistoryDto;
import ru.nikitaloh.practice.model.UserDto;
import ru.nikitaloh.practice.model.web.ConvertRequest;
import ru.nikitaloh.practice.model.web.HttpClientRate;
import ru.nikitaloh.practice.model.web.ValuteDto;
import ru.nikitaloh.practice.repository.UserRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

@Service
public class RateService {

    private final ObjectMapper mapper;
    private final HttpClientRate httpClientRate;
    private final UserService userService;
    private final HistoryService historyService;
    private final UserRepository userRepository;

    public RateService(
            ObjectMapper mapper,
            HttpClientRate httpClientRate,
            UserService userService,
            HistoryService historyService,
            UserRepository userRepository) {
        this.mapper = mapper;
        this.httpClientRate = httpClientRate;
        this.userService = userService;
        this.historyService = historyService;
        this.userRepository = userRepository;
    }

    private ValuteDto findValuteByCode(String charCode) throws Exception {
        return httpClientRate.fetchRate()
                .getValuteDto()
                .stream()
                .filter(valuteDto -> charCode.equals(valuteDto.getCharCode()))
                .findFirst()
                .orElseThrow(() -> new Exception("Valute %s not found".formatted(charCode)));
    }

    public String getAllRates(String userId)
            throws IOException, URISyntaxException, InterruptedException {
        userService.addUser(new UserDto(userId));
        return mapper.writeValueAsString(httpClientRate.fetchRate());
    }

    // разбить метод на функции для удобства чтения
    public double convert(
            ConvertRequest request,
            String userId
    ) throws Exception {

        userService.addUser(new UserDto(userId));

        historyService.saveHistory(
                new HistoryDto(
                        LocalDate.now(),
                        request.getFromValute(),
                        request.getToValute(),
                        userRepository.findByLogin(userId),
                        request.getAmount()
                )
        );

        ValuteDto vunitRateFrom = findValuteByCode(request.getFromValute());
        ValuteDto vunitRateTo = findValuteByCode(request.getToValute());

        return convert(request, vunitRateFrom, vunitRateTo);
    }

    private double convert(ConvertRequest request, ValuteDto valuteDtoFrom, ValuteDto valuteDtoTo) {
        double amount = request.getAmount();
        double vunitRateFrom = valuteDtoFrom.getVunitRate();
        double vunitRateTo = valuteDtoTo.getVunitRate();
        String charCodeFrom = valuteDtoFrom.getCharCode();
        String charCodeTo = valuteDtoTo.getCharCode();

        if (valuteDtoFrom.equals(valuteDtoTo)) {
            return 0.0;
        } else if (charCodeFrom.equals("RUB") && !charCodeTo.equals("RUB")) {
            return amount / vunitRateTo;
        } else if (!charCodeFrom.equals("RUB") && charCodeTo.equals("RUB")) {
            return amount * vunitRateFrom;
        } else {
            return (vunitRateFrom * amount) / vunitRateTo;
        }
    }
}