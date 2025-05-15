package view;

import java.awt.*;
import javax.swing.*;
import view.SanPhamPanel;
import view.NhaCungCapPanel;
import view.NhapHangPanel;
import view.XuatHangPanel;
import view.ThongKepanel;
import view.TonKhoPanel;

public class QuanLyKhoHangGUI extends JFrame {

	JPanel mainPanel;
	CardLayout cardLayout;

	public QuanLyKhoHangGUI() {
		setTitle("Quản Lý Kho Hàng");
		setSize(1100, 650);
		setResizable(false); // ko thay đổi kích thước của cửa sổ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Panel chứa các nút bên trái
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.LIGHT_GRAY);
		leftPanel.setPreferredSize(new Dimension(150, getHeight()));

		JButton button1 = new JButton("SẢN PHẨM");
		JButton button2 = new JButton("NHÀ CUNG CẤP");
		JButton button3 = new JButton("NHẬP HÀNG");
		JButton button4 = new JButton("XUẤT HÀNG");
		JButton button5 = new JButton("TỒN KHO");
		JButton button6 = new JButton("THỐNG KÊ");
		JButton button7 = new JButton("THOÁT");

		Dimension buttonSize = new Dimension(120, 30);
		button1.setPreferredSize(buttonSize);
		button2.setPreferredSize(buttonSize);
		button3.setPreferredSize(buttonSize);
		button4.setPreferredSize(buttonSize);
		button5.setPreferredSize(buttonSize);
		button6.setPreferredSize(buttonSize);
		button7.setPreferredSize(buttonSize);

		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		button5.setAlignmentX(Component.CENTER_ALIGNMENT);
		button6.setAlignmentX(Component.CENTER_ALIGNMENT);
		button7.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.add(button1);
		topPanel.add(button2);
		topPanel.add(button3);
		topPanel.add(button4);
		topPanel.add(button5);
		topPanel.add(button6);
		topPanel.add(button7);

		leftPanel.add(topPanel);

		// Panel chính để chuyển đổi màn hình
		mainPanel = new JPanel();
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);

		// Thêm các màn hình vào mainPanel
		JPanel sanphamPanel = new SanPhamPanel();
		JPanel nhacungcapPanel = new NhaCungCapPanel();
		JPanel nhaphangPanel = new NhapHangPanel();
		JPanel xuathangPanel = new XuatHangPanel();
		JPanel tonkhoPanel = new TonKhoPanel();
		JPanel thongkePanel = new ThongKepanel();

		mainPanel.add(sanphamPanel, "SAN_PHAM");
		mainPanel.add(nhacungcapPanel, "NHA_CUNG_CAP");
		mainPanel.add(nhaphangPanel, "NHAP_HANG");
		mainPanel.add(xuathangPanel, "XUAT_HANG");
		mainPanel.add(tonkhoPanel, "TON_KHO");
		mainPanel.add(thongkePanel, "THONG_KE");

		// Gắn các sự kiện nút để chuyển đổi panel
		button1.addActionListener(e -> cardLayout.show(mainPanel, "SAN_PHAM"));
		button2.addActionListener(e -> cardLayout.show(mainPanel, "NHA_CUNG_CAP"));
		button3.addActionListener(e -> cardLayout.show(mainPanel, "NHAP_HANG"));
		button4.addActionListener(e -> cardLayout.show(mainPanel, "XUAT_HANG"));
		button5.addActionListener(e -> cardLayout.show(mainPanel, "TON_KHO"));
		button6.addActionListener(e -> cardLayout.show(mainPanel, "THONG_KE"));
		button7.addActionListener(e -> System.exit(0));

		// gắn vào frame
		add(leftPanel, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new QuanLyKhoHangGUI());
	}

}
