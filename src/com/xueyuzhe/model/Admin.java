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
@Entity(dataSet="T_ADMIN")
public class Admin extends HistoriableEntity {
    @Property(dataItem="C_ACCOUNT", type=DataItemType.VARCHAR, length=60)
    private String account;
    
    @Property(dataItem="C_NAME", type=DataItemType.VARCHAR, length=60)
    private String name;
    
    @Property(dataItem="C_PASSWORD", type=DataItemType.VARCHAR, length=20)
    private String password;
    
    @Property(dataItem="C_STATUS", type=DataItemType.VARCHAR, length=2)
    private String status;

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }
    
    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }
    
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
    
}
