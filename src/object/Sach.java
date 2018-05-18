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
public class Sach extends SanPham {

    private String tacGia;
    private String nhaXuatBan;

    public Sach() {

    }

    public Sach(String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan, String theLoai,
            Date ngayNhap, Integer giaMua, Integer giaBan, Integer soLuong) {
        super(tenSach, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

}
