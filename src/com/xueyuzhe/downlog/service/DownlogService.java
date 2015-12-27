/**
 * 
 */
package com.xueyuzhe.downlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideamoment.ideajdbc.spring.IdeaJdbcTx;
import com.xueyuzhe.downlog.dao.DownlogDao;
import com.xueyuzhe.model.Book;
import com.xueyuzhe.model.Section;

/**
 * @author Chinakite
 *
 */
@Service
public class DownlogService {
    @Autowired
    private DownlogDao downlogDao;

    /**
     * @return the downlogDao
     */
    public DownlogDao getDownlogDao() {
        return downlogDao;
    }

    /**
     * @param downlogDao the downlogDao to set
     */
    public void setDownlogDao(DownlogDao downlogDao) {
        this.downlogDao = downlogDao;
    }

    @IdeaJdbcTx
    public List<Book> listDownlogBooks(String userId) {
        List<Book> books = downlogDao.listDownlogBooks(userId);
        return books;
    }
    
    @IdeaJdbcTx
    public List<Section> listDownlogSectionsByBook(String userId, String bookId) {
        List<Section> sections = downlogDao.listDownlogSectionsByBook(userId, bookId);
        return sections;
    }
}
