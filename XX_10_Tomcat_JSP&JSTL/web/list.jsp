<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        function deleteUser(id) {
            //用户提示
            if(confirm("确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/delUserServlet?id="+id;
            }
        }
        window.onload = function () {
            document.getElementById("reset").onclick =function(){
                document.getElementsByName("name")[0].setAttribute("value","");
                document.getElementsByName("address")[0].setAttribute("value","");
                document.getElementsByName("email")[0].setAttribute("value","");
            }
            document.getElementById("delSelectsUser").onclick = function () {
                var name = document.getElementsByName("UserId");
                var flag = false;
                for (var i = 0;i<name.length;i++){
                    if (name[i].checked){
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    //有复选框被选中
                    if (confirm("确定要删除这些数据吗？")) {
                        document.getElementById("from_id").submit();//表单提交
                    }
                }
            }
            document.getElementById("chbox").onclick = function () {
                //获取下边列表中的name
                var name = document.getElementsByName("UserId");
                for (var i = 0;i<name.length;i++){
                    //设置这些name[i]的状态等于chbox的状态
                    name[i].checked = this.checked;
                }
            }
        }

    </script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left">
        <form  class = "form-inline" action="${pageContext.request.contextPath}/FindUserBypageServlet" method="post">
            <div class = "form-group">
                <label for = "exampleInputName2">姓名</label>
                <input type = "text" class = "form-control" value="${condition.name[0]}" name="name" id = "exampleInputName2" >
            </div>
            <div class = "form-group">
                <label for = "exampleInputAddress">籍贯</label>
                <input type = "text" class = "form-control" value="${condition.address[0]}" name="address" id = "exampleInputAddress">
            </div>
            <div class = "form-group">
                <label for = "exampleInputEmail2">邮箱</label>
                <input type = "email" class = "form-control" value="${condition.email[0]}" name="email" id = "exampleInputEmail2">
            </div>
            <button type = "submit" class = "btn btn-default">查询</button>
            <button type = "reset" id="reset" class = "btn btn-default">重置</button>
        </form>
    </div>
    <div style="float: right;margin:5px ">
        <a class = "btn btn-primary" href = "${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class = "btn btn-primary" href = "javascript:void(0);" id="delSelectsUser">删除选中</a>
<%--        ${pageContext.request.contextPath}   获取项目名即虚拟目录--%>
<%--        ${pageContext.request.contextPath}/delUserServlet?id=${user.id}--%>
    </div>
    <form id="from_id" action = "${pageContext.request.contextPath}/delSelectUserServlet" method = "post">
        <table border = "1" class = "table table-bordered table-hover">
            <tr class = "success">
                <th><input id="chbox" type = "checkbox"/></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

<%--             <c:forEach var = "user" varStatus = "s" items = "${users}">--%>
                <c:forEach var = "user" varStatus = "s" items = "${pB.list}">
                <tr>
                    <th><input type = "checkbox" name="UserId" value="${user.id}"/></th>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class = "btn btn-default btn-sm"
                           href = "${pageContext.request.contextPath}/FindUserServlet?id=${user.id}">修改</a>&nbsp;<a
                            class = "btn btn-default btn-sm" href = "javascript:deleteUser(${user.id});">删除</a></td>
                </tr>
            </c:forEach>

            <%--<tr>--%>
            <%--    <td colspan="9" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a></td>--%>
            <%--</tr>--%>
        </table>
    </form>
    <div>
        <nav aria-label = "Page navigation">
            <ul class = "pagination">
                <li>
                    <a href = "${pageContext.request.contextPath}/FindUserBypageServlet?currentpage=${pB.currentPage-1}&rows=5&name=${condition.name[0]}&adderss=${condition.adderss[0]}&email=${condition.email[0]}" aria-label = "Previous">
                        <span aria-hidden = "true">&laquo;</span>
                    </a>
                </li>

                <c:choose>
                    <c:when test="${pB.totaPage <= 6}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${pB.totaPage}"/>
                    </c:when>
                    <%--页数超过了6页--%>
                    <c:otherwise>
                        <c:set var="begin" value="${pB.currentPage - 1}"/>
                        <c:set var="end" value="${pB.currentPage + 4}"/>
                        <%--如果begin减1后为0,设置起始页为1,最大页为6--%>
                        <c:if test="${begin -1 <= 0}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="6"/>
                        </c:if>
                        <%--如果end超过最大页,设置起始页=最大页-5--%>
                        <c:if test="${end > pB.totaPage}">
                            <c:set var="begin" value="${pB.totaPage - 5}"/>
                            <c:set var="end" value="${pB.totaPage}"/>
                        </c:if>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${begin}" end="${end}" var="i">
                    <%--<li><a href = "${pageContext.request.contextPath}/FindUserBypageServlet?currentpage=${i}&rows=5">${i}</a></li>--%>
                    <c:if test="${pB.currentPage == i}">
                        <li class="active"><a href = "${pageContext.request.contextPath}/FindUserBypageServlet?currentpage=${i}&rows=5&name=${condition.name[0]}&adderss=${condition.adderss[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>
                    <c:if test="${pB.currentPage != i}">
                        <li><a href = "${pageContext.request.contextPath}/FindUserBypageServlet?currentpage=${i}&rows=5&name=${condition.name[0]}&adderss=${condition.adderss[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href = "${pageContext.request.contextPath}/FindUserBypageServlet?currentpage=${pB.currentPage+1}&rows=5&name=${condition.name[0]}&adderss=${condition.adderss[0]}&email=${condition.email[0]}" aria-label = "Next">
                        <span aria-hidden = "true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px">共${pB.totalCount}条记录，共${pB.totaPage}页</span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
