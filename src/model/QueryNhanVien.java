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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import object.NhanVien;
import object.NhanVienChinhThuc;
import object.NhanVienThoiVu;
import object.TaiKhoanDangNhap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryNhanVien {

    private Connection con;
    private QueryTaiKhoan qTaiKhoan;
    private SimpleDateFormat sdf;

    public QueryNhanVien() {
        con = MyConnection.getConnection("QuanLyCuaHang", "sa", "hot10000%");
        qTaiKhoan = new QueryTaiKhoan();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public NhanVien timKiemNhanVienTheoMaTaiKhoan(TaiKhoanDangNhap taiKhoan) {
        NhanVien nv = new NhanVien();
        try {
            String sql = "select NhanVien.MaNhanVien,NhanVien.TenNhanVien,NhanVien.GioiTinh,NhanVien.NamSinh,LoaiNhanVien.TenLoaiNhanVien,NhanVien.SoCMND,"
                    + "NhanVien.SoDienThoai,NhanVien.DiaChi from NhanVien,LoaiNhanVien "
                    + "where NhanVien.MaLoaiNhanVien=LoaiNhanVien.MaLoaiNhanVien And MaTaiKhoan=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTenDangNhap());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loaiNhanVien = rs.getString("TenLoaiNhanVien");
                if (loaiNhanVien.equalsIgnoreCase("Chính Thức")) {
                    nv = new NhanVienChinhThuc();
                } else if (loaiNhanVien.equalsIgnoreCase("Thời Vụ")) {
                    nv = new NhanVienThoiVu();
                }
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                Date namSinh = rs.getDate("NamSinh");
                if (namSinh != null) {
                    nv.setNamSinh(new Date(namSinh.getTime()));
                } else {
                    nv.setNamSinh(null);
                }
                nv.setSoCMND(rs.getString("SoCMND"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setTaiKhoan(taiKhoan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public ArrayList<NhanVien> timKiemNhanVien(NhanVien nv) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            String sql = "select NhanVien.MaNhanVien,NhanVien.TenNhanVien,NhanVien.GioiTinh,NhanVien.NamSinh,LoaiNhanVien.TenLoaiNhanVien,"
                    + "NhanVien.SoDienThoai,NhanVien.SoCMND,NhanVien.DiaChi"
                    + " from NhanVien,LoaiNhanVien where NhanVien.MaLoaiNhanVien=LoaiNhanVien.MaLoaiNhanVien "
                    + "And MaNhanVien like ? And TenNhanVien like ? And GioiTinh like ? And NhanVien.MaLoaiNhanVien like ? "
                    + "And NamSinh like ? And SoCMND like ? And SoDienThoai like ? And DiaChi like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nv.getMaNhanVien() + "%");
            ps.setString(2, "%" + nv.getTen() + "%");
            ps.setString(3, "%" + nv.getGioiTinh() + "%");
            if (nv instanceof NhanVienChinhThuc) {
                ps.setString(4, "%CT%");
            } else if (nv instanceof NhanVienThoiVu) {
                ps.setString(4, "%TV%");
            } else {
                ps.setString(4, "%%");
            }
            if (nv.getNamSinh() == null) {
                ps.setString(5, "%%");
            } else {
                ps.setString(5, "%" + sdf.format(nv.getNamSinh()) + "%");
            }
            ps.setString(6, "%" + nv.getSoCMND() + "%");
            ps.setString(7, "%" + nv.getSoDienThoai() + "%");
            ps.setString(8, "%" + nv.getDiaChi() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loaiNhanVien = rs.getString("TenLoaiNhanVien");
                NhanVien nvTimThay;
                if (loaiNhanVien.equals("Chính Thức")) {
                    nvTimThay = new NhanVienChinhThuc();
                } else {
                    nvTimThay = new NhanVienThoiVu();
                }
                nvTimThay.setMaNhanVien(rs.getString("MaNhanVien"));
                nvTimThay.setTen(rs.getString("TenNhanVien"));
                nvTimThay.setGioiTinh(rs.getString("GioiTinh"));
                if (rs.getDate("NamSinh") == null) {
                    nvTimThay.setNamSinh(null);
                } else {
                    nvTimThay.setNamSinh(new java.sql.Date(rs.getDate("NamSinh").getTime()));
                }
                nvTimThay.setSoCMND(rs.getString("SoCMND"));
                nvTimThay.setSoDienThoai(rs.getString("SoDienThoai"));
                nvTimThay.setDiaChi(rs.getString("DiaChi"));
                ds.add(nvTimThay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int themNhanVien(NhanVien nv) {
        try {
            qTaiKhoan.taoTaiKhoan(nv.getMaNhanVien());
            String sql = "Insert Into NhanVien Values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTen());
            ps.setString(4, nv.getGioiTinh());
            if (nv.getNamSinh() == null) {
                ps.setDate(5, null);
            } else {
                ps.setDate(5, new java.sql.Date(nv.getNamSinh().getTime()));
            }
            if (nv instanceof NhanVienChinhThuc) {
                ps.setString(3, "CT");
            } else {
                ps.setString(3, "TV");
            }
            ps.setString(6, nv.getSoDienThoai());
            ps.setString(7, nv.getSoCMND());
            ps.setString(8, nv.getDiaChi());
            ps.setString(9, nv.getMaNhanVien());
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaNhanVien(NhanVien nv) {
        try {
            String sql = "update NhanVien set TenNhanVien=?,GioiTinh=?,NamSinh=?,MaLoaiNhanVien=?,SoCMND=?,SoDienThoai=?,DiaChi=? where MaNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getTen());
            ps.setString(2, nv.getGioiTinh());
            if (nv.getNamSinh() == null) {
                ps.setDate(3, null);
            } else {
                ps.setDate(3, new java.sql.Date(nv.getNamSinh().getTime()));
            }
            if (nv instanceof NhanVienChinhThuc) {
                ps.setString(4, "CT");
            } else {
                ps.setString(4, "TV");
            }
            ps.setString(5, nv.getSoCMND());
            ps.setString(6, nv.getSoDienThoai());
            ps.setString(7, nv.getDiaChi());
            ps.setString(8, nv.getMaNhanVien());
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaNhanVien(String maNhanVien) {
        try {
            String sql = "delete from NhanVien where MaNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            int kq = ps.executeUpdate();
            if (kq > 0) {
                qTaiKhoan.xoaTaiKhoan(maNhanVien);
            }
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<NhanVien> layToanBoNhanVien() {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            String sql = "select NhanVien.MaNhanVien,NhanVien.TenNhanVien,NhanVien.GioiTinh,NhanVien.NamSinh,LoaiNhanVien.TenLoaiNhanVien,NhanVien.SoCMND,NhanVien.SoDienThoai,NhanVien.DiaChi"
                    + " from NhanVien,LoaiNhanVien where NhanVien.MaLoaiNhanVien=LoaiNhanVien.MaLoaiNhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loaiNhanVien = rs.getString("TenLoaiNhanVien");
                if (loaiNhanVien.equalsIgnoreCase("Chính Thức")) {
                    NhanVienChinhThuc nv = new NhanVienChinhThuc();
                    nv.setMaNhanVien(rs.getString("MaNhanVien"));
                    nv.setTen(rs.getString("TenNhanVien"));
                    nv.setGioiTinh(rs.getString("GioiTinh"));
                    nv.setSoCMND(rs.getString("SoCMND"));
                    nv.setSoDienThoai(rs.getString("SoDienThoai"));
                    nv.setDiaChi(rs.getString("DiaChi"));
                    java.sql.Date namSinh = rs.getDate("NamSinh");
                    if (namSinh != null) {
                        nv.setNamSinh(new Date(namSinh.getTime()));
                    } else {
                        nv.setNamSinh(null);
                    }
                    ds.add(nv);
                } else {
                    NhanVienThoiVu nv = new NhanVienThoiVu();
                    nv.setMaNhanVien(rs.getString("MaNhanVien"));
                    nv.setTen(rs.getString("TenNhanVien"));
                    nv.setGioiTinh(rs.getString("GioiTinh"));
                    nv.setSoCMND(rs.getString("SoCMND"));
                    nv.setSoDienThoai(rs.getString("SoDienThoai"));
                    nv.setDiaChi(rs.getString("DiaChi"));
                    java.sql.Date namSinh = rs.getDate("NamSinh");
                    if (namSinh != null) {
                        nv.setNamSinh(new Date(namSinh.getTime()));
                    } else {
                        nv.setNamSinh(null);
                    }
                    ds.add(nv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    private NhanVien timKiemNhanVienTheoMaNhanVien(String maNhanVien) {
        try {
            String sql = "select * from NhanVien where MaNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maLoaiNhanVien = rs.getString("MaLoaiNhanVien");
                NhanVien nv;
                if (maLoaiNhanVien.equals("CT")) {
                    nv = new NhanVienChinhThuc();
                } else {
                    nv = new NhanVienThoiVu();
                }
                nv.setMaNhanVien(maNhanVien);
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                java.sql.Date namSinh = rs.getDate("NamSinh");
                if (namSinh == null) {
                    nv.setNamSinh(null);
                } else {
                    nv.setNamSinh(new Date(namSinh.getTime()));
                }
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setSoCMND(rs.getString("SoCMND"));
                nv.setDiaChi(rs.getString("DiaChi"));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<NhanVien, Date> layToanLuongNhanVien() {
        HashMap<NhanVien, Date> ds = new HashMap<>();
        try {
            String sql = "select * from LuongNhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("MaNhanVien");
                Integer soNgayLam = rs.getInt("SoNgayLam");
                Integer soNgayLamThem = rs.getInt("SoNgayLamThem");
                java.sql.Date ngayTraLuongQSL = rs.getDate("NgayTraLuong");
                java.sql.Date thang = rs.getDate("ThoiGian(Thang/Nam)");
                Integer tienTra = rs.getInt("TienTra");
                NhanVien nv = timKiemNhanVienTheoMaNhanVien(maNhanVien);
                nv.setSoNgayLam(soNgayLam);
                nv.setSoNgayLamThem(soNgayLamThem);
                if (ngayTraLuongQSL == null) {
                    nv.setNgayTraLuong(null);
                } else {
                    nv.setNgayTraLuong(new Date(ngayTraLuongQSL.getTime()));
                }
                ds.put(nv, thang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Integer tienLamMotNgay(String MaLoaiNhanVien) {
        Integer tien = 0;
        try {
            String sql = "select TienLamMotNgay from LoaiNhanVien where MaLoaiNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaLoaiNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tien = rs.getInt("TienLamMotNgay");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tien;
    }

    public Integer tienLamThemMotNgay(String MaLoaiNhanVien) {
        Integer tien = 0;
        try {
            String sql = "select TienLamThemMotNgay from LoaiNhanVien where MaLoaiNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaLoaiNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tien = rs.getInt("TienLamThemMotNgay");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tien;
    }

    public int themLuongNhanVien(NhanVien nv, Date thang) {
        try {
            String sql = "insert into LuongNhanVien values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(thang.getTime()));
            ps.setString(2, nv.getMaNhanVien());
            ps.setInt(3, nv.getSoNgayLam());
            ps.setInt(4, nv.getSoNgayLamThem());
            ps.setInt(6, nv.tinhLuong());
            ps.setDate(5, new java.sql.Date(nv.getNgayTraLuong().getTime()));
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean kiemTraTraLuong(NhanVien nv, Date thang) {
        try {
            String sql = "select * from LuongNhanVien where [ThoiGian(Thang/Nam)] like ? And MaNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            ps.setString(1, sdf.format(thang) + "%");
            ps.setString(2, nv.getMaNhanVien());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int suaLuongNhanVien(NhanVien nv, Date thang) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String sql = "update LuongNhanVien set SoNgayLam=?,SoNgayLamThem=?,NgayTraLuong=?,TienTra=? where [ThoiGian(Thang/Nam)] like ? And MaNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nv.getSoNgayLam());
            ps.setInt(2, nv.getSoNgayLamThem());
            ps.setDate(3, new java.sql.Date(nv.getNgayTraLuong().getTime()));
            ps.setInt(4, nv.tinhLuong());
            ps.setString(5, sdf.format(thang) + "%");
            ps.setString(6, nv.getMaNhanVien());
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaLuongNhanVien(NhanVien nv, Date thang) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String sql = "delete from LuongNhanVien where [ThoiGian(Thang/Nam)] like ? And MaNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(thang) + "%");
            ps.setString(2, nv.getMaNhanVien());
            int kq = ps.executeUpdate();
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<NhanVien> nhanVienTraLuong(Date ngayMin, Date ngayMax) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            String sql = "select NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien,Sum(SoNgayLam) As NgayLam,Sum(SoNgayLamThem) As NgayLamThem\n"
                    + "from LuongNhanVien,NhanVien \n"
                    + "where LuongNhanVien.MaNhanVien=NhanVien.MaNhanVien And NgayTraLuong >= ? And NgayTraLuong <=?\n"
                    + "group by NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayMin.getTime()));
            ps.setDate(2, new java.sql.Date(ngayMax.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiNhanVien = rs.getString("MaLoaiNhanVien");
                NhanVien nv;
                if (maLoaiNhanVien.equals("CT")) {
                    nv = new NhanVienChinhThuc();
                } else {
                    nv = new NhanVienThoiVu();
                }
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setSoNgayLam(rs.getInt("NgayLam"));
                nv.setSoNgayLamThem(rs.getInt("NgayLamThem"));
                ds.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<NhanVien> nhanVienTraLuong(Date ngay) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            String sql = "select NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien,Sum(SoNgayLam) As NgayLam,Sum(SoNgayLamThem) As NgayLamThem\n"
                    + "from LuongNhanVien,NhanVien \n"
                    + "where LuongNhanVien.MaNhanVien=NhanVien.MaNhanVien And NgayTraLuong = ?\n"
                    + "group by NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiNhanVien = rs.getString("MaLoaiNhanVien");
                NhanVien nv;
                if (maLoaiNhanVien.equals("CT")) {
                    nv = new NhanVienChinhThuc();
                } else {
                    nv = new NhanVienThoiVu();
                }
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setSoNgayLam(rs.getInt("NgayLam"));
                nv.setSoNgayLamThem(rs.getInt("NgayLamThem"));
                ds.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<NhanVien> thongKeNhanVienTheoThang(int thang, int nam) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            String sql = "select NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien,Sum(SoNgayLam) As NgayLam,Sum(SoNgayLamThem) As NgayLamThem\n"
                    + "from LuongNhanVien,NhanVien \n"
                    + "where LuongNhanVien.MaNhanVien=NhanVien.MaNhanVien And NgayTraLuong like ?\n"
                    + "group by NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "-" + thang + "-%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiNhanVien = rs.getString("MaLoaiNhanVien");
                NhanVien nv;
                if (maLoaiNhanVien.equals("CT")) {
                    nv = new NhanVienChinhThuc();
                } else {
                    nv = new NhanVienThoiVu();
                }
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setSoNgayLam(rs.getInt("NgayLam"));
                nv.setSoNgayLamThem(rs.getInt("NgayLamThem"));
                ds.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<NhanVien> thongKeNhanVienTheoNam(int nam) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            String sql = "select NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien,Sum(SoNgayLam) As NgayLam,Sum(SoNgayLamThem) As NgayLamThem\n"
                    + "from LuongNhanVien,NhanVien \n"
                    + "where LuongNhanVien.MaNhanVien=NhanVien.MaNhanVien And NgayTraLuong like ?\n"
                    + "group by NhanVien.MaNhanVien,NhanVien.TenNhanVien,MaLoaiNhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nam + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiNhanVien = rs.getString("MaLoaiNhanVien");
                NhanVien nv;
                if (maLoaiNhanVien.equals("CT")) {
                    nv = new NhanVienChinhThuc();
                } else {
                    nv = new NhanVienThoiVu();
                }
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setSoNgayLam(rs.getInt("NgayLam"));
                nv.setSoNgayLamThem(rs.getInt("NgayLamThem"));
                ds.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public NhanVien layThongTinNhanVien(String maNhanVien) {
        NhanVien nv;
        try {
            String sql = "select * from NhanVien where MaNhanVien=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String loaiNhanVien = rs.getString("MaLoaiNhanVien");
                if (loaiNhanVien.equals("CT")) {
                    nv = new NhanVienChinhThuc();
                } else {
                    nv = new NhanVienThoiVu();
                }
                nv.setMaNhanVien(maNhanVien);
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNamSinh(new Date(rs.getDate("NamSinh").getTime()));
                nv.setSoCMND(rs.getString("SoCMND"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setDiaChi(rs.getString("DiaChi"));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        QueryNhanVien q = new QueryNhanVien();
        NhanVien nv = q.layThongTinNhanVien("nv0002");
        System.out.println(""+nv.getTen());
    }
}
