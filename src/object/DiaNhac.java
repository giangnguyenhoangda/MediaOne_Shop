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
public class DiaNhac extends SanPham {

    private String caSi;
    private String nhaSanXuat;

    public DiaNhac() {
    }

    public DiaNhac(String tenDia, String caSi, String nhaSanXuat, Date namPhatHanh, String theLoai, Date ngayNhap,
            Integer giaMua, Integer giaBan, Integer soLuong) {
        super(tenDia, namPhatHanh, theLoai, ngayNhap, giaMua, giaBan, soLuong);
        this.caSi = caSi;
        this.nhaSanXuat = nhaSanXuat;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

}
