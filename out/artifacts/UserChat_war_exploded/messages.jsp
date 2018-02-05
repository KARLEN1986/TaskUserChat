<%@ page import="model.User" %>
<%@ page import="model.Message" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>
<% User user = (User) session.getAttribute("user");%>
Welcome <%=user.getName()%><br><br>
<table border="1">
    <tr>
        <th>Name Surname</th>
        <th>Message</th>
        <th>Time</th>
    </tr>

    <%
        List<Message> messages = (List<Message>) request.getAttribute("userMessages");
        if (messages!=null){
        for (Message message : messages) {
    %>
    <tr>
        <td><%=message.getFromUser().getName()%> <%=message.getFromUser().getSurname()%>
        </td>
        <td><%=message.getMessage()%>
        </td>
        <td><%=message.getTimestamp()%>
        </td>
    </tr>
    <%
        }}
    %>
</table><br><br>

<a href="/home">back</a>

</body>
</html>
