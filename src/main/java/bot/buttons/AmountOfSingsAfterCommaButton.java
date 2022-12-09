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

    public static SendMessage getMessage(String chatId) {
        String text = "Скільки знаків після коми ви бажаєте бачити після коми?";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        message.setReplyMarkup(createOrEditButton(chatId));

        return message;
    }

    private static InlineKeyboardMarkup createOrEditButton (String chatId){
        List<InlineKeyboardButton> rowButton1 = Stream.of("2")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton(2, chatId)).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton2 = Stream.of("3")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton(3, chatId)).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton3 = Stream.of("4")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton(4, chatId)).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton4 = Stream.of("До головного меню")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());

        return InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(rowButton1))
                .keyboard(Collections.singleton(rowButton2))
                .keyboard(Collections.singleton(rowButton3))
                .keyboard(Collections.singleton(rowButton4))
                .build();
    }

    private static String markButton(int number, String chatId) {
        return CurrencyBot.getClients().get(chatId).getSingAfterCommas() == number ? EmojiParser.parseToUnicode(":white_check_mark:") : " ";
    }

    private static EditMessageReplyMarkup editMessageBuilding (int numberOfSign, Update update){

        CurrencyBot.getClients().get(String.valueOf(update.getCallbackQuery().getMessage().getChatId())).setSingAfterCommas(numberOfSign);

        return EditMessageReplyMarkup.builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()))
                .replyMarkup(createOrEditButton(String.valueOf(update.getCallbackQuery().getMessage().getChatId())))
                .build();
    }

    public static class TwoButton {
        public static EditMessageReplyMarkup setSingsAfterComma(Update update) {
            return editMessageBuilding(2, update);
        }
    }

    public static class ThreeButton {
        public static EditMessageReplyMarkup setSingsAfterComma(Update update) {
            return editMessageBuilding(3, update);
        }
    }

    public static class FourButton {
        public static EditMessageReplyMarkup setSingsAfterComma(Update update) {
            return editMessageBuilding(4, update);
        }
    }
}
