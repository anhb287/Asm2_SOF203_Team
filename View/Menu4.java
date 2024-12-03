/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import FormCuaUsers.ConnectorHelper;
import FormCuaUsers.model.ThongTinLichHoc;
import FormCuaUsers.model.ThongTinSinhVien;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class Menu4 extends  javax.swing.JInternalFrame {

    static JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    List<ThongTinLichHoc> listttlh = new ArrayList<>();
    private int index = -1;
    public Menu4() {
        initComponents();
         // Khởi tạo cho JInternalFrame
        setResizable(true);
        setTitle("Quản Lý Lịch Học");
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        fillToTableLichHoc();
        refreshListLichHoc();
        
    }
    public void fillToTableLichHoc(){
    DefaultTableModel model = (DefaultTableModel) tblThongTinLichHoc.getModel();
    model.setRowCount(0);
    for (ThongTinLichHoc lh : listttlh) {
        model.addRow(new Object[]{lh.getTenSinhVienLichHoc(), 
            lh.getMaMonHocLichHoc(), lh.getTenMonHocLichHoc(), lh.getPhongHocLichHoc(), 
            lh.getLopHocLichHoc(), lh.getGhiChuLichHoc()});
    }
}

public void FillToTableLichHoc(ThongTinLichHoc lh){
    txtTenSinhVien.setText(lh.getTenSinhVienLichHoc());
    txtMaMonHocThongTInLichHoc.setText(lh.getMaMonHocLichHoc());
    txtTenMonHocThongTinLichHoc.setText(lh.getTenMonHocLichHoc());
    txtPhongHoc.setText(lh.getPhongHocLichHoc());
    txtLopHoc.setText(lh.getLopHocLichHoc());
    areaGhiChuThongTinLichHoc.setText(lh.getGhiChuLichHoc());
    fillToTableLichHoc();
    JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Lịch Học Thành Công!>");
}

public void refreshListLichHoc() {
    listttlh.clear();
    try {
        Connection conn = ConnectorHelper.connection();
        String SQL = "SELECT TenSV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu FROM lichhoc";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String TenSV = rs.getString("TenSV");
            String MaMonHoc = rs.getString("MaMonHoc");
            String TenMonHoc = rs.getString("TenMonHoc");
            String PhongHoc = rs.getString("PhongHoc");
            String LopHoc = rs.getString("LopHoc");
            String GhiChu = rs.getString("GhiChu");
            listttlh.add(new ThongTinLichHoc(TenSV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu));
        }
        pstm.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    public void updateLichHoc() {
    if (txtTenSinhVien.getText().length() <= 0 || 
        txtMaMonHocThongTInLichHoc.getText().length() <= 0 || txtTenMonHocThongTinLichHoc.getText().length() <= 0 || 
        txtPhongHoc.getText().length() <= 0 || txtLopHoc.getText().length() <= 0 || 
        areaGhiChuThongTinLichHoc.getText().length() <= 0) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        String TenSV = txtTenSinhVien.getText();
        String MaMonHoc = txtMaMonHocThongTInLichHoc.getText();
        String TenMonHoc = txtTenMonHocThongTinLichHoc.getText();
        String PhongHoc = txtPhongHoc.getText();
        String LopHoc = txtLopHoc.getText();
        String GhiChu = areaGhiChuThongTinLichHoc.getText();
        String SQL = "UPDATE lichhoc SET TenSV = ?, MaMonHoc = ?, TenMonHoc = ?, PhongHoc = ?, LopHoc = ?, GhiChu = ? WHERE MaMonHoc = ?";

        try (Connection conn = ConnectorHelper.connection(); 
             PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setString(1, TenSV);
            pstm.setString(2, MaMonHoc);
            pstm.setString(3, TenMonHoc);
            pstm.setString(4, PhongHoc);
            pstm.setString(5, LopHoc);
            pstm.setString(6, GhiChu);
            pstm.setString(7, MaMonHoc);
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy lịch học với MaMonHoc: " + TenSV, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }
        public void saveLichHoc() {
        if (txtTenSinhVien.getText().length() <= 0 || 
            txtMaMonHocThongTInLichHoc.getText().length() <= 0 || 
            txtTenMonHocThongTinLichHoc.getText().length() <= 0 || 
            txtPhongHoc.getText().length() <= 0 || 
            txtLopHoc.getText().length() <= 0 || 
            areaGhiChuThongTinLichHoc.getText().length() <= 0) {

            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tenSV = txtTenSinhVien.getText();
        String maMonHoc = txtMaMonHocThongTInLichHoc.getText();
        String tenMonHoc = txtTenMonHocThongTinLichHoc.getText();
        String phongHoc = txtPhongHoc.getText();
        String lopHoc = txtLopHoc.getText();
        String ghiChu = areaGhiChuThongTinLichHoc.getText();

        String sql = "INSERT INTO lichhoc (TenSV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ConnectorHelper.connection(); 
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, tenSV);
            pstm.setString(2, maMonHoc);
            pstm.setString(3, tenMonHoc);
            pstm.setString(4, phongHoc);
            pstm.setString(5, lopHoc);
            pstm.setString(6, ghiChu);
            
            int rowsAffected = pstm.executeUpdate();  // Kiểm tra số hàng bị ảnh hưởng
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Thêm mới thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            fillToTableLichHoc();
            refreshListLichHoc();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm mới không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();  // In chi tiết lỗi ra console
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm mới dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void XoaLichHoc(){
    try {
        listttlh.remove(index);
        deleteLichHoc();
        fillToTableLichHoc();
        refreshListLichHoc();
        clearFormLichHoc();
        JOptionPane.showMessageDialog(this, "Đã Xóa Lịch Học Mà Bạn Chọn");
    } catch(IndexOutOfBoundsException e){
        JOptionPane.showMessageDialog(this, "Vui lòng chọn đối tượng bạn muốn xóa");
    }
    }

    public void deleteLichHoc() {
    if (txtTenSinhVien.getText().length() <= 0) {
        JOptionPane.showMessageDialog(null, "Chưa nhập mã Môn học", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        String MaMonHoc = txtMaMonHocThongTInLichHoc.getText();
        String SQL = "DELETE FROM lichhoc WHERE MaMonHoc = ?";

        try (Connection conn = ConnectorHelper.connection(); 
             PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setString(1, MaMonHoc);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }

    public void FillToTableLichHoc(int index){
    txtTenSinhVien.setText(listttlh.get(index).getTenSinhVienLichHoc());
    txtLopHoc.setText(listttlh.get(index).getLopHocLichHoc());
    txtMaMonHocThongTInLichHoc.setText(listttlh.get(index).getMaMonHocLichHoc());
    txtTenMonHocThongTinLichHoc.setText(listttlh.get(index).getTenMonHocLichHoc());
    txtPhongHoc.setText(listttlh.get(index).getPhongHocLichHoc());
    areaGhiChuThongTinLichHoc.setText(listttlh.get(index).getGhiChuLichHoc());
    }

    public void clearFormLichHoc(){
    txtTenSinhVien.setText(null);
    txtLopHoc.setText(null);
    txtMaMonHocThongTInLichHoc.setText(null);
    txtTenMonHocThongTinLichHoc.setText(null);
    txtTenMonHocThongTinLichHoc.setText(null);
    txtPhongHoc.setText(null);
    areaGhiChuThongTinLichHoc.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel14 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtMaMonHocThongTInLichHoc = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtPhongHoc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTenSinhVien = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTenMonHocThongTinLichHoc = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtLopHoc = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaGhiChuThongTinLichHoc = new javax.swing.JTextArea();
        btnThemThongTinLichHoc = new javax.swing.JButton();
        btnXoaThongTinLichHoc = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblThongTinLichHoc = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(730, 460));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Quản Lý Lịch Học");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("Mã Môn Học");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        txtMaMonHocThongTInLichHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaMonHocThongTInLichHocActionPerformed(evt);
            }
        });
        getContentPane().add(txtMaMonHocThongTInLichHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 140, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 102));
        jLabel26.setText("Phòng Học");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, -1, -1));

        txtPhongHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhongHocActionPerformed(evt);
            }
        });
        getContentPane().add(txtPhongHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 80, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("Tên sinh viên");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtTenSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSinhVienActionPerformed(evt);
            }
        });
        getContentPane().add(txtTenSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 102));
        jLabel25.setText("Tên Môn Học");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, -1));

        txtTenMonHocThongTinLichHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenMonHocThongTinLichHocActionPerformed(evt);
            }
        });
        getContentPane().add(txtTenMonHocThongTinLichHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 90, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 102));
        jLabel27.setText("Lớp Học");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        txtLopHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLopHocActionPerformed(evt);
            }
        });
        getContentPane().add(txtLopHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 80, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 102));
        jLabel28.setText("Ghi Chú");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        areaGhiChuThongTinLichHoc.setColumns(20);
        areaGhiChuThongTinLichHoc.setRows(5);
        jScrollPane5.setViewportView(areaGhiChuThongTinLichHoc);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 290, 90));

        btnThemThongTinLichHoc.setBackground(new java.awt.Color(153, 204, 255));
        btnThemThongTinLichHoc.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        btnThemThongTinLichHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Đăng_Kí.png"))); // NOI18N
        btnThemThongTinLichHoc.setText("Thêm");
        btnThemThongTinLichHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThongTinLichHocActionPerformed(evt);
            }
        });
        getContentPane().add(btnThemThongTinLichHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 120, 40));

        btnXoaThongTinLichHoc.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaThongTinLichHoc.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        btnXoaThongTinLichHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/12319540.png"))); // NOI18N
        btnXoaThongTinLichHoc.setText("Xóa");
        btnXoaThongTinLichHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThongTinLichHocActionPerformed(evt);
            }
        });
        getContentPane().add(btnXoaThongTinLichHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 120, 40));

        btnThoat.setBackground(new java.awt.Color(255, 102, 102));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/image-removebg-preview.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        getContentPane().add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 120, 40));

        tblThongTinLichHoc.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblThongTinLichHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên sinh viên", "Mã Môn", "Tên Môn Học", "Phòng Học", "Lớp Học", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinLichHoc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblThongTinLichHocAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblThongTinLichHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinLichHocMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblThongTinLichHoc);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 700, 190));

        btnReset.setBackground(new java.awt.Color(51, 204, 255));
        btnReset.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear2.png"))); // NOI18N
        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, 40));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        getContentPane().add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 80, 30));

        jButton2.setText("Sửa");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nền.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaMonHocThongTInLichHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaMonHocThongTInLichHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaMonHocThongTInLichHocActionPerformed

    private void txtPhongHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhongHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhongHocActionPerformed

    private void txtTenSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSinhVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSinhVienActionPerformed

    private void txtTenMonHocThongTinLichHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenMonHocThongTinLichHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMonHocThongTinLichHocActionPerformed

    private void txtLopHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLopHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLopHocActionPerformed

    private void btnThemThongTinLichHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThongTinLichHocActionPerformed
       saveLichHoc();
       fillToTableLichHoc();
    }//GEN-LAST:event_btnThemThongTinLichHocActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnXoaThongTinLichHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThongTinLichHocActionPerformed
        XoaLichHoc();
    }//GEN-LAST:event_btnXoaThongTinLichHocActionPerformed

    private void tblThongTinLichHocAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblThongTinLichHocAncestorAdded
        fillToTableLichHoc();
    }//GEN-LAST:event_tblThongTinLichHocAncestorAdded

    private void tblThongTinLichHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinLichHocMouseClicked
        index = tblThongTinLichHoc.getSelectedRow();
        FillToTableLichHoc(index);
    }//GEN-LAST:event_tblThongTinLichHocMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtTenSinhVien.setText("");
        txtLopHoc.setText("");
        txtMaMonHocThongTInLichHoc.setText("");
        txtTenMonHocThongTinLichHoc.setText("");
        txtPhongHoc.setText("");
        areaGhiChuThongTinLichHoc.setText("");
        JOptionPane.showMessageDialog(this, "Form đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String maMonHoc = txtMaMonHocThongTInLichHoc.getText().trim(); // Lấy mã môn học từ ô nhập liệu
        if (maMonHoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã Môn Học để tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConnectorHelper.connection()) {
            String SQL = "SELECT TenSV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu FROM lichhoc WHERE MaMonHoc = ?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, maMonHoc);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                // Hiển thị dữ liệu lên form
                txtTenSinhVien.setText(rs.getString("TenSV"));
                txtMaMonHocThongTInLichHoc.setText(rs.getString("MaMonHoc"));
                txtTenMonHocThongTinLichHoc.setText(rs.getString("TenMonHoc"));
                txtPhongHoc.setText(rs.getString("PhongHoc"));
                txtLopHoc.setText(rs.getString("LopHoc"));
                areaGhiChuThongTinLichHoc.setText(rs.getString("GhiChu"));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy lịch học với Mã Môn Học: " + maMonHoc, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            pstm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTimActionPerformed

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
            java.util.logging.Logger.getLogger(Menu4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaGhiChuThongTinLichHoc;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThemThongTinLichHoc;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaThongTinLichHoc;
    private javax.swing.JButton jButton2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblThongTinLichHoc;
    private javax.swing.JTextField txtLopHoc;
    private javax.swing.JTextField txtMaMonHocThongTInLichHoc;
    private javax.swing.JTextField txtPhongHoc;
    private javax.swing.JTextField txtTenMonHocThongTinLichHoc;
    private javax.swing.JTextField txtTenSinhVien;
    // End of variables declaration//GEN-END:variables
}
