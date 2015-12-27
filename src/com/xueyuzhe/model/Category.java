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
@Entity(dataSet="T_CATEGORY")
public class Category extends HistoriableEntity {
    
    @Property(dataItem="C_NAME", type=DataItemType.VARCHAR, length=60)
    private String name;
    
    @Property(dataItem="C_DESC", type=DataItemType.VARCHAR, length=500)
    private String desc;
    
    @Property(dataItem="C_LOGOURL", type=DataItemType.VARCHAR, length=500)
    private String logoUrl;
    
    @Property(dataItem="C_STATUS", type=DataItemType.VARCHAR, length=2)
    private String status;
    
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
     * @return the logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }
    
    /**
     * @param logoUrl the logoUrl to set
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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
