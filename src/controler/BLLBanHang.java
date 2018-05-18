/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import model.QueryBanHang;
import object.BanHang;
import object.SanPham;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLBanHang {

    private QueryBanHang qBanHang;

    public BLLBanHang() {
        qBanHang = new QueryBanHang();
    }

    public int themBanHang(BanHang bh, boolean khachHangVip) {
        return qBanHang.themBanHang(bh, khachHangVip);
    }

    public ArrayList<SanPham> sanPhamBanNgay(Date ngay) {
        return qBanHang.thongKeTheoNgay(ngay);
    }

    public ArrayList<SanPham> sanPhamBanNgay(Date ngayMin, Date ngayMax) {
        return qBanHang.thongKeTheoNgay(ngayMin, ngayMax);
    }

    public ArrayList<SanPham> sanPhamBanThang(int thang, int nam) {
        return qBanHang.thongKeTheoThang(thang, nam);
    }

    public ArrayList<SanPham> sanPhamBanNam(int nam) {
        return qBanHang.thongKeTheoNam(nam);
    }
}
