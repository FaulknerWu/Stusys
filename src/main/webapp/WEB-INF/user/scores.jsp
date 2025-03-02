<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>学生信息</title>
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34dbdb;
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

        .content table {
            width: 100%;
            border-collapse: collapse;
            margin: 2rem 0;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .content th,
        .content td {
            padding: 1.2rem 1rem;
            text-align: center;
            transition: all 0.2s ease;
        }

        .content th {
            background-color: var(--primary-color);
            color: white;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.08em;
        }

        .content td {
            color: var(--text-color);
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
        }

        .content tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        .content tr:hover {
            background-color: rgba(52, 152, 219, 0.05);
            transform: scale(1.002);
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
        }

        .content tr:last-child td {
            border-bottom: none;
        }

        .summary {
            font-size: 1.2rem;
            color: var(--primary-color);
            margin: 1.5rem 0;
            padding: 1rem;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
        }

        .summary p {
            margin: 0;
            font-weight: 500;
        }

        .data-highlight {
            display: flex;
            align-items: center;
            gap: 2rem;
            margin-bottom: 3rem;
        }

        .data-item {
            flex: 1;
            background: white;
            padding: 1.5rem;
            border-radius: 8px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.06);
            transition: transform 0.3s ease;
        }

        .data-item:hover {
            transform: translateY(-5px);
        }

        .data-label {
            color: var(--primary-color);
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 0.5rem;
        }

        .data-value {
            font-size: 2rem;
            color: var(--secondary-color);
            font-weight: 700;
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
    <div class="welcome">学习概览：<c:out value='${userName}'/></div>
    <c:if test="${not empty errorMessage}">
        <div class="error-message"
             style="color: #dc3545; background: #f8d7da; padding: 1rem; border-radius: 4px; margin: 1rem 0;">
                ${errorMessage}
        </div>
    </c:if>
    <c:if test="${empty errorMessage}">
        <c:set var="total" value="0" scope="page"/>
        <table>
            <tr>
                <th>课程ID</th>
                <th>课程名称</th>
                <th>成绩</th>
                <th>学分</th>
            </tr>
            <c:forEach var="score" items="${scores}" varStatus="status">
                <tr>
                    <td><c:out value="${score.courseId}"/></td>
                    <td><c:out value="${courses[status.index].courseName}"/></td>
                    <td><c:out value="${score.score}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${score.score >= 60}">
                                <c:set var="total" value="${total + courses[status.index].credits}" scope="page"/>
                                <c:set var="completedCourses" value="${completedCourses + 1}" scope="page"/>
                                <c:out value="${courses[status.index].credits}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="failedCourses" value="${failedCourses + 1}" scope="page"/>
                                0
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="data-highlight">
            <div class="data-item">
                <div class="data-label">学分进度</div>
                <div class="data-value"><c:out value="${total}"/>/128</div>
            </div>
            <div class="data-item">
                <div class="data-label">已修过课程数</div>
                <div class="data-value"><c:out value="${completedCourses}"/></div>
            </div>
            <div class="data-item">
                <div class="data-label">挂科数</div>
                <div class="data-value"><c:out value="${failedCourses}"/></div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>