package telegram_bot_group_2.settings;

import java.util.List;

public class IntermediateSetting {
    private Long chatId;
    private String numberOfDecimalPlaces;
    private String selectedBank;
    private List<String> selectedCurrency;
    private String notificationTime;
    private String zoneId;
    private String language;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getNumberOfDecimalPlaces() {
        return numberOfDecimalPlaces;
    }

    public void setNumberOfDecimalPlaces(String numberOfDecimalPlaces) {
        this.numberOfDecimalPlaces = numberOfDecimalPlaces;
    }

    public String getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(String selectedBank) {
        this.selectedBank = selectedBank;
    }

    public List<String> getSelectedCurrency() {
        return selectedCurrency;
    }

    public void setSelectedCurrency(List<String> selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
