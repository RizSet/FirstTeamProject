package bot.buttons.TimeMessageButtons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NineButton {
    public static SendMessage setNineTimeNotation (String chatId, String response){
        String result = "";
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (response) {
            case "9":
                result = "Ви поставили поідомлення на 9:00";
                break;
            case "10":
                result = "Ви поставили поідомлення на 10:00";
                break;
            case "11":
                result = "Ви поставили поідомлення на 11:00";
                break;
            case "12":
                result = "Ви поставили поідомлення на 12:00";
                break;
            case "13":
                result = "Ви поставили поідомлення на 13:00";
                break;
            case "14":
                result = "Ви поставили поідомлення на 14:00";
                break;
            case "15":
                result = "Ви поставили поідомлення на 15:00";
                break;
            case "16":
                result = "Ви поставили поідомлення на 16:00";
                break;
            case "17":
                result = "Ви поставили поідомлення на 17:00";
                break;
            case "18":
                result = "Ви поставили поідомлення на 18:00";
                break;
            case "На головне меню":
                break;
            case "Вимкнути повідомлення":
                result = "Ви вимкнули повідомлення";
                 break;
        }

        message.setText(result);
        return message;
    }
}
