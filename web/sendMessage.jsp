<%@ page import="model.User" %>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Message</title>
</head>
<h1>Send Message</h1>
<form action="/sendMessage" method="post">
    <input type="hidden" name="toId" value="<%=request.getParameter("toId")%>">
    <br><br>to: <span><%=request.getParameter("name")%> <%=request.getParameter("surname")%></span><br><br>
    <textarea name="message"></textarea><br><br>
    <input type="submit" value="send">
</form>

</body>
</html>
