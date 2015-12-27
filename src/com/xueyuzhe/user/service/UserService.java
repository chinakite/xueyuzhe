/**
 * 
 */
package com.xueyuzhe.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideamoment.ideajdbc.IdeaJdbc;
import com.ideamoment.ideajdbc.action.Page;
import com.ideamoment.ideajdbc.spring.IdeaJdbcTx;
import com.xueyuzhe.model.User;
import com.xueyuzhe.model.UserCategory;
import com.xueyuzhe.user.dao.UserDao;

/**
 * @author Chinakite
 *
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    
    @IdeaJdbcTx
    public Page<User> pageAllUsers(int curPage, int pageSize, String keyword) {
        return userDao.pageAllUsers(curPage, pageSize, keyword);
    }
    
    @IdeaJdbcTx(readOnly=true)
    public User findUser(String id) {
        return IdeaJdbc.find(User.class, id);
    }

    @IdeaJdbcTx
    public User updateUser(String userId, String name, String email, String mobile, String age, String[] category) {
        User user = findUser(userId);
        user.setName(name);
        user.setEmail(email);
        user.setMobilephone(mobile);
        user.setAgeRange(age);
        user.setModifier(userId);
        user.setModifyTime(new Date());
        
        IdeaJdbc.update(user);

        userDao.deleteUserCategory(userId);
        
        for(int i=0; i<category.length; i++) {
            String c = category[i];
            UserCategory uc = new UserCategory();
            uc.setUserId(userId);
            uc.setCategoryId(c);
            IdeaJdbc.save(uc);
        }
        
        return user;
    }
    
    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }
    
    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
}
