package com.nevex.iextrading.stock.quote;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by NeVeX on 11/22/2017.
 */
public final class Quote implements Comparable<Quote> {

    private final String symbol;
    private final String companyName;
    private final String primaryExchange;
    private final String sector;
    private final CalculationPrice calculationPrice;

    private final BigDecimal open;
    private final OffsetDateTime openTime;

    private final BigDecimal close;
    private final OffsetDateTime closeTime;

    private final BigDecimal latestPrice;
    private final CalculationPriceSource latestSource;

    @Deprecated //  not sure how to use this
    private final String latestTime;
    private final OffsetDateTime latestUpdate;
    private final Long latestVolume;

    private final BigDecimal iexRealtimePrice;
    private final Long iexRealtimeSize;
    private final OffsetDateTime iexLastUpdated;

    private final BigDecimal delayedPrice;
    private final OffsetDateTime delayedPriceTime;

    private final BigDecimal previousClose;
    private final BigDecimal change;
    private final BigDecimal changePercent;

    private final BigDecimal iexMarketPercent;
    private final Long iexVolume;
    private final Long avg30DayVolume; // 30 day average volume

    private final BigDecimal iexBidPrice;
    private final Long iexBidSize;
    private final BigDecimal iexAskPrice;
    private final Long iexAskSize;

    private final Long marketCap;
    private final BigDecimal peRatio;

    private final BigDecimal week52High;
    private final BigDecimal week52Low;
    private final BigDecimal ytdChange;

    private void checkObject(Object obj, String name) {
        if ( obj == null) { throw new IllegalArgumentException("No "+name+" provided"); }
    }

    private void checkString(String str, String name) {
        if ( StringUtils.isBlank(str)) { throw new IllegalArgumentException("No "+name+" provided"); }
    }

    private Quote(Builder builder) {
        this.symbol = builder.symbol; checkString(symbol, "symbol");
        this.companyName = builder.companyName; checkString(companyName, "companyName");
        this.primaryExchange = builder.primaryExchange; checkString(primaryExchange, "primaryExchange");
        this.sector = builder.sector; checkString(sector, "sector");

        this.calculationPrice = builder.calculationPrice; checkObject(calculationPrice, "calculationPrice");
        this.open = builder.open; checkObject(open, "open");
        this.openTime = builder.openTime; checkObject(openTime, "openTime");

        this.close = builder.close; checkObject(close, "close");
        this.closeTime = builder.closeTime; checkObject(closeTime, "closeTime");

        this.latestPrice = builder.latestPrice; checkObject(latestPrice, "latestPrice");
        this.latestSource = builder.latestSource; checkObject(latestSource, "latestSource");

        this.latestTime = builder.latestTime;
        this.latestUpdate = builder.latestUpdate; checkObject(latestUpdate, "latestUpdate");
        this.latestVolume = builder.latestVolume; checkObject(latestVolume, "latestVolume");

        this.iexRealtimePrice = builder.iexRealtimePrice; checkObject(iexRealtimePrice, "iexRealtimePrice");
        this.iexRealtimeSize = builder.iexRealtimeSize; checkObject(iexRealtimeSize, "iexRealtimeSize");
        this.iexLastUpdated = builder.iexLastUpdated; checkObject(iexLastUpdated, "iexLastUpdated");

        this.delayedPrice = builder.delayedPrice; checkObject(delayedPrice, "delayedPrice");
        this.delayedPriceTime = builder.delayedPriceTime; checkObject(delayedPriceTime, "delayedPriceTime");

        this.previousClose = builder.previousClose; checkObject(previousClose, "previousClose");
        this.change = builder.change; checkObject(change, "change");
        this.changePercent = builder.changePercent; checkObject(changePercent, "changePercent");

        this.iexMarketPercent = builder.iexMarketPercent; checkObject(iexMarketPercent, "iexMarketPercent");
        this.iexVolume = builder.iexVolume; checkObject(iexVolume, "iexVolume");
        this.avg30DayVolume = builder.avg30DayVolume; checkObject(avg30DayVolume, "avg30DayVolume");

        this.iexBidPrice = builder.iexBidPrice; checkObject(iexBidPrice, "iexBidPrice");
        this.iexBidSize = builder.iexBidSize; checkObject(iexBidSize, "iexBidSize");
        this.iexAskPrice = builder.iexAskPrice; checkObject(iexAskPrice, "iexAskPrice");
        this.iexAskSize = builder.iexAskSize; checkObject(iexAskSize, "iexAskSize");

        this.marketCap = builder.marketCap; checkObject(marketCap, "marketCap");
        this.peRatio = builder.peRatio; checkObject(peRatio, "peRatio");

        this.week52High = builder.week52High; checkObject(week52High, "week52High");
        this.week52Low = builder.week52Low; checkObject(week52Low, "week52Low");
        this.ytdChange = builder.ytdChange; checkObject(ytdChange, "ytdChange");
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public String getSector() {
        return sector;
    }

    public CalculationPrice getCalculationPrice() {
        return calculationPrice;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public OffsetDateTime getOpenTime() {
        return openTime;
    }

    public BigDecimal getClose() {
        return close;
    }

    public OffsetDateTime getCloseTime() {
        return closeTime;
    }

    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    public CalculationPriceSource getLatestSource() {
        return latestSource;
    }

    @Deprecated
    public String getLatestTime() {
        return latestTime;
    }

    /**
     * If there's no trades for the stock that day, this can return an empty optional
     */
    public Optional<OffsetDateTime> getLatestUpdate() {
        return Optional.ofNullable(latestUpdate);
    }

    public Long getLatestVolume() {
        return latestVolume;
    }

    public BigDecimal getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public Long getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public OffsetDateTime getIexLastUpdated() {
        return iexLastUpdated;
    }

    public BigDecimal getDelayedPrice() {
        return delayedPrice;
    }

    public OffsetDateTime getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public BigDecimal getPreviousClose() {
        return previousClose;
    }

    public BigDecimal getChange() {
        return change;
    }

    public BigDecimal getChangePercent() {
        return changePercent;
    }

    public BigDecimal getIexMarketPercent() {
        return iexMarketPercent;
    }

    public Long getIexVolume() {
        return iexVolume;
    }

    public Long getAvg30DayVolume() {
        return avg30DayVolume;
    }

    public BigDecimal getIexBidPrice() {
        return iexBidPrice;
    }

    public Long getIexBidSize() {
        return iexBidSize;
    }

    public BigDecimal getIexAskPrice() {
        return iexAskPrice;
    }

    public Long getIexAskSize() {
        return iexAskSize;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public BigDecimal getPeRatio() {
        return peRatio;
    }

    public BigDecimal getWeek52High() {
        return week52High;
    }

    public BigDecimal getWeek52Low() {
        return week52Low;
    }

    public BigDecimal getYtdChange() {
        return ytdChange;
    }

    static Builder builder(ZoneOffset zoneOffset) { return new Builder(zoneOffset); }

    @Override
    public int compareTo(Quote other) {
        return Comparator.comparing(Quote::getSymbol)
                .thenComparing(Quote::getLatestPrice)
                .compare(this, other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(symbol, quote.symbol) &&
                Objects.equals(latestUpdate, quote.latestUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, latestUpdate);
    }

    static Quote from(ZoneOffset zoneOffset, QuoteDto quoteDto) {
        return new Builder(zoneOffset, quoteDto).build();
    }

    static class Builder {

        private final ZoneOffset zoneOffset;

        private String symbol;
        private String companyName;
        private String primaryExchange;
        private String sector;
        private CalculationPrice calculationPrice;

        private BigDecimal open;
        private OffsetDateTime openTime;

        private BigDecimal close;
        private OffsetDateTime closeTime;

        private BigDecimal latestPrice;
        private CalculationPriceSource latestSource;

        @Deprecated //  not sure how to use this
        private String latestTime;
        private OffsetDateTime latestUpdate;
        private Long latestVolume;

        private BigDecimal iexRealtimePrice;
        private Long iexRealtimeSize;
        private OffsetDateTime iexLastUpdated;

        private BigDecimal delayedPrice;
        private OffsetDateTime delayedPriceTime;

        private BigDecimal previousClose;
        private BigDecimal change;
        private BigDecimal changePercent;

        private BigDecimal iexMarketPercent;
        private Long iexVolume;
        private Long avg30DayVolume; // 30 day average volume

        private BigDecimal iexBidPrice;
        private Long iexBidSize;
        private BigDecimal iexAskPrice;
        private Long iexAskSize;

        private Long marketCap;
        private BigDecimal peRatio;

        private BigDecimal week52High;
        private BigDecimal week52Low;
        private BigDecimal ytdChange;


        private Builder(ZoneOffset zoneOffset, QuoteDto quoteDto) {
            this(zoneOffset);
            withSymbol(quoteDto.getSymbol());
            withCompanyName(quoteDto.getCompanyName());
            withPrimaryExchange(quoteDto.getPrimaryExchange());
            withSector(quoteDto.getSector());

            withCalculationPrice(quoteDto.getCalculationPrice());
            withOpen(quoteDto.getOpen());
            withOpenTime(quoteDto.getOpenTime());

            withClose(quoteDto.getClose());
            withCloseTime(quoteDto.getCloseTime());

            withLatestPrice(quoteDto.getLatestPrice());
            withLatestSource(quoteDto.getLatestSource());

            withLatestTime(quoteDto.getLatestTime());
            withLatestUpdate(quoteDto.getLatestUpdate());
            withLatestVolume(quoteDto.getLatestVolume());

            withIexRealtimePrice(quoteDto.getIexRealtimePrice());
            withIexRealtimeSize(quoteDto.getIexRealtimeSize());
            withIexLastUpdated(quoteDto.getIexLastUpdated());

            withDelayedPrice(quoteDto.getDelayedPrice());
            withDelayedPriceTime(quoteDto.getDelayedPriceTime());

            withPreviousClose(quoteDto.getPreviousClose());
            withChange(quoteDto.getChange());
            withChangePercent(quoteDto.getChangePercent());

            withIexMarketPercent(quoteDto.getIexMarketPercent());
            withIexVolume(quoteDto.getIexVolume());
            withAvg30DayVolume(quoteDto.getAvgTotalVolume());

            withIexBidPrice(quoteDto.getIexBidPrice());
            withIexBidSize(quoteDto.getIexBidSize());
            withIexAskPrice(quoteDto.getIexAskPrice());
            withIexAskSize(quoteDto.getIexAskSize());

            withMarketCap(quoteDto.getMarketCap());
            withPeRatio(quoteDto.getPeRatio());

            withWeek52High(quoteDto.getWeek52High());
            withWeek52Low(quoteDto.getWeek52Low());
            withYtdChange(quoteDto.getYtdChange());
        }


        private Builder(ZoneOffset zoneOffset) { this.zoneOffset = zoneOffset; }


        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder withPrimaryExchange(String primaryExchange) {
            this.primaryExchange = primaryExchange;
            return this;
        }

        public Builder withSector(String sector) {
            this.sector = sector;
            return this;
        }

        public Builder withCalculationPrice(String calculationPrice) {
            CalculationPrice.from(calculationPrice).ifPresent(cp -> this.calculationPrice = cp);
            return this;
        }

        public Builder withOpen(BigDecimal open) {
            this.open = open;
            return this;
        }

        public Builder withOpenTime(Long openTimeMs) {
            this.openTime = Instant.ofEpochMilli(openTimeMs).atOffset(zoneOffset);
            return this;
        }

        public Builder withClose(BigDecimal close) {
            this.close = close;
            return this;
        }

        public Builder withCloseTime(Long closeTimeMs) {
            this.closeTime = Instant.ofEpochMilli(closeTimeMs).atOffset(zoneOffset);
            return this;
        }

        public Builder withLatestPrice(BigDecimal latestPrice) {
            this.latestPrice = latestPrice;
            return this;
        }

        public Builder withLatestSource(String latestSource) {
            CalculationPriceSource.from(latestSource).ifPresent(s -> this.latestSource = s);
            return this;
        }

        @Deprecated
        public Builder withLatestTime(String latestTime) {
            this.latestTime = latestTime;
            return this;
        }

        public Builder withLatestUpdate(Long latestUpdateMs) {
            if ( latestUpdateMs > 0 ) { // special rule from the Api
                this.latestUpdate = Instant.ofEpochMilli(latestUpdateMs).atOffset(zoneOffset);
            }
            return this;
        }

        public Builder withLatestVolume(Long latestVolume) {
            this.latestVolume = latestVolume;
            return this;
        }

        public Builder withIexRealtimePrice(BigDecimal iexRealtimePrice) {
            this.iexRealtimePrice = iexRealtimePrice;
            return this;
        }

        public Builder withIexRealtimeSize(Long iexRealtimeSize) {
            this.iexRealtimeSize = iexRealtimeSize;
            return this;
        }

        public Builder withIexLastUpdated(Long iexLastUpdatedMs) {
            this.iexLastUpdated = Instant.ofEpochMilli(iexLastUpdatedMs).atOffset(zoneOffset);
            return this;
        }

        public Builder withDelayedPrice(BigDecimal delayedPrice) {
            this.delayedPrice = delayedPrice;
            return this;
        }

        public Builder withDelayedPriceTime(Long delayedPriceTimeMs) {
            this.delayedPriceTime = Instant.ofEpochMilli(delayedPriceTimeMs).atOffset(zoneOffset);
            return this;
        }

        public Builder withPreviousClose(BigDecimal previousClose) {
            this.previousClose = previousClose;
            return this;
        }

        public Builder withChange(BigDecimal change) {
            this.change = change;
            return this;
        }

        public Builder withChangePercent(BigDecimal changePercent) {
            this.changePercent = changePercent;
            return this;
        }

        public Builder withIexMarketPercent(BigDecimal iexMarketPercent) {
            this.iexMarketPercent = iexMarketPercent;
            return this;
        }

        public Builder withIexVolume(Long iexVolume) {
            this.iexVolume = iexVolume;
            return this;
        }

        public Builder withAvg30DayVolume(Long avg30DayVolume) {
            this.avg30DayVolume = avg30DayVolume;
            return this;
        }

        public Builder withIexBidPrice(BigDecimal iexBidPrice) {
            this.iexBidPrice = iexBidPrice;
            return this;
        }

        public Builder withIexBidSize(Long iexBidSize) {
            this.iexBidSize = iexBidSize;
            return this;
        }

        public Builder withIexAskPrice(BigDecimal iexAskPrice) {
            this.iexAskPrice = iexAskPrice;
            return this;
        }

        public Builder withIexAskSize(Long iexAskSize) {
            this.iexAskSize = iexAskSize;
            return this;
        }

        public Builder withMarketCap(Long marketCap) {
            this.marketCap = marketCap;
            return this;
        }

        public Builder withPeRatio(BigDecimal peRatio) {
            this.peRatio = peRatio;
            return this;
        }

        public Builder withWeek52High(BigDecimal week52High) {
            this.week52High = week52High;
            return this;
        }

        public Builder withWeek52Low(BigDecimal week52Low) {
            this.week52Low = week52Low;
            return this;
        }

        public Builder withYtdChange(BigDecimal ytdChange) {
            this.ytdChange = ytdChange;
            return this;
        }

        public Quote build() {
            return new Quote(this);
        }

    }
}
