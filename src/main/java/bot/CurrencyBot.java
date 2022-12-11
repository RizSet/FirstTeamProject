package bot;


import bot.buttons.GetInfoBotton;
import bot.buttons.PropertiesButton;
import bot.buttons.TimeMessageButton;

import bot.buttons.*;
import bot.buttons.TimeMessageLogic.*;

import bot.command.StartCommand;
import options.Option;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import parser.Banks;

import java.util.HashMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;


public class CurrencyBot extends TelegramLongPollingCommandBot {
    private Timer timer = new Timer();

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
        String chatId;
        if (update.hasMessage()) {
            chatId = String.valueOf(update.getMessage().getChatId());
            try {
                switch (update.getMessage().getText()) {
                    case ("9"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(9);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("10"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(10);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("11"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(11);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("12"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(12);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("13"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(13);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("14"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(14);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("15"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(15);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("16"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(16);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("17"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(17);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("18"):
                        execute(TimeMessageButton.setSchedule(update));
                        timer.setRemaindTime(18);
                        setTimeScheduler(chatId, timer.timeToRemaind());
                        break;
                    case ("Вимкнути повідомлення"):
                        execute(TimeMessageButton.shutDownSchedule(update));
                        cancelerSheduler();
                        break;
                    case ("На головне меню"):
                        break;
                }
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
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
                case ("Валюти"):
                    execute(CurrencyButton.getMessage(chatId));
                    break;
                case ("USD"):
                    execute(CurrencyButton.CurrenciesButton.setCurrencies(update));
                    break;
                case ("EUR"):
                    execute(CurrencyButton.CurrenciesButton.setCurrencies(update));
                    break;
                case ("Банк з якого буде братись курс"):
                    execute(BankButton.getMessage(chatId,optionCurrentChat));
                    break;
                case ("ПриватБанк"):
                    optionCurrentChat.setChosenBank(Banks.PRIVAT);
                    execute(BankButton.getUpdatedKeyboard(update, optionCurrentChat));
                    break;
                case ("НБУ"):
                    optionCurrentChat.setChosenBank(Banks.NBU);
                    execute(BankButton.getUpdatedKeyboard(update, optionCurrentChat));
                    break;
                case ("МоноБанк"):
                    optionCurrentChat.setChosenBank(Banks.MONO);
                    execute(BankButton.getUpdatedKeyboard(update, optionCurrentChat));
                    break;
                case ("Час сповіщення"):
                    execute(TimeMessageButton.getMessageCreateNotation(chatId));
                    break;
                case ("Головне меню"):
                    execute(ReturnButton.getMessage(chatId));
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

    static ScheduledFuture<?> messageNotation;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    public void setTimeScheduler(String chatId, int timeToRemaind) {

        Option optionCurrentChat = new Option();
        if (!clients.containsKey(chatId)) {
            clients.put(chatId, new Option());
        } else {
            optionCurrentChat = clients.get(chatId);
        }
        Option finalOptionCurrentChat = optionCurrentChat;
        Runnable message = () -> {
            try {
                execute(GetInfoBotton.getInfoMessage(chatId, finalOptionCurrentChat));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        };
        if (messageNotation != null) {
            cancelerSheduler();
        }
        messageNotation = scheduler.scheduleAtFixedRate(message, timeToRemaind, 86400, SECONDS);
    }

    public static void cancelerSheduler() {
        messageNotation.cancel(false);
        messageNotation = null;
    }

}
