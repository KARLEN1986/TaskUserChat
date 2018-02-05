<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) session.getAttribute("user");

  if (user != null) {
    response.sendRedirect("/home.jsp");
  } else {

%>

<html>
<head>
  <title>Login</title>
</head>
<body>
<%
  if (session.getAttribute("message") != null) { %>
<%=  session.getAttribute("message") %>
<%
    session.removeAttribute("message");
  }
%>

<form method="post" action="/login">
  email: <input type="text" name="email"/><br><br>
  password: <input type="password" name="password"/><br>

  <input type="submit" value="login"/>
</form>
<a href="/register.jsp">register</a>
</body>
</html>
<%
  }
%>
