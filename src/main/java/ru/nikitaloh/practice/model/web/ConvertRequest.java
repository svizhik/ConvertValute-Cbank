package ru.nikitaloh.practice.model.web;

//import lombok.Getter;

//@Getter
public class ConvertRequest {

    double amount;
    String toValute;
    String fromValute;

    public double getAmount() {
        return amount;
    }

    public String getToValute() {
        return toValute;
    }

    public String getFromValute() {
        return fromValute;
    }
}
