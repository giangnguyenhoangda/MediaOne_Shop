/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import model.QueryDiaNhac;
import object.DiaNhac;
import object.DiaPhim;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLDiaNhac {

    private QueryDiaNhac qDiaNhac;

    public BLLDiaNhac() {
        qDiaNhac = new QueryDiaNhac();
    }

    public ArrayList<DiaNhac> layToanBoDiaNhac() {
        return qDiaNhac.layToanBoDiaNhac();
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        DiaNhac s = new DiaNhac(tenDia, caSi, nhaSanXuat, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaNhac.timKiemDiaNhac(s);
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namPhatHanh, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, boolean timGanDung) {
        DiaNhac s = new DiaNhac(tenDia, caSi, nhaSanXuat, namPhatHanh, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaNhac.timKiemDiaNhac(s, timGanDung);
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namPhatHanh, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, String doiTuong, Integer min, Integer max, boolean timGanDung) {
        DiaNhac s = new DiaNhac(tenDia, caSi, nhaSanXuat, namPhatHanh, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaNhac.timKiemDiaNhac(s, doiTuong, min, max, timGanDung);
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namPhatHanh, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, String doiTuong1, Integer min1, Integer max1,
            String doiTuong2, Integer min2, Integer max2, boolean timGanDung) {
        DiaNhac s = new DiaNhac(tenDia, caSi, nhaSanXuat, namPhatHanh, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaNhac.timKiemDiaNhac(s, doiTuong1, min1, max1, doiTuong2, min2, max2, timGanDung);
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namPhatHanh, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong, Integer giaMuaMin, Integer giaMuaMax,
            Integer giaBanMin, Integer giaBanMax, Integer soLuongMin, Integer soLuongMax, boolean timGanDung) {
        DiaNhac s = new DiaNhac(tenDia, caSi, nhaSanXuat, namPhatHanh, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaNhac.timKiemDiaNhac(s, giaMuaMin, giaMuaMax, giaBanMin, giaBanMax, soLuongMin, soLuongMax, timGanDung);
    }

    public Integer themDiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        DiaNhac s = new DiaNhac(tenDia, caSi, nhaSanXuat, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaNhac.themDiaNhac(s);
    }

    public Integer suaDiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namXuatBan, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        DiaNhac s = new DiaNhac(tenDia, caSi, nhaSanXuat, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        return qDiaNhac.suaDiaNhac(s);
    }

    public Integer xoaDiaNhac(String tenDia) {
        DiaNhac s = new DiaNhac();
        s.setTenSanPham(tenDia);
        return qDiaNhac.xoaDiaNhac(s);
    }

    public ArrayList<DiaNhac> diaNhacNhapNgay(Date ngay) {
        return qDiaNhac.diaNhacNhapNgay(ngay);
    }

    public ArrayList<DiaNhac> diaNhacNhapNgay(Date ngayMin, Date ngayMax) {
        return qDiaNhac.diaNhacNhapNgay(ngayMin, ngayMax);
    }

    public ArrayList<DiaNhac> diaNhacNhapThangNam(int thang, int nam) {
        return qDiaNhac.diaNhacNhapThangNam(thang, nam);
    }

    public ArrayList<DiaNhac> diaNhacNhapNam(int nam) {
        return qDiaNhac.diaNhacNhapNam(nam);
    }

    public ArrayList<DiaNhac> diaNhacBanNgay(Date ngay) {
        return qDiaNhac.diaNhacBanNgay(ngay);
    }

    public ArrayList<DiaNhac> diaNhacBanNgay(Date ngayMin, Date ngayMax) {
        return qDiaNhac.diaNhacBanNgay(ngayMin, ngayMax);
    }

    public ArrayList<DiaNhac> diaNhacBanThangNam(int thang, int nam) {
        return qDiaNhac.diaNhacBanThang(thang, nam);
    }

    public ArrayList<DiaNhac> diaNhacBanNam(int nam) {
        return qDiaNhac.diaNhacBanNam(nam);
    }
}
