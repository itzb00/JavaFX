package ITEC3150;

import java.io.Serializable;
import java.util.Objects;

public class Stonk implements Serializable {
    private String ticker;
    private int shares;
    private double price;

    public Stonk(String gme, int i, double v) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stonk stonk = (Stonk) o;
        return shares == stonk.shares && Double.compare(stonk.price, price) == 0 && Objects.equals(ticker, stonk.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker, shares, price);
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stonk{" +
                "ticker='" + ticker + '\'' +
                ", shares=" + shares +
                ", price=" + price +
                '}';
    }
}
