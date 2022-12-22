/**
 * @author Satvir Uppal
 * CS2212 - Intro to Software Engineering
 * @purpose Helper class that will take in a txt file containing user data in the form of username:passsword
 * this class when then read the data into a
 */
import java.io.*;
import java.util.Hashtable;

public class UserDatabase {

    public static Hashtable<String, String> userData; //create a new hashtable of type <String, String> this will house the data

    /**
     * setUserData method that will take the above HashTable and populate it with a given text file such that the text file as the first line
     * username:password
     * @throws IOException in the event that the text file is not able to be read/ does not exist
     */
    private static void setUserData() throws IOException {
        try {
            userData = new Hashtable<>();
            File file = new File("userandpass.txt");
            FileReader reader = new FileReader(file);
            BufferedReader buffRead = new BufferedReader(reader);

            String readLine = buffRead.readLine(); // neglect first line
            while((readLine = buffRead.readLine()) != null){
                String[] split = readLine.split(":");
                //insert with username as key and password as data so Hashtable is structured as: {username, password}
                //normalize data in this fashion: remove all spaces (turn " " -> "")
                userData.put(split[0].toLowerCase().replaceAll(" ", ""),
                        split[1].replaceAll(" ", ""));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getDatabase method that will create and return the database, since the creation of the database is private (i.e. cannot be accessed by other classes)
     * this method has to call the create method and return the database.
     * @return return the Database if is not empty, else return null
     * @throws IOException
     */
    public static Hashtable<String, String> getDatabase() throws IOException{
        setUserData(); //call the creation method of the database
        if(!userData.isEmpty())
            return userData; //if the hashtable is not empty, return the data
        else
            return null;
    }
}