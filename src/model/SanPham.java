package model;

import java.util.Date;

public class SanPham {
	private String maSP;
	private String tenSP;
	private LoaiSanPham loaiSP;
	private double giaNhap;
	private double giaBan;
	private String donVi;
	private Date ngayHetHan;
	private Date ngayTao;
	private Date ngayCapNhat;

	public SanPham() {
	}

	public SanPham(String maSP, String tenSP, LoaiSanPham loaiSP, double giaNhap, double giaBan,
			String donVi, Date ngayHetHan, Date ngayTao, Date ngayCapNhat) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.donVi = donVi;
		this.ngayHetHan = ngayHetHan;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
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

	public LoaiSanPham getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(LoaiSanPham loaiSP) {
		this.loaiSP = loaiSP;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public Date getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(Date ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	
	public String getMaLoaiSP() {
        return loaiSP != null ? loaiSP.getMaLoaiSP() : null;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        if (this.loaiSP == null) {
            this.loaiSP = new LoaiSanPham();
        }
        this.loaiSP.setMaLoaiSP(maLoaiSP);
    }
}
