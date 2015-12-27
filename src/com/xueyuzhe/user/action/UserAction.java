/**
 * 
 */
package com.xueyuzhe.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideamoment.ideadp.restful.json.JsonData;
import com.ideamoment.ideadp.usercontext.UserContext;
import com.ideamoment.ideajdbc.action.Page;
import com.xueyuzhe.model.User;
import com.xueyuzhe.user.service.UserService;

/**
 * @author Chinakite
 *
 */
@Controller
public class UserAction {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/wx/profile", method=RequestMethod.GET)
    public ModelAndView profile(String tip) {
        return new ModelAndView("/WEB-INF/jsp/wx/wx_profile.jsp");
    }
    
    @RequestMapping(value="/wx/user", method=RequestMethod.GET)
    public JsonData loadUserInfo() {
        User sessUser = (User)UserContext.getCurrentContext().getRequest().getSession().getAttribute(UserContext.SESSION_USER);
        String userId = sessUser.getId();
        User user = userService.findUser(userId);
        return JsonData.success(user);
    }
    
    @RequestMapping(value="/wx/updateUser", method=RequestMethod.POST)
    public JsonData updateUserInfo(String name, String email, String mobile, String age, String category) {
        User sessUser = (User)UserContext.getCurrentContext().getRequest().getSession().getAttribute(UserContext.SESSION_USER);
        String userId = sessUser.getId();
        User user = userService.updateUser(userId, name, email, mobile, age, new String[]{category});
        return JsonData.success(user);
    }
    
    @RequestMapping(value="/console/up", method=RequestMethod.GET)
    public ModelAndView userPage() {
        return new ModelAndView("/WEB-INF/jsp/user/user.jsp");
    }
    
    @RequestMapping(value="/console/users", method=RequestMethod.GET)
    public JsonData books(int curPage, int pageSize, String keyword) {
        Page<User> users = userService.pageAllUsers(curPage, 10, keyword);
        return JsonData.success(users);
    }
    
    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }
    
    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
}
