/**
 * 
 */
package com.xueyuzhe.model;

import java.util.Date;

import com.ideamoment.ideadata.annotation.DataItemType;
import com.ideamoment.ideadata.annotation.Property;

/**
 * @author Chinakite
 *
 */
public class HistoriableEntity extends BaseEntity {
    @Property(dataItem="C_CREATOR", type=DataItemType.VARCHAR, length=32)
    protected String creator;
    
    @Property(dataItem="C_CREATETIME", type=DataItemType.DATETIME)
    protected Date createTime;
    
    @Property(dataItem="C_MODIFIER", type=DataItemType.VARCHAR, length=32)
    protected String modifier;
    
    @Property(dataItem="C_MODIFYTIME", type=DataItemType.DATETIME)
    protected Date modifyTime;
    
    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }
    
    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }
    
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the modifier
     */
    public String getModifier() {
        return modifier;
    }

    
    /**
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    
    /**
     * @return the modifyTime
     */
    public Date getModifyTime() {
        return modifyTime;
    }
    
    /**
     * @param modifyTime the modifyTime to set
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
