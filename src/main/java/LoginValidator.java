/**
 * @author Satvir Uppal
 * CS2212 - Intro to Software Engineering
 * @purpose This class is the LoginValidator, it implements a proxy design pattern such that it creates a proxy object
 * of the Validator interface and utilizes this to check the login
 * this is a part of the client code
 */
import java.io.IOException;
import java.util.Hashtable;

public class LoginValidator  {

    /**
     * Validate method that takes in 2 strings; username and password
     * @param user String of the client's username
     * @param pass String of the client's password
     * @return the boolean generated from the proxy object's validation method
     * @throws IOException
     */
    public static Boolean validate(String user, String pass) throws IOException {

        Validator valid = new ProxyValidator();
        try {
            return valid.validation(user, pass);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}