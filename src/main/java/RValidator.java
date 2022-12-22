/**
 * @author Satvir Uppal
 * CS2212 - Intro to Software Engineering
 * @purpose the realValidator that implements the proxy design pattern
 * this class is the realValidator, the one that the client code is unable to access (hence the proxy design pattern)
 */
import java.io.IOException;
import java.util.Hashtable;

public class RValidator implements Validator{

    /**
     * Validation class that is the **ACTUAL** validation class, this one is the class that does the validating of the user and password strings
     * @param user
     * @param pass
     * @return the boolean if the password matches the data of the key (username)
     * @throws IOException
     */
    @Override
    public Boolean validation(String user, String pass) throws IOException {
        try {
            Hashtable<String, String> userData = UserDatabase.getDatabase(); //create a new Hashtable and fill it using the UserDatabase class
            if(userData != null && !userData.isEmpty()){ //check if the hashtable is either null or not empty
                if(userData.get(user) != null){ //if there is a password
                    return userData.get(user).equals(pass); //return if the password matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
