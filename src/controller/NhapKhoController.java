package controller;

import dao.NhapKhoDAO;
import model.PhieuNhapKho;
import java.util.List;

public class NhapKhoController {
    private NhapKhoDAO dao = new NhapKhoDAO();

    public List<PhieuNhapKho> layTatCa() {
        return dao.getAll();
    }

    public boolean them(PhieuNhapKho nk) {
        return dao.insert(nk);
    }

    public boolean xoa(String maNhapKho) {
        return dao.delete(maNhapKho);
    }
}
