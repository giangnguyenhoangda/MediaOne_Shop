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
import object.Sach;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QuerySach {

    private Connection con;

    public QuerySach() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
    }

    public ArrayList<Sach> layToanBoSach() {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from Sach";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
                s.setTacGia(rs.getString("TacGia"));
                s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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

    public ArrayList<Sach> timKiemSach(Sach sach, boolean timGanDung) {

        ArrayList<Sach> ds = new ArrayList<Sach>();
        String sql = "SELECT * FROM Sach "
                + "where TenSach like ? AND TacGia like ? "
                + "AND NhaXuatBan like ? AND NamXuatBan like ? AND TheLoai like ? AND NgayNhap like ? "
                + "AND GiaMua like ? And GiaBan like ? And SoLuong like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (timGanDung == true) {
                ps.setString(1, "%" + sach.getTenSanPham() + "%");
                ps.setString(2, "%" + sach.getTacGia() + "%");
                ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sach.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                }
                ps.setString(5, "%" + sach.getTheLoai() + "%");
                if (sach.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                }
                if (sach.getGiaMua() == null) {
                    ps.setString(7, "%%");
                } else {
                    ps.setString(7, "%" + sach.getGiaMua() + "%");
                }
                if (sach.getGiaBan() == null) {
                    ps.setString(8, "%%");
                } else {
                    ps.setString(8, "%" + sach.getGiaBan() + "%");
                }
                if (sach.getSoLuong() == null) {
                    ps.setString(9, "%%");
                } else {
                    ps.setString(9, "%" + sach.getSoLuong() + "%");
                }
            } else {
                String tenSach = sach.getTenSanPham();
                String tacGia = sach.getTacGia();
                String nhaXuatBan = sach.getNhaXuatBan();
                String theLoai = sach.getTheLoai();
                if (tenSach.matches("\\s*")) {
                    ps.setString(1, "%%");
                } else {
                    ps.setString(1, sach.getTenSanPham());
                }
                if (tacGia.matches("\\s*")) {
                    ps.setString(2, "%%");
                } else {
                    ps.setString(2, sach.getTacGia());
                }
                if (nhaXuatBan.matches("\\s*")) {
                    ps.setString(3, "%%");
                } else {
                    ps.setString(3, sach.getNhaXuatBan());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sach.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, sdf.format(sach.getNamSanXuat()));
                }
                if (theLoai.matches("\\s*")) {
                    ps.setString(5, "%%");
                } else {
                    ps.setString(5, sach.getTheLoai());
                }
                if (sach.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, sdf.format(sach.getNgayNhap()));
                }
                if (sach.getGiaMua() == null) {
                    ps.setString(7, "%%");
                } else {
                    ps.setString(7, sach.getGiaMua() + "");
                }
                if (sach.getGiaBan() == null) {
                    ps.setString(8, "%%");
                } else {
                    ps.setString(8, sach.getGiaBan() + "");
                }
                if (sach.getSoLuong() == null) {
                    ps.setString(9, "%%");
                } else {
                    ps.setString(9, sach.getSoLuong() + "");
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
                s.setTacGia(rs.getString("TacGia"));
                s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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

    public ArrayList<Sach> timKiemSach(boolean timKiemGanDung, Sach sach, String doiTuong, Integer Min, Integer Max) {
        ArrayList<Sach> ds = new ArrayList<>();
        System.out.println("Min:" + Min);
        System.out.println("Max:" + Max);
        System.out.println("Giá mua:" + sach.getGiaMua());
        System.out.println("Giá bán:" + sach.getGiaBan());
        System.out.println("Số lượng:" + sach.getSoLuong());
        Integer giaMua = sach.getGiaMua();
        Integer giaBan = sach.getGiaBan();
        Integer soLuong = sach.getSoLuong();
        try {
            String sql = "select * from Sach where TenSach like ? And TacGia like ? And NhaXuatBan like ? And NamXuatBan like ? "
                    + "And TheLoai like ? And NgayNhap like ? ";
            if (doiTuong.equals("GiaMua")) {
                System.out.println("Vào GiaMua");
                sql += "And (GiaMua between ? and ?) And GiaBan like ? And SoLuong like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timKiemGanDung == true) {
                    ps.setString(1, "%" + sach.getTenSanPham() + "%");
                    ps.setString(2, "%" + sach.getTacGia() + "%");
                    ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                    }
                    ps.setString(5, "%" + sach.getTheLoai() + "%");
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                    }
                    ps.setInt(7, Min);
                    ps.setInt(8, Max);
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
                    String tenSach = sach.getTenSanPham();
                    String tacGia = sach.getTacGia();
                    String nhaXuatBan = sach.getNhaXuatBan();
                    String theLoai = sach.getTheLoai();
                    if (tenSach.matches("\\s*")) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, sach.getTenSanPham());
                    }
                    if (tacGia.matches("\\s*")) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, sach.getTacGia());
                    }
                    if (nhaXuatBan.matches("\\s*")) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, sach.getNhaXuatBan());
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, sdf.format(sach.getNamSanXuat()));
                    }
                    if (theLoai.matches("\\s*")) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, sach.getTheLoai());
                    }
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, sdf.format(sach.getNgayNhap()));
                    }
                    ps.setInt(7, Min);
                    ps.setInt(8, Max);
                    if (giaBan == null) {
                        ps.setString(9, "%%");
                    } else {
                        ps.setString(9, giaBan + "");
                    }
                    if (soLuong == null) {
                        ps.setString(10, "%%");
                    } else {
                        ps.setString(10, soLuong + "");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sach s = new Sach();
                    s.setTenSanPham(rs.getString("TenSach"));
                    s.setTacGia(rs.getString("TacGia"));
                    s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                    java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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
                System.out.println("Vào GiaBan");
                sql += "And GiaMua like ? And (GiaBan between ? and ?) And SoLuong like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timKiemGanDung == true) {
                    ps.setString(1, "%" + sach.getTenSanPham() + "%");
                    ps.setString(2, "%" + sach.getTacGia() + "%");
                    ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                    }
                    ps.setString(5, "%" + sach.getTheLoai() + "%");
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + sach.getGiaMua() + "%");
                    }
                    ps.setInt(8, Min);
                    ps.setInt(9, Max);
                    if (soLuong == null) {
                        ps.setString(10, "%%");
                    } else {
                        ps.setString(10, "%" + sach.getSoLuong() + "%");
                    }
                } else {
                    String tenSach = sach.getTenSanPham();
                    String tacGia = sach.getTacGia();
                    String nhaXuatBan = sach.getNhaXuatBan();
                    String theLoai = sach.getTheLoai();
                    if (tenSach.matches("\\s*")) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, sach.getTenSanPham());
                    }
                    if (tacGia.matches("\\s*")) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, sach.getTacGia());
                    }
                    if (nhaXuatBan.matches("\\s*")) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, sach.getNhaXuatBan());
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, sdf.format(sach.getNamSanXuat()));
                    }
                    if (theLoai.matches("\\s*")) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, sach.getTheLoai());
                    }
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, sdf.format(sach.getNgayNhap()));
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "" + sach.getGiaMua());
                    }
                    ps.setInt(8, Min);
                    ps.setInt(9, Max);
                    if (soLuong == null) {
                        ps.setString(10, "%%");
                    } else {
                        ps.setString(10, sach.getSoLuong() + "");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sach s = new Sach();
                    s.setTenSanPham(rs.getString("TenSach"));
                    s.setTacGia(rs.getString("TacGia"));
                    s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                    java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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
                System.out.println("Vào SoLuong");
                sql += "And GiaMua like ? And GiaBan like ? And (SoLuong between ? and ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timKiemGanDung == true) {
                    ps.setString(1, "%" + sach.getTenSanPham() + "%");
                    ps.setString(2, "%" + sach.getTacGia() + "%");
                    ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                    }
                    ps.setString(5, "%" + sach.getTheLoai() + "%");
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + sach.getGiaMua() + "%");
                    }
                    if (giaBan == null) {
                        ps.setString(8, "%%");
                    } else {
                        ps.setString(8, "%" + sach.getGiaBan() + "%");
                    }
                    ps.setInt(9, Min);
                    ps.setInt(10, Max);
                } else {
                    String tenSach = sach.getTenSanPham();
                    String tacGia = sach.getTacGia();
                    String nhaXuatBan = sach.getNhaXuatBan();
                    String theLoai = sach.getTheLoai();
                    if (tenSach.matches("\\s*")) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, sach.getTenSanPham());
                    }
                    if (tacGia.matches("\\s*")) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, sach.getTacGia());
                    }
                    if (nhaXuatBan.matches("\\s*")) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, sach.getNhaXuatBan());
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, sdf.format(sach.getNamSanXuat()));
                    }
                    if (theLoai.matches("\\s*")) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, sach.getTheLoai());
                    }
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, sdf.format(sach.getNgayNhap()));
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, sach.getGiaMua() + "");
                    }
                    if (giaBan == null) {
                        ps.setString(8, "%%");
                    } else {
                        ps.setString(8, sach.getGiaBan() + "");
                    }
                    ps.setInt(9, Min);
                    ps.setInt(10, Max);
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sach s = new Sach();
                    s.setTenSanPham(rs.getString("TenSach"));
                    s.setTacGia(rs.getString("TacGia"));
                    s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                    java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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

    public ArrayList<Sach> timKiemSach(boolean timKiemGanDung, Sach sach, String doiTuong1, Integer Min1, Integer Max1,
            String doiTuong2, Integer Min2, Integer Max2) {
        ArrayList<Sach> ds = new ArrayList<>();
        Integer giaMua = sach.getGiaMua();
        Integer giaBan = sach.getGiaBan();
        Integer soLuong = sach.getSoLuong();
        try {
            String sql = "select * from Sach where TenSach like ? And TacGia like ? And NhaXuatBan like ? And NamXuatBan like ? "
                    + "And TheLoai like ? And NgayNhap like ? ";
            if (doiTuong1.equals("GiaMua") && doiTuong2.equals("GiaBan")) {
                sql += " And (GiaMua between ? and ?) And (GiaBan between ? and ?) And SoLuong like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timKiemGanDung == true) {
                    ps.setString(1, "%" + sach.getTenSanPham() + "%");
                    ps.setString(2, "%" + sach.getTacGia() + "%");
                    ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                    }
                    ps.setString(5, "%" + sach.getTheLoai() + "%");
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                    }
                    ps.setInt(7, Min1);
                    ps.setInt(8, Max1);
                    ps.setInt(9, Min2);
                    ps.setInt(10, Max2);
                    if (soLuong == null) {
                        ps.setString(11, "%%");
                    } else {
                        ps.setString(11, "%" + sach.getSoLuong() + "%");
                    }
                } else {
                    String tenSach = sach.getTenSanPham();
                    String tacGia = sach.getTacGia();
                    String nhaXuatBan = sach.getNhaXuatBan();
                    String theLoai = sach.getTheLoai();
                    if (tenSach.matches("\\s*")) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, sach.getTenSanPham());
                    }
                    if (tacGia.matches("\\s*")) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, sach.getTacGia());
                    }
                    if (nhaXuatBan.matches("\\s*")) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, sach.getNhaXuatBan());
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, sdf.format(sach.getNamSanXuat()));
                    }
                    if (theLoai.matches("\\s*")) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, sach.getTheLoai());
                    }
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, sdf.format(sach.getNgayNhap()));
                    }
                    ps.setInt(7, Min1);
                    ps.setInt(8, Max1);
                    ps.setInt(9, Min2);
                    ps.setInt(10, Max2);
                    if (soLuong == null) {
                        ps.setString(11, "%%");
                    } else {
                        ps.setString(11, sach.getSoLuong() + "");
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sach s = new Sach();
                    s.setTenSanPham(rs.getString("TenSach"));
                    s.setTacGia(rs.getString("TacGia"));
                    s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                    java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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
                sql += " And (GiaMua between ? and ?) And GiaBan like ? And (SoLuong between ? and ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timKiemGanDung == true) {
                    ps.setString(1, "%" + sach.getTenSanPham() + "%");
                    ps.setString(2, "%" + sach.getTacGia() + "%");
                    ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                    }
                    ps.setString(5, "%" + sach.getTheLoai() + "%");
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                    }
                    ps.setInt(7, Min1);
                    ps.setInt(8, Max1);
                    if (giaBan == null) {
                        ps.setString(9, "%%");
                    } else {
                        ps.setString(9, "%" + sach.getGiaBan() + "%");
                    }
                    ps.setInt(10, Min2);
                    ps.setInt(11, Max2);
                } else {
                    String tenSach = sach.getTenSanPham();
                    String tacGia = sach.getTacGia();
                    String nhaXuatBan = sach.getNhaXuatBan();
                    String theLoai = sach.getTheLoai();
                    if (tenSach.matches("\\s*")) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, sach.getTenSanPham());
                    }
                    if (tacGia.matches("\\s*")) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, sach.getTacGia());
                    }
                    if (nhaXuatBan.matches("\\s*")) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, sach.getNhaXuatBan());
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, sdf.format(sach.getNamSanXuat()));
                    }
                    if (theLoai.matches("\\s*")) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, sach.getTheLoai());
                    }
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, sdf.format(sach.getNgayNhap()));
                    }
                    ps.setInt(7, Min1);
                    ps.setInt(8, Max1);
                    if (giaBan == null) {
                        ps.setString(9, "%%");
                    } else {
                        ps.setString(9, "" + sach.getGiaBan());
                    }
                    ps.setInt(10, Min2);
                    ps.setInt(11, Max2);
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sach s = new Sach();
                    s.setTenSanPham(rs.getString("TenSach"));
                    s.setTacGia(rs.getString("TacGia"));
                    s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                    java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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
                sql += " And GiaMua like ? And (GiaBan between ? and ?) And (SoLuong between ? and ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                if (timKiemGanDung == true) {
                    ps.setString(1, "%" + sach.getTenSanPham() + "%");
                    ps.setString(2, "%" + sach.getTacGia() + "%");
                    ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                    }
                    ps.setString(5, "%" + sach.getTheLoai() + "%");
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "%" + sach.getGiaMua() + "%");
                    }
                    ps.setInt(8, Min1);
                    ps.setInt(9, Max1);
                    ps.setInt(10, Min2);
                    ps.setInt(11, Max2);
                } else {
                    String tenSach = sach.getTenSanPham();
                    String tacGia = sach.getTacGia();
                    String nhaXuatBan = sach.getNhaXuatBan();
                    String theLoai = sach.getTheLoai();
                    if (tenSach.matches("\\s*")) {
                        ps.setString(1, "%%");
                    } else {
                        ps.setString(1, sach.getTenSanPham());
                    }
                    if (tacGia.matches("\\s*")) {
                        ps.setString(2, "%%");
                    } else {
                        ps.setString(2, sach.getTacGia());
                    }
                    if (nhaXuatBan.matches("\\s*")) {
                        ps.setString(3, "%%");
                    } else {
                        ps.setString(3, sach.getNhaXuatBan());
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (sach.getNamSanXuat() == null) {
                        ps.setString(4, "%%");
                    } else {
                        ps.setString(4, sdf.format(sach.getNamSanXuat()));
                    }
                    if (theLoai.matches("\\s*")) {
                        ps.setString(5, "%%");
                    } else {
                        ps.setString(5, sach.getTheLoai());
                    }
                    if (sach.getNgayNhap() == null) {
                        ps.setString(6, "%%");
                    } else {
                        ps.setString(6, sdf.format(sach.getNgayNhap()));
                    }
                    if (giaMua == null) {
                        ps.setString(7, "%%");
                    } else {
                        ps.setString(7, "" + sach.getGiaMua());
                    }
                    ps.setInt(8, Min1);
                    ps.setInt(9, Max1);
                    ps.setInt(10, Min2);
                    ps.setInt(11, Max2);
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sach s = new Sach();
                    s.setTenSanPham(rs.getString("TenSach"));
                    s.setTacGia(rs.getString("TacGia"));
                    s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                    java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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

    public ArrayList<Sach> timKiemSach(boolean timKiemGanDung, Sach sach, Integer giaMuaMin, Integer giaMuaMax, Integer giaBanMin, Integer giaBanMax,
            Integer soLuongMin, Integer soLuongMax) {

        ArrayList<Sach> ds = new ArrayList<Sach>();
        String sql = "SELECT * FROM Sach "
                + "where TenSach like ? AND TacGia like ? "
                + "AND NhaXuatBan like ? AND NamXuatBan like ? AND TheLoai like ? AND NgayNhap like ? "
                + "AND (GiaMua between ? and ?) And (GiaBan between ? and ?) And (SoLuong between ? and ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (timKiemGanDung == true) {
                ps.setString(1, "%" + sach.getTenSanPham() + "%");
                ps.setString(2, "%" + sach.getTacGia() + "%");
                ps.setString(3, "%" + sach.getNhaXuatBan() + "%");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sach.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, "%" + sdf.format(sach.getNamSanXuat()) + "%");
                }
                ps.setString(5, "%" + sach.getTheLoai() + "%");
                if (sach.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, "%" + sdf.format(sach.getNgayNhap()) + "%");
                }
            } else {
                String tenSach = sach.getTenSanPham();
                String tacGia = sach.getTacGia();
                String nhaXuatBan = sach.getNhaXuatBan();
                String theLoai = sach.getTheLoai();
                if (tenSach.matches("\\s*")) {
                    ps.setString(1, "%%");
                } else {
                    ps.setString(1, sach.getTenSanPham());
                }
                if (tacGia.matches("\\s*")) {
                    ps.setString(2, "%%");
                } else {
                    ps.setString(2, sach.getTacGia());
                }
                if (nhaXuatBan.matches("\\s*")) {
                    ps.setString(3, "%%");
                } else {
                    ps.setString(3, sach.getNhaXuatBan());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sach.getNamSanXuat() == null) {
                    ps.setString(4, "%%");
                } else {
                    ps.setString(4, sdf.format(sach.getNamSanXuat()));
                }
                if (theLoai.matches("\\s*")) {
                    ps.setString(5, "%%");
                } else {
                    ps.setString(5, sach.getTheLoai());
                }
                if (sach.getNgayNhap() == null) {
                    ps.setString(6, "%%");
                } else {
                    ps.setString(6, sdf.format(sach.getNgayNhap()));
                }
            }
            if (giaMuaMin != null) {
                ps.setInt(7, giaMuaMin);
            }
            if (giaMuaMax != null) {
                ps.setInt(8, giaMuaMax);
            }
            if (giaBanMin != null) {
                ps.setInt(9, giaBanMin);
            }
            if (giaBanMax != null) {
                ps.setInt(10, giaBanMax);
            }
            if (soLuongMin != null) {
                ps.setInt(11, soLuongMin);
            }
            if (soLuongMax != null) {
                ps.setInt(12, soLuongMax);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
                s.setTacGia(rs.getString("TacGia"));
                s.setNhaXuatBan(rs.getString("NhaXuatBan"));
                java.sql.Date NamXuatBan = rs.getDate("NamXuatBan");
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

    public int themSach(Sach sach) {
        String sql = "Insert Into Sach values(?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getTenSanPham());
            ps.setString(2, sach.getTacGia());
            ps.setString(3, sach.getNhaXuatBan());
            ps.setDate(4, new java.sql.Date((sach.getNamSanXuat()).getTime()));
            ps.setString(5, sach.getTheLoai());
            ps.setDate(6, new java.sql.Date((sach.getNgayNhap()).getTime()));
            ps.setInt(7, sach.getGiaMua());
            ps.setInt(8, sach.getGiaBan());
            ps.setInt(9, sach.getSoLuong());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaSach(Sach sach) {
        String sql = "Update Sach set TacGia=?,NhaXuatBan=?,NamXuatBan=?,TheLoai=?,NgayNhap=?,GiaMua=?,GiaBan=?,SoLuong=?"
                + " where TenSach=?";
        int kq = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getTacGia());
            ps.setString(2, sach.getNhaXuatBan());
            ps.setDate(3, new java.sql.Date((sach.getNamSanXuat()).getTime()));
            ps.setString(4, sach.getTheLoai());
            ps.setDate(5, new java.sql.Date((sach.getNgayNhap()).getTime()));
            ps.setInt(6, sach.getGiaMua());
            ps.setInt(7, sach.getGiaBan());
            ps.setInt(8, sach.getSoLuong());
            ps.setString(9, sach.getTenSanPham());
            kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int xoaSach(Sach sach) {
        int kq = 0;
        String sql = "delete from Sach where TenSach=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getTenSanPham());
            kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Sach> sachNhapNgay(Date ngay) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from Sach where NgayNhap=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Sach> sachNhapNgay(Date ngayMin, Date ngayMax) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from Sach where NgayNhap>=? ANd NgayNhap <=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayMin.getTime()));
            ps.setDate(2, new java.sql.Date(ngayMax.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Sach> sachNhapNam(int nam) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from Sach where NgayNhap like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Sach> sachNhapThangNam(int thang, int nam) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from Sach where NgayNhap like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-" + thang + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
                s.setGiaMua(rs.getInt("GiaMua"));
                s.setSoLuong(rs.getInt("SoLuong"));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Sach> sachBanNgay(Date ngay) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangSach where MaBanHang in ( select MaBanHang from BanHang where NgayBan=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
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

    public ArrayList<Sach> sachBanNgay(Date ngayMin, Date ngayMax) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangSach where MaBanHang in ( select MaBanHang from BanHang where NgayBan>=? And NgayBan<=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayMin.getTime()));
            ps.setDate(2, new java.sql.Date(ngayMax.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
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

    public ArrayList<Sach> sachBanThang(int thang, int nam) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangSach where MaBanHang in ( select MaBanHang from BanHang where NgayBan like ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-" + thang + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
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

    public ArrayList<Sach> sachBanNam(int nam) {
        ArrayList<Sach> ds = new ArrayList<>();
        try {
            String sql = "select * from ChiTietBanHangSach where MaBanHang in ( select MaBanHang from BanHang where NgayBan like ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSanPham(rs.getString("TenSach"));
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

}
