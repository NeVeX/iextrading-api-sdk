package com.nevex.iextrading.reference.stock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevex.iextrading.AbstractClient;
import com.nevex.iextrading.IEXTradingClientException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by NeVeX on 11/21/2017.
 */
public final class ReferenceDataClient extends AbstractClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReferenceDataClient.class);
    private final Request symbolsRequest;

    public ReferenceDataClient(OkHttpClient client, ObjectMapper objectMapper) {
        super(client, objectMapper);
        this.symbolsRequest = new Request.Builder().url(BASE_URL + "/ref-data/symbols").header("Accept", "application/json").get().build();
    }

    public Set<Symbol> getAllSymbols() throws IEXTradingClientException {

        List<SymbolDto> symbolDtos = executeRequestForList(symbolsRequest, new TypeReference<List<SymbolDto>>(){});
        Set<Symbol> symbols = new TreeSet<>();
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
