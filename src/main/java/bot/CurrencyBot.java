package bot;


import bot.command.StartCommand;
import fsm.Option;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class CurrencyBot extends TelegramLongPollingCommandBot {
    HashMap<String, Option> clients = new HashMap<>();

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
        switch (update.getCallbackQuery().getData()) {
            case ("�������� ���������� �� ����� �����"):
                break;
            case ("������������"):
                break;
            case ("ʳ������ ����� ���� ����"):
                break;
            case ("������"):
                break;
            case ("����"):
                break;
            case ("��� ��������� ����������"):
                break;
            case ("�� ��������� ����"):
                break;
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
