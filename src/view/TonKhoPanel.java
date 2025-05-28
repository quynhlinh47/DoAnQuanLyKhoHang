package view;

import controller.TonKhoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TonKhoPanel extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel;

	private JTextField txtTuKhoa;
	private JComboBox<String> searchCombo;
	private JButton btnLammoi, btnTim;

	public TonKhoPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		ImageIcon tim = new ImageIcon("src/hinhanh/tim.png");
		Image icontim = tim.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon4 = new ImageIcon(icontim);

		ImageIcon lammoi = new ImageIcon("src/hinhanh/reset.png");
		Image iconlammoi = lammoi.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon5 = new ImageIcon(iconlammoi);

		btnTim = new JButton("Tìm", resizedIcon4);
		btnLammoi = new JButton("Làm mới", resizedIcon5);

		searchCombo = new JComboBox<>(new String[] { "Tên sản phẩm", "Tên loại sản phẩm" });
		txtTuKhoa = new JTextField(15);

		topPanel.add(new JLabel("Tìm kiếm theo:"));
		topPanel.add(searchCombo);
		topPanel.add(txtTuKhoa);
		topPanel.add(btnTim);
		topPanel.add(btnLammoi);

		add(topPanel, BorderLayout.NORTH);

		String[] columns = { "Mã SP", "Tên SP", "Mã loại SP", "Tên loại SP", "Đơn vị", "Số lượng nhập", "Số lượng xuất",
				"Số lượng tồn" };
		tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		TonKhoController controller = new TonKhoController(table, txtTuKhoa, searchCombo, btnLammoi);
		
		 btnTim.addActionListener(e -> controller.searchTonKho());
		 

	}
}
