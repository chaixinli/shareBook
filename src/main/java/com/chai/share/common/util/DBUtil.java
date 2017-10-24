package com.chai.share.common.util;

import com.chai.share.common.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by chaixinli on 2017/10/21.
 */
@SuppressWarnings({"unchecked", "unused"})
public class DBUtil<T> {

    private static DBConn dbConn;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    static {
        dbConn = new DBConn();
    }

    /**
     * 查询一条数据
     * @param objClass
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public T findOne(Object objClass, String sql, List params) {
        Class clazz = objClass.getClass();
        try {
            this.connection = dbConn.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            this.getParamsType(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();
            Class type = null;  //属性类型
            if (resultSet.next()) {
                objClass = this.getValues(clazz, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConn.close(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) objClass;
    }

    /**
     * 分页查询
     * @param objClass
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<T> findList(Object objClass, String sql, List params) {
        List<Object> result = new ArrayList<Object>();//创建返回值对象
        try {
            Class clazz = objClass.getClass();
            connection = dbConn.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            this.getParamsType(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                objClass = this.getValues(clazz, resultSet);
                result.add(objClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConn.close(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (List<T>) result;
    }

    /**
     * 获取类中的一个对象
     * @param clazz
     * @return
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object getValues(Class clazz, ResultSet resultSet) throws NoSuchFieldException, NoSuchMethodException,
            SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class type = null;  //属性类型
        Method method = null;   //声明Method对象
        Object objClass = clazz.newInstance();//创建一个实例
        String clazzName = clazz.getName(); //获取类的名字

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = resultSetMetaData.getColumnName(i); //获取列的名字
            String fieldName = dbConn.getFieldName(columnName);     //获取属性的名字
            String methodName = dbConn.getSetMethodName(fieldName);  //组装set方法名称
            String fieldType = clazz.getDeclaredField(fieldName).getType().getName();   //获取字段类型名称
            if (fieldType.equals("int")) {
                type = int.class;   //赋值属性类型
                method = clazz.getMethod(methodName, type); //获得Method实例
                method.invoke(objClass, resultSet.getInt(columnName));
            } else if (fieldType.equals("long")) {
                type = long.class;   //赋值属性类型
                method = clazz.getMethod(methodName, type); //获得Method实例
                method.invoke(objClass, resultSet.getLong(columnName));
            } else if (fieldType.equals("double")) {
                type = double.class;   //赋值属性类型
                method = clazz.getMethod(methodName, type); //获得Method实例
                method.invoke(objClass, resultSet.getDouble(columnName));
            } else if (fieldType.equals("java.sql.Timestamp")) {
                type = java.sql.Timestamp.class;   //赋值属性类型
                method = clazz.getMethod(methodName, type); //获得Method实例
                method.invoke(objClass, resultSet.getTimestamp(columnName));
            } else if (fieldType.equals("java.lang.String")) {
                type = java.lang.String.class;   //赋值属性类型
                method = clazz.getMethod(methodName, type); //获得Method实例
                method.invoke(objClass, resultSet.getString(columnName));
            }
        }
        return objClass;
    }

    /**
     * 获取sql语句中的参数
     * @param preparedStatement
     * @param params
     * @throws SQLException
     */
    private void getParamsType(PreparedStatement preparedStatement, List params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            if (params.get(i) != null) {
                Object obj = params.get(i);
                 /*
                 * 判断参数的原始类型
                 * 暂时判断Integer，Long，Double，Timestamp，String类型
                 */
                if (obj.getClass().getName().equals("java.lang.Integer")) {
                    preparedStatement.setInt(i + 1, Integer.valueOf(obj.toString()));
                } else if (obj.getClass().getName().equals("java.lang.Long")) {
                    preparedStatement.setLong(i + 1, Long.valueOf(obj.toString()));
                } else if (obj.getClass().getName().equals("java.lang.Double")) {
                    preparedStatement.setDouble(i + 1, Double.valueOf(obj.toString()));
                } else if (obj.getClass().getName().equals("java.sql.Timestamp")) {
                    preparedStatement.setTimestamp(i + 1, Timestamp.valueOf(obj.toString()));
                } else if (obj.getClass().getName().equals("java.lang.String")) {
                    preparedStatement.setString(i + 1, obj.toString());
                } else {
                    preparedStatement.setObject(i + 1, obj);
                }
            }
        }
    }

/*    public static void main(String[] args) {
        DBUtil dbUtil = new DBUtil();
        String sql = "select * from user";
        User user = new User();
        List<User> list = dbUtil.findList(user, sql, new ArrayList());
        for (int i = 0; i < list.size(); i++) {
            user = list.get(i);
            System.out.println(user.toString());
        }
    }*/
}
