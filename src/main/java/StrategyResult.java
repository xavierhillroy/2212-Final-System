/**
 * @author Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose This class stores the results of a broker executing a trade
 */
import java.time.LocalDate;

public class StrategyResult {
    // Need quantity/ coin/ action
    private int quantity;
    private String coin;
    private String action;
    private LocalDate date;
    private double price;
    private Broker broker;
    private Strategy strategy;

    /**
     * constructor for a trade object
     * @param quantity number of coins purchased/sold
     * @param coin coin full name
     * @param action type of action (BUY/SELL)
     * @param date time of transaction
     * @param price cost per unit of transaction
     * @param broker broker executing trade
     * @param strategy strategy used to determine trade
     */
    public StrategyResult(int quantity, String coin, String action, LocalDate date, double price, Broker broker, Strategy strategy) {
        this.quantity = quantity;
        this.coin = coin;
        this.action = action;
        this.date = date;
        this.price = price;
        this.broker = broker;
        this.strategy = strategy;
    }


    /**
     * getter method for type of action
     * @return type of action
     */
    public String getAction() {
        return action;
    }

    /**
     * setter method for type of action
     * @param action type of action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * getter method for coin traded
     * @return string rep of coin traded
     */
    public String getCoin() {
        return coin;
    }

    /**
     * setter method for type of coin
     * @param coin string rep of coin
     */
    public void setCoin(String coin) {
        this.coin = coin;
    }

    /**
     * getter method for quantity of coins purchased
     * @return number of units purchased
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setter method for units purchased
     * @param quantity number of units purchaed
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * getter method for price per unit of transaction
     * @return price per unit of transaction
     */
    public double getPrice() {
        return price;
    }

    /**
     * setter method for price per unit of transaction
     * @param price price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * getter method for date of transaction
     * @return date of transaction occurence
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * setter method for date of exchange
     * @param date date to adjust to
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * getter method for broker involved in trade
     * @return broker involved in trade
     */
    public Broker getBroker() {
        return broker;
    }

    /**
     * setter method for broker used
     * @param broker broker object to adjust to
     */
    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    /**
     * getter method for strategy employed
     * @return strategy used for trade
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * setter method for strategy employed
     * @param strategy strategy to set
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
