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
import com.xueyuzhe.model.Admin;
import com.xueyuzhe.user.service.AdminService;

/**
 * @author Chinakite
 *
 */
@Controller
public class ConsoleLoginAcion {
    @Autowired
    private AdminService userService;
    
    @RequestMapping(value="/console/login", method=RequestMethod.GET)
    public ModelAndView loginpage() {
        return new ModelAndView("/login.jsp");
    }
    
    @RequestMapping(value="/console/userlogin", method=RequestMethod.POST)
    public JsonData adminLogin(String account, String password) {
        Admin admin = userService.login(account, password);
        return JsonData.success(admin);
    }

    /**
     * @return the userService
     */
    public AdminService getUserService() {
        return userService;
    }
    
    /**
     * @param userService the userService to set
     */
    public void setUserService(AdminService userService) {
        this.userService = userService;
    }
    
}
