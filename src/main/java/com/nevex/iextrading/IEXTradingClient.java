package com.nevex.iextrading;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.reference.stock.ReferenceDataClient;
import okhttp3.OkHttpClient;

/**
 * Created by NeVeX on 11/21/2017.
 */
public final class IEXTradingClient {

    private final ReferenceDataClient referenceDataClient;

    public IEXTradingClient() {
        OkHttpClient okHttpClient = new OkHttpClient(); // default for now

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        referenceDataClient = new ReferenceDataClient(okHttpClient, objectMapper);
    }

    public ReferenceDataClient referenceData() {
        return referenceDataClient;
    }

}
