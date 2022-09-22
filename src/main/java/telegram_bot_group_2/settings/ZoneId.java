package telegram_bot_group_2.settings;

public enum ZoneId {
    UTC_ZERO(0,"UTC 0", true),
    UTC_ONE(1,"UTC +1", false),
    UTC_TWO(2,"UTC +2", false),
    UTC_THREE(3, "UTC +3", false),
    UTC_FOUR(4,"UTC +4", false),
    UTC_FIVE(5,"UTC +5", false),
    UTC_SIX(6,"UTC +6", false),
    UTC_SEVEN(7,"UTC +7", false),
    UTC_EIGHT(8,"UTC +8", false),
    UTC_NINE(9,"UTC +9", false),
    UTC_TEN(10,"UTC +10", false),
    UTC_ELEVEN(11,"UTC +11", false),
    UTC_TWELVE(12,"UTC +12", false),
    UTC_MINUS_ONE(-1,"UTC -1", false),
    UTC_MINUS_TWO(-2,"UTC -2", false),
    UTC_MINUS_THREE(-3,"UTC -3", false),
    UTC_MINUS_FOUR(-4,"UTC -4", false),
    UTC_MINUS_FIVE(-5,"UTC -5", false),
    UTC_MINUS_SIX(-6,"UTC -6", false),
    UTC_MINUS_SEVEN(-7,"UTC -7", false),
    UTC_MINUS_EIGHT(-8,"UTC -8", false),
    UTC_MINUS_NINE(-9,"UTC -9", false),
    UTC_MINUS_TEN(-10,"UTC -10", false),
    UTC_MINUS_ELEVEN(-11,"UTC -11", false),
    UTC_MINUS_TWELVE(-12,"UTC -12", false);
    private int zone;

    private String nameZone;
    private boolean select;


    ZoneId(int zone,String nameZone, boolean select) {
        this.zone = zone;
        this.nameZone = nameZone;
        this.select = select;
    }
    public int getZone() {
        return zone;
    }
    public String getNameZone(){
        return nameZone;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public static ZoneId convertToEnum (String text){
        for (ZoneId button: ZoneId.values()) {
            if (button.getNameZone().equals(text)) {
                return button;
            }
        }
        return null;
    }
}
