package telegram_bot_group_2.bot_services;

public enum Buttons {

    START("Старт", "/start"),
    GET_INFO("Отримати інформацію", "Get info"),
    BANK("Банк \uD83C\uDFDB", "Bank"),
    CURRENCY("Валюта \uD83D\uDCB5", "Currency"),
    NOTIFICATION("Час сповіщення \uD83D\uDECE", "Notification time"),
    SETTINGS("Налаштування ⚙️", "Settings"),
    LANGUAGE("Мова", "Language"),
    ZONEID("Часовий пояс", "Time zone"),
    BACK_TO_SETTINGS("↩️", "Settings"),
    BACK_TO_START("🏠️", "BACK_TO_START"),
    NUM_DECIMAL_PLACES("Кількість знаків після коми","Number of decimal places" );

    private String buttonsNameUA;
    private String buttonsNameEN;

    Buttons(String buttonsNameUA, String buttonsNameEN) {
        this.buttonsNameUA = buttonsNameUA;

    }

    public String getNameUA() {
        return buttonsNameUA;
    }

    public String getNameEN() {
        return buttonsNameEN;
    }

    public static Buttons convertToEnum(String text) {
        for (Buttons button : Buttons.values()) {
            if (button.getNameEN().equals(text)) {
                return button;
            }
        }
        return null;
    }
}
