package telegram_bot_group_2.settings;
    public enum Banks {
        PRIVAT("ПриватБанк", "Privat", false),
        MONO("МоноБанк", "Monobank", false),
        NBU("НБУ", "NBU", false);

        private String bankNameUA;
        private String bankNameEN;
        private boolean select;

        Banks(String bankNameUA, String bankNameEN, boolean select) {
            this.bankNameUA = bankNameUA;
            this.bankNameEN = bankNameEN;
            this.select = select;
        }
        public String getBankNameEN() {
            return bankNameEN;
        }
        public void setBankNameEN(String bankNameEN) {
            this.bankNameEN = bankNameEN;
        }
        public String getBankNameUA() {
            return bankNameUA;
        }
        public void setBankNameUA(String bankNameUA) {
            this.bankNameUA = bankNameUA;
        }
        public boolean isSelect() { return select; }

        public void setSelect(boolean select) {
            this.select = select;
        }
        public static Banks convertToEnum (String text){
            for (Banks bank: Banks.values()) {
                if (bank.getBankNameEN().equals(text)) {
                    return bank;
                }
            }
            return null;
        }
}
