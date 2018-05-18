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
import java.util.Date;
import object.CacChiPhiKhac;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryCacLoaiChiPhiKhac {
    
    private Connection con;
    
    public QueryCacLoaiChiPhiKhac() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
    }
    
    public ArrayList<CacChiPhiKhac> layToanBo() {
        ArrayList<CacChiPhiKhac> ds = new ArrayList<>();
        try {
            String sql = "select * from CacChiPhiKhac";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CacChiPhiKhac c = new CacChiPhiKhac();
                c.setLoaiChiPhi(rs.getString("LoaiChiPhi"));
                c.setNgay(new Date(rs.getDate("Ngay").getTime()));
                c.setTienTra(rs.getInt("TienTra"));
                ds.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public int themChiPhiKhac(CacChiPhiKhac c) {
        try {
            String sql = "insert into CacChiPhiKhac values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getLoaiChiPhi());
            ps.setDate(2, new java.sql.Date(c.getNgay().getTime()));
            ps.setInt(3, c.getTienTra());
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int xoaChiPhiKhac(CacChiPhiKhac c) {
        try {
            String sql = "delete from CacChiPhiKhac where LoaiChiPhi=? And Ngay=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getLoaiChiPhi());
            ps.setDate(2, new java.sql.Date(c.getNgay().getTime()));
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int suaChiPhiKhac(CacChiPhiKhac c) {
        try {
            String sql = "update CacChiPhiKhac set TienTra=? where LoaiChiPhi=? And Ngay=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getTienTra());
            ps.setString(2, c.getLoaiChiPhi());
            ps.setDate(3, new java.sql.Date(c.getNgay().getTime()));
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<CacChiPhiKhac> thongKeTheoNgay(Date ngay) {
        ArrayList<CacChiPhiKhac> ds = new ArrayList<>();
        try {
            String sql = "select LoaiChiPhi,Ngay,Sum(TienTra) As Tien from CacChiPhiKhac where Ngay=? group by LoaiChiPhi,Ngay";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CacChiPhiKhac c = new CacChiPhiKhac();
                c.setLoaiChiPhi(rs.getString("LoaiChiPhi"));
                c.setNgay(new Date(rs.getDate("Ngay").getTime()));
                c.setTienTra(rs.getInt("Tien"));
                ds.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<CacChiPhiKhac> thongKeTheoNgay(Date ngayMin, Date ngayMax) {
        ArrayList<CacChiPhiKhac> ds = new ArrayList<>();
        try {
            String sql = "select LoaiChiPhi,Ngay,Sum(TienTra) As Tien from CacChiPhiKhac where Ngay>=? And Ngay<=? group by LoaiChiPhi,Ngay";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayMin.getTime()));
            ps.setDate(2, new java.sql.Date(ngayMax.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CacChiPhiKhac c = new CacChiPhiKhac();
                c.setLoaiChiPhi(rs.getString("LoaiChiPhi"));
                c.setNgay(new Date(rs.getDate("Ngay").getTime()));
                c.setTienTra(rs.getInt("Tien"));
                ds.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<CacChiPhiKhac> thongKeTheoNam(int nam) {
        ArrayList<CacChiPhiKhac> ds = new ArrayList<>();
        try {
            String sql = "select LoaiChiPhi,Ngay,Sum(TienTra) As Tien from CacChiPhiKhac where Ngay like ? group by LoaiChiPhi,Ngay";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CacChiPhiKhac c = new CacChiPhiKhac();
                c.setLoaiChiPhi(rs.getString("LoaiChiPhi"));
                c.setNgay(new Date(rs.getDate("Ngay").getTime()));
                c.setTienTra(rs.getInt("Tien"));
                ds.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<CacChiPhiKhac> thongKeTheoThangNam(int thang, int nam) {
        ArrayList<CacChiPhiKhac> ds = new ArrayList<>();
        try {
            String sql = "select LoaiChiPhi,Ngay,Sum(TienTra) As Tien from CacChiPhiKhac where Ngay like ? group by LoaiChiPhi,Ngay";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-" + thang + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CacChiPhiKhac c = new CacChiPhiKhac();
                c.setLoaiChiPhi(rs.getString("LoaiChiPhi"));
                c.setNgay(new Date(rs.getDate("Ngay").getTime()));
                c.setTienTra(rs.getInt("Tien"));
                ds.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
