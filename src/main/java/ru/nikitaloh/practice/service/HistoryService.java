package ru.nikitaloh.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.nikitaloh.practice.controller.UserController;
import ru.nikitaloh.practice.model.HistoryDto;
import ru.nikitaloh.practice.repository.HistoryRepository;
import ru.nikitaloh.practice.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository, UserRepository userRepository) {
        this.historyRepository = historyRepository;
    }

    public List<HistoryDto> getQueryHistory() {
        return historyRepository.getHistory();
    }

    public List<HistoryDto> getHistoryByDate(LocalDate date) {
        return historyRepository.findByDate(date);
    }

    public List<HistoryDto> getHistoryByDateFromTill(LocalDate start, LocalDate end) {
        return historyRepository.findByDate(start, end);
    }

    public void saveHistory(HistoryDto history) {
        logger.info("Сохранение истории: {}", history);
        historyRepository.save(history);
    }
}
