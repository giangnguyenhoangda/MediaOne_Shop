/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import object.BanHang;
import object.DiaNhac;
import object.DiaPhim;
import object.KhachHang;
import object.NhanVien;
import object.NhanVienChinhThuc;
import object.Sach;
import object.SanPham;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryBanHang {

    private Connection con;
    private QueryKhachHang qKhachHang;

    public QueryBanHang() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
        qKhachHang = new QueryKhachHang();
    }

    public int themBanHang(BanHang bh, boolean khachHangVIP) {
        try {
            if (khachHangVIP == false) {
                qKhachHang.themKhachHangVIP(bh.getKhachHangMua());
            }
            String sql = "insert into BanHang values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bh.getMaBanHang());
            ps.setString(2, bh.getNhanVienBan().getMaNhanVien());
            ps.setString(3, bh.getKhachHangMua().getMaKhachHang());
            ps.setDate(4, new java.sql.Date(bh.getNgayBan().getTime()));
            int kq = ps.executeUpdate();
            if (kq > 0) {
                int soLuong = themChiTietBanHang(bh);
                if (soLuong > 0) {
                    return kq;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int themChiTietBanHang(BanHang bh) {
        try {
            int kq = 0;
            String maBanHang = bh.getMaBanHang();
            for (SanPham sp : bh.getDanhSachSanPhamMua()) {
                String sql = "insert into ChiTietBanHang values(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maBanHang);
                ps.setString(2, sp.getTenSanPham());
                if (sp instanceof Sach) {
                    ps.setString(3, "Sách");
                } else if (sp instanceof DiaPhim) {
                    ps.setString(3, "Đĩa Phim");
                } else if (sp instanceof DiaNhac) {
                    ps.setString(3, "Đĩa Nhạc");
                }
                ps.setInt(4, sp.getSoLuong());
                ps.setInt(5, sp.getGiaMua());
                ps.setInt(6, sp.getGiaBan());
                kq += ps.executeUpdate();
            }
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<SanPham> thongKeTheoNgay(Date ngay) {
        ArrayList<SanPham> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHang where MaBanHang in ( select MaBanHang from BanHang where NgayBan=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loaiSanPham = rs.getString("LoaiSanPham");
                SanPham sp;
                if (loaiSanPham.equals("Sách")) {
                    sp = new Sach();
                } else if (loaiSanPham.equals("Đĩa Phim")) {
                    sp = new DiaPhim();
                } else {
                    sp = new DiaNhac();
                }
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaMua(rs.getInt("GiaMua"));
                sp.setGiaBan(rs.getInt("GiaBan"));
                ds.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<SanPham> thongKeTheoNgay(Date ngayMin, Date ngayMax) {
        ArrayList<SanPham> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHang where MaBanHang in ( select MaBanHang from BanHang where NgayBan>=? And NgayBan<=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayMin.getTime()));
            ps.setDate(2, new java.sql.Date(ngayMax.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loaiSanPham = rs.getString("LoaiSanPham");
                SanPham sp;
                if (loaiSanPham.equals("Sách")) {
                    sp = new Sach();
                } else if (loaiSanPham.equals("Đĩa Phim")) {
                    sp = new DiaPhim();
                } else {
                    sp = new DiaNhac();
                }
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaMua(rs.getInt("GiaMua"));
                sp.setGiaBan(rs.getInt("GiaBan"));
                ds.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<SanPham> thongKeTheoThang(int thang, int nam) {
        ArrayList<SanPham> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHang where MaBanHang in ( select MaBanHang from BanHang where NgayBan like ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-" + thang + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loaiSanPham = rs.getString("LoaiSanPham");
                SanPham sp;
                if (loaiSanPham.equals("Sách")) {
                    sp = new Sach();
                } else if (loaiSanPham.equals("Đĩa Phim")) {
                    sp = new DiaPhim();
                } else {
                    sp = new DiaNhac();
                }
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaMua(rs.getInt("GiaMua"));
                sp.setGiaBan(rs.getInt("GiaBan"));
                ds.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<SanPham> thongKeTheoNam(int nam) {
        ArrayList<SanPham> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHang where MaBanHang in ( select MaBanHang from BanHang where NgayBan like ? )";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loaiSanPham = rs.getString("LoaiSanPham");
                SanPham sp;
                if (loaiSanPham.equals("Sách")) {
                    sp = new Sach();
                } else if (loaiSanPham.equals("Đĩa Phim")) {
                    sp = new DiaPhim();
                } else {
                    sp = new DiaNhac();
                }
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaMua(rs.getInt("GiaMua"));
                sp.setGiaBan(rs.getInt("GiaBan"));
                ds.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

}
