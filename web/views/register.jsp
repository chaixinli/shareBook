<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <jsp:include page="../js/base.js"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>登录页面</title>
    </head>
    <body>
        <form action="RegisterServlet" method="post" name="register">
            <table border="1" width="200">
                <tr>
                    <td colspan="2" align="center" bgcolor="#f5f5dc">用户注册</td>
                </tr>
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" name="userName" size="10"/></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input type="text" name="password" size="10"/></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input type="text" name="password1" size="10"/></td>
                </tr>
                <tr>
                    <td><a href="">新用户注册</a></td>
                </tr>
            </table>
        </form>
    </body>
</html>