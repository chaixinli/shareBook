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
     * 获取数据表的属性名字
     * @param columnName
     * @return
     */
    public String getFieldName(String columnName) {
        String[] pieces = columnName.split("_");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < pieces.length; i++) {
            pieces[i].replace("-", "");
            if (i == 0) {
                stringBuffer.append(pieces[i].toLowerCase());
            } else {
                stringBuffer.append(pieces[i].substring(0, 1).toUpperCase());
                stringBuffer.append(pieces[i].substring(1).toLowerCase());
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 组装set方法
     * @param columnName 字段名
     * @return
     */
    public String getSetMethodName(String columnName) {
        return "set" + columnName.substring(0, 1).toUpperCase()
                + columnName.substring(1);
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
        DBConn dbConn = new DBConn();
        try {
            //获取表的信息
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                System.out.println("表名：" + resultSet.getString(3));
                System.out.println("----------------------------");
            }
            dbConn.close(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

//    public static void main(String[] args) {
//        DBConn db = new DBConn();
//        ResultSet resultSet = db.select("select * from user");
//        try {
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//            int columnCount = resultSetMetaData.getColumnCount();
//            System.out.println("ResultSet对象中的列数：" + columnCount);
//            for (int i = 1; i < columnCount; i++) {
//                System.out.println();
//                String columnName = resultSetMetaData.getColumnName(i);
//                String fieldName = db.getFieldName(columnName);
//                System.out.println("列名称：" + columnName + "---->属性名称：" + fieldName);
//                System.out.println("列类型（DB）:" + resultSetMetaData.getColumnTypeName(i));
//                System.out.println("长度：" + resultSetMetaData.getPrecision(i));
//                System.out.println("是否自动编号：" + resultSetMetaData.isAutoIncrement(i));
//                System.out.println("是否可以为空：" + resultSetMetaData.isNullable(i));
//                System.out.println("是否可以写入：" + resultSetMetaData.isReadOnly(i));
//            }
//
//            //获取数据表的值
//            while(resultSet.next()) {
//                System.out.print("id:" + resultSet.getLong(1) + ", ");
//                System.out.print("userName:" + resultSet.getString(2) + ", ");
//                System.out.print("userPassword:" + resultSet.getString(3) + ", ");
//                System.out.print("email:" + resultSet.getString(4) + ", ");
//                System.out.println("birthday: " + resultSet.getTimestamp(5) + ",");
//                System.out.print("createTime:" + resultSet.getTimestamp(6) + ", ");
//                System.out.println("updateTime:" + resultSet.getTimestamp(7));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                db.close(resultSet);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
