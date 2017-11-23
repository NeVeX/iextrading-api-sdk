package com.nevex.iextrading.stock.quote;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by NeVeX on 11/22/2017.
 */
final class QuoteDto {

    private final String symbol;
    private final String companyName;
    private final String primaryExchange;
    private final String sector;
    private final String calculationPrice; // enum mapping

    private final BigDecimal open;
    private final Long openTime; // looks like epoch

    private final BigDecimal close;
    private final Long closeTime; // looks like epcoh

    private final BigDecimal latestPrice;
    private final String latestSource;

    private final String latestTime; // human readable time depending on the latestSource
    private final Long latestUpdate; // epoch
    private final Long latestVolume;

    private final BigDecimal iexRealtimePrice;
    private final Long iexRealtimeSize;
    private final Long iexLastUpdated; // epoch

    private final BigDecimal delayedPrice;
    private final Long delayedPriceTime; // epoch?

    private final BigDecimal previousClose;
    private final BigDecimal change;
    private final BigDecimal changePercent;

    private final BigDecimal iexMarketPercent;
    private final Long iexVolume;
    private final Long avgTotalVolume; // 30 day average volume

    private final BigDecimal iexBidPrice;
    private final Long iexBidSize;
    private final BigDecimal iexAskPrice;
    private final Long iexAskSize;

    private final Long marketCap;
    private final BigDecimal peRatio;

    private final BigDecimal week52High;
    private final BigDecimal week52Low;
    private final BigDecimal ytdChange;

    @JsonCreator
    public QuoteDto(@JsonProperty("symbol") String symbol,
                    @JsonProperty("companyName") String companyName,
                    @JsonProperty("primaryExchange") String primaryExchange,
                    @JsonProperty("sector") String sector,
                    @JsonProperty("calculationPrice") String calculationPrice,
                    @JsonProperty("open") BigDecimal open,
                    @JsonProperty("openTime") Long openTime,
                    @JsonProperty("close") BigDecimal close,
                    @JsonProperty("closeTime") Long closeTime,
                    @JsonProperty("latestPrice") BigDecimal latestPrice,
                    @JsonProperty("latestSource") String latestSource,
                    @JsonProperty("latestTime") String latestTime,
                    @JsonProperty("latestUpdate") Long latestUpdate,
                    @JsonProperty("latestVolume") Long latestVolume,
                    @JsonProperty("iexRealtimePrice") BigDecimal iexRealtimePrice,
                    @JsonProperty("iexRealtimeSize") Long iexRealtimeSize,
                    @JsonProperty("iexLastUpdated") Long iexLastUpdated,
                    @JsonProperty("delayedPrice") BigDecimal delayedPrice,
                    @JsonProperty("delayedPriceTime") Long delayedPriceTime,
                    @JsonProperty("previousClose") BigDecimal previousClose,
                    @JsonProperty("change") BigDecimal change,
                    @JsonProperty("changePercent") BigDecimal changePercent,
                    @JsonProperty("iexMarketPercent") BigDecimal iexMarketPercent,
                    @JsonProperty("iexVolume") Long iexVolume,
                    @JsonProperty("avgTotalVolume") Long avgTotalVolume,
                    @JsonProperty("iexBidPrice") BigDecimal iexBidPrice,
                    @JsonProperty("iexBidSize") Long iexBidSize,
                    @JsonProperty("iexAskPrice") BigDecimal iexAskPrice,
                    @JsonProperty("iexAskSize") Long iexAskSize,
                    @JsonProperty("marketCap") Long marketCap,
                    @JsonProperty("peRatio") BigDecimal peRatio,
                    @JsonProperty("week52High") BigDecimal week52High,
                    @JsonProperty("week52Low") BigDecimal week52Low,
                    @JsonProperty("ytdChange") BigDecimal ytdChange) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.primaryExchange = primaryExchange;
        this.sector = sector;
        this.calculationPrice = calculationPrice;
        this.open = open;
        this.openTime = openTime;
        this.close = close;
        this.closeTime = closeTime;
        this.latestPrice = latestPrice;
        this.latestSource = latestSource;
        this.latestTime = latestTime;
        this.latestUpdate = latestUpdate;
        this.latestVolume = latestVolume;
        this.iexRealtimePrice = iexRealtimePrice;
        this.iexRealtimeSize = iexRealtimeSize;
        this.iexLastUpdated = iexLastUpdated;
        this.delayedPrice = delayedPrice;
        this.delayedPriceTime = delayedPriceTime;
        this.previousClose = previousClose;
        this.change = change;
        this.changePercent = changePercent;
        this.iexMarketPercent = iexMarketPercent;
        this.iexVolume = iexVolume;
        this.avgTotalVolume = avgTotalVolume;
        this.iexBidPrice = iexBidPrice;
        this.iexBidSize = iexBidSize;
        this.iexAskPrice = iexAskPrice;
        this.iexAskSize = iexAskSize;
        this.marketCap = marketCap;
        this.peRatio = peRatio;
        this.week52High = week52High;
        this.week52Low = week52Low;
        this.ytdChange = ytdChange;
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

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public BigDecimal getClose() {
        return close;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public Long getLatestUpdate() {
        return latestUpdate;
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

    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    public BigDecimal getDelayedPrice() {
        return delayedPrice;
    }

    public Long getDelayedPriceTime() {
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

    public Long getAvgTotalVolume() {
        return avgTotalVolume;
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
}
