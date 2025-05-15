package controller;

import dao.NhaCungCapDAO;
import model.NhaCungCap;
import java.util.List;

public class NhaCungCapController {
    private NhaCungCapDAO dao = new NhaCungCapDAO();

    public List<NhaCungCap> layTatCa() {
        return dao.getAll();
    }

    public boolean them(NhaCungCap ncc) {
        return dao.insert(ncc);
    }

    public boolean sua(NhaCungCap ncc) {
        return dao.update(ncc);
    }

    public boolean xoa(String maNCC) {
        return dao.delete(maNCC);
    }
}
