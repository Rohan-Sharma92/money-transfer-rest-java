package com.rohansharma.money_transfer_rest_java.domain;

import java.math.BigDecimal;
import java.util.Currency;

public class FxRate {

    private final Currency source;
    private final Currency target;
    private final BigDecimal rate;

    public FxRate(Currency source, Currency target, BigDecimal rate){
        this.source = source;
        this.target = target;
        this.rate = rate;
    }

    public Currency getSource() {
        return source;
    }

    public Currency getTarget() {
        return target;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
