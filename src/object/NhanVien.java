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
public class NhanVien extends Nguoi {

    private String maNhanVien;
    private int soNgayLam;
    private int soNgayLamThem;
    private TaiKhoanDangNhap taiKhoan;
    private Date ngayBatDauLam;
    private Date ngayTraLuong;
    private Integer luong;

    public NhanVien() {

    }

    public NhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, Date namSinh, String soDienThoai,
            String soCMND, String diaChi, int soNgayLam, int soNgayLamThem, TaiKhoanDangNhap taiKhoan, Date ngayBatDauLam) {
        super(tenNhanVien, gioiTinh, namSinh, soDienThoai, soCMND, diaChi);
        this.maNhanVien = maNhanVien;
        this.soNgayLam = soNgayLam;
        this.soNgayLamThem = soNgayLamThem;
        this.taiKhoan = taiKhoan;
        this.ngayBatDauLam = ngayBatDauLam;
    }

    public Date getNgayBatDauLam() {
        return ngayBatDauLam;
    }

    public void setNgayBatDauLam(Date ngayBatDauLam) {
        this.ngayBatDauLam = ngayBatDauLam;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    public void setSoNgayLam(int soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public int getSoNgayLamThem() {
        return soNgayLamThem;
    }

    public void setSoNgayLamThem(int soNgayLamThem) {
        this.soNgayLamThem = soNgayLamThem;
    }

    public TaiKhoanDangNhap getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoanDangNhap taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public int tinhLuong() {
        return 0;
    }

    public Integer getLuong() {
        return luong;
    }

    public void setLuong(Integer luong) {
        this.luong = luong;
    }

    public Date getNgayTraLuong() {
        return ngayTraLuong;
    }

    public void setNgayTraLuong(Date ngayTraLuong) {
        this.ngayTraLuong = ngayTraLuong;
    }

    @Override
    public String toString() {
        return getTen();
    }

}
