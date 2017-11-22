package com.nevex.iextrading.reference.stock;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * Created by Mark Cunningham on 11/21/2017.
 */
public final class Symbol {

    private final String symbol;
    private final String name;
    private final LocalDate date;
    private final boolean enabled;

    private Symbol(Builder builder) {
        symbol = builder.symbol;
        name = builder.name;
        date = builder.date;
        enabled = builder.enabled;

        if (StringUtils.isBlank(symbol)) { throw new IllegalArgumentException("Symbol cannot be blank"); }
        if (StringUtils.isBlank(name)) { throw new IllegalArgumentException("Name cannot be blank"); }
        if (date == null) { throw new IllegalArgumentException("Date cannot be null"); }
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static Symbol fromDto(SymbolDto dto) {
        return builder().withDto(dto).build();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {

        private String symbol;
        private String name;
        private LocalDate date;
        private boolean enabled;

        private Builder() { }

        public Builder withDto(SymbolDto dto) {
            symbol = dto.getSymbol();
            name = dto.getName();
            date = dto.getDate();
            enabled = dto.getEnabled();
            return this;
        }

        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Symbol build() {
            return new Symbol(this);
        }
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", enabled=" + enabled +
                '}';
    }
}