package bot.buttons.TimeMessageButtons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NineButton {
    public static SendMessage setNineTimeNotation (String chatId){
        String text = "Випоставили на 9:00";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);

        return message;
    }
}
