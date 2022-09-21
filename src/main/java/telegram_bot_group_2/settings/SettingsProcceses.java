package telegram_bot_group_2.settings;

public class SettingsProcceses {
    private CurrencyDataBase currencyDataBase;
//    public  Map<Long, Setting> settingsAllUsers = new HashMap<>();
//    private  final Gson settingGson = new Gson();
//    private final Object monitor = new Object();
//
//    public SettingsProcceses(CurrencyDataBase currencyDataBase) {
//        this.currencyDataBase = currencyDataBase;
//    }
//
//    public String getInfo(Long chatId) {
//        StringBuilder messageToUser = new StringBuilder();
//        Setting userSetting = settingsAllUsers.get(chatId);
//        Language language = userSetting.getSelectedLanguage();
//        String bankName;
//        switch (language) {
//            case UA:
//                bankName = userSetting.getSelectedBank().getBankNameUA();
//                break;
//            default:
//                bankName = userSetting.getSelectedBank().getBankNameEN();
//                break;
//        }
//        messageToUser.append(bankName).append("\n");
//        int numberDecPlaces = userSetting.getNumberOfDecimalPlaces();
//        List<Currency> currencies = userSetting.getSelectedCurrency();
//        Bank bankInfo = currencyDataBase.getCurrentInfo(userSetting.getSelectedBank());
//        for (Currency currency : currencies) {
//            messageToUser.append(Language.translate("Курс купівлі ", language))
//                    .append(currency.getCurrencyName())
//                    .append(" - ")
//                    .append(bankInfo.getBuyRate(currency) == 0 ? Language.translate("немає купівлі", language) :
//                            format("%." + numberDecPlaces + "f", bankInfo.getBuyRate(currency)) + addCurName(currency))
//                    .append("\n");
//            messageToUser.append(Language.translate("Курс продажу ", language))
//                    .append(currency.getCurrencyName())
//                    .append(" - ")
//                    .append(bankInfo.getSellRate(currency) == 0 ? Language.translate("немає продажу", language) :
//                            format("%." + numberDecPlaces + "f", bankInfo.getSellRate(currency)) + addCurName(currency))
//                    .append("\n");
//        }
//        return messageToUser.toString();
//    }

//    public void load() {
//        IntermediateSettings intermediateSettings = new IntermediateSettings();
//        synchronized (monitor) {
//
//            File file = fileSettingsGsonCheck();
//            if (file.length() != 0) {
//                try {
//                    intermediateSettings.intermediateSettings = new ObjectMapper().readValue(file,
//                            new TypeReference<Map<Long, IntermediateSetting>>() {
//                            });
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                converter(intermediateSettings);
//            }
//        }
//    }

//    public void save() {
//        synchronized (monitor) {
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSettingsGsonCheck()))) {
//                writer.write(settingGson.toJson(settingsAllUsers));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private void converter(IntermediateSettings intermediateSettings) {
//        synchronized (monitor) {
//            Map<Long, IntermediateSetting> inputMap = intermediateSettings.intermediateSettings;
//            Map<Long, Setting> outputMap = settingsAllUsers;
//            inputMap.forEach((k, v) -> {
//                Setting outputSetting = new Setting();
//
//                outputSetting.setChatId(v.getChatId());
//                outputSetting.setNumberOfDecimalPlaces(parseNumOfDecPlaces(v.getNumberOfDecimalPlaces()));
//                outputSetting.setSelectedBank(parseSelectedBank(v.getSelectedBank()));
//                outputSetting.setSelectedCurrency(parseCurrency(v.getSelectedCurrency()));
//                outputSetting.setNotificationTime(parseNotificationTime(v.getNotificationTime()));
//                outputSetting.setZoneId(parseZoneId(v.getZoneId()));
//                outputSetting.setSelectedLanguage(parseLanguage(v.getLanguage()));
//                outputMap.put(v.getChatId(), outputSetting);
//            });
//        }
//    }

//    private NumberOfDecimalPlaces parseNumOfDecPlaces(String inputStrNumOfDec) {
//        for (NumberOfDecimalPlaces value : NumberOfDecimalPlaces.values()) {
//            if (inputStrNumOfDec.equals(value.name())) {
//                return value;
//            }
//        }
//        return null;
//    }

    private Banks parseSelectedBank(String inputStrBank) {
        for (Banks value : Banks.values()) {
            if (inputStrBank.equals(value.name())) {
                return value;
            }
        }
        return null;
    }

//    private List<Currency> parseCurrency(List<String> inputListStrCurrency) {
//        List<Currency> result = new ArrayList<>();
//        for (Currency value : Currency.values()) {
//            for (String oneCurrency : inputListStrCurrency) {
//                if (oneCurrency.equals(value.name())) {
//                    result.add(value);
//                }
//            }
//
//        }
//        return result;
//    }

//    private NotificationTime parseNotificationTime(String inputStrNotificationTime) {
//        for (NotificationTime value : NotificationTime.values()) {
//            if (inputStrNotificationTime.equals(value.name())) {
//                return value;
//            }
//        }
//        return null;
//    }

//    private ZoneId parseZoneId(String inputStrZoneId) {
//        for (ZoneId value : ZoneId.values()) {
//            if (inputStrZoneId.equals(value.name())) {
//                return value;
//            }
//        }
//        return null;
//    }

//    private Language parseLanguage(String inputStrLang) {
//        for (Language value : Language.values()) {
//            if (inputStrLang.equals(value.name())) {
//                return value;
//            }
//        }
//        return null;
//    }

    private String addCurName(Currency currency) {
        switch (currency) {
            case USD:
            case GBP:
            case EUR:
                return " UAH";
        }
        return "";
    }


}
