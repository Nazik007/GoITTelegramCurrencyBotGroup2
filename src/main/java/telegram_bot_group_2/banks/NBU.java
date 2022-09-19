package telegram_bot_group_2.banks;


import java.util.Objects;

public class NBU {

    private int r030;
    private String txt;
    private float rate;
    private String cc;
    private String exchangedate;

    public int getR030() {
        return r030;
    }

    public String getTxt() {
        return txt;
    }

    public float getRate() {
        return rate;
    }

    public String getCc() {
        return cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBU nbu = (NBU) o;
        return r030 == nbu.r030 && Float.compare(nbu.rate, rate)
                == 0 && Objects.equals(txt, nbu.txt) && Objects.equals(cc, nbu.cc) && Objects.equals(exchangedate, nbu.exchangedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r030, txt, rate, cc, exchangedate);
    }

    @Override
    public String toString() {
        return "NBU{" +
                "r030=" + r030 +
                ", txt='" + txt + '\'' +
                ", rate=" + rate +
                ", cc='" + cc + '\'' +
                ", exchangedate='" + exchangedate + '\'' +
                '}';
    }
}
