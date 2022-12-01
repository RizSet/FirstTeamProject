package parser;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyApiService {
    BigDecimal getRate (List<String> currencies);
}
