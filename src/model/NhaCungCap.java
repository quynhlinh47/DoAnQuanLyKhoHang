package model;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String nguoiLienHe;
	private String sdt;
	private String email;
	private String diaChi;
	
	public NhaCungCap() {}
	
	public NhaCungCap(String maNCC, String tenNCC, String nguoiLienHe, String sdt, String email, String diaChi) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.nguoiLienHe = nguoiLienHe;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getNguoiLienHe() {
		return nguoiLienHe;
	}

	public void setNguoiLienHe(String nguoiLienHe) {
		this.nguoiLienHe = nguoiLienHe;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
}
