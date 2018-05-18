/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controler.BLLBanHang;
import controler.BLLDiaNhac;
import controler.BLLDiaPhim;
import controler.BLLKhachHang;
import controler.BLLNhanVien;
import controler.BLLSach;
import ireport.PhieuMuaHang;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import object.BanHang;
import object.DiaNhac;
import object.DiaPhim;
import object.KhachHang;
import object.NhanVien;
import object.Sach;
import object.SanPham;
import object.TaoMa;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BanHangGUI extends javax.swing.JFrame {

    /**
     * Creates new form BanHangGUI
     */
    private int index;
    private int indexTimKiem;
    private int indexTimKiemTheo;
    private int indexPanel;
    private BanHang banHang;
    private BLLSach bllSach;
    private BLLDiaNhac bllDiaNhac;
    private BLLDiaPhim bllDiaPhim;
    private BLLNhanVien bLLNhanVien;
    private BLLKhachHang bllKhachHang;
    private BLLBanHang bllBanHang;
    private SimpleDateFormat sdf;
    private DefaultTableModel dtmGioHang;
    private TaoMa taoMa;
    private int chietKhauKhachVip;
    private KhachHang khMua;
    private PhieuMuaHang phieuMuaHang;
    private NhanVien nv;

    public BanHangGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        khoiTaoDoiTuong();
        pnKhachHangVip.setVisible(false);
        hienThiGiaoDienTimKiemSach();
        hienThiBangGioHang(jTable1, null);
        hienThiBangGioHang(jTable3, null);

        String maBanHang = "MBH" + taoMa.taoChuoiTuSoDaCho(taoMa.laySoCuoiCuaBangBanHang());
        lblMaBanHang.setText(maBanHang);
        banHang.setMaBanHang(maBanHang);

        String maKhachHang = "KHT" + taoMa.taoChuoiTuSoDaCho(taoMa.laySoCuoiCuaBangKhachHangThuong());
        lblMaKhachHangThuong.setText(maKhachHang);
        khMua = new KhachHang(maKhachHang, "");
        banHang.setKhachHangMua(khMua);

        Calendar cal = Calendar.getInstance();
        lblNgayBan.setText(sdf.format(cal.getTime()));
        banHang.setNgayBan(cal.getTime());

        hienThiKhachHangVipLenComboBox();
        layThongTinNhanVienDangNhap();
        banHang.setNhanVienBan(nv);

        cbxLoaiKhachHang.setSelectedIndex(0);
        lblTongTien.setText("0 VND");
        lblChietKhau.setText("0 %");
        lblThanhTien.setText("0 VND");
        lblTienThua.setText("0 VND");
        indexTimKiemTheo = 0;

        hienThiGiaoDienTimKiemSach();
    }

    private void layThongTinNhanVienDangNhap() {
        nv = bLLNhanVien.timNhanVienTheoMaTaiKhoan(Login.taiKhoanDangNhap.getTenDangNhap());
        lblMaNhanVien.setText(nv.getMaNhanVien());
        lblTenNhanVien.setText(nv.getTen());
        banHang.setNhanVienBan(nv);
    }

    private void hienThiBangGioHang(JTable tbl, ArrayList<SanPham> ds) {
        dtmGioHang.setRowCount(0);
        if (ds != null) {
            for (SanPham sp : ds) {
                Vector<String> v = new Vector<>();
                v.add(sp.getTenSanPham());
                if (sp instanceof Sach) {
                    v.add("Sách");
                } else if (sp instanceof DiaNhac) {
                    v.add("Đĩa Nhạc");
                } else if (sp instanceof DiaPhim) {
                    v.add("Đĩa Phim");
                }
                v.add(sdf.format(sp.getNgayNhap()));
                v.add(sp.getSoLuong() + "");
                v.add(sp.getGiaBan() + " VND");
                v.add(sp.thanhTien() + " VND");
                dtmGioHang.addRow(v);
            }
        }
        tbl.setModel(dtmGioHang);
    }

    private void hienThiKhachHangVipLenComboBox() {
        ArrayList<KhachHang> ds = bllKhachHang.layToanBoKhachHangVip();
        DefaultComboBoxModel<String> cbxm = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Object> cbxmTen = new DefaultComboBoxModel<>();
        for (KhachHang kh : ds) {
            cbxm.addElement(kh.getMaKhachHang());
            cbxmTen.addElement(kh);
        }
        cbxMaKhachHang.setModel(cbxm);
        cbxTenKhachHang.setModel(cbxmTen);
        cbxMaKhachHang.setSelectedIndex(-1);
        cbxTenKhachHang.setSelectedIndex(-1);
    }

    private void khoiTaoDoiTuong() {
        banHang = new BanHang();
        bllSach = new BLLSach();
        bllDiaNhac = new BLLDiaNhac();
        bllDiaPhim = new BLLDiaPhim();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        taoMa = new TaoMa();
        bllKhachHang = new BLLKhachHang();
        bLLNhanVien = new BLLNhanVien();
        bllBanHang = new BLLBanHang();
        phieuMuaHang = new PhieuMuaHang();

        dtmGioHang = new DefaultTableModel();
        dtmGioHang.addColumn("Tên Sản Phẩm");
        dtmGioHang.addColumn("Loại Sản Phẩm");
        dtmGioHang.addColumn("Ngày Nhập");
        dtmGioHang.addColumn("Số Lượng");
        dtmGioHang.addColumn("Giá Bán");
        dtmGioHang.addColumn("Thành Tiền");

        chietKhauKhachVip = 5;
        khMua = new KhachHang();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMaBanHang = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMaNhanVien = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxLoaiKhachHang = new javax.swing.JComboBox<>();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        pnKhachHangThuong = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblMaKhachHangThuong = new javax.swing.JLabel();
        pnKhachHangVip = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbxMaKhachHang = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cbxTenKhachHang = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblNamSinh = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblSoCMND = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNgayBan = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblChietKhau = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tienKhachTra = new com.toedter.components.JSpinField();
        jLabel22 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        chkInHoaDon = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbxTimKiemTheo = new javax.swing.JComboBox<>();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnSach = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jDateChooserNamXuatBan = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        txtTheLoai = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        rdKhoangGiaMuaSach = new javax.swing.JRadioButton();
        giaMuaSachMin = new com.toedter.components.JSpinField();
        lbl = new javax.swing.JLabel();
        giaMuaSachMax = new com.toedter.components.JSpinField();
        txtNhaXuatBan = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jDateChooserNgayNhapSach = new com.toedter.calendar.JDateChooser();
        rdGiaMuaSachChinhXac = new javax.swing.JRadioButton();
        jSpinFieldGiaMua = new com.toedter.components.JSpinField();
        jLabel63 = new javax.swing.JLabel();
        rdKhoanGiaBanSach = new javax.swing.JRadioButton();
        jSpinFieldGiaBanSachMin = new com.toedter.components.JSpinField();
        jLabel64 = new javax.swing.JLabel();
        jSpinFieldGiaBanSachMax = new com.toedter.components.JSpinField();
        rdGiaBanSachChinhXac = new javax.swing.JRadioButton();
        jSpinFieldGiaBanSach = new com.toedter.components.JSpinField();
        jLabel30 = new javax.swing.JLabel();
        rdKhoangSoLuongSach = new javax.swing.JRadioButton();
        jSpinFieldSoLuongSachMin = new com.toedter.components.JSpinField();
        jLabel31 = new javax.swing.JLabel();
        jSpinFieldSoLuongSachMax = new com.toedter.components.JSpinField();
        rdSoLuongSachChinhXac = new javax.swing.JRadioButton();
        jSpinFieldSoLuongSach = new com.toedter.components.JSpinField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        pnDiaPhim = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtTenDiaPhim = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtDaoDien = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtDienVIen = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        namPhatHanhDiaPhim = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        theLoaiDiaPhim = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        ngayNhapDiaPhim = new com.toedter.calendar.JDateChooser();
        jLabel39 = new javax.swing.JLabel();
        rdKhoangGiaMuaDiaPhim = new javax.swing.JRadioButton();
        giaMuaDiaPhimMin = new com.toedter.components.JSpinField();
        giaMuaDiaPhimMax = new com.toedter.components.JSpinField();
        lbl1 = new javax.swing.JLabel();
        rdGiaBanSach1 = new javax.swing.JRadioButton();
        giaMuaDiaPhim = new com.toedter.components.JSpinField();
        jLabel42 = new javax.swing.JLabel();
        rdKhoangGiaBanDiaPhim = new javax.swing.JRadioButton();
        giaBanDiaPhimMin = new com.toedter.components.JSpinField();
        jLabel43 = new javax.swing.JLabel();
        giaBanDiaPhimMax = new com.toedter.components.JSpinField();
        jButton7 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        giaBanDiaPhim = new com.toedter.components.JSpinField();
        jLabel4 = new javax.swing.JLabel();
        rdKhoangSoLuongDiaPhim = new javax.swing.JRadioButton();
        soLuongDiaPhimMin = new com.toedter.components.JSpinField();
        jLabel6 = new javax.swing.JLabel();
        soLuongDiaPhimMax = new com.toedter.components.JSpinField();
        jRadioButton3 = new javax.swing.JRadioButton();
        soLuongDiaPhim = new com.toedter.components.JSpinField();
        jButton8 = new javax.swing.JButton();
        pnDiaNhac = new javax.swing.JPanel();
        rdKhoangGiaMuaDiaNhac = new javax.swing.JRadioButton();
        giaMuaDiaNhacMin = new com.toedter.components.JSpinField();
        giaMuaDiaNhacMax = new com.toedter.components.JSpinField();
        lbl2 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        rdGiaBanSach2 = new javax.swing.JRadioButton();
        txtTenDiaNhac = new javax.swing.JTextField();
        giaMuaDiaNhac = new com.toedter.components.JSpinField();
        jLabel47 = new javax.swing.JLabel();
        txtCaSi = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtNhaSanXuatDiaNhac = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        rdKhoangGiaBanDiaNhac = new javax.swing.JRadioButton();
        giaBanDiaNhacMin = new com.toedter.components.JSpinField();
        jLabel52 = new javax.swing.JLabel();
        namPhatHanhDiaNhac = new com.toedter.calendar.JDateChooser();
        giaBanDiaNhacMax = new com.toedter.components.JSpinField();
        jLabel53 = new javax.swing.JLabel();
        txtTheLoaiDiaNhac = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        ngayNhapDiaNhac = new com.toedter.calendar.JDateChooser();
        jLabel55 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        giaBanDiaNhac = new com.toedter.components.JSpinField();
        jLabel32 = new javax.swing.JLabel();
        rdKhoanSoLuongDiaNhac = new javax.swing.JRadioButton();
        soLuongDiaNhacMin = new com.toedter.components.JSpinField();
        jLabel33 = new javax.swing.JLabel();
        soLuongDiaNhacMax = new com.toedter.components.JSpinField();
        jRadioButton5 = new javax.swing.JRadioButton();
        soLuongDiaNhac = new com.toedter.components.JSpinField();
        jButton11 = new javax.swing.JButton();
        pnTatCa = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        rdGiaMuaSach3 = new javax.swing.JRadioButton();
        rdGiaBanSach3 = new javax.swing.JRadioButton();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jLabel60 = new javax.swing.JLabel();
        giaMuaSachMin3 = new com.toedter.components.JSpinField();
        giaMuaSachMax3 = new com.toedter.components.JSpinField();
        giaBanSachMax3 = new com.toedter.components.JSpinField();
        giaBanSachMin3 = new com.toedter.components.JSpinField();
        soLuongSachMin3 = new com.toedter.components.JSpinField();
        soLuongSachMax3 = new com.toedter.components.JSpinField();
        soLuongSach3 = new javax.swing.JRadioButton();
        jLabel61 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        soLuongMua = new com.toedter.components.JSpinField();
        jLabel2 = new javax.swing.JLabel();
        cbxLoaiTimKiem = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 670));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin:"));

        jLabel1.setText("Mã bán hàng:");

        jLabel3.setText("Mã Nhân Viên:");

        jLabel5.setText("Tên Nhân Viên:");

        jLabel7.setText("Loại Khách Hàng:");

        cbxLoaiKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách Hàng Thường", "Khách Hàng VIP" }));
        cbxLoaiKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLoaiKhachHangItemStateChanged(evt);
            }
        });

        pnKhachHangThuong.setBackground(new java.awt.Color(51, 204, 255));

        jLabel11.setText("Mã Khách Hàng:");

        javax.swing.GroupLayout pnKhachHangThuongLayout = new javax.swing.GroupLayout(pnKhachHangThuong);
        pnKhachHangThuong.setLayout(pnKhachHangThuongLayout);
        pnKhachHangThuongLayout.setHorizontalGroup(
            pnKhachHangThuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangThuongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaKhachHangThuong, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnKhachHangThuongLayout.setVerticalGroup(
            pnKhachHangThuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangThuongLayout.createSequentialGroup()
                .addGroup(pnKhachHangThuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaKhachHangThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 154, Short.MAX_VALUE))
        );

        pnKhachHangVip.setBackground(new java.awt.Color(51, 204, 255));

        jLabel15.setText("Mã khách hàng:");

        cbxMaKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMaKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaKhachHangItemStateChanged(evt);
            }
        });

        jLabel16.setText("Tên khách hàng:");

        cbxTenKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTenKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTenKhachHangItemStateChanged(evt);
            }
        });

        jLabel17.setText("Giới tính:");

        jLabel19.setText("Năm sinh:");

        jLabel21.setText("Số CMND:");

        jlabel.setText("Số điện thoại:");

        jLabel25.setText("Địa chỉ:");

        javax.swing.GroupLayout pnKhachHangVipLayout = new javax.swing.GroupLayout(pnKhachHangVip);
        pnKhachHangVip.setLayout(pnKhachHangVipLayout);
        pnKhachHangVipLayout.setHorizontalGroup(
            pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangVipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxMaKhachHang, 0, 180, Short.MAX_VALUE)
                    .addComponent(cbxTenKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSoCMND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnKhachHangVipLayout.setVerticalGroup(
            pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangVipLayout.createSequentialGroup()
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbxMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbxTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jLayeredPane2.setLayer(pnKhachHangThuong, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnKhachHangVip, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnKhachHangThuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnKhachHangVip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnKhachHangThuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnKhachHangVip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Ngày bán:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbxLoaiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addComponent(lblNgayBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblNgayBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxLoaiKhachHang)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(51, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh toán:"));

        jLabel8.setText("Tổng tiền:");

        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel10.setText("Chiết khấu:");

        lblChietKhau.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel14.setText("Thành tiền:");

        lblThanhTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThanhTien.setForeground(new java.awt.Color(255, 0, 0));
        lblThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel20.setText("Khách trả:");

        tienKhachTra.setBackground(new java.awt.Color(51, 204, 255));
        tienKhachTra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tienKhachTraPropertyChange(evt);
            }
        });

        jLabel22.setText("Tiền thừa");

        lblTienThua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 0, 0));
        lblTienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChietKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tienKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChietKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tienKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(51, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng:"));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Loại Sản Phẩm", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        chkInHoaDon.setBackground(new java.awt.Color(51, 204, 255));
        chkInHoaDon.setText("In hóa đơn");
        chkInHoaDon.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Cancel-16.png"))); // NOI18N
        jButton1.setText("Hủy bỏ");

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/credit-card.png"))); // NOI18N
        jButton2.setText("Bán hàng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(chkInHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkInHoaDon)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bán Hàng", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jPanel5.setBackground(new java.awt.Color(51, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm:"));

        jLabel12.setText("Tìm kiếm theo:");

        cbxTimKiemTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sách", "Đĩa nhạc", "Đĩa phim" }));
        cbxTimKiemTheo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTimKiemTheoItemStateChanged(evt);
            }
        });

        pnSach.setBackground(new java.awt.Color(51, 204, 255));

        jLabel18.setText("Tên sách:");

        jLabel23.setText("Tác giả:");

        jLabel24.setText("Nhà xuất bản:");

        jLabel26.setText("Năm xuất bản:");

        jDateChooserNamXuatBan.setBackground(new java.awt.Color(51, 204, 255));

        jLabel27.setText("Thể loại:");

        jLabel28.setText("Giá mua:");

        rdKhoangGiaMuaSach.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(rdKhoangGiaMuaSach);

        giaMuaSachMin.setBackground(new java.awt.Color(51, 204, 255));

        lbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl.setText("-");

        giaMuaSachMax.setBackground(new java.awt.Color(51, 204, 255));

        jLabel44.setText("Ngày nhập:");

        jDateChooserNgayNhapSach.setBackground(new java.awt.Color(51, 204, 255));

        rdGiaMuaSachChinhXac.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(rdGiaMuaSachChinhXac);

        jSpinFieldGiaMua.setBackground(new java.awt.Color(51, 204, 255));

        jLabel63.setText("Giá bán:");

        rdKhoanGiaBanSach.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup2.add(rdKhoanGiaBanSach);

        jSpinFieldGiaBanSachMin.setBackground(new java.awt.Color(51, 204, 255));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("-");

        jSpinFieldGiaBanSachMax.setBackground(new java.awt.Color(51, 204, 255));

        rdGiaBanSachChinhXac.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup2.add(rdGiaBanSachChinhXac);

        jSpinFieldGiaBanSach.setBackground(new java.awt.Color(51, 204, 255));

        jLabel30.setText("Số lượng:");

        rdKhoangSoLuongSach.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup3.add(rdKhoangSoLuongSach);

        jSpinFieldSoLuongSachMin.setBackground(new java.awt.Color(51, 204, 255));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("-");

        jSpinFieldSoLuongSachMax.setBackground(new java.awt.Color(51, 204, 255));

        rdSoLuongSachChinhXac.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup3.add(rdSoLuongSachChinhXac);

        jSpinFieldSoLuongSach.setBackground(new java.awt.Color(51, 204, 255));

        jButton5.setBackground(new java.awt.Color(51, 204, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        jButton5.setText("Tìm kiếm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 204, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton6.setText("Làm sạch");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSachLayout = new javax.swing.GroupLayout(pnSach);
        pnSach.setLayout(pnSachLayout);
        pnSachLayout.setHorizontalGroup(
            pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserNamXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNhaXuatBan)
                    .addComponent(txtTacGia)
                    .addComponent(txtTheLoai)
                    .addComponent(jDateChooserNgayNhapSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnSachLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnSachLayout.createSequentialGroup()
                        .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdGiaMuaSachChinhXac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdKhoangGiaMuaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rdGiaBanSachChinhXac)
                            .addComponent(rdKhoanGiaBanSach)
                            .addComponent(rdKhoangSoLuongSach)
                            .addComponent(rdSoLuongSachChinhXac))
                        .addGap(9, 9, 9)
                        .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnSachLayout.createSequentialGroup()
                                .addComponent(jSpinFieldSoLuongSachMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinFieldSoLuongSachMax, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSpinFieldGiaBanSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinFieldGiaMua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinFieldSoLuongSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnSachLayout.createSequentialGroup()
                                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnSachLayout.createSequentialGroup()
                                        .addComponent(jSpinFieldGiaBanSachMin, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinFieldGiaBanSachMax, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnSachLayout.createSequentialGroup()
                                        .addComponent(giaMuaSachMin, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(giaMuaSachMax, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnSachLayout.setVerticalGroup(
            pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSachLayout.createSequentialGroup()
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserNamXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTheLoai)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserNgayNhapSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdKhoangGiaMuaSach))
                    .addComponent(giaMuaSachMin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giaMuaSachMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinFieldGiaMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdGiaMuaSachChinhXac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnSachLayout.createSequentialGroup()
                        .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdKhoanGiaBanSach)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdGiaBanSachChinhXac))
                    .addGroup(pnSachLayout.createSequentialGroup()
                        .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinFieldGiaBanSachMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinFieldGiaBanSachMin, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinFieldGiaBanSach, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinFieldSoLuongSachMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinFieldSoLuongSachMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdKhoangSoLuongSach)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinFieldSoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdSoLuongSachChinhXac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(0, 31, Short.MAX_VALUE))
        );

        pnDiaPhim.setBackground(new java.awt.Color(51, 204, 255));

        jLabel29.setText("Tên đĩa phim:");

        jLabel34.setText("Đạo diễn:");

        jLabel35.setText("Diễn viên:");

        jLabel36.setText("Năm phát hành:");

        namPhatHanhDiaPhim.setBackground(new java.awt.Color(51, 204, 255));

        jLabel37.setText("Thể loại:");

        jLabel38.setText("Ngày nhập:");

        ngayNhapDiaPhim.setBackground(new java.awt.Color(51, 204, 255));

        jLabel39.setText("Giá mua:");

        rdKhoangGiaMuaDiaPhim.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup4.add(rdKhoangGiaMuaDiaPhim);

        giaMuaDiaPhimMin.setBackground(new java.awt.Color(51, 204, 255));

        giaMuaDiaPhimMax.setBackground(new java.awt.Color(51, 204, 255));

        lbl1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText("-");

        rdGiaBanSach1.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup4.add(rdGiaBanSach1);

        giaMuaDiaPhim.setBackground(new java.awt.Color(51, 204, 255));

        jLabel42.setText("Giá bán:");

        rdKhoangGiaBanDiaPhim.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup5.add(rdKhoangGiaBanDiaPhim);

        giaBanDiaPhimMin.setBackground(new java.awt.Color(51, 204, 255));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("-");

        giaBanDiaPhimMax.setBackground(new java.awt.Color(51, 204, 255));

        jButton7.setBackground(new java.awt.Color(51, 204, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        jButton7.setText("Tìm kiếm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup5.add(jRadioButton1);

        giaBanDiaPhim.setBackground(new java.awt.Color(51, 204, 255));

        jLabel4.setText("Số lượng:");

        rdKhoangSoLuongDiaPhim.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup6.add(rdKhoangSoLuongDiaPhim);

        soLuongDiaPhimMin.setBackground(new java.awt.Color(51, 204, 255));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("-");

        soLuongDiaPhimMax.setBackground(new java.awt.Color(51, 204, 255));

        jRadioButton3.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup6.add(jRadioButton3);

        soLuongDiaPhim.setBackground(new java.awt.Color(51, 204, 255));

        jButton8.setBackground(new java.awt.Color(51, 204, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton8.setText("Làm sạch");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDiaPhimLayout = new javax.swing.GroupLayout(pnDiaPhim);
        pnDiaPhim.setLayout(pnDiaPhimLayout);
        pnDiaPhimLayout.setHorizontalGroup(
            pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaPhimLayout.createSequentialGroup()
                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenDiaPhim)
                            .addComponent(txtDaoDien)
                            .addComponent(txtDienVIen)
                            .addComponent(namPhatHanhDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(theLoaiDiaPhim)
                            .addComponent(ngayNhapDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnDiaPhimLayout.createSequentialGroup()
                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                .addComponent(rdGiaBanSach1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(giaMuaDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(giaBanDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                                .addComponent(rdKhoangGiaBanDiaPhim)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(giaBanDiaPhimMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                                .addComponent(rdKhoangGiaMuaDiaPhim)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(giaMuaDiaPhimMin, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(giaMuaDiaPhimMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(giaBanDiaPhimMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                        .addComponent(rdKhoangSoLuongDiaPhim)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(soLuongDiaPhimMin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(soLuongDiaPhimMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnDiaPhimLayout.createSequentialGroup()
                                        .addComponent(jRadioButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(soLuongDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnDiaPhimLayout.setVerticalGroup(
            pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDiaPhimLayout.createSequentialGroup()
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(txtTenDiaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtDaoDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(txtDienVIen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(namPhatHanhDiaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(theLoaiDiaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(ngayNhapDiaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdKhoangGiaMuaDiaPhim))
                        .addComponent(giaMuaDiaPhimMin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(giaMuaDiaPhimMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdGiaBanSach1)
                    .addComponent(giaMuaDiaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(rdKhoangGiaBanDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(giaBanDiaPhimMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(giaBanDiaPhimMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(giaBanDiaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaPhimLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdKhoangSoLuongDiaPhim)
                            .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(soLuongDiaPhimMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(soLuongDiaPhimMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDiaPhimLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton3)
                    .addComponent(soLuongDiaPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnDiaPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnDiaNhac.setBackground(new java.awt.Color(51, 204, 255));

        rdKhoangGiaMuaDiaNhac.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup7.add(rdKhoangGiaMuaDiaNhac);

        giaMuaDiaNhacMin.setBackground(new java.awt.Color(51, 204, 255));

        giaMuaDiaNhacMax.setBackground(new java.awt.Color(51, 204, 255));

        lbl2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setText("-");

        jLabel46.setText("Tên đĩa nhạc:");

        rdGiaBanSach2.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup7.add(rdGiaBanSach2);

        giaMuaDiaNhac.setBackground(new java.awt.Color(51, 204, 255));

        jLabel47.setText("Ca sĩ:");

        jLabel49.setText("Nhà sản xuất");

        jLabel50.setText("Giá bán:");

        jLabel51.setText("Năm phát hành:");

        rdKhoangGiaBanDiaNhac.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup8.add(rdKhoangGiaBanDiaNhac);

        giaBanDiaNhacMin.setBackground(new java.awt.Color(51, 204, 255));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("-");

        namPhatHanhDiaNhac.setBackground(new java.awt.Color(51, 204, 255));

        giaBanDiaNhacMax.setBackground(new java.awt.Color(51, 204, 255));

        jLabel53.setText("Thể loại:");

        jLabel54.setText("Ngày nhập:");

        ngayNhapDiaNhac.setBackground(new java.awt.Color(51, 204, 255));

        jLabel55.setText("Giá mua:");

        jButton9.setBackground(new java.awt.Color(51, 204, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        jButton9.setText("Tìm kiếm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup8.add(jRadioButton2);

        giaBanDiaNhac.setBackground(new java.awt.Color(51, 204, 255));

        jLabel32.setText("Số lượng:");

        rdKhoanSoLuongDiaNhac.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup9.add(rdKhoanSoLuongDiaNhac);

        soLuongDiaNhacMin.setBackground(new java.awt.Color(51, 204, 255));

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("-");

        soLuongDiaNhacMax.setBackground(new java.awt.Color(51, 204, 255));

        jRadioButton5.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup9.add(jRadioButton5);

        soLuongDiaNhac.setBackground(new java.awt.Color(51, 204, 255));

        jButton11.setBackground(new java.awt.Color(51, 204, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton11.setText("Làm sạch");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDiaNhacLayout = new javax.swing.GroupLayout(pnDiaNhac);
        pnDiaNhac.setLayout(pnDiaNhacLayout);
        pnDiaNhacLayout.setHorizontalGroup(
            pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaNhacLayout.createSequentialGroup()
                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTenDiaNhac)
                            .addComponent(txtCaSi)
                            .addComponent(txtNhaSanXuatDiaNhac)
                            .addComponent(namPhatHanhDiaNhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTheLoaiDiaNhac)
                            .addComponent(ngayNhapDiaNhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnDiaNhacLayout.createSequentialGroup()
                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                .addComponent(rdGiaBanSach2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(giaMuaDiaNhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(giaBanDiaNhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                                .addComponent(rdKhoangGiaBanDiaNhac)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(giaBanDiaNhacMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                                .addComponent(rdKhoangGiaMuaDiaNhac)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(giaMuaDiaNhacMin, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(giaMuaDiaNhacMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(giaBanDiaNhacMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                        .addComponent(rdKhoanSoLuongDiaNhac)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(soLuongDiaNhacMin, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(soLuongDiaNhacMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnDiaNhacLayout.createSequentialGroup()
                                        .addComponent(jRadioButton5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(soLuongDiaNhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnDiaNhacLayout.setVerticalGroup(
            pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDiaNhacLayout.createSequentialGroup()
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(txtTenDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txtCaSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addComponent(txtNhaSanXuatDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(namPhatHanhDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtTheLoaiDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(ngayNhapDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(giaMuaDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnDiaNhacLayout.createSequentialGroup()
                        .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdKhoangGiaMuaDiaNhac))
                                .addComponent(giaMuaDiaNhacMin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(giaMuaDiaNhacMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdGiaBanSach2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(rdKhoangGiaBanDiaNhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(giaBanDiaNhacMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(giaBanDiaNhacMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(giaBanDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdKhoanSoLuongDiaNhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(soLuongDiaNhacMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(soLuongDiaNhacMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton5)
                    .addComponent(soLuongDiaNhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnDiaNhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton11))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnTatCa.setBackground(new java.awt.Color(51, 204, 255));

        jLabel56.setText("Tên sản phẩm:");

        jLabel57.setText("Năm sản xuất:");

        jDateChooser7.setBackground(new java.awt.Color(51, 204, 255));

        jLabel58.setText("Giá mua:");

        jLabel59.setText("Giá bán:");

        rdGiaMuaSach3.setBackground(new java.awt.Color(51, 204, 255));

        rdGiaBanSach3.setBackground(new java.awt.Color(51, 204, 255));

        jDateChooser8.setBackground(new java.awt.Color(51, 204, 255));

        jLabel60.setText("Ngày nhập:");

        giaMuaSachMin3.setBackground(new java.awt.Color(51, 204, 255));

        giaMuaSachMax3.setBackground(new java.awt.Color(51, 204, 255));

        giaBanSachMax3.setBackground(new java.awt.Color(51, 204, 255));

        giaBanSachMin3.setBackground(new java.awt.Color(51, 204, 255));

        soLuongSachMin3.setBackground(new java.awt.Color(51, 204, 255));

        soLuongSachMax3.setBackground(new java.awt.Color(51, 204, 255));

        soLuongSach3.setBackground(new java.awt.Color(51, 204, 255));

        jLabel61.setText("Số lượng");

        jButton10.setBackground(new java.awt.Color(51, 204, 255));
        jButton10.setText("Tìm kiếm");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTatCaLayout = new javax.swing.GroupLayout(pnTatCa);
        pnTatCa.setLayout(pnTatCaLayout);
        pnTatCaLayout.setHorizontalGroup(
            pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTatCaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTatCaLayout.createSequentialGroup()
                        .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnTatCaLayout.createSequentialGroup()
                                .addComponent(soLuongSach3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(soLuongSachMin3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnTatCaLayout.createSequentialGroup()
                                .addComponent(rdGiaMuaSach3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(giaMuaSachMin3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnTatCaLayout.createSequentialGroup()
                                .addComponent(rdGiaBanSach3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(giaBanSachMin3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(giaMuaSachMax3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(giaBanSachMax3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(soLuongSachMax3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnTatCaLayout.createSequentialGroup()
                        .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTatCaLayout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField9)
                            .addComponent(jDateChooser7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnTatCaLayout.setVerticalGroup(
            pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTatCaLayout.createSequentialGroup()
                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(giaBanSachMin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnTatCaLayout.createSequentialGroup()
                        .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdGiaMuaSach3))
                                .addComponent(giaMuaSachMin3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(giaMuaSachMax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTatCaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rdGiaBanSach3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnTatCaLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(giaBanSachMax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTatCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(soLuongSach3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(soLuongSachMin3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(soLuongSachMax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addGap(0, 190, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(pnSach, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pnDiaPhim, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pnDiaNhac, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pnTatCa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnDiaNhac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnTatCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnDiaPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnDiaNhac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnTatCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTimKiemTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbxTimKiemTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1))
        );

        jPanel8.setBackground(new java.awt.Color(51, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết quả:"));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel8.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel62.setText("Số lượng:");

        jButton3.setBackground(new java.awt.Color(51, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Add-16.png"))); // NOI18N
        jButton3.setText("Thêm vào giỏ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 204, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Delete-16.png"))); // NOI18N
        jButton4.setText("Xóa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(51, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng:"));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel9.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        soLuongMua.setBackground(new java.awt.Color(51, 204, 255));

        jLabel2.setText("Loại tìm kiếm:");

        cbxLoaiTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gần đúng", "Chính xác" }));
        cbxLoaiTimKiem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLoaiTimKiemItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(13, 13, 13)
                        .addComponent(cbxLoaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soLuongMua, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbxLoaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel62)
                                .addComponent(jButton3)
                                .addComponent(jButton4))
                            .addComponent(soLuongMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel3);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(95, 37));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/applications_system.png"))); // NOI18N
        jMenu1.setText("Hệ thống");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_user_1902268.png"))); // NOI18N
        jMenuItem1.setText("Quản lý khách hàng vip");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_info_button_36844.png"))); // NOI18N
        jMenuItem2.setText("Thông tin nhân viên");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_radial_arrows_1216559.png"))); // NOI18N
        jMenuItem4.setText("Đổi mật khẩu");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_exit_1736.png"))); // NOI18N
        jMenuItem3.setText("Đăng xuất");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxLoaiKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLoaiKhachHangItemStateChanged
        // TODO add your handling code here:
        index = cbxLoaiKhachHang.getSelectedIndex();
        switch (index) {
            case 0:
                hienThiGiaoDienKhachHangThuong();
                khMua = new KhachHang(lblMaKhachHangThuong.getText(), "Khách Hàng Thường");
                banHang.setKhachHangMua(khMua);
                break;
            case 1:
                hienThiGiaoDienKhachhangVip();
                break;
        }
    }//GEN-LAST:event_cbxLoaiKhachHangItemStateChanged

    private void cbxTimKiemTheoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTimKiemTheoItemStateChanged
        // TODO add your handling code here:
        indexTimKiem = cbxTimKiemTheo.getSelectedIndex();
        switch (indexTimKiem) {
            case 0:
                hienThiGiaoDienTimKiemSach();
                break;
            case 1:
                hienThiGiaoDienTimKiemDiaNhac();
                break;
            case 2:
                hienThiGiaoDienTimKiemDiaPhim();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cbxTimKiemTheoItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int[] rows = jTable2.getSelectedRows();
        if (rows.length <= 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm muốn thêm");
            return;
        }
        switch (indexTimKiem) {
            case 0:
                themSachVaoGio(rows);
                break;
            case 1:
                themDiaNhacVaoGio(rows);
                break;
            case 2:
                themDiaPhimVaoGio(rows);
                break;
        }
        hienThiBangGioHang(jTable1, banHang.getDanhSachSanPhamMua());
        hienThiBangGioHang(jTable3, banHang.getDanhSachSanPhamMua());
        hienThiThanhToan();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void hienThiThanhToan() {
        int tongTien = banHang.tinhTien();
        lblTongTien.setText(tongTien + " VND");
        int thanhTien;
        if (index == 0) {
            thanhTien = banHang.thanhTien(0);
        } else {
            thanhTien = banHang.thanhTien(chietKhauKhachVip);
        }
        lblThanhTien.setText(thanhTien + " VND");
    }

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int[] rows = jTable3.getSelectedRows();
        if (rows.length <= 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm muốn xóa");
            return;
        }
        switch (indexTimKiem) {
            case 0:
                xoaSachKhoiGio(rows);
                break;
            case 1:
                hienThiGiaoDienTimKiemDiaNhac();
                break;
            case 2:
                hienThiGiaoDienTimKiemDiaPhim();
                break;
            case 3:
                hienThiGiaoDienTimKiemSanPham();
                break;
        }
        hienThiBangGioHang(jTable1, banHang.getDanhSachSanPhamMua());
        hienThiBangGioHang(jTable3, banHang.getDanhSachSanPhamMua());
        hienThiThanhToan();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbxMaKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaKhachHangItemStateChanged
        // TODO add your handling code here:
        int index = cbxMaKhachHang.getSelectedIndex();
        if (index != -1 && index != cbxTenKhachHang.getSelectedIndex()) {
            cbxTenKhachHang.setSelectedIndex(index);
            KhachHang kh = (KhachHang) cbxTenKhachHang.getSelectedItem();
            lblGioiTinh.setText(kh.getGioiTinh());
            lblNamSinh.setText(sdf.format(kh.getNamSinh()));
            lblSoCMND.setText(kh.getSoCMND());
            lblSoDienThoai.setText(kh.getSoDienThoai());
            lblDiaChi.setText(kh.getDiaChi());
            khMua = new KhachHang(kh.getMaKhachHang(), kh.getTen(), kh.getGioiTinh(), kh.getNamSinh(), kh.getSoDienThoai(), kh.getSoCMND(), kh.getDiaChi());
            banHang.setKhachHangMua(khMua);
        }
    }//GEN-LAST:event_cbxMaKhachHangItemStateChanged

    private void cbxTenKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTenKhachHangItemStateChanged
        // TODO add your handling code here:
        int index = cbxTenKhachHang.getSelectedIndex();
        if (index != -1 && index != cbxMaKhachHang.getSelectedIndex()) {
            cbxMaKhachHang.setSelectedIndex(index);
            KhachHang kh = (KhachHang) cbxTenKhachHang.getSelectedItem();
            lblGioiTinh.setText(kh.getGioiTinh());
            lblNamSinh.setText(sdf.format(kh.getNamSinh()));
            lblSoCMND.setText(kh.getSoCMND());
            lblSoDienThoai.setText(kh.getSoDienThoai());
            lblDiaChi.setText(kh.getDiaChi());
            khMua = new KhachHang(kh.getMaKhachHang(), kh.getTen(), kh.getGioiTinh(), kh.getNamSinh(), kh.getSoDienThoai(), kh.getSoCMND(), kh.getDiaChi());
            banHang.setKhachHangMua(khMua);
        }
    }//GEN-LAST:event_cbxTenKhachHangItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int index = JOptionPane.showConfirmDialog(null, "Bạn muốn bán hàng ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (index == JOptionPane.YES_OPTION) {
            if (this.index == 1) {
                if (bllBanHang.themBanHang(banHang, true) > 0) {
                    JOptionPane.showMessageDialog(null, "Bán thành công");
                    if (chkInHoaDon.isSelected() == true) {
                        JFileChooser f = new JFileChooser();
                        if (f.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            System.out.println("Mở");
                            File file = f.getSelectedFile();
                            phieuMuaHang.inPhieuKhachHangVip(banHang, 5, file);
                        }
                    }
                    lamSachManHinhBanHang();
                }
            } else {
                if (bllBanHang.themBanHang(banHang, false) > 0) {
                    JOptionPane.showMessageDialog(null, "Bán thành công");
                    if (chkInHoaDon.isSelected() == true) {
                        JFileChooser f = new JFileChooser();
                        if (f.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            System.out.println("Mở");
                            File file = f.getSelectedFile();
                            phieuMuaHang.inPhieuKhachHangThuong(banHang, file);
                        }
                    }
                    lamSachManHinhBanHang();
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxLoaiTimKiemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLoaiTimKiemItemStateChanged
        // TODO add your handling code here:
        indexTimKiemTheo = cbxLoaiTimKiem.getSelectedIndex();
    }//GEN-LAST:event_cbxLoaiTimKiemItemStateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String tenDiaNhac = txtTenDiaNhac.getText();
        String caSi = txtCaSi.getText();
        String nhaSanXuat = txtNhaSanXuatDiaNhac.getText();
        Date namPhatHanh = namPhatHanhDiaNhac.getDate();
        String theLoai = theLoaiDiaPhim.getText();
        Date ngaynhap = ngayNhapDiaNhac.getDate();
        Integer giaMua = null, giaMuaMin = null, giaMuaMax = null;
        Integer giaBan = null, giaBanMin = null, giaBanMax = null;
        Integer soLuong = null, soLuongMin = null, soLuongMax = null;
        if (rdKhoangGiaMuaDiaNhac.isSelected()) {
            giaMuaMin = giaMuaDiaNhacMin.getValue();
            giaMuaMax = giaMuaDiaNhacMax.getValue();
        } else {
            if (giaMuaDiaNhac.getValue() > 0) {
                giaMua = giaMuaDiaNhac.getValue();
            }
        }
        if (rdKhoangGiaBanDiaNhac.isSelected()) {
            giaBanMin = giaBanDiaNhacMin.getValue();
            giaBanMax = giaBanDiaNhacMax.getValue();
        } else {
            if (giaBanDiaNhac.getValue() > 0) {
                giaBan = giaBanDiaNhac.getValue();
            }
        }
        if (rdKhoanSoLuongDiaNhac.isSelected() == true) {
            soLuongMin = soLuongDiaNhacMin.getValue();
            soLuongMax = soLuongDiaNhacMax.getValue();
        } else {
            if (soLuongDiaNhac.getValue() > 0) {
                soLuong = soLuongDiaNhac.getValue();
            }
        }
        boolean bGiaMua = rdKhoangGiaMuaDiaNhac.isSelected();
        boolean bGiaBan = rdKhoangGiaBanDiaNhac.isSelected();
        boolean bSoLuong = rdKhoanSoLuongDiaNhac.isSelected();
        boolean timGanDung = indexTimKiemTheo == 0 ? true : false;
        ArrayList<DiaNhac> ds = new ArrayList<>();
        if (bGiaMua == false && bGiaBan == false && bSoLuong == false) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, timGanDung);
        } else if (bGiaMua == true && bGiaBan == false && bSoLuong == false) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaMua", giaMuaMin, giaMuaMax, timGanDung);
        } else if (bGiaMua == false && bGiaBan == true && bSoLuong == false) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaBan", giaBanMin, giaBanMax, timGanDung);
        } else if (bGiaMua == false && bGiaBan == false && bSoLuong == true) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, "SoLuong", soLuongMin, soLuongMax, timGanDung);
        } else if (bGiaMua == true && bGiaBan == true && bSoLuong == false) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaMua", giaMuaMin, giaMuaMax, "GiaBan", giaBanMin, giaBanMax, timGanDung);
        } else if (bGiaMua == true && bGiaBan == false && bSoLuong == true) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaMua", giaMuaMin, giaMuaMax, "SoLuong", soLuongMin, soLuongMax, timGanDung);
        } else if (bGiaMua == false && bGiaBan == true && bSoLuong == true) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaBan", giaBanMin, giaBanMax, "SoLuong", soLuongMin, soLuongMax, timGanDung);
        } else if (bGiaMua == true && bGiaBan == true && bSoLuong == true) {
            ds = bllDiaNhac.timKiemDiaNhac(tenDiaNhac, caSi, nhaSanXuat, namPhatHanh, theLoai, ngaynhap, giaMua, giaBan, soLuong, giaMuaMin, giaMuaMax, giaBanMin, giaBanMax, soLuongMin, soLuongMax, timGanDung);
        }

        jTable2.setModel(hienThiDiaNhacLenBang(ds));
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String tenDiaPhim = txtTenDiaPhim.getText();
        String daoDien = txtDaoDien.getText();
        String dienVien = txtDienVIen.getText();
        Date namSanXuat = namPhatHanhDiaPhim.getDate();
        String theLoai = theLoaiDiaPhim.getText();
        Date ngaynhap = ngayNhapDiaPhim.getDate();
        Integer giaMua = null, giaMuaMin = null, giaMuaMax = null;
        Integer giaBan = null, giaBanMin = null, giaBanMax = null;
        Integer soLuong = null, soLuongMin = null, soLuongMax = null;
        if (rdKhoangGiaMuaDiaPhim.isSelected()) {
            giaMuaMin = giaMuaDiaPhimMin.getValue();
            giaMuaMax = giaMuaDiaPhimMax.getValue();
        } else {
            if (giaMuaDiaPhim.getValue() > 0) {
                giaMua = giaMuaDiaPhim.getValue();
            }
        }
        if (rdKhoanGiaBanSach.isSelected()) {
            giaBanMin = giaBanDiaPhimMin.getValue();
            giaBanMax = giaBanDiaPhimMax.getValue();
        } else {
            if (giaBanDiaPhim.getValue() > 0) {
                giaBan = giaBanDiaPhim.getValue();
            }
        }
        if (rdKhoangSoLuongDiaPhim.isSelected() == true) {
            soLuongMin = soLuongDiaPhimMin.getValue();
            soLuongMax = soLuongDiaPhimMax.getValue();
        } else {
            if (soLuongDiaPhim.getValue() > 0) {
                soLuong = soLuongDiaPhim.getValue();
            }
        }
        boolean bGiaMua = rdKhoangGiaMuaDiaPhim.isSelected();
        boolean bGiaBan = rdKhoangGiaBanDiaPhim.isSelected();
        boolean bSoLuong = rdKhoangSoLuongDiaPhim.isSelected();
        boolean timGanDung = indexTimKiemTheo == 0 ? true : false;
        ArrayList<DiaPhim> ds = new ArrayList<>();
        if (bGiaMua == false && bGiaBan == false && bSoLuong == false) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, timGanDung);
        } else if (bGiaMua == true && bGiaBan == false && bSoLuong == false) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaMua", giaMuaMin, giaMuaMax, timGanDung);
        } else if (bGiaMua == false && bGiaBan == true && bSoLuong == false) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaBan", giaBanMin, giaBanMax, timGanDung);
        } else if (bGiaMua == false && bGiaBan == false && bSoLuong == true) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, "SoLuong", soLuongMin, soLuongMax, timGanDung);
        } else if (bGiaMua == true && bGiaBan == true && bSoLuong == false) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaMua", giaMuaMin, giaMuaMax, "GiaBan", giaBanMin, giaBanMax, timGanDung);
        } else if (bGiaMua == true && bGiaBan == false && bSoLuong == true) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaMua", giaMuaMin, giaMuaMax, "SoLuong", soLuongMin, soLuongMax, timGanDung);
        } else if (bGiaMua == false && bGiaBan == true && bSoLuong == true) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, "GiaBan", giaBanMin, giaBanMax, "SoLuong", soLuongMin, soLuongMax, timGanDung);
        } else if (bGiaMua == true && bGiaBan == true && bSoLuong == true) {
            ds = bllDiaPhim.timKiemDiaPhim(tenDiaPhim, daoDien, dienVien, namSanXuat, theLoai, ngaynhap, giaMua, giaBan, soLuong, giaMuaMin, giaMuaMax, giaBanMin, giaBanMax, soLuongMin, soLuongMax, timGanDung);
        }

        jTable2.setModel(hienThiDiaPhimLenBangLenBang(ds));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String tenSach = txtTenSach.getText();
        String tacGia = txtTacGia.getText();
        String nhaXuatBan = txtNhaXuatBan.getText();
        Date namXuatBan = jDateChooserNamXuatBan.getDate();
        String theLoai = txtTheLoai.getText();
        Date ngayNhap = jDateChooserNgayNhapSach.getDate();
        Integer giaMua = null, giaMuaMin = null, giaMuaMax = null, giaBan = null, giaBanMin = null, giaBanMax = null,
                soLuong = null, soLuongMin = null, soLuongMax = null;
        if (rdGiaMuaSachChinhXac.isSelected() == true) {
            int mua = jSpinFieldGiaMua.getValue();
            if (mua > 0) {
                giaMua = mua;
            }
        } else {
            giaMuaMin = giaMuaSachMin.getValue();
            giaMuaMax = giaMuaSachMax.getValue();
        }
        if (rdGiaBanSachChinhXac.isSelected() == true) {
            int ban = jSpinFieldGiaBanSach.getValue();
            if (ban > 0) {
                giaBan = ban;
            }
        } else {
            giaBanMin = jSpinFieldGiaBanSachMin.getValue();
            giaBanMax = jSpinFieldGiaBanSachMax.getValue();
        }
        if (rdSoLuongSachChinhXac.isSelected() == true) {
            int sl = jSpinFieldSoLuongSach.getValue();
            if (sl > 0) {
                soLuong = sl;
            }
        } else {
            soLuongMin = jSpinFieldSoLuongSachMin.getValue();
            soLuongMax = jSpinFieldSoLuongSachMax.getValue();
        }
        ArrayList<Sach> ketQua = new ArrayList<>();
        boolean khoangGiaMua = rdKhoangGiaMuaSach.isSelected();
        boolean khoanGiaBan = rdKhoanGiaBanSach.isSelected();
        boolean khoangSoLuong = rdKhoangSoLuongSach.isSelected();
        boolean timGanDung = indexTimKiemTheo == 0 ? true : false;
        if (khoangGiaMua == false && khoanGiaBan == false && khoangSoLuong == false) {
            ketQua = bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, timGanDung);
        } else if (khoangGiaMua == true && khoanGiaBan == false && khoangSoLuong == false) {
            ketQua = bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, timGanDung, "GiaMua", giaMuaMin, giaMuaMax);
        } else if (khoangGiaMua == true && khoanGiaBan == true && khoangSoLuong == false) {
            ketQua = bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, timGanDung, "GiaMua", giaMuaMin, giaMuaMax, "GiaBan", giaBanMin, giaBanMax);
        } else if (khoangGiaMua == false && khoanGiaBan == true && khoangSoLuong == true) {
            ketQua = bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, timGanDung, "GiaBan", giaBanMin, giaBanMax, "SoLuong", soLuongMin, soLuongMax);
        } else if (khoangGiaMua == true && khoanGiaBan == false && khoangSoLuong == true) {
            ketQua = bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, timGanDung, "GiaMua", giaMuaMin, giaMuaMax, "SoLuong", soLuongMin, soLuongMax);
        } else if (khoangGiaMua == false && khoanGiaBan == true && khoangSoLuong == false) {
            ketQua = bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, timGanDung, "GiaBan", giaBanMin, giaBanMax);
        } else if (khoangGiaMua == false && khoanGiaBan == false && khoangSoLuong == true) {
            ketQua = bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, timGanDung, "SoLuong", soLuongMin, soLuongMax);
        } else if (khoangGiaMua == true && khoanGiaBan == true && khoangSoLuong == true) {
            ketQua = bllSach.timKiemSach(timGanDung, tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMuaMin, giaMuaMax, giaBanMin, giaBanMax, soLuongMin, soLuongMax);
        }
        jTable2.setModel(hienThiSachLenBang(ketQua));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        ThongTinNhanVien ui = new ThongTinNhanVien();
        ui.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        QuanLyKhachHangVIP ui = new QuanLyKhachHangVIP();
        ui.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            this.dispose();
            Login ui = new Login();
            ui.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        lamSachDiaNhac();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        lamSachDiaPhim();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        lamSachSach();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tienKhachTraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tienKhachTraPropertyChange
        // TODO add your handling code here:
        try {
            String tongTien = lblThanhTien.getText();
            if (!tongTien.equals("")) {
                tongTien = tongTien.substring(0, tongTien.length() - 4);
                int thanhtien = Integer.parseInt(tongTien);
                int khachTra = tienKhachTra.getValue();
                int tienTraLai = thanhtien - khachTra;
                lblTienThua.setText(tienTraLai + " VND");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tienKhachTraPropertyChange

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        ThayDoiMatKhau ui = new ThayDoiMatKhau();
        ui.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void lamSachDiaNhac() {
        txtTenDiaNhac.setText("");
        txtCaSi.setText("");
        txtNhaSanXuatDiaNhac.setText("");
        namPhatHanhDiaNhac.setDate(null);
        txtTheLoaiDiaNhac.setText("");
        giaMuaDiaNhac.setValue(0);
        giaMuaDiaNhacMin.setValue(0);
        giaMuaDiaNhacMax.setValue(0);
        giaBanDiaNhac.setValue(0);
        giaBanDiaNhacMin.setValue(0);
        giaBanDiaNhacMax.setValue(0);
        soLuongDiaNhac.setValue(0);
        soLuongDiaNhacMin.setValue(0);
        soLuongDiaNhacMax.setValue(0);
        ngayNhapDiaNhac.setDate(null);
    }

    private void lamSachDiaPhim() {
        txtTenDiaPhim.setText("");
        txtDaoDien.setText("");
        txtDienVIen.setText("");
        namPhatHanhDiaPhim.setDate(null);
        theLoaiDiaPhim.setText("");
        giaMuaDiaPhim.setValue(0);
        giaMuaDiaPhimMin.setValue(0);
        giaMuaDiaPhimMax.setValue(0);
        giaBanDiaPhim.setValue(0);
        giaBanDiaPhim.setValue(0);
        giaBanDiaPhimMax.setValue(0);
        soLuongDiaPhim.setValue(0);
        soLuongDiaPhimMin.setValue(0);
        soLuongDiaPhimMax.setValue(0);
        ngayNhapDiaPhim.setDate(null);
    }

    private void lamSachSach() {
        txtTenSach.setText("");
        txtTacGia.setText("");
        txtNhaXuatBan.setText("");
        jDateChooserNamXuatBan.setDate(null);
        txtTheLoai.setText("");
        jDateChooserNgayNhapSach.setDate(null);
        giaMuaSachMin.setValue(0);
        giaMuaSachMax.setValue(0);
        jSpinFieldGiaBanSachMin.setValue(0);
        jSpinFieldGiaMua.setValue(0);
        jSpinFieldGiaBanSachMax.setValue(0);
        jSpinFieldGiaBanSach.setValue(0);
        jSpinFieldSoLuongSachMin.setValue(0);
        jSpinFieldSoLuongSachMax.setValue(0);
        jSpinFieldSoLuongSach.setValue(0);
    }

    private void xoaSachKhoiGio(int[] rows) {
        for (int i : rows) {
            Sach s = new Sach();
            s.setTenSanPham(jTable3.getValueAt(i, 0) + "");
            s.setSoLuong(soLuongMua.getValue());
            banHang.xoaSanPhamKhoiDanhSach(s, s.getSoLuong());
        }
        hienThiBangGioHang(jTable1, banHang.getDanhSachSanPhamMua());
        hienThiBangGioHang(jTable3, banHang.getDanhSachSanPhamMua());
        hienThiThanhToan();
    }

    private void themSachVaoGio(int[] arr) {
        for (int i : arr) {
            try {
                Sach s = new Sach();
                s.setTenSanPham(jTable2.getValueAt(i, 0) + "");
                s.setTacGia(jTable2.getValueAt(i, 1) + "");
                s.setNhaXuatBan(jTable2.getValueAt(i, 2) + "");
                s.setNamSanXuat(sdf.parse(jTable2.getValueAt(i, 3) + ""));
                s.setTheLoai(jTable2.getValueAt(i, 4) + "");
                s.setNgayNhap(sdf.parse(jTable2.getValueAt(i, 5) + ""));
                String strGiaMua = jTable2.getValueAt(i, 6) + "";
                strGiaMua = strGiaMua.substring(0, strGiaMua.length() - 4);
                s.setGiaMua(Integer.parseInt(strGiaMua));
                String strGiaBan = jTable2.getValueAt(i, 7) + "";
                strGiaBan = strGiaBan.substring(0, strGiaBan.length() - 4);
                s.setGiaBan(Integer.parseInt(strGiaBan));
                String strSoLuong = jTable2.getValueAt(i, 8) + "";
                s.setSoLuong(soLuongMua.getValue());
                banHang.themSanPhamVaoDanhSach(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void themDiaNhacVaoGio(int[] arr) {
        for (int i : arr) {
            try {
                DiaNhac d = new DiaNhac();
                d.setTenSanPham(jTable2.getValueAt(i, 0) + "");
                d.setCaSi(jTable2.getValueAt(i, 1) + "");
                d.setNhaSanXuat(jTable2.getValueAt(i, 2) + "");
                d.setNamSanXuat(sdf.parse(jTable2.getValueAt(i, 3) + ""));
                d.setTheLoai(jTable2.getValueAt(i, 4) + "");
                d.setNgayNhap(sdf.parse(jTable2.getValueAt(i, 5) + ""));
                String strGiaMua = jTable2.getValueAt(i, 6) + "";
                strGiaMua = strGiaMua.substring(0, strGiaMua.length() - 4);
                d.setGiaMua(Integer.parseInt(strGiaMua));
                String strGiaBan = jTable2.getValueAt(i, 7) + "";
                strGiaBan = strGiaBan.substring(0, strGiaBan.length() - 4);
                d.setGiaBan(Integer.parseInt(strGiaBan));
                String strSoLuong = jTable2.getValueAt(i, 8) + "";
                d.setSoLuong(soLuongMua.getValue());
                banHang.themSanPhamVaoDanhSach(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void themDiaPhimVaoGio(int[] arr) {
        for (int i : arr) {
            try {
                DiaPhim d = new DiaPhim();
                d.setTenSanPham(jTable2.getValueAt(i, 0) + "");
                d.setDaoDien(jTable2.getValueAt(i, 1) + "");
                d.setDienVien(jTable2.getValueAt(i, 2) + "");
                d.setNamSanXuat(sdf.parse(jTable2.getValueAt(i, 3) + ""));
                d.setTheLoai(jTable2.getValueAt(i, 4) + "");
                d.setNgayNhap(sdf.parse(jTable2.getValueAt(i, 5) + ""));
                String strGiaMua = jTable2.getValueAt(i, 6) + "";
                strGiaMua = strGiaMua.substring(0, strGiaMua.length() - 4);
                d.setGiaMua(Integer.parseInt(strGiaMua));
                String strGiaBan = jTable2.getValueAt(i, 7) + "";
                strGiaBan = strGiaBan.substring(0, strGiaBan.length() - 4);
                d.setGiaBan(Integer.parseInt(strGiaBan));
                String strSoLuong = jTable2.getValueAt(i, 8) + "";
                d.setSoLuong(soLuongMua.getValue());
                banHang.themSanPhamVaoDanhSach(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private DefaultTableModel hienThiSachLenBang(ArrayList<Sach> ds) {
        DefaultTableModel dtmSach = new DefaultTableModel();
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Tên Sách");
        dtmSach.addColumn("Tác Giả");
        dtmSach.addColumn("Nhà Xuất Bản");
        dtmSach.addColumn("Năm Xuất Bản");
        dtmSach.addColumn("Thể Loại");
        dtmSach.addColumn("Ngày Nhập");
        dtmSach.addColumn("Giá Mua");
        dtmSach.addColumn("Giá Bán");
        dtmSach.addColumn("Số Lượng");
        if (ds != null) {
            for (Sach s : ds) {
                Vector<String> v = new Vector<>();
                v.add(s.getTenSanPham());
                v.add(s.getTacGia());
                v.add(s.getNhaXuatBan());
                v.add(sdf.format(s.getNamSanXuat()));
                v.add(s.getTheLoai());
                v.add(sdf.format(s.getNgayNhap()));
                v.add(s.getGiaMua() + " VND");
                v.add(s.getGiaBan() + " VND");
                v.add(s.getSoLuong() + "");
                dtmSach.addRow(v);
            }
        }
        return dtmSach;
    }

    private DefaultTableModel hienThiDiaPhimLenBangLenBang(ArrayList<DiaPhim> ds) {
        DefaultTableModel dtmDiaPhim = new DefaultTableModel();
        dtmDiaPhim = new DefaultTableModel();
        dtmDiaPhim.addColumn("Tên Đĩa Phim");
        dtmDiaPhim.addColumn("Đạo Diễn");
        dtmDiaPhim.addColumn("Diễn Viên");
        dtmDiaPhim.addColumn("Năm Xuất Bản");
        dtmDiaPhim.addColumn("Thể Loại");
        dtmDiaPhim.addColumn("Ngày Nhập");
        dtmDiaPhim.addColumn("Giá Mua");
        dtmDiaPhim.addColumn("Giá Bán");
        dtmDiaPhim.addColumn("Số Lượng");
        if (ds != null) {
            for (DiaPhim d : ds) {
                Vector<String> v = new Vector<>();
                v.add(d.getTenSanPham());
                v.add(d.getDaoDien());
                v.add(d.getDienVien());
                v.add(sdf.format(d.getNamSanXuat()));
                v.add(d.getTheLoai());
                v.add(sdf.format(d.getNgayNhap()));
                v.add(d.getGiaMua() + " VND");
                v.add(d.getGiaBan() + " VND");
                v.add(d.getSoLuong() + "");
                dtmDiaPhim.addRow(v);
            }
        }
        return dtmDiaPhim;
    }

    private DefaultTableModel hienThiDiaNhacLenBang(ArrayList<DiaNhac> ds) {
        DefaultTableModel dtmDiaNhac = new DefaultTableModel();
        dtmDiaNhac.addColumn("Tên Đĩa Nhạc");
        dtmDiaNhac.addColumn("Ca Sĩ");
        dtmDiaNhac.addColumn("Nhà Sản Xuất");
        dtmDiaNhac.addColumn("Năm Xuất Bản");
        dtmDiaNhac.addColumn("Thể Loại");
        dtmDiaNhac.addColumn("Ngày Nhập");
        dtmDiaNhac.addColumn("Giá Mua");
        dtmDiaNhac.addColumn("Giá Bán");
        dtmDiaNhac.addColumn("Số Lượng");
        if (ds != null) {
            for (DiaNhac d : ds) {
                Vector<String> v = new Vector<>();
                v.add(d.getTenSanPham());
                v.add(d.getCaSi());
                v.add(d.getNhaSanXuat());
                v.add(sdf.format(d.getNamSanXuat()));
                v.add(d.getTheLoai());
                v.add(sdf.format(d.getNgayNhap()));
                v.add(d.getGiaMua() + " VND");
                v.add(d.getGiaBan() + " VND");
                v.add(d.getSoLuong() + "");
                dtmDiaNhac.addRow(v);
            }
        }
        return dtmDiaNhac;

    }

    private DefaultTableModel hienThiSanPhamLenBang(ArrayList<SanPham> ds) {
        DefaultTableModel dtmSanPham = new DefaultTableModel();
        dtmSanPham = new DefaultTableModel();
        dtmSanPham.addColumn("Tên Sản Phẩm");
        dtmSanPham.addColumn("Năm Sản Xuất");
        dtmSanPham.addColumn("Thể Loại");
        dtmSanPham.addColumn("Ngày Nhập");
        dtmSanPham.addColumn("Giá Mua");
        dtmSanPham.addColumn("Giá Bán");
        dtmSanPham.addColumn("Số Lượng");
        if (ds != null) {
            for (SanPham sp : ds) {
                Vector<String> v = new Vector<>();
                v.add(sp.getTenSanPham());
                v.add(sdf.format(sp.getNamSanXuat()));
                v.add(sdf.format(sp.getNgayNhap()));
                v.add(sp.getGiaMua() + " VND");
                v.add(sp.getGiaBan() + " VND");
                v.add(sp.getSoLuong() + "");
                dtmSanPham.addRow(v);
            }
        }
        return dtmSanPham;
    }

    private void hienThiGiaoDienTimKiemSach() {
        pnSach.setVisible(true);
        pnDiaNhac.setVisible(false);
        pnDiaPhim.setVisible(false);
        pnTatCa.setVisible(false);
        rdKhoangGiaMuaSach.setSelected(true);
        rdKhoanGiaBanSach.setSelected(true);
        rdKhoangSoLuongSach.setSelected(true);
        jTable2.setModel(hienThiSachLenBang(bllSach.layToanBoSach()));
    }

    private void hienThiGiaoDienTimKiemDiaNhac() {
        pnSach.setVisible(false);
        pnDiaNhac.setVisible(true);
        pnDiaPhim.setVisible(false);
        pnTatCa.setVisible(false);
        namPhatHanhDiaNhac.setDate(null);
        rdKhoangGiaMuaDiaNhac.setSelected(true);
        rdKhoangGiaBanDiaNhac.setSelected(true);
        rdKhoanSoLuongDiaNhac.setSelected(true);
        jTable2.setModel(hienThiDiaNhacLenBang(bllDiaNhac.layToanBoDiaNhac()));
    }

    private void hienThiGiaoDienTimKiemDiaPhim() {
        pnSach.setVisible(false);
        pnDiaNhac.setVisible(false);
        pnDiaPhim.setVisible(true);
        pnTatCa.setVisible(false);
        namPhatHanhDiaPhim.setDate(null);
        ngayNhapDiaPhim.setDate(null);
        rdKhoangGiaMuaDiaPhim.setSelected(true);
        rdKhoangGiaBanDiaPhim.setSelected(true);
        rdKhoangSoLuongDiaPhim.setSelected(true);
        jTable2.setModel(hienThiDiaPhimLenBangLenBang(bllDiaPhim.layToanBoDiaPhim()));
    }

    private void hienThiGiaoDienTimKiemSanPham() {
        pnSach.setVisible(false);
        pnDiaNhac.setVisible(false);
        pnDiaPhim.setVisible(false);
        pnTatCa.setVisible(true);

        jTable2.setModel(hienThiSanPhamLenBang(null));
    }

    private void hienThiGiaoDienKhachHangThuong() {
        pnKhachHangThuong.setVisible(true);
        pnKhachHangVip.setVisible(false);
        lblChietKhau.setText("0%");
    }

    private void hienThiGiaoDienKhachhangVip() {
        pnKhachHangVip.setVisible(true);
        pnKhachHangThuong.setVisible(false);
        lblChietKhau.setText("5%");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JComboBox<String> cbxLoaiKhachHang;
    private javax.swing.JComboBox<String> cbxLoaiTimKiem;
    private javax.swing.JComboBox<String> cbxMaKhachHang;
    private javax.swing.JComboBox<Object> cbxTenKhachHang;
    private javax.swing.JComboBox<String> cbxTimKiemTheo;
    private javax.swing.JCheckBox chkInHoaDon;
    private com.toedter.components.JSpinField giaBanDiaNhac;
    private com.toedter.components.JSpinField giaBanDiaNhacMax;
    private com.toedter.components.JSpinField giaBanDiaNhacMin;
    private com.toedter.components.JSpinField giaBanDiaPhim;
    private com.toedter.components.JSpinField giaBanDiaPhimMax;
    private com.toedter.components.JSpinField giaBanDiaPhimMin;
    private com.toedter.components.JSpinField giaBanSachMax3;
    private com.toedter.components.JSpinField giaBanSachMin3;
    private com.toedter.components.JSpinField giaMuaDiaNhac;
    private com.toedter.components.JSpinField giaMuaDiaNhacMax;
    private com.toedter.components.JSpinField giaMuaDiaNhacMin;
    private com.toedter.components.JSpinField giaMuaDiaPhim;
    private com.toedter.components.JSpinField giaMuaDiaPhimMax;
    private com.toedter.components.JSpinField giaMuaDiaPhimMin;
    private com.toedter.components.JSpinField giaMuaSachMax;
    private com.toedter.components.JSpinField giaMuaSachMax3;
    private com.toedter.components.JSpinField giaMuaSachMin;
    private com.toedter.components.JSpinField giaMuaSachMin3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private com.toedter.calendar.JDateChooser jDateChooserNamXuatBan;
    private com.toedter.calendar.JDateChooser jDateChooserNgayNhapSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.components.JSpinField jSpinFieldGiaBanSach;
    private com.toedter.components.JSpinField jSpinFieldGiaBanSachMax;
    private com.toedter.components.JSpinField jSpinFieldGiaBanSachMin;
    private com.toedter.components.JSpinField jSpinFieldGiaMua;
    private com.toedter.components.JSpinField jSpinFieldSoLuongSach;
    private com.toedter.components.JSpinField jSpinFieldSoLuongSachMax;
    private com.toedter.components.JSpinField jSpinFieldSoLuongSachMin;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lblChietKhau;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaBanHang;
    private javax.swing.JLabel lblMaKhachHangThuong;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNamSinh;
    private javax.swing.JLabel lblNgayBan;
    private javax.swing.JLabel lblSoCMND;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private com.toedter.calendar.JDateChooser namPhatHanhDiaNhac;
    private com.toedter.calendar.JDateChooser namPhatHanhDiaPhim;
    private com.toedter.calendar.JDateChooser ngayNhapDiaNhac;
    private com.toedter.calendar.JDateChooser ngayNhapDiaPhim;
    private javax.swing.JPanel pnDiaNhac;
    private javax.swing.JPanel pnDiaPhim;
    private javax.swing.JPanel pnKhachHangThuong;
    private javax.swing.JPanel pnKhachHangVip;
    private javax.swing.JPanel pnSach;
    private javax.swing.JPanel pnTatCa;
    private javax.swing.JRadioButton rdGiaBanSach1;
    private javax.swing.JRadioButton rdGiaBanSach2;
    private javax.swing.JRadioButton rdGiaBanSach3;
    private javax.swing.JRadioButton rdGiaBanSachChinhXac;
    private javax.swing.JRadioButton rdGiaMuaSach3;
    private javax.swing.JRadioButton rdGiaMuaSachChinhXac;
    private javax.swing.JRadioButton rdKhoanGiaBanSach;
    private javax.swing.JRadioButton rdKhoanSoLuongDiaNhac;
    private javax.swing.JRadioButton rdKhoangGiaBanDiaNhac;
    private javax.swing.JRadioButton rdKhoangGiaBanDiaPhim;
    private javax.swing.JRadioButton rdKhoangGiaMuaDiaNhac;
    private javax.swing.JRadioButton rdKhoangGiaMuaDiaPhim;
    private javax.swing.JRadioButton rdKhoangGiaMuaSach;
    private javax.swing.JRadioButton rdKhoangSoLuongDiaPhim;
    private javax.swing.JRadioButton rdKhoangSoLuongSach;
    private javax.swing.JRadioButton rdSoLuongSachChinhXac;
    private com.toedter.components.JSpinField soLuongDiaNhac;
    private com.toedter.components.JSpinField soLuongDiaNhacMax;
    private com.toedter.components.JSpinField soLuongDiaNhacMin;
    private com.toedter.components.JSpinField soLuongDiaPhim;
    private com.toedter.components.JSpinField soLuongDiaPhimMax;
    private com.toedter.components.JSpinField soLuongDiaPhimMin;
    private com.toedter.components.JSpinField soLuongMua;
    private javax.swing.JRadioButton soLuongSach3;
    private com.toedter.components.JSpinField soLuongSachMax3;
    private com.toedter.components.JSpinField soLuongSachMin3;
    private javax.swing.JTextField theLoaiDiaPhim;
    private com.toedter.components.JSpinField tienKhachTra;
    private javax.swing.JTextField txtCaSi;
    private javax.swing.JTextField txtDaoDien;
    private javax.swing.JTextField txtDienVIen;
    private javax.swing.JTextField txtNhaSanXuatDiaNhac;
    private javax.swing.JTextField txtNhaXuatBan;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenDiaNhac;
    private javax.swing.JTextField txtTenDiaPhim;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTheLoai;
    private javax.swing.JTextField txtTheLoaiDiaNhac;
    // End of variables declaration//GEN-END:variables

    private void lamSachManHinhBanHang() {
        lblTongTien.setText("0 VND");
        lblChietKhau.setText("0 %");
        lblThanhTien.setText("0 VND");
        lblTienThua.setText("0 VND");
        tienKhachTra.setValue(0);
        cbxLoaiKhachHang.setSelectedIndex(0);
        pnKhachHangThuong.setVisible(true);
        pnKhachHangVip.setVisible(false);
        cbxMaKhachHang.setSelectedIndex(-1);
        cbxTenKhachHang.setSelectedIndex(-1);
        dtmGioHang.setRowCount(0);

        String maBanHang = "MBH" + taoMa.taoChuoiTuSoDaCho(taoMa.laySoCuoiCuaBangBanHang());
        lblMaBanHang.setText(maBanHang);
        banHang.setMaBanHang(maBanHang);

        banHang.setDanhSachSanPhamMua(new ArrayList<>());

        String maKhachHang = "KHT" + taoMa.taoChuoiTuSoDaCho(taoMa.laySoCuoiCuaBangKhachHangThuong());
        lblMaKhachHangThuong.setText(maKhachHang);
        khMua = new KhachHang(maKhachHang, "");
        banHang.setKhachHangMua(khMua);
        jTable1.setModel(dtmGioHang);
        jTable3.setModel(dtmGioHang);
    }
}
