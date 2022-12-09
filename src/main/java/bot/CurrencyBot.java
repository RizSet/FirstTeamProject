package bot;


import bot.command.StartCommand;
import buttons.CurrencyButton;
import fsm.Option;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class CurrencyBot extends TelegramLongPollingCommandBot {
    private static HashMap<String, Option> clients = new HashMap<>();

    public static HashMap<String, Option> getClients() {
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
        }
        optionCurrentChat = clients.get(chatId);
        try {
            switch (update.getCallbackQuery().getData()) {
                case ("Отримати інформацію по курсу валют"):
                    break;
                case ("Налаштування"):
                    break;
                case ("Кількість знаків після коми"):
                    break;
                case ("Валюта"):
                    execute(CurrencyButton.getMessage(chatId));
                    break;
                case ("Банк"):
//                BunkButton.getMessage();
                    break;
                case ("Час отримання повідомлень"):
                    break;
                case ("До головного меню"):
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
