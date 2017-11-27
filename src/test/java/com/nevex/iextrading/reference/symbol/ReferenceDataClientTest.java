package com.nevex.iextrading.reference.symbol;

import com.nevex.iextrading.IEXTradingClient;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by NeVeX on 11/21/2017.
 */
public class ReferenceDataClientTest {

    @Test
    public void makeSureCanGetSymbols() throws Exception {

        IEXTradingClient client = IEXTradingClient.builder().build();
        Set<Symbol> symbols = client.referenceData().getAllSymbols();

        assertNotNull(symbols);
        assertFalse(symbols.isEmpty());

    }

}
