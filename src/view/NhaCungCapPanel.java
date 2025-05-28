package view;

import controller.NhaCungCapController;
import model.NhaCungCap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class NhaCungCapPanel extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel;
	private NhaCungCapController controller;
	
	private JButton addButton, deleteButton, updateButton, searchButton, lammoiButton;
	private JTextField maField, tenField, nguoilienheField, sdtField, emailField, diachiField, searchField;


	public NhaCungCapPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		JPanel thongtinNCC = thongtinNCC();

		String[] column = { "Mã NCC", "Tên NCC","Người liên hệ", "Số điện thoại", "Email", "Địa chỉ" };
		tableModel = new DefaultTableModel(column, 0);

		table = new JTable(tableModel);
		JScrollPane tableScroll = new JScrollPane(table);

		add(thongtinNCC, BorderLayout.CENTER);
		add(tableScroll, BorderLayout.SOUTH);
		
		controller = new NhaCungCapController();

		
		loadData();
		addEventHandlers();
		
	}

	private void loadData() {
		tableModel.setRowCount(0);
        List<NhaCungCap> list = controller.layTatCa();

        for (NhaCungCap sp : list) {
            tableModel.addRow(toRow(sp));
        }
		
	}
	private Object[] toRow(NhaCungCap sp) {
        return new Object[]{
            sp.getMaNCC(),
            sp.getTenNCC(),
            sp.getNguoilienhe(),
            sp.getSdt(),
            sp.getEmail(),
            sp.getDiaChi()
        };
    }

	private JPanel thongtinNCC() {
		JPanel thongtin = new JPanel();
		thongtin.setLayout(null);

		JLabel maNCC = new JLabel("Mã nhà cung cấp:");
		maNCC.setBounds(10, 20, 100, 20);
		maField = new JTextField();
		maField.setBounds(130, 20, 180, 25);
		thongtin.add(maNCC);
		thongtin.add(maField);

		JLabel tenNCC = new JLabel("Tên nhà cung cấp:");
		tenNCC.setBounds(10, 50, 150, 20);
	    tenField = new JTextField();
		tenField.setBounds(130, 50, 180, 25);
		thongtin.add(tenNCC);
		thongtin.add(tenField);

		JLabel nguoilienhe = new JLabel("Người liên hệ:");
		nguoilienhe.setBounds(10, 80, 100, 20);
		nguoilienheField = new JTextField();
		nguoilienheField.setBounds(130, 80, 180, 25);
		thongtin.add(nguoilienhe);
		thongtin.add(nguoilienheField);
		
		JLabel email = new JLabel("Email:"); 
   	    email.setBounds(330, 20, 100, 20);
        emailField = new JTextField();  
        emailField.setBounds(400, 20, 180, 25);
        thongtin.add(email);
	    thongtin.add(emailField);

	    JLabel diachi = new JLabel("Địa chỉ:"); 
	    diachi.setBounds(330, 50, 100, 20);
	    diachiField = new JTextField();  
	    diachiField.setBounds(400, 50, 180, 25);
        thongtin.add(diachi);
	    thongtin.add(diachiField);
	    
	    JLabel sdt = new JLabel("Số điện thoại:"); 
	    sdt.setBounds(330, 80, 150, 20);
	    sdtField = new JTextField();  
	    sdtField.setBounds(410, 80, 170, 25);
        thongtin.add(sdt);
	    thongtin.add(sdtField);
	    
	    
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
        
        ImageIcon lammoi = new ImageIcon("src/hinhanh/lammoi.png");
        Image iconlammoi = lammoi.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(iconlammoi);
	    
	    addButton = new JButton("Thêm", resizedIcon1);   
        addButton.setBounds(600, 30, 90, 25);
        updateButton = new JButton("Cập nhật", resizedIcon3);
        updateButton.setBounds(690, 30, 110, 25);
        deleteButton = new JButton("Xóa", resizedIcon2);
        deleteButton.setBounds(800, 30, 80,25);
        lammoiButton = new JButton(resizedIcon5);
        lammoiButton.setBounds(830, 80, 40, 25);
        
        searchField = new JTextField();
        searchField.setBounds(600, 80, 180, 25);
        searchButton = new JButton(resizedIcon4);
        searchButton.setBounds(780, 80, 40, 25);
        
        
   	    thongtin.add(addButton);
   	    thongtin.add(updateButton);
   	    thongtin.add(deleteButton);
   	    thongtin.add(searchField);
   	    thongtin.add(searchButton);
   	    thongtin.add(lammoiButton);
   	   
   	    
		return thongtin;
	}
	private void addEventHandlers() {
        addButton.addActionListener(e -> {
            NhaCungCap ncc = new NhaCungCap(
                    maField.getText(), tenField.getText(), nguoilienheField.getText(),
                    sdtField.getText(), emailField.getText(), diachiField.getText()
            );
            if (controller.them(ncc)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        });

        updateButton.addActionListener(e -> {
            NhaCungCap ncc = new NhaCungCap(
                    maField.getText(), tenField.getText(), nguoilienheField.getText(),
                    sdtField.getText(), emailField.getText(), diachiField.getText()
            );
            if (controller.sua(ncc)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String ma = tableModel.getValueAt(selectedRow, 0).toString();
                if (controller.xoa(ma)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa");
            }
        });

        searchButton.addActionListener(e -> {
            String ten = searchField.getText();
            List<NhaCungCap> list = controller.timTheoTen(ten);
            tableModel.setRowCount(0);
            for (NhaCungCap ncc : list) {
                tableModel.addRow(toRow(ncc));
            }
        });

        lammoiButton.addActionListener(e -> {
            maField.setText("");
            tenField.setText("");
            nguoilienheField.setText("");
            sdtField.setText("");
            emailField.setText("");
            diachiField.setText("");
            searchField.setText("");
            loadData();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                maField.setText(tableModel.getValueAt(row, 0).toString());
                tenField.setText(tableModel.getValueAt(row, 1).toString());
                nguoilienheField.setText(tableModel.getValueAt(row, 2).toString());
                sdtField.setText(tableModel.getValueAt(row, 3).toString());
                emailField.setText(tableModel.getValueAt(row, 4).toString());
                diachiField.setText(tableModel.getValueAt(row, 5).toString());
                
                maField.setEnabled(false);
            }
        });
    }
	
}
