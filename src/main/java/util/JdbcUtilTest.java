package util;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JdbcUtilTest {

    @Test
    public void executeUpdate() {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from user");
        System.out.println("maps = " + maps);
    }

    @Test
    public void executeQuery() {
    }
}