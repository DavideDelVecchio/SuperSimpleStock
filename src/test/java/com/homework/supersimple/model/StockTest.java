package com.homework.supersimple.model;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by davide on 27/08/16.
 */
public class StockTest {
    private PreferredStock stock ;

    @Before
    public void setUp() throws Exception {
        stock = new PreferredStock.Builder()
                .symbol("GIN")
                .lastDividend(8.0)
                .fixed_dividend(2.0)
                .parvalue(100.0)
                .tickerPrice(100.0)
                .createPreferredStock();

    }

    @Test
    public void testDividendYeldCalculation() throws Exception {
        double actualResult = stock.calculateDividendYeld();
        assertThat(actualResult,equalTo(2.0));

    }


    @Test
    public void testPERatioCalculation() throws Exception {
          double actualResult = stock.calculatePERatio();
        assertThat(actualResult,equalTo(12.5));
    }
}
