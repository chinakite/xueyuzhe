/**
 * 
 */
package com.xueyuzhe.user.dao;

import org.springframework.stereotype.Component;

import com.ideamoment.ideajdbc.IdeaJdbc;
import com.xueyuzhe.model.Admin;

/**
 * @author Chinakite
 *
 */
@Component
public class AdminDao {
    /**
     * 查询管理员账号
     * 
     * @param account 账号
     * @return
     */
    public Admin queryAdmin(String account) {
        String sql = "select * from T_ADMIN where C_ACCOUNT = :account";
        
        Admin admin = (Admin)IdeaJdbc.query(sql)
                                        .setParameter("account", account)
                                        .uniqueTo(Admin.class);
        
        return admin;
    }
}
