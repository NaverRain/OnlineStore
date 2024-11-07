package com.naverrain.persistence.utils.db.utils.connectionpool;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

public class DbcpConnectionPool {

    private static BasicDataSource bds = new BasicDataSource();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException();
        }
        bds.setUrl("jdbc:mysql://localhost:3306/naver_db");
        bds.setUsername("root");
        bds.setPassword("0000");
        bds.setMinIdle(3);
		bds.setTimeBetweenEvictionRunsMillis(1000);
        bds.setMaxIdle(20);
        bds.setMaxOpenPreparedStatements(200);
    }
    public static Connection getConnection() throws SQLException {
        return bds.getConnection();
    }


}
