package model;

public class LoaiSanPham {
	private String maLoaiSP;
	private String tenLoaiSP;
	
	public LoaiSanPham() {}
	
	public LoaiSanPham(String maLoaiSp, String tenLoaiSp) {
		this.maLoaiSP = maLoaiSp;
		this.tenLoaiSP = tenLoaiSp;
	}

	public String getMaLoaiSP() {
		return maLoaiSP;
	}

	public void setMaLoaiSP(String maLoaiSp) {
		this.maLoaiSP = maLoaiSp;
	}

	public String getTenLoaiSP() {
		return tenLoaiSP;
	}

	public void setTenLoaiSP(String tenLoaiSp) {
		this.tenLoaiSP = tenLoaiSp;
	}
	
	public String toString() {
		return tenLoaiSP;
	}
}
