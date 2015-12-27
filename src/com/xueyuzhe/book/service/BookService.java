/**
 * 
 */
package com.xueyuzhe.book.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideamoment.ideadp.exception.IdeaDataException;
import com.ideamoment.ideadp.exception.IdeaDataExceptionCode;
import com.ideamoment.ideajdbc.IdeaJdbc;
import com.ideamoment.ideajdbc.action.Page;
import com.ideamoment.ideajdbc.spring.IdeaJdbcTx;
import com.xueyuzhe.book.dao.BookDao;
import com.xueyuzhe.model.Book;
import com.xueyuzhe.model.PublishStatus;

/**
 * @author Chinakite
 *
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    @IdeaJdbcTx
    public List<Book> listPublishedBooks(String categoryId) {
        return bookDao.listPublishedBooks(categoryId);
    }
    
    @IdeaJdbcTx
    public List<Book> queryPublishedBook(String keyword) {
        return bookDao.queryPublishedBook(keyword);
    }
    
    @IdeaJdbcTx
    public Page<Book> queryAllBooks(int curPage,
                                    int pageSize,
                                    String categoryId,
                                    String keyword) {
        return bookDao.pageAllBooks(curPage, pageSize, categoryId, keyword);
    }
    
    @IdeaJdbcTx
    public Book findBook(String id) {
        return IdeaJdbc.find(Book.class, id);
    }
    
    /**
     * @return the bookDao
     */
    public BookDao getBookDao() {
        return bookDao;
    }
    
    /**
     * @param bookDao the bookDao to set
     */
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @IdeaJdbcTx
    public void addBook(String name,
                        String desc,
                        String logoUrl,
                        String categoryId) {

        Book book = new Book();
        book.setName(name);
        book.setDesc(desc);
        book.setLogoUrl(logoUrl);
        book.setCategoryId(categoryId);
        book.setStatus(PublishStatus.DRAFT);
        book.setCreator("1");
        book.setCreateTime(new Date());
        IdeaJdbc.save(book);
        
    }

    @IdeaJdbcTx
    public boolean updateBook(String id,
                           String name,
                           String desc,
                           String logoUrl) {
        Book book = IdeaJdbc.find(Book.class, id);
        
        if(book == null) {
            throw new IdeaDataException(IdeaDataExceptionCode.DATA_NOU_FOUND, String.format("Book[%s] is not found.", id));
        }else{
            book.setName(name);
            book.setDesc(desc);
            book.setLogoUrl(logoUrl);
            book.setModifier("1");
            book.setModifyTime(new Date());
            IdeaJdbc.update(book);
        }
        
        return true;
        
    }
    
    @IdeaJdbcTx
    public int disableBook(String id) {
        return IdeaJdbc.update(Book.class, id)
                        .setProperty("status", PublishStatus.DROPED)
                        .execute();
    }
    
    @IdeaJdbcTx
    public int enableBook(String id) {
        return IdeaJdbc.update(Book.class, id)
                        .setProperty("status", PublishStatus.PUBLISHED)
                        .execute();
    }

    @IdeaJdbcTx
    public void deleteBook(String id) {
        IdeaJdbc.delete(Book.class, id);
    }

}
