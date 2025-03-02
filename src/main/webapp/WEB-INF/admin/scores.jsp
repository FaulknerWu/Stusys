<%--
  Created by IntelliJ IDEA.
  User: Faulk
  Date: 2025/3/2
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>成绩管理</title>
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #b134db;
            --background-color: #f8f9fa;
            --text-color: #333;
            --sidebar-width: 300px;
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

        .container {
            display: flex;
            gap: 2rem;
            max-width: 1400px;
            margin: 2rem auto;
            padding: 0 2rem;
        }

        .alert {
            padding: 1rem;
            margin: 1rem 0;
            border-radius: 4px;
        }

        .success {
            background: #dff0d8;
            color: #3c763d;
            border: 1px solid #d0e9c6;
        }

        .error {
            background: #f2dede;
            color: #a94442;
            border: 1px solid #ebccd1;
        }

        .form-card {
            background: white;
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
        }

        .form-row {
            flex-direction: column;
            gap: 1rem;
        }

        .form-group {
            flex: 1;
        }

        input[type="text"],
        input[type="number"],
        select {
            width: 100%;
            padding: 0.8rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            transition: border-color 0.3s ease;
        }

        .btn {
            background: var(--primary-color);
            color: white;
            padding: 0.8rem 1.5rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .data-table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            margin: 2rem 0;
        }

        .data-table th,
        .data-table td {
            padding: 1.2rem;
            text-align: left;
        }

        .action-link {
            color: var(--primary-color);
            text-decoration: none;
            margin-right: 1rem;
            transition: all 0.3s ease;
        }

        .page-title {
            margin-top: 0;
        }

        .sidebar {
            width: var(--sidebar-width);
            flex-shrink: 0;
        }

        .main-content {
            flex: 1;
            min-width: 0;
        }

        .sidebar-card {
            background: white;
            border-radius: 10px;
            padding: 1.5rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
        }
    </style>
</head>
<body>
<div class="navbar">
    <nav class="navbar-nav">
        <a class="nav-link" href="<c:url value='/admin/dashboard'/>">首页</a>
        <a class="nav-link" href="<c:url value='/admin/courses'/>">课程管理</a>
        <a class="nav-link" href="<c:url value='/admin/students'/>">学生管理</a>
        <a class="nav-link" href="<c:url value='/admin/scores'/>">学生成绩管理</a>
    </nav>
</div>

<div class="container">
    <!-- 侧边栏 -->
    <div class="sidebar">
        <div class="sidebar-card">
            <h2>添加成绩</h2>
            <form action="scores" method="post">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label>学生</label>
                    <select name="studentId" required>
                        <option value="">-- 请选择学生 --</option>
                        <c:forEach items="${students}" var="student">
                            <option value="${student.studentId}">
                                    ${student.name} (${student.studentId})
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>课程ID</label>
                    <input type="text" name="courseId" required>
                </div>
                <div class="form-group">
                    <label>成绩</label>
                    <input type="number" step="0.1" name="score" required>
                </div>
                <button type="submit" class="btn">添加成绩</button>
            </form>
        </div>
    </div>

    <!-- 主内容区 -->
    <div class="main-content">
        <h1 class="page-title">学生成绩管理</h1>

        <c:if test="${not empty success}">
            <div class="alert success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <div class="form-card">
            <h2>选择学生</h2>
            <form method="get" action="scores">
                <div class="form-row">
                    <div class="form-group">
                        <select name="studentId" onchange="this.form.submit()">
                            <option value="">-- 请选择学生 --</option>
                            <c:forEach items="${students}" var="student">
                                <option value="${student.studentId}"
                                    ${selectedStudentId eq student.studentId ? 'selected' : ''}>
                                        ${student.name} (${student.studentId})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <c:if test="${not empty scores}">
            <table class="data-table">
                <thead>
                <tr>
                    <th>课程ID</th>
                    <th>成绩</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${scores}" var="score">
                    <tr>
                        <td>${score.courseId}</td>
                        <td>${score.score}</td>
                        <td>
                            <a href="scores?action=edit&id=${score.id}&studentId=${selectedStudentId}"
                               class="action-link">编辑</a>
                            <a href="scores?action=delete&id=${score.id}"
                               class="action-link"
                               onclick="return confirm('确定删除该成绩吗？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty editScore}">
            <div class="form-card">
                <h2>编辑成绩</h2>
                <form action="scores" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${editScore.id}">
                    <div class="form-row">
                        <div class="form-group">
                            <label>学号</label>
                            <input type="text" value="${editScore.studentId}" disabled>
                        </div>
                        <div class="form-group">
                            <label>课程ID</label>
                            <input type="text" name="courseId" value="${editScore.courseId}" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label>成绩</label>
                            <input type="number" step="0.1" name="score" value="${editScore.score}" required>
                        </div>
                    </div>
                    <button type="submit" class="btn">更新成绩</button>
                </form>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>