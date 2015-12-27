/**
 * 
 */
package com.xueyuzhe.wx.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ideamoment.wx.webauth.filter.WxAccessableChecker;


/**
 * @author Chinakite
 *
 */
public class OauthSessionChecker implements WxAccessableChecker {

    /* (non-Javadoc)
     * @see com.ideamoment.wx.webauth.filter.WxAccessableChecker#onCheckAccessable(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    public boolean onCheckAccessable(ServletRequest req, ServletResponse resp) {
        HttpServletRequest request = (HttpServletRequest)req;
        Object openId = request.getSession().getAttribute("WX_OPEN_ID");
        if(openId == null) {
            return false;
        }else{
            return true;
        }
    }

}
