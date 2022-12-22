/**
 * @author Samuel Near
 * CS2212 - Intro to Software Engineering
 * @purpose TableViewer class that will be used to create and utilize the MainUI's table after a trade has happened
 * The table on the main UI will contain information on the nature of the trade in addition to any relevant data
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class TableViewer implements TradingObservers {

    /*Table viewer = Observer 2 (observer design pattern)*/

    /**
     * Update method that calls the createTableOutput (i.e. creates a new table)
     * called from an external class to commence any methods and algorithms in this class
     */
    @Override
    public void update() {
        createTableOutput();
    }
    private TradeLog tradeLog; // create a new instance variable of type TradeLog

    /**
     * initializer class that initializes the instance varaible
     * @param tradeLog take in a tradeLog (i.e. the previous tradeLog and use it in this context)
     */
    TableViewer(TradeLog tradeLog){
        this.tradeLog = tradeLog;
    }

    /**
     * CreateTableOutput creates the section of the UI for the Table that will be updated each time a trade has been made
     */
    private void createTableOutput() {
        //System.out.println("In method createTableOutput");
        // Dummy dates for demo purposes. These should come from selection menu
        Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};

        Object[][] data = toArray(); // SHOULD HAVE ACTUAL DATA

        JTable table = new JTable(data, columnNames);
        //table.setPreferredSize(new Dimension(600, 300));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);

        MainUI.getInstance().updateStats(scrollPane);
    }

    /**
     * toArray creates and adds elements into a double array
     * This double array will contain all relevant data that is required to communicate the nature of the trade
     * if a trade has been unsuccessful, due to user error, then the fields will be populated for null
     * @return return a doubleArray of type Object (generic)
     */
    private Object[][] toArray(){
        // Create a 2d array to store data in size of rows is num of trades, and each trade has 7 attributes ie the columns
        Object[][] realData = new Object[tradeLog.getTradeLog().size()][7];
        ArrayList<StrategyResult> trades = tradeLog.getTradeLog(); // Container holding actual trades
        for (int row =0; row<trades.size(); row++){ // cycling through trades
            for (int column = 0; column < 7; column++){
                switch (column){
                    case 0: {
                        realData[row][column] = trades.get(row).getBroker().getName(); // puts name into first col of each row
                        break;
                    }
                    case 1: { //insert the strategy name into the table
                        realData[row][column] = trades.get(row).getStrategy().getName();
                        break;
                    }
                    case 2:{ //insert the coin used in the
                        realData[row][column]=trades.get(row).getCoin();
                        break;
                    }
                    case 3:
                    {
                        realData[row][column]=trades.get(row).getAction();
                        break;
                    }
                    case 4:{ //if the trade was a fail, then enter null
                        if(trades.get(row).getQuantity() == -1){
                            realData[row][column]= "null";
                        }else{ //insert the quantity of the trade into the table
                            realData[row][column]=trades.get(row).getQuantity();
                        }
                        break;
                    }
                    case 5:{ //if the trade was a fail, enter null
                        if(trades.get(row).getPrice() == -1.0){
                            realData[row][column]= "null";
                        }else { //insert the price of the trade into the table
                            realData[row][column] = trades.get(row).getPrice();
                        }
                        break;
                    }
                    case 6:{ //insert date into the table
                        realData[row][column]=trades.get(row).getDate();
                        break;
                    }

                }// ENd of switch
            }// end of column for loop
        }// end of row loop
    return realData;
    }


}
