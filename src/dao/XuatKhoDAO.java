package dao;

import model.PhieuXuatKho;
import java.sql.*;
import java.util.*;

import dbConnection.MySQLConnection;

public class XuatKhoDAO {

    public List<PhieuXuatKho> getAll() {
        List<PhieuXuatKho> list = new ArrayList<>();
        String sql = "SELECT * FROM xuat_kho";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            	PhieuXuatKho xk = new PhieuXuatKho();
                xk.setMaXuatKho(rs.getString("ma_xuat_kho"));
                xk.setMaSanPham(rs.getString("ma_san_pham"));
                xk.setSoLuong(rs.getInt("so_luong"));
                xk.setDonVi(rs.getString("don_vi"));
                xk.setGiaBan(rs.getDouble("gia_ban"));
                xk.setNgayXuat(rs.getDate("ngay_xuat"));

                list.add(xk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(PhieuXuatKho xk) {
        String sql = "INSERT INTO xuat_kho (ma_xuat_kho, ma_san_pham, so_luong, don_vi, gia_ban, ngay_xuat) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, xk.getMaXuatKho());
            ps.setString(2, xk.getMaSanPham());
            ps.setInt(3, xk.getSoLuong());
            ps.setString(4, xk.getDonVi());
            ps.setDouble(5, xk.getGiaBan());
            ps.setDate(6, new java.sql.Date(xk.getNgayXuat().getTime()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String maXuatKho) {
        String sql = "DELETE FROM xuat_kho WHERE ma_xuat_kho=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maXuatKho);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
