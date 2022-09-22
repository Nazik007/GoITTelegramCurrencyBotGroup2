package telegram_bot_group_2;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram_bot_group_2.settings.Settings;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;

public class Timer implements Runnable{
    private Settings settings;

    public Timer(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void run() {
        while (true) {
            try {
                timer();
                Thread.sleep(1500);
            } catch (InterruptedException | TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timer() throws InterruptedException, TelegramApiException {
        ZonedDateTime Greenwich = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime startTime = LocalDateTime.from(Greenwich);
        LocalDateTime startDays = LocalDateTime.from(Greenwich).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime timeSendMessage = LocalDateTime.from(Greenwich).withMinute(0).withSecond(0);
        if (timeSendMessage.isBefore(startTime)) {
            timeSendMessage = timeSendMessage.plusHours(1);
        }

        Duration timeToSendMess = Duration.between(startTime, timeSendMessage);
        Thread.sleep(timeToSendMess.toMillis());
        Duration hour = Duration.between(startDays, timeSendMessage);
        for (Map.Entry userSet : settings.settingsAllUsers.entrySet()) {
            Long key = (Long) userSet.getKey();
            Long chatId = settings.settingsAllUsers.get(key).getChatId();
            int userNotificationTime = settings.settingsAllUsers.get(key).getNotificationTime().getTime();
            int userZoneId = settings.settingsAllUsers.get(key).getZoneId().getZone();
            if (userNotificationTime == (int) hour.toHours()+userZoneId) {
                CurrencyInfoBot timer = CurrencyInfoBot.getInstance("timer", settings);
                timer.printMessage(chatId, settings.getInfo(chatId));
            }
        }
    }
}
