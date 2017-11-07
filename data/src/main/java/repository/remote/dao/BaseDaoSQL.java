package repository.remote.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class BaseDaoSQL {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    FileInputStream fis;

    Properties property = new Properties();

    Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        fis = new FileInputStream("data/src/main/resources/config.properties");
        property.load(fis);

        String JDBC_DB_URL = property.getProperty("db.host");
        String JDBC_USER = property.getProperty("db.login");
        String JDBC_PASS =  property.getProperty("db.password");

        Class.forName(JDBC_DRIVER);
        Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
        connObj.setAutoCommit(false);

        return connObj;
    }
}
