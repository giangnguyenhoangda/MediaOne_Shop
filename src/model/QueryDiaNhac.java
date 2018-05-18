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
import javax.swing.JOptionPane;
import object.DiaNhac;
import object.DiaPhim;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryDiaNhac {

    private Connection con;
    private String regex;
    private SimpleDateFormat sdf;

    public QueryDiaNhac() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
        regex = "\\s*";
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public ArrayList<DiaNhac> layToanBoDiaNhac() {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from DiaNhac";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac s = new DiaNhac();
                s.setTenSanPham(rs.getString("TenDiaNhac"));
                s.setCaSi(rs.getString("CaSi"));
                s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                s.setTheLoai(rs.getString("TheLoai"));
                java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                s.setNgayNhap(new Date(NgayNhap.getTime()));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setGiaBan(rs.getInt("GiaBan"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
        return ds;
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(DiaNhac d, String doiTuong, Integer min, Integer max, boolean timGanDung) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        Integer giaMua = d.getGiaMua();
        Integer giaBan = d.getGiaBan();
        Integer soLuong = d.getSoLuong();
        try {
            String sql = "select * from DiaNhac where TenDiaNhac like ? And CaSi like ? And NhaSanXuat like ?"
                    + " And NamPhatHanh like ? And TheLoai like ? And NgayNhap like ? ";
            if (doiTuong.equals("GiaMua")) {
                sql += "And (GiaMua between ? and ?) And GiaBan like ? And SoLuong Like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timGanDung == true) {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                    ps.setString(2, "%" + d.getCaSi() + "%");
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    if (min != null) {
                        ps.setInt(7, min);
                    }
                    if (max != null) {
                        ps.setInt(8, max);
                    }
                    if (giaBan == null) {
                        ps.setString(9, "%%");
                    } else {
                        ps.setString(9, "%" + giaBan + "%");
                    }
                    if (soLuong == null) {
                        ps.setString(10, "%%");
                    } else {
                        ps.setString(10, "%" + soLuong + "%");
                    }
                } else {
                    if (d.getTenSanPham().matches(regex)) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, "%" + d.getTenSanPham() + "%");
                    }
                    if (d.getCaSi().matches(regex)) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, "%" + d.getCaSi() + "%");
                    }
                    if (d.getNhaSanXuat().matches(regex)) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    }
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    if (d.getTheLoai().matches(regex)) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, "%" + d.getTheLoai() + "%");
                    }
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    if (min != null) {
                        ps.setInt(7, min);
                    }
                    if (max != null) {
                        ps.setInt(8, max);
                    }
                    if (giaBan == null) {
                        ps.setString(9, "%%");
                    } else {
                        ps.setString(9, "%" + giaBan + "%");
                    }
                    if (soLuong == null) {
                        ps.setString(10, "%%");
                    } else {
                        ps.setString(10, "%" + soLuong + "%");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DiaNhac s = new DiaNhac();
                    s.setTenSanPham(rs.getString("TenDiaNhac"));
                    s.setCaSi(rs.getString("CaSi"));
                    s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                    java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                    s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                    s.setTheLoai(rs.getString("TheLoai"));
                    java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                    s.setNgayNhap(new Date(NgayNhap.getTime()));
                    s.setGiaMua(rs.getInt("GiaMua"));
                    s.setGiaBan(rs.getInt("GiaBan"));
                    s.setSoLuong(rs.getInt("SoLuong"));
                    ds.add(s);
                }
            } else if (doiTuong.equals("GiaBan")) {
                sql += "And GiaMua like ? And (GiaBan between ? and ?) And SoLuong like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timGanDung == true) {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                    ps.setString(2, "%" + d.getCaSi() + "%");
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    if (min != null) {
                        ps.setInt(8, min);
                    }
                    if (max != null) {
                        ps.setInt(9, max);
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + giaBan + "%");
                    }
                    if (soLuong == null) {
                        ps.setString(10, "%%");
                    } else {
                        ps.setString(10, "%" + soLuong + "%");
                    }
                } else {
                    if (d.getTenSanPham().matches(regex)) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, "%" + d.getTenSanPham() + "%");
                    }
                    if (d.getCaSi().matches(regex)) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, "%" + d.getCaSi() + "%");
                    }
                    if (d.getNhaSanXuat().matches(regex)) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    }
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    if (d.getTheLoai().matches(regex)) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, "%" + d.getTheLoai() + "%");
                    }
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    if (min != null) {
                        ps.setInt(8, min);
                    }
                    if (max != null) {
                        ps.setInt(9, max);
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + giaMua + "%");
                    }
                    if (soLuong == null) {
                        ps.setString(10, "%%");
                    } else {
                        ps.setString(10, "%" + soLuong + "%");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DiaNhac s = new DiaNhac();
                    s.setTenSanPham(rs.getString("TenDiaNhac"));
                    s.setCaSi(rs.getString("CaSi"));
                    s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                    java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                    s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                    s.setTheLoai(rs.getString("TheLoai"));
                    java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                    s.setNgayNhap(new Date(NgayNhap.getTime()));
                    s.setGiaMua(rs.getInt("GiaMua"));
                    s.setGiaBan(rs.getInt("GiaBan"));
                    s.setSoLuong(rs.getInt("SoLuong"));
                    ds.add(s);
                }
            } else if (doiTuong.equals("SoLuong")) {
                sql += "And GiaMua like ? And GiaBan like ? And (SoLuong between ? and ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timGanDung == true) {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                    ps.setString(2, "%" + d.getCaSi() + "%");
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    if (min != null) {
                        ps.setInt(9, min);
                    }
                    if (max != null) {
                        ps.setInt(10, max);
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + giaMua + "%");
                    }
                    if (giaBan == null) {
                        ps.setString(8, "%%");
                    } else {
                        ps.setString(8, "%" + giaBan + "%");
                    }
                } else {
                    if (d.getTenSanPham().matches(regex)) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, "%" + d.getTenSanPham() + "%");
                    }
                    if (d.getCaSi().matches(regex)) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, "%" + d.getCaSi() + "%");
                    }
                    if (d.getNhaSanXuat().matches(regex)) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    }
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    if (d.getTheLoai().matches(regex)) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, "%" + d.getTheLoai() + "%");
                    }
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    if (min != null) {
                        ps.setInt(9, min);
                    }
                    if (max != null) {
                        ps.setInt(10, max);
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + giaMua + "%");
                    }
                    if (giaBan == null) {
                        ps.setString(8, "%%");
                    } else {
                        ps.setString(8, "%" + giaBan + "%");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DiaNhac s = new DiaNhac();
                    s.setTenSanPham(rs.getString("TenDiaNhac"));
                    s.setCaSi(rs.getString("CaSi"));
                    s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                    java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                    s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                    s.setTheLoai(rs.getString("TheLoai"));
                    java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                    s.setNgayNhap(new Date(NgayNhap.getTime()));
                    s.setGiaMua(rs.getInt("GiaMua"));
                    s.setGiaBan(rs.getInt("GiaBan"));
                    s.setSoLuong(rs.getInt("SoLuong"));
                    ds.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(DiaNhac d, String doiTuong1, Integer min1, Integer max1, String doiTuong2, Integer min2,
            Integer max2, boolean timGanDung) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        Integer giaMua = d.getGiaMua();
        Integer giaBan = d.getGiaBan();
        Integer soLuong = d.getSoLuong();
        try {
            String sql = "select * from DiaNhac where TenDiaNhac like ? And CaSi like ? And NhaSanXuat like ?"
                    + " And NamPhatHanh like ? And TheLoai like ? And NgayNhap like ? ";
            if (doiTuong1.equals("GiaMua") && doiTuong2.equals("GiaBan")) {
                sql += "And (GiaMua between ? and ?) And (GiaBan between ? and ?) And SoLuong like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timGanDung == true) {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                    ps.setString(2, "%" + d.getCaSi() + "%");
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    ps.setInt(7, min1);
                    ps.setInt(8, max1);
                    ps.setInt(9, min2);
                    ps.setInt(10, max2);
                    if (soLuong == null) {
                        ps.setString(11, "%%");
                    } else {
                        ps.setString(11, "%" + soLuong + "%");
                    }
                } else {
                    if (d.getTenSanPham().matches(regex)) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, "%" + d.getTenSanPham() + "%");
                    }
                    if (d.getCaSi().matches(regex)) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, "%" + d.getCaSi() + "%");
                    }
                    if (d.getNhaSanXuat().matches(regex)) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    }
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    if (d.getTheLoai().matches(regex)) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, "%" + d.getTheLoai() + "%");
                    }
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    ps.setInt(7, min1);
                    ps.setInt(8, max1);
                    ps.setInt(9, min2);
                    ps.setInt(10, max2);
                    if (soLuong == null) {
                        ps.setString(11, "%%");
                    } else {
                        ps.setString(11, soLuong + "");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DiaNhac s = new DiaNhac();
                    s.setTenSanPham(rs.getString("TenDiaNhac"));
                    s.setCaSi(rs.getString("CaSi"));
                    s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                    java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                    s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                    s.setTheLoai(rs.getString("TheLoai"));
                    java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                    s.setNgayNhap(new Date(NgayNhap.getTime()));
                    s.setGiaMua(rs.getInt("GiaMua"));
                    s.setGiaBan(rs.getInt("GiaBan"));
                    s.setSoLuong(rs.getInt("SoLuong"));
                    ds.add(s);
                }
            } else if (doiTuong1.equals("GiaMua") && doiTuong2.equals("SoLuong")) {
                sql += "And (GiaMua between ? and ?) And GiaBan like ? And (SoLuong between ? and ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timGanDung == true) {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                    ps.setString(2, "%" + d.getCaSi() + "%");
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    ps.setInt(7, min1);
                    ps.setInt(8, max1);
                    ps.setInt(10, min2);
                    ps.setInt(11, max2);
                    if (giaBan == null) {
                        ps.setString(9, "%%");
                    } else {
                        ps.setString(9, "%" + giaBan + "%");
                    }
                } else {
                    if (d.getTenSanPham().matches(regex)) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, "%" + d.getTenSanPham() + "%");
                    }
                    if (d.getCaSi().matches(regex)) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, "%" + d.getCaSi() + "%");
                    }
                    if (d.getNhaSanXuat().matches(regex)) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    }
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    if (d.getTheLoai().matches(regex)) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, "%" + d.getTheLoai() + "%");
                    }
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    ps.setInt(7, min1);
                    ps.setInt(8, max1);
                    ps.setInt(10, min2);
                    ps.setInt(11, max2);
                    if (giaBan == null) {
                        ps.setString(9, "%%");
                    } else {
                        ps.setString(9, giaBan + "");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DiaNhac s = new DiaNhac();
                    s.setTenSanPham(rs.getString("TenDiaNhac"));
                    s.setCaSi(rs.getString("CaSi"));
                    s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                    java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                    s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                    s.setTheLoai(rs.getString("TheLoai"));
                    java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                    s.setNgayNhap(new Date(NgayNhap.getTime()));
                    s.setGiaMua(rs.getInt("GiaMua"));
                    s.setGiaBan(rs.getInt("GiaBan"));
                    s.setSoLuong(rs.getInt("SoLuong"));
                    ds.add(s);
                }
            } else if (doiTuong1.equals("GiaBan") && doiTuong2.equals("SoLuong")) {
                sql += "And GiaMua like ? And (GiaBan between ? and ?) And (SoLuong between ? and ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timGanDung == true) {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                    ps.setString(2, "%" + d.getCaSi() + "%");
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    ps.setInt(8, min1);
                    ps.setInt(9, max1);
                    ps.setInt(10, min2);
                    ps.setInt(11, max2);
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + giaMua + "%");
                    }
                } else {
                    if (d.getTenSanPham().matches(regex)) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, "%" + d.getTenSanPham() + "%");
                    }
                    if (d.getCaSi().matches(regex)) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, "%" + d.getCaSi() + "%");
                    }
                    if (d.getNhaSanXuat().matches(regex)) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                    }
                    if (d.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                    }
                    if (d.getTheLoai().matches(regex)) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, "%" + d.getTheLoai() + "%");
                    }
                    if (d.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                    }
                    ps.setInt(8, min1);
                    ps.setInt(9, max1);
                    ps.setInt(10, min2);
                    ps.setInt(11, max2);
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, giaMua + "");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DiaNhac s = new DiaNhac();
                    s.setTenSanPham(rs.getString("TenDiaNhac"));
                    s.setCaSi(rs.getString("CaSi"));
                    s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                    java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                    s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                    s.setTheLoai(rs.getString("TheLoai"));
                    java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                    s.setNgayNhap(new Date(NgayNhap.getTime()));
                    s.setGiaMua(rs.getInt("GiaMua"));
                    s.setGiaBan(rs.getInt("GiaBan"));
                    s.setSoLuong(rs.getInt("SoLuong"));
                    ds.add(s);
                }
            }
        } catch (Exception e) {

        }
        return ds;
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(DiaNhac d, Integer giaMuaMin, Integer giaMuaMax, Integer giaBanMin, Integer giaBanMax,
            Integer soLuongMin, Integer soLuongMax, boolean timGanDung) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from DiaNhac where TenDiaNhac like ? And CaSi like ? And NhaSanXuat like ?"
                    + " And NamPhatHanh like ? And TheLoai like ? And NgayNhap like ? "
                    + "And (GiaMua between ? and ?) And (GiaBan between ? and ?) And (SoLuong between ? and ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            if (timGanDung == true) {
                ps.setString(1, "%" + d.getTenSanPham() + "%");
                ps.setString(2, "%" + d.getCaSi() + "%");
                ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                if (d.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                }
                ps.setString(5, "%" + d.getTheLoai() + "%");
                if (d.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                }
                ps.setInt(7, giaMuaMin);
                ps.setInt(8, giaMuaMax);
                ps.setInt(9, giaBanMin);
                ps.setInt(10, giaBanMax);
                ps.setInt(11, soLuongMin);
                ps.setInt(12, soLuongMax);
            } else {
                if (d.getTenSanPham().matches(regex)) {
                    ps.setString(1, "%%");
                } else {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                }
                if (d.getCaSi().matches(regex)) {
                    ps.setString(2, "%%");
                } else {
                    ps.setString(2, "%" + d.getCaSi() + "%");
                }
                if (d.getNhaSanXuat().matches(regex)) {
                    ps.setString(3, "%%");
                } else {
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                }
                if (d.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, "%" + sdf.format(d.getNamSanXuat() + "%"));
                }
                if (d.getTheLoai().matches(regex)) {
                    ps.setString(5, "%%");
                } else {
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                }
                if (d.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, "%" + sdf.format(d.getNgayNhap() + "%"));
                }
                ps.setInt(7, giaMuaMin);
                ps.setInt(8, giaMuaMax);
                ps.setInt(9, giaBanMin);
                ps.setInt(10, giaBanMax);
                ps.setInt(11, soLuongMin);
                ps.setInt(12, soLuongMax);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac s = new DiaNhac();
                s.setTenSanPham(rs.getString("TenDiaNhac"));
                s.setCaSi(rs.getString("CaSi"));
                s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                s.setTheLoai(rs.getString("TheLoai"));
                java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                s.setNgayNhap(new Date(NgayNhap.getTime()));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setGiaBan(rs.getInt("GiaBan"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(DiaNhac d, boolean timGanDung) {

        ArrayList<DiaNhac> ds = new ArrayList<DiaNhac>();
        String sql = "SELECT * FROM DiaNhac "
                + "where TenDiaNhac like ? AND CaSi like ? "
                + "AND NhaSanXuat like ? AND NamPhatHanh like ? AND TheLoai like ? AND NgayNhap like ? "
                + "AND GiaMua like ? And GiaBan like ? And SoLuong like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (timGanDung == true) {
                ps.setString(1, "%" + d.getTenSanPham() + "%");
                ps.setString(2, "%" + d.getCaSi() + "%");
                ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (d.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, "%" + sdf.format(d.getNamSanXuat()) + "%");
                }
                ps.setString(5, "%" + d.getTheLoai() + "%");
                if (d.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, "%" + sdf.format(d.getNgayNhap()) + "%");
                }
                if (d.getGiaMua() == null) {
                    ps.setString(7, "%%");
                } else {
                    ps.setString(7, "%" + d.getGiaMua() + "%");
                }
                if (d.getGiaBan() == null) {
                    ps.setString(8, "%%");
                } else {
                    ps.setString(8, "%" + d.getGiaBan() + "%");
                }
                if (d.getSoLuong() == null) {
                    ps.setString(9, "%%");
                } else {
                    ps.setString(9, "%" + d.getSoLuong() + "%");
                }
            } else {
                if (d.getTenSanPham().matches(regex)) {
                    ps.setString(1, "%%");
                } else {
                    ps.setString(1, "%" + d.getTenSanPham() + "%");
                }
                if (d.getCaSi().matches(regex)) {
                    ps.setString(2, "%%");
                } else {
                    ps.setString(2, "%" + d.getCaSi() + "%");
                }
                if (d.getNhaSanXuat().matches(regex)) {
                    ps.setString(3, "%%");
                } else {
                    ps.setString(3, "%" + d.getNhaSanXuat() + "%");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (d.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, "%" + sdf.format(d.getNamSanXuat()) + "%");
                }
                if (d.getTheLoai().matches(regex)) {
                    ps.setString(5, "%%");
                } else {
                    ps.setString(5, "%" + d.getTheLoai() + "%");
                }
                if (d.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, "%" + sdf.format(d.getNgayNhap()) + "%");
                }
                if (d.getGiaMua() == null) {
                    ps.setString(7, "%%");
                } else {
                    ps.setString(7, "%" + d.getGiaMua() + "%");
                }
                if (d.getGiaBan() == null) {
                    ps.setString(8, "%%");
                } else {
                    ps.setString(8, "%" + d.getGiaBan() + "%");
                }
                if (d.getSoLuong() == null) {
                    ps.setString(9, "%%");
                } else {
                    ps.setString(9, "%" + d.getSoLuong() + "%");
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac s = new DiaNhac();
                s.setTenSanPham(rs.getString("TenDiaNhac"));
                s.setCaSi(rs.getString("CaSi"));
                s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                s.setTheLoai(rs.getString("TheLoai"));
                java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                s.setNgayNhap(new Date(NgayNhap.getTime()));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setGiaBan(rs.getInt("GiaBan"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> timKiemDiaNhac(DiaNhac d) {

        ArrayList<DiaNhac> ds = new ArrayList<DiaNhac>();
        String sql = "SELECT * FROM DiaNhac "
                + "where TenDiaNhac like ? AND CaSi like ? "
                + "AND NhaSanXuat like ? AND NamPhatHanh like ? AND TheLoai like ? AND NgayNhap like ? "
                + "AND GiaMua like ? And GiaBan like ? And SoLuong like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + d.getTenSanPham() + "%");
            ps.setString(2, "%" + d.getCaSi() + "%");
            ps.setString(3, "%" + d.getNhaSanXuat() + "%");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (d.getNamSanXuat() == null) {
                ps.setString(4, "%%");
            } else {
                ps.setString(4, "%" + sdf.format(d.getNamSanXuat()) + "%");
            }
            ps.setString(5, "%" + d.getTheLoai() + "%");
            if (d.getNgayNhap() == null) {
                ps.setString(6, "%%");
            } else {
                ps.setString(6, "%" + sdf.format(d.getNgayNhap()) + "%");
            }
            if (d.getGiaMua() == null) {
                ps.setString(7, "%%");
            } else {
                ps.setString(7, "%" + d.getGiaMua() + "%");
            }
            if (d.getGiaBan() == null) {
                ps.setString(8, "%%");
            } else {
                ps.setString(8, "%" + d.getGiaBan() + "%");
            }
            if (d.getSoLuong() == null) {
                ps.setString(9, "%%");
            } else {
                ps.setString(9, "%" + d.getSoLuong() + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac s = new DiaNhac();
                s.setTenSanPham(rs.getString("TenDiaNhac"));
                s.setCaSi(rs.getString("CaSi"));
                s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                java.sql.Date NamXuatBan = rs.getDate("NamPhatHanh");
                s.setNamSanXuat(new Date(NamXuatBan.getTime()));
                s.setTheLoai(rs.getString("TheLoai"));
                java.sql.Date NgayNhap = rs.getDate("NgayNhap");
                s.setNgayNhap(new Date(NgayNhap.getTime()));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setGiaBan(rs.getInt("GiaBan"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int themDiaNhac(DiaNhac d) {
        String sql = "Insert Into DiaNhac values(?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, d.getTenSanPham());
            ps.setString(2, d.getCaSi());
            ps.setString(3, d.getNhaSanXuat());
            ps.setDate(4, new java.sql.Date((d.getNamSanXuat()).getTime()));
            ps.setString(5, d.getTheLoai());
            ps.setDate(6, new java.sql.Date((d.getNgayNhap()).getTime()));
            ps.setInt(7, d.getGiaMua());
            ps.setInt(8, d.getGiaBan());
            ps.setInt(9, d.getSoLuong());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaDiaNhac(DiaNhac d) {
        String sql = "Update DiaNhac set CaSi=?,NhaSanXuat=?,NamPhatHanh=?,TheLoai=?,NgayNhap=?,GiaMua=?,GiaBan=?,SoLuong=?"
                + " where TenDiaNhac=?";
        int kq = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, d.getCaSi());
            ps.setString(2, d.getNhaSanXuat());
            ps.setDate(3, new java.sql.Date((d.getNamSanXuat()).getTime()));
            ps.setString(4, d.getTheLoai());
            ps.setDate(5, new java.sql.Date((d.getNgayNhap()).getTime()));
            ps.setInt(6, d.getGiaMua());
            ps.setInt(7, d.getGiaBan());
            ps.setInt(8, d.getSoLuong());
            ps.setString(9, d.getTenSanPham());
            kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int xoaDiaNhac(DiaNhac d) {
        int kq = 0;
        String sql = "delete from DiaNhac where TenDiaNhac=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, d.getTenSanPham());
            kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<DiaNhac> diaNhacNhapNgay(Date ngay) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from DiaNhac where NgayNhap=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaNhac"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> diaNhacNhapNgay(Date ngayMin, Date ngayMax) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from DiaNhac where NgayNhap>=? And NgayNhap<=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayMin.getTime()));
            ps.setDate(2, new java.sql.Date(ngayMax.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaNhac"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> diaNhacNhapThangNam(int thang, int nam) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from DiaNhac where NgayNhap like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-" + thang + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaNhac"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> diaNhacNhapNam(int nam) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from DiaNhac where NgayNhap like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaNhac"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> diaNhacBanNgay(Date ngay) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangDiaNhac where MaBanHang in ( select MaBanHang from BanHang where NgayBan=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaPhim"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setGiaBan(rs.getInt("GiaBan"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> diaNhacBanNam(int nam) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangDiaNhac where MaBanHang in ( select MaBanHang from BanHang where NgayBan like ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaPhim"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setGiaBan(rs.getInt("GiaBan"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> diaNhacBanNgay(Date ngayMin, Date ngayMax) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangDiaNhac where MaBanHang in ( select MaBanHang from BanHang where NgayBan>=? And NgayBan<=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayMin.getTime()));
            ps.setDate(2, new java.sql.Date(ngayMax.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaPhim"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setGiaBan(rs.getInt("GiaBan"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DiaNhac> diaNhacBanThang(int thang, int nam) {
        ArrayList<DiaNhac> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangDiaNhac where MaBanHang in ( select MaBanHang from BanHang where NgayBan like ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-" + thang + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(rs.getString("TenDiaPhim"));
                d.setGiaMua(rs.getInt("GiaMua"));
                d.setGiaBan(rs.getInt("GiaBan"));
                d.setSoLuong(rs.getInt("SoLuong"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
