package bot;

import bot.buttons.AmountOfSingsAfterCommaButton;
import bot.buttons.CurrencyButton;
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

    public static HashMap<String, Option> getClients() {
        return clients;
    }

    public static void setClients(HashMap<String, Option> clients) {
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
                case ("Отримати інформацію по курсу валют"):
                    execute(GetInfoBotton.getInfoMessage(chatId));
                    break;
                case ("Налаштування"):
                    execute(PropertiesButton.getMessage(chatId));
                    break;
                case ("Кількість знаків після коми"):
                    execute(AmountOfSingsAfterCommaButton.getMessage(chatId));
                    break;
                case ("Валюти"):
                    execute(CurrencyButton.getMessage(chatId));
                    break;
                case ("USD"):
                    execute(CurrencyButton.UsdButton.setCurrency(update));
                    break;
                case ("EUR"):
                    execute(CurrencyButton.EurButton.setCurrency(update));
                    break;
                case ("Банк"):
//                BunkButton.getMessage();
                    break;
                case ("Час отримання повідомлень"):
                    break;
                case ("До головного меню"):
                    break;
                case ("2"):
                    execute(AmountOfSingsAfterCommaButton.TwoButton.setSingsAfterComma(update));
                    break;
                case ("3"):
                    execute(AmountOfSingsAfterCommaButton.ThreeButton.setSingsAfterComma(update));
                    break;
                case ("4"):
                    execute(AmountOfSingsAfterCommaButton.FourButton.setSingsAfterComma(update));
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
