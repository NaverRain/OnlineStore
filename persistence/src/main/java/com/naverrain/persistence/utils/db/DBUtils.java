package com.naverrain.persistence.utils.db;

import com.naverrain.persistence.utils.db.utils.connectionpool.DbcpConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {

//    private static final String JDBC_LOCAL_HOST = "jdbc:mysql://localhost:3306/";
//    private static final String DB_NAME = "naver_db";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "0000";

    private DBUtils(){
    }



    public static Connection getConnection(){
        try {
           return DbcpConnectionPool.getConnection();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
