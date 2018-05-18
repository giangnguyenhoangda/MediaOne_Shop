/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import object.NhanVien;
import object.TaiKhoanDangNhap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryTaiKhoan {

    private Connection con;

    public QueryTaiKhoan() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
    }

    public int taoTaiKhoan(String maNhanVien) {
        try {
            String sql = "insert into TaiKhoanDangNhap values(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ps.setString(2, "1");
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaTaiKhoan(String maNhanVien) {
        try {
            String sql = "delete from TaiKhoanDangNhap where MaTaiKhoan=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean kiemTraTonTai(TaiKhoanDangNhap tk) {
        try {
            String sql = "select * from TaiKhoanDangNhap where MaTaiKhoan=? And MatKhau=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int thayDoiMatKhau(TaiKhoanDangNhap tk) {
        try {
            String sql = "update TaiKhoanDangNhap set MatKhau=? where MaTaiKhoan=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tk.getMatKhau());
            ps.setString(2, tk.getTenDangNhap());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
