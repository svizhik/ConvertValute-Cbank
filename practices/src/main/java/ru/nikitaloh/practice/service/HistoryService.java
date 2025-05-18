package ru.nikitaloh.practice.service;

//import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nikitaloh.practice.controller.UserController;
import ru.nikitaloh.practice.model.web.Response;
import ru.nikitaloh.practice.repository.HistoryRepository;
import ru.nikitaloh.practice.model.HistoryDto;
import ru.nikitaloh.practice.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;


//@AllArgsConstructor
@Service
public class HistoryService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository, UserRepository userRepository) {
        this.historyRepository = historyRepository;
    }

    // GET > SELECT
    public List<HistoryDto> getQueryHistory() {
        return historyRepository.getHistory();
    }

    public List<HistoryDto> getHistoryByDate(LocalDate date) {
        return historyRepository.findByDate(date);
    }

    public List<HistoryDto> getHistoryByDateFromTill(LocalDate start, LocalDate end) {
        return historyRepository.findByDate(start, end);
    }

    // PUT > INSERT (ADD)
    public ResponseEntity<Response> saveHistory(HistoryDto history) {
        historyRepository.save(history);
        logger.info("История сохранена: {}", history);
        return ResponseEntity.ok(new Response("История успешно сохранена в базе данных.", history));
    }

}
