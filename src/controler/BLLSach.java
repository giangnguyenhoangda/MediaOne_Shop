/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import model.QuerySach;
import object.Sach;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLSach {

    private QuerySach qSach;

    public BLLSach() {
        qSach = new QuerySach();
    }

    public ArrayList<Sach> layToanBoSach() {
        return qSach.layToanBoSach();
    }

    public ArrayList<Sach> timKiemSach(String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, boolean timGanDung) {
        Sach s = new Sach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qSach.timKiemSach(s, timGanDung);
    }

    public ArrayList<Sach> timKiemSach(String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, boolean timGanDung, String doiTuong, int min, int max) {
        Sach s = new Sach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qSach.timKiemSach(timGanDung, s, doiTuong, min, max);
    }

    public ArrayList<Sach> timKiemSach(String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, boolean timGanDung, String doiTuong1, Integer min1, Integer max1, String doiTuong2, Integer min2, Integer max2) {
        Sach s = new Sach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qSach.timKiemSach(timGanDung, s, doiTuong1, min1, max1, doiTuong2, min2, max2);
    }

    public ArrayList<Sach> timKiemSach(boolean timKiemGanDung, String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMuaMin, Integer giaMuaMax, Integer giaBanMin, Integer giaBanMax, Integer soLuongMin, Integer soLuongMax) {
        Sach s = new Sach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, 0, 0, 0);
        return qSach.timKiemSach(timKiemGanDung, s, giaMuaMin, giaMuaMax, giaBanMin, giaBanMax, soLuongMin, soLuongMax);
    }

    public Integer themSach(String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        Sach s = new Sach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qSach.themSach(s);
    }

    public Integer suaSach(String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        Sach s = new Sach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qSach.suaSach(s);
    }

    public Integer xoaSach(String tenSach) {
        Sach s = new Sach();
        s.setTenSanPham(tenSach);
        return qSach.xoaSach(s);
    }

    public ArrayList<Sach> sachNhapNgay(Date ngay) {
        return qSach.sachNhapNgay(ngay);
    }

    public ArrayList<Sach> sachNhapNgay(Date ngayMin, Date ngayMax) {
        return qSach.sachNhapNgay(ngayMin, ngayMax);
    }

    public ArrayList<Sach> sachNhapNam(int nam) {
        return qSach.sachNhapNam(nam);
    }

    public ArrayList<Sach> sachNhapThangNam(int thang, int nam) {
        return qSach.sachNhapThangNam(thang, nam);
    }

    public ArrayList<Sach> sachBanNgay(Date ngay) {
        return qSach.sachBanNgay(ngay);
    }

    public ArrayList<Sach> sachBanNgay(Date ngayMin, Date ngayMax) {
        return qSach.sachBanNgay(ngayMin, ngayMax);
    }

    public ArrayList<Sach> sachBanNam(int nam) {
        return qSach.sachBanNam(nam);
    }

    public ArrayList<Sach> sachBanThangNam(int thang, int nam) {
        return qSach.sachBanThang(thang, nam);
    }
}
