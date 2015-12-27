/**
 * 
 */
package com.xueyuzhe.category.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ideamoment.ideajdbc.IdeaJdbc;
import com.xueyuzhe.model.Category;
import com.xueyuzhe.model.PublishStatus;

/**
 * @author Chinakite
 *
 */
@Component
public class CategoryDao {
    public List<Category> listPublishedCategory() {
        List<Category> result = IdeaJdbc.query("select * from T_CATEGORY where C_STATUS = :status")
                                        .setParameter("status", PublishStatus.PUBLISHED)
                                        .listTo(Category.class);
    
        return result;
    }
    
    public List<Category> listAllCategory() {
        List<Category> result = IdeaJdbc.query("select * from T_CATEGORY")
                                        .listTo(Category.class);
    
        return result;
    }
    
    public List<Category> queryCategory(String keyword) {
        List<Category> result = IdeaJdbc.query("select * from T_CATEGORY where C_NAME like :name")
                                        .setParameter("name", "%" + keyword + "%")
                                        .listTo(Category.class);
    
        return result;
    }
}
