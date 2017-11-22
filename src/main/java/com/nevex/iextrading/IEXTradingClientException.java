package com.nevex.iextrading;

/**
 * Created by Mark Cunningham on 11/21/2017.
 */
public class IEXTradingClientException extends Exception {

    public IEXTradingClientException(Exception e) {
        super(e);
    }

    public IEXTradingClientException(String message, Exception e) {
        super(message, e);
    }

}
