/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.MyConnection;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class TaoMa {

    private Connection con;

    public TaoMa() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
    }

    public String taoChuoiTuSoDaCho(int n) {
        int sotieptheo = 0;
        if (n >= 0 && n < 9) {
            sotieptheo = n + 1;
            return "000" + sotieptheo;
        } else if (n >= 9 && n < 99) {
            sotieptheo = n + 1;
            return "00" + sotieptheo;
        } else if (n >= 99 && n < 999) {
            sotieptheo = n + 1;
            return "0" + sotieptheo;
        } else if (n >= 999 && n < 9999) {
            sotieptheo = n + 1;
            return "" + sotieptheo;
        }
        return "";
    }

    public int laySoCuoiCuaBangNhanVien() {
        try {
            String sql = "select MaNhanVien from NhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String str = "";
            while (rs.next()) {
                str = rs.getString("MaNhanVien");
            }
            if (str.equals("")) {
                return 0;
            } else {
                str = str.substring(3);
                int stt = Integer.parseInt(str);
                return stt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int laySoCuoiCuaBangKhachHangVIP() {
        try {
            String sql = "select MaKhachHang from KhachHang where MaKhachHang like 'KHV%'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String str = "";
            while (rs.next()) {
                str = rs.getString("MaKhachHang");
            }
            if (str.equals("")) {
                return 0;
            } else {
                str = str.substring(3);
                int stt = Integer.parseInt(str);
                return stt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int laySoCuoiCuaBangBanHang() {
        try {
            String sql = "select MaBanHang from BanHang";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String str = "";
            while (rs.next()) {
                str = rs.getString("MaBanHang");
            }
            if (str.equals("")) {
                return 0;
            } else {
                str = str.substring(3);
                int stt = Integer.parseInt(str);
                return stt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int laySoCuoiCuaBangKhachHangThuong() {
        try {
            String sql = "select MaKhachHang from KhachHang where MaKhachHang like 'KHT%'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String str = "";
            while (rs.next()) {
                str = rs.getString("MaKhachHang");
            }
            if (str.equals("")) {
                return 0;
            } else {
                str = str.substring(3);
                int stt = Integer.parseInt(str);
                return stt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        TaoMa t = new TaoMa();
        System.out.println("" + t.taoChuoiTuSoDaCho(t.laySoCuoiCuaBangBanHang()));
    }
}
