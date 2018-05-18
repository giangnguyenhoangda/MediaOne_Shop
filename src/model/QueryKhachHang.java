/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import object.KhachHang;
import object.NhanVien;
import object.NhanVienChinhThuc;
import object.NhanVienThoiVu;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryKhachHang {

    private Connection con;
    private SimpleDateFormat sdf;

    public QueryKhachHang() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public ArrayList<KhachHang> timKiemKhachHangVIP(KhachHang kh) {
        ArrayList<KhachHang> ds = new ArrayList<>();
        try {
            String sql = "select * from KhachHang where MaKhachHang like ? And TenKhachHang like ? And GioiTinh like ?"
                    + " And NamSinh like ? And SoCMND like ? And SoDienThoai like ? And DiaChi like ? And MaKhachHang like 'KHV%'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + kh.getMaKhachHang() + "%");
            ps.setString(2, "%" + kh.getTen() + "%");
            ps.setString(3, "%" + kh.getGioiTinh() + "%");
            if (kh.getNamSinh() == null) {
                ps.setString(4, "%%");
            } else {
                ps.setString(4, "%" + sdf.format(kh.getNamSinh()) + "%");
            }
            ps.setString(5, "%" + kh.getSoCMND() + "%");
            ps.setString(6, "%" + kh.getSoDienThoai() + "%");
            ps.setString(7, "%" + kh.getDiaChi() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khTimThay = new KhachHang();
                khTimThay.setMaKhachHang(rs.getString("MaKhachHang"));
                khTimThay.setTen(rs.getString("TenKhachHang"));
                khTimThay.setGioiTinh(rs.getString("GioiTinh"));
                if (rs.getDate("NamSinh") == null) {
                    khTimThay.setNamSinh(null);
                } else {
                    khTimThay.setNamSinh(new Date(rs.getDate("NamSinh").getTime()));
                }
                khTimThay.setSoCMND(rs.getString("SoCMND"));
                khTimThay.setSoDienThoai(rs.getString("SoDienThoai"));
                khTimThay.setDiaChi(rs.getString("DiaChi"));
                ds.add(khTimThay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int themKhachHangVIP(KhachHang kh) {
        try {
            String sql = "Insert Into KhachHang Values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getGioiTinh());
            if (kh.getNamSinh() == null) {
                ps.setDate(4, null);
            } else {
                ps.setDate(4, new java.sql.Date(kh.getNamSinh().getTime()));
            }
            ps.setString(5, kh.getSoDienThoai());
            ps.setString(6, kh.getSoCMND());
            ps.setString(7, kh.getDiaChi());
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaKhachHangVIP(KhachHang kh) {
        try {
            String sql = "update KhachHang set TenKhachHang=?,GioiTinh=?,NamSinh=?,SoCMND=?,SoDienThoai=?,DiaChi=? where MaKhachHang=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getTen());
            ps.setString(2, kh.getGioiTinh());
            if (kh.getNamSinh() == null) {
                ps.setDate(3, null);
            } else {
                ps.setDate(3, new java.sql.Date(kh.getNamSinh().getTime()));
            }
            ps.setString(4, kh.getSoCMND());
            ps.setString(5, kh.getSoDienThoai());
            ps.setString(6, kh.getDiaChi());
            ps.setString(7, kh.getMaKhachHang());
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaKhachHangVIP(String maKhachHang) {
        try {
            String sql = "delete from KhachHang where MaKhachHang=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKhachHang);
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<KhachHang> layToanBoKhachHangVIP() {
        ArrayList<KhachHang> ds = new ArrayList<>();
        try {
            String sql = "select * from KhachHang where MaKhachHang like 'KHV%'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTen(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                if (rs.getDate("NamSinh") == null) {
                    kh.setNamSinh(null);
                } else {
                    kh.setNamSinh(new Date(rs.getDate("NamSinh").getTime()));
                }
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setSoCMND(rs.getString("SoCMND"));
                kh.setDiaChi(rs.getString("DiaChi"));
                ds.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

}
