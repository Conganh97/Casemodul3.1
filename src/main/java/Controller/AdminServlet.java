package Controller;

import Dao.SanPhamDao;
import Dao.UserDao;
import Models.Login;
import Models.Sanpham;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    SanPhamDao sanPhamDao = new SanPhamDao();
    UserDao userDao = new UserDao();
    RequestDispatcher dispatcher ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher dispatcher = null;
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    showCreateForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    showDeleteForm(req, resp);
                    break;
                case "editUser":
                    showEditFormUser(req,resp);
                    break;
                default:
                    listStudent(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    createSanpham(req, resp);
                    break;
                case "edit":
                    editSanpham(req, resp);
                    break;
                case "delete":
                    deleteSanpham(req, resp);
                    break;
                case "editUser":
                    editUser(req,resp);
                    break;
            }
        }catch (SQLException | ParseException ex){
            throw new ServletException(ex);
        }
    }
    private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Sanpham> listSanpham = sanPhamDao.getAll();

        request.setAttribute("listMenu", listSanpham);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("web/create.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Sanpham existingSanpham= sanPhamDao.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("web/editadmin.jsp");
        request.setAttribute("sanpham", existingSanpham);
        dispatcher.forward(request,response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Sanpham existingSanpham = sanPhamDao.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("web/delete.jsp");
        request.setAttribute("sanpham", existingSanpham);
        dispatcher.forward(request, response);
    }

    private void createSanpham(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        String tensp = request.getParameter("tensp");
        String dvt = request.getParameter("dvt");
        String mota = request.getParameter("mota");
        Float gia = Float.valueOf(request.getParameter("gia"));
        String img = request.getParameter("img");
        String loaisp = request.getParameter("loaisp");
        Sanpham sanpham = new Sanpham(tensp, dvt, mota, gia, img, loaisp);
        sanPhamDao.create(sanpham);
        listStudent(request,response);
    }

    private void editSanpham(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        int idsp = Integer.parseInt(request.getParameter("id"));
        String tensp = request.getParameter("tensp");
        String dvt = request.getParameter("dvt");
        String mota = request.getParameter("mota");
        Float gia = Float.valueOf(request.getParameter("gia"));
        String img = request.getParameter("img");
        String loaisp = request.getParameter("loaisp");
        Sanpham sanpham = new Sanpham(idsp,tensp, dvt, mota, gia, img, loaisp);
        sanPhamDao.edit(idsp,sanpham);
        listStudent(request,response);
    }

    private void deleteSanpham(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        sanPhamDao.delete(id);
        listStudent(request,response);
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
        dispatcher = request.getRequestDispatcher("web/editadmin.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        User existingStudent = Login.user;
        dispatcher = request.getRequestDispatcher("web/editadmin.jsp");
        request.setAttribute("user", existingStudent);
        dispatcher.forward(request, response);
    }
}
