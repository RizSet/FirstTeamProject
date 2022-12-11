package bot.buttons;

import bot.CurrencyBot;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.StrictMath.toIntExact;

public class AmountOfSingsAfterCommaButton {
    static List<InlineKeyboardButton> rowButton1;
    static List<InlineKeyboardButton> rowButton2;
    static List<InlineKeyboardButton> rowButton3;
    static List<InlineKeyboardButton> rowButton4;

    public static SendMessage getMessage(String chatId) {
        String text = "Скільки знаків після коми ви бажаєте бачити після коми?";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);

        rowButton1 = Stream.of("2")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        rowButton2 = Stream.of("3")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        rowButton3 = Stream.of("4")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        rowButton4 = Stream.of("До головного меню")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());


        markButton(CurrencyBot.getClients().get(chatId).getSingAfterCommas());

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(rowButton1))
                .keyboard(Collections.singleton(rowButton2))
                .keyboard(Collections.singleton(rowButton3))
                .keyboard(Collections.singleton(rowButton4))
                .build();

        message.setReplyMarkup(keyboard);

        return message;
    }

    public static void unmarkButton(int number) {
        switch (number) {
            case 2:
                rowButton1 = Stream.of("2")
                        .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
            case 3:
                rowButton2 = Stream.of("3")
                        .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
            case 4:
                rowButton3 = Stream.of("4")
                        .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
        }
    }

    public static void markButton(int number) {
        switch (number) {
            case 2:
                rowButton1 = Stream.of("2")
                        .map(it -> InlineKeyboardButton.builder().text(it + EmojiParser.parseToUnicode(":white_check_mark:")).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
            case 3:
                rowButton2 = Stream.of("3")
                        .map(it -> InlineKeyboardButton.builder().text(it + EmojiParser.parseToUnicode(":white_check_mark:")).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
//
            case 4:
                rowButton3 = Stream.of("4")
                        .map(it -> InlineKeyboardButton.builder().text(it + EmojiParser.parseToUnicode(":white_check_mark:")).callbackData(it).build())
                        .collect(Collectors.toList());
                break;
        }
    }

    public static class TwoButton {
        public static EditMessageReplyMarkup setSingsAfterComma(Update update) {
            EditMessageText newMessage = new EditMessageText();
            newMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            newMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
            newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());

            unmarkButton(CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).getSingAfterCommas());
            CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).setSingAfterCommas(2);
            markButton(2);

            return EditMessageReplyMarkup.builder()
                    .chatId(newMessage.getChatId())
                    .messageId(newMessage.getMessageId())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .keyboard(Collections.singleton(rowButton1))
                            .keyboard(Collections.singleton(rowButton2))
                            .keyboard(Collections.singleton(rowButton3))
                            .keyboard(Collections.singleton(rowButton4))
                            .build())
                    .build();
        }
    }

    public static class ThreeButton {
        public static EditMessageReplyMarkup setSingsAfterComma(Update update) {
            EditMessageText newMessage = new EditMessageText();
            newMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            newMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
            newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());

            unmarkButton(CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).getSingAfterCommas());
            CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).setSingAfterCommas(3);
            markButton(3);
            return EditMessageReplyMarkup.builder()
                    .chatId(newMessage.getChatId())
                    .messageId(newMessage.getMessageId())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .keyboard(Collections.singleton(rowButton1))
                            .keyboard(Collections.singleton(rowButton2))
                            .keyboard(Collections.singleton(rowButton3))
                            .keyboard(Collections.singleton(rowButton4))
                            .build())
                    .build();
        }
    }

    public static class FourButton {
        public static EditMessageReplyMarkup setSingsAfterComma(Update update) {
            EditMessageText newMessage = new EditMessageText();
            newMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            newMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
            newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());

            unmarkButton(CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).getSingAfterCommas());
            CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).setSingAfterCommas(4);
            markButton(4);
            return EditMessageReplyMarkup.builder()
                    .chatId(newMessage.getChatId())
                    .messageId(newMessage.getMessageId())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .keyboard(Collections.singleton(rowButton1))
                            .keyboard(Collections.singleton(rowButton2))
                            .keyboard(Collections.singleton(rowButton3))
                            .keyboard(Collections.singleton(rowButton4))
                            .build())
                    .build();
        }
    }
}
