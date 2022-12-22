/**
 * @author Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose This class acts as a container for the Strategy results and implements the Observer design pattern. This class  acts as a subject
 */
import java.util.ArrayList;
import java.util.Observer;
/*TradeLog = ConcreteSubject (Observer design pattern)
* */

public class TradeLog implements DisplayInfo{

    private ArrayList<TradingObservers> observers = new ArrayList<>();
    private ArrayList<StrategyResult> trades = new ArrayList<>();

    /**
     * getter method for list of trades
     * @return arrayList of trade objects (strategyResults)
     */
    public ArrayList<StrategyResult> getTradeLog() {
        return trades;
    }

    /**
     * getter method for a trade object
     * @param broker broker who completed the trade
     * @param date date the trade was completed
     * @return a trade that matches the param
     */
    public StrategyResult getTrade(String broker, String date){

        for (StrategyResult trade: trades){
            if (trade.getDate().equals(date) && trade.getBroker().getName().equals(broker))return trade;
        }
        return null;
    }

    /**
     * method to trigger observer functions related to trades and trade completion
     */
    public void notifyObservers(){
        for (TradingObservers o : observers){
            o.update();
        }
    }

    /**
     * method to insert a trade to the log
     * @param s the trade object to insert
     * @return whether the addition was succesful
     */
    public boolean addTrade(StrategyResult s){
        return trades.add(s);
    }

    /**
     * method to insert an additional observer function
     * @param observer the observer to add
     */
    public void attach(TradingObservers observer){
        observers.add(observer);
    }

}
