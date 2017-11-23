package com.nevex.iextrading.reference.symbol;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by NeVeX on 11/21/2017.
 */
public final class Symbol implements Comparable<Symbol> {

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

    @Override
    public int compareTo(Symbol other) {
        return StringUtils.compareIgnoreCase(symbol, other.getSymbol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return StringUtils.equalsIgnoreCase(symbol, symbol1.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

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
