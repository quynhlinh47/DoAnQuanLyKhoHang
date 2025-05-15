package dao;

import model.NhaCungCap;
import java.sql.*;
import java.util.*;

import dbConnection.MySQLConnection;

public class NhaCungCapDAO {

    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new NhaCungCap(
                        rs.getString("ma_nha_cung_cap"),
                        rs.getString("ten_nha_cung_cap"),
                        rs.getString("nguoi_lien_he"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("email"),
                        rs.getString("dia_chi")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(NhaCungCap ncc) {
        String sql = "INSERT INTO nha_cung_cap VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getNguoiLienHe());
            ps.setString(4, ncc.getSdt());
            ps.setString(5, ncc.getEmail());
            ps.setString(6, ncc.getDiaChi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(NhaCungCap ncc) {
        String sql = "UPDATE nha_cung_cap SET ten_nha_cung_cap=?, nguoi_lien_he=?, so_dien_thoai=?, email=?, dia_chi=? WHERE ma_nha_cung_cap=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getNguoiLienHe());
            ps.setString(3, ncc.getSdt());
            ps.setString(4, ncc.getEmail());
            ps.setString(5, ncc.getDiaChi());
            ps.setString(6, ncc.getMaNCC());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String maNCC) {
        String sql = "DELETE FROM nha_cung_cap WHERE ma_nha_cung_cap=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNCC);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
