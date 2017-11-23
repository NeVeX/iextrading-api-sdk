package com.nevex.iextrading.stock.quote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.AbstractClient;
import com.nevex.iextrading.IEXTradingClientException;
import com.nevex.iextrading.reference.symbol.ReferenceDataClient;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by NeVeX on 11/22/2017.
 */
public class StockQuoteClient extends AbstractClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(StockQuoteClient.class);
    private final static String URL = BASE_URL + "/stock";
    private final Request.Builder quoteRequestBuilder;

    private AtomicBoolean usePercentageDisplay = new AtomicBoolean(false);

    @Override
    protected StockQuoteClient withZoneOffset(ZoneOffset zoneOffset) {
        super.changeZoneOffset(zoneOffset);
        return this;
    }

    public StockQuoteClient(OkHttpClient client, ObjectMapper objectMapper) {
        super(client, objectMapper);
        // aapl/quote
        quoteRequestBuilder = new Request.Builder().header("Accept", "application/json").get();
    }

    Optional<Quote> getQuote(String symbol) throws IEXTradingClientException {
        if (StringUtils.isBlank(symbol)) { throw new IEXTradingClientException("Cannot get quote for blank symbol"); }

        Request request = quoteRequestBuilder
                .url(buildUrl(symbol))
                .build();
        QuoteDto quoteDto = super.executeForObject(request, QuoteDto.class);

        if ( quoteDto == null) { return Optional.empty(); }

        try {
            return Optional.of(Quote.from(zoneOffset, quoteDto));
        } catch (Exception e) {
            throw new IEXTradingClientException("Could not create a new instance of Quote from the given API response", e);
        }
    }

    private HttpUrl buildUrl(String symbol) {
        return HttpUrl.parse(URL)
                .newBuilder()
                .addPathSegment(symbol)
                .addPathSegment("quote")
                .addQueryParameter("displayPercent", Boolean.toString(usePercentageDisplay.get()))
                .build();
    }

    /**
     * Multiplies all percent values by a factor of 100.
     * <br>Default is false
     */
    public StockQuoteClient withPercentageDisplay(boolean percentageDisplayEnabled) {
        usePercentageDisplay.set(percentageDisplayEnabled);
        return this;
    }
}
