package servlet;

import util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/adduser")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String sex = request.getParameter("sex");
        String[] hobbies = request.getParameterValues("hobby");
        String hobby = "";
        for (int i = 0; i < hobbies.length; i++) {
            hobby += hobbies[i] + ",";
        }
        String info = request.getParameter("info");
        String city = request.getParameter("city");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("password2 = " + password2);
        System.out.println("sex = " + sex);
        System.out.println("hobby = " + hobby);
        System.out.println("info = " + info);
        System.out.println("city = " + city);
        String sql = "insert into user (username,password,sex,hobby,info,city) values(?,?,?,?,?,?);";
        JdbcUtil.executeUpdate(sql, username, password, sex, hobby, info, city);
        response.sendRedirect("queyAlluser"); //重定向到queryAlluser路径
    }
}
