<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2022/11/9
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户数据</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    修改用户数据
                </h1>
            </div>
        </div>
    </div>
</div>
<form action="${pageContext.request.contextPath}/user/UpdateUser" method="post">
    <!--input标签中设置name属性对应实体类的属性，名称要一致！-->
    <div class="form-group">
        <label for="userId">用户编号</label>
        <input type="password" class="form-control"  id="userId" name="id" value="${user.id}" readonly="readonly">
    </div>
    <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" class="form-control"  id="username" name="name" value="${user.name}">
    </div>
    <div class="form-group">
        <label for="userpwd">密码</label>
        <input type="password" class="form-control"  id="userpwd" name="pwd" value="${user.pwd}">
    </div>
    <div class="form-group">
        <input type="submit" value="修改">
    </div>
    <a href="${pageContext.request.contextPath}/user/all">返回</a>

</form>

</body>
</html>
