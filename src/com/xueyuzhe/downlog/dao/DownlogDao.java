/**
 * 
 */
package com.xueyuzhe.downlog.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ideamoment.ideajdbc.IdeaJdbc;
import com.xueyuzhe.model.Book;
import com.xueyuzhe.model.Section;

/**
 * @author Chinakite
 *
 */
@Component
public class DownlogDao {

    public List<Book> listDownlogBooks(String userId) {
        String sql = "select * from T_BOOK where C_ID in (select distinct C_BOOK_ID from T_DOWNLOAD_LOG where C_USER_ID = :userId)";
        
        List<Book> books = IdeaJdbc.query(sql)
                                    .setParameter("userId", userId)
                                    .listTo(Book.class);
        
        return books;
    }
    
    public List<Section> listDownlogSectionsByBook(String userId, String bookId) {
//        String sql = "select * from T_SECTION where C_ID in (select distinct C_SECTION_ID from T_DOWNLOAD_LOG where C_USER_ID = :userId and C_BOOK_ID = :bookId)";
        
        String sql = "SELECT "
                   +    "s.C_ID AS s$id, "
                   +    "s.C_NAME AS s$name, "
                   +    "s.C_DESC AS s$desc, "
                   +    "s.C_STATUS AS s$status, "
                   +    "s.C_BOOK_ID AS s$bookId, "
                   +    "s.C_CREATOR AS s$creator, "
                   +    "s.C_CREATETIME AS s$createTime, "
                   +    "s.C_MODIFIER AS s$modifier, "
                   +    "s.C_MODIFYTIME AS s$modifyTime, "
                   +    "sa.C_ID AS sa$id, "
                   +    "sa.C_AUDIOURL AS sa$audioUrl "
                   + "FROM "
                   +    "T_SECTION s "
                   +    "RIGHT JOIN T_SECTION_AUDIO sa  "
                   +        "ON s.C_ID = sa.C_SECTION_ID  "
                   + "WHERE s.C_ID IN  "
                   +    "(SELECT DISTINCT dl.C_SECTION_ID "
                   +        "FROM "
                   +        "T_DOWNLOAD_LOG dl "
                   +        "WHERE dl.C_USER_ID = :userId "
                   +            "AND dl.C_BOOK_ID = :bookId)";
        
        
        List<Section> sections = IdeaJdbc.query(sql)
                                            .setParameter("userId", userId)
                                            .setParameter("bookId", bookId)
                                            .populate("audioes", "sa")
                                            .listTo(Section.class, "s");
        
        return sections;
    }

}
