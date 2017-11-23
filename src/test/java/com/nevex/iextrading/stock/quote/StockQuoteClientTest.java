package com.nevex.iextrading.stock.quote;

import com.nevex.iextrading.IEXTradingClient;
import org.junit.Test;

import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by NeVeX on 11/23/2017.
 */
public class StockQuoteClientTest {

    @Test
    public void makeSureCanGetStockQuote() throws Exception {

        IEXTradingClient client = new IEXTradingClient();
        Optional<Quote> appleQuoteOpt = client.quotes().withZoneOffset(ZoneOffset.ofHours(-8)).getQuote("aapl");
        assertNotNull(appleQuoteOpt);
        assertTrue(appleQuoteOpt.isPresent());

        Optional<Quote> microsoftQuoteOpt = client.quotes().getQuote("MSFT");
        assertNotNull(microsoftQuoteOpt);
        assertTrue(microsoftQuoteOpt.isPresent());
    }

    @Test
    public void makeSureNonExistingStockReturnsEmpty() throws Exception {
        assertFalse(new IEXTradingClient().quotes().getQuote("i_do_not_exist").isPresent());
    }

}
