package fun.faulkner.stusys.controller;

import fun.faulkner.stusys.dao.UserDaoImpl;
import fun.faulkner.stusys.entity.User;
import fun.faulkner.stusys.service.UserService;
import fun.faulkner.stusys.service.UserServiceImpl;
import fun.faulkner.stusys.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.getUserByUsername(username);

        if (user != null && PasswordUtil.verifyPassword(password, user.getPasswordHash())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin/dashboard");
            } else {
                response.sendRedirect("user/dashboard");
            }
        } else {
            request.setAttribute("errorMessage", "无效的用户名或密码");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}