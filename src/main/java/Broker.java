/**
 * @author Xavier Hill Roy and Michael Schmidt
 * CS2212 - Intro to Software Engineering
 * @purpose Class to store broker information and facilitate the processing and dispersion of trades
 */
public class Broker {
    private String name;
    private String[] cryptoTickerList;
    private Strategy strategy;

    /**
     * constructor for a broker object
     * @param name name of broker
     * @param cryptoTickerList list of coins of interest
     * @param strategy strategy to employ when using trade
     */
    Broker(String name, String[] cryptoTickerList, Strategy strategy){
        setName(name);
        setCryptoTickerList(cryptoTickerList);
        setStrategy(strategy);
    }

    /**
     * setter method for name of broker
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter method for crypto list
     * @param cryptoTickerList list of all interested coins
     */
    public void setCryptoTickerList(String[] cryptoTickerList) {
        this.cryptoTickerList = cryptoTickerList;
    }

    /**
     * setter method for strategy
     * @param strategy strategy to employ
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * getter method for name of broker
     * @return name of broker
     */
    public String getName(){
        return name;
    }

    /**
     * getter method for list of interested coins
     * @return list of interested coins
     */
    public String[] getCryptoTickerList(){
        return cryptoTickerList; //NOTE THIS IS ACTUALLY THE FULL NAMES, NOT TICKERS
    }

    /**
     * getter method for strategy in use
     * @return strategy in use
     */
    public Strategy getStrategy(){
        return strategy;
    }

    /**
     * determine trade for broker based on strategy and interested coins
     * @param dataBase cumulative coin information
     * @return the trade outcome
     */
    public StrategyResult determineTrade(CoinsInfo dataBase){
        return strategy.determineExecution(cryptoTickerList,dataBase,this);
    }

}
