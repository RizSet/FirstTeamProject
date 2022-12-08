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

    public String printMesssage(){
        String result = "";
        if (option.getChosenBank().equals(Banks.MONO)) {
            result += "���� � Monobank:\n";
            result += printCurrensy(monobank.getRate(option.getCurrencies()), option.getSingAfterCommas());
        } else if(option.getChosenBank().equals(Banks.PRIVATE)) {
            result += "���� � PrivatBank:\n";
            result += printCurrensy(privatbank.getRate(option.getCurrencies()), option.getSingAfterCommas());
        } else {
            result += "���� � NBU:\n";
            result += printCurrensy(nbu.getRate(option.getCurrencies()), option.getSingAfterCommas());
        }
        return result;
    }

    private String printCurrensy(List<CurrencyRate> currensyList, int singAfterCommas) {
        StringBuilder currensyResult = new StringBuilder();
        if (option.getChosenBank().equals(Banks.NBU)) {
            for (CurrencyRate currensyInfo: currensyList) {
                currensyResult.append(currensyInfo.getCurrency()).append("/UAH\n");
                currensyResult.append("����:")
                        .append(currensyInfo.getBuy().setScale(singAfterCommas, RoundingMode.DOWN))
                                .append("\n");
            }
        } else {
            for (CurrencyRate currensyInfo : currensyList) {
                currensyResult.append(currensyInfo.getCurrency()).append("/UAH\n");
                currensyResult.append("�������:")
                        .append(currensyInfo.getBuy().setScale(singAfterCommas, RoundingMode.DOWN))
                        .append("\n");
                currensyResult.append("������:")
                        .append(currensyInfo.getSell().setScale(singAfterCommas, RoundingMode.DOWN))
                        .append("\n");
            }
        }
        return currensyResult.toString();
    }
}