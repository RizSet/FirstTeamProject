package parser;

import parser.dto.Currencies;
import parser.dto.CurrencyRate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface CurrencyApiService {
    List<CurrencyRate> getRate (List<Currencies> currencies) throws IOException;
}
