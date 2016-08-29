package com.homework.supersimple;

import com.homework.supersimple.manager.TradeManager;
import com.homework.supersimple.model.*;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by davide on 29/08/16.
 */
public class Simulator {
    private static List<Stock> stocksList;
    private static List<TradeRecord> tradeRecords ;
    private static DecimalFormat df = new DecimalFormat("#.00");
    public static List<Stock> loadStocks(){
        List<Stock> stocksList = new ArrayList<Stock>();
        stocksList.add(new CommonStock.Builder()
                .symbol(StockTicker.TEA.toString())
                .lastDividend(0.0)
                .parvalue(100.0).createCommonStock());
        stocksList.add(new CommonStock.Builder()
                .symbol(StockTicker.POP.toString())
                .lastDividend(8.0)
                .parvalue(100.0).createCommonStock());
        stocksList.add(new CommonStock.Builder()
                .symbol(StockTicker.ALE.toString())
                .lastDividend(23.0)
                .parvalue(60.0).createCommonStock());
        stocksList.add(new PreferredStock.Builder()
                .symbol(StockTicker.GIN.toString())
                .lastDividend(8.0)
                .parvalue(100.0).fixed_dividend(2).createPreferredStock());
        stocksList.add(new CommonStock.Builder()
                .symbol(StockTicker.JOE.toString())
                .lastDividend(13.0)
                .parvalue(250.0).createCommonStock());

        return stocksList;
    }
     public static void main(String args[]){
                 stocksList = loadStocks();
         Random rand = new Random();


         // This simulator Initialize some trade records with random values

         int tradesNumber = rand.nextInt(10000);
         System.out.println("Generating values for total trades: " + tradesNumber);


         tradeRecords = new ArrayList<TradeRecord>();
         for (int i = 0; i < tradesNumber; i++) {
             Stock stock = stocksList.get(rand.nextInt(stocksList.size()));

             Indicator tickerIndicator;

             tickerIndicator = Indicator.BUY;


             double price = Math.abs(Math.round(10 * (rand.nextGaussian() + 1) * 100) / 100d);

             int sharesQuantity = ajustNumberOfShares(tradeRecords, tickerIndicator, rand);
              tradeRecords.add(new TradeRecord.Builder().stock(stock).indicator(tickerIndicator).numberOfShares(sharesQuantity).price(price).timestamp(new Date()).createTradeRecord());

         }
           adjustTickerPrice(tradeRecords);


         for (Stock stock : stocksList) {
             List<TradeRecord> tradeForStock = filterStocks(stock,tradeRecords);
             double tickerPrice = tradeForStock.get(tradeForStock.size() - 1).getPrice() - tradeForStock.get(0).getPrice();
             String stockSymbol = stock.getSymbol();


             double dividendYield = stock.calculateDividendYeld();
             System.out.println("Dividend Yield for " + stockSymbol + ": " + df.format(dividendYield));
             System.out.println("P/E Ratio for " + stockSymbol + ": " + df.format(stock.calculatePERatio()));
             System.out.println("StockPrice for " + stockSymbol + ": " + df.format(stock.getTickerPrice()));
         }
         TradeManager manager= new TradeManager(stocksList,tradeRecords);
         System.out.println("GBCE All Share Index: " + df.format(manager.calculateGBCEAllShareIndex()));



     }

    private static int ajustNumberOfShares(List<TradeRecord> stockTradesList, Indicator tradeOperationFlag, Random rand) {
        int result = rand.nextInt(1000);

        int totalShares = 0;

        for (Iterator<TradeRecord> iterator = stockTradesList.iterator(); iterator.hasNext();) {
            TradeRecord trade = iterator.next();
            totalShares += (trade.getPrice() * ((trade.getIndicator().equals(Indicator.SELL) ? -1 : 1)));
        }

        if (tradeOperationFlag.equals(Indicator.SELL)) {
            while (totalShares - result < 0) {
                result = rand.nextInt(1000);
            }
        }

        return result;
    }
    private static void adjustTickerPrice(List<TradeRecord> list) {
        for (Stock stock : stocksList) {
            List<TradeRecord> listTrades = filterStocks(stock,list);
            if(listTrades.get(listTrades.size() - 1).getPrice() < listTrades.get(0).getPrice()){
                listTrades.get(listTrades.size() - 1).getStock().updatePrice(listTrades.get(0).getPrice() + 1d);
            }
        }

    }
    private static List<TradeRecord> filterStocks(Stock stock,List<TradeRecord> tradeRecords){
        return    tradeRecords.stream().filter(t-> t.getStock().equals(stock)).collect(Collectors.toList());
    }
}
