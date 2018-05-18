/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QuanLy {

    public int tongDoanhThu(ArrayList<BanHang> danhSachBanHang) {
        int tongDoanhThu = 0;
        for (BanHang bh : danhSachBanHang) {
            for (SanPham sp : bh.getDanhSachSanPhamMua()) {
                tongDoanhThu += sp.getGiaBan() * sp.getSoLuong();
            }
        }
        return tongDoanhThu;
    }

    public int tinhTongLuongNhanVien(ArrayList<NhanVien> danhSachNhanVien) {
        int tongLuongNhanVien = 0;
        for (NhanVien nv : danhSachNhanVien) {
            tongLuongNhanVien += nv.tinhLuong(); // liên kết động ở nv.tinhLuong();
        }
        return tongLuongNhanVien;
    }

    public int tinhLoiNhuan(ArrayList<BanHang> danhSachBanHang, ArrayList<NhanVien> danhSachNhanVien) {
        int tongLoiNhuan = 0;
        int tongDoanhThu = tongDoanhThu(danhSachBanHang);
        int tongLuongNhanVien = tinhTongLuongNhanVien(danhSachNhanVien);
        int tongTienMuaSanPham = 0;
        for (BanHang bh : danhSachBanHang) {
            for (SanPham sp : bh.getDanhSachSanPhamMua()) {
                tongTienMuaSanPham += sp.getGiaMua() * sp.getSoLuong();
            }
        }
        tongLoiNhuan = tongDoanhThu - tongLuongNhanVien - tongTienMuaSanPham;
        return tongLoiNhuan;
    }

    public int tinhTongGiaMuaSanPham(ArrayList<SanPham> ds) {
        int tong = 0;
        for (SanPham sp : ds) {
            tong += sp.getGiaMua() * sp.getSoLuong();
        }
        return tong;
    }

    public int tinhTongGiaBanSanPham(ArrayList<SanPham> ds) {
        int tong = 0;
        for (SanPham sp : ds) {
            tong += sp.getGiaBan() * sp.getSoLuong();
        }
        return tong;
    }

    public int tinhTongLaiGiaSanPham(ArrayList<SanPham> ds) {
        int tong = 0;
        for (SanPham sp : ds) {
            tong += (sp.getGiaBan() - sp.getGiaMua()) * sp.getSoLuong();
        }
        return tong;
    }

    public int tinhTongCacChiPhiKhac(ArrayList<CacChiPhiKhac> ds) {
        int tong = 0;
        for (CacChiPhiKhac c : ds) {
            tong += c.getTienTra();
        }
        return tong;
    }

    public int tinhLoiNhuan(ArrayList<NhanVien> dsNhanVien, ArrayList<SanPham> dsSanPhamMua, ArrayList<SanPham> dsSanPhamBan, ArrayList<CacChiPhiKhac> dsCacChiPhi) {
        int doanhThu = 0;
        int tongluongnhanvien = tinhTongLuongNhanVien(dsNhanVien);
        int tongtienmuasanpham = tinhTongGiaMuaSanPham(dsSanPhamMua);
        int tongtienbansanpham = tinhTongGiaBanSanPham(dsSanPhamBan);
        int tongtiencacchiphikhac = tinhTongCacChiPhiKhac(dsCacChiPhi);
        doanhThu = tongtienbansanpham - tongtienmuasanpham - tongluongnhanvien - tongtiencacchiphikhac;
        return doanhThu;
    }
}
