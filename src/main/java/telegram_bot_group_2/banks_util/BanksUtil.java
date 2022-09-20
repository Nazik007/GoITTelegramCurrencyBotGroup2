package telegram_bot_group_2.banks_util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import telegram_bot_group_2.banks.NBU;
import telegram_bot_group_2.banks.Privat;


import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

public class BanksUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    private static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";
    private static final String MONOBANK_URL = "https://api.monobank.ua/bank/currency";
    private static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11";

    static Type typeNbu = new TypeToken<List<NBU>> (){}.getType();
    static Type typePrivat = new TypeToken<List<Privat>>() { }.getType();


}
