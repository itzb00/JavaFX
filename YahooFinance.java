package ITEC3150;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.histquotes2.HistDividendsRequest;
import yahoofinance.histquotes2.HistQuotes2Request;
import yahoofinance.histquotes2.HistSplitsRequest;
import yahoofinance.histquotes2.HistoricalDividend;
import yahoofinance.histquotes2.HistoricalSplit;
import yahoofinance.quotes.query1v7.StockQuotesQuery1V7Request;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.csv.StockQuotesData;
import yahoofinance.quotes.csv.StockQuotesRequest;
import yahoofinance.quotes.stock.StockStats;


public class YahooFinance {

    private static final Logger log = LoggerFactory.getLogger(YahooFinance.class);

    private final String symbol;
    private String name;
    private String currency;
    private String stockExchange;

    private StockQuote quote;
    private StockStats stats;
    private StockDividend dividend;

    private List<HistoricalQuote> history;
    private List<HistoricalDividend> dividendHistory;
    private List<HistoricalSplit> splitHistory;

    public YahooFinance(String symbol) {
        this.symbol = symbol;
    }

    private void update() throws IOException {
        if(yahoofinance.YahooFinance.QUOTES_QUERY1V7_ENABLED.equalsIgnoreCase("true")) {
            StockQuotesQuery1V7Request request = new StockQuotesQuery1V7Request(this.symbol);
            yahoofinance.Stock stock = request.getSingleResult();
            if (stock != null) {
                this.setName(stock.getName());
                this.setCurrency(stock.getCurrency());
                this.setStockExchange(stock.getStockExchange());
                this.setQuote(stock.getQuote());
                this.setStats(stock.getStats());
                this.setDividend(stock.getDividend());
                log.info("Updated Stock with symbol: {}", this.symbol);
            } else {
                log.error("Failed to update Stock with symbol: {}", this.symbol);
            }
        } else {
            StockQuotesRequest request = new StockQuotesRequest(this.symbol);
            StockQuotesData data = request.getSingleResult();
            if (data != null) {
                this.setQuote(data.getQuote());
                this.setStats(data.getStats());
                this.setDividend(data.getDividend());
                log.info("Updated Stock with symbol: {}", this.symbol);
            } else {
                log.error("Failed to update Stock with symbol: {}", this.symbol);
            }
        }
    }


    public boolean isValid() {
        return this.name != null;
    }


    public StockQuote getQuote() {
        return this.quote;
    }

    public StockQuote getQuote(boolean refresh) throws IOException {
        if(refresh) {
            this.update();
        }
        return this.quote;
    }

    public void setQuote(StockQuote quote) {
        this.quote = quote;
    }
    public StockStats getStats() {
        return this.stats;
    }
    public StockStats getStats(boolean refresh) throws IOException {
        if(refresh) {
            this.update();
        }
        return this.stats;
    }
    public void setStats(StockStats stats) {
        this.stats = stats;
    }
    public StockDividend getDividend() {
        return this.dividend;
    }
    public StockDividend getDividend(boolean refresh) throws IOException {
        if(refresh) {
            this.update();
        }
        return this.dividend;
    }
    public void setDividend(StockDividend dividend) {
        this.dividend = dividend;
    }

    public List<HistoricalQuote> getHistory() throws IOException {
        if(this.history != null) {
            return this.history;
        }
        return this.getHistory(HistQuotesRequest.DEFAULT_FROM);
    }

    public List<HistoricalQuote> getHistory(Interval interval) throws IOException {
        return this.getHistory(HistQuotesRequest.DEFAULT_FROM, interval);
    }

    public List<HistoricalQuote> getHistory(Calendar from) throws IOException {
        return this.getHistory(from, HistQuotesRequest.DEFAULT_TO);
    }

    public List<HistoricalQuote> getHistory(Calendar from, Interval interval) throws IOException {
        return this.getHistory(from, HistQuotesRequest.DEFAULT_TO, interval);
    }

    public List<HistoricalQuote> getHistory(Calendar from, Calendar to) throws IOException {
        return this.getHistory(from, to, Interval.MONTHLY);
    }

    public List<HistoricalQuote> getHistory(Calendar from, Calendar to, Interval interval) throws IOException {
        if(yahoofinance.YahooFinance.HISTQUOTES2_ENABLED.equalsIgnoreCase("true")) {
            HistQuotes2Request hist = new HistQuotes2Request(this.symbol, from, to, interval);
            this.setHistory(hist.getResult());
        } else {
            HistQuotesRequest hist = new HistQuotesRequest(this.symbol, from, to, interval);
            this.setHistory(hist.getResult());
        }
        return this.history;
    }

    public void setHistory(List<HistoricalQuote> history) {
        this.history = history;
    }

    public List<HistoricalDividend> getDividendHistory() throws IOException {
        if(this.dividendHistory != null) {
            return this.dividendHistory;
        }
        return this.getDividendHistory(HistDividendsRequest.DEFAULT_FROM);
    }


    public List<HistoricalDividend> getDividendHistory(Calendar from) throws IOException {
        return this.getDividendHistory(from, HistDividendsRequest.DEFAULT_TO);
    }

    public List<HistoricalDividend> getDividendHistory(Calendar from, Calendar to) throws IOException {
        if(yahoofinance.YahooFinance.HISTQUOTES2_ENABLED.equalsIgnoreCase("true")) {
            HistDividendsRequest histDiv = new HistDividendsRequest(this.symbol, from, to);
            this.setDividendHistory(histDiv.getResult());
        } else {
            // Historical dividends cannot be retrieved without CRUMB
            this.setDividendHistory(null);
        }
        return this.dividendHistory;
    }

    public void setDividendHistory(List<HistoricalDividend> dividendHistory) {
        this.dividendHistory = dividendHistory;
    }

    public List<HistoricalSplit> getSplitHistory() throws IOException {
        if(this.splitHistory != null) {
            return this.splitHistory;
        }
        return this.getSplitHistory(HistSplitsRequest.DEFAULT_FROM);
    }

    public List<HistoricalSplit> getSplitHistory(Calendar from) throws IOException {
        return this.getSplitHistory(from, HistSplitsRequest.DEFAULT_TO);
    }

    public List<HistoricalSplit> getSplitHistory(Calendar from, Calendar to) throws IOException {
        if(yahoofinance.YahooFinance.HISTQUOTES2_ENABLED.equalsIgnoreCase("true")) {
            HistSplitsRequest histSplit = new HistSplitsRequest(this.symbol, from, to);
            this.setSplitHistory(histSplit.getResult());
        } else {
            // Historical splits cannot be retrieved without CRUMB
            this.setSplitHistory(null);
        }
        return this.splitHistory;
    }

    public void setSplitHistory(List<HistoricalSplit> splitHistory) {
        this.splitHistory = splitHistory;
    }

    public String getSymbol() {
        return symbol;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    @Override
    public String toString() {
        return this.symbol + ": " + this.quote.getPrice();
    }

    public void print() {
        System.out.println(this.symbol);
        System.out.println("--------------------------------");
        for (Field f : this.getClass().getDeclaredFields()) {
            try {
                System.out.println(f.getName() + ": " + f.get(this));
            } catch (IllegalArgumentException ex) {
                log.error(null, ex);
            } catch (IllegalAccessException ex) {
                log.error(null, ex);
            }
        }
        System.out.println("--------------------------------");
    }

}