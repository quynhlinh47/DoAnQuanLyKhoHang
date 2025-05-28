package model;

public class ThongKeModel {
    private String ngay;
    private int soLuongNhap;
    private int soLuongXuat;

    public ThongKeModel(String ngay, int nhap, int xuat) {
        this.ngay = ngay;
        this.soLuongNhap = nhap;
        this.soLuongXuat = xuat;
    }

    public String getNgay() { return ngay; }
    public int getSoLuongNhap() { return soLuongNhap; }
    public int getSoLuongXuat() { return soLuongXuat; }

    public void setNgay(String ngay) { this.ngay = ngay; }
    public void setSoLuongNhap(int soLuongNhap) { this.soLuongNhap = soLuongNhap; }
    public void setSoLuongXuat(int soLuongXuat) { this.soLuongXuat = soLuongXuat; }
}
