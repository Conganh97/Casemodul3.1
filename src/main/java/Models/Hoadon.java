package Models;

import java.util.Date;

public class Hoadon {
    private int idhd;
    private Date ngxuathd;
    private String ten;
    private float trigia;

    public Hoadon() {
    }

    public Hoadon(int idhd, Date ngxuathd, String ten, float trigia) {
        this.idhd = idhd;
        this.ngxuathd = ngxuathd;
        this.ten = ten;
        this.trigia = trigia;
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public Date getNgxuathd() {
        return ngxuathd;
    }

    public void setNgxuathd(Date ngxuathd) {
        this.ngxuathd = ngxuathd;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getTrigia() {
        return trigia;
    }

    public void setTrigia(float trigia) {
        this.trigia = trigia;
    }
}
