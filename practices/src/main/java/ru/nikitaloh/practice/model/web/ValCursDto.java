package ru.nikitaloh.practice.model.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
//import lombok.Getter;
//import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

//@Getter
//@ToString
@JacksonXmlRootElement(localName = "ValCurs")
public class ValCursDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JacksonXmlProperty(isAttribute = true, localName = "Date")
    LocalDate date;

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    String name;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    List<ValuteDto> valuteDto;

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<ValuteDto> getValuteDto() {
        return valuteDto;
    }
}
