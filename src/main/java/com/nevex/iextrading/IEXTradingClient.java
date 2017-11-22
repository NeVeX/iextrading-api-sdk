package com.nevex.iextrading;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.reference.stock.ReferenceSymbolsClient;
import okhttp3.OkHttpClient;

/**
 * Created by NeVeX on 11/21/2017.
 */
public final class IEXTradingClient {

    public final static String BASE_URL = "https://api.iextrading.com/1.0";
    private final ReferenceSymbolsClient referenceSymbolsClient;

    public IEXTradingClient() {
        OkHttpClient okHttpClient = new OkHttpClient(); // default for now

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        referenceSymbolsClient = new ReferenceSymbolsClient(okHttpClient, objectMapper);
    }

    public ReferenceSymbolsClient symbols() {
        return referenceSymbolsClient;
    }

}
