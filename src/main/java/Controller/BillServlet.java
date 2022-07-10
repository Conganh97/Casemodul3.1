package Controller;

import Dao.GioHangDao;
import Models.GioHang;
import Models.Hoadon;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/bill")
public class BillServlet extends HttpServlet {
    RequestDispatcher requestDispatcher = null;
    GioHangDao gioHangDao = new GioHangDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order = req.getParameter("order");
        if(order == null) {
            order = "";
        }
        switch (order) {
            case "detail":
                int idcthd = Integer.parseInt(req.getParameter("idhdct"));
                List<GioHang> hdlist = gioHangDao.findCTHDByIdhd(idcthd);
                requestDispatcher = req.getRequestDispatcher("/web/billdetail.jsp");
                req.setAttribute("billdetail",hdlist);
                requestDispatcher.forward(req,resp);
                break;

            default:
                List <Hoadon> hoadonList = gioHangDao.allBill();
                req.setAttribute("billadmin", hoadonList);
                req.setAttribute("doanhthu",gioHangDao.doanhthu());
                requestDispatcher = req.getRequestDispatcher("/bill.jsp");
                requestDispatcher.forward(req, resp);

        }
    }
}