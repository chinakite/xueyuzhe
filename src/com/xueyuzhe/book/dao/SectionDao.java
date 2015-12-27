/**
 * 
 */
package com.xueyuzhe.book.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ideamoment.ideajdbc.IdeaJdbc;
import com.xueyuzhe.model.Section;
import com.xueyuzhe.model.SectionAudio;

/**
 * @author Chinakite
 *
 */
@Component
public class SectionDao {
    
    public List<Section> listAllSectionsByBook(String bookId) {
        String sql = "select s.C_ID as s$id, s.C_NAME as s$name, s.C_DESC as s$desc, s.C_STATUS as s$status, s.C_BOOK_ID as s$bookId, s.C_CREATOR as s$creator, s.C_CREATETIME as s$createTime, s.C_MODIFIER as s$modifier, s.C_MODIFYTIME as s$modifyTime, sa.C_ID as sa$id, sa.C_AUDIOURL as sa$audioUrl  from T_SECTION s right join T_SECTION_AUDIO sa on s.C_ID = sa.C_SECTION_ID where s.C_BOOK_ID = :bookId";
    
        List<Section> secs = IdeaJdbc.query(sql)
                                        .setParameter("bookId", bookId)
                                        .populate("audioes", "sa")
                                        .listTo(Section.class, "s");
    
        return secs;
    }
    
    public int deleteAudioesBySectionId(String sectionId) {
        String sql = "delete from T_SECTION_AUDIO where C_SECTION_ID = :sectionId";
        return IdeaJdbc.sql(sql).setParameter("sectionId", sectionId).execute();
    }
    
    public List<SectionAudio> listAudioesBySectionId(String sectionId) {
        String sql = "select * from T_SECTION_AUDIO where C_SECTION_ID = :sectionId";
        List<SectionAudio> audioes = IdeaJdbc.query(sql)
                                                .setParameter("sectionId", sectionId)
                                                .listTo(SectionAudio.class);
        return audioes;
    }
}
