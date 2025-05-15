package model;

import java.util.Date;

public class PhieuNhapKho {
    private String maNhapKho;
    private SanPham sanPham;
    private NhaCungCap nhaCungCap;
    private int soLuong;
    private String donVi;
    private double giaNhap;
    private Date ngayNhap;

    public PhieuNhapKho() {}

    public PhieuNhapKho(String maNhapKho, SanPham sanPham, NhaCungCap nhaCungCap,
                        int soLuong, String donVi, double giaNhap, Date ngayNhap) {
        this.maNhapKho = maNhapKho;
        this.sanPham = sanPham;
        this.nhaCungCap = nhaCungCap;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.giaNhap = giaNhap;
        this.ngayNhap = ngayNhap;
    }

	public String getMaNhapKho() {
		return maNhapKho;
	}

	public void setMaNhapKho(String maNhapKho) {
		this.maNhapKho = maNhapKho;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
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

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
    
	
	//
	public String getMaSanPham() {
	    return sanPham != null ? sanPham.getMaSP() : null;
	}

	public String getMaNhaCungCap() {
	    return nhaCungCap != null ? nhaCungCap.getMaNCC() : null;
	}
	public void setMaSanPham(String maSP) {
	    if (this.sanPham == null) {
	        this.sanPham = new SanPham();
	    }
	    this.sanPham.setMaSP(maSP);
	}

	public void setMaNhaCungCap(String maNCC) {
	    if (this.nhaCungCap == null) {
	        this.nhaCungCap = new NhaCungCap();
	    }
	    this.nhaCungCap.setMaNCC(maNCC);
	}

}
