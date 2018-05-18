/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.Date;
import model.QueryKhachHang;
import object.KhachHang;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLKhachHang {

    private QueryKhachHang qKhachHang;

    public BLLKhachHang() {
        qKhachHang = new QueryKhachHang();
    }

    public ArrayList<KhachHang> timKiemKhachHangVIP(String maKhachHang, String tenKhachHang, String gioiTinh, Date namSinh,
            String soCMND, String soDienThoai, String diaChi) {
        KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, gioiTinh, namSinh, soDienThoai, soCMND, diaChi);
        return qKhachHang.timKiemKhachHangVIP(kh);
    }

    public int themKhachHangVIP(String maKhachHang, String tenKhachHang, String gioiTinh, Date namSinh,
            String soCMND, String soDienThoai, String diaChi) {
        KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, gioiTinh, namSinh, soDienThoai, soCMND, diaChi);
        return qKhachHang.themKhachHangVIP(kh);
    }

    public int suaKhachHangVIP(String maKhachHang, String tenKhachHang, String gioiTinh, Date namSinh,
            String soCMND, String soDienThoai, String diaChi) {
        KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, gioiTinh, namSinh, soDienThoai, soCMND, diaChi);
        return qKhachHang.suaKhachHangVIP(kh);
    }

    public int xoaKhachHangVIP(String maKhachHang) {
        return qKhachHang.xoaKhachHangVIP(maKhachHang);
    }

    public ArrayList<KhachHang> layToanBoKhachHangVip() {
        return qKhachHang.layToanBoKhachHangVIP();
    }
}
