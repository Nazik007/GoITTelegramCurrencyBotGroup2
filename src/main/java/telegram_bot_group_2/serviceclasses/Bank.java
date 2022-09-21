package telegram_bot_group_2.serviceclasses;

import telegram_bot_group_2.settings.Currency;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bank {

    private Float USD_buy = 0.0f;
    private Float USD_sell = 0.0f;
    private Float EUR_buy = 0.0f;
    private Float EUR_sell = 0.0f;
    private Float GBP_buy = 0.0f;
    private Float GBP_sell = 0.0f;

    private LocalDateTime time;


    public Bank() {
    }

    public Bank(Float USD_buy, Float USD_sell, Float EUR_buy, Float EUR_sell, Float GBP_buy, Float GBP_sell, LocalDateTime time) {
        this.USD_buy = USD_buy;
        this.USD_sell = USD_sell;
        this.EUR_buy = EUR_buy;
        this.EUR_sell = EUR_sell;
        this.GBP_buy = GBP_buy;
        this.GBP_sell = GBP_sell;
        this.time = time;
    }

    public Float getUSD_buy() {
        return USD_buy;
    }

    public void setUSD_buy(Float USD_buy) {
        this.USD_buy = USD_buy;
    }

    public Float getUSD_sell() {
        return USD_sell;
    }

    public void setUSD_sell(Float USD_sell) {
        this.USD_sell = USD_sell;
    }

    public Float getEUR_buy() {
        return EUR_buy;
    }

    public void setEUR_buy(Float EUR_buy) {
        this.EUR_buy = EUR_buy;
    }

    public Float getEUR_sell() {
        return EUR_sell;
    }

    public void setEUR_sell(Float EUR_sell) {
        this.EUR_sell = EUR_sell;
    }

    public Float getGBP_buy() {
        return GBP_buy;
    }

    public void setGBP_buy(Float GBP_buy) {
        this.GBP_buy = GBP_buy;
    }

    public Float getGBP_sell() {
        return GBP_sell;
    }

    public void setGBP_sell(Float GBP_sell) {
        this.GBP_sell = GBP_sell;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(USD_buy, bank.USD_buy) && Objects.equals(USD_sell, bank.USD_sell) && Objects.equals(EUR_buy, bank.EUR_buy) && Objects.equals(EUR_sell, bank.EUR_sell) && Objects.equals(GBP_buy, bank.GBP_buy) && Objects.equals(GBP_sell, bank.GBP_sell) && Objects.equals(time, bank.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(USD_buy, USD_sell, EUR_buy, EUR_sell, GBP_buy, GBP_sell, time);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "USD_buy=" + USD_buy +
                ", USD_sell=" + USD_sell +
                ", EUR_buy=" + EUR_buy +
                ", EUR_sell=" + EUR_sell +
                ", GBP_buy=" + GBP_buy +
                ", GBP_sell=" + GBP_sell +
                ", time=" + time +
                '}';
    }

    public Float getBuyRate (Currency currency) {
        switch (currency){
            case USD:
                return this.USD_buy;
            case EUR:
                return this.EUR_buy;
            case GBP:
                return this.GBP_buy;
        }
        return null;
    }

    public Float getSellRate (Currency currency) {
        switch (currency) {
            case USD:
                return this.USD_sell;
            case EUR:
                return this.EUR_sell;
            case GBP:
                return this.GBP_sell;
        }
        return null;
    }
}
