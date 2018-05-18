/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ireport;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import model.MyConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import object.BanHang;
import object.KhachHang;
import object.NhanVien;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class PhieuMuaHang {

    private Connection con;

    public PhieuMuaHang() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
    }

    public void inPhieuKhachHangVip(BanHang bh, int chietKhau, File f) {
        try {
            //File file = new File("src/ireport/Sach/reportKetQuaTimKiemSach.jrxml");
            KhachHang kh = bh.getKhachHangMua();
            NhanVien nv = bh.getNhanVienBan();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/PhieuMuaHangKhachVIP.jrxml");
            Hashtable paramenter = new Hashtable();
            paramenter.put("pMaBanHang", bh.getMaBanHang());
            paramenter.put("pMaNhanVien", nv.getMaNhanVien());
            paramenter.put("pTenNhanVien", nv.getTen());
            paramenter.put("pChietKhau", chietKhau + " %");
            paramenter.put("pMaKhachHang", kh.getMaKhachHang());
            paramenter.put("pTenKhachHang", kh.getTen());
            paramenter.put("pGioiTinh", kh.getGioiTinh());
            paramenter.put("pNamSinh", sdf.format(kh.getNamSinh()));
            paramenter.put("pSoCMND", kh.getSoCMND());
            paramenter.put("pSoDienThoai", kh.getSoDienThoai());
            paramenter.put("pDiaChi", kh.getDiaChi());
            paramenter.put("pTongTien", bh.thanhTien(5) + " VND");
            JasperPrint print = JasperFillManager.fillReport(report, paramenter, con);
//            JasperViewer view = new JasperViewer(print, false);
//            view.setVisible(true);
            JasperExportManager.exportReportToPdfFile(print, f.getAbsolutePath());
            Desktop d = Desktop.getDesktop();
            d.open(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inPhieuKhachHangThuong(BanHang bh, File f) {
        try {
            //File file = new File("src/ireport/Sach/reportKetQuaTimKiemSach.jrxml");
            KhachHang kh = bh.getKhachHangMua();
            NhanVien nv = bh.getNhanVienBan();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/PhieuMuaHangKhachThuong.jrxml");
            Hashtable paramenter = new Hashtable();
            paramenter.put("pMaBanHang", bh.getMaBanHang());
            paramenter.put("pMaNhanVien", nv.getMaNhanVien());
            paramenter.put("pTenNhanVien", nv.getTen());
            paramenter.put("pChietKhau", 0 + " %");
            paramenter.put("pMaKhachHang", kh.getMaKhachHang());
            paramenter.put("pTongTien", bh.thanhTien(0) + " VND");
            JasperPrint print = JasperFillManager.fillReport(report, paramenter, con);
//            JasperViewer view = new JasperViewer(print, false);
//            view.setVisible(true);
            JasperExportManager.exportReportToPdfFile(print, f.getAbsolutePath());
            Desktop d = Desktop.getDesktop();
            d.open(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BanHang bh = new BanHang();
        bh.setMaBanHang("MBH0003");
        NhanVien nv = new NhanVien();
        nv.setMaNhanVien("NV0002");
        nv.setTen("Giang");
        bh.setNhanVienBan(nv);
        KhachHang kh = new KhachHang("KH", "Giang");
        bh.setKhachHangMua(kh);
        PhieuMuaHang p = new PhieuMuaHang();
//        p.inPhieuKhachHangThuong(bh);
    }
}
