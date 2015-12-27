/**
 * 
 */
package com.xueyuzhe.model;

import java.util.Date;

import com.ideamoment.ideadata.annotation.DataItemType;
import com.ideamoment.ideadata.annotation.Entity;
import com.ideamoment.ideadata.annotation.Property;

/**
 * @author Chinakite
 *
 */
@Entity(dataSet="T_USER_ACCESSLOG")
public class UserAccessLog extends BaseEntity{
    @Property(dataItem="C_USER_ID", type=DataItemType.VARCHAR, length=32)
    private String userId;
    
    @Property(dataItem="C_PLATFORM", type=DataItemType.VARCHAR, length=60)
    private String platform;
    
    @Property(dataItem="C_IP", type=DataItemType.VARCHAR, length=30)
    private String ip;
    
    @Property(dataItem="C_ACCESSTIME", type=DataItemType.DATETIME)
    private Date accessTime;

    
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
     * @return the platform
     */
    public String getPlatform() {
    
        return platform;
    }

    
    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
    
        this.platform = platform;
    }

    
    /**
     * @return the ip
     */
    public String getIp() {
    
        return ip;
    }

    
    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
    
        this.ip = ip;
    }

    
    /**
     * @return the accessTime
     */
    public Date getAccessTime() {
    
        return accessTime;
    }

    
    /**
     * @param accessTime the accessTime to set
     */
    public void setAccessTime(Date accessTime) {
    
        this.accessTime = accessTime;
    }
    
    
}
