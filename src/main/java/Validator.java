/**
 * @author Satvir Uppal
 * CS2212 - Intro to Software Engineering
 * @purpose Validator interface that is used during the proxy implementation
 * this interface is used by the ProxyValidator and RValidator
 */

import java.io.IOException;

public interface Validator {
    /**
     * Validation Class in the interface
     * @param user String of the username of the client
     * @param pass String of the password of the client
     * @return boolean if the credentials are valid
     * @throws IOException
     */
     Boolean validation(String user, String pass) throws IOException;
}
