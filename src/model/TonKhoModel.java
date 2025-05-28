package model;

public class TonKhoModel {
    private String maSP;
    private String tenSP;
    private String maLoai;
    private String tenLoai;
    private String donVi;
    private int soLuongNhap;
    private int soLuongXuat;
    private int soluongton;

    public TonKhoModel() {}

    public TonKhoModel(String maSP, String tenSP, String maLoai, String tenLoai, String donVi,
                       int soLuongNhap, int soLuongXuat, int soluongton) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.donVi = donVi;
        this.soLuongNhap = soLuongNhap;
        this.soLuongXuat = soLuongXuat;
        this.soluongton = soluongton;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public int getSoLuongXuat() {
        return soLuongXuat;
    }

    public void setSoLuongXuat(int soLuongXuat) {
        this.soLuongXuat = soLuongXuat;
    }

    public int getSoLuongTon() {
        return soluongton;
    }

    public void setSoLuongTon(int soluongton) {
        this.soluongton = soluongton;
    }
}
