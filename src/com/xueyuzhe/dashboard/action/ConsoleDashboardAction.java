/**
 * 
 */
package com.xueyuzhe.dashboard.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Chinakite
 *
 */
@Controller
public class ConsoleDashboardAction {
    @RequestMapping(value="/console/dashboard", method=RequestMethod.GET)
    public ModelAndView dashboardPage() {
        return new ModelAndView("/WEB-INF/jsp/dashboard.jsp");
    }
}
