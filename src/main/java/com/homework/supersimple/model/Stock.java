package com.homework.supersimple.model;

/**
 * Created by davide on 28/08/16.
 */
public interface Stock {
    String getSymbol();

    double getLastDividend();

    double getTickerPrice();

    double getParvalue();

    double calculateDividendYeld();

    double calculatePERatio();

    String getTypeofStock();

    void updatePrice(double v);
}
