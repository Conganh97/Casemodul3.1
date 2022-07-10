package Controller;

import Dao.GioHangDao;
import Dao.UserDao;
import Models.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet (urlPatterns = "/giohang")
public class GioHangServlet extends HttpServlet{
    GioHangDao gioHangDao = new GioHangDao();
    static List<GioHang> gioHangList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        if(order == null) {
            order = "";
        }
        try {
            switch (order) {
                case "create":
                    createOrder(request, response);
                    break;
                case "chitiet":
                    showChiTiet(request, response);
                    break;
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    GioHang gioHang = gioHangDao.findById(id);
                    gioHangList.remove(gioHang);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/giohang.jsp");
                    request.setAttribute("list",gioHangList);
                    requestDispatcher.forward(request,response);
                    break;
                default:
                    showLichSu(request, response);
            }
        }catch (SQLException | ParseException ex){
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        if(order == null) {
            order = "";
        }
        try {
            switch (order) {
                case "create":
                    createThanhToan(request, response);
                    break;
                default:

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/giohang");
//                    requestDispatcher.forward(request,response);
            }
        }catch (SQLException | ParseException ex){
            throw new ServletException(ex);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        GioHang gioHang = gioHangDao.findById(id);
        gioHangList.add(gioHang);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/giohang.jsp");
        request.setAttribute("list",gioHangList);
        requestDispatcher.forward(request,response);
    }

    private void createThanhToan(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        Date ngxuathd = new Date(Calendar.getInstance().getTime().getTime());
        gioHangDao.createHoaDon(ngxuathd,Login.user.getIduser());
        int idhd = gioHangDao.catchIdhd(ngxuathd,Login.user.getIduser());
        for (int i = 0; i < gioHangList.size(); i++) {
            gioHangDao.createCTHD(idhd,gioHangList.get(i).getIdsp(),gioHangList.get(i).getSl());
        }
        float trigia = gioHangDao.catchTrigia(idhd);

        Hoadon hoadon = new Hoadon(idhd,ngxuathd,Login.user.getTen(),trigia);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hoadon.jsp");
        request.setAttribute("list",gioHangList);
        request.setAttribute("hoadon",hoadon);
        requestDispatcher.forward(request,response);
    }

    private void showLichSu(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        List<Hoadon> hoadonList = gioHangDao.findHDByIdUser(Login.user.getIduser());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/lichsu.jsp");
        request.setAttribute("list",hoadonList);
        requestDispatcher.forward(request,response);
    }

    private void showChiTiet(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        int idhd = Integer.parseInt(request.getParameter("idhdct"));
        List<GioHang> gioHangList = gioHangDao.findCTHDByIdhd(idhd);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cthd.jsp");
        request.setAttribute("list",gioHangList);
        requestDispatcher.forward(request,response);
    }
}