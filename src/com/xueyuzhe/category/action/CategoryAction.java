/**
 * 
 */
package com.xueyuzhe.category.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ideamoment.ideadp.restful.json.JsonData;
import com.xueyuzhe.category.service.CategoryService;
import com.xueyuzhe.model.Category;

/**
 * @author Chinakite
 *
 */
@Controller
public class CategoryAction {
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping(value="/console/cgp", method=RequestMethod.GET)
    public ModelAndView categoryPage() {
        return new ModelAndView("/WEB-INF/jsp/category/category.jsp");
    }
    
    @RequestMapping(value="/wx/category", method=RequestMethod.GET)
    public JsonData wxCategory(String keyword) {
        List<Category> categories = categoryService.listPublishedCategory();
        return JsonData.success(categories);
    }
    
    @RequestMapping(value="/console/cg", method=RequestMethod.GET)
    public JsonData category(String keyword) {
        List<Category> categories = categoryService.listAllCategory(keyword);
        return JsonData.success(categories);
    }
    
    @RequestMapping(value="/console/newCategory", method=RequestMethod.POST)
    public JsonData newCategory(String name, String desc, String logoUrl) {
        categoryService.addCategory(name, desc, logoUrl);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/category/{id}", method=RequestMethod.GET)
    public JsonData findCategory(@PathVariable String id) {
        Category category = categoryService.findCategory(id);
        return JsonData.success(category);
    }
    
    @RequestMapping(value="/console/editCategory", method=RequestMethod.POST)
    public JsonData editCategory(String id, String name, String desc, String logoUrl) {
        categoryService.updateCategory(id, name, desc, logoUrl);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/disCategory", method=RequestMethod.POST)
    public JsonData disableCategory(String id) {
        categoryService.disableCategory(id);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/enCategory", method=RequestMethod.POST)
    public JsonData enableCategory(String id) {
        categoryService.enableCategory(id);
        return JsonData.SUCCESS;
    }
    
    @RequestMapping(value="/console/delCategory", method=RequestMethod.POST)
    public JsonData deleteCategory(String id) {
        categoryService.deleteCategory(id);
        return JsonData.SUCCESS;
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
