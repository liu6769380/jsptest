package util;


import java.sql.*;
import java.util.*;

public class JdbcUtil {
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/user?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    static String username = "root";
    static String password = "123456";


    public static Connection getConnection() {

        Connection conn = null;
        try {
            Class.forName(driver); //加载驱动类
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int executeUpdate(String sql, Object... params) {
        System.out.println("params = " + params.length);
        Connection conn = getConnection();
        Statement st = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, null);
        }
        return rows;
    }

    /**
     * 执行查询操作
     *
     * @param sql    要执行的查询sql语句
     * @param params SQL语句预编译参数（如无可省略）
     * @return list 结果集，每一条结果为所有查询的字段名和字段值为键值对的Map集合
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet set = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ps = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            set = ps.executeQuery();
            ResultSetMetaData rsmd = set.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (set.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnName(i), set.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, set);
        }
       // [{'username':'zhansan','password':'134',...},{'username':'zhansan','password':'134',...},,]
        return list;
    }

    /**
     * 释放资源
     *
     * @param conn Connection对象
     * @param ps   PreparedStatement对象
     * @param rs   ResultSet对象
     * @throws SQLException
     */
    private static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
