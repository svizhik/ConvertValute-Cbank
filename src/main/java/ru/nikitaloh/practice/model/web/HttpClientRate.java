package ru.nikitaloh.practice.model.web;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;

@Component
//@Slf4j
public class HttpClientRate {

    private final String VALUTES_URL = "https://cbr.ru/scripts/XML_daily.asp?date_req=%s";
    private final String DATE_PATTERN = "dd/MM/yyyy";

    private final HttpClient client;
    private final XmlMapper objectMapper;

    public static ValCursDto cacheRate;



    private final Logger log = LoggerFactory.getLogger(HttpClientRate.class);

    public HttpClientRate() {
        client = HttpClient.newHttpClient();
        objectMapper = (XmlMapper) new XmlMapper().registerModule(new JavaTimeModule());
    }

    public ValCursDto fetchRate() throws IOException, URISyntaxException, InterruptedException {

        if (cacheRate != null && cacheRate.getDate().equals(LocalDate.now())) {
            return cacheRate;
        }
        return fetchRateInternal();
    }

    private ValCursDto fetchRateInternal() throws URISyntaxException, IOException, InterruptedException {
        String url = VALUTES_URL.formatted(
                LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        );
        log.info("Fetching rate from {}", url);
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
        String xmlResponse = client
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body()
                .replace(",", ".");
        log.info("CB RF currency response: {}", xmlResponse);
        cacheRate = objectMapper.readValue(xmlResponse, ValCursDto.class);

        ValuteDto valuteRub = new ValuteDto(
                "RUS7377",
                "7377",
                "RUB",
                1,
                "Рубли",
                1.0,
                1.0
        );
        cacheRate.getValuteDto().add(valuteRub);

        return cacheRate;
    }


}



