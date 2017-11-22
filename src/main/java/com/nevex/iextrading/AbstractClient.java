package com.nevex.iextrading;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NeVeX on 11/22/2017.
 */
public abstract class AbstractClient {

    protected final static String BASE_URL = "https://api.iextrading.com/1.0";
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    protected AbstractClient(OkHttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    protected <T> List<T> executeRequestForList(Request request, TypeReference<List<T>> typeReference) throws IEXTradingClientException {

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new IEXTradingClientException(e);
        }

        if ( !response.isSuccessful() || response.body() == null ) {
            return new ArrayList<>();
        }

        List<T> dataList;
        try {
            dataList = objectMapper.readValue(response.body().byteStream(), typeReference);
        } catch (IOException e) {
            throw new IEXTradingClientException(e);
        }

        if ( dataList == null ) {
            return new ArrayList<>();
        }
        return dataList;
    }

}
