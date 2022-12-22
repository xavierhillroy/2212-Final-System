/**
 * @author Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose Strategy interface that is used during the Factory implementation
 * this interface is used by the all Strategy classes
 */
public interface Strategy {

    /**
     * getter method for name of a strategy
     * @return the denoted name for the strat
     */
    String getName();

    /**
     * trade calculator for a broker
     * @param coinList list of interested (and relevant) coins
     * @param coinDataBase database of current coin info
     * @param broker the broker looking to complete the trade
     * @return a trade object representing the brokers action
     */
    StrategyResult determineExecution(String[] coinList, CoinsInfo coinDataBase, Broker broker);

}
