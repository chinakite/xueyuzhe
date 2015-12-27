/**
 * 
 */
package com.xueyuzhe.upload;

import com.ideamoment.ideadp.exception.IdeaBaseException;


/**
 * @author Chinakite
 *
 */
public class UploadException extends IdeaBaseException {

    private static final long serialVersionUID = -8936870627520065255L;

    public UploadException(String code, String message) {
        super(code, message);
    }

    public UploadException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
