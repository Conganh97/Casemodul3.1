package Models;

public class Sanpham {
    private int idsp;
    private String tensp;
    private String dvt;
    private String mota;
    private Float gia;
    private String img;
    private String loaisp;

    public Sanpham(int idsp, String tensp, String dvt, String mota, Float gia, String img, String loaisp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.dvt = dvt;
        this.mota = mota;
        this.gia = gia;
        this.img = img;
        this.loaisp = loaisp;
    }

    public Sanpham(String tensp, String dvt, String mota, Float gia, String img, String loaisp) {
        this.tensp = tensp;
        this.dvt = dvt;
        this.mota = mota;
        this.gia = gia;
        this.img = img;
        this.loaisp = loaisp;
    }

    public Sanpham() {
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

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }
}
