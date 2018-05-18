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
public class SanPham {

    private String tenSanPham;
    private Date namSanXuat;
    private String theLoai;
    private Date ngayNhap;
    private Integer giaMua;
    private Integer giaBan;
    private Integer soLuong;

    public SanPham() {
    }

    public SanPham(String tenSanPham, Date namSanXuat, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        this.tenSanPham = tenSanPham;
        this.namSanXuat = namSanXuat;
        this.theLoai = theLoai;
        this.ngayNhap = ngayNhap;
        this.giaMua = giaMua;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Date getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(Date namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Integer getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(Integer giaMua) {
        this.giaMua = giaMua;
    }

    public Integer getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public int thanhTien() {
        return soLuong * giaBan;
    }

    @Override
    public boolean equals(Object obj) {
        SanPham sp = (SanPham) obj;
        return tenSanPham.equalsIgnoreCase(sp.getTenSanPham());
    }

}
