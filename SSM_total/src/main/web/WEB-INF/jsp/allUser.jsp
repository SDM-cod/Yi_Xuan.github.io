<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2022/11/8
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>用户列表</small>
                    </h1>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a href="${pageContext.request.contextPath}/user/addUser">新增用户</a>
            </div>
            <div class="col-md-4 column">
                <form action="${pageContext.request.contextPath}/user/queryUser" method="get" style="float: right">
                    <input type="text" placeholder="请输入查询用户" name="queryUser" id="search" class="form-control" onblur="getU()">
                    <input type="submit" value="查询" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>用户编号</th>
                        <th>用户姓名</th>
                        <th>用户密码</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.pwd}</td>
                            <td><!--对应两个请求-->
                                <a href="${pageContext.request.contextPath}/user/toUpdateUserPaper/${user.id}">修改</a>&nbsp;|&nbsp;
                                <a href="${pageContext.request.contextPath}/user/deleteUser/${user.id}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script>
        function getU() {
            $.post({
                url:"${pageContext.request.contextPath}/user/queryUserLike",
                //控制器方法中的参数从data中获取数据，参数名称要和data内的key一致
                data:{"name":$("#search").val()},
                //回调函数，将控制器返回的数据通过回调函数处理后显示
                success:function (data) {
                    console.log(data);
                },
                error:function () {
                    console.log("请求错误");
                }
            })
        }

    </script>
</body>
</html>
