<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "cn.Web.domain.User" %>
<%@ page import = "java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Bad Body
  Date: 2020/7/14
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType = "text/html;charset=UTF-8" language = "java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%
            List list = new ArrayList();
            list.add(new User("张三",23,new Date()));
            list.add(new User("李四",24,new Date()));
            list.add(new User("王五",21,new Date()));
            request.setAttribute("list",list);
        %>
        <table border="1" width="500" align="center">
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>生日</th>
            </tr>
            <c:forEach items="${list}" var="user" varStatus="s">
                <c:if test="${s.count % 2 == 0}">
                    <tr bgcolor="#ff7f50">
                        <th>${s.count}</th>
                        <th>${user.name}</th>
                        <th>${user.age}</th>
                        <th>${user.birStr}</th>
                    </tr>
                </c:if>
                <c:if test="${s.count % 2 != 0}">
                    <tr bgcolor="aqua">
                        <th>${s.count}</th>
                        <th>${user.name}</th>
                        <th>${user.age}</th>
                        <th>${user.birStr}</th>
                    </tr>
                </c:if>

            </c:forEach>
        </table>
    </body>
</html>
