package view;

import controller.LoaiSanPhamController;
import controller.SanPhamController;
import model.LoaiSanPham;
import model.SanPham;
import view.SanPhamDialog;

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
    private LoaiSanPhamController loaiController;


    private JButton btnThem, btnXoa, btnSua, btnTim, btnLammoi;
    private JComboBox<String> cmbTimKiem;
    private JTextField txtTuKhoa;

    public SanPhamPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        controller = new SanPhamController();
     
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //icon và chỉnh size
        ImageIcon them = new ImageIcon("src/hinhanh/them.png");
        Image iconthem = them.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(iconthem);
        
        ImageIcon xoa = new ImageIcon("src/hinhanh/xoa.png");
        Image iconxoa = xoa.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(iconxoa);
        
        ImageIcon sua = new ImageIcon("src/hinhanh/sua.png");
        Image iconsua = sua.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(iconsua);
        
        ImageIcon tim = new ImageIcon("src/hinhanh/tim.png");
        Image icontim = tim.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(icontim);
        
        ImageIcon lammoi = new ImageIcon("src/hinhanh/reset.png");
        Image iconlammoi = lammoi.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(iconlammoi);
        
        
        btnThem = new JButton("Thêm", resizedIcon1);
        btnXoa = new JButton("Xoá", resizedIcon2);
        btnSua = new JButton("Sửa", resizedIcon3);
        btnTim = new JButton("Tìm", resizedIcon4);
        btnLammoi = new JButton("Làm mới", resizedIcon5);

        cmbTimKiem = new JComboBox<>(new String[]{"Tên sản phẩm", "Mã loại sản phẩm"});
        txtTuKhoa = new JTextField(15);

        topPanel.add(btnThem);
        topPanel.add(btnXoa);
        topPanel.add(btnSua);
        topPanel.add(new JLabel("Tìm kiếm theo:"));
        topPanel.add(cmbTimKiem);
        topPanel.add(txtTuKhoa);
        topPanel.add(btnTim);
        topPanel.add(btnLammoi);

        add(topPanel, BorderLayout.NORTH);

        
        String[] columns = {
            "Mã SP", "Tên SP", "Mã loại SP", "Giá nhập", "Giá bán",
           "Đơn vị", "Ngày hết hạn", "Ngày tạo", "Ngày cập nhật"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        
        
        loaiController = new LoaiSanPhamController();
        
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
        JButton btnThemLoai = new JButton("Thêm Loại", resizedIcon1);
        JButton btnSuaLoai = new JButton("Sửa Loại", resizedIcon3);

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
        
        btnLammoi.addActionListener(e->{
         loadData();
         loadLoaiSP();
         
         txtMaLoai.setText("");
         txtTenLoai.setText("");
         txtMaLoai.setEditable(true);
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
                
                txtMaLoai.setText("");
                txtTenLoai.setText("");
                txtMaLoai.setEditable(true);
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
                
                txtMaLoai.setText("");
                txtTenLoai.setText("");
                txtMaLoai.setEditable(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        });
        
        tableLoai.getSelectionModel().addListSelectionListener(e -> {
            int selected = tableLoai.getSelectedRow();
            if (selected >= 0) {
            	txtMaLoai.setText(modelLoai.getValueAt(selected, 0).toString());
                txtTenLoai.setText(modelLoai.getValueAt(selected, 1).toString());
                txtMaLoai.setEditable(false);
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
        List<LoaiSanPham> dsloai = loaiController.layTatCa();

        for (LoaiSanPham sp : dsloai) {
            modelLoai.addRow(toRowLoai(sp));
        }
    }
    private Object[] toRowLoai(LoaiSanPham sp) {
        return new Object[]{
            sp.getMaLoaiSP(),
            sp.getTenLoaiSP()
        };
    }

}
