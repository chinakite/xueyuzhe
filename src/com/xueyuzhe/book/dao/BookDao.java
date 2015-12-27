/**
 * 
 */
package com.xueyuzhe.book.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ideamoment.ideajdbc.IdeaJdbc;
import com.ideamoment.ideajdbc.action.Page;
import com.ideamoment.ideajdbc.action.Query;
import com.xueyuzhe.model.Book;

/**
 * @author Chinakite
 *
 */
@Component
public class BookDao {
    
    public List<Book> listPublishedBooks(String categoryId) {
        String sql = "select b.C_ID as b$id, b.C_NAME as b$name, b.C_DESC as b$desc, b.C_CATEGORY_ID as b$categoryId, b.C_LOGOURL as b$logoUrl, b.C_STATUS as b$status, b.C_CREATOR as b$creator, b.C_CREATETIME as b$createTime, b.C_MODIFIER as b$modifier, b.C_MODIFYTIME as b$modifytime, c.C_ID as c$id, c.C_NAME as c$name from T_BOOK b left join T_CATEGORY c on b.C_CATEGORY_ID = c.C_ID";
        if(StringUtils.isNotEmpty(categoryId)) {
            sql += " WHERE b.C_CATEGORY_ID = :categoryId";
        }
        Query query = IdeaJdbc.query(sql);
        if(StringUtils.isNotEmpty(categoryId)) {
            query.setParameter("categoryId", categoryId);
        }
        
        List<Book> books = query.populate("category", "c").listTo(Book.class, "b");
        return books;
    }
    
    /**
     * 分页列出所有图书
     * 
     * @return
     */
    public Page<Book> pageAllBooks(int curPage, int pageSize, String categoryId, String keyword) {
        boolean useAnd = false;
        String sql = "select b.C_ID as b$id, b.C_NAME as b$name, b.C_DESC as b$desc, b.C_CATEGORY_ID as b$categoryId, b.C_LOGOURL as b$logoUrl, b.C_STATUS as b$status, b.C_CREATOR as b$creator, b.C_CREATETIME as b$createTime, b.C_MODIFIER as b$modifier, b.C_MODIFYTIME as b$modifytime, c.C_ID as c$id, c.C_NAME as c$name from T_BOOK b left join T_CATEGORY c on b.C_CATEGORY_ID = c.C_ID";
        if(StringUtils.isNotEmpty(categoryId)) {
            sql += " WHERE b.C_CATEGORY_ID = :categoryId";
            useAnd = true;
        }
        
        if(StringUtils.isNotEmpty(keyword)) {
            if(useAnd) {
                sql += " AND";
            }else{
                sql += " WHERE"; 
            }
            sql += " b.C_NAME like :name";
        }
        
        Query query = IdeaJdbc.query(sql);
        if(StringUtils.isNotEmpty(categoryId)) {
            query.setParameter("categoryId", categoryId);
        }
        if(StringUtils.isNotEmpty(keyword)) {
            query.setParameter("name", "%" + keyword + "%");
        }
        Page<Book> books = query.populate("category", "c").pageTo(Book.class, "b", curPage, pageSize);
        return books;
    }
    
    /**
     * 分页列出某类别下图书
     * 
     * @return
     */
    public Page<Book> pageAllBooksByCategory(int curPage, int pageSize, String categoryId, String keyword) {
        String sql = "select b.C_ID as b$id, b.C_NAME as b$name, b.C_DESC as b$desc, b.C_CATEGORY_ID as b$categoryId, b.C_LOGOURL as b$logoUrl, b.C_STATUS as b$status, b.C_CREATOR as b$creator, b.C_CREATETIME as b$createTime, b.C_MODIFIER as b$modifier, b.C_MODIFYTIME as b$modifytime, c.C_ID as c$id, c.C_NAME as c$name from T_BOOK b left join T_CATEGORY c on b.C_CATEGORY_ID = c.C_ID WHERE b.C_CATEGORY_ID = :categoryId";
        
        if(StringUtils.isNotEmpty(keyword)) {
            sql += " AND b.C_NAME like :name";
        }
        
        Query query = IdeaJdbc.query(sql);
        if(StringUtils.isNotEmpty(categoryId)) {
            query.setParameter("categoryId", categoryId);
        }
        if(StringUtils.isNotEmpty(keyword)) {
            query.setParameter("name", "%" + keyword + "%");
        }
        Page<Book> books = query.populate("category", "c").pageTo(Book.class, "b", curPage, pageSize);
        return books;
    }

    public List<Book> queryPublishedBook(String keyword) {
        String sql = "select b.C_ID as b$id, b.C_NAME as b$name, b.C_DESC as b$desc, b.C_CATEGORY_ID as b$categoryId, b.C_LOGOURL as b$logoUrl, b.C_STATUS as b$status, b.C_CREATOR as b$creator, b.C_CREATETIME as b$createTime, b.C_MODIFIER as b$modifier, b.C_MODIFYTIME as b$modifytime, c.C_ID as c$id, c.C_NAME as c$name from T_BOOK b left join T_CATEGORY c on b.C_CATEGORY_ID = c.C_ID";
        if(StringUtils.isNotEmpty(keyword)) {
            sql += " WHERE b.C_NAME like :name";
        }
        Query query = IdeaJdbc.query(sql);
        if(StringUtils.isNotEmpty(keyword)) {
            query.setParameter("name", "%"+keyword+"%");
        }
        
        List<Book> books = query.populate("category", "c").listTo(Book.class, "b");
        return books;
    }
}
