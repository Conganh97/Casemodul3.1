package Dao;

import Connect.ConnectMySql;
import Models.GioHang;
import Models.Hoadon;
import Models.Login;
import Models.Sanpham;

import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GioHangDao implements CRUD<GioHang> {
    @Override
    public List<GioHang> getAll() {
        return null;
    }

    @Override
    public boolean create(GioHang gioHang) {
        return false;
    }

    @Override
    public boolean edit(int id, GioHang gioHang) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public GioHang findById(int id) {
        GioHang gioHang = null;
        try (Connection connection = ConnectMySql.getConnect()
        ) { String sql = "select idsp, tensp, gia from sanpham where idsp =?";
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

                rs.next();
                    int idsp = Integer.parseInt(rs.getString("idsp"));
                    String tensp = rs.getString("tensp");
                    float gia = Float.parseFloat(rs.getString("gia"));
                    gioHang = new GioHang(idsp,tensp,gia, 1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return gioHang;
    }

    public List<Hoadon> findHDByIdUser(int iduser) {
        List<Hoadon> hoadonList = new ArrayList<>();
        try (Connection connection = ConnectMySql.getConnect()
        ) { String sql = "select idhd, ngxuathd, trigia from hoadon where iduser =?";
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, iduser);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int idhd = Integer.parseInt(rs.getString("idhd"));
                    Date ngxuathd = rs.getDate("ngxuathd");
                    float trigia = Float.parseFloat(rs.getString("trigia"));
                    hoadonList.add(new Hoadon(idhd,ngxuathd, Login.user.getTen(), trigia));
                }

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return hoadonList;
    }

    public List<GioHang> findCTHDByIdhd(int idhd) {
        List<GioHang> gioHangList = new ArrayList<>();
        try (Connection connection = ConnectMySql.getConnect()
        ) { String sql = "select s.idsp,s.tensp,gia,c.sl from cthd c join sanpham s on c.idsp = s.idsp where idhd = ?;";
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idhd);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int idsp = Integer.parseInt(rs.getString("idsp"));
                    String tensp = rs.getString("tensp");
                    float gia = Float.parseFloat(rs.getString("gia"));
                    int sl = Integer.parseInt(rs.getString("sl"));
                    gioHangList.add(new GioHang(idsp,tensp, gia, sl));
                }

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return gioHangList;
    }

    public boolean createHoaDon(Date ngxuathd, int iduser) {
        boolean check = false;
        try (Connection connection = ConnectMySql.getConnect()
        ) { String sql = "insert into hoadon (ngxuathd,iduser,trigia) values (?,?,0);";
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, ngxuathd);
                preparedStatement.setInt(2, iduser);
                System.out.println(preparedStatement);
                check = preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    public int catchIdhd(Date ngxuathd, int iduser) {
        int idhd = -1;
        try (Connection connection = ConnectMySql.getConnect()
        ) { String sql = "select idhd from hoadon where ngxuathd=? and iduser=? and trigia=0;";
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, ngxuathd);
                preparedStatement.setInt(2, iduser);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

                rs.next();
                idhd = Integer.parseInt(rs.getString("idhd"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return idhd;
    }

    public float catchTrigia(int idhd) {
        float trigia = 0;
        try (Connection connection = ConnectMySql.getConnect()
        ) { String sql = "select trigia from hoadon where idhd = ?;";
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idhd);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

                rs.next();
                trigia = Float.parseFloat(rs.getString("trigia"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return trigia;
    }

    public boolean createCTHD(int idhd, int idsp, int sl) {
        boolean check = false;
        try (Connection connection = ConnectMySql.getConnect()
        ) { String sql = "insert into cthd (idhd,idsp,sl) values (?,?,?)";
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idhd);
                preparedStatement.setInt(2, idsp);
                preparedStatement.setInt(3, sl);
                System.out.println(preparedStatement);
                check = preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace();
                System.out.println("SQLState:" + ((SQLException) e).getSQLState());
                System.out.println("Error Code:" + ((SQLException) e).getSQLState());
                System.out.println("Message:" + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause:" + t);
                    t = t.getCause();
                }
            }
        }
    }
    public List<Hoadon> allBill() {
        List<Hoadon> hoadonList = new ArrayList<>();
        try (Connection connection = ConnectMySql.getConnect()) {
            String sql = "select idhd,ngxuathd,trigia,ten from hoadon join user on hoadon.iduser = user.iduser;";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int idhd = Integer.parseInt(rs.getString("idhd"));
                Date ngxuathd = rs.getDate("ngxuathd");
                float trigia = Float.parseFloat(rs.getString("trigia"));
                String ten = rs.getString("ten");
                hoadonList.add(new Hoadon(idhd,ngxuathd,ten, trigia));
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return hoadonList;
    }
    public float doanhthu() {
        float trigia = 0;
        try (Connection connection = ConnectMySql.getConnect()) {
            String sql = "select sum(trigia) as doanhthu from hoadon;";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            trigia = resultSet.getFloat("doanhthu");
        } catch (SQLException e) {
            printSQLException(e);
        }
        return trigia;
    }
}
