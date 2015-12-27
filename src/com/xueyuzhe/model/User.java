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
@Entity(dataSet="T_USER")
public class User extends HistoriableEntity {
    @Property(dataItem="C_ACCOUNT", type=DataItemType.VARCHAR, length=60)
    private String account;
    
    @Property(dataItem="C_NAME", type=DataItemType.VARCHAR, length=60)
    private String name;
    
    @Property(dataItem="C_GENDER", type=DataItemType.VARCHAR, length=1)
    private String gender;
    
    @Property(dataItem="C_EMAIL", type=DataItemType.VARCHAR, length=200)
    private String email;
    
    @Property(dataItem="C_MOBILEPHONE", type=DataItemType.VARCHAR, length=20)
    private String mobilephone;
    
    @Property(dataItem="C_AGERANGE", type=DataItemType.VARCHAR, length=2)
    private String ageRange;
    
    @Property(dataItem="C_WX_OPENID", type=DataItemType.VARCHAR, length=60)
    private String wxOpenId;
    
    @Property(dataItem="C_WX_UNIONID", type=DataItemType.VARCHAR, length=60)
    private String wxUnionId;
    
    @Property(dataItem="C_LOGOURL", type=DataItemType.VARCHAR, length=500)
    private String logoUrl;
    
    @Property(dataItem="C_status", type=DataItemType.VARCHAR, length=2)
    private String status;
    
    @Property(dataItem="C_CITY", type=DataItemType.VARCHAR, length=60)
    private String city;
    
    @Property(dataItem="C_PROVINCE", type=DataItemType.VARCHAR, length=60)
    private String province;
    
    @Property(dataItem="C_COUNTRY", type=DataItemType.VARCHAR, length=60)
    private String country;
    
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
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the mobilephone
     */
    public String getMobilephone() {
        return mobilephone;
    }
    
    /**
     * @param mobilephone the mobilephone to set
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    
    /**
     * @return the ageRange
     */
    public String getAgeRange() {
        return ageRange;
    }
    
    /**
     * @param ageRange the ageRange to set
     */
    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
    
    /**
     * @return the wxOpenId
     */
    public String getWxOpenId() {
        return wxOpenId;
    }
    
    /**
     * @param wxOpenId the wxOpenId to set
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
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
     * @return the wxUnionId
     */
    public String getWxUnionId() {
        return wxUnionId;
    }

    /**
     * @param wxUnionId the wxUnionId to set
     */
    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }
    
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenderStr() {
        if(this.gender == null || this.gender.equals(GenderType.SECRET)) {
            return GenderType.SECRET_STR;
        }else if(this.gender.equals(GenderType.MALE)) {
            return GenderType.MALE_STR;
        }else if(this.gender.equals(GenderType.FEMALE)) {
            return GenderType.FEMALE_STR;
        }else{
            return GenderType.SECRET_STR;
        }
    }
    
    public String getStatusStr() {
        if(this.status == null || this.status.equals(UserStatus.DISABLED)) {
            return UserStatus.DISABLED_STR;
        }else if(this.status.equals(UserStatus.ENABLED)) {
            return UserStatus.ENABLED_STR;
        }else{
            return UserStatus.DISABLED_STR;
        }
    }
}
