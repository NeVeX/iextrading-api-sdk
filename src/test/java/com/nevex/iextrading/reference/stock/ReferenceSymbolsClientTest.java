package com.nevex.iextrading.reference.stock;

import com.nevex.iextrading.IEXTradingClient;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Mark Cunningham on 11/21/2017.
 */
public class ReferenceSymbolsClientTest {

    @Test
    public void makeSureCanGetSymbols() throws Exception {

        IEXTradingClient client = new IEXTradingClient();
        List<Symbol> symbols = client.symbols().getAllSymbols();

        assertNotNull(symbols);
        assertFalse(symbols.isEmpty());

    }

}
