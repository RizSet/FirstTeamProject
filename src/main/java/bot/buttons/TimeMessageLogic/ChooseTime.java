package bot.buttons.TimeMessageLogic;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ChooseTime {
    public static SendMessage setTimeNotation(String chatId, String response, Timer timer){
        String result = "";

        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (response) {
            case "9":
                result = "Ви поставили поідомлення на 9:00";
                timer.setRemaindTime(9);
                timer.setNotationOn(true);
                break;
            case "10":
                result = "Ви поставили поідомлення на 10:00";
                timer.setRemaindTime(10);
                timer.setNotationOn(true);
                break;
            case "11":
                result = "Ви поставили поідомлення на 11:00";
                timer.setRemaindTime(11);
                timer.setNotationOn(true);
                break;
            case "12":
                result = "Ви поставили поідомлення на 12:00";
                timer.setRemaindTime(12);
                timer.setNotationOn(true);
                break;
            case "13":
                result = "Ви поставили поідомлення на 13:00";
                timer.setRemaindTime(13);
                timer.setNotationOn(true);
                break;
            case "14":
                result = "Ви поставили поідомлення на 14:00";
                timer.setRemaindTime(14);
                timer.setNotationOn(true);
                break;
            case "15":
                result = "Ви поставили поідомлення на 15:00";
                timer.setRemaindTime(15);
                timer.setNotationOn(true);
                break;
            case "16":
                result = "Ви поставили поідомлення на 16:00";
                timer.setRemaindTime(16);
                timer.setNotationOn(true);
                break;
            case "17":
                result = "Ви поставили поідомлення на 17:00";
                timer.setRemaindTime(17);
                timer.setNotationOn(true);
                break;
            case "18":
                result = "Ви поставили поідомлення на 18:00";
                timer.setRemaindTime(18);
                timer.setNotationOn(true);
                break;
            case "На головне меню":
                break;
            case "Вимкнути повідомлення":
                result = "Ви вимкнули повідомлення";
                timer.setNotationOn(false);
                 break;
        }

        message.setText(result);

        return message;
    }
}
