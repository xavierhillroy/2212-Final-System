/**
 * @author Satvir Uppal
 * CS2212 - Intro to Software Engineering
 * @purpose This is the proxyValidator (i.e. the object that contains a limited set of actions so the client is unable to access
 * the actual Validator's validation method
 */
import java.io.IOException;

public class ProxyValidator implements Validator{
    private Validator validator = new RValidator(); // create an object of type RealValidator, this stops the client from seeing back end of the program

    /**
     * Validation method that implements the proxy design pattern so that the client is unable to access the back of the validation
     * @param user
     * @param pass
     * @return return the boolean from the call to the real validator
     * @throws IOException
     */
    @Override
    public Boolean validation(String user, String pass) throws IOException {
        return validator.validation(user, pass);
    }
}
