package servlet;

import bean.User;
import util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/querydata")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hot-fix");
        request.setCharacterEncoding("utf-8");
        System.out.println("test4");
        String ids = request.getParameter("id");
        int id = Integer.parseInt(ids);
        String sql = "delete from user where id = ?";
        JdbcUtil.executeUpdate(sql, id);
        response.sendRedirect("/");
    }
}
