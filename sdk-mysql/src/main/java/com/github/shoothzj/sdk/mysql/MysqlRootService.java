package com.github.shoothzj.sdk.mysql;

import com.github.shoothzj.sdk.mysql.module.MysqlConnModule;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author hezhangjian
 */
@Slf4j
public class MysqlRootService {

    private static final String CREATE_DB_SQL = "CREATE DATABASE IF NOT EXISTS %s DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin";

    private static final String CREATE_USER = "CREATE USER IF NOT EXISTS ? identified by ?";

    private final String rootUrl;

    private final String rootUser;

    private final String rootPwd;

    public MysqlRootService(MysqlConnModule mysqlConnModule, String rootUser, String rootPwd) {
        this.rootUrl = String.format("jdbc:mysql://%s:%d/mysql", mysqlConnModule.getHost(), mysqlConnModule.getPort());
        this.rootUser = rootUser;
        this.rootPwd = rootPwd;
    }

    public void createDbAndUser(String dbName, String username, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(rootUrl, rootUser, rootPwd);

        {
            PreparedStatement preparedStatement = conn.prepareStatement(String.format(CREATE_DB_SQL, dbName));
            preparedStatement.execute();
        }

        {
            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        }

        {
            PreparedStatement preparedStatement = conn.prepareStatement("GRANT ALL ON " + dbName + " .* to ?@'%'");
            preparedStatement.setString(1, username);
            preparedStatement.execute();
        }
    }

    public void createDb(String dbName) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(rootUrl, rootUser, rootPwd);
        PreparedStatement preparedStatement = conn.prepareStatement(String.format(CREATE_DB_SQL, dbName));
        preparedStatement.execute();
    }

}
