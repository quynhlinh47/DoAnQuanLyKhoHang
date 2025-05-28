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
	
	private ThongKepanel thongkePanel;

	public QuanLyKhoHangGUI() {
		setTitle("Quản Lý Kho Hàng");
		setSize(1180, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		ImageIcon logo = new ImageIcon("src/hinhanh/logo.png");
		setIconImage(logo.getImage());

		// Panel chứa các nút bên trái
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(new Color(0x005580));
		leftPanel.setPreferredSize(new Dimension(230, getHeight()));

		JButton button1 = new JButton("QUẢN LÝ SẢN PHẨM", new ImageIcon("src/hinhanh/product.png"));
		JButton button2 = new JButton("QUẢN LÝ NHÀ CUNG CẤP", new ImageIcon("src/hinhanh/nhacc.png"));
		JButton button3 = new JButton("NHẬP HÀNG", new ImageIcon("src/hinhanh/import.png"));
		JButton button4 = new JButton("XUẤT HÀNG",new ImageIcon("src/hinhanh/export.png"));
		JButton button5 = new JButton("TỒN KHO",new ImageIcon("src/hinhanh/tonkho.png"));
		JButton button6 = new JButton("THỐNG KÊ", new ImageIcon("src/hinhanh/thongke2.png"));
		JButton button7 = new JButton("THOÁT", new ImageIcon("src/hinhanh/close.png"));

		button1.setHorizontalAlignment(SwingConstants.LEFT);
		button1.setIconTextGap(5); // khoảng cách giữa icon và chữ
		button2.setHorizontalAlignment(SwingConstants.LEFT);
		button2.setIconTextGap(1);
		button3.setHorizontalAlignment(SwingConstants.LEFT);
		button3.setIconTextGap(5);
		button4.setHorizontalAlignment(SwingConstants.LEFT);
		button4.setIconTextGap(5);
		button5.setHorizontalAlignment(SwingConstants.LEFT);
		button5.setIconTextGap(5);
		button6.setHorizontalAlignment(SwingConstants.LEFT);
		button6.setIconTextGap(5);
		button7.setHorizontalAlignment(SwingConstants.LEFT);
		button7.setIconTextGap(10);
		
		//làm phẳng nút
		button1.setFocusPainted(false);
		button2.setFocusPainted(false);
		button3.setFocusPainted(false);
		button4.setFocusPainted(false);
		button5.setFocusPainted(false);
		button6.setFocusPainted(false);
		button7.setFocusPainted(false);
		
		//màu nút
		button1.setBackground(new Color(0xC6E2FF));
		button2.setBackground(new Color(0xC6E2FF));
		button3.setBackground(new Color(0xC6E2FF));
		button4.setBackground(new Color(0xC6E2FF));
		button5.setBackground(new Color(0xC6E2FF));
		button6.setBackground(new Color(0xC6E2FF));
		button7.setBackground(new Color(0xC6E2FF));
		
		Dimension buttonSize = new Dimension(200, 35);
		button1.setPreferredSize(buttonSize);
		button2.setPreferredSize(buttonSize);
		button3.setPreferredSize(buttonSize);
		button4.setPreferredSize(buttonSize);
		button5.setPreferredSize(buttonSize);
		button6.setPreferredSize(buttonSize);
		button7.setPreferredSize(buttonSize);

		button1.setAlignmentX(Component.LEFT_ALIGNMENT);
		button2.setAlignmentX(Component.LEFT_ALIGNMENT);
		button3.setAlignmentX(Component.LEFT_ALIGNMENT);
		button4.setAlignmentX(Component.LEFT_ALIGNMENT);
		button5.setAlignmentX(Component.LEFT_ALIGNMENT);
		button6.setAlignmentX(Component.LEFT_ALIGNMENT);
		button7.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.setBackground(new Color(0x005580));
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
		thongkePanel = new ThongKepanel();
		

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
		button6.addActionListener(e ->{
		    thongkePanel.reloadThongKe();
		    cardLayout.show(mainPanel, "THONG_KE");
		});
		button7.addActionListener(e -> System.exit(0));

		// gắn vào frame
		add(leftPanel, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);
		
		
		setVisible(true);
	}

}
