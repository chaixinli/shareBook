<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录页面</title>
</head>
<body>
    <form action="LoginServlet" method="post" name="login">
        <div class="htmleaf-container">
            <div id="wrapper" class="login-page">
                <div id="login_form" class="form">
                    <form class="register-form">
                        <input type="text" placeholder="用户名" id="user-name"/>
                        <input type="password" placeholder="密码" id="password"/>
                        <input type="password" placeholder="确认密码" id="reset-password"/>
                        <input type="text" placeholder="电子邮件" id="email">
                        <button id="create">创建账户</button>
                        <p></p>
                    </form>
                </div>
            </div>
        </div>
        <table border="1" cellspacing="1" cellpadding="1" bordercolor="silver" align="center">
            <tr>
                <td colspan="2" align="center" bgcolor="#f5f5dc">用户登录</td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="userName"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="text" name="password"/></td>
            </tr>
            <tr>
                <td><a href="/views/register.jsp">新用户注册</a></td>
                <td>
                    <input type="submit" name="submit" onclick="return check(this);"/>
                    <input type="reset" name="reset"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
