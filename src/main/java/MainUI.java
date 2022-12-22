/**
 * @author Samuel Near and Satvir Uppal
 * CS2212 - Intro to Software Engineering
 * @purpose this class is the UI housing of the MainUI that will allow the client to interact with the back end of the
 * Software System. This class, much like the LoginUI will take in user-inputs from either button prompts or text inputs
 * the majority of this system is taken from the sample code so the commenting is light.
 *
 */

//import libraries that will be used

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MainUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static MainUI instance;
    private JPanel stats, chartPanel, tablePanel;

    // Should be a reference to a separate object in actual implementation
    private List<String> selectedList;

    private JTextArea selectedTickerList;
    //	private JTextArea tickerList;
    private JTextArea tickerText;
    private JTextArea BrokerText;
    private JComboBox<String> strategyList;
    private Map<String, List<String>> brokersTickers = new HashMap<>();
    private Map<String, String> brokersStrategies = new HashMap<>();
    private List<String> selectedTickers = new ArrayList<>();
    private String selectedStrategy = "";
    private DefaultTableModel dtm;
    private JTable table;

    private ArrayList<String> brokerNames = new ArrayList();

    public static MainUI getInstance() {
        if (instance == null)
            instance = new MainUI();
        return instance;
    }

    /**
     * method mainUI is launched when a new instance is created.
     * creates the main user interface.
     */
    MainUI() {

        // Set window title
        super("<CoinTrader>");

        // Set top bar
        JPanel north = new JPanel();

        JButton trade = new JButton("Perform Trade");
        trade.setActionCommand("refresh");
        trade.addActionListener(this);

        JPanel south = new JPanel();

        south.add(trade);

        dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
        table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
                TitledBorder.CENTER, TitledBorder.TOP));
        Vector<String> strategyNames = new Vector<String>();
        strategyNames.add("ValueInvesting : Litecoin, Ethereum");
        strategyNames.add("GammaSqueeze : Ethereum, Bitcoin");
        strategyNames.add("MomentumInvesting : Ethereum, Litecoin");
        strategyNames.add("FactorInvesting : Bitcoin, Dogecoin");
        TableColumn strategyColumn = table.getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox(strategyNames);
        strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
        JButton addRow = new JButton("Add Row");
        JButton remRow = new JButton("Remove Row");
        addRow.setActionCommand("addTableRow");
        addRow.addActionListener(this);
        remRow.setActionCommand("remTableRow");
        remRow.addActionListener(this);

        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);

        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
        east.add(scrollPane);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(addRow);
        buttons.add(remRow);
        east.add(buttons);

        // Set charts region
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(1250, 650));
        stats = new JPanel();
        stats.setLayout(new GridLayout(2, 2));
        west.add(stats);

        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(east, BorderLayout.EAST);
        getContentPane().add(west, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);
    }

    /**
     * updates the states with the passed component.
     * @param component
     */
    public void updateStats(JComponent component) {
        stats.add(component);
        stats.revalidate();
    }

    /**
     * LaunchMainUI gets called from main system when log in is valid
     */
    public static void LaunchMainUI() {
        JFrame frame = MainUI.getInstance();
        frame.setSize(900, 600);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * display error if a broker's coins has an error in it.
     * @param b broker that has the coin Error
     */
    public static void catchCoinError(Broker b){
        JOptionPane.showMessageDialog(null, "Unexpected Error\n"+"Broker: "+b.getName()+" Does not have the coins required for it's strategy");
    }

    /**
     * display error if a broker's coins has an error
     * @param rowNum used to display which broker had duplicated broker
     */
    public static void catchDoubleBroker(int rowNum){
        JOptionPane.showMessageDialog(null, "Unexpected Error\n A broker name has been inputted more than once\n Row "+rowNum+" was not added");
    }

    @Override
    /**
     * method takes action based on what is inputted from the user.
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //Trade button is pressed
        if ("refresh".equals(command)) {
            //rest all the information
            MainSystem.clearBrokerList();
            brokerNames.clear();

            for (int count = 0; count < dtm.getRowCount(); count++){
                Object traderObject = dtm.getValueAt(count, 0);
                if (traderObject == null) {
                    JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
                    return;
                }
                String traderName = traderObject.toString();
                Object coinObject = dtm.getValueAt(count, 1);
                if (coinObject == null) {
                    JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
                    return;
                }
                String coinString = coinObject.toString().toLowerCase();
                String[] coinNames = coinString.split(",");
                Object strategyObject = dtm.getValueAt(count, 2);
                if (strategyObject == null) {
                    JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
                    return;
                }
                String strategyName = strategyObject.toString();
                String[] strategyNameList = strategyName.split(" ");
                strategyName = strategyNameList[0].trim();

                //trim each string in the list of coins
                for(int j=0; j<coinNames.length; j++){
                    coinNames[j] = coinNames[j].toLowerCase().trim();
                }

                //check to see if the broker has already been added
                if(!brokerNames.contains(traderName)){
                    //for each line in table make a usrSelectionObject
                    MainSystem.addUserSelection(traderName,coinNames,strategyName); // connects the user selections to the back end
                    brokerNames.add(traderName);
                }
                else{
                    catchDoubleBroker(count+1);
                }
            }
            stats.removeAll();
            MainSystem.invokeStrategies(); // Attaches the visualisers

        } else if ("addTableRow".equals(command)) {
            dtm.addRow(new String[3]);
        } else if ("remTableRow".equals(command)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1)
                dtm.removeRow(selectedRow);
        }
    }
}