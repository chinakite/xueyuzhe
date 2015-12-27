/**
 * 
 */
package com.xueyuzhe.book.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideamoment.ideadp.restful.json.JsonData;
import com.ideamoment.ideajdbc.action.Page;
import com.xueyuzhe.book.service.BookService;
import com.xueyuzhe.category.service.CategoryService;
import com.xueyuzhe.model.Book;
import com.xueyuzhe.model.Category;

/**
 * @author Chinakite
 *
 */
@Controller
public class BookAction {
    @Autowired
    private BookService bookService;
    
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/console/bp", method=RequestMethod.GET)
    public ModelAndView bookPage(String categoryId) {
        HashMap<String, String> model = new HashMap<String, String>();
        model.put("categoryId", categoryId);
        return new ModelAndView("/WEB-INF/jsp/book/book.jsp", model);
    }
    
    @RequestMapping(value="/wx/category/{categoryId}/bp", method=RequestMethod.GET)
    public ModelAndView wxBookPage(@PathVariable String categoryId) {
        HashMap<String, String> model = new HashMap<String, String>();
        model.put("categoryId", categoryId);
        
        Category category = categoryService.findCategory(categoryId);
        model.put("categoryName", category.getName());
        
        return new ModelAndView("/WEB-INF/jsp/wx/wx_book.jsp", model);
    }
    
    @RequestMapping(value="/wx/category/{categoryId}/books", method=RequestMethod.GET)
    public JsonData wxBooks(@PathVariable String categoryId) {
        List<Book> books = bookService.listPublishedBooks(categoryId);
        return JsonData.success(books);
    }
    
    @RequestMapping(value="/wx/searchBook", method=RequestMethod.GET)
    public ModelAndView searchBook(String key) {
        List<Book> books = bookService.queryPublishedBook(key);
        
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("books", books);
        
        return new ModelAndView("/WEB-INF/jsp/wx/wx_searchResult.jsp", model);
    }
    
    @RequestMapping(value="/console/books", method=RequestMethod.GET)
    public JsonData books(int curPage, int pageSize, String categoryId, String keyword) {
        Page<Book> books = bookService.queryAllBooks(curPage, 10, categoryId, keyword);
        return JsonData.success(books);
    }
    
    @RequestMapping(value="/console/book/{id}", method=RequestMethod.GET)
    public JsonData findCategory(@PathVariable String id) {
        Book book = bookService.findBook(id);
        return JsonData.success(book);
    }
    
    @RequestMapping(value="/console/newBook", method=RequestMethod.POST)
    public JsonData newBook(String name, String desc, String logoUrl, String categoryId) {
        bookService.addBook(name, desc, logoUrl, categoryId);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/editBook", method=RequestMethod.POST)
    public JsonData editBook(String id, String name, String desc, String logoUrl) {
        bookService.updateBook(id, name, desc, logoUrl);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/disBook", method=RequestMethod.POST)
    public JsonData disableBook(String id) {
        bookService.disableBook(id);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/enBook", method=RequestMethod.POST)
    public JsonData enableBook(String id) {
        bookService.enableBook(id);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/delBook", method=RequestMethod.POST)
    public JsonData deleteBook(String id) {
        bookService.deleteBook(id);
        return JsonData.SUCCESS;
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
