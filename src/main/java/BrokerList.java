/**
 * @author Xavier Hill Roy and Michael Schmidt
 * CS2212 - Intro to Software Engineering
 * @purpose This class is a container for the broker and is the class contains the method that executes the trades.
 * This class is what is changing the state of the subject in the observer design pattern this also implements
 * the strategy design pattern as only the strategy selected by the client is implemented
 */
import java.util.ArrayList;

/*BrokerList = Controller (Observer design pattern)*/
public class BrokerList{

    private ArrayList<Broker> brokerArrayList = new ArrayList();
    private String[] fullCoinList;
    private CoinsInfo coinDataBase = new CoinsInfo();

    /**
     * getter method for list of brokers
     * @return an array list of all active brokers
     */
    public ArrayList<Broker> getBrokerArrayList() {
        return this.brokerArrayList;
    }

    /**
     * getter method for specific broker
     * @param brokerName string id of broker
     * @return broker related to passed string
     */
    public Broker getBroker(String brokerName) {
        for (int i = 0; i < brokerArrayList.size(); i ++){
            if (brokerArrayList.get(i).getName().equals(brokerName)){
                return brokerArrayList.get(i);
            }
        }
        return null;
    }

    /**
     * method to remove a specified broker from the list, also updates the coin list
     * @param b broker object to remove
     * @return whether the removal was succesful
     */
    public boolean removeBroker(Broker b) {
        boolean retValue = this.brokerArrayList.remove(b);
        genCoinList();
        return retValue;
    }

    /**
     * method to insert a broker into the arraylist
     * @param b broker to add
     * @return boolean representation of addition success
     */
    public boolean addBroker(Broker b) {
        boolean retValue = this.brokerArrayList.add(b);
        genCoinList();
        return retValue;
    }

    /**
     * method to update the full coin list.
     * will loop through all brokers and determine
     */
    private void genCoinList(){ //UPDATES THE FULL COIN LIST WHENEVER A CHANGE IS MADE TO THE BROKER LIST
        ArrayList<String> startList = new ArrayList<>(); //list of coins
        for (int i = 0; i < brokerArrayList.size(); i ++){ //loop through brokers
            for (int j = 0; j < brokerArrayList.get(i).getCryptoTickerList().length; j ++){ //loop through broker coinlist
                if (!startList.contains(brokerArrayList.get(i).getCryptoTickerList()[j])){ //ignore duplicates
                    startList.add(brokerArrayList.get(i).getCryptoTickerList()[j]); //add to list
                }
            }
        }
        this.fullCoinList = new String[startList.size()];
        for (int i = 0; i < fullCoinList.length; i ++){
            fullCoinList[i] = startList.get(i);
        }
    }

    /**
     * function to call upon each broker in list to evaluate, and perform, trades
     * @param tradeLog the current log of trades.
     */
    public void trade(TradeLog tradeLog){

        coinDataBase.updateInfo(fullCoinList);

        for (int i = 0; i < brokerArrayList.size(); i ++){
            StrategyResult result = brokerArrayList.get(i).determineTrade(coinDataBase);

            tradeLog.addTrade(result);
        }
        tradeLog.notifyObservers();

    }

    /**
     * method to determine if a coin is within the current broker list's scope
     * @param ticker coin to check
     * @return whether the coin exists in the list
     */
    private boolean tickerInList(String ticker){
        for (int i = 0; i < fullCoinList.length; i ++){
            if (fullCoinList[i].equals(ticker)){
                return true;
            }
        }
        return false;
    }

    /**
     * method to clear the list of brokers
     */
    public void clearBrokerList(){
        this.brokerArrayList.clear();
    }

    /**
     * getter method for a full coin list
     * @return a full list of coins
     */
    public String[] getExhaustiveCoinList(){
        return fullCoinList;
    }
}
