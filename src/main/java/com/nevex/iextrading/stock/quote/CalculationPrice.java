package com.nevex.iextrading.stock.quote;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by NeVeX on 11/22/2017.
 */
public enum CalculationPrice {

    TOPS("tops"),
    SIP("sip"),
    PREVIOUS_CLOSE("previousclose"),
    CLOSE("close");

    private final String priceName;

    CalculationPrice(String name) {
        this.priceName = name;
    }

    /**
     * Tries to map the given string to a calculation price
     */
    static Optional<CalculationPrice> from(String name) {
        return Stream.of(values()).filter(cp -> StringUtils.equalsIgnoreCase(cp.priceName, name)).findFirst();
    }
}
