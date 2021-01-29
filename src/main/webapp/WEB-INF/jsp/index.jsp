<%--
  Created by IntelliJ IDEA.
  Date: 2020-02-23
  Time: 14:27
  主要用于页面的跳转
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="refresh" content="0;url=${pageContext.request.contextPath }/platform/login.html">
    <title>Title</title>
</head>
<body>
<%--hello world1!--%>
</body>
</html>
