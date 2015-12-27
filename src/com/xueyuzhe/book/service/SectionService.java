/**
 * 
 */
package com.xueyuzhe.book.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideamoment.ideadp.appcontext.IdeaApplicationContext;
import com.ideamoment.ideadp.exception.IdeaDataException;
import com.ideamoment.ideadp.exception.IdeaDataExceptionCode;
import com.ideamoment.ideajdbc.IdeaJdbc;
import com.ideamoment.ideajdbc.spring.IdeaJdbcTx;
import com.xueyuzhe.book.dao.BookDao;
import com.xueyuzhe.book.dao.SectionDao;
import com.xueyuzhe.model.Book;
import com.xueyuzhe.model.Constants;
import com.xueyuzhe.model.DownloadLog;
import com.xueyuzhe.model.PublishStatus;
import com.xueyuzhe.model.Section;
import com.xueyuzhe.model.SectionAudio;

/**
 * @author Chinakite
 *
 */
@Service
public class SectionService {
    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private BookDao bookDao;
    
    /**
     * @return the sectionDao
     */
    public SectionDao getSectionDao() {
        return sectionDao;
    }

    
    /**
     * @param sectionDao the sectionDao to set
     */
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
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
    public List<Section> listSections(String bookId) {
        return sectionDao.listAllSectionsByBook(bookId);
    }

    @IdeaJdbcTx
    public void addSection(String bookId,
                            String name,
                            String desc,
                            String audioUrl) {

        Section section = new Section();
        section.setBookId(bookId);
        section.setName(name);
        section.setDesc(desc);
        section.setStatus(PublishStatus.DRAFT);
        section.setCreator("1");
        section.setCreateTime(new Date());
        IdeaJdbc.save(section);
        
        String sectionId = section.getId();
        
        SectionAudio audio = new SectionAudio();
        audio.setSectionId(sectionId);
        audio.setAudioUrl(audioUrl);
        IdeaJdbc.save(audio);
        
    }
    
    @IdeaJdbcTx
    public Section findSection(String id) {
        Section section = IdeaJdbc.find(Section.class, id);
        if(section == null) {
            throw new IdeaDataException(IdeaDataExceptionCode.DATA_NOU_FOUND, String.format("Book[%s] is not found.", id));
        }else{
            List<SectionAudio> audioes = sectionDao.listAudioesBySectionId(id);
            section.setAudioes(audioes);
            return section;
        }
    }
    
    @IdeaJdbcTx
    public boolean updateSection(String id,
                           String name,
                           String desc,
                           String audioUrl) {
        Section section = IdeaJdbc.find(Section.class, id);
        
        if(section == null) {
            throw new IdeaDataException(IdeaDataExceptionCode.DATA_NOU_FOUND, String.format("Book[%s] is not found.", id));
        }else{
            section.setName(name);
            section.setDesc(desc);
            section.setModifier("1");
            section.setModifyTime(new Date());
            IdeaJdbc.update(section);
            
            List<SectionAudio> audioes = sectionDao.listAudioesBySectionId(id);
            if(audioes != null && audioes.size() > 0) {
                for(SectionAudio sa : audioes) {
                    sa.setAudioUrl(audioUrl);
                    IdeaJdbc.update(sa);
                }
            }else{
                SectionAudio sa = new SectionAudio();
                sa.setSectionId(id);
                sa.setAudioUrl(audioUrl);
                IdeaJdbc.save(sa);
            }
        }
        
        return true;
        
    }
    
    @IdeaJdbcTx
    public int disableSection(String id) {
        return IdeaJdbc.update(Section.class, id)
                        .setProperty("status", PublishStatus.DROPED)
                        .execute();
    }
    
    @IdeaJdbcTx
    public int enableSection(String id) {
        return IdeaJdbc.update(Section.class, id)
                        .setProperty("status", PublishStatus.PUBLISHED)
                        .execute();
    }

    @IdeaJdbcTx
    public void deleteSection(String id) {
        sectionDao.deleteAudioesBySectionId(id);
        IdeaJdbc.delete(Section.class, id);
    }
    
    @IdeaJdbcTx
    public DownloadFile downloadAudioes(String sectionId, String userId) {
        Section section = findSection(sectionId);
        List<SectionAudio> audioes = section.getAudioes();
        
        if(audioes.size() == 0) {
            throw new IdeaDataException(IdeaDataExceptionCode.DATA_NOU_FOUND, String.format("Audio of section[%s] is not found.", sectionId));
        }else{
            SectionAudio sa = audioes.get(0);
            String audioUrl = sa.getAudioUrl();
            
            String webRoot = IdeaApplicationContext.getInstance().getWebRoot();
            
            File file = new File(webRoot + audioUrl);
            
            DownloadLog dlog = new DownloadLog();
            dlog.setBookId(section.getBookId());
            dlog.setCreateTime(new Date());
            dlog.setCreator(Constants.SYSTEM_ID);
            dlog.setSectionAudioId(sa.getId());
            dlog.setSectionId(sectionId);
            dlog.setUserId(userId);
            IdeaJdbc.save(dlog);
            
            DownloadFile df = new DownloadFile();
            df.setFile(file);
            
            String bookId = section.getBookId();
            Book book = IdeaJdbc.find(Book.class, bookId);
            
            df.setBookName(book.getName());
            df.setSectionName(section.getName());
            return df;
        }
    }
    
    public class DownloadFile {
        File file;
        
        String bookName;
        
        String sectionName;

        
        /**
         * @return the file
         */
        public File getFile() {
        
            return file;
        }

        
        /**
         * @param file the file to set
         */
        public void setFile(File file) {
        
            this.file = file;
        }

        
        /**
         * @return the bookName
         */
        public String getBookName() {
        
            return bookName;
        }

        
        /**
         * @param bookName the bookName to set
         */
        public void setBookName(String bookName) {
        
            this.bookName = bookName;
        }

        
        /**
         * @return the sectionName
         */
        public String getSectionName() {
        
            return sectionName;
        }

        
        /**
         * @param sectionName the sectionName to set
         */
        public void setSectionName(String sectionName) {
        
            this.sectionName = sectionName;
        }
        
        
    }
}
