package ru.nikitaloh.practice.controller;

//import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nikitaloh.practice.model.HistoryDto;
import ru.nikitaloh.practice.service.HistoryService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@AllArgsConstructor
@Controller
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    // GET > SELECT
    @GetMapping("/api/history")
    public List<HistoryDto> getHistory() {
        return historyService.getQueryHistory();
    }

    @GetMapping("/api/history/date")
    public List<HistoryDto> getHistoryByDate(@RequestParam LocalDate date) {
        return historyService.getHistoryByDate(date);
    }

    @GetMapping("/api/history/date/episode")
    public List<HistoryDto> getHistoryByDateFromTill(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return historyService.getHistoryByDateFromTill(start, end);
    }

    // PUT > INSERT (ADD)
//    @PutMapping("/api/newhistory")
//    public boolean addHistory(@RequestBody HistoryDto history) {
//        return historyService.saveHistory(history).hasBody();
//    }


}
