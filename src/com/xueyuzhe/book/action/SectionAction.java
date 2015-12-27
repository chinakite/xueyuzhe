/**
 * 
 */
package com.xueyuzhe.book.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideamoment.ideadp.restful.json.JsonData;
import com.xueyuzhe.book.DownloadException;
import com.xueyuzhe.book.DownloadExceptionCode;
import com.xueyuzhe.book.service.BookService;
import com.xueyuzhe.book.service.SectionService;
import com.xueyuzhe.book.service.SectionService.DownloadFile;
import com.xueyuzhe.category.service.CategoryService;
import com.xueyuzhe.model.Book;
import com.xueyuzhe.model.Section;

/**
 * @author Chinakite
 *
 */
@Controller
public class SectionAction {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private BookService bookService;
    
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping(value="/console/book/{bookId}/secp", method=RequestMethod.GET)
    public ModelAndView sectionPage(@PathVariable String bookId) {
        Book book = bookService.findBook(bookId);
        
        HashMap<String, String> model = new HashMap<String, String>();
        model.put("bookId", bookId);
        model.put("bookName", book.getName());
        model.put("bookDesc", book.getDesc());
        return new ModelAndView("/WEB-INF/jsp/book/section.jsp", model);
    }
    
    @RequestMapping(value="/wx/book/{bookId}/secp", method=RequestMethod.GET)
    public ModelAndView wxSectionPage(@PathVariable String bookId) {
        Book book = bookService.findBook(bookId);
        
        HashMap<String, String> model = new HashMap<String, String>();
        model.put("bookId", bookId);
        model.put("bookName", book.getName());
        model.put("bookDesc", book.getDesc());
        return new ModelAndView("/WEB-INF/jsp/wx/wx_section.jsp", model);
    }
    
    @RequestMapping(value="/console/book/{bookId}/sec", method=RequestMethod.GET)
    public JsonData listSections(@PathVariable String bookId) {
        List<Section> secs = sectionService.listSections(bookId);
        return JsonData.success(secs);
    }
    
    @RequestMapping(value="/console/book/{bookId}/addsec", method=RequestMethod.POST)
    public JsonData addSection(@PathVariable String bookId, String name, String desc, String audioUrl) {
        sectionService.addSection(bookId, name, desc, audioUrl);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/section/{id}", method=RequestMethod.GET)
    public JsonData findCategory(@PathVariable String id) {
        Section section = sectionService.findSection(id);
        return JsonData.success(section);
    }
    
    @RequestMapping(value="/console/editsec", method=RequestMethod.POST)
    public JsonData editSection(String id, String name, String desc, String logoUrl) {
        sectionService.updateSection(id, name, desc, logoUrl);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/dissec", method=RequestMethod.POST)
    public JsonData disableSection(String id) {
        sectionService.disableSection(id);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/ensec", method=RequestMethod.POST)
    public JsonData enableSection(String id) {
        sectionService.enableSection(id);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/delsec", method=RequestMethod.POST)
    public JsonData deleteSection(String id) {
        sectionService.deleteSection(id);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/wx/downloadAudio", method=RequestMethod.GET)
    public void downloadAudio(HttpServletRequest req, HttpServletResponse resp, String id) {
//        UserContext userContext = UserContext.getCurrentContext();
//        userContext.get
        
        String userId = "1";
        
        DownloadFile file = sectionService.downloadAudioes(id, userId);
        
        OutputStream os = null;  
        try {  
            os = resp.getOutputStream();
            resp.reset();
            
            String fileName = URLEncoder.encode(file.getBookName() + " " + file.getSectionName(), "UTF-8");
            fileName = URLDecoder.decode(fileName, "ISO8859_1");
            
            resp.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".mp3");  
            resp.setContentType("application/octet-stream; charset=utf-8");  
            os.write(FileUtils.readFileToByteArray(file.getFile()));  
            os.flush();  
        } catch (IOException e) {
            e.printStackTrace();
            throw new DownloadException(DownloadExceptionCode.IO_ERROR, e.getMessage(), e);
        } finally {  
            if (os != null) {  
                try {
                    os.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                    throw new DownloadException(DownloadExceptionCode.IO_ERROR, ie.getMessage(), ie);
                }  
            }  
        }  
    }
    
    /**
     * @return the sectionService
     */
    public SectionService getSectionService() {
        return sectionService;
    }

    /**
     * @param sectionService the sectionService to set
     */
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     * @return the bookService
     */
    public BookService getBookService() {
        return bookService;
    }

    /**
     * @param bookService the bookService to set
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * @return the categoryService
     */
    public CategoryService getCategoryService() {
        return categoryService;
    }
    
    /**
     * @param categoryService the categoryService to set
     */
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
}
