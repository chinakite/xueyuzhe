/**
 * 
 */
package com.xueyuzhe.home.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Chinakite
 *
 */
@Controller
public class WxHomeAction {
    
    @RequestMapping(value="/wx/index", method=RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/WEB-INF/jsp/wx/wx_index.jsp");
    }
}
