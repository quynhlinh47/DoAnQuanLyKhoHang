package dao;

import model.PhieuNhapKho;

import java.sql.*;
import java.util.*;

import dbConnection.MySQLConnection;

public class NhapKhoDAO {

	public List<PhieuNhapKho> getAll() {
		List<PhieuNhapKho> list = new ArrayList<>();
		String sql = "SELECT * FROM nhap_kho";

		try (Connection conn = MySQLConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				PhieuNhapKho nk = new PhieuNhapKho();
				nk.setMaNhapKho(rs.getString("ma_nhap_kho"));
				nk.setMaSanPham(rs.getString("ma_san_pham"));
                nk.setMaNhaCungCap(rs.getString("ma_nha_cung_cap"));
				nk.setSoLuong(rs.getInt("so_luong"));
				nk.setDonVi(rs.getString("don_vi"));
				nk.setGiaNhap(rs.getDouble("gia_nhap"));
				nk.setNgayNhap(rs.getDate("ngay_nhap"));

				list.add(nk);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean insert(PhieuNhapKho nk) {
		String sql = "INSERT INTO nhap_kho (ma_nhap_kho, ma_san_pham, ma_nha_cung_cap, so_luong, don_vi, gia_nhap, ngay_nhap) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = MySQLConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, nk.getMaNhapKho());
			ps.setString(2, nk.getMaSanPham());
			ps.setString(3, nk.getMaNhaCungCap());
			ps.setInt(4, nk.getSoLuong());
			ps.setString(5, nk.getDonVi());
			ps.setDouble(6, nk.getGiaNhap());
			ps.setDate(7, new java.sql.Date(nk.getNgayNhap().getTime()));

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean delete(String maNhapKho) {
		String sql = "DELETE FROM nhap_kho WHERE ma_nhap_kho=?";

		try (Connection conn = MySQLConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, maNhapKho);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
