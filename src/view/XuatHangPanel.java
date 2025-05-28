package view;

import java.awt.*;
import java.awt.event.HierarchyEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.XuatKhoController;
import dao.SanPhamDAO;
import model.PhieuXuatKho;
import model.SanPham;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XuatHangPanel extends JPanel {
	private JTable table;
	private DefaultTableModel xuatkhoTable;
	private XuatKhoController controller;

	private SanPhamDAO sanPhamDAO = new SanPhamDAO();

	private JTextField maXKfield, soLuongField, giaBanField, ngayXuatField;
	private JComboBox<String> comboMaSP, comboDonvi;
	private JButton btnThem, btnXoa, btnSua, btnLammoi;
	
	public XuatHangPanel() {
		setLayout(null);
		
		controller = new XuatKhoController();
		
		String[] column = {"Mã XK", "Mã SP", "Tên SP", "Số lượng", "Đơn vị", "Giá bán", "Ngày xuất"};
		xuatkhoTable = new DefaultTableModel(column, 0);
		table = new JTable(xuatkhoTable);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setBounds(10, 10, 550, 550);
		add(tableScroll);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(null);
		formPanel.setBounds(580, 10, 350, 350);
		formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin xuất kho"));
		add(formPanel);
		
		JLabel lblMaXK = new JLabel("Mã xuất kho:");
		lblMaXK.setBounds(20, 30, 100, 25);
		formPanel.add(lblMaXK);

		maXKfield = new JTextField();
		maXKfield.setBounds(130, 30, 180, 25);
		formPanel.add(maXKfield);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setBounds(20, 70, 100, 25);
		formPanel.add(lblMaSP);
		
		comboMaSP = new JComboBox<>();
		comboMaSP.setBounds(130, 70, 180, 25);
		formPanel.add(comboMaSP);
		
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setBounds(20, 110, 100, 25);
		formPanel.add(lblSoLuong);
		
		soLuongField = new JTextField();
		soLuongField.setBounds(130, 110, 180, 25);
		formPanel.add(soLuongField);
		
		JLabel lblDonVi = new JLabel("Đơn vị:");
		lblDonVi.setBounds(20, 150, 100, 25);
		formPanel.add(lblDonVi);

		comboDonvi = new JComboBox<>();
		comboDonvi.setBounds(130, 150, 180, 25);
		formPanel.add(comboDonvi);
		
		JLabel lblGiaNhap = new JLabel("Giá bán:");
		lblGiaNhap.setBounds(20, 190, 100, 25);
		formPanel.add(lblGiaNhap);

		giaBanField = new JTextField();
		giaBanField.setBounds(130, 190, 180, 25);
		formPanel.add(giaBanField);
		
		JLabel lblNgayBan = new JLabel("Ngày xuất:");
		lblNgayBan.setBounds(20, 230, 100, 25);
		formPanel.add(lblNgayBan);

		ngayXuatField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		ngayXuatField.setBounds(130, 230, 180, 25);
		formPanel.add(ngayXuatField);
		
		ImageIcon them = new ImageIcon("src/hinhanh/them1.png");
        Image iconthem = them.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(iconthem);
        
        ImageIcon xoa = new ImageIcon("src/hinhanh/delete.png");
        Image iconxoa = xoa.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(iconxoa);
        
        ImageIcon sua = new ImageIcon("src/hinhanh/edit.png");
        Image iconsua = sua.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(iconsua);

        ImageIcon lammoi = new ImageIcon("src/hinhanh/reset.png");
        Image iconlammoi = lammoi.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(iconlammoi);
        
        btnThem = new JButton("Thêm phiếu", resizedIcon1);
		btnThem.setBounds(100, 270, 140, 30);
		formPanel.add(btnThem);
		
		btnXoa = new JButton("Xóa", resizedIcon2);
		btnXoa.setBounds(150, 570, 90, 30);
		add(btnXoa);

		btnSua = new JButton("Sửa", resizedIcon3);
		btnSua.setBounds(250, 570, 90, 30);
		add(btnSua);
		
		btnLammoi = new JButton("Làm mới", resizedIcon4);
		btnLammoi.setBounds(350, 570, 110, 30);
		add(btnLammoi);
		
		loadData();
		loadComboBoxData();
		
		
		btnLammoi.addActionListener(e->{
			loadData();
			
			maXKfield.setText("");
            soLuongField.setText("");
            giaBanField.setText("");
            ngayXuatField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
            maXKfield.setEnabled(true);
            comboMaSP.setEnabled(true);
            comboDonvi.setEnabled(true);
            ngayXuatField.setEnabled(true);

		});
		btnThem.addActionListener(e -> {
		    String maXK = maXKfield.getText().trim();
		    String soLuongStr = soLuongField.getText().trim();
		    String giaBanStr = giaBanField.getText().trim();
		    String ngayXuatStr = ngayXuatField.getText().trim();

		    
		    if (maXK.isEmpty() || soLuongStr.isEmpty() || giaBanStr.isEmpty() || ngayXuatStr.isEmpty()
		        || comboMaSP.getSelectedItem() == null ||  comboDonvi.getSelectedItem() == null) {
		        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
		        return;
		    }

		    try {
		        int soLuong = Integer.parseInt(soLuongStr);
		        double giaBan = Double.parseDouble(giaBanStr);
		        Date ngayXuat = new SimpleDateFormat("yyyy-MM-dd").parse(ngayXuatStr);

		        
		        String selectedSP = (String) comboMaSP.getSelectedItem();
		        String donVi = (String) comboDonvi.getSelectedItem();

		        String maSP = selectedSP.split(" - ")[0];
		       
		        PhieuXuatKho xk = new PhieuXuatKho();
		        xk.setMaXuatKho(maXK);
		        xk.setMaSanPham(maSP);
		        xk.setSoLuong(soLuong);
		        xk.setDonVi(donVi);
		        xk.setGiaBan(giaBan);
		        xk.setNgayXuat(ngayXuat);

		   
		        if (controller.them(xk)) {
		            JOptionPane.showMessageDialog(this, "Thêm phiếu xuất kho thành công!");
		            loadData();
		            
		            maXKfield.setText("");
		            soLuongField.setText("");
		            giaBanField.setText("");
		            ngayXuatField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		        } else {
		            JOptionPane.showMessageDialog(this, "Thêm thất bại! Mã xuất kho có thể đã tồn tại.");
		        }

		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Số lượng và giá bán phải là số.");
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày. Dạng đúng: yyyy-MM-dd");
		        ex.printStackTrace();
		    }
		});
		btnXoa.addActionListener(e -> {
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow >= 0) {
		        String maXK = (String) xuatkhoTable.getValueAt(selectedRow, 0);
		        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phiếu " + maXK + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            if (controller.xoa(maXK)) {
		                JOptionPane.showMessageDialog(this, "Xóa thành công!");
		                loadData();
		            } else {
		                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa.");
		    }
		});
		btnSua.addActionListener(e -> {
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow >= 0) {
		        String maXK = maXKfield.getText().trim();

		        try {
		            int soLuong = Integer.parseInt(soLuongField.getText().trim());
		            double giaBan = Double.parseDouble(giaBanField.getText().trim());

		            // Lấy phiếu cũ
		            PhieuXuatKho nk = controller.layTatCa().stream()
		                    .filter(p -> p.getMaXuatKho().equals(maXK))
		                    .findFirst().orElse(null);

		            if (nk != null) {
		                nk.setSoLuong(soLuong);
		                nk.setGiaBan(giaBan);

		                if (controller.xoa(maXK) && controller.them(nk)) {
		                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
		                    loadData();
		                } else {
		                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
		                }
		            } else {
		                JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu để cập nhật.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(this, "Số lượng và giá bán phải là số!");
		        }

		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa.");
		    }
		});
		table.getSelectionModel().addListSelectionListener(event -> {
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow >= 0) {
		        maXKfield.setText(xuatkhoTable.getValueAt(selectedRow, 0).toString());
	
		        String maSP = xuatkhoTable.getValueAt(selectedRow, 1).toString();
		        for (int i = 0; i < comboMaSP.getItemCount(); i++) {
		            if (comboMaSP.getItemAt(i).startsWith(maSP + " -")) {
		                comboMaSP.setSelectedIndex(i);
		                break;
		            }
		        }

		        soLuongField.setText(xuatkhoTable.getValueAt(selectedRow, 3).toString());
		        comboDonvi.setSelectedItem(xuatkhoTable.getValueAt(selectedRow, 4).toString());
		        giaBanField.setText(xuatkhoTable.getValueAt(selectedRow, 5).toString());
		        ngayXuatField.setText(xuatkhoTable.getValueAt(selectedRow, 6).toString());

		        //ko cho chỉnh
		        maXKfield.setEnabled(false);
		        comboMaSP.setEnabled(false);
		        comboDonvi.setEnabled(false);
		        ngayXuatField.setEnabled(false);
		    }
		});


		
		this.addHierarchyListener(e->{
			if((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && this.isShowing()) {
				loadComboBoxData();
			}
		});

		
	}
	private void loadData() {
		xuatkhoTable.setRowCount(0);
		List<PhieuXuatKho> list = controller.layTatCa();
		for (PhieuXuatKho nk : list) {
			xuatkhoTable.addRow(toRow(nk));
		}
	}
	private Object[] toRow(PhieuXuatKho xk) {
	    String tenSP = "";
	    SanPham sp = sanPhamDAO.findById(xk.getMaSanPham());
	    if (sp != null) {
	        tenSP = sp.getTenSP();
	    }

	    return new Object[] {
	        xk.getMaXuatKho(),
	        xk.getMaSanPham(),
	        tenSP,
	        xk.getSoLuong(),
	        xk.getDonVi(),
	        xk.getGiaBan(),
	        new SimpleDateFormat("yyyy-MM-dd").format(xk.getNgayXuat())
	    };
	}
	private void loadComboBoxData() {
		comboMaSP.removeAllItems();
		comboDonvi.removeAllItems();

		Set<String> maSPSet = new HashSet<>();
		Set<String> donViSet = new HashSet<>();
		List<SanPham> dsSanPham = sanPhamDAO.getAll();
		for (SanPham sp : dsSanPham) {
		    String item = sp.getMaSP() + " - " + sp.getTenSP();
		    if (maSPSet.add(item)) {
		        comboMaSP.addItem(item);
		    }

		    if (donViSet.add(sp.getDonVi())) {
		        comboDonvi.addItem(sp.getDonVi());
		    }
		}

		comboMaSP.addActionListener(e -> {
		    String selected = (String) comboMaSP.getSelectedItem();
		    if (selected != null) {
		        String maSP = selected.split(" - ")[0];
		        SanPham sp = sanPhamDAO.findById(maSP);
		        if (sp != null) {
		        	comboDonvi.setSelectedItem(sp.getDonVi());

		        }
		    }
		});

	}
	public void reloadComboBoxData() {
	    loadComboBoxData();
	}
}	
	
	
