package message;

import fsm.Option;
import parser.Banks;

public class MessageCurrency {
    Option option = new Option();

    public void printMesssage(){
        if (option.getChosenBank().equals(Banks.MONO)) {
            System.out.println("Курс в Monobank");
            for(int i = 0;i < option.getCurrencies().size(); i++) {
                System.out.println();
            }
        } else if(option.getChosenBank().equals(Banks.PRIVATE)) {
            System.out.println("Курс в PrivatBank");
        } else {
            System.out.println("Курс в NBU");
        }
    }
}
