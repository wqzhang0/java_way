<%--
  Created by IntelliJ IDEA.
  User: wqzhang
  Date: 2017/8/15
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Hello world</title>
    <base href="<%=basePath%>">
</head>
<body>
<h1>主页</h1>

<a href="demoShowController/goDataTablesDemo.do">DataTables Demo</a>
</body>
</html>
