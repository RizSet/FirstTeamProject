package parser.dto;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyRate {
    BigDecimal sell;
    BigDecimal buy;
    Currencies currency;

    public CurrencyRate(BigDecimal sell, BigDecimal buy, Currencies currency) {
        this.sell = sell;
        this.buy = buy;
        this.currency = currency;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "sell=" + sell +
                ", buy=" + buy +
                ", currency=" + currency +
                '}';
    }
}
