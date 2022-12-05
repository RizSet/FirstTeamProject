package message;

import fsm.Option;
import parser.*;
import parser.dto.CurrencyRate;

import java.math.RoundingMode;
import java.util.List;

public class MessageCurrency {
    Option option = new Option();
    CurrencyApiService monobank = new CurrencyMonoParser();
    CurrencyApiService privatbank = new CurrencyPrivateParser();
    CurrencyApiService nbu = new CurrencyNBUParser();

    public void printMesssage(){
        if (option.getChosenBank().equals(Banks.MONO)) {
            System.out.println("Курс в Monobank:");
            printCurrensy(monobank.getRate(option.getCurrencies()), option.getSingAfterCommas());
        } else if(option.getChosenBank().equals(Banks.PRIVATE)) {
            System.out.println("Курс в PrivatBank:");
            printCurrensy(privatbank.getRate(option.getCurrencies()), option.getSingAfterCommas());
        } else {
            System.out.println("Курс в NBU:");
            printCurrensy(nbu.getRate(option.getCurrencies()), option.getSingAfterCommas());
        }
    }

    private void printCurrensy(List<CurrencyRate> currensyList, int singAfterCommas) {
        if (option.getChosenBank().equals(Banks.NBU)) {
            for (CurrencyRate currensyInfo: currensyList) {
                System.out.println(currensyInfo.getCurrency() + "/UAH");
                System.out.println("Курс:" + currensyInfo.getBuy().setScale(singAfterCommas, RoundingMode.DOWN));
            }
        } else {
            for (CurrencyRate currensyInfo : currensyList) {
                System.out.println(currensyInfo.getCurrency() + "/UAH");
                System.out.println("Покупка:" + currensyInfo.getBuy().setScale(singAfterCommas, RoundingMode.DOWN));
                System.out.println("Продаж:" + currensyInfo.getSell().setScale(singAfterCommas, RoundingMode.DOWN));
            }
        }
    }
}
