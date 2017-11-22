package com.nevex.iextrading.reference.stock;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

/**
 * Created by NeVeX on 11/21/2017.
 */
final class SymbolDto {

    private final String symbol;
    private final String name;
    private final LocalDate date;
    private final Boolean enabled;

    @JsonCreator
    SymbolDto(
            @JsonProperty(value = "symbol") String symbol,
            @JsonProperty(value = "name") String name,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @JsonDeserialize(using = LocalDateDeserializer.class)
            @JsonProperty(value = "date") LocalDate date,
            @JsonProperty(value = "isEnabled") Boolean enabled) {
        this.symbol = symbol;
        this.name = name;
        this.date = date;
        this.enabled = enabled;
    }

    String getSymbol() {
        return symbol;
    }

    String getName() {
        return name;
    }

    LocalDate getDate() {
        return date;
    }

    Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "SymbolDto{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", enabled=" + enabled +
                '}';
    }
}
