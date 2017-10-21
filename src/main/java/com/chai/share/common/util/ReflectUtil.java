package com.chai.share.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取字段名称类
 * Created by chaixinli on 2017/10/21.
 */
public class ReflectUtil {

    public static List<String> getKeys(Class<?> clazz) {
        List<String> list = new ArrayList<String>();
        try {
            //根据Class的静态方法获取所有字段名称、不包括继承字段
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                list.add(fields[i].getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
