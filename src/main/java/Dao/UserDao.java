package Dao;

import Connect.ConnectMySql;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UserDao implements CRUD<User>{
    private static final String SELECT_USER_BY_ID_MK = "select * from user where tk = ? and mk =?";
    private static final String SELECT_USER_BY_ID = "Select tk,mk,chucvu,sdt,ten,gioitinh,diachi where id = ?";
    private static final String INSERT_USER_SQL = "INSERT INTO user (tk,mk,chucvu,sdt,ten,gioitinh,diachi) VALUES (?,?,?,?,?,?,?);";
    private static final String UPDATE_USER_SQL = "UPDATE user set mk = ?,sdt = ?,ten = ?,gioitinh = ?,diachi = ? where iduser= ?;";


    public User getUser (String user, String pass){
        try (Connection connection = ConnectMySql.getConnect()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID_MK);
            statement.setString(1,user);
            statement.setString(2,pass);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("iduser");
            String tk = resultSet.getString("tk");
            String mk = resultSet.getString("mk");
            String chucvu = resultSet.getString("chucvu");
            String sdt = resultSet.getString("sdt");
            String ten = resultSet.getString("ten");
            String gioitinh = resultSet.getString("gioitinh");
            String diachi = resultSet.getString("diachi");

            return new User(id,tk,mk,chucvu,sdt,ten,gioitinh,diachi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserById(int id) {
        try (Connection connection = ConnectMySql.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int iduser = resultSet.getInt("iduser");
            String tk = resultSet.getString("tk");
            String mk = resultSet.getString("mk");
            String chucvu = resultSet.getString("chucvu");
            String sdt = resultSet.getString("sdt");
            String ten = resultSet.getString("ten");
            String gioitinh = resultSet.getString("gioitinh");
            String diachi = resultSet.getString("diachi");

            return new User(iduser, tk, mk, chucvu, sdt, ten, gioitinh,diachi);

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean create(User user) {
        boolean check = false;
        try (Connection connection = ConnectMySql.getConnect()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getTk());
            preparedStatement.setString(2,user.getMk());
            preparedStatement.setString(3, user.getChucvu());
            preparedStatement.setString(4, user.getSdt());
            preparedStatement.setString(5, user.getTen());
            preparedStatement.setString(6, user.getGioitinh());
            preparedStatement.setString(7, user.getDiachi());

            check = preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    @Override
    public boolean edit(int id, User user) {
        boolean check = false;
        try (Connection connection = ConnectMySql.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setInt(6, user.getIduser());
            preparedStatement.setString(1, user.getMk());
            preparedStatement.setString(2,user.getSdt());
            preparedStatement.setString(3, user.getTen());
            preparedStatement.setString(4, user.getGioitinh());
            preparedStatement.setString(5, user.getDiachi());
            check = preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
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
}
