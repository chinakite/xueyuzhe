/**
 * 
 */
package com.xueyuzhe.category.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideamoment.ideadp.exception.IdeaDataException;
import com.ideamoment.ideadp.exception.IdeaDataExceptionCode;
import com.ideamoment.ideajdbc.IdeaJdbc;
import com.ideamoment.ideajdbc.spring.IdeaJdbcTx;
import com.xueyuzhe.category.dao.CategoryDao;
import com.xueyuzhe.model.Category;
import com.xueyuzhe.model.PublishStatus;

/**
 * @author Chinakite
 *
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    
    @IdeaJdbcTx
    public List<Category> listPublishedCategory() {
        List<Category> categories = categoryDao.listPublishedCategory();
        return categories;
    }
    
    @IdeaJdbcTx
    public List<Category> listAllCategory(String keyword) {
        if(keyword == null) {
            List<Category> categories = categoryDao.listAllCategory();
            return categories;
        }else{
            List<Category> categories = categoryDao.queryCategory(keyword);
            return categories;
        }
    }
    
    @IdeaJdbcTx
    public boolean addCategory(String name, String desc, String logoUrl) {
        
        Category category = new Category();
        category.setName(name);
        category.setDesc(desc);
        category.setLogoUrl(logoUrl);
        category.setStatus(PublishStatus.DRAFT);
        category.setCreator("1");
        category.setCreateTime(new Date());
        IdeaJdbc.save(category);
        
        return true;
    }
    
    @IdeaJdbcTx
    public boolean updateCategory(String id, String name, String desc, String logoUrl) {
        Category category = IdeaJdbc.find(Category.class, id);
        
        if(category == null) {
            throw new IdeaDataException(IdeaDataExceptionCode.DATA_NOU_FOUND, String.format("Category[%s] is not found.", id));
        }else{
            category.setName(name);
            category.setDesc(desc);
            category.setLogoUrl(logoUrl);
            category.setModifier("1");
            category.setModifyTime(new Date());
            IdeaJdbc.update(category);
        }
        
        return true;
    }
    
    @IdeaJdbcTx
    public Category findCategory(String id) {
        return IdeaJdbc.find(Category.class, id);
    }
    
    @IdeaJdbcTx
    public int disableCategory(String id) {
        return IdeaJdbc.update(Category.class, id)
                        .setProperty("status", PublishStatus.DROPED)
                        .execute();
    }
    
    @IdeaJdbcTx
    public int enableCategory(String id) {
        return IdeaJdbc.update(Category.class, id)
                        .setProperty("status", PublishStatus.PUBLISHED)
                        .execute();
    }

    @IdeaJdbcTx
    public void deleteCategory(String id) {
        IdeaJdbc.delete(Category.class, id);
    }

    /**
     * @return the categoryDao
     */
    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    /**
     * @param categoryDao the categoryDao to set
     */
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
}
