package ru.nikitaloh.practice.model.web;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;


//@Getter
//@Setter
//@RequiredArgsConstructor
//@ToString
public class ValuteDto {

    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    String id;

    @JacksonXmlProperty(localName = "NumCode")
    String numCode;

    @JacksonXmlProperty(localName = "CharCode")
    String charCode;

    @JacksonXmlProperty(localName = "Nominal")
    int nominal;

    @JacksonXmlProperty(localName = "Name")
    String name;

    @JacksonXmlProperty(localName = "Value")
    double value;

    @JacksonXmlProperty(localName = "VunitRate")
    double vunitRate;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVunitRate() {
        return vunitRate;
    }

    public void setVunitRate(double vunitRate) {
        this.vunitRate = vunitRate;
    }
}
