/**
 * 
 */
package com.xueyuzhe.model;

import com.ideamoment.ideadata.annotation.DataItemType;
import com.ideamoment.ideadata.annotation.Id;

/**
 * @author Chinakite
 *
 */
public class BaseEntity {
    @Id(dataItem="C_ID", type=DataItemType.VARCHAR, length=32, generator="uuid")
    protected String id;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
