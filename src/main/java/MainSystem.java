/**
 * @author Samuel Near
 * CS2212 - Intro to Software Engineering
 * @purpose this class provides a main system so the loginUI and mainUI can communicate with
 * backend systems: tradelog, brokerList, Histogram and table viewer.
 */
public class MainSystem {

    static BrokerList brokerList = new BrokerList();
    static TradeLog tradeLog= new TradeLog();

    static HistogramViewer histogram = new HistogramViewer(tradeLog);
    static TableViewer table = new TableViewer(tradeLog);

    /**
     * main method starts the whole system, runs the login ui
     * then attached observers
     */
    public static void main(String[] args) {

        //Display Login UI
        LoginUI.launchLogInUI();
        // attach observers
        attachObservers();
    }

    //If login UI returns false exit system
    //Else start Main UI
    public static void loginCheck(Boolean valid){
        if(valid){
            MainUI.LaunchMainUI();
        }
        else{
            System.exit(0);
        }
    }

    /**
     *
     * @param name name of the broker object
     * @param coins  list of coins the user inputted for the broker
     * @param strategy strategy the user chose for the broker to use
     */
    public static void addUserSelection(String name, String[] coins, String strategy){
        UsrSelection newSelection = new UsrSelection(name,coins,strategy);
        //create broker factory
        BrokerFactory brokerFactory = new BrokerFactory();
        //create a broker using the user selection and the broker factory
        Broker newBroker = brokerFactory.create(newSelection);
        //add broker to list
        brokerList.addBroker(newBroker);
    }

    /**
     * method invokeStrategies occurs after the user presses the trade button
     * takes the list of all the brokers and invokes method trade
     * passing it an empty trade log.
     */
    public static void invokeStrategies(){
        brokerList.trade(tradeLog);
    }

    /**
     * attach histogram and table.
     */
    public static void attachObservers(){
        tradeLog.attach(histogram);
        tradeLog.attach(table);
    }

    /**
     * method clearBrokerList clears the list of brokers. This method
     * is invoked everytime the trade button is pressed.
     */
    public static void clearBrokerList(){
        brokerList.clearBrokerList();
    }
}
