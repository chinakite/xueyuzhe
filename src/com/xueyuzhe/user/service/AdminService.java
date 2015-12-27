/**
 * 
 */
package com.xueyuzhe.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideamoment.ideadp.util.CodecUtils;
import com.ideamoment.ideajdbc.spring.IdeaJdbcTx;
import com.xueyuzhe.model.Admin;
import com.xueyuzhe.user.LoginException;
import com.xueyuzhe.user.LoginExceptionCode;
import com.xueyuzhe.user.dao.AdminDao;

/**
 * @author Chinakite
 *
 */
@Service
public class AdminService {
    @Autowired
    private AdminDao userDao;

    /**
     * 管理员登录
     * 
     * @param account 管理员账号
     * @param password 管理员密码
     * @return
     */
    @IdeaJdbcTx
    public Admin login(String account, String password) {
        Admin admin = userDao.queryAdmin(account);
        if(admin == null) {
            throw new LoginException(LoginExceptionCode.ADMIN_NOT_FOUND, String.format("Admin[%s] is not found.", account));
        }else{
            String dbPwd = admin.getPassword();
            String inputPwd = CodecUtils.md5(password);
            if(dbPwd.equals(inputPwd)) {
                return admin;
            }else{
                throw new LoginException(LoginExceptionCode.PASSWORD_NOT_CORRECT, String.format("Admin[%s] password is not correct.", account));
            }
        }
    }
    
    /**
     * @return the userDao
     */
    public AdminDao getUserDao() {
        return userDao;
    }
    
    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(AdminDao userDao) {
        this.userDao = userDao;
    }
}
