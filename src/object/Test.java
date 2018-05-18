/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class Test {

    public static void main(String[] args) {
        NhanVienChinhThuc.tienLamMotNgay = 100000;
        NhanVienChinhThuc.tienLamThemMotNgay = 110000;
        NhanVienThoiVu.tienLamMotNgay = 50000;
        NhanVienThoiVu.tienLamThemMotNgay = 60;

        QuanLy quanLy = new QuanLy();
        NhanVienChinhThuc nv1 = new NhanVienChinhThuc();
        nv1.setSoNgayLam(26);
        NhanVienThoiVu nv2 = new NhanVienThoiVu();
        nv2.setSoNgayLam(10);
        ArrayList<NhanVien> ds = new ArrayList<>();
        ds.add(nv1);
        ds.add(nv2);
        int tongTienLuong = 0;
        tongTienLuong = quanLy.tinhTongLuongNhanVien(ds);
        System.out.println("Tổng tiền lương:" + tongTienLuong);

        BanHang banHang = new BanHang();
        Sach sach = new Sach();
        sach.setTenSanPham("doremon");
        sach.setTacGia("nobita");
        sach.setSoLuong(5);
        DiaNhac diaNhac = new DiaNhac();
        diaNhac.setTenSanPham("abc");
        diaNhac.setCaSi("sontung");
        diaNhac.setSoLuong(2);

        ArrayList<SanPham> dssp = new ArrayList<>();
        SanPham diaPhim = new DiaPhim(); // upcasting
        dssp.add(sach); // upcasting
        dssp.add(diaNhac);
        banHang.setDanhSachSanPhamMua(dssp);

        for (SanPham sp : dssp) {
            if (sp instanceof Sach) {
                // downcasting
                Sach sach2 = (Sach) sp;
                System.out.println("Tên sách:" + sach2.getTenSanPham() + " tác giả:" + sach2.getTacGia() + " số lượng:" + sach2.getSoLuong());
            } else if (sp instanceof DiaNhac) {
                DiaNhac diaNhac1 = (DiaNhac) sp;
                System.out.println("Tên đĩa:" + diaNhac1.getTenSanPham() + " ca sĩ:" + diaNhac1.getCaSi() + " số lượng:" + diaNhac1.getSoLuong());
            }
        }
        Sach sach1 = new Sach();
        sach1.setTenSanPham("doremon");
        sach1.setSoLuong(2);
        banHang.themSanPhamVaoDanhSach(sach1);
        for (SanPham sp : banHang.getDanhSachSanPhamMua()) {
            System.out.println("Tên:" + sp.getTenSanPham() + " số lượng:" + sp.getSoLuong());
        }
        // xóa đi 1 quyển doremon
        if (banHang.xoaSanPhamKhoiDanhSach(sach, 1)) {
            System.out.println("Xóa thành công");
            for (SanPham sp : banHang.getDanhSachSanPhamMua()) {
                System.out.println("Tên:" + sp.getTenSanPham() + " số lượng:" + sp.getSoLuong());
            }
        } else {
            System.out.println("Xóa thất bại");
        }
        // xóa toàn bộ quyển doremon
        if (banHang.xoaSanPhamKhoiDanhSach(sach)) {
            System.out.println("Xóa thành công");
            for (SanPham sp : banHang.getDanhSachSanPhamMua()) {
                System.out.println("Tên:" + sp.getTenSanPham() + " số lượng:" + sp.getSoLuong());
            }
        } else {
            System.out.println("Xóa thất bại");
        }

        String regex = "^KHV";
        String s = "KHV12";
        s = s.substring(0, 3);
        System.out.println(s);
        if (s.matches(regex)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
