package com.chai.share.dao;


import com.alibaba.fastjson.JSON;
import com.chai.share.common.domain.User;
import com.chai.share.common.util.DBUtil;
import com.chai.share.common.util.UserUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

/**
 * Created by chaixinli on 2017/10/20.
 */
public class UserDao {

    private DBUtil dbUtil = new DBUtil();


    /**
     * 添加用户
     * @param user
     * @return
     */
    int createUser(User user) {
        List<Object> params = Lists.newArrayList();
        StringBuffer sql = new StringBuffer("insert into user(");
        sql.append(UserUtil.userName.getColumnName() + ",");
        sql.append(UserUtil.userPassword.getColumnName() + ",");
        sql.append(UserUtil.email.getColumnName() + ",");
        sql.append(UserUtil.birthday.getColumnName() + ")");
        sql.append("values(?,?,?,?)");
        params.add(user.getUserName());
        params.add(user.getUserPassword());
        params.add(user.getEmail());
        params.add(user.getBirthday());
        dbUtil.updateTable(sql.toString(), params);
        return 0;
    }

    /**
     * 查找符合条件的用户
     * @param map
     * @return
     */
    List<User> queryUser(Map<Object, Object> map) {
        StringBuffer sql = new StringBuffer("select * from `user` where 1 = 1");
        List<Object> params = this.getRules(map, sql);
        List<User> list = dbUtil.findList(new User(), sql.toString(), params);
        return list;
    }

    /**
     * 获取条件
     * @param map
     * @param sql
     * @return
     */
    private List getRules(Map<Object, Object> map, StringBuffer sql) {
        List<Object> params = Lists.newArrayList();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            String fieldName = (String) entry.getKey();
            Object value = entry.setValue(fieldName);
            if (fieldName != null && fieldName.equals("userName")) {
                sql.append(" and " + UserUtil.userName.getColumnName() + "= ?");
            } else if (fieldName != null && fieldName.equals("userPassword")) {
                sql.append(" and " + UserUtil.userPassword.getColumnName() + "= ?");
            } else if (fieldName != null && fieldName.equals("email")) {
                sql.append(" and " + UserUtil.email.getColumnName() + "= ?");
            } else if (fieldName != null && fieldName.equals("birthday")) {
                sql.append(" and " + UserUtil.birthday.getColumnName() + "= ?");
            } else if (fieldName != null && fieldName.equals("createTime")) {
                sql.append(" and " + UserUtil.createTime.getColumnName() + "= ?");
            } else if (fieldName != null && fieldName.equals("updateTime")) {
                sql.append(" and " + UserUtil.updateTime.getColumnName() + "= ?");
            }
            params.add(value);
        }
        return params;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        Map<Object, Object> map = Maps.newHashMap();
        map.put("userName", "admin");
        map.put("userPassword", "123456");
        List<User> list = userDao.queryUser(map);
        for (User user : list) {
            System.out.println(JSON.toJSONString(user));
        }
    }
}
