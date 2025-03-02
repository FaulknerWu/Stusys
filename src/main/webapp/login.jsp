<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
  <title>用户登录</title>
</head>
<body>
<form action="login" method="post">
  <label for="username">用户名:</label>
  <input type="text" id="username" name="username" placeholder="用户名" required><br>

  <label for="password">密码:</label>
  <input type="password" id="password" name="password" placeholder="密码" required><br>

  <button type="submit">登录</button>
</form>
<c:if test="${not empty errorMessage}">
  <div style="color: red;">${errorMessage}</div>
</c:if>
</body>
</html>