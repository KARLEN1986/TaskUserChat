
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
ՙՙ<form method="post" action="/register" enctype="multipart/form-data">
    name:<input type="text" name="name"/><br><br>
    surname:<input type="text" name="surname"/><br><br>
    age:<input type="number" name="age"/><br><br>
    email:<input type="text" name="email"/><br><br>
    password:<input type="text" name="password"/><br><br>
    profile pic: <input type="file" name="profilePic"><br><br>


    <input type="submit" value="submit"/>
</form>
<a href="/index.jsp">back</a>
</body>
</html>
