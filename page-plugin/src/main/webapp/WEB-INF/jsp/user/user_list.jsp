<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  user: wqzhang
  Date: 2017/9/20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <script type="text/javascript" src="plugins/jquery.js"/>
    <script type="text/javascript">

        function goTo(targetPage) {
//            targetPage -1 上一页
//            targetPage -2 下一页
            var currentPage = $("#currentPage").val();
            var totalPage = $("#totalPage").val();
            if (targetPage == -1) {
                if (currentPageIndex > 0) {
                    currentPageIndex = currentPageIndex - 1;
                }
            } else if (targetPage == -2) {
                if (currentPageIndex < totalPage - 1) {
                    currentPageIndex = currentPageIndex + 1;
                }
            } else {
                if (targetPage < 1) {
                    currentPageIndex = 0;
                }
                if (targetPage > totalPage + 1) {
                    currentPageIndex = 0;
                }
            }
        }

        function doGoTo(targetPage) {
            $("Form").submit();
        }
    </script>
</head>
<body>
<form action="/userListController/list.do">
    <table>
        <thead>
        <tr>
            <th>User Id</th>
            <th>User Name</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="user" varStatus="vs">
            <tr>
                <td>${user.ID}</td>
                <td>${user.NAME}</td>
            </tr>
        </c:forEach>
        </tbody>


    </table>

    <table>
        <lable>当前页</lable>
        ${page.currentPage}
        <lable>显示数目</lable>
        ${page.showCount}
        <lable>总页数</lable>
        ${page.totalPage}
        <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}">
        <input type="hidden" name="showCount" id="showCount" value="${page.showCount}">
        <input type="hidden" name="totalPage" id="totalPage" value="${page.totalPage}">

        <a href="#" onclick="goTo(-1)">下一页</a>
        <a href="#" onclick="goTo(-2)">上一页</a>
    </table>
</form>

</body>
</html>
