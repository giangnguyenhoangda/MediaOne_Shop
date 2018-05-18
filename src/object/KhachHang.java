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
public class KhachHang extends Nguoi {

    private String maKhachHang;

    public KhachHang() {

    }

    // dùng cho khách hàng thường
    public KhachHang(String maKhachHang, String tenKhachHang) {
        this.maKhachHang = maKhachHang;
        setTen(tenKhachHang);
    }

    // dùng cho khách hàng VIP
    public KhachHang(String maKhachHang, String tenKhachHang, String gioiTinh, Date namSinh, String soDienThoai,
            String soCMND, String diaChi) {
        super(tenKhachHang, gioiTinh, namSinh, soDienThoai, soCMND, diaChi);
        this.maKhachHang = maKhachHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    @Override
    public String toString() {
        return getTen();
    }
}
