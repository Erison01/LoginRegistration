<%--
  Created by IntelliJ IDEA.
  User: Dell Latitude E7450
  Date: 6/21/2023
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Title</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="d-flex justify-content-between align-items-center m-4">
<h1>Welcome ${user.userName}!</h1>
  <a href="/logout">Logout</a>
</div>
</body>
</html>
