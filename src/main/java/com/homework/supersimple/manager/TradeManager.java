package com.homework.supersimple.manager;

import com.homework.supersimple.model.Stock;
import com.homework.supersimple.model.TradeRecord;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by davide on 29/08/16.
 */
public class TradeManager {
    public TradeManager(List<Stock> stocks, List<TradeRecord> trades) {
        this.stocks = stocks;
        this.trades = trades;
    }

    private List<Stock> stocks;
    private List<TradeRecord> trades;
    private static final int filterIntervalSecs = 15 * 60;

    public double calculateStockPrice(Stock stock) {
        List<TradeRecord> selected_trades = trades.stream().filter(t -> t.getStock().equals(stock)).collect(Collectors.toList());
        if (selected_trades == null)
            return 0;
        List<TradeRecord> recent = filterByTime(selected_trades);
        if (recent == null)
            return 0;
        LongStream filteredShares= recent.stream().mapToInt(t -> t.getNumberOfShares()).asLongStream();
        if (filteredShares == null)
            return 0;
        long total_shares = filteredShares.sum();
        if (total_shares == 0) {
            return 0;
        }
        double share_prizes = recent.stream().mapToDouble(t -> t.getNumberOfShares() * t.getPrice()).sum();
        return share_prizes / total_shares;

    }

    public double calculateGBCEAllShareIndex() {
        OptionalDouble allpriceproduct= stocks.stream().mapToDouble(t -> calculateStockPrice(t)).reduce((a, b) -> a * b);
        double product =allpriceproduct.orElse(0.0);
        return Math.sqrt(product);
    }

    private List<TradeRecord> filterByTime(List<TradeRecord> alltrades) {
        Date now = new Date();
        List<TradeRecord> recent = alltrades.stream().
                filter(t -> Duration.between(t.getTimestamp().toInstant(), now.toInstant()).getSeconds() < filterIntervalSecs)
                .collect(Collectors.toList());
        return recent;

    }

    public void addTradeRecord(TradeRecord record) {
        trades.add(record);
    }

}
