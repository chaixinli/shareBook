package com.chai.share.common.util;

/**
 * Created by chaixinli on 2017/10/24.
 */
public enum UserUtil {
    id("ID", 1),
    userName("USER_NAME", 2),
    userPassword("USER_PASSWORD", 3),
    email("EMAIL", 4),
    birthday("BIRTHDAY", 5),
    createTime("CREATE_TIME", 6),
    updateTime("UPDATE_TIME", 7);

    private String columnName;
    private int index;

    UserUtil(String columnName, int index) {
        this.columnName = columnName;
        this.index = index;
    }

    public static String getColumnName(int index) {
        for (UserUtil userUtil : UserUtil.values()) {
            if (userUtil.getIndex() == index) {
                return userUtil.columnName;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
