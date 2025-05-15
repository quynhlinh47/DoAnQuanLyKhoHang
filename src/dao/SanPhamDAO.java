package dao;

import model.SanPham;
import java.sql.*;
import java.util.*;

import dbConnection.MySQLConnection;

public class SanPhamDAO {

    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM san_pham";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("ma_san_pham"));
                sp.setTenSP(rs.getString("ten_san_pham"));
                sp.setMaLoaiSP(rs.getString("ma_loai_san_pham"));
                sp.setGiaNhap(rs.getDouble("gia_nhap"));
                sp.setGiaBan(rs.getDouble("gia_ban"));
                sp.setDonVi(rs.getString("don_vi"));
                sp.setNgayHetHan(rs.getDate("ngay_het_han"));
                sp.setNgayTao(rs.getDate("ngay_tao"));
                sp.setNgayCapNhat(rs.getDate("ngay_cap_nhat"));

                list.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(SanPham sp) {
        String sql = "INSERT INTO san_pham(ma_san_pham, ten_san_pham, ma_loai_san_pham, gia_nhap, gia_ban, don_vi, ngay_het_han, ngay_tao, ngay_cap_nhat) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setString(3, sp.getMaLoaiSP());
            ps.setDouble(4, sp.getGiaNhap());
            ps.setDouble(5, sp.getGiaBan());
            ps.setString(6, sp.getDonVi());
            ps.setDate(7, new java.sql.Date(sp.getNgayHetHan().getTime()));
            ps.setDate(8, new java.sql.Date(sp.getNgayTao().getTime()));
            ps.setDate(9, new java.sql.Date(sp.getNgayCapNhat().getTime()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(SanPham sp) {
        String sql = "UPDATE san_pham SET ten_san_pham=?, ma_loai_san_pham=?, gia_nhap=?, gia_ban=?, don_vi=?, "
                   + "ngay_het_han=?, ngay_tao=?, ngay_cap_nhat=? WHERE ma_san_pham=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getMaLoaiSP());
            ps.setDouble(3, sp.getGiaNhap());
            ps.setDouble(4, sp.getGiaBan());
            ps.setString(5, sp.getDonVi());
            ps.setDate(6, new java.sql.Date(sp.getNgayHetHan().getTime()));
            ps.setDate(7, new java.sql.Date(sp.getNgayTao().getTime()));
            ps.setDate(8, new java.sql.Date(sp.getNgayCapNhat().getTime()));
            ps.setString(9, sp.getMaSP());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String maSP) {
        String sql = "DELETE FROM san_pham WHERE ma_san_pham=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSP);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<SanPham> findByTenSP(String tenSP) {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM san_pham WHERE ten_san_pham LIKE ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + tenSP + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SanPham> findByLoaiSP(String maLoaiSP) {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM san_pham WHERE ma_loai_san_pham LIKE ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + maLoaiSP + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    private SanPham mapRow(ResultSet rs) throws SQLException {
        SanPham sp = new SanPham();
        sp.setMaSP(rs.getString("ma_san_pham"));
        sp.setTenSP(rs.getString("ten_san_pham"));
        sp.setMaLoaiSP(rs.getString("ma_loai_san_pham"));
        sp.setGiaNhap(rs.getDouble("gia_nhap"));
        sp.setGiaBan(rs.getDouble("gia_ban"));
        sp.setDonVi(rs.getString("don_vi"));
        sp.setNgayHetHan(rs.getDate("ngay_het_han"));
        sp.setNgayTao(rs.getDate("ngay_tao"));
        sp.setNgayCapNhat(rs.getDate("ngay_cap_nhat"));
        return sp;
    }
}
