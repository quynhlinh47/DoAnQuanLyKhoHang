package view;

import java.awt.*;
import java.awt.event.HierarchyEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.NhapKhoController;
import dao.NhaCungCapDAO;
import dao.SanPhamDAO;
import model.NhaCungCap;
import model.PhieuNhapKho;
import model.SanPham;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NhapHangPanel extends JPanel {
	private JTable table;
	private DefaultTableModel nhapkhoTable;
	private NhapKhoController controller;

	private SanPhamDAO sanPhamDAO = new SanPhamDAO();
	private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();

	private JTextField maNKfield, soLuongField, giaNhapField, ngayNhapField;
	private JComboBox<String> comboMaSP, comboMaNCC, comboDonvi;
	private JButton btnThem, btnXoa, btnSua, btnLammoi;

	public NhapHangPanel() {
		setLayout(null);

		controller = new NhapKhoController();

		String[] column = { "Mã NK", "Mã SP","Tên SP", "Mã NCC", "Số lượng", "Đơn vị", "Giá nhập", "Ngày nhập" };
		nhapkhoTable = new DefaultTableModel(column, 0);
		table = new JTable(nhapkhoTable);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setBounds(10, 10, 550, 550);
		add(tableScroll);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(null);
		formPanel.setBounds(580, 10, 350, 400);
		formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin nhập kho"));
		add(formPanel);

		JLabel lblMaNK = new JLabel("Mã nhập kho:");
		lblMaNK.setBounds(20, 30, 100, 25);
		formPanel.add(lblMaNK);

		maNKfield = new JTextField();
		maNKfield.setBounds(130, 30, 180, 25);
		formPanel.add(maNKfield);

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setBounds(20, 70, 100, 25);
		formPanel.add(lblMaSP);

		comboMaSP = new JComboBox<>();
		comboMaSP.setBounds(130, 70, 180, 25);
		formPanel.add(comboMaSP);

		JLabel lblMaNCC = new JLabel("Mã nhà CC:");
		lblMaNCC.setBounds(20, 110, 100, 25);
		formPanel.add(lblMaNCC);

		comboMaNCC = new JComboBox<>();
		comboMaNCC.setBounds(130, 110, 180, 25);
		formPanel.add(comboMaNCC);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setBounds(20, 150, 100, 25);
		formPanel.add(lblSoLuong);

		soLuongField = new JTextField();
		soLuongField.setBounds(130, 150, 180, 25);
		formPanel.add(soLuongField);
		
		JLabel lblDonVi = new JLabel("Đơn vị:");
		lblDonVi.setBounds(20, 190, 100, 25);
		formPanel.add(lblDonVi);

		comboDonvi = new JComboBox<>();
		comboDonvi.setBounds(130, 190, 180, 25);
		formPanel.add(comboDonvi);


		JLabel lblGiaNhap = new JLabel("Giá nhập:");
		lblGiaNhap.setBounds(20, 230, 100, 25);
		formPanel.add(lblGiaNhap);

		giaNhapField = new JTextField();
		giaNhapField.setBounds(130, 230, 180, 25);
		formPanel.add(giaNhapField);

		JLabel lblNgayNhap = new JLabel("Ngày nhập:");
		lblNgayNhap.setBounds(20, 270, 100, 25);
		formPanel.add(lblNgayNhap);

		ngayNhapField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		ngayNhapField.setBounds(130, 270, 180, 25);
		formPanel.add(ngayNhapField);
		
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
		btnThem.setBounds(100, 320, 140, 30);
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
			
			maNKfield.setText("");
            soLuongField.setText("");
            giaNhapField.setText("");
            ngayNhapField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
            maNKfield.setEnabled(true);
            comboMaSP.setEnabled(true);
            comboMaNCC.setEnabled(true);
            comboDonvi.setEnabled(true);
            ngayNhapField.setEnabled(true);

		});
		btnThem.addActionListener(e -> {
		    String maNK = maNKfield.getText().trim();
		    String soLuongStr = soLuongField.getText().trim();
		    String giaNhapStr = giaNhapField.getText().trim();
		    String ngayNhapStr = ngayNhapField.getText().trim();

		    
		    if (maNK.isEmpty() || soLuongStr.isEmpty() || giaNhapStr.isEmpty() || ngayNhapStr.isEmpty()
		        || comboMaSP.getSelectedItem() == null || comboMaNCC.getSelectedItem() == null || comboDonvi.getSelectedItem() == null) {
		        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
		        return;
		    }

		    try {
		        int soLuong = Integer.parseInt(soLuongStr);
		        double giaNhap = Double.parseDouble(giaNhapStr);
		        Date ngayNhap = new SimpleDateFormat("yyyy-MM-dd").parse(ngayNhapStr);

		        
		        String selectedSP = (String) comboMaSP.getSelectedItem();
		        String selectedNCC = (String) comboMaNCC.getSelectedItem();
		        String donVi = (String) comboDonvi.getSelectedItem();

		        String maSP = selectedSP.split(" - ")[0];
		        String maNCC = selectedNCC.split(" - ")[0];

		       
		        PhieuNhapKho nk = new PhieuNhapKho();
		        nk.setMaNhapKho(maNK);
		        nk.setMaSanPham(maSP);
		        nk.setMaNhaCungCap(maNCC);
		        nk.setSoLuong(soLuong);
		        nk.setDonVi(donVi);
		        nk.setGiaNhap(giaNhap);
		        nk.setNgayNhap(ngayNhap);

		   
		        if (controller.them(nk)) {
		            JOptionPane.showMessageDialog(this, "Thêm phiếu nhập kho thành công!");
		            loadData();
		            
		            maNKfield.setText("");
		            soLuongField.setText("");
		            giaNhapField.setText("");
		            ngayNhapField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		        } else {
		            JOptionPane.showMessageDialog(this, "Thêm thất bại! Mã nhập kho có thể đã tồn tại.");
		        }

		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Số lượng và giá nhập phải là số.");
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày. Dạng đúng: yyyy-MM-dd");
		        ex.printStackTrace();
		    }
		});
		btnXoa.addActionListener(e -> {
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow >= 0) {
		        String maNK = (String) nhapkhoTable.getValueAt(selectedRow, 0);
		        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phiếu " + maNK + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            if (controller.xoa(maNK)) {
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
		        String maNK = maNKfield.getText().trim();

		        try {
		            int soLuong = Integer.parseInt(soLuongField.getText().trim());
		            double giaNhap = Double.parseDouble(giaNhapField.getText().trim());

		            // Lấy phiếu cũ
		            PhieuNhapKho nk = controller.layTatCa().stream()
		                    .filter(p -> p.getMaNhapKho().equals(maNK))
		                    .findFirst().orElse(null);

		            if (nk != null) {
		                nk.setSoLuong(soLuong);
		                nk.setGiaNhap(giaNhap);

		                if (controller.xoa(maNK) && controller.them(nk)) {
		                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
		                    loadData();
		                } else {
		                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
		                }
		            } else {
		                JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu để cập nhật.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(this, "Số lượng và giá nhập phải là số!");
		        }

		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa.");
		    }
		});
		
		
		table.getSelectionModel().addListSelectionListener(event -> {
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow >= 0) {
		        maNKfield.setText(nhapkhoTable.getValueAt(selectedRow, 0).toString());
	
		        String maSP = nhapkhoTable.getValueAt(selectedRow, 1).toString();
		        for (int i = 0; i < comboMaSP.getItemCount(); i++) {
		            if (comboMaSP.getItemAt(i).startsWith(maSP + " -")) {
		                comboMaSP.setSelectedIndex(i);
		                break;
		            }
		        }
		        String maNCC = nhapkhoTable.getValueAt(selectedRow, 3).toString();
		        for (int i = 0; i < comboMaNCC.getItemCount(); i++) {
		            if (comboMaNCC.getItemAt(i).startsWith(maNCC + " -")) {
		                comboMaNCC.setSelectedIndex(i);
		                break;
		            }
		        }

		        soLuongField.setText(nhapkhoTable.getValueAt(selectedRow, 4).toString());
		        comboDonvi.setSelectedItem(nhapkhoTable.getValueAt(selectedRow, 5).toString());
		        giaNhapField.setText(nhapkhoTable.getValueAt(selectedRow, 6).toString());
		        ngayNhapField.setText(nhapkhoTable.getValueAt(selectedRow, 7).toString());

		        //ko cho chỉnh
		        maNKfield.setEnabled(false);
		        comboMaSP.setEnabled(false);
		        comboMaNCC.setEnabled(false);
		        comboDonvi.setEnabled(false);
		        ngayNhapField.setEnabled(false);
		    }
		});


		
		this.addHierarchyListener(e->{
			if((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && this.isShowing()) {
				loadComboBoxData();
			}
		});

	}
	

	private void loadData() {
		nhapkhoTable.setRowCount(0);
		List<PhieuNhapKho> list = controller.layTatCa();
		for (PhieuNhapKho nk : list) {
			nhapkhoTable.addRow(toRow(nk));
		}
	}

	private Object[] toRow(PhieuNhapKho nk) {
	    String tenSP = "";
	    SanPham sp = sanPhamDAO.findById(nk.getMaSanPham());
	    if (sp != null) {
	        tenSP = sp.getTenSP();
	    }

	    return new Object[] {
	        nk.getMaNhapKho(),
	        nk.getMaSanPham(),
	        tenSP,
	        nk.getMaNhaCungCap(),
	        nk.getSoLuong(),
	        nk.getDonVi(),
	        nk.getGiaNhap(),
	        new SimpleDateFormat("yyyy-MM-dd").format(nk.getNgayNhap())
	    };
	}


	private void loadComboBoxData() {
		comboMaSP.removeAllItems();
		comboMaNCC.removeAllItems();
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

		Set<String> maNCCSet = new HashSet<>();
		List<NhaCungCap> dsNCC = nhaCungCapDAO.getAll();
		for (NhaCungCap ncc : dsNCC) {
		    String item = ncc.getMaNCC() + " - " + ncc.getTenNCC();
		    if (maNCCSet.add(item)) {
		        comboMaNCC.addItem(item);
		    }
		}
		// Gợi ý đơn vị đúng khi chọn sản phẩm
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