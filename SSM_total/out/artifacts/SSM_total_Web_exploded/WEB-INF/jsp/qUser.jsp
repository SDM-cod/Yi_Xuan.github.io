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
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    该用户信息如下
                </h1>
            </div>
        </div>
    </div>
</div>
<form action="${pageContext.request.contextPath}/user/addUserOp" method="post">
    <!--input标签中设置name属性对应实体类的属性，名称要一致！-->
    <div class="form-group">
        <label for="userId">用户编号</label>
        <input type="text" class="form-control" id="userId" disabled="disabled" value="${qUser.id}">
    </div>
    <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" class="form-control" id="username" disabled="disabled" value="${qUser.name}">
    </div>
    <div class="form-group">
        <label for="userpwd">用户密码</label>
        <input type="text" class="form-control" id="userpwd" disabled="disabled" value="${qUser.pwd}">
    </div>
    <a href="${pageContext.request.contextPath}/user/all">返回</a>


</form>

</body>
</html>
