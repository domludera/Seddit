<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.UserPost" %><%--
  Created by IntelliJ IDEA.
  User: melon
  Date: 2020-11-05
  Time: 3:38 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seddit</title>
</head>
<body>

<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
    }
//    userName = (String) session.getAttribute("username");
    if(userName == null) response.sendRedirect("login");
%>

<h1>Hi <%=userName%>, Welcome to Seddit</h1>

<form action="logout" method="post">
    <input type="submit" value="logout" >
</form>

<form action="PostManager" method="post">
    <input type="text" name="name" value="<%=userName%>" hidden/>
    <input type="text" name="message"/>
    <input type="submit" name="postmessage" value="Post"/>
</form>

<%@ include file="posts.jsp" %>


</body>
</html>
