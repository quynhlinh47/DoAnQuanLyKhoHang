package dao;

import java.sql.*;
import java.util.*;

import model.TonKhoModel;
import dbConnection.*;

public class TonKhoDAO {

    public List<TonKhoModel> getAllTonKho() {
        List<TonKhoModel> list = new ArrayList<>();
        String sql = """
            SELECT sp.ma_san_pham, sp.ten_san_pham, lsp.ma_loai_san_pham, lsp.ten_loai_san_pham, sp.don_vi,
                   IFNULL(nhap.tong_nhap, 0) AS so_luong_nhap,
                   IFNULL(xuat.tong_xuat, 0) AS so_luong_xuat,
                   IFNULL(nhap.tong_nhap, 0) - IFNULL(xuat.tong_xuat, 0) AS ton_kho
            FROM san_pham sp
            JOIN loai_san_pham lsp ON sp.ma_loai_san_pham = lsp.ma_loai_san_pham
            LEFT JOIN (
                SELECT ma_san_pham, SUM(so_luong) AS tong_nhap
                FROM nhap_kho
                GROUP BY ma_san_pham
            ) nhap ON sp.ma_san_pham = nhap.ma_san_pham
            LEFT JOIN (
                SELECT ma_san_pham, SUM(so_luong) AS tong_xuat
                FROM xuat_kho
                GROUP BY ma_san_pham
            ) xuat ON sp.ma_san_pham = xuat.ma_san_pham
        """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TonKhoModel tk = new TonKhoModel();
                tk.setMaSP(rs.getString("ma_san_pham"));
                tk.setTenSP(rs.getString("ten_san_pham"));
                tk.setMaLoai(rs.getString("ma_loai_san_pham"));
                tk.setTenLoai(rs.getString("ten_loai_san_pham"));
                tk.setDonVi(rs.getString("don_vi"));
                tk.setSoLuongNhap(rs.getInt("so_luong_nhap"));
                tk.setSoLuongXuat(rs.getInt("so_luong_xuat"));
                tk.setSoLuongTon(rs.getInt("ton_kho"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TonKhoModel> searchTonKho(String keyword, String type) {
        List<TonKhoModel> list = new ArrayList<>();
        
        String condition;
        if ("Tên sản phẩm".equalsIgnoreCase(type.trim())) {
            condition = "sp.ten_san_pham";
        } else if ("Tên loại sản phẩm".equalsIgnoreCase(type.trim())) {
            condition = "lsp.ten_loai_san_pham";
        } else {
            condition = "sp.ten_san_pham"; 
        }

        String sql = """
            SELECT sp.ma_san_pham, sp.ten_san_pham, lsp.ma_loai_san_pham, lsp.ten_loai_san_pham, sp.don_vi,
                   IFNULL(nhap.tong_nhap, 0) AS so_luong_nhap,
                   IFNULL(xuat.tong_xuat, 0) AS so_luong_xuat,
                   IFNULL(nhap.tong_nhap, 0) - IFNULL(xuat.tong_xuat, 0) AS ton_kho
            FROM san_pham sp
            JOIN loai_san_pham lsp ON sp.ma_loai_san_pham = lsp.ma_loai_san_pham
            LEFT JOIN (
                SELECT ma_san_pham, SUM(so_luong) AS tong_nhap
                FROM nhap_kho
                GROUP BY ma_san_pham
            ) nhap ON sp.ma_san_pham = nhap.ma_san_pham
            LEFT JOIN (
                SELECT ma_san_pham, SUM(so_luong) AS tong_xuat
                FROM xuat_kho
                GROUP BY ma_san_pham
            ) xuat ON sp.ma_san_pham = xuat.ma_san_pham
            WHERE LOWER(""" + condition + ") LIKE LOWER(?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword.trim() + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TonKhoModel tk = new TonKhoModel();
                    tk.setMaSP(rs.getString("ma_san_pham"));
                    tk.setTenSP(rs.getString("ten_san_pham"));
                    tk.setMaLoai(rs.getString("ma_loai_san_pham"));
                    tk.setTenLoai(rs.getString("ten_loai_san_pham"));
                    tk.setDonVi(rs.getString("don_vi"));
                    tk.setSoLuongNhap(rs.getInt("so_luong_nhap"));
                    tk.setSoLuongXuat(rs.getInt("so_luong_xuat"));
                    tk.setSoLuongTon(rs.getInt("ton_kho"));
                    list.add(tk);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
