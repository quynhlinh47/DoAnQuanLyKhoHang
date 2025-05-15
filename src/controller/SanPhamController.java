package controller;

import dao.SanPhamDAO;
import model.SanPham;
import java.util.List;

public class SanPhamController {
    private SanPhamDAO dao = new SanPhamDAO();

    public List<SanPham> layTatCa() {
        return dao.getAll();
    }

    public boolean them(SanPham sp) {
        return dao.insert(sp);
    }

    public boolean sua(SanPham sp) {
        return dao.update(sp);
    }

    public boolean xoa(String maSP) {
        return dao.delete(maSP);
    }

    public SanPham timTheoMa(String maSP) {
        return dao.getById(maSP);
    }
}
