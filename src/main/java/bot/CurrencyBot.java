package bot;

import bot.buttons.AmountOfSingsAfterCommaButton;
import bot.buttons.BankButton;
import bot.buttons.GetInfoBotton;
import bot.buttons.PropertiesButton;
import bot.command.StartCommand;
import fsm.Option;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        }
        optionCurrentChat = clients.get(chatId);
        try {
            switch (update.getCallbackQuery().getData()) {
                case ("Отримати інформацію по курсу валют"):
                    execute(GetInfoBotton.getInfoMessage(chatId, optionCurrentChat));
                    break;
                case ("Налаштування"):
                    execute(PropertiesButton.getMessage(chatId));
                    break;
                case ("Кількість знаків після коми"):
                    execute(AmountOfSingsAfterCommaButton.getMessage(chatId));
                    break;
                case ("Валюта"):
                    break;
                case ("Р‘Р°РЅРє Р· СЏРєРѕРіРѕ Р±СѓРґРµ Р±СЂР°С‚РёСЃСЊ РєСѓСЂСЃ"):
                    execute(BankButton.getMessage(chatId,optionCurrentChat));
                    break;
                case ("РџСЂРёРІР°С‚Р‘Р°РЅРє"):
                    optionCurrentChat.setChosenBank(Banks.PRIVAT);
                    execute(BankButton.getUpdatedKeyboard(update, optionCurrentChat));
                    break;
                case ("РќР‘РЈ"):
                    optionCurrentChat.setChosenBank(Banks.NBU);
                    execute(BankButton.getUpdatedKeyboard(update, optionCurrentChat));
                    break;
                case ("РњРѕРЅРѕР‘Р°РЅРє"):
                    optionCurrentChat.setChosenBank(Banks.MONO);
                    execute(BankButton.getUpdatedKeyboard(update, optionCurrentChat));
                    break;
                case ("Час сповіщення"):
                    break;
                case ("До головного меню"):
                    break;
                case ("2"):
                    execute(AmountOfSingsAfterCommaButton.NumberOfSignButton.setSingsAfterComma(update));
                    break;
                case ("3"):
                    execute(AmountOfSingsAfterCommaButton.NumberOfSignButton.setSingsAfterComma(update));
                    break;
                case ("4"):
                    execute(AmountOfSingsAfterCommaButton.NumberOfSignButton.setSingsAfterComma(update));
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
