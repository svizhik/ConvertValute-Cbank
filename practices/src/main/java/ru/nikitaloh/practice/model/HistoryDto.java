package ru.nikitaloh.practice.model;

import jakarta.persistence.*;
//import lombok.*;
import java.time.LocalDate;

//@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "query_history")
public class HistoryDto {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "from_valute", nullable = false)
    private String fromValute;

    @Column(name = "to_valute", nullable = false)
    private String toValute;

    @Column(name = "first_summ", nullable = false)
    private double firstSumm;

    @Column(name = "result_summ", nullable = false)
    private double resultSumm;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
    private String userId;

    public HistoryDto(String userId, String fromValute, String toValute, double firstSumm, double resultSumm) {
        this.userId = userId;
        this.fromValute = fromValute;
        this.toValute = toValute;
        this.firstSumm = firstSumm;
        this.resultSumm = resultSumm;
    }

   public HistoryDto() {}


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFromValute() {
        return fromValute;
    }

    public void setFromValute(String fromValute) {
        this.fromValute = fromValute;
    }

    public String getToValute() {
        return toValute;
    }

    public void setToValute(String toValute) {
        this.toValute = toValute;
    }

    public double getFirstSumm() {
        return firstSumm;
    }

    public void setFirstSumm(double firstSumm) {
        this.firstSumm = firstSumm;
    }

    public double getResultSumm() {
        return resultSumm;
    }

    public void setResultSumm(double resultSumm) {
        this.resultSumm = resultSumm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId.getId();
    }


    // todo здесь было setUserId
}
