package com.nevex.iextrading.stock.quote;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by NeVeX on 11/22/2017.
 */
public enum CalculationPriceSource {

    IEX_REAL_TIME("IEX real time price"),
    FIFTEEN_MINUTE_DELAY("15 minute delayed price"),
    PREVIOUS_CLOSE("Previous close"),
    CLOSE("Close");

    private final String sourceName;

    CalculationPriceSource(String name) {
        this.sourceName = name;
    }

    /**
     * Tries to map the given string to a calculation price source
     */
    static Optional<CalculationPriceSource> from(String name) {
        return Stream.of(values()).filter(cp -> StringUtils.equalsIgnoreCase(cp.sourceName, name)).findFirst();
    }
}
