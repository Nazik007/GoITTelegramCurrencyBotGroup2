package telegram_bot_group_2.banks;

import java.util.Objects;

public class Privat {
    private String ccy; //23232
    private String base_ccy;
    private float buy;
    private float sale;
    public String getCcy() { return ccy; }
    public String getBase_ccy() { return base_ccy; }
    public float getBuy() { return buy; }
    public float getSale() { return sale; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privat privat = (Privat) o;
        return Float.compare(privat.buy, buy) == 0 && Float.compare(privat.sale, sale) == 0 && ccy.equals(privat.ccy) && base_ccy.equals(privat.base_ccy);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ccy, base_ccy, buy, sale);
    }
    @Override
    public String toString() {
        return "Privat{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
