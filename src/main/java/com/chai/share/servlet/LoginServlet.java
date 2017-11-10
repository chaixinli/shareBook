package com.chai.share.servlet;

import com.chai.share.common.domain.User;
import com.chai.share.common.util.DBUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chaixinli on 2017/10/21.
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 0;




    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        //设置HTTP响应的文档类型,此处为Text/html,如果更改为application\msword则设置为word文档格式
        response.setContentType("text/html");
        //设置响应所采用的编码方式
        response.setCharacterEncoding("utf-8");

        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("password");

        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPwd);



    }
}
