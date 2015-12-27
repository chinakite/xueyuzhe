/**
 * 
 */
package com.xueyuzhe.downlog.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideamoment.ideadp.restful.json.JsonData;
import com.xueyuzhe.downlog.service.DownlogService;
import com.xueyuzhe.model.Book;
import com.xueyuzhe.model.Section;

/**
 * @author Chinakite
 *
 */
@Controller
public class DownlogAction {
    @Autowired
    private DownlogService downlogService;
    
    @RequestMapping(value="/wx/dlp", method=RequestMethod.GET)
    public ModelAndView downlogPage() {
        HashMap<String, String> model = new HashMap<String, String>();
        return new ModelAndView("/WEB-INF/jsp/wx/wx_downlog.jsp", model);
    }
    
    @RequestMapping(value="/wx/dldp/{bookId}", method=RequestMethod.GET)
    public ModelAndView downlogDetailPage(@PathVariable String bookId) {
        HashMap<String, String> model = new HashMap<String, String>();
        model.put("bookId", bookId);
        return new ModelAndView("/WEB-INF/jsp/wx/wx_downlogDetail.jsp", model);
    }
    
    @RequestMapping(value="/wx/dl", method=RequestMethod.GET)
    public JsonData downlog() {
        String userId = "1";
        List<Book> books = downlogService.listDownlogBooks(userId);
        return JsonData.success(books);
    }
    
    @RequestMapping(value="/wx/dlsec/{bookId}", method=RequestMethod.GET)
    public JsonData downlogSections(@PathVariable String bookId) {
        String userId = "1";
        List<Section> books = downlogService.listDownlogSectionsByBook(userId, bookId);
        return JsonData.success(books);
    }

    
    /**
     * @return the downlogService
     */
    public DownlogService getDownlogService() {
        return downlogService;
    }
    
    /**
     * @param downlogService the downlogService to set
     */
    public void setDownlogService(DownlogService downlogService) {
        this.downlogService = downlogService;
    }
    
}
