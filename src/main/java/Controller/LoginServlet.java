package Controller;

import Dao.UserDao;
import Models.Login;
import Models.Sanpham;
import Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet (urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Login.user != null) {
            if (Login.user.getChucvu().equals("admin")) {
                resp.sendRedirect("/admin.jsp");
            } else {
                resp.sendRedirect("/menu.jsp");
            }
        }else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("pass");

        User user = userDao.getUser(username, password);
        if (user != null) {

            Login.user = user;
            if (user.getChucvu().equals("admin")) {
                resp.sendRedirect("/admin.jsp");
            } else {
                resp.sendRedirect("/menu.jsp");
            }
        }else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }
    }







}
