package servlet;

import bean.User;
import util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/queyAlluser")
public class QueryAllUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "select * from user";
        List<Map<String, Object>> mapList = JdbcUtil.executeQuery(sql);

        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i); //{'username':'zhansan','password':'134',...}
        }
        for (Map<String, Object> map : mapList) {
            int id = (int) map.get("id");
            String username = (String)map.get("username");
            String password = (String)map.get("password");
            String sex = (String)map.get("sex");
            String hobby = (String)map.get("hobby");
            String info = (String)map.get("info");
            String city = (String)map.get("city");
            User user = new User(id, username, password, sex, hobby, info, city);
            users.add(user);
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("showuser.jsp").forward(request, response);//
    }
}
