package com.nevex.iextrading.stock.quote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.AbstractClient;
import com.nevex.iextrading.IEXTradingClient;
import com.nevex.iextrading.IEXTradingClientException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by NeVeX on 11/22/2017.
 */
public class StockQuoteClient extends AbstractClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(StockQuoteClient.class);
    private final static String URL = BASE_URL + "/stock";
    private final Request.Builder quoteRequestBuilder;
    private final boolean usePercentageDisplay;

    private StockQuoteClient(IEXTradingClient.Config config, Builder builder) {
        super(config);
        this.usePercentageDisplay = builder.usePercentageDisplay;
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
                .addQueryParameter("displayPercent", Boolean.toString(usePercentageDisplay))
                .build();
    }

    public static BuilderWithParent builder(IEXTradingClient.Builder parentBuilder) { return new BuilderWithParent(parentBuilder); }

    public static Builder builder() { return new BuilderWithoutParent(); }

    public static abstract class Builder<B extends Builder> {
        private boolean usePercentageDisplay = false;
        protected abstract B getImplementingBuilder();

        /**
         * Multiplies all percent values by a factor of 100.
         * <br>Default is false
         */
        public B withPercentageDisplay(boolean usePercentageDisplay) {
            this.usePercentageDisplay = usePercentageDisplay;
            return getImplementingBuilder();
        }

        public StockQuoteClient build(IEXTradingClient.Config config) {
            return new StockQuoteClient(config, this);
        }
    }

    public static class BuilderWithoutParent extends Builder<BuilderWithoutParent> {

        @Override
        protected BuilderWithoutParent getImplementingBuilder() {
            return this;
        }
    }

    /**
     * Wrapper to the builder class with a link back to the parent - useful for complex chain building
     */
    public static class BuilderWithParent extends Builder<BuilderWithParent> {
        private final IEXTradingClient.Builder parentBuilder;

        private BuilderWithParent(IEXTradingClient.Builder parentBuilder) {
            this.parentBuilder = parentBuilder;
        }

        /**
         * Convenience method to get to the parent builder when chaining builders together
         * <br>Note, this does not build the client yet
         */
        public IEXTradingClient.Builder done() {
            return parentBuilder;
        }

        @Override
        protected BuilderWithParent getImplementingBuilder() {
            return this;
        }
    }

}
