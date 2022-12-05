package parser.dto;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyRate {
    BigDecimal sell;
    BigDecimal buy;
    Currencies currency;

    public CurrencyRate(BigDecimal sell, BigDecimal buy, Currencies currency) {
        setSell(sell);
        setBuy(buy);
        setCurrency(currency);
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
}
