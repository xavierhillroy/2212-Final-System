/**
 * @author Xavier Hill Roy
 * CS2212 - Intro to Software Engineering
 * @purpose User creates this by entering strings into the ui this object gets past to broker factory which then creates a broker object based on the inputted strings
 *      These are the strings entered by user when choosing what he wants to create-
 *     This is then used and converted to strategies and broker using the appropriate factories
 */
public class UsrSelection {

    private String name;
    private String[] tckrLst;
    private String strategy;

    /**
     *Constructor for user election object
     * @param name
     * @param tckrLst
     * @param strategy
     */
    UsrSelection(String name, String[] tckrLst, String strategy){
        setName(name);
        setTckrLst(tckrLst);
        setStrategy(strategy);
    }


    /**
     * setter method for name of user selection
     * @param n
     */
    public void setName(String n){
        name =n;
    }

    /**
     * setter method for the coin names
     * @param tLst
     */
    public void setTckrLst(String[] tLst){
        tckrLst = tLst;
    }

    /**
     * setter methods for strategy
     * @param s
     */
    public void setStrategy(String s){
        strategy = s;
    }

    /**
     *getter method for name
     * @return string name
     */
    public String getName(){
        return name;
    }

    /**
     * getter method for tickerList
     * @return array of string containing the names of the coins
     */
    public String[] getTckrLst(){
        return tckrLst;
    }

    /**
     * getter method for the strategy
     * @return string with name of strategy
     */
    public String getStrategy() {
        return strategy;
    }
}
