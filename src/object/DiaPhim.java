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
public class DiaPhim extends SanPham {

    private String daoDien;
    private String dienVien;

    public DiaPhim() {

    }

    public DiaPhim(String tenDiaPhim, String daoDien, String dienVien, Date namPhatHanh, String theLoai,
            Date ngayNhap, Integer giaMua, Integer giaBan, Integer soLuong) {
        super(tenDiaPhim, namPhatHanh, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        this.daoDien = daoDien;
        this.dienVien = dienVien;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

}
