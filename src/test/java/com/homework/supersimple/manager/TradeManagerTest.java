package com.homework.supersimple.manager;

import com.homework.supersimple.Simulator;
import com.homework.supersimple.model.*;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by davide on 29/08/16.
 */
public class TradeManagerTest {
    private TradeRecord record ;
    private TradeRecord record2 ;
    private List<Stock> stocks;
    private TradeManager manager;
    private Date past;
    private double amountTEA,amountPOP,amountGIN,amountJOE,amountALE;
    @Before
    public void setUp() throws Exception {
        stocks = loadStocks();
        amountTEA =  43.0;
        amountPOP =  (((25*80)+(100*70.0))/125);
        amountALE =   58.0;
        amountGIN = 89.0;
        amountJOE =  89.0;
        Stock pop = new CommonStock.Builder()
                .symbol("POP")
                .parvalue(100.0)
                .lastDividend(8.0)
                .createCommonStock();
        record = new TradeRecord.Builder()
                .indicator(Indicator.BUY)
                .numberOfShares(25)
                .stock(pop).price(80.0)
                .timestamp(new Date())
                .createTradeRecord();
        Stock pop2 = new CommonStock.Builder()
                .symbol("POP")
                .parvalue(100.0)
                .lastDividend(8.0)
                .createCommonStock();
        String  dateinput = "28/08/2016 23:54:32" ;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        past = formatter.parse(dateinput);
        record2 = new TradeRecord.Builder()
                .indicator(Indicator.BUY)
                .numberOfShares(100)
                .stock(pop2).price(70.0)
                .timestamp(new Date())
                .createTradeRecord();
         List<TradeRecord> tradeRecords = new ArrayList<TradeRecord>();
        tradeRecords.add(record);
        tradeRecords.add(record2);
        TradeRecord recordTEA = new TradeRecord.Builder()
                .indicator(Indicator.BUY)
                .numberOfShares(35)
                .stock(stocks.get(0)).price(43.0)
                .timestamp(new Date())
                .createTradeRecord();
        TradeRecord recordGIN = new TradeRecord.Builder()
                .indicator(Indicator.SELL)
                .numberOfShares(77)
                .stock(stocks.get(3)).price(89.0)
                .timestamp(new Date())
                .createTradeRecord();
        TradeRecord recordALE = new TradeRecord.Builder()
                .indicator(Indicator.SELL)
                .numberOfShares(33)
                .stock(stocks.get(2)).price(58.0)
                .timestamp(new Date())
                .createTradeRecord();
        TradeRecord recordJOE = new TradeRecord.Builder()
                .indicator(Indicator.BUY)
                .numberOfShares(48)
                .stock(stocks.get(4)).price(89.0)
                .timestamp(new Date())
                .createTradeRecord();
        tradeRecords.add(recordALE);
        tradeRecords.add(recordGIN);
        tradeRecords.add(recordTEA) ;
        tradeRecords.add(recordJOE) ;
        manager = new TradeManager(stocks,tradeRecords);

    }
    @Test
    public void calculateStockPrice() throws Exception {

        System.out.println("Calculating Stock Price for :"+stocks.get(0).getSymbol());
        double actualResultTEA = manager.calculateStockPrice(stocks.get(0));
        assertThat(actualResultTEA,equalTo(amountTEA));
        System.out.println("Calculating Stock Price for :"+stocks.get(1).getSymbol());

        double actualResultPOP = manager.calculateStockPrice(stocks.get(1));
        assertThat(actualResultPOP,equalTo(amountPOP));
        System.out.println("Calculating Stock Price for :"+stocks.get(2).getSymbol());

        double actualResultALE = manager.calculateStockPrice(stocks.get(2));
        assertThat(actualResultALE,equalTo(amountALE));
        System.out.println("Calculating Stock Price for :"+stocks.get(3).getSymbol());
        double actualResultGIN = manager.calculateStockPrice(stocks.get(3));

        assertThat(actualResultGIN,equalTo(amountGIN));
        System.out.println("Calculating Stock Price for :"+stocks.get(4).getSymbol());

        double actualResultJOE = manager.calculateStockPrice(stocks.get(4));
        assertThat(actualResultJOE,equalTo(amountJOE));
    }

    private static List<Stock> loadStocks(){

        return Simulator.loadStocks();
    }

    @Test
    public void calculateGBCEAllShareIndex() throws Exception {
        double actualResult = manager.calculateGBCEAllShareIndex();
         assertThat(actualResult,equalTo(Math.sqrt(amountALE*amountJOE*amountGIN*amountTEA*amountPOP)));
    }

    @Test
    public void addTradeRecord() throws Exception {

    }

}