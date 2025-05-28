package view;

import model.SanPham;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class SanPhamDialog extends JDialog {

    private JTextField txtMaSP, txtTenSP, txtMaLoai, txtGiaNhap, txtGiaBan, txtDonVi;
    private JSpinner spnHetHan, spnNgayTao, spnNgayCapNhat;
    private boolean isConfirmed = false;

    public SanPhamDialog(Frame owner, String title, SanPham sanPham) {
        super(owner, title, true);
        setSize(450, 500);
        setLocationRelativeTo(owner);
        setLayout(null);

        Font font = new Font("Arial", Font.PLAIN, 14);
        
        
        ImageIcon luu = new ImageIcon("src/hinhanh/luu.png");
        Image luuicon = luu.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(luuicon);
        
        ImageIcon huy = new ImageIcon("src/hinhanh/close.png");
        Image huyicon = huy.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(huyicon);
        

        int labelX = 30, fieldX = 160, width = 230, height = 25, y = 20, dy = 40;

        JLabel lblMaSP = new JLabel("Mã SP:");
        lblMaSP.setBounds(labelX, y, 100, height);
        txtMaSP = new JTextField();
        txtMaSP.setBounds(fieldX, y, width, height);

        JLabel lblTenSP = new JLabel("Tên SP:");
        y += dy;
        lblTenSP.setBounds(labelX, y, 100, height);
        txtTenSP = new JTextField();
        txtTenSP.setBounds(fieldX, y, width, height);

        JLabel lblMaLoai = new JLabel("Mã Loại SP:");
        y += dy;
        lblMaLoai.setBounds(labelX, y, 100, height);
        txtMaLoai = new JTextField();
        txtMaLoai.setBounds(fieldX, y, width, height);

        JLabel lblGiaNhap = new JLabel("Giá Nhập:");
        y += dy;
        lblGiaNhap.setBounds(labelX, y, 100, height);
        txtGiaNhap = new JTextField();
        txtGiaNhap.setBounds(fieldX, y, width, height);

        JLabel lblGiaBan = new JLabel("Giá Bán:");
        y += dy;
        lblGiaBan.setBounds(labelX, y, 100, height);
        txtGiaBan = new JTextField();
        txtGiaBan.setBounds(fieldX, y, width, height);

        JLabel lblDonVi = new JLabel("Đơn vị:");
        y += dy;
        lblDonVi.setBounds(labelX, y, 100, height);
        txtDonVi = new JTextField();
        txtDonVi.setBounds(fieldX, y, width, height);

        JLabel lblHetHan = new JLabel("Ngày hết hạn:");
        y += dy;
        lblHetHan.setBounds(labelX, y, 120, height);
        spnHetHan = new JSpinner(new SpinnerDateModel());
        spnHetHan.setEditor(new JSpinner.DateEditor(spnHetHan, "yyyy-MM-dd"));
        spnHetHan.setBounds(fieldX, y, width, height);

        JLabel lblNgayTao = new JLabel("Ngày tạo:");
        y += dy;
        lblNgayTao.setBounds(labelX, y, 100, height);
        spnNgayTao = new JSpinner(new SpinnerDateModel());
        spnNgayTao.setEditor(new JSpinner.DateEditor(spnNgayTao, "yyyy-MM-dd"));
        spnNgayTao.setBounds(fieldX, y, width, height);

        JLabel lblNgayCapNhat = new JLabel("Ngày cập nhật:");
        y += dy;
        lblNgayCapNhat.setBounds(labelX, y, 120, height);
        spnNgayCapNhat = new JSpinner(new SpinnerDateModel());
        spnNgayCapNhat.setEditor(new JSpinner.DateEditor(spnNgayCapNhat, "yyyy-MM-dd"));
        spnNgayCapNhat.setBounds(fieldX, y, width, height);

        JButton btnOK = new JButton("Lưu",resizedIcon1);
        JButton btnCancel = new JButton("Huỷ", resizedIcon2);
        y += dy + 10;
        btnOK.setBounds(100, y, 100, 30);
        btnCancel.setBounds(230, y, 100, 30);

      
        add(lblMaSP); add(txtMaSP);
        add(lblTenSP); add(txtTenSP);
        add(lblMaLoai); add(txtMaLoai);
        add(lblGiaNhap); add(txtGiaNhap);
        add(lblGiaBan); add(txtGiaBan);
        add(lblDonVi); add(txtDonVi);
        add(lblHetHan); add(spnHetHan);
        add(lblNgayTao); add(spnNgayTao);
        add(lblNgayCapNhat); add(spnNgayCapNhat);
        add(btnOK); add(btnCancel);

        // Gán dữ liệu nếu sửa
        if (sanPham != null) {
            txtMaSP.setText(sanPham.getMaSP());
            txtMaSP.setEditable(false);
            txtTenSP.setText(sanPham.getTenSP());
            txtMaLoai.setText(sanPham.getMaLoaiSP());
            txtGiaNhap.setText(String.valueOf(sanPham.getGiaNhap()));
            txtGiaBan.setText(String.valueOf(sanPham.getGiaBan()));
            txtDonVi.setText(sanPham.getDonVi());
            spnHetHan.setValue(sanPham.getNgayHetHan());
            spnNgayTao.setValue(sanPham.getNgayTao());
            spnNgayCapNhat.setValue(sanPham.getNgayCapNhat());
        } else {
            Date now = new Date();
            spnHetHan.setValue(now);
            spnNgayTao.setValue(now);
            spnNgayCapNhat.setValue(now);
        }

        btnOK.addActionListener(e -> {
            isConfirmed = true;
            setVisible(false);
        });

        btnCancel.addActionListener(e -> {
            isConfirmed = false;
            setVisible(false);
        });
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public SanPham getSanPham() {
        try {
            SanPham sp = new SanPham();
            sp.setMaSP(txtMaSP.getText().trim());
            sp.setTenSP(txtTenSP.getText().trim());
            sp.setMaLoaiSP(txtMaLoai.getText().trim());
            sp.setGiaNhap(Double.parseDouble(txtGiaNhap.getText().trim()));
            sp.setGiaBan(Double.parseDouble(txtGiaBan.getText().trim()));
            sp.setDonVi(txtDonVi.getText().trim());
            sp.setNgayHetHan((Date) spnHetHan.getValue());
            sp.setNgayTao((Date) spnNgayTao.getValue());
            sp.setNgayCapNhat((Date) spnNgayCapNhat.getValue());
            return sp;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}