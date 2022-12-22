/**
 * @author Michael Schmidt, Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose Value investing strategy (implementation of strategy type)
 */

import java.time.LocalDate;
import java.util.Locale;

public class StrategyValueInvesting implements Strategy{

    private final String stratName = "VALUESTRAT";
    private final String strategyCoin1 = "litecoin";
    private final String strategyCoin2 = "ethereum";
    /**
     * getter method for name of strategy
     * @return name of strategy
     */
    @Override
    public String getName() {
        return stratName;
    }
    /**
     *Determines which trade to make depending on the prices of coins
     * @param coinList
     * @param coinDataBase
     * @param broker
     * @return Strategy result Object
     */
    @Override
    public StrategyResult determineExecution(String[] coinList, CoinsInfo coinDataBase, Broker broker) {

        // if the required coins are not present the strategy will return a failed strategy result object
        if (!validateUsrCoins(coinList)) {
            MainUI.catchCoinError(broker);
            return new StrategyResult(-1 ,null,"Fail",java.time.LocalDate.now(),-1,broker, broker.getStrategy());
        }
        // if litecoin > 3000 buy 600 litecoins
        if (coinDataBase.getCoinInfo(strategyCoin1).getPrice() > 3000){
            StrategyResult testResult = new StrategyResult(600, strategyCoin1,"Buy", LocalDate.now(),
                    coinDataBase.getCoinInfo(strategyCoin1).getPrice(),
                    broker, this);

            return testResult;
            // else sell 300 ethereum
        } else {
            StrategyResult testResult = new StrategyResult(300, strategyCoin2,"Sell", LocalDate.now(),
                    coinDataBase.getCoinInfo(strategyCoin1).getPrice(),
                    broker, this);
            return testResult;
        }

    }
    /**
     * Searches if a coin is in a coinlist
     * @param targetCoin
     * @param coins
     * @return a boolean, true if coin is in the list false if the coin is not in the list
     */
    private boolean linearSearch(String targetCoin, String[] coins){ // simple linear search algo to find coin
        for (String coin: coins){
            if (targetCoin.toLowerCase(Locale.ROOT).equals(coin))
                return true;
        }// end of loop
        return false;
    }
    /**
     * getter method for required coins
     * @return String[] containing the coins the strategy is interested in
     */
    public String[] getRequiredCoins(){
        return new String[]{strategyCoin1,strategyCoin2};
    }
    /**
     *Check that the broker has the proper coins that are needed for the strategy to execute
     * @param coinList
     * @return boolean object, true the broker associates with the required coins for the strategy, and false if it doesnt
     */
    private boolean validateUsrCoins(String[] coinList){
        boolean foundLayer1 = false;
        for (int i = 0; i < coinList.length; i ++){
            if (strategyCoin1.equals(coinList[i])){
                foundLayer1 = true;
            }
        }
        if (!foundLayer1){
            return false;
        }
        boolean foundLayer2 = false;
        for (int i = 0; i < coinList.length; i ++){
            if (strategyCoin2.equals(coinList[i])){
                foundLayer2 = true;
            }
        }
        return foundLayer2;
    }

}
