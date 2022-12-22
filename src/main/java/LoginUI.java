/**
 * @author Satvir Uppal
 * CS2212 - Intro to Software Engineering
 * @purpose this class is the LoginUI window that initializes and generates the loginUI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginUI extends JFrame implements ActionListener {
    //create and initialize the elements of the UI (i.e. labels, text fields, buttons, etc.)
    private static JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();

    private static JLabel usernameLabel = new JLabel("USERNAME:");
    private static JLabel passwordLabel = new JLabel("PASSWORD:");

    private static JTextField username = new JTextField();
    private static JPasswordField password = new JPasswordField();

    private static JButton loginBtn = new JButton("LOGIN");
    private static JButton resetBtn = new JButton("RESET");

    private static JCheckBox showPass = new JCheckBox("Show Password");

    //object of type LoginUI that will create an instance of the window
    private static LoginUI instance;

    /** GetInstance Method: this method will make the LoginUI the current instance.
     * This is ane example of Singleton design patter
     * @return the instance of the current window
     */
    public static LoginUI getInstance() {
        if (instance == null)
            instance = new LoginUI();
        return instance;
    }

    /**
     * SetFrame method that initializes the frame and panel of the UI
     * This method sets the dimensions and location of the window
     */
    private static void setFrame(){
        panel.setLayout(null);

        frame.setTitle("<CoinTrader> Login");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(450, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * SetElements method that sets the locations and size of the elements of the UI such as the
     * username & password labels, text-enterable-fields, and buttons (login, show password, and reset)
     */
    private static void setElements(){
        usernameLabel.setBounds(50, 8, 200, 20);
        username.setBounds(50, 25, 193, 28);
        passwordLabel.setBounds(50, 58, 200, 20);
        password.setBounds(50, 75, 193, 28);
        loginBtn.setBounds(50, 120, 90, 25);
        resetBtn.setBounds(152, 120, 90, 25);
        showPass.setBounds(250, 80, 150, 25);
    }

    /**
     * addElements method that adds the elements on to the panel such that
     *they can be interacted with and utilized
     */
    private static void addElements(){
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(loginBtn);
        panel.add(resetBtn);
        panel.add(showPass);
        setAction();
    }

    /**
     * setAction that will set the buttons (login, show password, and reset)
     * this makes the buttons 'observers' so they are awaiting an action to commence their methods
     */
    private static void setAction(){
        showPass.addActionListener((ActionListener) new LoginUI());
        loginBtn.addActionListener((ActionListener) new LoginUI());
        resetBtn.addActionListener((ActionListener) new LoginUI());
    }

    /**
     * LaunchLoginUI acts as a main method that is called by MainSystem.java to open and commence the program
     * this method calls the helper methods above to initialize and create the UI
     */
    public static void launchLogInUI(){
        setFrame();
        setElements();
        addElements();
        getInstance();
    }

    /**
     * ActionPerformed that is called only when an action such as a button check or click happens
     * this is similar to an observer design pattern as it only commences a method when an action is performed
     * @param e that is passed only when a button that is included on the LoginUI is pressed/ checked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // loginButton action class that will commence only when the login button is clicked
        if(e.getSource() == loginBtn){
            // normalize the username field (i.e. make it lowercase) and dump it into a string
            String User = username.getText().toLowerCase();
            // dump input password into a string
            String Pass = String.valueOf(password.getPassword());
            // if the input fields are empty, prompt the user to enter something in before proceeding
            if(User.isEmpty() && Pass.isEmpty())
                JOptionPane.showMessageDialog(this, "Enter your Username and Password");
            else {
                try {
                    // call the validator method to validate the login
                    if (LoginValidator.validate(User, Pass)) {
                        // if the login was valid, close the current UI and call the loginCheck method in MainSystem with true
                        // this loginChek method will begin the MainUI so trading can commence
                        frame.dispose();
                        MainSystem.loginCheck(true);
                    } else {
                        //if the login was a fail, notify the user with a JOptionPane and pass false to the MainSystem.loginCheck method
                        //When false is passed, the program will terminate, as per the instructions
                        JOptionPane.showMessageDialog(this, "LOGIN FAILED \nProgram will now terminate");
                        MainSystem.loginCheck(false);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // reset button that will clear the fields if the user so chooses to
        if(e.getSource() == resetBtn){
            username.setText("");
            password.setText("");
        }
        // show password checkbox that will toggle btwn showing the password characters as ***** or as alphanumeric characters
        if(e.getSource() == showPass){
            if(!showPass.isSelected())
                password.setEchoChar('*');
            else
                password.setEchoChar((char) 0);
        }
    }
}