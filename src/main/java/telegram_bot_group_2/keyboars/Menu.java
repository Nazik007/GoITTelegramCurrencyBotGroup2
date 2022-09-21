package telegram_bot_group_2.keyboars;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import telegram_bot_group_2.settings.Setting;

public abstract class Menu {
    private Setting settings;
//    public abstract InlineKeyboardMarkup keyboardSettings (Setting setting);

    public abstract  InlineKeyboardMarkup keyboardStart();

    public Menu(Setting settings) {
        this.settings = settings;
    }

//    public InlineKeyboardMarkup keyboardBanks (long chatId) {
//
//    }
}
