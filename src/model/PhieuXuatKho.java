package model;

import java.util.Date;

public class PhieuXuatKho {
    private String maXuatKho;
    private SanPham sanPham;
    private int soLuong;
    private String donVi;
    private double giaBan;
    private Date ngayXuat;

    public PhieuXuatKho() {}

    public PhieuXuatKho(String maXuatKho, SanPham sanPham, int soLuong,
                        String donVi, double giaBan, Date ngayXuat) {
        this.maXuatKho = maXuatKho;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.giaBan = giaBan;
        this.ngayXuat = ngayXuat;
    }

	public String getMaXuatKho() {
		return maXuatKho;
	}

	public void setMaXuatKho(String maXuatKho) {
		this.maXuatKho = maXuatKho;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}
    
	//
	public String getMaSanPham() {
	    return sanPham != null ? sanPham.getMaSP() : null;
	}
	public void setMaSanPham(String maSP) {
	    if (this.sanPham == null) {
	        this.sanPham = new SanPham();
	    }
	    this.sanPham.setMaSP(maSP);
	}
}
