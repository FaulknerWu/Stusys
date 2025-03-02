<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Student System</title>
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #3498db;
            --background-color: #f8f9fa;
            --text-color: #333;
        }

        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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

        .author-footer {
            margin-top: 4rem;
            padding: 1.5rem 0;
            border-top: 1px solid rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center;
            gap: 1rem;
            color: var(--primary-color);
        }

        .author-badge {
            background: var(--secondary-color);
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-size: 0.9rem;
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="navbar">
    <nav class="navbar-nav">
        <a class="nav-link" href="${pageContext.request.contextPath}/user/dashboard">首页</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/user/scores">成绩查询</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/user/info">">学生信息</a>
    </nav>
</div>
<div class="content">
    <h1 class="welcome">欢迎进入简易学生管理系统</h1>
    <p>当前账户无管理员权限</p>
</div>
<div class="content">
    <div class="author-footer">
        <div class="author-badge">
            <span>HUTB 计科2302 WWF</span>
        </div>
        <div class="contact-info">
            <small>faulkner_wu@outlook.com</small>
        </div>
    </div>
</div>
</body>
</html>