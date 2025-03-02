<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>学生信息</title>
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #b134db;
            --background-color: #f8f9fa;
            --text-color: #333;
        }

        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            background-color: var(--background-color);
            color: var(--text-color);
        }

        .navbar {
            background-color: var(--primary-color);
            padding: 1rem 2rem;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .navbar-nav {
            display: flex;
            gap: 2rem;
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .nav-link {
            color: white;
            text-decoration: none;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            transition: all 0.3s ease;
            position: relative;
        }

        .nav-link:hover {
            background-color: rgba(255, 255, 255, 0.1);
            transform: translateY(-2px);
        }

        .nav-link::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 50%;
            width: 0;
            height: 2px;
            background: var(--secondary-color);
            transition: all 0.3s ease;
        }

        .nav-link:hover::after {
            width: 100%;
            left: 0;
        }

        .content {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 2rem;
        }

        .welcome {
            font-size: 2.5rem;
            color: var(--primary-color);
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 3px solid var(--secondary-color);
            display: inline-block;
        }

        .info-card {
            background: white;
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
        }

        .info-item {
            display: flex;
            align-items: center;
            margin-bottom: 1.5rem;
            font-size: 1.1rem;
        }

        .info-item label {
            width: 120px;
            color: var(--primary-color);
            font-weight: 500;
        }

        .info-item span {
            color: var(--text-color);
            padding-left: 1rem;
        }
    </style>
</head>
<body>
<div class="navbar">
    <nav class="navbar-nav">
        <a class="nav-link" href="<c:url value="/user/dashboard"/>">首页</a>
        <a class="nav-link" href="<c:url value='/user/scores'/>">成绩查询</a>
        <a class="nav-link" href="<c:url value="/user/info"/>">学生信息</a>
    </nav>
</div>

<div class="content">
    <h1 class="welcome"><c:out value="${userName}"/></h1>
    <c:if test="${not empty errorMessage}">
        <div class="error-message"
             style="color: #dc3545; background: #f8d7da; padding: 1rem; border-radius: 4px; margin: 1rem 0;">
                ${errorMessage}
        </div>
    </c:if>
    <c:if test="${empty errorMessage}">
        <div class="info-card">
            <div class="info-item">
                <label>学号</label>
                <span><c:out value="${user.studentId}"/></span>
            </div>
            <div class="info-item">
                <label>性别</label>
                <span><c:out value="${user.gender}"/></span>
            </div>
            <div class="info-item">
                <label>年龄</label>
                <span><c:out value="${user.age}"/></span>
            </div>
            <div class="info-item">
                <label>班级</label>
                <span><c:out value="${user.className}"/></span>
            </div>
            <div class="info-item">
                <label>手机号</label>
                <span><c:out value="${user.phone}"/></span>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>