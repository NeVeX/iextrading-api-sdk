package com.nevex.iextrading.reference.stock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.IEXTradingClient;
import com.nevex.iextrading.IEXTradingClientException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Cunningham on 11/21/2017.
 */
public final class ReferenceSymbolsClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReferenceSymbolsClient.class);
    private final static String URL = IEXTradingClient.BASE_URL + "/ref-data/symbols";
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public ReferenceSymbolsClient(OkHttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public List<Symbol> getAllSymbols() throws IEXTradingClientException {
        Request request = new Request.Builder().url(URL).header("Accept", "application/json").get().build();
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new IEXTradingClientException(e);
        }

        if ( !response.isSuccessful() || response.body() == null ) {
            return new ArrayList<>();
        }

        List<SymbolDto> symbolDtos;
        try {
            symbolDtos = objectMapper.readValue(response.body().byteStream(), new TypeReference<List<SymbolDto>>(){});
        } catch (IOException e) {
            throw new IEXTradingClientException(e);
        }

        if ( symbolDtos == null || symbolDtos.isEmpty()) {
            return new ArrayList<>();
        }

        List<Symbol> symbols = new ArrayList<>();
        for ( SymbolDto dto : symbolDtos) {
            try {
                symbols.add(Symbol.fromDto(dto));
            } catch (Exception e) {
                LOGGER.warn("Could not map symbol dto {} into a symbol. Error: {}", dto, e.getMessage());
            }
        }
        return symbols;
    }

}
