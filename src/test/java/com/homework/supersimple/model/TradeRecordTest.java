package com.homework.supersimple.model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by davide on 28/08/16.
 */
public class TradeRecordTest {
    private TradeRecord record ;
    private TradeRecord record2 ;
    private Date past;
    @Before
    public void setUp() throws Exception {
            Stock pop = new CommonStock.Builder()
                    .symbol("POP")
                    .parvalue(100.0)
                    .lastDividend(8.0)
                    .tickerPrice(50.0)
                    .createCommonStock();
                 record = new TradeRecord.Builder()
                         .indicator(Indicator.BUY)
                         .numberOfShares(100)
                         .stock(pop).price(70.0)
                         .timestamp(new Date())
                         .createTradeRecord();
        Stock pop2 = new CommonStock.Builder()
                .symbol("POP")
                .parvalue(100.0)
                .lastDividend(8.0)
                .tickerPrice(50.0)
                .createCommonStock();
        String  dateinput = "28/08/2016 23:54:32" ;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        past = formatter.parse(dateinput);
        record2 = new TradeRecord.Builder()
                .indicator(Indicator.BUY)
                .numberOfShares(100)
                .stock(pop2).price(70.0)
                .timestamp(past)
                .createTradeRecord();

    }
    @Test
    public void testFileterStocks() throws Exception {
               List<TradeRecord>  records = new ArrayList<TradeRecord>();
               records.add(record);
               records.add(record2);
               Date now = new Date() ;
               System.out.println(StockTicker.ALE.toString());
               List<TradeRecord>  recent = records.stream().filter(t-> Duration.between(t.getTimestamp().toInstant(),now.toInstant()).getSeconds()< 15*60).collect(Collectors.toList());
               assertThat(recent.size(),equalTo(1));

    }

}