/**
 * 
 */
package com.xueyuzhe;

import com.ideamoment.ideajdbc.configuration.IdeaJdbcConfiguration;
import com.ideamoment.ideajdbc.tool.mysql.entity2ddl.MySqlEntity2Ddl;
import com.xueyuzhe.model.UserAccessLog;

/**
 * @author Chinakite
 *
 */
public class GenerateDb {

    /**
     * @param args
     */
    public static void main(String[] args) {
        IdeaJdbcConfiguration.initConfig("ideajdbc.properties");
        
        MySqlEntity2Ddl tool = new MySqlEntity2Ddl();
        Class[] cls = new Class[]{
                UserAccessLog.class};
        
        for(Class clz : cls) {
            tool.syncTable("xueyuzhe", "xueyuzhe", null, clz, true);
        }
        
    }

}
