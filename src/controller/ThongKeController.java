package controller;

import dao.ThongKeDAO;
import model.ThongKeModel;
import view.ThongKepanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class ThongKeController {
    private ThongKepanel view;
    private ThongKeDAO dao = new ThongKeDAO();

    public ThongKeController(ThongKepanel view) {
        this.view = view;
    }

    public void loadThongKe() {
        // Tổng số
        int sp = dao.demSanPham();
        int ncc = dao.demNhaCungCap();
        int tongTon = dao.tinhTongTonKho();
        view.updateStatCard(sp, ncc, tongTon);

        // Bảng thống kê chi tiết
        List<ThongKeModel> list = dao.getThongKeTheoNgay();
        var model = view.getTableModel();
        model.setRowCount(0);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ThongKeModel tk : list) {
            model.addRow(new Object[]{tk.getNgay(), tk.getSoLuongNhap(), tk.getSoLuongXuat()});
            dataset.addValue(tk.getSoLuongNhap(), "Nhập", tk.getNgay());
            dataset.addValue(tk.getSoLuongXuat(), "Xuất", tk.getNgay());
        }

        // Biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê nhập/xuất theo ngày", "Ngày", "Số lượng",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(550, 400));

        view.getChartPanel().removeAll();
        view.getChartPanel().add(chartPanel);
        view.getChartPanel().revalidate();
        view.getChartPanel().repaint();

        view.getChartPanel().revalidate();
        view.getChartPanel().repaint();
    }
}
