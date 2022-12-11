package bot.buttons;

import bot.CurrencyBot;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import parser.dto.Currencies;

import java.util.ArrayList;
import java.util.Collections;
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
        message.setReplyMarkup(createButtons(chatId));
        return message;
    }

    private static InlineKeyboardMarkup createButtons(String chatId){
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
    private static InlineKeyboardMarkup editUsdButton(String chatId) {
        List<InlineKeyboardButton> usdButton;
        if (UsdButton.getIsMarked()) {
            usdButton = Stream.of("USD")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + unmarkButton("USD", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());
            UsdButton.setIsMarked(false);
        } else {
            usdButton = Stream.of("USD")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton("USD", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());
            UsdButton.setIsMarked(true);
        }
        List<InlineKeyboardButton> eurButton;
        if (EurButton.getIsMarked()) {
            eurButton = Stream.of("EUR")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton("EUR", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());
        } else {
            eurButton = Stream.of("EUR")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + unmarkButton("EUR", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());
        }
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
    private static InlineKeyboardMarkup editEurButton(String chatId) {
        List<InlineKeyboardButton> usdButton;
        if (UsdButton.getIsMarked()) {
            usdButton = Stream.of("USD")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton("USD", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());
        } else {
            usdButton = Stream.of("USD")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + unmarkButton("USD", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());

        }
        List<InlineKeyboardButton> eurButton;
        if (EurButton.getIsMarked()) {
            eurButton = Stream.of("EUR")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + unmarkButton("EUR", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());
            EurButton.setIsMarked(false);
        } else {
            eurButton = Stream.of("EUR")
                    .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton("EUR", chatId)).callbackData(it).build())
                    .collect(Collectors.toList());
            EurButton.setIsMarked(true);
        }
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
     return EmojiParser.parseToUnicode(":white_check_mark:");
    }
    private static String unmarkButton(String name, String chatId) {
        return "";
    }


    private static EditMessageReplyMarkup editMessageBuildingUsd (Update update) {
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        int messageId = toIntExact(update.getCallbackQuery().getMessage().getMessageId());
        List<Currencies> currencies = new ArrayList<>();
        boolean isMarked = UsdButton.getIsMarked();
        if (isMarked) {
            currencies.add(Currencies.EUR);
        } else {
            currencies.add(Currencies.USD);
            currencies.add(Currencies.EUR);
        }
        CurrencyBot.getClients().get(chatId).setCurrencies(currencies);
        return EditMessageReplyMarkup.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .replyMarkup(editUsdButton(chatId))
                    .build();

    }
    private static EditMessageReplyMarkup editMessageBuildingEur (Update update) {
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        int messageId = toIntExact(update.getCallbackQuery().getMessage().getMessageId());
        List<Currencies> currencies = new ArrayList<>();
        boolean isMarked = EurButton.getIsMarked();
        if (isMarked) {
            currencies.add(Currencies.USD);
        } else {
            currencies.add(Currencies.USD);
            currencies.add(Currencies.EUR);
        }
        CurrencyBot.getClients().get(chatId).setCurrencies(currencies);
        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(editEurButton(chatId))
                .build();

    }


    public static class UsdButton {
    private static boolean isMarked = true;

        public static boolean getIsMarked() {
            return isMarked;
        }

        public static void setIsMarked(boolean isMarked) {
            UsdButton.isMarked = isMarked;
        }

        public static EditMessageReplyMarkup setCurrency(Update update) {
            return editMessageBuildingUsd(update);
        }
    }

    public static class EurButton {
        private static boolean isMarked = true;

        public static boolean getIsMarked() {
            return isMarked;
        }

        public static void setIsMarked(boolean isMarked) {
            EurButton.isMarked = isMarked;
        }

        public static EditMessageReplyMarkup setCurrency(Update update) {
            return editMessageBuildingEur(update);
        }
    }
}