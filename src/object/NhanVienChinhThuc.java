/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Date;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class NhanVienChinhThuc extends NhanVien {

    public static int tienLamMotNgay; // ngày làm chính thức
    public static int tienLamThemMotNgay; // ngày làm thêm

    public NhanVienChinhThuc() {
    }

    public NhanVienChinhThuc(String maNhanVien, String tenNhanVien, String gioiTinh, Date namSinh, String soDienThoai, String soCMND, String diaChi, int soNgayLam, int soNgayLamThem, TaiKhoanDangNhap taiKhoan, Date ngayBatDauLam) {
        super(maNhanVien, tenNhanVien, gioiTinh, namSinh, soDienThoai, soCMND, diaChi, soNgayLam, soNgayLamThem, taiKhoan, ngayBatDauLam);
    }

    @Override
    public int tinhLuong() {
        if (getSoNgayLam() > 25) {
            return 6500000 + getSoNgayLamThem() * tienLamThemMotNgay;
        }
        return getSoNgayLam() * tienLamMotNgay + getSoNgayLamThem() * tienLamThemMotNgay;
    }

}
