package com.homework.supersimple.model;

/**
 * Created by Davide Del Vecchio on 27/08/16.
 * Implements the characteristics of a CommonStock  as exemplified in the text
 */
public class CommonStock implements Stock {

    private final String symbol;


    private final double lastDividend;
    private  double tickerPrice;
    private final double parvalue;


    public String getSymbol() {
        return symbol;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public double getTickerPrice() {
        return tickerPrice;
    }

    public double getParvalue() {
        return parvalue;
    }

    public double calculateDividendYeld() {
        double yeld = 0.0;
        if (tickerPrice != 0)
            yeld = lastDividend / tickerPrice;
        return yeld;
    }

    public double calculatePERatio() {
        double ratio = 0.0;
        if (lastDividend > 0)
            ratio = tickerPrice / lastDividend;
        return ratio;
    }

    private CommonStock(Builder b) {
        this.symbol = b.symbol;
        this.lastDividend = b.lastDividend;
        this.tickerPrice = b.tickerPrice;
        this.parvalue = b.parvalue;
    }

    public String getTypeofStock() {
        return "Common";
    }

    @Override
    public void updatePrice(double v) {
        this.tickerPrice = v;
    }

    public static class Builder {

        private String symbol;
        private double lastDividend;
        private double tickerPrice;
        private double parvalue;

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder lastDividend(Double lastDividend) {
            this.lastDividend = lastDividend;
            return this;
        }

        public Builder tickerPrice(Double tickerPrice) {
            this.tickerPrice = tickerPrice;
            return this;
        }

        public Builder parvalue(Double parvalue) {
            this.parvalue = parvalue;
            return this;
        }

        public CommonStock createCommonStock() {
            return new CommonStock(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonStock that = (CommonStock) o;

        if (Double.compare(that.lastDividend, lastDividend) != 0) return false;
        if (Double.compare(that.tickerPrice, tickerPrice) != 0) return false;
        if (Double.compare(that.parvalue, parvalue) != 0) return false;
        return symbol != null ? symbol.equals(that.symbol) : that.symbol == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symbol != null ? symbol.hashCode() : 0;
        temp = Double.doubleToLongBits(lastDividend);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tickerPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(parvalue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CommonStock{" +
                "symbol='" + symbol + '\'' +
                ", lastDividend=" + lastDividend +
                ", tickerPrice=" + tickerPrice +
                ", parvalue=" + parvalue +
                '}';
    }
}
