package telegram_bot_group_2.banks_util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import telegram_bot_group_2.banks.MonoBank;
import telegram_bot_group_2.banks.NBU;
import telegram_bot_group_2.banks.Privat;
import telegram_bot_group_2.serviceclasses.Bank;


import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BanksUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    private static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";
    private static final String MonoBank_URL = "https://api.monobank.ua/bank/currency";
    private static final String Privat_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11";

    static Type typeNBU = new TypeToken<List<NBU>> (){}.getType();
    static Type typePrivat = new TypeToken<List<Privat>>(){}.getType();
    static Type typeMonoBank = new TypeToken<List<MonoBank>>(){}.getType();

    public Bank getNBUAPI() throws IOException, InterruptedException {
        final List<NBU> dateNBU = sendGetBank(URI.create(NBU_URL),typeNBU);
        return getNbu(dateNBU);
    }
    public <T> List<T> sendGetBank(URI uri,Type typeBank) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), typeBank);
    }
    public Bank getPrivatAPI() throws IOException, InterruptedException {
        final List<Privat> datePrivat = sendGetBank(URI.create(Privat_URL),typePrivat);
        return getPrivat(datePrivat);
    }
    public Bank getNbu(List<NBU> listCurr) {
        Bank bank = new Bank();
        for (NBU currency : listCurr) {
            switch (currency.getCc()) {
                case "USD":
                    bank.setUSD_buy(currency.getRate());
                    bank.setUSD_sell(currency.getRate());
                    break;
                case "EUR":
                    bank.setEUR_buy(currency.getRate());
                    bank.setEUR_sell(currency.getRate());
                    break;
                case "GBP":
                    bank.setGBP_buy(currency.getRate());
                    bank.setGBP_sell(currency.getRate());
                    break;
            }
        }
        return bank;
    }
    public Bank getPrivat(List<Privat> date) {
        Bank bank = new Bank();
        for (Privat currency : date) {
            switch (currency.getCcy()) {
                case "USD":
                    bank.setUSD_buy(currency.getBuy());
                    bank.setUSD_sell(currency.getSale());
                    break;
                case "EUR":
                    bank.setEUR_buy(currency.getBuy());
                    bank.setEUR_sell(currency.getSale());
                    break;
                case"GBP":
                    bank.setGBP_buy(currency.getBuy());
                    bank.setGBP_sell(currency.getSale());
                    break;
            }
        }
        return bank;
    }
    public Bank getMonoAPI() throws IOException, InterruptedException {
        final List<MonoBank> dateMonoBank = sendGetBank(URI.create(MonoBank_URL),typeMonoBank);
        return getMonoBank(dateMonoBank);
    }
    public Bank getMonoBank(List<MonoBank> monobankList) {
        Bank bank = new Bank();
        for (MonoBank currency : monobankList) {
            if (currency.getCurrencyCodeA() == 840 && currency.getCurrencyCodeB() == 980) {
                bank.setUSD_buy(currency.getRateBuy());
                bank.setUSD_sell(currency.getRateSell());
            } else if (currency.getCurrencyCodeA() == 978 && currency.getCurrencyCodeB() == 980) {
                bank.setEUR_buy(currency.getRateBuy());
                bank.setEUR_sell(currency.getRateSell());
            } else if (currency.getCurrencyCodeA() == 840 && currency.getCurrencyCodeB() == 980) {
                bank.setGBP_buy(currency.getRateBuy());
                bank.setGBP_sell(currency.getRateSell());

            }
        }

        return bank;
    }
    }

