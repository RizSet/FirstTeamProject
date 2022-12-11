package fsm;

import parser.Banks;
import parser.dto.Currencies;

import java.util.Arrays;
import java.util.List;

public class Option {
    private int singAfterCommas = 2;
    private List<Currencies> currencies = Arrays.asList(Currencies.USD);
    private Banks chosenBank = Banks.PRIVAT;

    public int getSingAfterCommas() {
        return singAfterCommas;
    }

    public void setSingAfterCommas(int singAfterCommas) {
        this.singAfterCommas = singAfterCommas;
    }

    public List<Currencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currencies> currencies) {
        this.currencies = currencies;
    }

    public Banks getChosenBank() {
        return chosenBank;
    }

    public void setChosenBank(Banks chosenBank) {
        this.chosenBank = chosenBank;
    }
}
