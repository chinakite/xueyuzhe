/**
 * 
 */
package com.xueyuzhe.wx.filter;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ideamoment.ideadp.usercontext.UserContext;
import com.ideamoment.ideajdbc.IdeaJdbc;
import com.ideamoment.wx.user.model.WxUser;
import com.ideamoment.wx.webauth.servlet.WxOAuthEventListener;
import com.xueyuzhe.model.Constants;
import com.xueyuzhe.model.GenderType;
import com.xueyuzhe.model.User;
import com.xueyuzhe.model.UserAccessLog;


/**
 * @author Chinakite
 *
 */
public class OauthEventListener implements WxOAuthEventListener{

    @Override
    public void afterGetUserInfo(ServletRequest req,
                                 ServletResponse resp,
                                 WxUser user) {
        String openId = user.getOpenId();
        
        IdeaJdbc.beginTransaction();
        User dbuser = (User)IdeaJdbc.query("select * from T_USER where C_WX_OPENID = :openId")
                                    .setParameter("openId", user.getOpenId())
                                    .uniqueTo(User.class);
        
        if(dbuser != null) {
            dbuser.setLogoUrl(user.getHeadImgUrl());
            dbuser.setName(user.getNickName());
            if(user.getSex().equals("1")) {
                dbuser.setGender(GenderType.MALE);
            }else if(user.getSex().equals("2")){
                dbuser.setGender(GenderType.FEMALE);
            }else{
                dbuser.setGender(GenderType.SECRET);
            }
            dbuser.setCity(user.getCity());
            dbuser.setProvince(user.getProvince());
            dbuser.setCountry(user.getCountry());
            dbuser.setModifyTime(new Date());
            dbuser.setModifier(Constants.SYSTEM_ID);
            
            IdeaJdbc.update(dbuser);
            
            UserAccessLog accLog = new UserAccessLog();
            accLog.setAccessTime(new Date());
            accLog.setUserId(dbuser.getId());
            
            IdeaJdbc.save(accLog);
            
            ((HttpServletRequest)req).getSession().setAttribute(UserContext.SESSION_USER, dbuser);
        }else{
            User newUser = new User();
            newUser.setLogoUrl(user.getHeadImgUrl());
            newUser.setName(user.getNickName());
            newUser.setWxOpenId(user.getOpenId());
            newUser.setWxUnionId(user.getUnionId());
            if(user.getSex().equals("1")) {
                newUser.setGender(GenderType.MALE);
            }else if(user.getSex().equals("2")){
                newUser.setGender(GenderType.FEMALE);
            }else{
                newUser.setGender(GenderType.SECRET);
            }
            newUser.setCity(user.getCity());
            newUser.setProvince(user.getProvince());
            newUser.setCountry(user.getCountry());
            newUser.setCreateTime(new Date());
            newUser.setCreator(Constants.SYSTEM_ID);
            
            IdeaJdbc.save(newUser);
            
            UserAccessLog accLog = new UserAccessLog();
            accLog.setAccessTime(new Date());
            accLog.setUserId(newUser.getId());
            
            IdeaJdbc.save(accLog);
            
            ((HttpServletRequest)req).getSession().setAttribute(UserContext.SESSION_USER, newUser);
        }
        IdeaJdbc.commitTransaction();
        IdeaJdbc.endTransaction();
        
        ((HttpServletRequest)req).getSession().setAttribute("WX_OPEN_ID", openId);
    }

    @Override
    public void afterGotOpenId(ServletRequest req,
                               ServletResponse resp,
                               String openId,
                               String accessToken) {
    }

}
