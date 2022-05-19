<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--el表达式 --%>
<%@ page isELIgnored="false" %>
<html>
<head>
</head>
<body>
<table border="1px">
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>性别</th>
        <th>爱好</th>
        <th>个人简介</th>
        <th>城市</th>
        <td>操作</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.sex}</td>
            <td>${user.hobby}</td>
            <td>${user.info}</td>
            <td>${user.city}</td>
            <td>
                <a href="/jsptest/deleteuser?id=${user.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
