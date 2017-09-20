<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wqzhang
  Date: 2017/9/18
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>DataTable Demo 2017年9月18日</title>
    <base href="<%=basePath%>">

    <!--或者下载到本地，下面有下载地址-->
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">

    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="plugins/jquery.js"></script>

    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="plugins/datatables/jquery.dataTables.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#table_example").DataTable({
                "jQueryUI": true,
//                scrollY:400,
                ordering: true,
                "sesarching": false,
                paging: true,
                "orderClasses": true

            });
        });





    </script>

</head>
<body>
<table id="table_example" class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>User Id</th>
        <th>User Name</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${users}" var="user" varStatus="vs">
        <tr>
            <td>${user.ID}</td>
            <td>${user.NAME}</td>
        </tr>

    </c:forEach>


    </tbody>
</table>


</body>
</html>
