package com.nevex.iextrading;

import org.junit.Test;

import java.time.ZoneOffset;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by NeVeX on 11/26/2017.
 */
public class IEXTradingClientTest {

    @Test
    public void makeSureSimpleBuilderWorks() {
        IEXTradingClient client = IEXTradingClient.builder().build();
        assertNotNull(client);
    }

    @Test
    public void makeSureComplexBuilderWorks() {
        IEXTradingClient client =
                IEXTradingClient.builder()
                        .withZoneOffset(ZoneOffset.UTC)
                        .stockQuoteClient()
                            .withPercentageDisplay(true)
                            .done()
                .build();
        assertNotNull(client);
    }

}
