/**
 * 
 */
package com.xueyuzhe.model;

import java.util.ArrayList;
import java.util.List;

import com.ideamoment.ideadata.annotation.DataItemType;
import com.ideamoment.ideadata.annotation.Entity;
import com.ideamoment.ideadata.annotation.Property;
import com.ideamoment.ideadata.annotation.Ref;

/**
 * @author Chinakite
 *
 */
@Entity(dataSet="T_SECTION")
public class Section extends HistoriableEntity {
    @Property(dataItem="C_NAME", type=DataItemType.VARCHAR, length=60)
    private String name;
    
    @Property(dataItem="C_DESC", type=DataItemType.VARCHAR, length=500)
    private String desc;
    
    @Property(dataItem="C_STATUS", type=DataItemType.VARCHAR, length=2)
    private String status;
    
    @Property(dataItem="C_BOOK_ID", type=DataItemType.VARCHAR, length=32)
    private String bookId;
    
    @Ref(entityClass=SectionAudio.class)
    private List<SectionAudio> audioes = new ArrayList<SectionAudio>();
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
    
    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the audioes
     */
    public List<SectionAudio> getAudioes() {
        return audioes;
    }
    
    /**
     * @param audioes the audioes to set
     */
    public void setAudioes(List<SectionAudio> audioes) {
        this.audioes = audioes;
    }

    public String getStatusStr() {
        if(this.status == null || this.status.equals(PublishStatus.DRAFT)) {
            return PublishStatus.DRAFT_STR;
        }else if(this.status.equals(PublishStatus.PUBLISHED)) {
            return PublishStatus.PUBLISHED_STR;
        }else if(this.status.equals(PublishStatus.DROPED)) {
            return PublishStatus.DROPED_STR;
        }else{
            return PublishStatus.DRAFT_STR;
        }
    }
}
