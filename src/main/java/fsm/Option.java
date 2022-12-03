package fsm;

import parser.Banks;
import parser.dto.Currencies;

import java.util.Arrays;
import java.util.List;

public class Option {

    int singAfterCommas = 2;
    private List<Currencies> currencies = Arrays.asList(Currencies.USD);
    private Banks chosenBank = Banks.PRIVATE;

}
