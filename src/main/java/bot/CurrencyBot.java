package bot;


import bot.buttons.GetInfoBotton;
import bot.buttons.PropertiesButton;
import bot.command.StartCommand;
import fsm.Option;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class CurrencyBot extends TelegramLongPollingCommandBot {
    private static HashMap<String, Option> clients = new HashMap<>();

    public HashMap<String, Option> getClients() {
        return clients;
    }

    public void setClients(HashMap<String, Option> clients) {
        CurrencyBot.clients = clients;
    }

    public CurrencyBot() {
        register(new StartCommand());
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        Option optionCurrentChat;
        if (!clients.containsKey(chatId)) {
            clients.put(chatId, new Option());
        } else {
            optionCurrentChat = clients.get(chatId);
        }
        try {
            switch (update.getCallbackQuery().getData()) {
                case ("�������� ���������� �� ����� �����"):
                    execute(GetInfoBotton.getInfoMessage(chatId));
                    break;
                case ("������������"):
                    execute(PropertiesButton.getMessage(chatId));
                    break;
                case ("ʳ������ ����� ���� ����"):
                    break;
                case ("������"):
                    break;
                case ("����"):
//                BunkButton.getMessage();
                    break;
                case ("��� ��������� ����������"):
                    break;
                case ("�� ��������� ����"):
                    break;
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }
}
