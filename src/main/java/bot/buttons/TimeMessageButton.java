package bot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeMessageButton {
    public static SendMessage getMessageCreateNotation(String chatId){
        String text = "Виберіть час сповіщення";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);


        KeyboardButton keyboardButton1 = KeyboardButton
                .builder()
                .text("9")
                .build();
        KeyboardButton keyboardButton2 = KeyboardButton
                .builder()
                .text("10")
                .build();
        KeyboardButton keyboardButton3 = KeyboardButton
                .builder()
                .text("11")
                .build();
        KeyboardButton keyboardButton4 = KeyboardButton
                .builder()
                .text("12")
                .build();
        KeyboardButton keyboardButton5 = KeyboardButton
                .builder()
                .text("13")
                .build();
        KeyboardButton keyboardButton6 = KeyboardButton
                .builder()
                .text("14")
                .build();
        KeyboardButton keyboardButton7 = KeyboardButton
                .builder()
                .text("15")
                .build();
        KeyboardButton keyboardButton8 = KeyboardButton
                .builder()
                .text("16")
                .build();
        KeyboardButton keyboardButton9 = KeyboardButton
                .builder()
                .text("17")
                .build();
        KeyboardButton keyboardButton10 = KeyboardButton
                .builder()
                .text("18")
                .build();
        KeyboardButton keyboardButton11 = KeyboardButton
                .builder()
                .text("Вимкнути повідомлення")
                .build();
        KeyboardButton keyboardButton12 = KeyboardButton
                .builder()
                .text("На головне меню")
                .build();

        KeyboardRow row1 = new KeyboardRow(List.of(keyboardButton1, keyboardButton2, keyboardButton3));
        KeyboardRow row2 = new KeyboardRow(List.of(keyboardButton4, keyboardButton5, keyboardButton6));
        KeyboardRow row3 = new KeyboardRow(List.of(keyboardButton7, keyboardButton8, keyboardButton9));
        KeyboardRow row4 = new KeyboardRow(List.of(keyboardButton10, keyboardButton11, keyboardButton12));

        message.setReplyMarkup(ReplyKeyboardMarkup
                .builder()
                .keyboardRow(row1)
                .keyboardRow(row2)
                .keyboardRow(row3)
                .keyboardRow(row4)
                .build());

        return message;
    }

}
