package message;

import fsm.Option;
import parser.Banks;
import parser.CurrencyApiService;
import parser.CurrencyMonoParser;
import parser.dto.CurrencyRate;

import java.math.RoundingMode;
import java.util.List;

public class MessageCurrency {
    Option option = new Option();
    CurrencyApiService monobank = new CurrencyMonoParser();

    public void printMesssage(){
        if (option.getChosenBank().equals(Banks.MONO)) {
            System.out.println("Курс в Monobank:");
            printCurrensy(monobank.getRate(option.getCurrencies()), option.getSingAfterCommas());
        } else if(option.getChosenBank().equals(Banks.PRIVATE)) {
            System.out.println("Курс в PrivatBank");
        } else {
            System.out.println("Курс в NBU");
        }
    }

    private void printCurrensy(List<CurrencyRate> currensyList, int singAfterCommas) {
        for (CurrencyRate currensyInfo: currensyList) {
            System.out.println(currensyInfo.getCurrency() + "/UAH");
            System.out.println("Покупка:" + currensyInfo.getBuy().setScale(singAfterCommas, RoundingMode.DOWN));
            System.out.println("Продаж:" + currensyInfo.getSell().setScale(singAfterCommas, RoundingMode.DOWN));
        }
    }
}
