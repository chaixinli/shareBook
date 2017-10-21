package com.chai.share.common.util;

import java.sql.*;

/**
 * Created by chaixinli on 2017/10/20.
 */
public class DBConn {

    private static String driver;   //驱动

    private static String url;  //URL

    private static String userName;     //用户名

    private static String password;     //密码

    private static Connection connection;   //定义连接

    private static Statement statement;     //statement

    private ResultSet resultSet;    //结果集

    static {
        try {
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://127.0.0.1:3306/sharebook";
            userName = "root";
            password = "123456";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("--------mysql连接成功-------");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造函数，默认加载配置文件为jdbc.driver
     */
    public DBConn() {
        this.connection = this.getConnection();
    }

    public Connection getConnection() {
        return DBConn.connection;
    }

    /**
     * 插入
     * @param sql
     * @return
     */
    public int insert(String sql) {
        int count = -1;
        try {
            statement = connection.createStatement();
            count = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 删除
     * @param sql
     * @return
     */
    public int delete(String sql) {
        int count = -1;
        try {
            statement = connection.createStatement();
            count = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 更新
     * @param sql
     * @return
     */
    public int update(String sql) {
        int count = -1;
        try {
            statement = connection.createStatement();
            count = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询结果集
     * @param sql
     * @return
     */
    public ResultSet select(String sql) {
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 关闭数据库结果集，数据库操作对象，数据库连接
     * @param resultSet
     * @throws SQLException
     */
    public void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
            resultSet = null;
        }
        if (statement != null) {
            statement.close();
            statement = null;
        }
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    /**
     * 关闭数据库操作对象，数据库连接对象
     * @throws SQLException
     */
    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
            statement = null;
        }
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

/*    public static void main(String[] args) {
        DBConn db = new DBConn();
        db.getConnection();
        ResultSet resultSet = db.select("select * from user");
        try {
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}
