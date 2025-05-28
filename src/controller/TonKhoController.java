package controller;

import dao.TonKhoDAO;
import model.TonKhoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class TonKhoController {

    private TonKhoDAO tonKhoDAO;
    private JTable tableTonKho;
    private JTextField txtTimKiem;
    private JComboBox<String> cbTieuChi;
    private JButton btnLamMoi;

    private DefaultTableModel tableModel;

    public TonKhoController(JTable tableTonKho, JTextField txtTimKiem, JComboBox<String> cbTieuChi, JButton btnLamMoi) {
        this.tonKhoDAO = new TonKhoDAO();
        this.tableTonKho = tableTonKho;
        this.txtTimKiem = txtTimKiem;
        this.cbTieuChi = cbTieuChi;
        this.btnLamMoi = btnLamMoi;

        setupTable();
        loadTonKho();
        addEvents();
    }

    private void setupTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {
            "Mã SP", "Tên SP", "Mã Loại", "Tên Loại", "Đơn vị", "Số lượng nhập", "Số lượng xuất", "Số lượng tồn"
        });
        tableTonKho.setModel(tableModel);
    }

    private void addEvents() {
        txtTimKiem.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchTonKho();
            }
        });

        // Sự kiện làm mới
        btnLamMoi.addActionListener(e -> {
            txtTimKiem.setText("");
            cbTieuChi.setSelectedIndex(0);
            loadTonKho();
        });
    }

    public void loadTonKho() {
        List<TonKhoModel> list = tonKhoDAO.getAllTonKho();
        tableModel.setRowCount(0); 
        for (TonKhoModel tk : list) {
            tableModel.addRow(new Object[] {
                tk.getMaSP(), tk.getTenSP(), tk.getMaLoai(), tk.getTenLoai(),
                tk.getDonVi(), tk.getSoLuongNhap(), tk.getSoLuongXuat(), tk.getSoLuongTon()
            });
        }
    }

    public void searchTonKho() {
        String keyword = txtTimKiem.getText().trim();
        String tieuChi = (String) cbTieuChi.getSelectedItem();

        if (keyword.isEmpty()) {
            loadTonKho();
            return;
        }

        List<TonKhoModel> list = tonKhoDAO.searchTonKho(keyword, tieuChi);
        tableModel.setRowCount(0);
        for (TonKhoModel tk : list) {
            tableModel.addRow(new Object[] {
                tk.getMaSP(), tk.getTenSP(), tk.getMaLoai(), tk.getTenLoai(),
                tk.getDonVi(), tk.getSoLuongNhap(), tk.getSoLuongXuat(), tk.getSoLuongTon()
            });
        }
    }
}
