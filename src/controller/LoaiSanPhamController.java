package controller;

import dao.LoaiSanPhamDAO;
import model.LoaiSanPham;
import java.util.List;

public class LoaiSanPhamController {
    private LoaiSanPhamDAO loaiSPDAO = new LoaiSanPhamDAO();

    public List<LoaiSanPham> layTatCa() {
        return loaiSPDAO.getAllLoaiSP();
    }

    public boolean them(LoaiSanPham lsp) {
        return loaiSPDAO.insertLoaiSP(lsp);
    }

    public boolean sua(LoaiSanPham lsp) {
        return loaiSPDAO.updateLoaiSP(lsp);
    }

    public boolean xoa(String maLoaiSP) {
        return loaiSPDAO.deleteLoaiSP(maLoaiSP);
    }
}
