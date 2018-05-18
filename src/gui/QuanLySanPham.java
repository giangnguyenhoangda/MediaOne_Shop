/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controler.BLLDiaNhac;
import controler.BLLDiaPhim;
import controler.BLLSach;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import object.DiaNhac;
import object.DiaPhim;
import object.Sach;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QuanLySanPham extends javax.swing.JFrame {

    /**
     * Creates new form QuanLySanPhamUI2
     */
    private DefaultTableModel dtmSach;
    private DefaultTableModel dtmDiaNhac;
    private DefaultTableModel dtmDiaPhim;
    private SimpleDateFormat sdf;
    private BLLSach bllSach;
    private BLLDiaNhac bllDiaNhac;
    private BLLDiaPhim bllDiaPhim;
    
    public QuanLySanPham() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        khoiTaoCacDoiTuong();
        tblSanPham.setModel(dtmSach);
        hienThiSachLenBang(bllSach.layToanBoSach());
    }
    
    private void hienThiSachLenBang(ArrayList<Sach> ds) {
        dtmSach.setRowCount(0);
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
        tblSanPham.setModel(dtmSach);
    }
    
    private void hienThiDiaNhacLenBang(ArrayList<DiaNhac> ds) {
        dtmDiaNhac.setRowCount(0);
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
        tblSanPham.setModel(dtmDiaNhac);
    }
    
    private void hienThiDiaPhimLenBang(ArrayList<DiaPhim> ds) {
        dtmDiaPhim.setRowCount(0);
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
        tblSanPham.setModel(dtmDiaPhim);
    }
    
    private void khoiTaoCacDoiTuong() {
        
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
        
        dtmDiaNhac = new DefaultTableModel();
        dtmDiaNhac.addColumn("Tên Đĩa Nhạc");
        dtmDiaNhac.addColumn("Ca Sĩ");
        dtmDiaNhac.addColumn("Nhà Sản Xuất");
        dtmDiaNhac.addColumn("Năm Xuất Bản");
        dtmDiaNhac.addColumn("Thể Loại");
        dtmDiaNhac.addColumn("Ngày Nhập");
        dtmDiaNhac.addColumn("Giá Mua");
        dtmDiaNhac.addColumn("Giá Bán");
        dtmDiaNhac.addColumn("Số Lượng");
        
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
        
        bllSach = new BLLSach();
        bllDiaNhac = new BLLDiaNhac();
        bllDiaPhim = new BLLDiaPhim();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    private void hienThiGiaoDienQuanLySach() {
        lblTenSanPham.setText("Tên Sách:");
        lblTacGia_DaoDien_CaSi.setText("Tác Giả:");
        lblNhaXuatBan_DienVien_NhaSanXuat.setText("Nhà Xuất Bản:");
        hienThiSachLenBang(bllSach.layToanBoSach());
    }
    
    private void hienThiGiaoDienQuanLyDiaPhim() {
        lblTenSanPham.setText("Tên Đĩa Phim:");
        lblTacGia_DaoDien_CaSi.setText("Đạo Diễn:");
        lblNhaXuatBan_DienVien_NhaSanXuat.setText("Diễn Viên:");
        hienThiDiaPhimLenBang(bllDiaPhim.layToanBoDiaPhim());
    }
    
    private void hienThiGiaoDienQuanLyDiaNhac() {
        lblTenSanPham.setText("Tên Đĩa Nhạc:");
        lblTacGia_DaoDien_CaSi.setText("Ca Sĩ:");
        lblNhaXuatBan_DienVien_NhaSanXuat.setText("Nhà Sản Xuất:");
        hienThiDiaNhacLenBang(bllDiaNhac.layToanBoDiaNhac());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxQuanLy = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lblTenSanPham = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        lblTacGia_DaoDien_CaSi = new javax.swing.JLabel();
        txtTacGia_DaoDien_Casi = new javax.swing.JTextField();
        lblNhaXuatBan_DienVien_NhaSanXuat = new javax.swing.JLabel();
        txtNhaXuatBan_DienVien_NhaSanXuat = new javax.swing.JTextField();
        lblNamSanXuat = new javax.swing.JLabel();
        jDateChooserNamSanXuat = new com.toedter.calendar.JDateChooser();
        lblTheLoai = new javax.swing.JLabel();
        txtTheLoai = new javax.swing.JTextField();
        lblNgayNhap = new javax.swing.JLabel();
        jDateChooserNgayNhap = new com.toedter.calendar.JDateChooser();
        lblGiaMua = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtGiaMua = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnThemTuFile = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamSachDuLieu = new javax.swing.JButton();
        btnXemToanBo = new javax.swing.JButton();
        btnInThongTin = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 680));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("QUẢN LÝ SẢN PHẨM");
        lblTieuDe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addGap(201, 201, 201))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jLabel2.setText("Quản lý:");

        cbxQuanLy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sách", "Đĩa Nhạc", "Đĩa Phim" }));
        cbxQuanLy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxQuanLyItemStateChanged(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin:"));

        lblTenSanPham.setText("Tên Sách:");

        lblTacGia_DaoDien_CaSi.setText("Tác Giả:");

        lblNhaXuatBan_DienVien_NhaSanXuat.setText("Nhà Xuất Bản:");

        lblNamSanXuat.setText("Năm Sản Xuất:");

        jDateChooserNamSanXuat.setBackground(new java.awt.Color(51, 204, 255));
        jDateChooserNamSanXuat.setDateFormatString("dd/MM/yyyy");

        lblTheLoai.setText("Thể Loại:");

        lblNgayNhap.setText("Ngày Nhập:");

        jDateChooserNgayNhap.setBackground(new java.awt.Color(51, 204, 255));
        jDateChooserNgayNhap.setDateFormatString("dd/MM/yyyy");

        lblGiaMua.setText("Giá Mua:");

        lblGiaBan.setText("Giá Bán:");

        lblSoLuong.setText("Số Lượng:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGiaMua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNhaXuatBan_DienVien_NhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTacGia_DaoDien_CaSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTacGia_DaoDien_Casi)
                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserNamSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(txtNhaXuatBan_DienVien_NhaSanXuat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTheLoai, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiaMua)
                    .addComponent(txtGiaBan)
                    .addComponent(txtSoLuong))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenSanPham)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTacGia_DaoDien_CaSi)
                    .addComponent(txtTacGia_DaoDien_Casi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNhaXuatBan_DienVien_NhaSanXuat)
                    .addComponent(txtNhaXuatBan_DienVien_NhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserNamSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTheLoai)
                    .addComponent(txtTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaMua)
                    .addComponent(txtGiaMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaBan)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 204, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Add-16.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnThemTuFile.setBackground(new java.awt.Color(51, 204, 255));
        btnThemTuFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Microsoft Excel-16.png"))); // NOI18N
        btnThemTuFile.setText("Thêm Từ File");

        btnSua.setBackground(new java.awt.Color(51, 204, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Edit-16.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(51, 204, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Delete-16.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamSachDuLieu.setBackground(new java.awt.Color(51, 204, 255));
        btnLamSachDuLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        btnLamSachDuLieu.setText("Làm Sạch Dữ Liệu Nhập");
        btnLamSachDuLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamSachDuLieuActionPerformed(evt);
            }
        });

        btnXemToanBo.setBackground(new java.awt.Color(51, 204, 255));
        btnXemToanBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        btnXemToanBo.setText("Xem Toàn Bộ");
        btnXemToanBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemToanBoActionPerformed(evt);
            }
        });

        btnInThongTin.setBackground(new java.awt.Color(51, 204, 255));
        btnInThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16.png"))); // NOI18N
        btnInThongTin.setText("In Thông Tin");

        jPanel5.setBackground(new java.awt.Color(51, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách:"));
        jPanel5.setLayout(new java.awt.BorderLayout());

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemTuFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnLamSachDuLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXemToanBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnInThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(btnThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemTuFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamSachDuLieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemToanBo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInThongTin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxQuanLyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxQuanLyItemStateChanged
        // TODO add your handling code here:
        int index = cbxQuanLy.getSelectedIndex();
        if (index == 0) {
            hienThiGiaoDienQuanLySach();
        } else if (index == 1) {
            hienThiGiaoDienQuanLyDiaNhac();
        } else {
            hienThiGiaoDienQuanLyDiaPhim();
        }
    }//GEN-LAST:event_cbxQuanLyItemStateChanged

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        try {
            int row = tblSanPham.getSelectedRow();
            txtTen.setText(tblSanPham.getValueAt(row, 0) + "");
            txtTacGia_DaoDien_Casi.setText(tblSanPham.getValueAt(row, 1) + "");
            txtNhaXuatBan_DienVien_NhaSanXuat.setText(tblSanPham.getValueAt(row, 2) + "");
            Date namXuatBan = sdf.parse(tblSanPham.getValueAt(row, 3) + "");
            jDateChooserNamSanXuat.setDate(namXuatBan);
            txtTheLoai.setText(tblSanPham.getValueAt(row, 4) + "");
            Date ngayNhap = sdf.parse(tblSanPham.getValueAt(row, 5) + "");
            jDateChooserNgayNhap.setDate(ngayNhap);
            String giaMua = tblSanPham.getValueAt(row, 6) + "";
            giaMua = giaMua.substring(0, giaMua.length() - 4);
            txtGiaMua.setText(giaMua);
            String giaBan = tblSanPham.getValueAt(row, 7) + "";
            giaBan = giaBan.substring(0, giaBan.length() - 4);
            txtGiaBan.setText(giaBan);
            txtSoLuong.setText(tblSanPham.getValueAt(row, 8) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int index = cbxQuanLy.getSelectedIndex();
        switch (index) {
            case 0:
                themSach();
                break;
            case 1:
                themDiaNhac();
                break;
            case 2:
                themDiaPhim();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnThemActionPerformed
    
    private void themSach() {
        try {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm sách ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan != JOptionPane.YES_OPTION) {
                return;
            }
            String tenSach = txtTen.getText();
            String tacGia = txtTacGia_DaoDien_Casi.getText();
            String nhaXuatBan = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
            Date namXuatBan = jDateChooserNamSanXuat.getDate();
            String theLoai = txtTheLoai.getText();
            Date ngayNhap = jDateChooserNgayNhap.getDate();
            int giaMua = Integer.parseInt(txtGiaMua.getText());
            int giaBan = Integer.parseInt(txtGiaBan.getText());
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            if (bllSach.themSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong) > 0) {
                JOptionPane.showMessageDialog(null, "Thêm sách thành công");
                hienThiSachLenBang(bllSach.layToanBoSach());
                lamSachDuLieuNhap();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm sách thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void themDiaNhac() {
        try {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm đĩa nhạc ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan != JOptionPane.YES_OPTION) {
                return;
            }
            String tenDia = txtTen.getText();
            String caSi = txtTacGia_DaoDien_Casi.getText();
            String nhaSanXuat = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
            Date namXuatBan = jDateChooserNamSanXuat.getDate();
            String theLoai = txtTheLoai.getText();
            Date ngayNhap = jDateChooserNgayNhap.getDate();
            int giaMua = Integer.parseInt(txtGiaMua.getText());
            int giaBan = Integer.parseInt(txtGiaBan.getText());
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            if (bllDiaNhac.themDiaNhac(tenDia, caSi, nhaSanXuat, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong) > 0) {
                JOptionPane.showMessageDialog(null, "Thêm đĩa nhạc thành công");
                hienThiDiaNhacLenBang(bllDiaNhac.layToanBoDiaNhac());
                lamSachDuLieuNhap();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm đĩa nhạc thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void themDiaPhim() {
        try {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm đĩa nhạc ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan != JOptionPane.YES_OPTION) {
                return;
            }
            String tenDia = txtTen.getText();
            String daoDien = txtTacGia_DaoDien_Casi.getText();
            String dienVien = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
            Date namXuatBan = jDateChooserNamSanXuat.getDate();
            String theLoai = txtTheLoai.getText();
            Date ngayNhap = jDateChooserNgayNhap.getDate();
            int giaMua = Integer.parseInt(txtGiaMua.getText());
            int giaBan = Integer.parseInt(txtGiaBan.getText());
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            if (bllDiaPhim.themDiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong) > 0) {
                JOptionPane.showMessageDialog(null, "Thêm đĩa phim thành công");
                hienThiDiaPhimLenBang(bllDiaPhim.layToanBoDiaPhim());
                lamSachDuLieuNhap();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm đĩa nhạc thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean kiemTraDuLieuSachNhap(String tenSach, String tacGia, String nhaXuatBan, Date namXuatBan,
            String theLoai, Date ngayNhap, String giaMua, String giaBan, String soLuong) {
        String regex1 = "\\s*";
        String regex2 = "\\d+";
        if (tenSach.matches(regex1) && tacGia.matches(regex1) && nhaXuatBan.matches(regex1) && namXuatBan == null && theLoai.matches(regex1)
                && ngayNhap == null && giaMua.matches(regex1) && giaBan.matches(regex1) && soLuong.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return false;
        } else if (tenSach.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên sách");
            return false;
        } else if (tacGia.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tác giả");
            return false;
        } else if (nhaXuatBan.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập nhà xuất bản");
            return false;
        } else if (namXuatBan == null) {
            JOptionPane.showMessageDialog(null, "Chưa chọn năm xuất bản");
            return false;
        } else if (theLoai.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập thể loại");
            return false;
        } else if (ngayNhap == null) {
            JOptionPane.showMessageDialog(null, "Chưa chọn ngày nhập");
            return false;
        } else if (giaMua.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập giá mua");
            return false;
        } else if (giaBan.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập giá bán");
            return false;
        } else if (soLuong.matches(regex1)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số lượng");
            return false;
        }
        return true;
    }
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int[] rows = tblSanPham.getSelectedRows();
        if (rows.length < 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cần xóa");
        } else {
            int index = cbxQuanLy.getSelectedIndex();
            switch (index) {
                case 0:
                    xoaSach(rows);
                    break;
                case 1:
                    xoaDiaNhac(rows);
                    break;
                case 2:
                    xoaDiaPhim(rows);
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Chọn sản phẩm cần sửa");
        } else {
            int index = cbxQuanLy.getSelectedIndex();
            switch (index) {
                case 0:
                    suaSach(row);
                    break;
                case 1:
                    suaDiaNhac(row);
                    break;
                case 2:
                    suaDiaPhim(row);
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        int index = cbxQuanLy.getSelectedIndex();
        switch (index) {
            case 0:
                timSach();
                break;
            case 1:
                timDiaNhac();
                break;
            case 2:
                timDiaPhim();
                break;
            default:
                break;
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed
    
    private void timSach() {
        String tenSach = txtTen.getText();
        String tacGia = txtTacGia_DaoDien_Casi.getText();
        String nhaXuatBan = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
        Date namXuatBan = jDateChooserNamSanXuat.getDate();
        String theLoai = txtTheLoai.getText();
        Date ngayNhap = jDateChooserNgayNhap.getDate();
        Integer giaMua = TryParse(txtGiaMua.getText());
        Integer giaBan = TryParse(txtGiaBan.getText());
        Integer soLuong = TryParse(txtSoLuong.getText());
        hienThiSachLenBang(bllSach.timKiemSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, true));
    }
    
    private void timDiaNhac() {
        String tenDia = txtTen.getText();
        String caSi = txtTacGia_DaoDien_Casi.getText();
        String nhaSanXuat = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
        Date namXuatBan = jDateChooserNamSanXuat.getDate();
        String theLoai = txtTheLoai.getText();
        Date ngayNhap = jDateChooserNgayNhap.getDate();
        Integer giaMua = TryParse(txtGiaMua.getText());
        Integer giaBan = TryParse(txtGiaBan.getText());
        Integer soLuong = TryParse(txtSoLuong.getText());
        hienThiDiaNhacLenBang(bllDiaNhac.timKiemDiaNhac(tenDia, caSi, nhaSanXuat, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong));
    }
    
    private void timDiaPhim() {
        String tenDia = txtTen.getText();
        String daoDien = txtTacGia_DaoDien_Casi.getText();
        String dienVien = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
        Date namXuatBan = jDateChooserNamSanXuat.getDate();
        String theLoai = txtTheLoai.getText();
        Date ngayNhap = jDateChooserNgayNhap.getDate();
        Integer giaMua = TryParse(txtGiaMua.getText());
        Integer giaBan = TryParse(txtGiaBan.getText());
        Integer soLuong = TryParse(txtSoLuong.getText());
        hienThiDiaPhimLenBang(bllDiaPhim.timKiemDiaPhim(tenDia, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong, true));
    }

    private void btnLamSachDuLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamSachDuLieuActionPerformed
        // TODO add your handling code here:
        lamSachDuLieuNhap();
    }//GEN-LAST:event_btnLamSachDuLieuActionPerformed

    private void btnXemToanBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemToanBoActionPerformed
        // TODO add your handling code here:
        int index = cbxQuanLy.getSelectedIndex();
        switch (index) {
            case 0:
                hienThiSachLenBang(bllSach.layToanBoSach());
                break;
            case 1:
                hienThiDiaNhacLenBang(bllDiaNhac.layToanBoDiaNhac());
                break;
            case 2:
                hienThiDiaPhimLenBang(bllDiaPhim.layToanBoDiaPhim());
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnXemToanBoActionPerformed
    
    private void lamSachDuLieuNhap() {
        txtTen.setText("");
        txtTacGia_DaoDien_Casi.setText("");
        txtNhaXuatBan_DienVien_NhaSanXuat.setText("");
        jDateChooserNamSanXuat.setDate(null);
        txtTheLoai.setText("");
        jDateChooserNgayNhap.setDate(null);
        txtGiaMua.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("");
    }
    
    private Integer TryParse(String s) {
        if (s.matches("\\s*")) {
            return null;
        } else {
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    
    private void suaSach(int index) {
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa sách ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }
        String tenSach = txtTen.getText();
        String tacGia = txtTacGia_DaoDien_Casi.getText();
        String nhaXuatBan = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
        Date namXuatBan = jDateChooserNamSanXuat.getDate();
        String theLoai = txtTheLoai.getText();
        Date ngayNhap = jDateChooserNgayNhap.getDate();
        int giaMua = Integer.parseInt(txtGiaMua.getText());
        int giaBan = Integer.parseInt(txtGiaBan.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        if (bllSach.suaSach(tenSach, tacGia, nhaXuatBan, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong) > 0) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
            hienThiSachLenBang(bllSach.layToanBoSach());
            lamSachDuLieuNhap();
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }
    
    private void suaDiaNhac(int index) {
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa đĩa nhạc ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }
        String tenDia = txtTen.getText();
        String caSi = txtTacGia_DaoDien_Casi.getText();
        String nhaSanXuat = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
        Date namXuatBan = jDateChooserNamSanXuat.getDate();
        String theLoai = txtTheLoai.getText();
        Date ngayNhap = jDateChooserNgayNhap.getDate();
        int giaMua = Integer.parseInt(txtGiaMua.getText());
        int giaBan = Integer.parseInt(txtGiaBan.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        if (bllDiaNhac.suaDiaNhac(tenDia, caSi, nhaSanXuat, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong) > 0) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
            hienThiDiaNhacLenBang(bllDiaNhac.layToanBoDiaNhac());
            lamSachDuLieuNhap();
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }
    
    private void suaDiaPhim(int index) {
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa đĩa phim ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }
        String tenSach = txtTen.getText();
        String daoDien = txtTacGia_DaoDien_Casi.getText();
        String dienVien = txtNhaXuatBan_DienVien_NhaSanXuat.getText();
        Date namXuatBan = jDateChooserNamSanXuat.getDate();
        String theLoai = txtTheLoai.getText();
        Date ngayNhap = jDateChooserNgayNhap.getDate();
        int giaMua = Integer.parseInt(txtGiaMua.getText());
        int giaBan = Integer.parseInt(txtGiaBan.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        if (bllDiaPhim.suaDiaPhim(tenSach, daoDien, dienVien, namXuatBan, theLoai, ngayNhap, giaMua, giaBan, soLuong) > 0) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
            hienThiDiaPhimLenBang(bllDiaPhim.layToanBoDiaPhim());
            lamSachDuLieuNhap();
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }
    
    private void xoaSach(int[] arr) {
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sách ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }
        int count = 0;
        for (int i : arr) {
            String tenSach = tblSanPham.getValueAt(i, 0) + "";
            bllSach.xoaSach(tenSach);
            count++;
        }
        JOptionPane.showMessageDialog(null, "Xóa thành công " + count + " sách");
        hienThiSachLenBang(bllSach.layToanBoSach());
        lamSachDuLieuNhap();
    }
    
    private void xoaDiaNhac(int[] arr) {
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa đĩa nhạc ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }
        int count = 0;
        for (int i : arr) {
            String tendia = tblSanPham.getValueAt(i, 0) + "";
            bllDiaNhac.xoaDiaNhac(tendia);
            count++;
        }
        JOptionPane.showMessageDialog(null, "Xóa thành công " + count + " đĩa nhạc");
        hienThiDiaNhacLenBang(bllDiaNhac.layToanBoDiaNhac());
        lamSachDuLieuNhap();
    }
    
    private void xoaDiaPhim(int[] arr) {
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa đĩa phim ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }
        int count = 0;
        for (int i : arr) {
            String tendia = tblSanPham.getValueAt(i, 0) + "";
            bllDiaPhim.xoaDiaPhim(tendia);
            count++;
        }
        JOptionPane.showMessageDialog(null, "Xóa thành công " + count + " đĩa phim");
        hienThiDiaPhimLenBang(bllDiaPhim.layToanBoDiaPhim());
        lamSachDuLieuNhap();
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
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInThongTin;
    private javax.swing.JButton btnLamSachDuLieu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemTuFile;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemToanBo;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxQuanLy;
    private com.toedter.calendar.JDateChooser jDateChooserNamSanXuat;
    private com.toedter.calendar.JDateChooser jDateChooserNgayNhap;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaMua;
    private javax.swing.JLabel lblNamSanXuat;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblNhaXuatBan_DienVien_NhaSanXuat;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTacGia_DaoDien_CaSi;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaMua;
    private javax.swing.JTextField txtNhaXuatBan_DienVien_NhaSanXuat;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTacGia_DaoDien_Casi;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTheLoai;
    // End of variables declaration//GEN-END:variables
}
