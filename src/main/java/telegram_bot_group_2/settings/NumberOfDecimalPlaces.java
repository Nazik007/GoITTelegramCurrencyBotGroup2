package telegram_bot_group_2.settings;

public enum NumberOfDecimalPlaces {
    TWO (2, "twoPlaces",false),
    THREE (3, "threePlaces",false),
    FOUR (4, "fourPlaces",false);

    private String nameDecPlaces;
    private int intNumber;
    private boolean select;

    NumberOfDecimalPlaces(int intNumber, String nameDecPlaces, boolean select) {
        this.intNumber = intNumber;
        this.nameDecPlaces = nameDecPlaces;
        this.select = select;
    }

    public int getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(int intNumber) {
        this.intNumber = intNumber;
    }

    public String getNameDecPlaces() {
        return nameDecPlaces;
    }

    public void setNameDecPlaces(String nameDecPlaces) {
        this.nameDecPlaces = nameDecPlaces;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public static NumberOfDecimalPlaces convertToEnum (String text){
        for (NumberOfDecimalPlaces decimalPlaces: NumberOfDecimalPlaces.values()) {
            if (decimalPlaces.getNameDecPlaces().equals(text)) {
                return decimalPlaces;
            }
        }
        return null;
    }
}
