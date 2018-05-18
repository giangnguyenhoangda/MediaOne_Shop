/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.QueryTaiKhoan;
import object.TaiKhoanDangNhap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLTaiKhoan {

    private QueryTaiKhoan qTaiKhoan;

    public BLLTaiKhoan() {
        qTaiKhoan = new QueryTaiKhoan();
    }

    public boolean kiemTraTaiKhoan(String tenDangNhap, String matKhau) {
        TaiKhoanDangNhap tk = new TaiKhoanDangNhap(tenDangNhap, matKhau);
        return qTaiKhoan.kiemTraTonTai(tk);
    }

    public int thayDoiMatKhau(String tenDangNhap, String matKhau) {
        TaiKhoanDangNhap tk = new TaiKhoanDangNhap(tenDangNhap, matKhau);
        return qTaiKhoan.thayDoiMatKhau(tk);
    }
}
