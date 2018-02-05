<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<% User user = (User) session.getAttribute("user");%>

Welcome <%=user.getName()%> | <a href="/logout">logout</a><br><br>
<a href="/messages">view all messages</a><br><br>

<div style="width: auto">Friend List:
    <% List<User> friends = (List<User>) request.getAttribute("userFriends"); %>
    <table width="800px" border="1">
        <tr>
            <th>image</th>
            <th>name</th>
            <th>surname</th>
            <th>send message</th>
            <th>delete</th>
            <% if (friends != null && friends.size() > 0) {%>
            <% for(User friend : friends) { %>
        </tr>

        <tr>
            <td>
                <% if (friend.getProfilePic() != null) { %>
                <img src="/getImage?fileName=<%=friend.getProfilePic() %>" width="60"/>
                <%}%>

            </td>
            <td><%= friend.getName() %></td>
            <td><%= friend.getSurname() %></td>
            <td><a href="sendMessage.jsp?toId=<%=friend.getId()%>&name=<%=friend.getName()%>&surname=<%=friend.getSurname()%>">sendMessage</a>
            </td>
            <td><a href="/deleteFriend?friendId=<%=friend.getId()%>">delete</a></td>
        </tr>
           <%}%>

        <% } %>
    </table>
</div>



ՙ<div style="width: auto">All users
    <% List<User> users = (List<User>) request.getAttribute("users"); %>
    <table width="900px" border="">
        <tr>
            <th>image</th>
            <th>name</th>
            <th>surname</th>
            <th>age</th>

        </tr>

        <tr>
            <% for(User user1 : users) { %>
            <td>
                <% if (user1.getProfilePic() != null) { %>
                <img src="/getImage?fileName=<%=user1.getProfilePic()%>"width="60"/>
                <%}%>

            </td>
            <td><%= user1.getName() %></td>
            <td><%= user1.getSurname() %></td>
            <td><%= user1.getAge() %></td>
            <td><a href="/addFriendServlet?friendId=<%=user1.getId()%>">Add to friends list </a></td>
        </tr>
        <% } %>
    </table>
    <br><br>

    <table width="800px" border="">
        <tr>
            <th>image</th>
            <th>name</th>
            <th>surname</th>
            <th>age</th>
            <th>added</th>
            <% for(User friend : friends) { %>
        </tr>

        <tr>
            <td>
                <% if (friend.getProfilePic() != null) { %>
                <img src="/getImage?fileName=<%=friend.getProfilePic()%>"width="60"/>
                <%}%>

            </td>
            <td><%= friend.getName() %></td>
            <td><%= friend.getSurname() %></td>
            <td><%= friend.getAge() %></td>
            <td><%= "added"%></td>
        </tr>
        <% } %>
    </table>

</div>
</body>
</html>
