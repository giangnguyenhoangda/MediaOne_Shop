/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import model.QueryNhanVien;
import object.NhanVien;
import object.NhanVienChinhThuc;
import object.NhanVienThoiVu;
import object.TaiKhoanDangNhap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLNhanVien {

    private QueryNhanVien qNhanVien;

    public BLLNhanVien() {
        qNhanVien = new QueryNhanVien();
    }

    public NhanVien timNhanVienTheoMaTaiKhoan(String maTaiKhoan) {
        TaiKhoanDangNhap tk = new TaiKhoanDangNhap();
        tk.setTenDangNhap(maTaiKhoan);
        return qNhanVien.timKiemNhanVienTheoMaTaiKhoan(tk);
    }

    public ArrayList<NhanVien> timKiemNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, Date NamSinh, String loaiNhanVien, String soDienThoai,
            String soCMND, String diaChi) {
        NhanVien nv;
        if (loaiNhanVien.equals("Chính Thức")) {
            nv = new NhanVienChinhThuc(maNhanVien, tenNhanVien, gioiTinh, NamSinh, soDienThoai, soCMND, diaChi, 0, 0, null, null);
        } else if (loaiNhanVien.equals("Thời Vụ")) {
            nv = new NhanVienThoiVu(maNhanVien, tenNhanVien, gioiTinh, NamSinh, soDienThoai, soCMND, diaChi, 0, 0, null, null);
        } else {
            nv = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, NamSinh, soDienThoai, soCMND, diaChi, 0, 0, null, null);
        }
        return qNhanVien.timKiemNhanVien(nv);
    }

    public int themNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, Date NamSinh, String loaiNhanVien, String soDienThoai,
            String soCMND, String diaChi) {
        NhanVien nv;
        if (loaiNhanVien.equals("Chính Thức")) {
            nv = new NhanVienChinhThuc(maNhanVien, tenNhanVien, gioiTinh, NamSinh, soDienThoai, soCMND, diaChi, 0, 0, null, null);
        } else {
            nv = new NhanVienThoiVu(maNhanVien, tenNhanVien, gioiTinh, NamSinh, soDienThoai, soCMND, diaChi, 0, 0, null, null);
        }
        return qNhanVien.themNhanVien(nv);
    }

    public int suaNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, Date NamSinh, String loaiNhanVien, String soDienThoai,
            String soCMND, String diaChi) {
        NhanVien nv;
        if (loaiNhanVien.equals("Chính Thức")) {
            nv = new NhanVienChinhThuc(maNhanVien, tenNhanVien, gioiTinh, NamSinh, soDienThoai, soCMND, diaChi, 0, 0, null, null);
        } else {
            nv = new NhanVienThoiVu(maNhanVien, tenNhanVien, gioiTinh, NamSinh, soDienThoai, soCMND, diaChi, 0, 0, null, null);
        }
        return qNhanVien.suaNhanVien(nv);
    }

    public int xoaNhanVien(String maNhanVien) {
        return qNhanVien.xoaNhanVien(maNhanVien);
    }

    public ArrayList<NhanVien> layToanBoNhanVien() {
        return qNhanVien.layToanBoNhanVien();
    }

    public HashMap<NhanVien, Date> layToanBoLuongNhanVien() {
        return qNhanVien.layToanLuongNhanVien();
    }

    public Integer tienLamMotNgay(String maLoaiNhanVien) {
        return qNhanVien.tienLamMotNgay(maLoaiNhanVien);
    }

    public Integer tienThemLamMotNgay(String maLoaiNhanVien) {
        return qNhanVien.tienLamThemMotNgay(maLoaiNhanVien);
    }

    public boolean kiemTraTraLuong(NhanVien nv, Date thang) {
        return qNhanVien.kiemTraTraLuong(nv, thang);
    }

    public int themLuong(NhanVien nv, Date thang) {
        return qNhanVien.themLuongNhanVien(nv, thang);
    }

    public int suaLuong(NhanVien nv, Date thang) {
        return qNhanVien.suaLuongNhanVien(nv, thang);
    }

    public int xoaLuong(NhanVien nv, Date thang) {
        return qNhanVien.xoaLuongNhanVien(nv, thang);
    }

    public ArrayList<NhanVien> nhanVienTraLuong(Date ngay) {
        return qNhanVien.nhanVienTraLuong(ngay);
    }

    public ArrayList<NhanVien> nhanVienTraLuong(Date ngayMin, Date ngayMax) {
        return qNhanVien.nhanVienTraLuong(ngayMin, ngayMax);
    }

    public ArrayList<NhanVien> thongKeNhanVienTheoNam(int nam) {
        return qNhanVien.thongKeNhanVienTheoNam(nam);
    }

    public ArrayList<NhanVien> thongKeNhanVienTheoThang(int thang, int nam) {
        return qNhanVien.thongKeNhanVienTheoThang(thang, nam);
    }

    public NhanVien layThongTinNhanVien(String maNhanVien) {
        return qNhanVien.layThongTinNhanVien(maNhanVien);
    }
}
