package Controller;

import Dao.UserDao;
import Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServelet extends HttpServlet {
    UserDao userDao = new UserDao();
    User user = null;
    RequestDispatcher dispatcher;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String tk = req.getParameter("tk");
        String mk = req.getParameter("mk");
        String chucvu = req.getParameter("chucvu");
        String sdt = req.getParameter("sdt");
        String ten = req.getParameter("ten");
        String gioitinh = req.getParameter("gioitinh");
        String diachi = req.getParameter("diachi");
        user = new User(tk, mk, chucvu, sdt, ten, gioitinh, diachi);
        if (userDao.create(user)) {
            req.setAttribute("message", "Account created successfully, please login");
            dispatcher = req.getRequestDispatcher("userView/register.jsp");
            dispatcher.forward(req, resp);
        }else {
            req.setAttribute("message", "Account creation failed, please try again");
            dispatcher = req.getRequestDispatcher("userView/register.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher = req.getRequestDispatcher("userView/register.jsp");
        dispatcher.forward(req, resp);
    }

}
