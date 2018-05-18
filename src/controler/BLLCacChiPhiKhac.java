/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import model.QueryCacLoaiChiPhiKhac;
import object.CacChiPhiKhac;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLCacChiPhiKhac {

    private QueryCacLoaiChiPhiKhac q;

    public BLLCacChiPhiKhac() {
        q = new QueryCacLoaiChiPhiKhac();
    }

    public ArrayList<CacChiPhiKhac> layToanBo() {
        return q.layToanBo();
    }

    public int themChiPhi(String loaiChiPhi, Date ngay, Integer tien) {
        CacChiPhiKhac c = new CacChiPhiKhac(loaiChiPhi, ngay, tien);
        return q.themChiPhiKhac(c);
    }

    public int suaChiPhi(String loaiChiPhi, Date ngay, Integer tien) {
        CacChiPhiKhac c = new CacChiPhiKhac(loaiChiPhi, ngay, tien);
        return q.suaChiPhiKhac(c);
    }

    public int xoaChiPhi(String loaiChiPhi, Date ngay, Integer tien) {
        CacChiPhiKhac c = new CacChiPhiKhac(loaiChiPhi, ngay, tien);
        return q.xoaChiPhiKhac(c);
    }

    public ArrayList<CacChiPhiKhac> thongKeTheoNgay(Date ngay) {
        return q.thongKeTheoNgay(ngay);
    }

    public ArrayList<CacChiPhiKhac> thongKeTheoNgay(Date ngayMin, Date ngayMax) {
        return q.thongKeTheoNgay(ngayMin, ngayMax);
    }

    public ArrayList<CacChiPhiKhac> thongKeTheoThangNam(int thang, int nam) {
        return q.thongKeTheoThangNam(thang, nam);
    }

    public ArrayList<CacChiPhiKhac> thongKeTheoNam(int nam) {
        return q.thongKeTheoNam(nam);
    }
}
