package telegram_bot_group_2.bot_services.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegram_bot_group_2.bot_services.Buttons;
import telegram_bot_group_2.settings.Currency;
import telegram_bot_group_2.settings.Setting;
import telegram_bot_group_2.settings.Settings;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuUA extends Menu{
    public MenuUA(Settings settings) {
        super(settings);
    }

    @Override
    public InlineKeyboardMarkup keyboardSettings(Setting setting) {
        String selectedCurr = setting.getSelectedCurrency().stream()
                .map(Currency::getCurrencyName)
                .collect(Collectors.joining(", ", "(", ")"));

        List<List<InlineKeyboardButton>> keyboardMenuSettings = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow5 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow6 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow7 = new ArrayList<>();
        InlineKeyboardButton buttonNumOfDecPlaces = InlineKeyboardButton.builder()
                .text(Buttons.NUM_DECIMAL_PLACES.getNameUA() + " (" + setting.getNumberOfDecimalPlaces() + ")")
                .callbackData(Buttons.NUM_DECIMAL_PLACES.getNameEN())
                .build();
        InlineKeyboardButton buttonBank = InlineKeyboardButton.builder()
                .text(Buttons.BANK.getNameUA() + " (" + setting.getSelectedBank().getBankNameUA() + ")")
                .callbackData(Buttons.BANK.getNameEN())
                .build();
        InlineKeyboardButton buttonCurrency = InlineKeyboardButton.builder()
                .text(Buttons.CURRENCY.getNameUA() + selectedCurr)
                .callbackData(Buttons.CURRENCY.getNameEN())
                .build();
        String NotificationTimeSet = setting.getNotificationTime().getTime() == 0 ? "OFF" :
                String.valueOf(setting.getNotificationTime().getTime());
        InlineKeyboardButton buttonNotificationTime = InlineKeyboardButton.builder()
                .text(Buttons.NOTIFICATION.getNameUA() + " (" + NotificationTimeSet + ")")
                .callbackData(Buttons.NOTIFICATION.getNameEN())
                .build();
        InlineKeyboardButton buttonZoneId = InlineKeyboardButton.builder()
                .text(Buttons.ZONEID.getNameUA() + " (" + setting.getZoneId().getNameZone() + ")")
                .callbackData(Buttons.ZONEID.getNameEN())
                .build();
        InlineKeyboardButton buttonLang = InlineKeyboardButton.builder()
                .text(Buttons.LANGUAGE.getNameUA() + " (" + setting.getSelectedLanguage().getLangFlag() + ")")
                .callbackData(Buttons.LANGUAGE.getNameEN())
                .build();
        InlineKeyboardButton buttonBack = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_START.getNameUA())
                .callbackData(Buttons.BACK_TO_START.getNameEN())
                .build();

        keyboardMSetRow1.add(buttonNumOfDecPlaces);
        keyboardMSetRow2.add(buttonBank);
        keyboardMSetRow3.add(buttonCurrency);
        keyboardMSetRow4.add(buttonNotificationTime);
        keyboardMSetRow5.add(buttonZoneId);
        keyboardMSetRow6.add(buttonLang);
        keyboardMSetRow7.add(buttonBack);
        keyboardMenuSettings.add(keyboardMSetRow1);
        keyboardMenuSettings.add(keyboardMSetRow2);
        keyboardMenuSettings.add(keyboardMSetRow3);
        keyboardMenuSettings.add(keyboardMSetRow4);
        keyboardMenuSettings.add(keyboardMSetRow5);
        keyboardMenuSettings.add(keyboardMSetRow6);
        keyboardMenuSettings.add(keyboardMSetRow7);

        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuSettings).build();
    }

    @Override
    public InlineKeyboardMarkup keyboardStart() {
        List<List<InlineKeyboardButton>> keyboardMenuStart = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSRow2 = new ArrayList<>();
        InlineKeyboardButton buttonGetInfo = InlineKeyboardButton.builder()
                .text(Buttons.GET_INFO.getNameUA())
                .callbackData(Buttons.GET_INFO.getNameEN())
                .build();
        InlineKeyboardButton buttonSettings = InlineKeyboardButton.builder()
                .text(Buttons.SETTINGS.getNameUA())
                .callbackData(Buttons.SETTINGS.getNameEN())
                .build();
        keyboardMSRow1.add(buttonGetInfo);
        keyboardMSRow2.add(buttonSettings);
        keyboardMenuStart.add(keyboardMSRow1);
        keyboardMenuStart.add(keyboardMSRow2);
        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuStart).build();
    }
}