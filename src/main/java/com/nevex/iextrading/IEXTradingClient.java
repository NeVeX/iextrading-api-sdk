package com.nevex.iextrading;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.reference.symbol.ReferenceDataClient;
import com.nevex.iextrading.stock.quote.StockQuoteClient;
import okhttp3.OkHttpClient;

import java.time.ZoneOffset;

/**
 * Created by NeVeX on 11/21/2017.
 */
public final class IEXTradingClient {

    private final ReferenceDataClient referenceDataClient;
    private final StockQuoteClient stockQuoteClient;

    private IEXTradingClient(Config config, Builder builder) {
        referenceDataClient = new ReferenceDataClient(config);
        stockQuoteClient = builder.stockQuoteClientBuilder.build(config);
    }

    public ReferenceDataClient referenceData() {
        return referenceDataClient;
    }

    public StockQuoteClient quotes() {
        return stockQuoteClient;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {

        private ZoneOffset zoneOffset = ZoneOffset.UTC;
        private StockQuoteClient.BuilderWithParent stockQuoteClientBuilder;
        private OkHttpClient okHttpClient;
        private ObjectMapper objectMapper;

        private Builder() {
            okHttpClient = new OkHttpClient(); // default for now

            objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            stockQuoteClientBuilder = StockQuoteClient.builder(this);
        }

        public Builder withZoneOffset(ZoneOffset zoneOffset) {
            this.zoneOffset = zoneOffset;
            return this;
        }

        public Builder withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public Builder withOkHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public StockQuoteClient.BuilderWithParent stockQuoteClient() {
            return stockQuoteClientBuilder;
        }

        public IEXTradingClient build() {
            Config config = new Config(this);
            return new IEXTradingClient(config, this);
        }
    }

    public static class Config {

        private final ZoneOffset zoneOffset;
        private final OkHttpClient okHttpClient;
        private final ObjectMapper objectMapper;

        private Config(Builder builder) {
            this.objectMapper = builder.objectMapper;
            this.okHttpClient = builder.okHttpClient;
            this.zoneOffset = builder.zoneOffset;
            if ( zoneOffset == null) { throw new IllegalArgumentException("ZoneOffset is null"); }
            if ( okHttpClient == null) { throw new IllegalArgumentException("OkHttpClient is null"); }
            if ( objectMapper == null) { throw new IllegalArgumentException("ObjectMapper is null"); }
        }

        public ZoneOffset getZoneOffset() {
            return zoneOffset;
        }

        public OkHttpClient getOkHttpClient() {
            return okHttpClient;
        }

        public ObjectMapper getObjectMapper() {
            return objectMapper;
        }
    }

}
