# SuperSimpleStock

Homework for project Super Simple Stocks


 _Author_ : Davide Del Vecchio

 _Language_ :  java 8 (uses Lambda )

 _Build_    :  mvn clean package will generate supersimplestocks-1.0-SNAPSHOT.jar under taget folder

 _Usage_ : java -cp supersimplestocks-1.0-SNAPSHOT.jar  com.homework.supersimple.Simulator

# Considerations
 This application for each stock

calculate the dividend yield

ii. calculate the P/E Ratio
iii. record a trade, with timestamp, quantity of shares, buy or sell indicator and price
iv. Calculate Stock Price based on trades recorded in past 15 minutes

Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

The system is initialized with the following values :

###### Global Beverage Corporation Exchange
 
 Stock Symbol  | Type | Last Dividend | Fixed Dividend | Par Value
 ------------- | ---- | ------------: | :------------: | --------: 
 TEA           | Common    | 0  |    | 100
 POP           | Common    | 8  |    | 100
 ALE           | Common    | 23 |    | 60
 GIN           | Preferred | 8  | 2% | 100
 JOE           | Common    | 13 |    | 250