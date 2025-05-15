package view;

import controller.LoaiSanPhamController;
import controller.SanPhamController;
import model.LoaiSanPham;
import model.SanPham;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SanPhamPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private SanPhamController controller;
    
    private JTable tableLoai;
    private DefaultTableModel modelLoai;


    private JButton btnThem, btnXoa, btnSua, btnTim;
    private JComboBox<String> cmbTimKiem;
    private JTextField txtTuKhoa;

    public SanPhamPanel() {
        setLayout(new BorderLayout());

        controller = new SanPhamController();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xoá");
        btnSua = new JButton("Sửa");
        btnTim = new JButton("Tìm");

        cmbTimKiem = new JComboBox<>(new String[]{"Tên sản phẩm", "Loại sản phẩm"});
        txtTuKhoa = new JTextField(15);

        topPanel.add(btnThem);
        topPanel.add(btnXoa);
        topPanel.add(btnSua);
        topPanel.add(new JLabel("Tìm kiếm theo:"));
        topPanel.add(cmbTimKiem);
        topPanel.add(txtTuKhoa);
        topPanel.add(btnTim);

        add(topPanel, BorderLayout.NORTH);

        
        String[] columns = {
            "Mã SP", "Tên SP", "Mã loại SP", "Giá nhập", "Giá bán",
           "Đơn vị", "Ngày hết hạn", "Ngày tạo", "Ngày cập nhật"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        
        
        
        String[] loaiColumns = {"Mã loại SP", "Tên loại SP"};
        modelLoai = new DefaultTableModel(loaiColumns, 0);
        tableLoai = new JTable(modelLoai);
     
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JScrollPane loaiScrollPane = new JScrollPane(tableLoai);
        loaiScrollPane.setPreferredSize(new Dimension(0, 100));
        bottomPanel.add(loaiScrollPane, BorderLayout.CENTER);

      
     // Panel nhập và nút thao tác loại sản phẩm
        JPanel panelLoaiRight = new JPanel();
        panelLoaiRight.setLayout(new BoxLayout(panelLoaiRight, BoxLayout.Y_AXIS));
        panelLoaiRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtMaLoai = new JTextField(20);
        JTextField txtTenLoai = new JTextField(20);
        JButton btnThemLoai = new JButton("Thêm Loại");
        JButton btnSuaLoai = new JButton("Sửa Loại");

        panelLoaiRight.add(new JLabel("Mã loại SP:"));
        panelLoaiRight.add(txtMaLoai);
        panelLoaiRight.add(Box.createVerticalStrut(5));
        panelLoaiRight.add(new JLabel("Tên loại SP:"));
        panelLoaiRight.add(txtTenLoai);
        panelLoaiRight.add(Box.createVerticalStrut(10));
        panelLoaiRight.add(btnThemLoai);
        panelLoaiRight.add(Box.createVerticalStrut(10));
        panelLoaiRight.add(btnSuaLoai);

        bottomPanel.add(panelLoaiRight, BorderLayout.EAST);


        add(bottomPanel, BorderLayout.SOUTH);


        loadData();
        loadLoaiSP();


        
        
        //sự kiện
        btnTim.addActionListener(this::timKiem);
        btnXoa.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa.", "Chưa chọn", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String maSP = tableModel.getValueAt(selected, 0).toString();
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc muốn xóa sản phẩm mã “" + maSP + "” không?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                boolean success = controller.xoa(maSP);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnThem.addActionListener(e -> {
            SanPhamDialog dialog = new SanPhamDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm Sản Phẩm", null);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                SanPham sp = dialog.getSanPham();
                if (sp != null && controller.them(sp)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }
            }
        });

        btnSua.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                String maSP = table.getValueAt(selected, 0).toString();
                List<SanPham> all = controller.layTatCa();
                SanPham spChon = all.stream().filter(sp -> sp.getMaSP().equals(maSP)).findFirst().orElse(null);

                if (spChon != null) {
                    SanPhamDialog dialog = new SanPhamDialog((Frame) SwingUtilities.getWindowAncestor(this), "Sửa Sản Phẩm", spChon);
                    dialog.setVisible(true);

                    if (dialog.isConfirmed()) {
                        SanPham spMoi = dialog.getSanPham();
                        if (spMoi != null && controller.sua(spMoi)) {
                            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                            loadData();
                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa.");
            }
        });
        
        
        //
        btnThemLoai.addActionListener(e -> {
            String ma = txtMaLoai.getText().trim();
            String ten = txtTenLoai.getText().trim();

            if (ma.isEmpty() || ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mã và tên loại sản phẩm.");
                return;
            }

            LoaiSanPham loai = new LoaiSanPham(ma, ten);
            if (new LoaiSanPhamController().them(loai)) {
                JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thành công!");
                loadLoaiSP();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại! Có thể mã đã tồn tại.");
            }
        });

        btnSuaLoai.addActionListener(e -> {
            int selected = tableLoai.getSelectedRow();
            if (selected == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một loại sản phẩm để sửa.");
                return;
            }

            String ma = txtMaLoai.getText().trim();
            String ten = txtTenLoai.getText().trim();

            if (ma.isEmpty() || ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mã và tên loại sản phẩm.");
                return;
            }

            LoaiSanPham loai = new LoaiSanPham(ma, ten);
            if (new LoaiSanPhamController().sua(loai)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadLoaiSP();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        });


    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<SanPham> list = controller.layTatCa();

        for (SanPham sp : list) {
            tableModel.addRow(toRow(sp));
        }
    }

    private void timKiem(ActionEvent e) {
        String tieuChi = (String) cmbTimKiem.getSelectedItem();
        String tuKhoa = txtTuKhoa.getText().trim();

        List<SanPham> ketQua;

        if (tieuChi.equals("Tên sản phẩm")) {
            ketQua = controller.timTheoTen(tuKhoa);
        } else {
            ketQua = controller.timTheoLoai(tuKhoa);
        }

        tableModel.setRowCount(0);
        for (SanPham sp : ketQua) {
            tableModel.addRow(toRow(sp));
        }
    }

    private Object[] toRow(SanPham sp) {
        return new Object[]{
            sp.getMaSP(),
            sp.getTenSP(),
            sp.getMaLoaiSP(),
            sp.getGiaNhap(),
            sp.getGiaBan(),
            sp.getDonVi(),
            sp.getNgayHetHan(),
            sp.getNgayTao(),
            sp.getNgayCapNhat()
        };
    }
    
    private void loadLoaiSP() {
        modelLoai.setRowCount(0);
        List<LoaiSanPham> dsLoai = new LoaiSanPhamController().layTatCa();
        for (LoaiSanPham loai : dsLoai) {
            modelLoai.addRow(new Object[]{
                loai.getMaLoaiSP(),
                loai.getTenLoaiSP()
            });
        }
    }

}
