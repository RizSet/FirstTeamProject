package bot.buttons.TimeMessageButtons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NineButton {
    public static SendMessage setNineTimeNotation (String chatId, String response){
        String result = "";
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (response) {
            case "9":
                result = "�� ��������� ���������� �� 9:00";
                break;
            case "10":
                result = "�� ��������� ���������� �� 10:00";
                break;
            case "11":
                result = "�� ��������� ���������� �� 11:00";
                break;
            case "12":
                result = "�� ��������� ���������� �� 12:00";
                break;
            case "13":
                result = "�� ��������� ���������� �� 13:00";
                break;
            case "14":
                result = "�� ��������� ���������� �� 14:00";
                break;
            case "15":
                result = "�� ��������� ���������� �� 15:00";
                break;
            case "16":
                result = "�� ��������� ���������� �� 16:00";
                break;
            case "17":
                result = "�� ��������� ���������� �� 17:00";
                break;
            case "18":
                result = "�� ��������� ���������� �� 18:00";
                break;
            case "�� ������� ����":
                break;
            case "�������� �����������":
                result = "�� �������� �����������";
                 break;
        }

        message.setText(result);
        return message;
    }
}
