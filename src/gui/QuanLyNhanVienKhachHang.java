/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controler.BLLKhachHang;
import controler.BLLNhanVien;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import object.KhachHang;
import object.NhanVien;
import object.NhanVienChinhThuc;
import object.NhanVienThoiVu;
import object.TaoMa;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QuanLyNhanVienKhachHang extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyNhanVienKhachHang
     */
    private int index;
    private BLLNhanVien bllNhanVien;
    private BLLKhachHang bllKhachHang;
    private DefaultTableModel dtmNhanVien;
    private DefaultTableModel dtmKhachHang;
    private DefaultTableModel dtmLuongNhanVien;
    private SimpleDateFormat sdf;
    private TaoMa taoMa;
    private int sttCuoi;
    private String maTiepTheo;

    public QuanLyNhanVienKhachHang() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pnNhanVien.setVisible(true);
        pnKhachHang.setVisible(false);
        pnLuongNhanVien.setVisible(false);
        khoiTaoDoiTuongVaGan();

        NhanVienChinhThuc.tienLamMotNgay = bllNhanVien.tienLamMotNgay("CT");
        NhanVienChinhThuc.tienLamThemMotNgay = bllNhanVien.tienThemLamMotNgay("CT");
        NhanVienThoiVu.tienLamMotNgay = bllNhanVien.tienLamMotNgay("TV");
        NhanVienThoiVu.tienLamThemMotNgay = bllNhanVien.tienThemLamMotNgay("TV");

        hienThiNhanVienLenBang(bllNhanVien.layToanBoNhanVien());
        lamSachDuLieuNhanVien();
        hienThiMaNhanVienTiepTheo();
    }

    private void khoiTaoDoiTuongVaGan() {
        index = 1;
        bllNhanVien = new BLLNhanVien();
        bllKhachHang = new BLLKhachHang();
        taoMa = new TaoMa();

        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã Nhân Viên");
        dtmNhanVien.addColumn("Tên Nhân Viên");
        dtmNhanVien.addColumn("Giới Tính");
        dtmNhanVien.addColumn("Năm Sinh");
        dtmNhanVien.addColumn("Loại Nhân Viên");
        dtmNhanVien.addColumn("Số Điện Thoại");
        dtmNhanVien.addColumn("Số CMND");
        dtmNhanVien.addColumn("Địa Chỉ");

        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Mã Khách Hàng");
        dtmKhachHang.addColumn("Tên Khách Hàng");
        dtmKhachHang.addColumn("Giới Tính");
        dtmKhachHang.addColumn("Năm Sinh");
        dtmKhachHang.addColumn("Số Điện Thoại");
        dtmKhachHang.addColumn("Số CMND");
        dtmKhachHang.addColumn("Địa Chỉ");

        dtmLuongNhanVien = new DefaultTableModel();
        dtmLuongNhanVien.addColumn("Tháng");
        dtmLuongNhanVien.addColumn("Mã Nhân Viên");
        dtmLuongNhanVien.addColumn("Tên Nhân Viên");
        dtmLuongNhanVien.addColumn("Số Ngày Làm");
        dtmLuongNhanVien.addColumn("Số Ngày Làm Thêm");
        dtmLuongNhanVien.addColumn("Lương Tháng");
        dtmLuongNhanVien.addColumn("Ngày Trả Lương");

        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    private void hienThiNhanVienLenBang(ArrayList<NhanVien> ds) {
        dtmNhanVien.setRowCount(0);
        for (NhanVien nv : ds) {
            Vector<String> v = new Vector<>();
            v.add(nv.getMaNhanVien());
            v.add(nv.getTen());
            v.add(nv.getGioiTinh());
            if (nv.getNamSinh() == null) {
                v.add("");
            } else {
                v.add(sdf.format(nv.getNamSinh()));
            }
            if (nv instanceof NhanVienChinhThuc) {
                v.add("Chính Thức");
            } else {
                v.add("Thời Vụ");
            }
            v.add(nv.getSoDienThoai());
            v.add(nv.getSoCMND());
            v.add(nv.getDiaChi());
            dtmNhanVien.addRow(v);
        }
        tblDanhSach.setModel(dtmNhanVien);
    }

    private void hienThiKhachHangLenBang(ArrayList<KhachHang> ds) {
        dtmKhachHang.setRowCount(0);
        for (KhachHang kh : ds) {
            Vector<String> v = new Vector<>();
            v.add(kh.getMaKhachHang());
            v.add(kh.getTen());
            v.add(kh.getGioiTinh());
            if (kh.getNamSinh() == null) {
                v.add("");
            } else {
                v.add(sdf.format(kh.getNamSinh()));
            }
            v.add(kh.getSoDienThoai());
            v.add(kh.getSoCMND());
            v.add(kh.getDiaChi());
            dtmKhachHang.addRow(v);
        }
        tblDanhSach.setModel(dtmKhachHang);
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
        jCheckBox1 = new javax.swing.JCheckBox();
        jSpinField2 = new com.toedter.components.JSpinField();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        pnNhanVien = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMaNhanVienTiepTheo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdNhanVienNam = new javax.swing.JRadioButton();
        rdNhanVienNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        cbxLoaiNhanVien = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtSoCMNDNhanVien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSoDTNhanVien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChiNhanVien = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserNamSinhNhanVien = new com.toedter.calendar.JDateChooser();
        btnTimKiemNhanVien = new javax.swing.JButton();
        btnThemNhanVien = new javax.swing.JButton();
        btnThemNhanVienTuFile = new javax.swing.JButton();
        btnSuaNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        btnXemToanBoNhanVien = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        chkSuDUngMaNhanVienTiepTheo = new javax.swing.JCheckBox();
        pnKhachHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblMaKhachHangTiepTheo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        rdKhachHangNam = new javax.swing.JRadioButton();
        rdKhachHangNu = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        txtSoCMNDKhachHang = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSoDienThoaiKhachHang = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDiaChiKhachHang = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jDateChooserNamSinhKhachHang = new com.toedter.calendar.JDateChooser();
        btnTimKiemKhachHang = new javax.swing.JButton();
        btnThemKhachHang = new javax.swing.JButton();
        ThemKhachHangTuFile = new javax.swing.JButton();
        btnSuaKhachHang = new javax.swing.JButton();
        btnXoaKhachHang = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        btnXemToanBoKhachHang = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        chkSuDungMaKhachHangTiepTheo = new javax.swing.JCheckBox();
        pnLuongNhanVien = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbxMaNhanVien = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSpinField1 = new com.toedter.components.JSpinField();
        jLabel24 = new javax.swing.JLabel();
        jSpinField3 = new com.toedter.components.JSpinField();
        jLabel25 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cbxTenNhanVien = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 680));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        pnNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        pnNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhân Viên:"));

        jLabel1.setText("Mã Nhân Viên:");

        jLabel3.setText("Mã Tiếp Theo:");

        lblMaNhanVienTiepTheo.setText("jLabel4");

        jLabel5.setText("Tên Nhân Viên:");

        txtTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNhanVienActionPerformed(evt);
            }
        });

        jLabel6.setText("Giới Tính:");

        rdNhanVienNam.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(rdNhanVienNam);
        rdNhanVienNam.setText("Nam");

        rdNhanVienNu.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(rdNhanVienNu);
        rdNhanVienNu.setText("Nữ");

        jLabel7.setText("Loại Nhân Viên:");

        cbxLoaiNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chính Thức", "Thời Vụ" }));

        jLabel8.setText("Số CMND:");

        jLabel9.setText("Số ĐT:");

        jLabel10.setText("Địa Chỉ:");

        jLabel4.setText("Năm Sinh:");

        jDateChooserNamSinhNhanVien.setDateFormatString("dd/MM/yyyy");

        btnTimKiemNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        btnTimKiemNhanVien.setText("Tìm Kiếm");
        btnTimKiemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNhanVienActionPerformed(evt);
            }
        });

        btnThemNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Add-16.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnThemNhanVienTuFile.setBackground(new java.awt.Color(51, 204, 255));
        btnThemNhanVienTuFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Microsoft Excel-16.png"))); // NOI18N
        btnThemNhanVienTuFile.setText("Thêm Từ File");
        btnThemNhanVienTuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienTuFileActionPerformed(evt);
            }
        });

        btnSuaNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Edit-16.png"))); // NOI18N
        btnSuaNhanVien.setText("Sửa");
        btnSuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnXoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Delete-16.png"))); // NOI18N
        btnXoaNhanVien.setText("Xóa");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(51, 204, 255));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton22.setText("Làm Sạch Dữ Liệu Nhập");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        btnXemToanBoNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnXemToanBoNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        btnXemToanBoNhanVien.setText("Xem Toàn Bộ");
        btnXemToanBoNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemToanBoNhanVienActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(51, 204, 255));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16.png"))); // NOI18N
        jButton24.setText("In Thông Tin");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        chkSuDUngMaNhanVienTiepTheo.setBackground(new java.awt.Color(51, 204, 255));
        chkSuDUngMaNhanVienTiepTheo.setText("Sử dụng mã tiếp theo");
        chkSuDUngMaNhanVienTiepTheo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkSuDUngMaNhanVienTiepTheoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnNhanVienLayout = new javax.swing.GroupLayout(pnNhanVien);
        pnNhanVien.setLayout(pnNhanVienLayout);
        pnNhanVienLayout.setHorizontalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNhanVienLayout.createSequentialGroup()
                        .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5))
                        .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnNhanVienLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenNhanVien))
                            .addGroup(pnNhanVienLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnNhanVienLayout.createSequentialGroup()
                                        .addComponent(rdNhanVienNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdNhanVienNu)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblMaNhanVienTiepTheo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnNhanVienLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnNhanVienLayout.createSequentialGroup()
                                        .addComponent(chkSuDUngMaNhanVienTiepTheo)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtMaNhanVien)))))
                    .addGroup(pnNhanVienLayout.createSequentialGroup()
                        .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChiNhanVien)
                            .addComponent(txtSoCMNDNhanVien)
                            .addComponent(txtSoDTNhanVien)
                            .addGroup(pnNhanVienLayout.createSequentialGroup()
                                .addComponent(cbxLoaiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jDateChooserNamSinhNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnNhanVienLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemNhanVienTuFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnNhanVienLayout.createSequentialGroup()
                                .addComponent(btnTimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnNhanVienLayout.createSequentialGroup()
                                .addComponent(btnSuaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXemToanBoNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnNhanVienLayout.setVerticalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblMaNhanVienTiepTheo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSuDUngMaNhanVienTiepTheo)
                .addGap(1, 1, 1)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNhanVienNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdNhanVienNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxLoaiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jDateChooserNamSinhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoCMNDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtSoDTNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDiaChiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemNhanVien)
                    .addComponent(btnThemNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemNhanVienTuFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaNhanVien)
                    .addComponent(btnXoaNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemToanBoNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24)
                .addGap(33, 33, 33))
        );

        pnKhachHang.setBackground(new java.awt.Color(51, 204, 255));
        pnKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách Hàng:"));

        jLabel2.setText("Mã Khách Hàng:");

        jLabel11.setText("Mã Tiếp Theo:");

        lblMaKhachHangTiepTheo.setText("jLabel12");

        jLabel13.setText("Tên Khách Hàng:");

        jLabel14.setText("Giới Tính:");

        rdKhachHangNam.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup2.add(rdKhachHangNam);
        rdKhachHangNam.setText("Nam");

        rdKhachHangNu.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup2.add(rdKhachHangNu);
        rdKhachHangNu.setText("Nữ");

        jLabel15.setText("Số CMND:");

        jLabel16.setText("Số Điện Thoại:");

        jLabel17.setText("Địa Chỉ:");

        jLabel18.setText("Năm Sinh:");

        jDateChooserNamSinhKhachHang.setDateFormatString("dd/MM/yyyy");

        btnTimKiemKhachHang.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiemKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        btnTimKiemKhachHang.setText("Tìm Kiếm");
        btnTimKiemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKhachHangActionPerformed(evt);
            }
        });

        btnThemKhachHang.setBackground(new java.awt.Color(51, 204, 255));
        btnThemKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Add-16.png"))); // NOI18N
        btnThemKhachHang.setText("Thêm");
        btnThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachHangActionPerformed(evt);
            }
        });

        ThemKhachHangTuFile.setBackground(new java.awt.Color(51, 204, 255));
        ThemKhachHangTuFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Microsoft Excel-16.png"))); // NOI18N
        ThemKhachHangTuFile.setText("Thêm Từ File");
        ThemKhachHangTuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemKhachHangTuFileActionPerformed(evt);
            }
        });

        btnSuaKhachHang.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Edit-16.png"))); // NOI18N
        btnSuaKhachHang.setText("Sửa");
        btnSuaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKhachHangActionPerformed(evt);
            }
        });

        btnXoaKhachHang.setBackground(new java.awt.Color(51, 204, 255));
        btnXoaKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Delete-16.png"))); // NOI18N
        btnXoaKhachHang.setText("Xóa");
        btnXoaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhachHangActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(51, 204, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton14.setText("Làm Sạch Dữ Liệu Nhập");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        btnXemToanBoKhachHang.setBackground(new java.awt.Color(51, 204, 255));
        btnXemToanBoKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        btnXemToanBoKhachHang.setText("Xem Toàn Bộ");
        btnXemToanBoKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemToanBoKhachHangActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(51, 204, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16.png"))); // NOI18N
        jButton16.setText("In Thông Tin");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        chkSuDungMaKhachHangTiepTheo.setBackground(new java.awt.Color(51, 204, 255));
        chkSuDungMaKhachHangTiepTheo.setText("Sử dụng mã tiếp theo");
        chkSuDungMaKhachHangTiepTheo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkSuDungMaKhachHangTiepTheoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnKhachHangLayout = new javax.swing.GroupLayout(pnKhachHang);
        pnKhachHang.setLayout(pnKhachHangLayout);
        pnKhachHangLayout.setHorizontalGroup(
            pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangLayout.createSequentialGroup()
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhachHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnKhachHangLayout.createSequentialGroup()
                                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoDienThoaiKhachHang, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSoCMNDKhachHang)
                                    .addComponent(txtDiaChiKhachHang)))
                            .addGroup(pnKhachHangLayout.createSequentialGroup()
                                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKhachHang)
                                    .addComponent(lblMaKhachHangTiepTheo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenKhachHang)
                                    .addComponent(jDateChooserNamSinhKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnKhachHangLayout.createSequentialGroup()
                                        .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkSuDungMaKhachHangTiepTheo)
                                            .addGroup(pnKhachHangLayout.createSequentialGroup()
                                                .addComponent(rdKhachHangNam)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdKhachHangNu)))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(pnKhachHangLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ThemKhachHangTuFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnKhachHangLayout.createSequentialGroup()
                                .addComponent(btnTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnKhachHangLayout.createSequentialGroup()
                                .addComponent(btnSuaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXemToanBoKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 56, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnKhachHangLayout.setVerticalGroup(
            pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblMaKhachHangTiepTheo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSuDungMaKhachHangTiepTheo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdKhachHangNam)
                        .addComponent(rdKhachHangNu))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jDateChooserNamSinhKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSoCMNDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtSoDienThoaiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtDiaChiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemKhachHang)
                    .addComponent(btnThemKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ThemKhachHangTuFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaKhachHang)
                    .addComponent(btnXoaKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemToanBoKhachHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pnLuongNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        pnLuongNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập lương nhân viên:"));

        jLabel12.setText("Mã Nhân Viên:");

        cbxMaNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        cbxMaNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaNhanVienItemStateChanged(evt);
            }
        });

        jLabel19.setText("Tên Nhân Viên:");

        jLabel21.setText("Loại Nhân Viên:");

        jLabel23.setText("Số Ngày Làm:");

        jSpinField1.setBackground(new java.awt.Color(51, 204, 255));
        jSpinField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinField1PropertyChange(evt);
            }
        });

        jLabel24.setText("Số Ngày Làm Thêm:");

        jSpinField3.setBackground(new java.awt.Color(51, 204, 255));
        jSpinField3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinField3PropertyChange(evt);
            }
        });

        jLabel25.setText("Ngày Trả Lương:");

        jDateChooser1.setBackground(new java.awt.Color(51, 204, 255));
        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jLabel26.setText("Lương:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/favorite_add.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/deletered.png"))); // NOI18N
        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/software_update.png"))); // NOI18N
        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cbxTenNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTenNhanVienItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnLuongNhanVienLayout = new javax.swing.GroupLayout(pnLuongNhanVien);
        pnLuongNhanVien.setLayout(pnLuongNhanVienLayout);
        pnLuongNhanVienLayout.setHorizontalGroup(
            pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLuongNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLuongNhanVienLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(4, 4, 4)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnLuongNhanVienLayout.createSequentialGroup()
                        .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxMaNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTenNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnLuongNhanVienLayout.setVerticalGroup(
            pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLuongNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxMaNhanVien)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnLuongNhanVienLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnLuongNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)))
                    .addGroup(pnLuongNhanVienLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jLayeredPane2.setLayer(pnNhanVien, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnKhachHang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(pnLuongNhanVien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnLuongNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnLuongNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách:"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDanhSachMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(231, 40));

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/business_man_blue.png"))); // NOI18N
        jMenu2.setText("Quản Lý Nhân Viên Và Khách Hàng");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_user_1902268.png"))); // NOI18N
        jMenuItem1.setText("Quản Lý Nhân Viên");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/seo-93-16.png"))); // NOI18N
        jMenuItem2.setText("Quản Lý Khách Hàng VIP");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_US-dollar_63166.png"))); // NOI18N
        jMenuItem3.setText("Lương Nhân Viên");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNhanVienActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        pnNhanVien.setVisible(true);
        pnKhachHang.setVisible(false);
        pnLuongNhanVien.setVisible(false);
        index = 1;
        hienThiMaNhanVienTiepTheo();
        hienThiNhanVienLenBang(bllNhanVien.layToanBoNhanVien());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        pnNhanVien.setVisible(false);
        pnKhachHang.setVisible(true);
        pnLuongNhanVien.setVisible(false);
        index = 2;
        hienThiMaKhachHangTiepTheo();
        hienThiKhachHangLenBang(bllKhachHang.layToanBoKhachHangVip());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tblDanhSachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMousePressed
        // TODO add your handling code here:
        switch (index) {
            case 1:
                dienThongTinNhanVien();
                break;
            case 2:
                dienThongTinKhachHang();
                break;
            case 3:
                dienThongTinLuongNhanVien();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_tblDanhSachMousePressed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        String maNhanVien = lblMaNhanVienTiepTheo.getText();
        if (chkSuDUngMaNhanVienTiepTheo.isSelected() == false) {
            maNhanVien = txtMaNhanVien.getText();
        }
        String tenNhanVien = txtTenNhanVien.getText();
        String gioiTinh = "";
        if (rdNhanVienNam.isSelected() == true) {
            gioiTinh = "Nam";
        } else if (rdNhanVienNu.isSelected() == true) {
            gioiTinh = "Nữ";
        }
        Date namSinh = jDateChooserNamSinhNhanVien.getDate();
        String loaiNhanVien = "";
        if (cbxLoaiNhanVien.getSelectedIndex() != -1) {
            loaiNhanVien = cbxLoaiNhanVien.getSelectedItem().toString();
        }
        String soDienThoai = txtSoDTNhanVien.getText();
        String soCMND = txtSoCMNDNhanVien.getText();
        String diaChi = txtDiaChiNhanVien.getText();
        if (kiemTraDuLieuNhapNhanVien(maNhanVien, tenNhanVien, gioiTinh, namSinh, loaiNhanVien, soCMND, soDienThoai, diaChi, "Thêm")) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm nhân viên ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                if (bllNhanVien.themNhanVien(maNhanVien, tenNhanVien, gioiTinh, namSinh, loaiNhanVien, soDienThoai, soCMND, diaChi) > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
                    hienThiNhanVienLenBang(bllNhanVien.layToanBoNhanVien());
                    lamSachDuLieuNhanVien();
                    hienThiMaNhanVienTiepTheo();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
                }
            }
        }

    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void hienThiMaNhanVienTiepTheo() {
        sttCuoi = taoMa.laySoCuoiCuaBangNhanVien();
        maTiepTheo = "NV" + taoMa.taoChuoiTuSoDaCho(sttCuoi);
        lblMaNhanVienTiepTheo.setText(maTiepTheo);
    }

    private void hienThiMaKhachHangTiepTheo() {
        sttCuoi = taoMa.laySoCuoiCuaBangKhachHangVIP();
        maTiepTheo = "KHV" + taoMa.taoChuoiTuSoDaCho(sttCuoi);
        lblMaKhachHangTiepTheo.setText(maTiepTheo);
    }

    private void btnSuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVienActionPerformed
        // TODO add your handling code here:
        String maNhanVien = txtMaNhanVien.getText();
        String tenNhanVien = txtTenNhanVien.getText();
        String gioiTinh;
        if (rdNhanVienNam.isSelected() == true) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        Date namSinh = jDateChooserNamSinhNhanVien.getDate();
        String loaiNhanVien = cbxLoaiNhanVien.getSelectedItem().toString();
        String soDienThoai = txtSoDTNhanVien.getText();
        String soCMND = txtSoCMNDNhanVien.getText();
        String diaChi = txtDiaChiNhanVien.getText();
        if (kiemTraDuLieuNhapNhanVien(maNhanVien, tenNhanVien, gioiTinh, namSinh, loaiNhanVien, soCMND, soDienThoai, diaChi, "Sửa")) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa nhân viên ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                if (bllNhanVien.suaNhanVien(maNhanVien, tenNhanVien, gioiTinh, namSinh, loaiNhanVien, soDienThoai, soCMND, diaChi) > 0) {
                    JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công");
                    hienThiNhanVienLenBang(bllNhanVien.layToanBoNhanVien());
                    lamSachDuLieuNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaNhanVienActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        // TODO add your handling code here:
        int[] rows = tblDanhSach.getSelectedRows();
        if (rows.length > 0) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                String maNhanVien = "";
                int cout = 0;
                for (int i : rows) {
                    maNhanVien = tblDanhSach.getValueAt(i, 0) + "";
                    cout += bllNhanVien.xoaNhanVien(maNhanVien);
                }
                if (cout > 0) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công " + cout + "/" + rows.length + " nhân viên");
                    hienThiNhanVienLenBang(bllNhanVien.layToanBoNhanVien());
                    lamSachDuLieuNhanVien();
                    hienThiMaNhanVienTiepTheo();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn nhân viên cần xóa");
        }
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void btnTimKiemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNhanVienActionPerformed
        // TODO add your handling code here:
        String maNhanVien = txtMaNhanVien.getText();
        String tenNhanVien = txtTenNhanVien.getText();
        String gioiTinh = "";
        if (rdNhanVienNam.isSelected() == true) {
            gioiTinh = "Nam";
        } else if (rdNhanVienNu.isSelected() == true) {
            gioiTinh = "Nữ";
        }
        Date namSinh = jDateChooserNamSinhNhanVien.getDate();
        String loaiNhanVien = "";
        if (cbxLoaiNhanVien.getSelectedIndex() != -1) {
            loaiNhanVien = cbxLoaiNhanVien.getSelectedItem().toString();
        }
        String soCMND = txtSoCMNDNhanVien.getText();
        String soDienThoai = txtSoDTNhanVien.getText();
        String diaChi = txtDiaChiNhanVien.getText();
        System.out.println("Mã nv:" + maNhanVien);
        if (kiemTraDuLieuNhapNhanVien(maNhanVien, tenNhanVien, gioiTinh, namSinh, loaiNhanVien, soCMND, soDienThoai, diaChi, "Tìm Kiếm")) {
            hienThiNhanVienLenBang(bllNhanVien.timKiemNhanVien(maNhanVien, tenNhanVien, gioiTinh, namSinh, loaiNhanVien, soDienThoai, soCMND, diaChi));
        }
    }//GEN-LAST:event_btnTimKiemNhanVienActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        lamSachDuLieuNhanVien();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        lamSachDuLieuKhachHang();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void btnTimKiemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKhachHangActionPerformed
        // TODO add your handling code here:
        String maKhachHang = txtMaKhachHang.getText();
        String tenKhachHang = txtTenKhachHang.getText();
        String gioiTinh = "";
        if (rdKhachHangNam.isSelected()) {
            gioiTinh = "Nam";
        } else if (rdKhachHangNu.isSelected()) {
            gioiTinh = "Nữ";
        }
        Date namSinh = jDateChooserNamSinhKhachHang.getDate();
        String soCMND = txtSoCMNDKhachHang.getText();
        String soDienThoai = txtSoDienThoaiKhachHang.getText();
        String diaChi = txtDiaChiKhachHang.getText();
        System.out.println("Mã kh:" + maKhachHang);
        if (kiemTraDuLieuNhapKhachHang(maKhachHang, tenKhachHang, gioiTinh, namSinh, soCMND, soDienThoai, diaChi, "Tìm Kiếm")) {
            hienThiKhachHangLenBang(bllKhachHang.timKiemKhachHangVIP(maKhachHang, tenKhachHang, gioiTinh, namSinh, soCMND, soDienThoai, diaChi));
        }
    }//GEN-LAST:event_btnTimKiemKhachHangActionPerformed

    private void btnThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachHangActionPerformed
        // TODO add your handling code here:
        String maKhachHang = lblMaKhachHangTiepTheo.getText();
        if (chkSuDungMaKhachHangTiepTheo.isSelected() == false) {
            maKhachHang = txtMaKhachHang.getText();
        }
        String tenKhachHang = txtTenKhachHang.getText();
        String gioiTinh = "";
        if (rdKhachHangNam.isSelected()) {
            gioiTinh = "Nam";
        } else if (rdKhachHangNu.isSelected()) {
            gioiTinh = "Nữ";
        }
        Date namSinh = jDateChooserNamSinhKhachHang.getDate();
        String soCMND = txtSoCMNDKhachHang.getText();
        String soDienThoai = txtSoDienThoaiKhachHang.getText();
        String diaChi = txtDiaChiKhachHang.getText();
        if (kiemTraDuLieuNhapKhachHang(maKhachHang, tenKhachHang, gioiTinh, namSinh, soCMND, soDienThoai, diaChi, "Thêm")) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm khách hàng ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan != JOptionPane.YES_OPTION) {
                return;
            }
            if (bllKhachHang.themKhachHangVIP(maKhachHang, tenKhachHang, gioiTinh, namSinh, soCMND, soDienThoai, diaChi) > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                lamSachDuLieuKhachHang();
                hienThiMaKhachHangTiepTheo();
                hienThiKhachHangLenBang(bllKhachHang.layToanBoKhachHangVip());
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private boolean kiemTraDuLieuNhapKhachHang(String maKhachHang, String tenKhachHang, String gioiTinh, Date namSinh,
            String soCMND, String soDienThoai, String diaChi, String thaoTac) {
        String regex1 = "\\s*";
        String regex2 = "\\d{9}|\\d{12}";
        String regex3 = "\\d{10}|\\d{11}";
        if (maKhachHang.matches(regex1) && tenKhachHang.matches(regex1) && gioiTinh.matches(regex1) && namSinh == null
                && soCMND.matches(regex1) && soDienThoai.matches(regex1) && diaChi.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return false;
        } else if (thaoTac.equalsIgnoreCase("Thêm")) {
            if (maKhachHang.matches(regex1)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mã khách hàng");
                return false;
            } else if (gioiTinh.matches(regex1)) {
                JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
                return false;
            }
        } else if (thaoTac.equals("Sửa")) {
            if (maKhachHang.matches(regex1)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mã khách hàng");
                return false;
            }
            int row = tblDanhSach.getSelectedRow();
            String maKhachHangGoc = tblDanhSach.getValueAt(row, 0) + "";
            if (!maKhachHang.equals(maKhachHangGoc)) {
                JOptionPane.showMessageDialog(null, "Không thể sửa mã khách hàng");
                txtMaKhachHang.setText(maKhachHangGoc);
                return false;
            }
        }
        if (!soCMND.matches(regex1) && !soCMND.matches(regex2)) {
            JOptionPane.showMessageDialog(null, "Số chứng minh nhân dân phải là số có 9 hoặc 12 số");
            return false;
        } else if (!soDienThoai.matches(regex1) && !soDienThoai.matches(regex3)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là số có 10 hoặc 11 số");
            return false;
        }
        return true;
    }

    private boolean kiemTraDuLieuNhapNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, Date namSinh,
            String loaiNhanVien, String soCMND, String soDienThoai, String diaChi, String thaoTac) {
        String regex1 = "\\s*";
        String regex2 = "\\d{9}|\\d{12}";
        String regex3 = "\\d{10}|\\d{11}";
        System.out.println("sô cmnd" + soCMND);
        System.out.println("Số điện thoại" + soDienThoai);
        if (maNhanVien.matches(regex1) && tenNhanVien.matches(regex1) && gioiTinh.matches(regex1) && namSinh == null
                && loaiNhanVien.matches(regex1) && soCMND.matches(regex1) && soDienThoai.matches(regex1) && diaChi.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return false;
        } else if (thaoTac.equalsIgnoreCase("Thêm")) {
            if (maNhanVien.matches(regex1)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên");
                return false;
            } else if (gioiTinh.matches(regex1)) {
                JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
                return false;
            }
        } else if (thaoTac.equals("Sửa")) {
            if (maNhanVien.matches(regex1)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên");
                return false;
            }
            int row = tblDanhSach.getSelectedRow();
            String maNhanVienGoc = tblDanhSach.getValueAt(row, 0) + "";
            if (!maNhanVien.equals(maNhanVienGoc)) {
                JOptionPane.showMessageDialog(null, "Không thể sửa mã nhân viên");
                txtMaKhachHang.setText(maNhanVienGoc);
                return false;
            }
        }
        if (!thaoTac.equals("Tìm Kiếm")) {
            if (loaiNhanVien.matches(regex1)) {
                JOptionPane.showMessageDialog(null, "Chưa chọn loại nhân viên");
                return false;
            }
        }
        if (!soCMND.matches(regex1) && !soCMND.matches(regex2)) {
            JOptionPane.showMessageDialog(null, "Số chứng minh nhân dân phải là số có 9 hoặc 12 số");
            return false;
        } else if (!soDienThoai.matches(regex1) && !soDienThoai.matches(regex3)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là số có 10 hoặc 11 số");
            return false;
        }
        return true;
    }

    private void btnSuaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKhachHangActionPerformed
        // TODO add your handling code here:
        String maKhachHang = txtMaKhachHang.getText();
        System.out.println("Mã kh" + maKhachHang);
        String tenKhachHang = txtTenKhachHang.getText();
        String gioiTinh = "";
        if (rdKhachHangNam.isSelected()) {
            gioiTinh = "Nam";
        } else if (rdKhachHangNu.isSelected()) {
            gioiTinh = "Nữ";
        }
        Date namSinh = jDateChooserNamSinhKhachHang.getDate();
        String soCMND = txtSoCMNDKhachHang.getText();
        String soDienThoai = txtSoDienThoaiKhachHang.getText();
        String diaChi = txtDiaChiKhachHang.getText();
        if (kiemTraDuLieuNhapKhachHang(maKhachHang, tenKhachHang, gioiTinh, namSinh, soCMND, soDienThoai, diaChi, "Sửa")) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa khách hàng ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                if (bllKhachHang.suaKhachHangVIP(maKhachHang, tenKhachHang, gioiTinh, namSinh, soCMND, soDienThoai, diaChi) > 0) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    lamSachDuLieuKhachHang();
                    hienThiKhachHangLenBang(bllKhachHang.layToanBoKhachHangVip());
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaKhachHangActionPerformed

    private void btnXoaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhachHangActionPerformed
        // TODO add your handling code here:
        int[] rows = tblDanhSach.getSelectedRows();
        if (rows.length > 0) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan != JOptionPane.YES_OPTION) {
                return;
            }
            int cout = 0;
            String maKhachHang;
            for (int i = 0; i < rows.length; i++) {
                maKhachHang = tblDanhSach.getValueAt(i, 0) + "";
                cout += bllKhachHang.xoaKhachHangVIP(maKhachHang);
            }
            if (cout > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công " + cout + "/" + rows.length + " khách hàng");
                lamSachDuLieuKhachHang();
                hienThiMaKhachHangTiepTheo();
                hienThiKhachHangLenBang(bllKhachHang.layToanBoKhachHangVip());
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng cần xóa");
        }

    }//GEN-LAST:event_btnXoaKhachHangActionPerformed

    private void chkSuDUngMaNhanVienTiepTheoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkSuDUngMaNhanVienTiepTheoStateChanged
        // TODO add your handling code here:
        if (chkSuDUngMaNhanVienTiepTheo.isSelected()) {
            txtMaNhanVien.setEditable(false);
        } else {
            txtMaNhanVien.setEditable(true);
        }
    }//GEN-LAST:event_chkSuDUngMaNhanVienTiepTheoStateChanged

    private void chkSuDungMaKhachHangTiepTheoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkSuDungMaKhachHangTiepTheoStateChanged
        // TODO add your handling code here:
        if (chkSuDungMaKhachHangTiepTheo.isSelected()) {
            txtMaKhachHang.setEditable(false);
        } else {
            txtMaKhachHang.setEditable(true);
        }
    }//GEN-LAST:event_chkSuDungMaKhachHangTiepTheoStateChanged

    private void btnXemToanBoNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemToanBoNhanVienActionPerformed
        // TODO add your handling code here:
        hienThiNhanVienLenBang(bllNhanVien.layToanBoNhanVien());
    }//GEN-LAST:event_btnXemToanBoNhanVienActionPerformed

    private void btnThemNhanVienTuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienTuFileActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Chức năng đang cập nhật");
    }//GEN-LAST:event_btnThemNhanVienTuFileActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Chức năng đang cập nhật");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void ThemKhachHangTuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemKhachHangTuFileActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Chức năng đang cập nhật");
    }//GEN-LAST:event_ThemKhachHangTuFileActionPerformed

    private void btnXemToanBoKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemToanBoKhachHangActionPerformed
        // TODO add your handling code here:
        hienThiKhachHangLenBang(bllKhachHang.layToanBoKhachHangVip());
    }//GEN-LAST:event_btnXemToanBoKhachHangActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Chức năng đang cập nhật");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        pnKhachHang.setVisible(false);
        pnNhanVien.setVisible(false);
        pnLuongNhanVien.setVisible(true);
        index = 3;
        hienThiMaNhanVienLenCombobox(bllNhanVien.layToanBoNhanVien());
        hienThiLuongNhanVienLenBang(bllNhanVien.layToanBoLuongNhanVien());
        jDateChooser1.setDate(Calendar.getInstance().getTime());
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void cbxMaNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaNhanVienItemStateChanged
        // TODO add your handling code here:
        int index = cbxMaNhanVien.getSelectedIndex();
        if (index != -1 && index != cbxTenNhanVien.getSelectedIndex()) {
            cbxTenNhanVien.setSelectedIndex(index);
            NhanVien nv = (NhanVien) cbxTenNhanVien.getSelectedItem();
            if (nv instanceof NhanVienChinhThuc) {
                jLabel22.setText("Chính Thức");
            } else {
                jLabel22.setText("Thời Vụ");
            }
            jSpinField1.setValue(nv.getSoNgayLam());
            jSpinField3.setValue(nv.getSoNgayLamThem());
            jLabel27.setText(nv.tinhLuong() + " VND");
        }
    }//GEN-LAST:event_cbxMaNhanVienItemStateChanged

    private void cbxTenNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTenNhanVienItemStateChanged
        // TODO add your handling code here:
        int index = cbxTenNhanVien.getSelectedIndex();
        if (index != -1 && index != cbxMaNhanVien.getSelectedIndex()) {
            cbxMaNhanVien.setSelectedIndex(index);
            NhanVien nv = (NhanVien) cbxTenNhanVien.getSelectedItem();
            if (nv instanceof NhanVienChinhThuc) {
                jLabel22.setText("Chính Thức");
            } else {
                jLabel22.setText("Thời Vụ");
            }
            jSpinField1.setValue(nv.getSoNgayLam());
            jSpinField3.setValue(nv.getSoNgayLamThem());
            jLabel27.setText(nv.tinhLuong() + " VND");
        }
    }//GEN-LAST:event_cbxTenNhanVienItemStateChanged

    private void jSpinField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinField1PropertyChange
        // TODO add your handling code here:
        System.out.println("" + jSpinField1.getValue());
        int index = cbxMaNhanVien.getSelectedIndex();
        if (index != -1) {
            NhanVien nv = (NhanVien) cbxTenNhanVien.getItemAt(index);
            nv.setSoNgayLam(jSpinField1.getValue());
            jLabel27.setText(nv.tinhLuong() + " VND");
        }
    }//GEN-LAST:event_jSpinField1PropertyChange

    private void jSpinField3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinField3PropertyChange
        // TODO add your handling code here:
        int index = cbxMaNhanVien.getSelectedIndex();
        if (index != -1) {
            NhanVien nv = (NhanVien) cbxTenNhanVien.getItemAt(index);
            nv.setSoNgayLamThem(jSpinField3.getValue());
            jLabel27.setText(nv.tinhLuong() + " VND");
        }
    }//GEN-LAST:event_jSpinField3PropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = tblDanhSach.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Chọn dữ liệu cần sửa trước");
        } else {
            int soNgayLam = jSpinField1.getValue();
            int soNgayLamThem = jSpinField3.getValue();
            Date ngayTra = jDateChooser1.getDate();
            if (ngayTra == null) {
                JOptionPane.showMessageDialog(null, "Chưa chọn ngày trả");
                return;
            }
            int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (kt == JOptionPane.YES_OPTION) {
                try {
                    int index = cbxTenNhanVien.getSelectedIndex();
                    NhanVien nv = (NhanVien) cbxTenNhanVien.getSelectedItem();
                    nv.setSoNgayLam(soNgayLam);
                    nv.setSoNgayLamThem(soNgayLamThem);
                    nv.setNgayTraLuong(ngayTra);
                    String strThang = tblDanhSach.getValueAt(row, 0) + "";
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
                    Date thang = sdf.parse(strThang);
                    if (bllNhanVien.suaLuong(nv, thang) > 0) {
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        hienThiLuongNhanVienLenBang(bllNhanVien.layToanBoLuongNhanVien());
                        lamSachLuongNhanVien();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa thất bại");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int index = cbxTenNhanVien.getSelectedIndex();
        if (index != -1) {
            NhanVien nv = (NhanVien) cbxTenNhanVien.getSelectedItem();
            Calendar cal = Calendar.getInstance();
            if (bllNhanVien.kiemTraTraLuong(nv, cal.getTime())) {
                JOptionPane.showMessageDialog(null, "Nhân viên " + nv.getTen() + " đã trả lương tháng này");
                return;
            }
            int soNgayLam = jSpinField1.getValue();
            int soNgayLamThem = jSpinField3.getValue();
            Date ngayTra = jDateChooser1.getDate();
            if (ngayTra == null) {
                int xacNhan = JOptionPane.showConfirmDialog(null, "<html><p>Bạn chưa chọn ngày trả.</p>"
                        + "<p>Bạn co muốn lấy ngày hiện tại là ngày trả lương</p>"
                        + "<p>Nhấn Yes để trả lương theo ngày hiện tại</p>"
                        + "<p>No để hủy bỏ trả lương</p></html>", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (xacNhan == JOptionPane.YES_OPTION) {
                    nv.setNgayTraLuong(Calendar.getInstance().getTime());
                } else {
                    return;
                }
            } else {
                nv.setNgayTraLuong(ngayTra);
            }
            nv.setSoNgayLam(soNgayLam);
            nv.setSoNgayLamThem(soNgayLamThem);
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn muốn trả lương cho nhân viên " + nv.getTen(), "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                if (bllNhanVien.themLuong(nv, cal.getTime()) > 0) {
                    JOptionPane.showMessageDialog(null, "Trả lương thành công");
                    hienThiLuongNhanVienLenBang(bllNhanVien.layToanBoLuongNhanVien());
                    lamSachLuongNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Trả lương thất bại");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lamSachLuongNhanVien() {
        cbxMaNhanVien.setSelectedIndex(-1);
        cbxTenNhanVien.setSelectedIndex(-1);
        jSpinField1.setValue(0);
        jSpinField3.setValue(0);
        jLabel27.setText("0 VND");
        jDateChooser1.setDate(null);
        jLabel22.setText("");
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int[] rows = tblDanhSach.getSelectedRows();
        if (rows.length == 0) {
            JOptionPane.showMessageDialog(null, "Chọn dữ liệu cần xóa trước");
            return;
        }
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            try {
                int cout = 0;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
                for (int i : rows) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNhanVien(tblDanhSach.getValueAt(i, 1) + "");
                    Date thang = sdf.parse(tblDanhSach.getValueAt(i, 0) + "");
                    cout += bllNhanVien.xoaLuong(nv, thang);
                }
                if (cout > 0) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công " + cout + "/" + rows.length + " lương nhân viên");
                    hienThiLuongNhanVienLenBang(bllNhanVien.layToanBoLuongNhanVien());
                    lamSachLuongNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    private void hienThiMaNhanVienLenCombobox(ArrayList<NhanVien> ds) {
        DefaultComboBoxModel<String> cbxmMa = new DefaultComboBoxModel();
        DefaultComboBoxModel<Object> cbxTen = new DefaultComboBoxModel<>();
        if (ds != null) {
            for (NhanVien nv : ds) {
                cbxmMa.addElement(nv.getMaNhanVien());
                cbxTen.addElement(nv);
            }
            cbxMaNhanVien.setModel(cbxmMa);
            cbxTenNhanVien.setModel(cbxTen);
            cbxMaNhanVien.setSelectedIndex(-1);
            cbxTenNhanVien.setSelectedIndex(-1);
        }
    }

    private void hienThiLuongNhanVienLenBang(HashMap<NhanVien, Date> ds) {
        dtmLuongNhanVien.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        for (Map.Entry<NhanVien, Date> m : ds.entrySet()) {
            NhanVien nv = m.getKey();
            Date thang = m.getValue();
            Vector<String> v = new Vector<>();
            v.add(sdf.format(thang));
            v.add(nv.getMaNhanVien());
            v.add(nv.getTen());
            v.add(nv.getSoNgayLam() + " ngày");
            v.add(nv.getSoNgayLamThem() + " ngày");
            v.add(nv.tinhLuong() + " VND");
            v.add(this.sdf.format(nv.getNgayTraLuong()));
            dtmLuongNhanVien.addRow(v);
        }
        tblDanhSach.setModel(dtmLuongNhanVien);
    }

    private void lamSachDuLieuKhachHang() {
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        if (rdKhachHangNam.isSelected()) {
            rdKhachHangNam.setSelected(false);
        } else {
            rdKhachHangNu.setSelected(false);
        }
        jDateChooserNamSinhKhachHang.setDate(null);
        txtSoCMNDKhachHang.setText("");
        txtSoDienThoaiKhachHang.setText("");
        txtDiaChiKhachHang.setText("");
    }

    private void lamSachDuLieuNhanVien() {
        txtMaNhanVien.setText("");
        txtTenNhanVien.setText("");
        if (rdNhanVienNam.isSelected() == true) {
            rdNhanVienNam.setSelected(false);
        } else {
            rdNhanVienNu.setSelected(false);
        }
        jDateChooserNamSinhNhanVien.setDate(null);
        cbxLoaiNhanVien.setSelectedIndex(-1);
        txtSoCMNDNhanVien.setText("");
        txtSoDTNhanVien.setText("");
        txtDiaChiNhanVien.setText("");
    }

    private void dienThongTinNhanVien() {
        try {
            int row = tblDanhSach.getSelectedRow();
            txtMaNhanVien.setText(tblDanhSach.getValueAt(row, 0) + "");
            txtTenNhanVien.setText(tblDanhSach.getValueAt(row, 1) + "");
            String gioiTinh = tblDanhSach.getValueAt(row, 2) + "";
            if (gioiTinh.equals("Nam")) {
                rdNhanVienNam.setSelected(true);
            } else {
                rdNhanVienNu.setSelected(true);
            }
            String strNamSinh = tblDanhSach.getValueAt(row, 3) + "";
            Date namSinh = null;
            if (!strNamSinh.equals("")) {
                namSinh = sdf.parse(strNamSinh);
            }
            jDateChooserNamSinhNhanVien.setDate(namSinh);
            String loaiNhanVien = tblDanhSach.getValueAt(row, 4) + "";
            if (loaiNhanVien.equals("Chính Thức")) {
                cbxLoaiNhanVien.setSelectedIndex(0);
            } else {
                cbxLoaiNhanVien.setSelectedIndex(1);
            }
            txtSoDTNhanVien.setText(tblDanhSach.getValueAt(row, 5) + "");
            txtSoCMNDNhanVien.setText(tblDanhSach.getValueAt(row, 6) + "");
            txtDiaChiNhanVien.setText(tblDanhSach.getValueAt(row, 7) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dienThongTinKhachHang() {
        try {
            int row = tblDanhSach.getSelectedRow();
            txtMaKhachHang.setText(tblDanhSach.getValueAt(row, 0) + "");
            txtTenKhachHang.setText(tblDanhSach.getValueAt(row, 1) + "");
            String gioiTinh = tblDanhSach.getValueAt(row, 2) + "";
            if (gioiTinh.equals("Nam")) {
                rdKhachHangNam.setSelected(true);
            } else {
                rdKhachHangNu.setSelected(true);
            }
            Date namSinh = null;
            String strNamSinh = tblDanhSach.getValueAt(row, 3) + "";
            if (!strNamSinh.equals("")) {
                namSinh = sdf.parse(strNamSinh);
            }
            jDateChooserNamSinhKhachHang.setDate(namSinh);
            txtSoDienThoaiKhachHang.setText(tblDanhSach.getValueAt(row, 4) + "");
            txtSoCMNDKhachHang.setText(tblDanhSach.getValueAt(row, 5) + "");
            txtDiaChiKhachHang.setText(tblDanhSach.getValueAt(row, 6) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(QuanLyNhanVienKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVienKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ThemKhachHangTuFile;
    private javax.swing.JButton btnSuaKhachHang;
    private javax.swing.JButton btnSuaNhanVien;
    private javax.swing.JButton btnThemKhachHang;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThemNhanVienTuFile;
    private javax.swing.JButton btnTimKiemKhachHang;
    private javax.swing.JButton btnTimKiemNhanVien;
    private javax.swing.JButton btnXemToanBoKhachHang;
    private javax.swing.JButton btnXemToanBoNhanVien;
    private javax.swing.JButton btnXoaKhachHang;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxLoaiNhanVien;
    private javax.swing.JComboBox<String> cbxMaNhanVien;
    private javax.swing.JComboBox<Object> cbxTenNhanVien;
    private javax.swing.JCheckBox chkSuDUngMaNhanVienTiepTheo;
    private javax.swing.JCheckBox chkSuDungMaKhachHangTiepTheo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooserNamSinhKhachHang;
    private com.toedter.calendar.JDateChooser jDateChooserNamSinhNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.components.JSpinField jSpinField1;
    private com.toedter.components.JSpinField jSpinField2;
    private com.toedter.components.JSpinField jSpinField3;
    private javax.swing.JLabel lblMaKhachHangTiepTheo;
    private javax.swing.JLabel lblMaNhanVienTiepTheo;
    private javax.swing.JPanel pnKhachHang;
    private javax.swing.JPanel pnLuongNhanVien;
    private javax.swing.JPanel pnNhanVien;
    private javax.swing.JRadioButton rdKhachHangNam;
    private javax.swing.JRadioButton rdKhachHangNu;
    private javax.swing.JRadioButton rdNhanVienNam;
    private javax.swing.JRadioButton rdNhanVienNu;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtDiaChiKhachHang;
    private javax.swing.JTextField txtDiaChiNhanVien;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSoCMNDKhachHang;
    private javax.swing.JTextField txtSoCMNDNhanVien;
    private javax.swing.JTextField txtSoDTNhanVien;
    private javax.swing.JTextField txtSoDienThoaiKhachHang;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables

    private void dienThongTinLuongNhanVien() {
        try {
            int row = tblDanhSach.getSelectedRow();
            String maNhanVien = tblDanhSach.getValueAt(row, 1) + "";
            String tenNhanVien = tblDanhSach.getValueAt(row, 2) + "";
            String strSoNgayLam = tblDanhSach.getValueAt(row, 3) + "";
            String strSoNgayLamThem = tblDanhSach.getValueAt(row, 4) + "";
            String tienLuong = tblDanhSach.getValueAt(row, 5) + "";
            String strNgayTraTien = tblDanhSach.getValueAt(row, 6) + "";

            strSoNgayLam = strSoNgayLam.substring(0, strSoNgayLam.length() - 5);
            strSoNgayLamThem = strSoNgayLamThem.substring(0, strSoNgayLamThem.length() - 5);

            int soNgayLam = Integer.parseInt(strSoNgayLam);
            int soNgayLamThem = Integer.parseInt(strSoNgayLamThem);
            Date ngayTraLuong = sdf.parse(strNgayTraTien);

            for (int i = 0; i < cbxMaNhanVien.getItemCount(); i++) {
                String s = cbxMaNhanVien.getItemAt(i);
                if (maNhanVien.equals(s)) {
                    cbxMaNhanVien.setSelectedIndex(i);
                    cbxTenNhanVien.setSelectedIndex(i);
                    break;
                }
            }
            jSpinField1.setValue(soNgayLam);
            jSpinField3.setValue(soNgayLamThem);
            jDateChooser1.setDate(ngayTraLuong);
            jLabel27.setText(tienLuong);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
