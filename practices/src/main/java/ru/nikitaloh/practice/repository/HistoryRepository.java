package ru.nikitaloh.practice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nikitaloh.practice.model.HistoryDto;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface HistoryRepository extends JpaRepository<HistoryDto, String> {

    // GET > SELECT
    @Query(value = "select * from query_history",nativeQuery = true)
    List<HistoryDto> getHistory();
    @Query(value = "select * from query_history where date=:date",nativeQuery = true)
    List<HistoryDto> findByDate(LocalDate date);
    @Query(value = "select * from query_history where date >=: dateStart && date <=: dateEnd", nativeQuery = true)
    List<HistoryDto> findByDate(LocalDate dateStart, LocalDate dateEnd);

//    // PUT > POST
// уже есть save

}
