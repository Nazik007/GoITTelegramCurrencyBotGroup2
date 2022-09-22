package telegram_bot_group_2;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegram_bot_group_2.banks.currency.CurrencyDataBase;
import telegram_bot_group_2.settings.Settings;

public class AppLauncher {
    public static void main(String[] args) throws TelegramApiException {
        CurrencyDataBase dataBase = new CurrencyDataBase();
        Settings settings = new Settings(dataBase);
        settings.load();

        Timer timer = new Timer(settings);
        Thread textInTime = new Thread(timer);
        textInTime.start();

        TelegramBot bot = TelegramBot.getInstance("telegramBot", settings);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}
