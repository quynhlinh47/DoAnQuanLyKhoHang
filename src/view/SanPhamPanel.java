package view;

import dao.SanPhamDAO;
import model.SanPham;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SanPhamPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private SanPhamDAO sanPhamDAO;

    public SanPhamPanel() {
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Quản lý hàng hoá");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Bảng hiển thị dữ liệu sản phẩm
        String[] columns = {"Mã SP", "Tên SP", "Mã loại SP", "Giá nhập", "Giá bán", "Số lượng tồn", "Đơn vị", "Ngày hết hạn", "Ngày tạo", "Ngày cập nhật"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Cho bảng nằm trong JScrollPane để có thanh cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Khởi tạo DAO
        sanPhamDAO = new SanPhamDAO();

        // Load dữ liệu từ database vào bảng
        loadData();
    }

    private void loadData() {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        // Lấy danh sách sản phẩm từ DAO
        List<SanPham> list = sanPhamDAO.getAll();

        // Thêm từng sản phẩm vào bảng
        for (SanPham sp : list) {
            Object[] row = {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getMaLoaiSP(),
                    sp.getGiaNhap(),
                    sp.getGiaBan(),
                    sp.getSoLuongTon(),
                    sp.getDonVi(),
                    sp.getNgayHetHan(),
                    sp.getNgayTao(),
                    sp.getNgayCapNhat()
            };
            tableModel.addRow(row);
        }
    }
}
