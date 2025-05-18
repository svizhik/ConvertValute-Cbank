package ru.nikitaloh.practice.service;

import org.springframework.stereotype.Service;
import ru.nikitaloh.practice.model.UserDto;
import ru.nikitaloh.practice.model.web.ValCursDto;
import ru.nikitaloh.practice.model.web.ValuteDto;
import ru.nikitaloh.practice.model.web.HttpClientRate;
import ru.nikitaloh.practice.repository.UserRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class RateService {

    private final HttpClientRate httpClientRate;
    private final ValCursDto valCursDto;
    private final UserService userService;

    public RateService(HttpClientRate httpClientRate, UserService userService) throws IOException, URISyntaxException, InterruptedException {
        this.httpClientRate = httpClientRate;
        this.userService = userService;
        this.valCursDto = new ValCursDto();
    }

    // TODO: здесь внутри, где сохраняю историю, сделать userService.save()
    // todo: по энтити истории сохранять историю, внутри нее сохранять юзера
    // todo если юзера нет - провалидируй и ответь ошибкой

    public ValCursDto getAllRates() throws IOException, URISyntaxException, InterruptedException {
        return httpClientRate.fetchRate();
    }

    public double convertValute(
            double amount,
            String toValute
    ) throws Exception {
        double vunitRate = httpClientRate
                .fetchRate()
                .getValuteDto()
                .stream()
                .filter(
                        valuteDto -> toValute
                                .equals(
                                        valuteDto
                                                .getCharCode()
                                )
                ).findFirst()
                .orElseThrow(() -> new Exception("Valute %s not found".formatted(toValute)))
                .getVunitRate();

        return amount / vunitRate;
    }

    public double convertValuteToRus(
            double amount,
            String fromValute
    ) throws Exception {
        double vunitRate = httpClientRate
                .fetchRate()
                .getValuteDto()
                .stream()
                .filter(
                        valuteDto -> fromValute
                                .equals(
                                        valuteDto.getCharCode()
                                )
                ).findFirst()
                .orElseThrow(() -> new Exception("Valute %s not found".formatted(fromValute)))
                .getVunitRate();

        return vunitRate * amount;
    }

    public double convertValuteToValute(
            double amount,
            String fromValute,
            String toValute
    ) throws Exception {
        Stream<ValuteDto> listOfValutes = httpClientRate.fetchRate().getValuteDto().stream();

        double vunitRateFrom = listOfValutes
                .filter(
                        valuteDto -> fromValute
                                .equals(
                                        valuteDto
                                                .getCharCode()
                                )
                ).findFirst()
                .orElseThrow(() -> new Exception("Valute %s not found".formatted(fromValute)))
                .getVunitRate();

        double vunitRateTo = httpClientRate
                .fetchRate()
                .getValuteDto()
                .stream()
                .filter(
                        valuteDto -> toValute
                                .equals(
                                        valuteDto
                                                .getCharCode()
                                )
                ).findFirst()
                .orElseThrow(() -> new Exception("Valute %s not found".formatted(toValute)))
                .getVunitRate();

        return (vunitRateFrom * amount)  / vunitRateTo;
    }

}
