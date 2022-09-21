package telegram_bot_group_2.serviceclasses;

import telegram_bot_group_2.banks_util.BanksUtil;
import telegram_bot_group_2.settings.Banks;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;


public class CurrencyDataBase {
    public HashMap<Banks, Bank> currentInfo = new HashMap<>();
    private final Object monitor = new Object();

    public Bank getCurrentInfo (Banks bankName) {
        synchronized (monitor) {
            if (currentInfo.get(bankName) == null) {
                try {
                    setCurrentInfo(bankName);
                }catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
            Bank bank = currentInfo.get(bankName);
            long timeDiff = Duration.between(bank.getTime(), LocalDateTime.now()).toMinutes();
            if (timeDiff > 5) {
                try {
                    setCurrentInfo(bankName);
                }catch (IOException | InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        }
        return currentInfo.get(bankName);
    }

    private void setCurrentInfo(Banks bankName) throws IOException, InterruptedException {
        BanksUtil banksUtil = new BanksUtil();
        switch (bankName){
            case PRIVAT:
                Bank bankPrivat = banksUtil.getPrivatAPI();
                bankPrivat.setTime(LocalDateTime.now());
                currentInfo.put(bankName,bankPrivat);
                break;
            case MONO:
                Bank bankMono = banksUtil.getMonoAPI();
                bankMono.setTime(LocalDateTime.now());
                currentInfo.put(bankName, bankMono);
                break;
            case NBU:
                Bank bankNBU = banksUtil.getNBUAPI();
                bankNBU.setTime(LocalDateTime.now());
                currentInfo.put(bankName,bankNBU);
                break;
        }
    }
}
