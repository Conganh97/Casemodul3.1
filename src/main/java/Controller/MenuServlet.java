package Controller;

import Dao.UserDao;
import Models.Login;
import Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

@WebServlet (urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    RequestDispatcher dispatcher;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "editUser":
                    showEditFormUser(req, resp);
                    break;
                case "backmenu":
                    dispatcher = req.getRequestDispatcher("/menu.jsp");
                    dispatcher.forward(req, resp);
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editUser":
                editUser(req, resp);
                break;

        }
    }

    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        User existingStudent = Login.user;
        dispatcher = request.getRequestDispatcher("userView/editUser.jsp");
        request.setAttribute("user", existingStudent);
        dispatcher.forward(request, response);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int iduser = Login.user.getIduser();
        String mk = request.getParameter("mk");
        String sdt = request.getParameter("sdt");
        String ten = request.getParameter("ten");
        String gioitinh = request.getParameter("gioitinh");
        String diachi = request.getParameter("diachi");
        User user = new User(iduser, mk, sdt, ten, gioitinh, diachi);

        if (userDao.edit(iduser,user)) {
            User existingStudent = Login.user;
            request.setAttribute("user", existingStudent);
            request.setAttribute("message", "Successful !! Account be going to update after logout! ");
        }else {
            User existingStudent = Login.user;
            request.setAttribute("user", existingStudent);
            request.setAttribute("message", "Account update failed, please try again!");
        }
        dispatcher = request.getRequestDispatcher("userView/editUser.jsp");
        dispatcher.forward(request, response);
    }

}
