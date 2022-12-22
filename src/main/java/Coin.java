/**
 * @author Michael Schmidt
 * CS2212 - Intro to Software Engineering
 * @purpose provide an efficient storage method for information pertaining to a coin
 */
public class Coin {

    private String ticker; //ticker rep of coin
    private String fullName; //full name of coin
    private double price; //current price of coin

    /**
     * constructor for a coin
     * @param ticker ticker of coin
     * @param fullName full name of coin
     * @param price current price of coin
     */
    public Coin(String ticker, String fullName, double price){
        this.fullName = fullName;
        this.ticker = ticker;
        this.price = price;
    }

    /**
     * returns a ticker representation of the coin
     * @return coin ticker symbol
     */
    public String getTicker(){
        return ticker;
    }

    /**
     * getter method for full name of coin
     * @return full name of coin
     */
    public String getFullName(){
        return fullName;
    }

    /**
     * getter method for price of coin
     * @return price of coin
     */
    public double getPrice(){
        return price;
    }

    /**
     * setter method for price of coin
     * @param newPrice  new price to set
     */
    public void setPrice(double newPrice){
        price = newPrice;
    }

    /**
     * to string method for coin
     * @return string representation of the coin
     */
    public String toString(){
        return ("Full Name: " + fullName + "\nTicker: " + ticker + "\nPrice: " + price + "\n");
    }

}
