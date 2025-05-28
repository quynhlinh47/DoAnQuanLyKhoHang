package dao;

import dbConnection.MySQLConnection;
import model.ThongKeModel;

import java.sql.*;
import java.util.*;

public class ThongKeDAO {

    public int demSanPham() {
        return dem("SELECT COUNT(*) FROM san_pham");
    }

    public int demNhaCungCap() {
        return dem("SELECT COUNT(*) FROM nha_cung_cap");
    }

    private int dem(String sql) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ThongKeModel> getThongKeTheoNgay() {
        List<ThongKeModel> list = new ArrayList<>();
        String sql = """
            SELECT DATE(ngay_nhap) AS ngay, SUM(so_luong) AS tong_nhap, 0 AS tong_xuat
            FROM nhap_kho GROUP BY DATE(ngay_nhap)
            UNION
            SELECT DATE(ngay_xuat), 0, SUM(so_luong)
            FROM xuat_kho GROUP BY DATE(ngay_xuat)
            ORDER BY ngay ASC
        """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            Map<String, ThongKeModel> map = new TreeMap<>();
            while (rs.next()) {
                String ngay = rs.getString("ngay");
                int nhap = rs.getInt("tong_nhap");
                int xuat = rs.getInt("tong_xuat");
                ThongKeModel model = map.getOrDefault(ngay, new ThongKeModel(ngay, 0, 0));
                model.setSoLuongNhap(model.getSoLuongNhap() + nhap);
                model.setSoLuongXuat(model.getSoLuongXuat() + xuat);
                map.put(ngay, model);
            }
            list.addAll(map.values());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
