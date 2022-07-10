package Models;

public class GioHang {
    private int idsp;
    private String tensp;
    private float gia;
    private int sl;

    public GioHang(int idsp,String tensp, float gia, int sl) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.gia = gia;
        this.sl = sl;
    }

    public GioHang() {
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
