/**
 * 
 */
package com.xueyuzhe.user;

import com.ideamoment.ideadp.exception.IdeaBaseException;


/**
 * @author Chinakite
 *
 */
public class LoginException extends IdeaBaseException {

    public LoginException(String code, String message) {
        super(code, message);
    }
    
}
