<%--
  Created by IntelliJ IDEA.
  User: Faulk
  Date: 2025/3/2
  Time: 12:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生管理</title>
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
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
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
            background-color: rgba(255,255,255,0.1);
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
            border-top: 1px solid rgba(0,0,0,0.1);
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
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
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
        input[type="number"]:focus,
        select:focus {
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
        <a class="nav-link" href="<c:url value='/admin/dashboard'/>">首页</a>
        <a class="nav-link" href="<c:url value='/admin/courses'/>">课程管理</a>
        <a class="nav-link" href="<c:url value='/admin/students'/>">学生管理</a>
        <a class="nav-link" href="<c:url value='/admin/scores'/>">学生成绩管理</a>
    </nav>
</div>
<div class="container">
    <h1 class="page-title">学生信息管理</h1>

    <c:if test="${not empty success}">
        <div class="alert success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
    </c:if>

    <c:if test="${not empty editStudent}">
        <div class="form-card">
            <h2>编辑学生信息</h2>
            <form action="students" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${editStudent.id}">
                <div class="form-row">
                    <div class="form-group">
                        <label>学号</label>
                        <input type="text" value="${editStudent.studentId}" disabled>
                    </div>
                    <div class="form-group">
                        <label>班级</label>
                        <input type="text" name="className" value="${editStudent.className}" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>姓名</label>
                        <input type="text" name="name" value="${editStudent.name}" required>
                    </div>
                    <div class="form-group">
                        <label>年龄</label>
                        <input type="number" name="age" value="${editStudent.age}" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>性别</label>
                        <select name="gender" required>
                            <option value="男" ${editStudent.gender == '男' ? 'selected' : ''}>男</option>
                            <option value="女" ${editStudent.gender == '女' ? 'selected' : ''}>女</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>电话</label>
                        <input type="text" name="phone" value="${editStudent.phone}" required>
                    </div>
                </div>
                <button type="submit" class="btn">更新信息</button>
            </form>
        </div>
    </c:if>

    <div class="form-card">
        <h2>添加新学生</h2>
        <form action="students" method="post">
            <input type="hidden" name="action" value="add">
            <div class="form-row">
                <div class="form-group">
                    <label>学号</label>
                    <input type="text" name="studentId" required>
                </div>
                <div class="form-group">
                    <label>班级</label>
                    <input type="text" name="className" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" name="name" required>
                </div>
                <div class="form-group">
                    <label>年龄</label>
                    <input type="number" name="age" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>性别</label>
                    <select name="gender" required>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>电话</label>
                    <input type="text" name="phone" required>
                </div>
            </div>
            <button type="submit" class="btn">添加学生</button>
        </form>
    </div>

    <table class="data-table">
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>班级</th>
            <th>年龄</th>
            <th>性别</th>
            <th>电话</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.studentId}</td>
                <td>${student.name}</td>
                <td>${student.className}</td>
                <td>${student.age}</td>
                <td>${student.gender}</td>
                <td>${student.phone}</td>
                <td>
                    <a href="students?action=edit&id=${student.id}" class="action-link">编辑</a>
                    <a href="students?action=delete&id=${student.id}"
                       class="action-link"
                       onclick="return confirm('确定要删除该学生吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>