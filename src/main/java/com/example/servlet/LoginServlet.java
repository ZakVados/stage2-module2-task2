package com.example.servlet;


import com.example.Users;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users users = Users.getInstance();
        List<String> usersArr = users.getUsers();
        try {
            if (usersArr.contains(req.getParameter("login")) && !req.getParameter("password").isEmpty()) {
                session.setAttribute("user", req.getParameter("login"));
                resp.sendRedirect("/user/hello.jsp");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
