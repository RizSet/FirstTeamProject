package buttons;

import bot.CurrencyBot;
import com.vdurmont.emoji.EmojiParser;
import fsm.Option;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import parser.dto.Currencies;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

public class CurrencyButton {
    static List<InlineKeyboardButton> rowButton1;
    static List<InlineKeyboardButton> rowButton2;
    static List<InlineKeyboardButton> rowButton3;

    public static SendMessage getMessage(String chatId){
        String text  = "За курсом яких валют ви бажаєте стежити?";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);

        rowButton1 = Stream.of("USD")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        rowButton2 = Stream.of("EUR")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        rowButton3 = Stream.of("До головного меню")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(rowButton1))
                .keyboard(Collections.singleton(rowButton2))
                .keyboard(Collections.singleton(rowButton3))
                .build();

        message.setReplyMarkup(keyboard);

        return message;
    }

    public static void unmarkButton(String text) {
        switch (text) {
            case "USD":
                rowButton1 = Stream.of("USD")
                        .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
            case "EUR":
                rowButton2 = Stream.of("EUR")
                        .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
        }
    }

    public static void markButton(String text) {
        switch (text) {
            case "USD":
                rowButton1 = Stream.of("2")
                        .map(it -> InlineKeyboardButton.builder().text(it + EmojiParser.parseToUnicode(":white_check_mark:")).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
            case "EUR":
                rowButton2 = Stream.of("3")
                        .map(it -> InlineKeyboardButton.builder().text(it + EmojiParser.parseToUnicode(":white_check_mark:")).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
        }
    }

    public static class UsdButton {
        public static EditMessageReplyMarkup setCurrencies(Update update) {
            EditMessageText newMessage = new EditMessageText();
            newMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            newMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
            newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
            List<Currencies> currencies = CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).getCurrencies();

            if(currencies.contains(Currencies.USD)){
                currencies.remove(Currencies.USD);
                unmarkButton("USD");
            } else{
                currencies.add(Currencies.USD);
                markButton("USD");
            }

            return EditMessageReplyMarkup.builder()
                    .chatId(newMessage.getChatId())
                    .messageId(newMessage.getMessageId())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .keyboard(Collections.singleton(rowButton1))
                            .keyboard(Collections.singleton(rowButton2))
                            .keyboard(Collections.singleton(rowButton3))
                            .build())
                    .build();
        }
    }

    public static class EurButton {
        public static EditMessageReplyMarkup setCurrencies(Update update) {
            EditMessageText newMessage = new EditMessageText();
            newMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            newMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
            newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
            List<Currencies> currencies = CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).getCurrencies();

            if(currencies.contains(Currencies.EUR)){
                currencies.remove(Currencies.EUR);
                unmarkButton("EUR");
            } else{
                currencies.add(Currencies.EUR);
                markButton("EUR");
            }

            return EditMessageReplyMarkup.builder()
                    .chatId(newMessage.getChatId())
                    .messageId(newMessage.getMessageId())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .keyboard(Collections.singleton(rowButton1))
                            .keyboard(Collections.singleton(rowButton2))
                            .keyboard(Collections.singleton(rowButton3))
                            .build())
                    .build();
        }
    }
}
