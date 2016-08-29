package com.homework.supersimple.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * Created by davide on 28/08/16.
 */
public class CommonStockTest {
    private CommonStock stock;
    @Test
    public void calculateDividendYeld() throws Exception {
                 double actualResul = stock.calculateDividendYeld();
                 assertThat(actualResul,equalTo(0.0));
    }

    @Test
    public void calculatePERatio() throws Exception {
            double actualResult = stock.calculatePERatio();
            assertThat(actualResult,equalTo(0.0));

    }

    @Test
    public void getTypeofStock() throws Exception {
         String value = stock.getTypeofStock();
         assertThat(value,equalTo("Common"));
    }

    @Before
    public void setUp() throws Exception {

        stock = new CommonStock.Builder()
                .symbol("TEA")
                .lastDividend(0.0)
                .parvalue(100.0)
                .tickerPrice(100.0)
                .createCommonStock();
    }
}