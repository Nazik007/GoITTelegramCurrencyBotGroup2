package telegram_bot_group_2;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram_bot_group_2.banks.Banks;
import telegram_bot_group_2.banks.currency.Currency;
import telegram_bot_group_2.bot_services.Buttons;
import telegram_bot_group_2.bot_services.keyboards.Menu;
import telegram_bot_group_2.bot_services.keyboards.MenuEN;
import telegram_bot_group_2.bot_services.keyboards.MenuUA;
import telegram_bot_group_2.settings.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelegramBot extends TelegramLongPollingBot {

    private static TelegramBot instance;
    private static final ExecutorService service = Executors.newSingleThreadExecutor();
    private Settings settings;
    private String value;
    private Menu menu;
    private final static Object monitor = new Object();

    private TelegramBot (String value, Settings settings) {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.menu = new MenuUA(settings);
        this.value = value;
        this.settings = settings;
    }

    public static TelegramBot getInstance(String value, Settings settings) {
        if (instance == null) {
            instance = new TelegramBot(value, settings);
        }
        return instance;
    }

    @Override
    public String getBotUsername() {
        return "@Berdo22Bot";
    }

    @Override
    public String getBotToken() {
        return "5110494726:AAHvvtZ2yxM8dnzpR730WBz4eeG7haGp9Kw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
        if (update.hasCallbackQuery()) {
            handleQuery(update.getCallbackQuery());
        }
    }

    private void handleQuery(CallbackQuery buttonQuery) {
        Setting userSettings;
        long chatId = buttonQuery.getMessage().getChatId();
        synchronized (monitor) {
            if (settings.settingsAllUsers.get(chatId) == null) {
                userSettings = new Setting(chatId, NumberOfDecimalPlaces.TWO, Banks.PRIVAT, Currency.getSelectedCurrencyList(), NotificationTime.NINE,
                        ZoneId.UTC_THREE, Language.UA);
            }else {
                userSettings = settings.settingsAllUsers.get(chatId);
            }
        }
        menu = getMenu(userSettings);
        checkLanguageMenu(buttonQuery, userSettings);
    }

    private Menu getMenu(Setting userSettings) {
        menu = userSettings.getSelectedLanguage() == Language.EN ? new MenuEN(settings) : new MenuUA(settings);
        return menu;
    }

    private void handleMessage(Message message) {
        Setting userSettings;
        long chatId = message.getChatId();
        synchronized (monitor) {
            if (settings.settingsAllUsers.get(chatId) == null) {
                userSettings = new Setting(chatId, NumberOfDecimalPlaces.TWO, Banks.PRIVAT, Currency.getSelectedCurrencyList(),
                        NotificationTime.NINE, ZoneId.UTC_THREE, Language.UA);
            } else {
                userSettings = settings.settingsAllUsers.get(chatId);
            }
        }
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity;
            commandEntity = message.getEntities().stream()
                    .filter(it -> "bot_command".equals(it.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals(Buttons.START.getNameEN())){
                    printText(chatId, menu.keyboardLanguage(chatId),
                            "Оберіть мову, будь-ласка. Select your language, please "
                            );
                    synchronized (monitor) {
                        settings.settingsAllUsers.put(chatId, userSettings);
                    }
                }
            }
        }else {
            printText(chatId, Language.translate("Щоб почати напишіть /start або натисніть на кнопку нижче", userSettings.getSelectedLanguage()));
        }
    }

    public void printText(long chatId, String textToPrint) {
        try {
            execute(SendMessage.builder()
                    .text(textToPrint)
                    .chatId(chatId)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void printText (Long chatID, InlineKeyboardMarkup keyboard, String text) {
        try {
            execute(SendMessage.builder()
                    .text(text)
                    .chatId(chatID)
                    .replyMarkup(keyboard)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

     private void checkLanguageMenu (CallbackQuery buttonQuery, Setting userSettings) {
        long chatId = buttonQuery.getMessage().getChatId();
        String buttonData = buttonQuery.getData();
        if (Language.convertToEnumSet(buttonData) != null ) {
            switch (Language.convertToEnumSet(buttonData)) {
                case UA:
                    saveSelectedLanguage(buttonQuery, Language.UA, userSettings);
                    break;
                case EN:
                    saveSelectedLanguage(buttonQuery, Language.EN, userSettings);
                    break;
            }
        }
     }


    private void saveSelectedLanguage(CallbackQuery buttonQuery, Language enumInfo, Setting userSettings) {
        Long chatId = buttonQuery.getMessage().getChatId();
        userSettings.setSelectedLanguage(enumInfo);
        menu = getMenu(userSettings);
        printText(chatId, menu.keyboardStart(), Language.translate("Вітаю, я допоможу тобі дізнатись актуальний курс валют.",
                userSettings.getSelectedLanguage()));
    }



    public void checkNotificationMenu(CallbackQuery buttonQuery, Setting userSettings) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (NotificationTime.convertToEnum(dataButtonQuery) != null) {
            switch (NotificationTime.convertToEnum(dataButtonQuery)) {
                case NINE:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.NINE, userSettings);
                    break;
                case TEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.TEN, userSettings);
                    break;
                case ELEVEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.ELEVEN, userSettings);
                    break;
                case TWELVE:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.TWELVE, userSettings);
                    break;
                case THIRTEEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.THIRTEEN, userSettings);
                    break;
                case FOURTEEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.FOURTEEN, userSettings);
                    break;
                case FIFTEEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.FIFTEEN, userSettings);
                    break;
                case SIXTEEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.SIXTEEN, userSettings);
                    break;
                case SEVENTEEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.SEVENTEEN, userSettings);
                    break;
                case EIGHTEEN:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.EIGHTEEN, userSettings);
                    break;
                case SWICH_OFF:
                    saveSelectNotificationTime(buttonQuery, NotificationTime.SWICH_OFF, userSettings);
                    break;
            }
        }
    }

    private void saveSelectNotificationTime(CallbackQuery buttonQuery, NotificationTime enumData, Setting userSettings)
            throws TelegramApiException {
        if (userSettings.getNotificationTime().getTime() != enumData.getTime()) {
            userSettings.setNotificationTime(enumData);
            updateMessage(buttonQuery, menu.keyboardNotification(buttonQuery.getMessage().getChatId()));
        }
    }






    public void checkMainMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Buttons.convertToEnum(dataButtonQuery) != null) {
            switch (Buttons.convertToEnum(dataButtonQuery)) {
                case GET_INFO:
                    service.execute(new SaveSettings(settings));
                    printText(chatId, settings.getInfo(chatId));
                    printText(chatId, menu.keyboardStart(),
                            Language.translate("Щоб отримати інфо натисність кнопку",
                                    settings.settingsAllUsers.get(chatId).getSelectedLanguage()));
                    break;
                case SETTINGS:
                    printText(chatId, menu.keyboardSettings(settings.settingsAllUsers.get(chatId)),
                            Language.translate("Виберіть налаштування",
                                    settings.settingsAllUsers.get(chatId).getSelectedLanguage()));
                    break;
                case BACK_TO_START:
                    printText(chatId, menu.keyboardStart(),
                            Language.translate("Щоб отримати інфо натисність кнопку",
                                    settings.settingsAllUsers.get(chatId).getSelectedLanguage()));
                    break;
                case NUM_DECIMAL_PLACES:
                    updateMessage(buttonQuery, menu.keyboardNumDecPlaces(chatId));
                    break;
                case BANK:
                    updateMessage(buttonQuery, menu.keyboardBanks(chatId));
                    break;
                case CURRENCY:
                    updateMessage(buttonQuery, menu.keyboardCurrency(chatId));
                    break;
                case NOTIFICATION:
                    updateMessage(buttonQuery, menu.keyboardNotification(chatId));
                    break;
                case ZONEID:
                    updateMessage(buttonQuery, menu.keyboardZoneId(chatId));
                    break;
                case LANGUAGE:
                    updateMessage(buttonQuery, menu.keyboardLanguageSet(chatId));
                    break;
            }
        }
    }

    private void updateMessage(CallbackQuery buttonQuery, InlineKeyboardMarkup keyboard)
            throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        int messageId = buttonQuery.getMessage().getMessageId();
        execute(EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(keyboard)
                .build());
    }


    public void checkBanksMenu(CallbackQuery buttonQuery, Setting userSettings) throws TelegramApiException {
        Banks selectedBank = userSettings.getSelectedBank();
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Banks.convertToEnum(dataButtonQuery) != null) {
            switch (Banks.convertToEnum(dataButtonQuery)) {
                case PRIVAT:
                    if (!selectedBank.equals(Banks.PRIVAT)) {
                        saveSelectBanks(buttonQuery, Banks.PRIVAT, userSettings);
                    }
                    break;
                case NBU:
                    if (!selectedBank.equals(Banks.NBU)) {
                        saveSelectBanks(buttonQuery, Banks.NBU, userSettings);
                    }
                    break;
                case MONO:
                    if (!selectedBank.equals(Banks.MONO)) {
                        saveSelectBanks(buttonQuery, Banks.MONO, userSettings);
                    }
                    break;
            }
        }
    }

    private void saveSelectBanks(CallbackQuery buttonQuery, Banks enumData, Setting userSettings) throws TelegramApiException {
        userSettings.setSelectedBank(enumData);
        updateMessage(buttonQuery, menu.keyboardBanks(buttonQuery.getMessage().getChatId()));
    }


    public void checkZoneIdMenu(CallbackQuery buttonQuery, Setting userSettings) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (ZoneId.convertToEnum(dataButtonQuery) != null) {
            switch (ZoneId.convertToEnum(dataButtonQuery)) {
                case UTC_ONE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_ONE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_ONE, userSettings);
                    }
                    break;
                case UTC_TWO:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_TWO.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_TWO, userSettings);
                    }
                    break;
                case UTC_THREE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_THREE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_THREE, userSettings);
                    }
                    break;
                case UTC_FOUR:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_FOUR.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_FOUR, userSettings);
                    }
                    break;
                case UTC_FIVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_FIVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_FIVE, userSettings);
                    }
                    break;
                case UTC_SIX:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_SIX.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_SIX, userSettings);
                    }
                    break;
                case UTC_SEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_SEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_SEVEN, userSettings);
                    }
                    break;
                case UTC_EIGHT:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_EIGHT.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_EIGHT, userSettings);
                    }
                    break;
                case UTC_NINE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_NINE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_NINE, userSettings);
                    }
                    break;
                case UTC_TEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_TEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_TEN, userSettings);
                    }
                    break;
                case UTC_ELEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_ELEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_ELEVEN, userSettings);
                    }
                    break;
                case UTC_TWELVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_TWELVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_TWELVE, userSettings);
                    }
                    break;
                case UTC_MINUS_ONE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_ONE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_ONE, userSettings);
                    }
                    break;
                case UTC_MINUS_TWO:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_TWO.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_TWO, userSettings);
                    }
                    break;
                case UTC_MINUS_THREE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_THREE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_THREE, userSettings);
                    }
                    break;
                case UTC_MINUS_FOUR:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_FOUR.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_FOUR, userSettings);
                    }
                    break;
                case UTC_MINUS_FIVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_FIVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_FIVE, userSettings);
                    }
                    break;
                case UTC_MINUS_SIX:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_SIX.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_SIX, userSettings);
                    }
                    break;
                case UTC_MINUS_SEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_SEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_SEVEN, userSettings);
                    }
                    break;
                case UTC_MINUS_EIGHT:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_EIGHT.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_EIGHT, userSettings);
                    }
                    break;
                case UTC_MINUS_NINE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_NINE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_NINE, userSettings);
                    }
                    break;
                case UTC_MINUS_TEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_TEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_TEN, userSettings);
                    }
                    break;
                case UTC_MINUS_ELEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_ELEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_ELEVEN, userSettings);
                    }
                    break;
                case UTC_MINUS_TWELVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_MINUS_TWELVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_MINUS_TWELVE, userSettings);
                    }
                    break;
                case UTC_ZERO:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTC_ZERO.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTC_ZERO, userSettings);
                    }
                    break;
            }
        }
    }

    private void saveSelectZoneId(CallbackQuery buttonQuery, ZoneId enumData, Setting userSettings) throws TelegramApiException {
        userSettings.setZoneId(enumData);
        updateMessage(buttonQuery, menu.keyboardZoneId(buttonQuery.getMessage().getChatId()));
    }



    public void checkCurrencyMenu(CallbackQuery buttonQuery,Setting userSettings) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Currency.convertToEnum(dataButtonQuery) != null) {
            switch (Currency.convertToEnum(dataButtonQuery)) {
                case USD:
                    saveSelectCurrency(buttonQuery, Currency.USD, userSettings);
                    break;
                case EUR:
                    saveSelectCurrency(buttonQuery, Currency.EUR, userSettings);
                    break;
            }
        }
    }

    private void saveSelectCurrency(CallbackQuery buttonQuery, Currency enumData, Setting userSettings) throws TelegramApiException {
        List<Currency> currentCurrencies = userSettings.getSelectedCurrency();
        if (currentCurrencies.contains(enumData)) {
            currentCurrencies.remove(enumData);
        } else {
            currentCurrencies.add(enumData);
        }
        updateMessage(buttonQuery, menu.keyboardCurrency(buttonQuery.getMessage().getChatId()));
    }


    public void checkDecimalPlacesMenu(CallbackQuery buttonQuery, Setting userSettings) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (NumberOfDecimalPlaces.convertToEnum(dataButtonQuery) != null) {
            switch (NumberOfDecimalPlaces.convertToEnum(dataButtonQuery)) {
                case TWO:
                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.TWO.getIntNumber()) {
                        saveDecimalPlace(buttonQuery, NumberOfDecimalPlaces.TWO, userSettings);
                    }
                    break;
                case THREE:
                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.THREE.getIntNumber()) {
                        saveDecimalPlace(buttonQuery, NumberOfDecimalPlaces.THREE, userSettings);
                    }
                    break;
                case FOUR:
                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.FOUR.getIntNumber()) {
                        saveDecimalPlace(buttonQuery, NumberOfDecimalPlaces.FOUR, userSettings);
                    }
                    break;
            }
        }
    }

    private void saveDecimalPlace(CallbackQuery buttonQuery, NumberOfDecimalPlaces enumData, Setting userSettings)
            throws TelegramApiException {
        userSettings.setNumberOfDecimalPlaces(enumData);
        updateMessage(buttonQuery, menu.keyboardNumDecPlaces(buttonQuery.getMessage().getChatId()));
    }
}
