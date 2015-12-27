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
@Entity(dataSet="T_SECTION_AUDIO")
public class SectionAudio extends BaseEntity {
    @Property(dataItem="C_SECTION_ID", type=DataItemType.VARCHAR, length=32)
    private String sectionId;
    
    @Property(dataItem="C_AUDIOURL", type=DataItemType.VARCHAR, length=500)
    private String audioUrl;
    
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
     * @return the audioUrl
     */
    public String getAudioUrl() {
        return audioUrl;
    }
    
    /**
     * @param audioUrl the audioUrl to set
     */
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
