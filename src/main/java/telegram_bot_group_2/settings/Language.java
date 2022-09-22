package telegram_bot_group_2.settings;

public enum Language {

    EN("English", "EnglishSet", "English \uD83C\uDDFA\uD83C\uDDF8"),
    UA("Ukrainian", "UkrainianSet", "Українська \uD83C\uDDFA\uD83C\uDDE6");

    String langName;
    String langNameSet;
    String langFlag;

    Language(String langName, String langNameSet, String langFlag) {
        this.langName = langName;
        this.langFlag = langFlag;
        this.langNameSet = langNameSet;
    }

    public String getLangName() {
        return langName;
    }

    public String getLangNameSet() {
        return langNameSet;
    }

    public String getLangFlag() {
        return langFlag;
    }

    public static Language convertToEnumSet(String text) {
        for (Language lang : Language.values()) {
            if (lang.getLangNameSet().equals(text)) {
                return lang;
            }
        }
        return null;
    }

    public static String translate(String text, Language language) {
        switch (text) {
            case "Курс купівлі ":
                if (EN.equals(language)) {
                    return "Purchase fx rate ";
                }
                return text;
            case "Курс продажу ":
                if (EN.equals(language)) {
                    return "Sales fx rate ";
                }
                return text;
            case "немає купівлі":
                if (EN.equals(language)) {
                    return "no purchase";
                }
                return text;
            case "немає продажу":
                if (EN.equals(language)) {
                    return "no sale";
                }
                return text;
            case "Будь ласка впишіть /start або натисніть кнопку.":
                if (EN.equals(language)) {
                    return "Please type /start or press the button.";
                }
                return text;
            case "Щоб отримати інфо натисність кнопку":
                if (EN.equals(language)) {
                    return "For get info please press the button";
                }
                return text;
            case "Виберіть налаштування":
                if (EN.equals(language)) {
                    return "Please choose options";
                }
                return text;
            case "Ласкаво просимо. Цей бот дозволить відслідкувати актуальні курси валют.":
                if (EN.equals(language)) {
                    return "Welcome. This bot will help you to follow up current fx rates.";
                }
                return text;
            case "Підтвердити":
                if (EN.equals(language)) {
                    return "Confirm";
                }
                return text;
        }
        return "";
    }
}


