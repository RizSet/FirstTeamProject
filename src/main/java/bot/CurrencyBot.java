package bot;


import bot.buttons.GetInfoBotton;
import bot.buttons.PropertiesButton;
import bot.buttons.TimeMessageButton;
import bot.buttons.TimeMessageLogic.ChooseTime;
import bot.buttons.TimeMessageLogic.Timer;
import bot.command.StartCommand;
import fsm.Option;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CurrencyBot extends TelegramLongPollingCommandBot {
    private Timer timer = new Timer();


    private static HashMap<String, Option> clients = new HashMap<>();

    public HashMap<String, Option> getClients() {
        return clients;
    }

    public void setClients(HashMap<String, Option> clients) {
        CurrencyBot.clients = clients;
    }

    public CurrencyBot() {
        register(new StartCommand());
        timer.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
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
    public void onUpdatesReceived(List<Update> updates) {
        for (Update item : updates) {
            String chatId = String.valueOf(item.getMessage().getChatId());
            try {
                if (item.hasMessage()) {
                    if (item.getMessage().getText().equals("На головне меню")) {
                        //menu buttoon
                        break;
                    }
                    execute(ChooseTime.setTimeNotation(chatId, item.getMessage().getText(), timer));
                    if(timer.isNotationOn()) {
                        timer.scheduledExecutorService.scheduleAtFixedRate(
                                () -> {
                                    try {
                                        execute(GetInfoBotton.getInfoMessage(chatId));
                                    } catch (TelegramApiException e) {
                                        throw new RuntimeException(e);
                                    }
                                },
                                5,
                                5,
                                TimeUnit.SECONDS);
                    } else {
                        timer.scheduledExecutorService.shutdown();
                    }
                }
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

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
