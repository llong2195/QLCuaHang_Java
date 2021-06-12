/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Process.CTHoaDon;
import Process.HoaDon;
import Process.KhachHang;
import Process.MonAn;
import Process.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author llong
 */
public class panelBanHang extends javax.swing.JPanel {
    MonAn MA = new MonAn();
    NhanVien NV = new NhanVien();
    KhachHang KH = new KhachHang();
    HoaDon HD = new HoaDon();
    CTHoaDon CTHD = new CTHoaDon();
    // TB mon an 
    final DefaultTableModel tableModelMonAn = new DefaultTableModel();
    // TB hóa đơn : ds món ăn trong hóa đơn
    final DefaultTableModel tableModelMonAnHD = new DefaultTableModel();
    String MaHD;

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }
    /**
     * Creates new form panelBanHang
     */
    public panelBanHang() {
        initComponents();
        String []ColsnameMonAN = {"Mã Món","Tên Món", "Đơn Giá", "Loại"};
        tableModelMonAn.setColumnIdentifiers(ColsnameMonAN);
        tbMonAn.setModel(tableModelMonAn);
        
        String []ColsnameHD = {"Mã Món","Tên Món", "Đơn Giá", "Số Lượng", "Thành Tiền"};
        tableModelMonAnHD.setColumnIdentifiers(ColsnameHD);
        tbCTHD.setModel(tableModelMonAnHD);
        ShowcbbNV();
        ShowcbbKH();
        LocalDate dateNow = LocalDate.now();
        txtNgayHD.setText(dateNow.toString());
        
    }
    
    private void ShowcbbNV(){
        ResultSet rs = null;
        try {
            rs = NV.getData();
            while(rs.next()){
                cbbNhanVien.addItem(rs.getString("TenNV"));
            }
        } catch (Exception e) {
        }
    }
    private void ShowcbbKH(){
        ResultSet rs = null;
        try {
            rs = KH.getData();
            while(rs.next()){
                cbbKhachHang.addItem(rs.getString("TenKH"));
            }
        } catch (Exception e) {
        }
    }
    
    private void ShowData(){
        try {
            ShowTBMonAn();
            ShowTBMonAnHD();
            ResultSet rs = CTHD.getData(getMaHD());
            System.out.println(getMaHD());
            if(rs.next()){
                lbTongTien.setText(rs.getString("TongTien"));
                System.out.println("getTong Tien : " + rs.getString("TongTien"));
            }
        } catch (Exception ex) {
            Logger.getLogger(panelBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clearText(){
        txtMaMon.setText("");
        txtTenMon.setText("");
        txtDonGia.setText("");
        cbbLoai.setSelectedIndex(0);
        txtTimKiem.setText("");
        spSoLuong.setValue(0);
    }
    // Danh Sách Món ăn trogn hóa đơn
    private void ShowTBMonAn(){
        ResultSet rs = null;
        try {
            rs = MA.getData();
            while(rs.next()){
                String rows[] = new String[4];
                rows[0] = rs.getString("MaMon");
                rows[1] = rs.getString("TenMon");
                rows[2] = rs.getString("DonGia");
                rows[3] = rs.getString("Loai");
                tableModelMonAn.addRow(rows);
            }
        } catch (Exception e) {
            System.out.println("Err : " + e.getMessage());
        }
    }
     private void ShowTBMonAn(String Ten){
        
        ResultSet rs = null;
        try {
            if(Ten.equals("ThucAn") || Ten.equals("DoUong")){
                rs = MA.SearchData_Loai(Ten);
            }
            else{
                rs = MA.SearchData_Ten(Ten);
            }
            clearTBMonAn();
            while(rs.next()){
                String rows[] = new String[4];
                rows[0] = rs.getString("MaMon");
                rows[1] = rs.getString("TenMon");
                rows[2] = rs.getString("DonGia");
                rows[3] = rs.getString("Loai");
                tableModelMonAn.addRow(rows);
            }
        } catch (Exception e) {
            System.out.println("Err : " + e.getMessage());
        }
    }
    
    private void ShowTBMonAnHD(){
        ResultSet rs = null;
        try {
            rs = CTHD.getMonAnHD(getMaHD());
            while(rs.next()){
                String rows[] = new String[5];
                rows[0] = rs.getString("MaMon");
                rows[1] = rs.getString("TenMon");
                rows[2] = rs.getString("DonGia");
                rows[3] = rs.getString("SoLuong");
                rows[4] = rs.getString("ThanhTien");
                tableModelMonAnHD.addRow(rows);
            }
        } catch (Exception e) {
            System.out.println("Err : " + e.getMessage());
        }
    }
    private void clearTBMonAn(){
        int n = tableModelMonAn.getRowCount()-1;
        while(n>=0){
            tableModelMonAn.removeRow(n--);
        }
    }
    private void clearTBMonAnHD(){
        int n = tableModelMonAnHD.getRowCount()-1;
        while(n>=0){
            tableModelMonAnHD.removeRow(n--);
        }
    }

    private void clearData(){
        clearText();
        clearTBMonAn();
        clearTBMonAnHD();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbLoai = new javax.swing.JComboBox<>();
        txtMaMon = new javax.swing.JTextField();
        txtTenMon = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        spSoLuong = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        btnTaoHD = new javax.swing.JButton();
        btnThemMon = new javax.swing.JButton();
        btnXoaMon = new javax.swing.JButton();
        pnHoaDon = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTHD = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMonAn = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        cbbLoaiMon = new javax.swing.JComboBox<>();
        btnTimkiem = new javax.swing.JButton();
        pnThongTin = new javax.swing.JPanel();
        cbbNhanVien = new javax.swing.JComboBox<>();
        txtNgayHD = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cbbLoaiKhach = new javax.swing.JComboBox<>();
        lbMaNV = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbKhachHang = new javax.swing.JComboBox<>();
        lbMaKH = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setMaximumSize(new java.awt.Dimension(1040, 610));
        setMinimumSize(new java.awt.Dimension(1040, 610));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel1.setText("Mã Món :");

        jLabel2.setText("Tên Món :");

        jLabel3.setText("Đơn Giá :");

        jLabel4.setText("Loại :");

        cbbLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thức Ăn", "Đồ Uống" }));
        cbbLoai.setEnabled(false);

        txtMaMon.setEnabled(false);

        txtTenMon.setEnabled(false);

        txtDonGia.setEnabled(false);

        jLabel5.setText("Số Lượng :");

        spSoLuong.setToolTipText("");
        spSoLuong.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnTaoHD.setText("Tạo Hóa Đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnThemMon.setText("Thêm Món");
        btnThemMon.setEnabled(false);
        btnThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonActionPerformed(evt);
            }
        });

        btnXoaMon.setText("Xóa Món");
        btnXoaMon.setEnabled(false);
        btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnThemMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoHD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnTaoHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemMon)
                .addGap(44, 44, 44)
                .addComponent(btnXoaMon)
                .addGap(21, 21, 21))
        );

        pnHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));

        tbCTHD.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCTHD);

        jLabel13.setText("Tổng Tiên :");

        lbTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTongTien.setText("0");

        jLabel14.setText("VND");

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnHoaDonLayout = new javax.swing.GroupLayout(pnHoaDon);
        pnHoaDon.setLayout(pnHoaDonLayout);
        pnHoaDonLayout.setHorizontalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoaDonLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnHoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoaDonLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnReset)
                                .addGap(63, 63, 63)))))
                .addContainerGap())
        );
        pnHoaDonLayout.setVerticalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lbTongTien)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(btnReset)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Món Ăn"));

        tbMonAn.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMonAn);

        cbbLoaiMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "Thức Ăn", "Đồ Uống" }));
        cbbLoaiMon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiMonItemStateChanged(evt);
            }
        });

        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimkiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        cbbNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNhanVienItemStateChanged(evt);
            }
        });

        txtNgayHD.setEnabled(false);

        txtSDT.setEnabled(false);

        cbbLoaiKhach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách Lẻ", "Khách Quen" }));
        cbbLoaiKhach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiKhachItemStateChanged(evt);
            }
        });

        lbMaNV.setText(" ");

        jLabel10.setText("Ngày HD :");

        jLabel9.setText("SDT :");

        jLabel6.setText("Nhân Viên :");

        jLabel7.setText("Tên KH :");

        cbbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách Lẻ" }));
        cbbKhachHang.setEnabled(false);
        cbbKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbKhachHangItemStateChanged(evt);
            }
        });

        lbMaKH.setText("TMP");

        jLabel12.setText("Mã KH :");

        jLabel8.setText("Mã Hóa Đơn :");

        jLabel11.setText("Mã NV :");

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(21, 21, 21)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayHD)
                    .addComponent(lbMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(cbbLoaiKhach, 0, 111, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnThongTinLayout.setVerticalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLoaiKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMaNV)
                    .addComponent(jLabel11))
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lbMaKH))))
                .addGap(18, 18, 18)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtNgayHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        // TODO add your handling code here:
        String TenMon = txtTimKiem.getText();
        if(TenMon.length() == 0){
            JOptionPane.showMessageDialog(this, "Không được bỏ trống thông tin");
                return;
        }
        clearTBMonAn();
        ShowTBMonAn(TenMon);
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void tbMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonAnMouseClicked
        // TODO add your handling code here:
        try {
            int row = tbMonAn.getSelectedRow();
            String ma = tbMonAn.getModel().getValueAt(row, 0).toString();
            ResultSet rs = MA.getData(ma);
            if(rs.next()){
                txtMaMon.setText(rs.getString("MaMon"));
                txtTenMon.setText(rs.getString("TenMon"));
                txtDonGia.setText(rs.getString("DonGia"));
                cbbLoai.setSelectedItem(rs.getString("Loai").equals("ThucAn") ? "Thức Ăn" : "Đồ Uống");
                txtMaMon.setEnabled(false);
            }
            btnThemMon.setEnabled(true);
            btnXoaMon.setEnabled(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbMonAnMouseClicked

    private void cbbLoaiKhachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiKhachItemStateChanged
        // TODO add your handling code here:
        if(cbbLoaiKhach.getSelectedItem().equals("Khách Lẻ")){
            String TenNV = cbbKhachHang.getSelectedItem().toString();
            cbbKhachHang.setEnabled(false);
            cbbKhachHang.addItem("Khách Lẻ");
            cbbKhachHang.setSelectedItem("Khách Lẻ");
        }else{
            cbbKhachHang.setEnabled(true);
        }
        
    }//GEN-LAST:event_cbbLoaiKhachItemStateChanged

    private void cbbNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNhanVienItemStateChanged
        // TODO add your handling code here:
        ResultSet rs= null;
        try {
            String TenNV = cbbNhanVien.getSelectedItem().toString();
            rs = NV.getData_Name(TenNV);
            if(rs.next()){
                lbMaNV.setText(rs.getString("MaNV"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbNhanVienItemStateChanged

    private void cbbKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbKhachHangItemStateChanged
        // TODO add your handling code here:
        ResultSet rs= null;
        try {
            String TenKH = cbbKhachHang.getSelectedItem().toString();
            rs = KH.getData_Name(TenKH);
            if(rs.next()){
                lbMaKH.setText(rs.getString("MaKH"));
                txtSDT.setText(rs.getString("SDT"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbKhachHangItemStateChanged

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        // TODO add your handling code here:
        try {
            String MaHD = txtMaHD.getText();
            String MaNV = lbMaNV.getText();
            String MaKH = lbMaKH.getText();
            String NgayHD = txtNgayHD.getText();
            if(MaHD.length() == 0 || MaNV.length() == 0 || MaKH.length() == 0){
                JOptionPane.showMessageDialog(this, "Không được bỏ trống thông tin");
                return;
            }
            setMaHD(MaHD);
            HD.InsertData(MaHD, MaNV, MaKH, NgayHD);
            btnTaoHD.setEnabled(false);
            txtMaHD.setEnabled(false);
            cbbKhachHang.setEnabled(false);
            cbbLoaiKhach.setEnabled(false);
            cbbNhanVien.setEnabled(false);
            ShowTBMonAn();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có Lỗi Sảy Ra!");
        }
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void tbCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTHDMouseClicked
        // TODO add your handling code here:
        try {
            int row = tbCTHD.getSelectedRow();
            String ma = tbCTHD.getModel().getValueAt(row, 0).toString();
            ResultSet rs = CTHD.getOneMonAnHD(getMaHD(), ma);
            if(rs.next()){
                txtMaMon.setText(rs.getString("MaMon"));
                txtTenMon.setText(rs.getString("TenMon"));
                txtDonGia.setText(rs.getString("DonGia"));
                spSoLuong.setValue(rs.getInt("SoLuong"));
                cbbLoai.setSelectedItem(rs.getString("Loai").equals("ThucAn") ? "Thức Ăn" : "Đồ Uống");
            }
            btnThemMon.setEnabled(false);
            btnXoaMon.setEnabled(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbCTHDMouseClicked

    private void btnThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonActionPerformed
        // TODO add your handling code here:
        try {
            String MaMon = txtMaMon.getText();
            int sl = Integer.parseInt(spSoLuong.getValue().toString());
            if(sl <= 0){
                JOptionPane.showMessageDialog(this, "Số Lượng Phải Lớn Hơn 0 !");
                return;
            }
            String soluong = ""+sl;
            CTHD.InsertData(getMaHD(), MaMon, soluong);
            clearData();
            ShowData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có Lỗi Xảy Ra !");
        }
        
    }//GEN-LAST:event_btnThemMonActionPerformed

    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        // TODO add your handling code here:
        try {
            String MaMon = txtMaMon.getText();
            if(MaMon.length() == 0){
                JOptionPane.showMessageDialog(this, "Chọn Món Cần Xóa !");
                return;
            }
            CTHD.DeleteData(getMaHD(), MaMon);
            clearData();
            ShowData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có Lỗi Xảy Ra !");
        }
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this, "Chắc Chắn Hoàn Thành Hóa Đơn Này ?") == JOptionPane.YES_OPTION){
            clearData();
        btnTaoHD.setEnabled(true);
        btnXoaMon.setEnabled(false);
        btnThemMon.setEnabled(false);
        txtMaHD.setEnabled(true);
        cbbLoaiKhach.setEnabled(true);
        cbbNhanVien.setEnabled(true);
        setMaHD("");
        txtMaHD.setText("");
        txtSDT.setText("");
        lbTongTien.setText("");
        }
        
    }//GEN-LAST:event_btnResetActionPerformed

    private void cbbLoaiMonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiMonItemStateChanged
        try {
            // TODO add your handling code here:
            String Loai = (String) cbbLoaiMon.getSelectedItem();
            if(Loai.equals("ALL")){
                clearTBMonAn();
                ShowTBMonAn();
            }else{
                Loai = Loai.equals("Thức Ăn") ? "ThucAn" : "DoUong";
                clearTBMonAn();
                System.out.println(Loai);
                ShowTBMonAn(Loai);
            }
        } catch (Exception ex) {
            Logger.getLogger(panelBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbbLoaiMonItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JComboBox<String> cbbLoai;
    private javax.swing.JComboBox<String> cbbLoaiKhach;
    private javax.swing.JComboBox<String> cbbLoaiMon;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JPanel pnHoaDon;
    private javax.swing.JPanel pnThongTin;
    private javax.swing.JSpinner spSoLuong;
    private javax.swing.JTable tbCTHD;
    private javax.swing.JTable tbMonAn;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaMon;
    private javax.swing.JTextField txtNgayHD;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenMon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
