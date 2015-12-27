/**
 * 
 */
package com.xueyuzhe.user.dao;

import org.springframework.stereotype.Component;

import com.ideamoment.ideajdbc.IdeaJdbc;
import com.ideamoment.ideajdbc.action.Page;
import com.xueyuzhe.model.User;

/**
 * @author Chinakite
 *
 */
@Component
public class UserDao {
    
    public Page<User> pageAllUsers(int curPage, int pageSize, String keyword) {
        String sql = "select * from T_USER";
        return IdeaJdbc.query(sql).pageTo(User.class, curPage, pageSize);
    }
    
    public int deleteUserCategory(String userId) {
        String sql = "delete from T_USER_CATEGORY where C_USER_ID = :userId";
        return IdeaJdbc.sql(sql).setParameter("userId", userId).execute();
    }
}
