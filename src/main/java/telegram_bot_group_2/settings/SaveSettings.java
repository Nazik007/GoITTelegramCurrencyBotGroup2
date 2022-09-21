package telegram_bot_group_2.settings;

public class SaveSettings implements Runnable {

    private Settings settings;

    public SaveSettings(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void run() {
        settings.save();
    }
}
