package bot.buttons;

import bot.CurrencyBot;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import parser.dto.Currencies;

import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.lang.StrictMath.toIntExact;

public class CurrencyButton {


    public static SendMessage getMessage(String chatId) {
        String text = "Курс якої валюти ви хочете дізнатися?";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        message.setReplyMarkup(createOrEditButton(chatId));

        return message;
    }

    private static InlineKeyboardMarkup createOrEditButton (String chatId){
        List<InlineKeyboardButton> usdButton = Stream.of("USD")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton("USD", chatId)).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> eurButton = Stream.of("EUR")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton("EUR", chatId)).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> backToMainMenuButton = Stream.of("До головного меню")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());

        return InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(usdButton))
                .keyboard(Collections.singleton(eurButton))
                .keyboard(Collections.singleton(backToMainMenuButton))
                .build();
    }

    private static String markButton(String name, String chatId) {
        String mark = "";
        Currencies currency;
        List<Currencies> currencies = CurrencyBot.getClients().get(chatId).getCurrencies();
        if(name.equals("USD")) {
            currency = Currencies.USD;
        } else {
            currency = Currencies.EUR;
        }
        if(currencies.size() > 1) {
           if(currencies.get(0).equals(currency)) {
               mark = EmojiParser.parseToUnicode(":white_check_mark:");
           }
        }
        else {
            for (int i = 0; i < currencies.size(); i++) {
                if (currencies.get(i).equals(currency)) {
                    mark = EmojiParser.parseToUnicode(":white_check_mark:");
                }
            }
        }
     return mark;
    }

    private static EditMessageReplyMarkup editMessageBuilding (Update update){
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        int messageId = toIntExact(update.getCallbackQuery().getMessage().getMessageId());
        String callbackQuery = update.getCallbackQuery().getData();

        Currencies currency;
        if(callbackQuery.equals("USD")) {
            currency = Currencies.USD;
        } else {
            currency = Currencies.EUR;
        }
        List<Currencies> currencies = CurrencyBot.getClients().get(chatId).getCurrencies();
        if(currencies.contains(currency)) {
            currencies.remove(currency);
        } else {
            currencies.add(currency);
        }

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(createOrEditButton(chatId))
                .build();
    }

    public static class TwoButton {
        public static EditMessageReplyMarkup setSingsAfterComma(Update update) {
            return editMessageBuilding(update);
        }
    }

    public static class usdButton {
        public static EditMessageReplyMarkup setCurrency(Update update) {
            return editMessageBuilding(update);
        }
    }

    public static class eurButton {
        public static EditMessageReplyMarkup setCurrency(Update update) {
            return editMessageBuilding(update);
        }
    }
}