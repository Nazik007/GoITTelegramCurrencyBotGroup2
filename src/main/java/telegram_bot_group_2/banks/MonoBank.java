package telegram_bot_group_2.banks;

import java.util.Objects;

public class MonoBank {
    private int currencyCodeA;
    private int currencyCodeB;
    private int date;
    private float rateBuy;
    private float rateSell;

    private float rateCross;
    public int getCurrencyCodeA() { return currencyCodeA; }

    public int getCurrencyCodeB() { return currencyCodeB; }

    public int getDate() { return date; }

    public float getRateBuy() { return rateBuy; }

    public float getRateSell() { return rateSell; }

    public float getRateCross() { return rateCross; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonoBank monobank = (MonoBank) o;
        return currencyCodeA == monobank.currencyCodeA
                && currencyCodeB == monobank.currencyCodeB
                && date == monobank.date
                && Float.compare(monobank.rateBuy, rateBuy) == 0
                && Float.compare(monobank.rateSell, rateSell) == 0
                && Float.compare(monobank.rateCross, rateCross) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCodeA, currencyCodeB, date, rateBuy, rateSell, rateCross);
    }

    @Override
    public String toString() {
        return "Monobank{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateBuy=" + rateBuy +
                ", rateSell=" + rateSell +
                ", rateCross=" + rateCross +
                '}';
    }
}
