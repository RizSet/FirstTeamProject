package bot;


import bot.buttons.GetInfoBotton;
import bot.buttons.PropertiesButton;
import bot.buttons.TimeMessageButton;
import bot.buttons.TimeMessageButtons.NineButton;
import bot.command.StartCommand;
import fsm.Option;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

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

        if (update.hasMessage()) {
            try {
                execute(NineButton.setNineTimeNotation(chatId));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            switch (update.getCallbackQuery().getData()) {
                case ("Отримати інформацію по курсу валют"):
                    execute(GetInfoBotton.getInfoMessage(chatId));
                    break;
                case ("Налаштування"):
                    execute(PropertiesButton.getMessage(chatId));
                    break;
                case ("Кількість знаків після коми"):
                    break;
                case ("Валюта"):
                    break;
                case ("Банк"):
//                BunkButton.getMessage();
                    break;
                case ("Час сповіщення"):
                    execute(TimeMessageButton.getMessageCreateNotation(chatId));
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
