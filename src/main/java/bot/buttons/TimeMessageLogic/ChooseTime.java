package bot.buttons.TimeMessageLogic;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ChooseTime {
    public static SendMessage setTimeNotation(String chatId, String response, Timer timer){
        String result = "";

        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (response) {
            case "9":
                result = "�� ��������� ���������� �� 9:00";
                timer.setRemaindTime(9);
                timer.setNotationOn(true);
                break;
            case "10":
                result = "�� ��������� ���������� �� 10:00";
                timer.setRemaindTime(10);
                timer.setNotationOn(true);
                break;
            case "11":
                result = "�� ��������� ���������� �� 11:00";
                timer.setRemaindTime(11);
                timer.setNotationOn(true);
                break;
            case "12":
                result = "�� ��������� ���������� �� 12:00";
                timer.setRemaindTime(12);
                timer.setNotationOn(true);
                break;
            case "13":
                result = "�� ��������� ���������� �� 13:00";
                timer.setRemaindTime(13);
                timer.setNotationOn(true);
                break;
            case "14":
                result = "�� ��������� ���������� �� 14:00";
                timer.setRemaindTime(14);
                timer.setNotationOn(true);
                break;
            case "15":
                result = "�� ��������� ���������� �� 15:00";
                timer.setRemaindTime(15);
                timer.setNotationOn(true);
                break;
            case "16":
                result = "�� ��������� ���������� �� 16:00";
                timer.setRemaindTime(16);
                timer.setNotationOn(true);
                break;
            case "17":
                result = "�� ��������� ���������� �� 17:00";
                timer.setRemaindTime(17);
                timer.setNotationOn(true);
                break;
            case "18":
                result = "�� ��������� ���������� �� 18:00";
                timer.setRemaindTime(18);
                timer.setNotationOn(true);
                break;
            case "�� ������� ����":
                break;
            case "�������� �����������":
                result = "�� �������� �����������";
                timer.setNotationOn(false);
                 break;
        }

        message.setText(result);

        return message;
    }
}
