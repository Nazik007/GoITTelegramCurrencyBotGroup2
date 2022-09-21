package telegram_bot_group_2.settings;

public enum NotificationTime {
    NINE (9, false),
    TEN (10,false),
    ELEVEN (11,false),
    TWELVE (12,false),
    THIRTEEN (13,false),
    FOURTEEN (14,false),
    FIFTEEN (15,false),
    SIXTEEN (16,false),
    SEVENTEEN (17,false),
    EIGHTEEN (18,false),
    SWICH_OFF (0,false);


    private int time;
    private boolean select;

    NotificationTime(int time, boolean select) {
        this.time = time;
        this.select = select;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public static NotificationTime convertToEnum (String text){
        for (NotificationTime time: NotificationTime.values()) {
            if (String.valueOf(time.getTime()).equals(text)) {
                return time;
            }
        }
        return null;
    }
}