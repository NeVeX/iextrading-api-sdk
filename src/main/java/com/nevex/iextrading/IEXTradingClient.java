package com.nevex.iextrading;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.reference.symbol.ReferenceDataClient;
import com.nevex.iextrading.stock.quote.StockQuoteClient;
import okhttp3.OkHttpClient;

/**
 * Created by NeVeX on 11/21/2017.
 */
public final class IEXTradingClient {

    private final ReferenceDataClient referenceDataClient;
    private final StockQuoteClient stockQuoteClient;

    public IEXTradingClient() {
        OkHttpClient okHttpClient = new OkHttpClient(); // default for now

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        referenceDataClient = new ReferenceDataClient(okHttpClient, objectMapper);
        stockQuoteClient = new StockQuoteClient(okHttpClient, objectMapper);
    }

    public ReferenceDataClient referenceData() {
        return referenceDataClient;
    }

    public StockQuoteClient quotes() {
        return stockQuoteClient;
    }

}
