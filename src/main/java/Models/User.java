package Models;

import java.util.Date;

public class User {
    private int iduser;
    private String tk;
    private String mk;
    private String chucvu;
    private String sdt;
    private String ten;
    private String gioitinh;
    private String diachi;

    public User(int iduser, String tk, String mk, String chucvu, String sdt, String ten, String gioitinh, String diachi) {
        this.iduser = iduser;
        this.tk = tk;
        this.mk = mk;
        this.chucvu = chucvu;
        this.sdt = sdt;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
    }

    public User(String tk, String mk, String chucvu, String sdt, String ten, String gioitinh, String diachi) {
        this.tk = tk;
        this.mk = mk;
        this.chucvu = chucvu;
        this.sdt = sdt;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
    }

    public User(int iduser, String mk, String sdt, String ten, String gioitinh, String diachi) {
        this.iduser = iduser;
        this.mk = mk;
        this.sdt = sdt;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public User() {
    }
}
