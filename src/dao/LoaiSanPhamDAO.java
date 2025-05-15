package dao;

import model.LoaiSanPham;
import java.sql.*;
import java.util.*;

import dbConnection.MySQLConnection;

public class LoaiSanPhamDAO {

    public List<LoaiSanPham> getAllLoaiSP() {
        List<LoaiSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM loai_san_pham";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new LoaiSanPham(rs.getString("ma_loai_san_pham"), rs.getString("ten_loai_sp")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insertLoaiSP(LoaiSanPham loai) {
        String sql = "INSERT INTO loai_san_pham(ma_loai_san_pham, ten_loai_san_pham) VALUES (?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loai.getMaLoaiSP());
            ps.setString(2, loai.getTenLoaiSP());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateLoaiSP(LoaiSanPham loai) {
        String sql = "UPDATE loai_san_pham SET ten_loai_san_pham=? WHERE ma_loai_san_pham=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loai.getTenLoaiSP());
            ps.setString(2, loai.getMaLoaiSP());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteLoaiSP(String maLoaiSP) {
        String sql = "DELETE FROM loai_san_pham WHERE ma_loai_san_pham=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLoaiSP);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
