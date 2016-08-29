package com.homework.supersimple.model;

/**
 * Created by davide on 28/08/16.
 * This class implements the basic responsability of PreferredStock
 * client will instatiate it buy the proper Builder interface
 * and will derive the PE Ratio and Dividend Yeld
 * the PE Ratio is calculated by using the last dividend
 */
public class PreferredStock implements Stock {
    private final String symbol;

    private final double lastDividend;
    private  double tickerPrice;
    private final double parvalue;
    private final double fixed_dividend;


    private PreferredStock(Builder b) {
        this.symbol = b.symbol;
        this.lastDividend = b.lastDividend;
        this.tickerPrice = b.tickerPrice;
        this.parvalue = b.parvalue;
        this.fixed_dividend = b.fixed_dividend;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double getLastDividend() {
        return this.lastDividend;
    }

    public String getTypeofStock() {
        return "Preferred";
    }

    public double getTickerPrice() {
        return this.tickerPrice;
    }

    public void updatePrice(double prize) { this.tickerPrice=prize;}

    public double getParvalue() {
        return this.parvalue;
    }


    public double calculateDividendYeld() {
        double yeld = -1.0;
        if (tickerPrice != 0)
            yeld = (fixed_dividend * parvalue) / tickerPrice;
        return yeld;
    }

    public double calculatePERatio() {
        double ratio = -1.0;
        if (lastDividend != 0)
            ratio = tickerPrice / lastDividend;
        return ratio;
    }

    public static class Builder {

        private String symbol;
        private double lastDividend;
        private double tickerPrice;
        private double parvalue;
        private double fixed_dividend;

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder lastDividend(double lastDividend) {
            this.lastDividend = lastDividend;
            return this;
        }

        public Builder tickerPrice(double tickerPrice) {
            this.tickerPrice = tickerPrice;
            return this;
        }

        public Builder parvalue(double parvalue) {
            this.parvalue = parvalue;
            return this;
        }

        public Builder fixed_dividend(double fixed_dividend) {
            this.fixed_dividend = fixed_dividend;
            return this;
        }

        public PreferredStock createPreferredStock() {
            return new PreferredStock(this);
        }
    }

    @Override
    public String toString() {
        return "PreferredStock{" +
                "symbol='" + symbol + '\'' +
                ", lastDividend=" + lastDividend +
                ", tickerPrice=" + tickerPrice +
                ", parvalue=" + parvalue +
                ", fixed_dividend=" + fixed_dividend +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreferredStock that = (PreferredStock) o;

        if (Double.compare(that.lastDividend, lastDividend) != 0) return false;
        if (Double.compare(that.tickerPrice, tickerPrice) != 0) return false;
        if (Double.compare(that.parvalue, parvalue) != 0) return false;
        if (Double.compare(that.fixed_dividend, fixed_dividend) != 0) return false;
        return symbol.equals(that.symbol);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symbol.hashCode();
        temp = Double.doubleToLongBits(lastDividend);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tickerPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(parvalue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fixed_dividend);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
