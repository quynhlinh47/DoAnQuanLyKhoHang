package controller;

import dao.XuatKhoDAO;
import model.PhieuXuatKho;
import java.util.List;

public class XuatKhoController {
    private XuatKhoDAO dao = new XuatKhoDAO();

    public List<PhieuXuatKho> layTatCa() {
        return dao.getAll();
    }

    public boolean them(PhieuXuatKho xk) {
        return dao.insert(xk);
    }

    public boolean xoa(String maXuatKho) {
        return dao.delete(maXuatKho);
    }
}
