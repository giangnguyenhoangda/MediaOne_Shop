/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import model.QueryDiaPhim;
import object.DiaPhim;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLDiaPhim {

    private QueryDiaPhim qDiaPhim;

    public BLLDiaPhim() {
        qDiaPhim = new QueryDiaPhim();
    }

    public ArrayList<DiaPhim> layToanBoDiaPhim() {
        return qDiaPhim.layToanBoDiaPhim();
    }

    public ArrayList<DiaPhim> timKiemDiaPhim(String tenDia, String daoDien, String dienVien, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, boolean timGanDung) {
        DiaPhim s = new DiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaPhim.timKiemDiaPhim(s, timGanDung);
    }

    public ArrayList<DiaPhim> timKiemDiaPhim(String tenDia, String daoDien, String dienVien, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, String doiTuong, Integer min, Integer max, boolean timGanDung) {
        DiaPhim s = new DiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaPhim.timKiemDiaPhim(s, doiTuong, min, max, timGanDung);
    }

    public ArrayList<DiaPhim> timKiemDiaPhim(String tenDia, String daoDien, String dienVien, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, String doiTuong1, Integer min1, Integer max1,
            String doiTuong2, Integer min2, Integer max2, boolean timGanDung) {
        DiaPhim s = new DiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaPhim.timKiemDiaPhim(s, doiTuong1, min1, max1, doiTuong2, min2, max2, timGanDung);
    }

    public ArrayList<DiaPhim> timKiemDiaPhim(String tenDia, String daoDien, String dienVien, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, Integer giaMuaMin, Integer giaMuaMax,
            Integer giaBanMin, Integer giaBanMax, Integer soLuongMin, Integer soLuongMax, boolean timGanDung) {
        DiaPhim s = new DiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaPhim.timKiemDiaPhim(s, giaMuaMin, giaMuaMax, giaBanMin, giaBanMax, soLuongMin, soLuongMax, timGanDung);
    }

    public Integer themDiaPhim(String tenDia, String daoDien, String dienVien, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        DiaPhim s = new DiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaPhim.themDiaPhim(s);
    }

    public Integer suaDiaPhim(String tenDia, String daoDien, String dienVien, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        DiaPhim s = new DiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaPhim.suaDiaNhac(s);
    }

    public Integer xoaDiaPhim(String tenDia) {
        DiaPhim s = new DiaPhim();
        s.setTenSanPham(tenDia);
        return qDiaPhim.xoaDiaPhim(s);
    }

    public ArrayList<DiaPhim> diaPhimNhapNgay(Date ngay) {
        return qDiaPhim.diaPhimNhapNgay(ngay);
    }

    public ArrayList<DiaPhim> diaPhimNhapNgay(Date ngayMin, Date ngayMax) {
        return qDiaPhim.diaPhimNhapNgay(ngayMin, ngayMax);
    }

    public ArrayList<DiaPhim> diaPhimNhapThangNam(int thang, int nam) {
        return qDiaPhim.diaPhimNhapThangNam(thang, nam);
    }

    public ArrayList<DiaPhim> diaPhimNhapNam(int nam) {
        return qDiaPhim.diaPhimNhapNam(nam);
    }

    public ArrayList<DiaPhim> diaPhimBanNgay(Date ngay) {
        return qDiaPhim.diaPhimBanNgay(ngay);
    }

    public ArrayList<DiaPhim> diaPhimBanNgay(Date ngayMin, Date ngayMax) {
        return qDiaPhim.diaPhimBanNgay(ngayMin, ngayMax);
    }

    public ArrayList<DiaPhim> diaPhimBanThangNam(int thang, int nam) {
        return qDiaPhim.diaPhimBanThang(thang, nam);
    }

    public ArrayList<DiaPhim> diaPhimBanNam(int nam) {
        return qDiaPhim.diaPhimBanNam(nam);
    }
}
