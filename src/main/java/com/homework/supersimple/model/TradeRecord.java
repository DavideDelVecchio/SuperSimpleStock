package com.homework.supersimple.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by davide on 28/08/16.
 */
public class TradeRecord {
    private final String id = UUID.randomUUID().toString();
    private final Stock stock;

    public String getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    private final Date timestamp;
    private final double price;
    private final int numberOfShares;
    private Indicator  indicator ;

    public TradeRecord(Builder b) {
        this.stock = b.stock;
        this.timestamp = b.timestamp;
        this.price = b.price;
        this.numberOfShares = b.numberOfShares;
        this.indicator = b.indicator;

    }
    public static class Builder{

        private Stock stock;
        private Date timestamp;
        private double price;
        private int numberOfShares;
        private Indicator indicator;

        public Builder stock(Stock stock) {
            this.stock = stock;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder numberOfShares(int numberOfShares) {
            this.numberOfShares = numberOfShares;
            return this;
        }

        public Builder indicator(Indicator ind) {
            this.indicator = ind;
            return this;
        }
        public TradeRecord createTradeRecord() {

            return new TradeRecord(this);
        }
    }
}
