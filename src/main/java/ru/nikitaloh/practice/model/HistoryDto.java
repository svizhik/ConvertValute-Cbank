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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
    private UserDto userId;

    @Column(name = "amount", nullable = false)
    private double amount;

    public HistoryDto(LocalDate date, String fromValute, String toValute, UserDto userId, double amount) {
        this.date = date;
        this.fromValute = fromValute;
        this.toValute = toValute;
        this.userId = userId;
        this.amount = amount;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId.getId();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    // todo здесь было setUserId
}
