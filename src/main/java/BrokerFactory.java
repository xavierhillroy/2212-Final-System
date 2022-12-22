/**
 * @author Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose This class implements the factory
 */
public class BrokerFactory {
    /**
     * creates appropriate broker object based on attributes selected by the user
     * @param usrSelection
     * @return Broker
     */
    public Broker create(UsrSelection usrSelection){// factory for creating the broker
        return new Broker(
                retrieveName(usrSelection),
                retrieveTckrLst(usrSelection),
                retrieveStrategy(usrSelection));
    }

    /**
     * Extracts the broker name from the usrSelection object
     * @param usrSelection
     * @return String name of the broker
     */
    private String retrieveName(UsrSelection usrSelection){
        return usrSelection.getName();
    }

    /**
     * Extracting the coins associated with the broker, takes in a string and splits using ',' as a delimiter
     * @param usrSelection
     * @return String[] a list containing the names of the desired coins
     */
    private String[] retrieveTckrLst(UsrSelection usrSelection){

        return usrSelection.getTckrLst();
    }

    /**
     * Extracts the name of the strategy from the userSelection object and creates the appropriate strategy object based on input
     * @param usrSelection
     * @return appropriate Strategy object
     */
    private Strategy retrieveStrategy(UsrSelection usrSelection){
        StrategyFactory factory = new StrategyFactory();
        return factory.create(usrSelection.getStrategy());
    }
}
