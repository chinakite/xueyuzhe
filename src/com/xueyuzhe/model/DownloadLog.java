/**
 * 
 */
package com.xueyuzhe.model;

import com.ideamoment.ideadata.annotation.DataItemType;
import com.ideamoment.ideadata.annotation.Entity;
import com.ideamoment.ideadata.annotation.Property;

/**
 * @author Chinakite
 *
 */
@Entity(dataSet="T_DOWNLOAD_LOG")
public class DownloadLog extends HistoriableEntity {
    @Property(dataItem="C_USER_ID", type=DataItemType.VARCHAR, length=32)
    private String userId;
    
    @Property(dataItem="C_SECTION_AUDIO_ID", type=DataItemType.VARCHAR, length=32)
    private String sectionAudioId;
    
    @Property(dataItem="C_SECTION_ID", type=DataItemType.VARCHAR, length=32)
    private String sectionId;
    
    @Property(dataItem="C_BOOK_ID", type=DataItemType.VARCHAR, length=32)
    private String bookId;
    
    @Property(dataItem="C_CATEGORY_ID", type=DataItemType.VARCHAR, length=32)
    private String categoryId;

    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * @return the sectionAudioId
     */
    public String getSectionAudioId() {
        return sectionAudioId;
    }
    
    /**
     * @param sectionAudioId the sectionAudioId to set
     */
    public void setSectionAudioId(String sectionAudioId) {
        this.sectionAudioId = sectionAudioId;
    }
    
    /**
     * @return the sectionId
     */
    public String getSectionId() {
        return sectionId;
    }
    
    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
    
    /**
     * @return the bookId
     */
    public String getBookId() {
        return bookId;
    }
    
    /**
     * @param bookId the bookId to set
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    
    /**
     * @return the categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }
    
    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
}
