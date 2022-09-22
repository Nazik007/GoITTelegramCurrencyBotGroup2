package telegram_bot_group_2.banks.currency;

import java.util.ArrayList;
import java.util.List;

public enum Currency {
    USD("USD", true),
    EUR("EUR", false),
    GBP("GBP", false),
    UAH("UAH", false);

    private String currencyName;
    private boolean currencySelect;

    Currency(String currencyName, boolean currencySelect) {
        this.currencyName = currencyName;
        this.currencySelect = currencySelect;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public boolean isCurrencySelect() {
        return currencySelect;
    }

    public void setCurrencySelect(boolean currencySelect) {
        this.currencySelect = currencySelect;
    }

    public static List<Currency> getSelectedCurrencyList () {
        List<Currency> selectedCurrency = new ArrayList<>();
        for (Currency currency: Currency.values()) {
            if (currency.currencySelect) {
                selectedCurrency.add(currency);
            }
        }
        return selectedCurrency;
    }

    public static Currency convertToEnum (String text) {
        for (Currency currency: Currency.values()) {
            if (currency.name().equals(text)) {
                return currency;
            }
        }
        return null;
    }

}
