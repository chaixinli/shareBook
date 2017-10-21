package com.chai.share.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public T findOne(Object objClass, String sql, List params) throws SQLException {
        Class clazz = objClass.getClass();
        try {
            this.connection = dbConn.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                if (params.get(i) != null) {
                    Object obj = params.get(i);
                    if (obj.getClass().getName().equals("java.lang.String")) {
                        preparedStatement.setString(i + 1, obj.toString());
                    } else if (obj.getClass().getName().equals("java.lang.Integer")) {
                        preparedStatement.setInt(i + 1, Integer.valueOf(obj.toString()));
                    } else {
                        preparedStatement.setObject(i + 1, obj);
                    }
                }
            }
            resultSet = preparedStatement.executeQuery();
            Class type = null;  //属性类型
            if (resultSet.next()) {
                objClass = clazz.newInstance();//创建一个实例
                List<String> list = ReflectUtil.getKeys(clazz);//获取所有的字段名称
                Method method = null;//声明Method对象

                for (int i = 0; i < list.size(); i++) {
                    String key = list.get(i);
                    String methodName = getSetMethodName(key); //组装set方法名称
                    String typeName = clazz.getDeclaredField(key).getType().getName();//获取字段类型名称
                    if (typeName.equals("int")) {
                        type = int.class;
                        method = clazz.getMethod(methodName, type);
                        method.invoke(objClass, resultSet.getInt(key));
                    } else if (typeName.equals("java.lang.String")) {
                        type = java.lang.String.class;
                        method = clazz.getMethod(methodName, type);
                        method.invoke(objClass, resultSet.getShort(key));
                    }
                }
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
            dbConn.close(resultSet);
        }
        return (T) objClass;
    }

    public List<T> findList(Object objClass, String sql, List params) throws SQLException {
        List<Object> result = new ArrayList<Object>();//创建返回值对象
        try {
            Class clazz = objClass.getClass();
            connection = dbConn.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                if (params.get(i) != null) {
                    Object obj = params.get(i);
                     /*
                     * 判断参数的原始类型
                     * 暂时判断Integer跟String类型
                     */
                    if (obj.getClass().getName().equals("java.lang.String")) {
                        preparedStatement.setString(i + 1, obj.toString());
                    } else if (obj.getClass().getName().equals("java.lang.Integer")) {
                        preparedStatement.setInt(i + 1, Integer.valueOf(obj.toString()));
                    } else {
                        preparedStatement.setObject(i + 1, obj);
                    }
                }
            }
            resultSet = preparedStatement.executeQuery();
            Class type = null;  //属性类型
            while (resultSet.next()) {
                objClass = clazz.newInstance();//创建一个实例
                List<String> list = ReflectUtil.getKeys(clazz); //获取所有的字段名称
                Method method = null;   //声明Method对象

                for (int i = 0; i < list.size(); i++) {
                    String key = list.get(i);
                    String methodName = getSetMethodName(key);  //组装set方法名称
                    String typeName = clazz.getDeclaredField(key).getType().getName();  //获取字段类型名称

                    if (typeName.equals("int")) {
                        type = int.class;   //赋值属性类型
                        method = clazz.getMethod(methodName, type);//获得Method实例
                        method.invoke(objClass, resultSet.getString(key));//调用该set方法
                    } else if (type.equals("java.lang.String")) {
                        type = java.lang.String.class;
                        method = clazz.getMethod(methodName, type);
                        method.invoke(objClass, resultSet.getString(key));
                    }
                }
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
            dbConn.close(resultSet);
        }
        return (List<T>) result;
    }

    /**
     * 组装set方法
     * @param columnName 字段名
     * @return
     */
    private static String getSetMethodName(String columnName) {
        return "set" + columnName.substring(0, 1).toUpperCase()
                + columnName.toLowerCase().substring(1);
    }
}
