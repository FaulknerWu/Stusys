<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Course Management</title>
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

        .container {
            max-width: 1200px;
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
            display: flex;
            gap: 2rem;
            margin-bottom: 1.5rem;
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

        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: var(--primary-color);
            outline: none;
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

        .btn:hover {
            background: var(--secondary-color);
            transform: translateY(-2px);
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
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

        .data-table th {
            background-color: var(--primary-color);
            color: white;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.08em;
        }

        .data-table td {
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
        }

        .data-table tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        .data-table tr:hover {
            background-color: rgba(52, 152, 219, 0.05);
        }

        .action-link {
            color: var(--primary-color);
            text-decoration: none;
            margin-right: 1rem;
            transition: all 0.3s ease;
        }

        .action-link:hover {
            color: var(--secondary-color);
            text-decoration: underline;
        }

        .page-title {
            font-size: 2.5rem;
            color: var(--primary-color);
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 3px solid var(--secondary-color);
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="navbar">
    <nav class="navbar-nav">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">首页</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/courses">课程管理</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/students">学生管理</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/scores">成绩管理</a>
    </nav>
</div>

<div class="container">
    <h1 class="page-title">课程管理</h1>

    <c:if test="${not empty success}">
        <div class="alert success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
    </c:if>

    <c:if test="${not empty editCourse}">
        <div class="form-card">
            <h2>编辑课程</h2>
            <form action="${pageContext.request.contextPath}/admin/courses" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="originalCourseId" value="${editCourse.courseId}">
                <div class="form-row">
                    <div class="form-group">
                        <label>课程ID</label>
                        <input type="text" name="courseId" value="${editCourse.courseId}" required>
                    </div>
                    <div class="form-group">
                        <label>课程名称</label>
                        <input type="text" name="courseName" value="${editCourse.courseName}" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>教师</label>
                        <input type="text" name="teacherName" value="${editCourse.teacherName}" required>
                    </div>
                    <div class="form-group">
                        <label>学分</label>
                        <input type="number" name="credits" value="${editCourse.credits}" required>
                    </div>
                </div>
                <button type="submit" class="btn">更新课程</button>
            </form>
        </div>
    </c:if>

    <div class="form-card">
        <h2>添加课程</h2>
        <form action="${pageContext.request.contextPath}/admin/courses" method="post">
            <input type="hidden" name="action" value="add">
            <div class="form-row">
                <div class="form-group">
                    <label>课程ID</label>
                    <input type="text" name="courseId" required>
                </div>
                <div class="form-group">
                    <label>课程名称</label>
                    <input type="text" name="courseName" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>教师</label>
                    <input type="text" name="teacherName" required>
                </div>
                <div class="form-group">
                    <label>学分</label>
                    <input type="number" name="credits" required>
                </div>
            </div>
            <button type="submit" class="btn">添加课程</button>
        </form>
    </div>
    <%-- Course List --%>
    <table class="data-table">
        <thead>
        <tr>
            <th>课程ID</th>
            <th>课程名称</th>
            <th>教师</th>
            <th>学分</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.courseId}</td>
                <td>${course.courseName}</td>
                <td>${course.teacherName}</td>
                <td>${course.credits}</td>
                <td>
                    <a href="courses?action=edit&courseId=${course.courseId}" class="action-link">编辑</a>
                    <a href="courses?action=delete&courseId=${course.courseId}"
                       class="action-link"
                       onclick="return confirm('Delete this course?')">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>