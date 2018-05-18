/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BanHang {

    private String maBanHang;
    private NhanVien nhanVienBan;
    private KhachHang khachHangMua;
    private Date ngayBan;
    private ArrayList<SanPham> danhSachSanPhamMua;

    public BanHang() {
        danhSachSanPhamMua = new ArrayList<>();
    }

    public BanHang(String maBanHang, NhanVien nhanVienBan, KhachHang khachHangMua,
            Date ngayBan, ArrayList<SanPham> danhSachSanPhamMua) {
        this.maBanHang = maBanHang;
        this.nhanVienBan = nhanVienBan;
        this.khachHangMua = khachHangMua;
        this.ngayBan = ngayBan;
        this.danhSachSanPhamMua = danhSachSanPhamMua;
    }

    public String getMaBanHang() {
        return maBanHang;
    }

    public void setMaBanHang(String maBanHang) {
        this.maBanHang = maBanHang;
    }

    public NhanVien getNhanVienBan() {
        return nhanVienBan;
    }

    public void setNhanVienBan(NhanVien nhanVienBan) {
        this.nhanVienBan = nhanVienBan;
    }

    public KhachHang getKhachHangMua() {
        return khachHangMua;
    }

    public void setKhachHangMua(KhachHang khachHangMua) {
        this.khachHangMua = khachHangMua;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public ArrayList<SanPham> getDanhSachSanPhamMua() {
        return danhSachSanPhamMua;
    }

    public void setDanhSachSanPhamMua(ArrayList<SanPham> danhSachSanPhamMua) {
        this.danhSachSanPhamMua = danhSachSanPhamMua;
    }

    public void themSanPhamVaoDanhSach(SanPham sp) {
        if (danhSachSanPhamMua.contains(sp)) {
            SanPham sp1;
            int viTri = 0;
            for (SanPham sp2 : danhSachSanPhamMua) {
                if (sp.equals(sp2)) {
                    break;
                }
                viTri++;
            }
            sp1 = danhSachSanPhamMua.get(viTri);
            sp1.setSoLuong(sp1.getSoLuong() + sp.getSoLuong());

        } else {
            danhSachSanPhamMua.add(sp);
        }
    }

    public boolean xoaSanPhamKhoiDanhSach(SanPham sp) {
        if (danhSachSanPhamMua.contains(sp)) {
            danhSachSanPhamMua.remove(sp);
            return true;
        } else {
            return false;
        }
    }

    public boolean xoaSanPhamKhoiDanhSach(SanPham sp, int soLuong) {
        if (danhSachSanPhamMua.contains(sp)) {
            SanPham sp1;
            int viTri = 0;
            for (SanPham sp2 : danhSachSanPhamMua) {
                if (sp.equals(sp2)) {
                    break;
                }
                viTri++;
            }
            sp1 = danhSachSanPhamMua.get(viTri);
            if (soLuong <= sp1.getSoLuong()) {
                sp1.setSoLuong(sp1.getSoLuong() - soLuong);
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public int tinhTien() {
        int tongTien = 0;
        for (SanPham sp : danhSachSanPhamMua) {
            tongTien += sp.getGiaBan() * sp.getSoLuong();
        }
        return tongTien;
    }

    public int thanhTien(int chietKhau) {
        String maKhachHang = khachHangMua.getMaKhachHang();
//        maKhachHang = maKhachHang.substring(0, 3);
        return tinhTien() * (100 - chietKhau) / 100;
    }
}
