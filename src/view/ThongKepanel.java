package view;

import controller.ThongKeController;
import dao.ThongKeDAO;
import model.ThongKeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class ThongKepanel extends JPanel {
    private JPanel chartPanel, cardPanel;
    private JTable tableChiTiet;
    private DefaultTableModel tableModel;
    
    ImageIcon sp = new ImageIcon("src/hinhanh/khohang.png");
    Image iconsp = sp.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon1 = new ImageIcon(iconsp);
    
    ImageIcon ncc = new ImageIcon("src/hinhanh/nhacc.png");
    Image iconncc = ncc.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon2 = new ImageIcon(iconncc);

    public ThongKepanel() {
        setLayout(new BorderLayout());

        // ======= Thẻ thống kê tổng quát ========
        cardPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));

        
        cardPanel.add(createStatCard("Sản phẩm trong kho", "30", Color.YELLOW, resizedIcon1 ));
        cardPanel.add(createStatCard("Nhà cung cấp", "8", Color.ORANGE, resizedIcon2 ));
        

        add(cardPanel, BorderLayout.NORTH);

        // ======= Biểu đồ cột (thống kê theo ngày) ========
        chartPanel = new JPanel();
        chartPanel.setPreferredSize(new Dimension(200, 100));
        add(chartPanel, BorderLayout.CENTER);

        // ======= Bảng chi tiết thống kê theo ngày ========
        String[] cols = {"Ngày", "Số lượng nhập", "Số lượng xuất"};
        tableModel = new DefaultTableModel(cols, 0);
        tableChiTiet = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableChiTiet);
        scrollPane.setPreferredSize(new Dimension(300,500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.EAST);

        
        ThongKeController controller = new ThongKeController(this);
        controller.loadThongKe();
    }

    private JPanel createStatCard(String title, String value, Color bgColor, ImageIcon resizedIcon1) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblIcon = new JLabel(resizedIcon1, SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI", Font.PLAIN, 30));

        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(lblIcon, BorderLayout.WEST);
        panel.add(lblValue, BorderLayout.CENTER);
        panel.add(lblTitle, BorderLayout.SOUTH);

        return panel;
    }

    public JPanel getChartPanel() {
        return chartPanel;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void updateStatCard(int sp, int ncc) {
        cardPanel.removeAll();
        cardPanel.add(createStatCard("Sản phẩm trong kho", String.valueOf(sp), Color.YELLOW, resizedIcon1 ));
        cardPanel.add(createStatCard("Nhà cung cấp", String.valueOf(ncc), Color.ORANGE, resizedIcon2));
        cardPanel.revalidate();
        cardPanel.repaint();
    }
}
