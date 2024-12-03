/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import FormCuaUsers.ConnectorHelper;
import FormCuaUsers.model.ThongTinGiangVien;
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

/**
 *
 * @author ADMIN
 */
public class Menu2 extends  javax.swing.JInternalFrame {

    private List<ThongTinGiangVien> listttgv = new ArrayList<>();
    private int index = -1;
   public Menu2() {
        initComponents();
        setResizable(true);
        setTitle("Quản Lý Giảng Viên");
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        fillToTableGiangVien();
        refreshListGiangVien();
    }
    // Phương thức trả về bảng JTable
    public JTable getTable() {
        return tblThongTinGiangVien;
    }
    // Phương thức điền dữ liệu vào bảng
    public void fillToTableGiangVien() {
        DefaultTableModel model = (DefaultTableModel) tblThongTinGiangVien.getModel();
        model.setRowCount(0); // Xóa toàn bộ dữ liệu trong bảng trước khi thêm mới
        for (ThongTinGiangVien gv : listttgv) {
            model.addRow(new Object[]{gv.getMaGiangVienThongTinGiangVien(), gv.getTenGiangVienThongTinGiangVien(), 
                gv.getSoDienThoaiThongTinGiangVien(), gv.getEmailThongTinGiangVien()});
        }
    }

public void FillToTableGiangVien(ThongTinGiangVien gv){
    txtMaGiangVienThongTinGiangVien.setText(gv.getMaGiangVienThongTinGiangVien());
    txtTenGiangVienThongTinGiangVien.setText(gv.getTenGiangVienThongTinGiangVien());
    txtSoDienThoaiThongTinGiangVien.setText(gv.getSoDienThoaiThongTinGiangVien());
    txtEmailGiangVienThongTinGiangVien.setText(gv.getEmailThongTinGiangVien());
    fillToTableGiangVien();
    JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Giảng Viên Thành Công!>");
}

public void saveGiangVien() {
    if (txtMaGiangVienThongTinGiangVien.getText().length() <= 0 || txtTenGiangVienThongTinGiangVien.getText().length() <= 0 || 
        txtSoDienThoaiThongTinGiangVien.getText().length() <= 0 || txtEmailGiangVienThongTinGiangVien.getText().length() <= 0) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String MaGV = txtMaGiangVienThongTinGiangVien.getText();
    String TenGV = txtTenGiangVienThongTinGiangVien.getText();
    String SoDienThoai = txtSoDienThoaiThongTinGiangVien.getText();
    String Email = txtEmailGiangVienThongTinGiangVien.getText();
    String SQL = "INSERT INTO giangvien (MaGV, TenGV, SoDienThoai, Email) VALUES (?, ?, ?, ?)";

    try (Connection conn = ConnectorHelper.connection(); 
         PreparedStatement pstm = conn.prepareStatement(SQL)) {
        pstm.setString(1, MaGV);
        pstm.setString(2, TenGV);
        pstm.setString(3, SoDienThoai);
        pstm.setString(4, Email);
        pstm.executeUpdate();
        refreshListGiangVien();
        JOptionPane.showMessageDialog(null, "Thêm mới thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
        fillToTableGiangVien();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi thêm mới dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

 // Phương thức làm mới danh sách giảng viên từ cơ sở dữ liệu
    public void refreshListGiangVien() {
        listttgv.clear();
        try (Connection conn = ConnectorHelper.connection()) {
            String SQL = "SELECT MaGV, TenGV, SoDienThoai, Email FROM giangvien";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String MaGV = rs.getString("MaGV");
                String TenGV = rs.getString("TenGV");
                String SoDienThoai = rs.getString("SoDienThoai");
                String Email = rs.getString("Email");
                listttgv.add(new ThongTinGiangVien(MaGV, TenGV, SoDienThoai, Email));
            }
            pstm.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

 // Phương thức cập nhật giảng viên
    public void updateGiangVien() {
        if (txtMaGiangVienThongTinGiangVien.getText().trim().isEmpty() || 
            txtTenGiangVienThongTinGiangVien.getText().trim().isEmpty() || 
            txtSoDienThoaiThongTinGiangVien.getText().trim().isEmpty() || 
            txtEmailGiangVienThongTinGiangVien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String MaGV = txtMaGiangVienThongTinGiangVien.getText().trim();
        String TenGV = txtTenGiangVienThongTinGiangVien.getText().trim();
        String SoDienThoai = txtSoDienThoaiThongTinGiangVien.getText().trim();
        String Email = txtEmailGiangVienThongTinGiangVien.getText().trim();
        String SQL = "UPDATE giangvien SET TenGV = ?, SoDienThoai = ?, Email = ? WHERE MaGV = ?";
        try (Connection conn = ConnectorHelper.connection(); 
             PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setString(1, TenGV);
            pstm.setString(2, SoDienThoai);
            pstm.setString(3, Email);
            pstm.setString(4, MaGV);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                fillToTableGiangVien(); 
                refreshListGiangVien();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy giảng viên với MaGV: " + MaGV, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dữ liệu: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

public void XoaGiangVien(){
    try {
        listttgv.remove(index);
        deleteGiangVien();
        fillToTableGiangVien();
        refreshListGiangVien();
        clearFormGiangVien();
        JOptionPane.showMessageDialog(this, "Đã Xóa Giảng Viên Mà Bạn Chọn");
    } catch(IndexOutOfBoundsException e){
        JOptionPane.showMessageDialog(this, "Vui lòng chọn đối tượng bạn muốn xóa");
    }
}

public void deleteGiangVien() {
    if (txtMaGiangVienThongTinGiangVien.getText().length() <= 0) {
        JOptionPane.showMessageDialog(null, "Chưa nhập mã giảng viên", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        String MaGV = txtMaGiangVienThongTinGiangVien.getText();
        String SQL = "DELETE FROM giangvien WHERE MaGV = ?";

        try (Connection conn = ConnectorHelper.connection(); 
             PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setString(1, MaGV);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

public void FillToTableGiangVien(int index){
    txtMaGiangVienThongTinGiangVien.setText(listttgv.get(index).getMaGiangVienThongTinGiangVien());
    txtTenGiangVienThongTinGiangVien.setText(listttgv.get(index).getTenGiangVienThongTinGiangVien());
    txtSoDienThoaiThongTinGiangVien.setText(listttgv.get(index).getSoDienThoaiThongTinGiangVien());
    txtEmailGiangVienThongTinGiangVien.setText(listttgv.get(index).getEmailThongTinGiangVien());
}

public void clearFormGiangVien(){
    txtMaGiangVienThongTinGiangVien.setText(null);
    txtTenGiangVienThongTinGiangVien.setText(null);
    txtSoDienThoaiThongTinGiangVien.setText(null);
    txtEmailGiangVienThongTinGiangVien.setText(null);
    index = -1;
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenGiangVienThongTinGiangVien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoaiThongTinGiangVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaGiangVienThongTinGiangVien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmailGiangVienThongTinGiangVien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongTinGiangVien = new javax.swing.JTable();
        btnThemThongTinGiangVien = new javax.swing.JButton();
        btnXoaThongTinGiangVien = new javax.swing.JButton();
        btnSuaThongTinGiangVien = new javax.swing.JButton();
        btnExitThongTinGiangVien = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(730, 460));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Quản Lý Giảng Viên");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Tên giảng viên");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));
        getContentPane().add(txtTenGiangVienThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 270, 30));

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Số Điện Thoại ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));
        getContentPane().add(txtSoDienThoaiThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 170, -1));

        jLabel3.setBackground(new java.awt.Color(0, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Mã giảng viên");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));
        getContentPane().add(txtMaGiangVienThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 180, -1));

        jLabel5.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Email Giảng Viên");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));
        getContentPane().add(txtEmailGiangVienThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 340, -1));

        tblThongTinGiangVien.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblThongTinGiangVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên giảng viên", "Mã giảng viên", "Số Điện Thoại", "Email Giảng Viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinGiangVien.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblThongTinGiangVienAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblThongTinGiangVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinGiangVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThongTinGiangVien);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 680, 190));

        btnThemThongTinGiangVien.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnThemThongTinGiangVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        btnThemThongTinGiangVien.setText("Thêm");
        btnThemThongTinGiangVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThongTinGiangVienActionPerformed(evt);
            }
        });
        getContentPane().add(btnThemThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 30));

        btnXoaThongTinGiangVien.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXoaThongTinGiangVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/12319540.png"))); // NOI18N
        btnXoaThongTinGiangVien.setText("Xóa");
        btnXoaThongTinGiangVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThongTinGiangVienActionPerformed(evt);
            }
        });
        getContentPane().add(btnXoaThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, 30));

        btnSuaThongTinGiangVien.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnSuaThongTinGiangVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
        btnSuaThongTinGiangVien.setText("Sửa");
        btnSuaThongTinGiangVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinGiangVienActionPerformed(evt);
            }
        });
        getContentPane().add(btnSuaThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, 30));

        btnExitThongTinGiangVien.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnExitThongTinGiangVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/image-removebg-preview.png"))); // NOI18N
        btnExitThongTinGiangVien.setText("Thoát");
        btnExitThongTinGiangVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThongTinGiangVienActionPerformed(evt);
            }
        });
        getContentPane().add(btnExitThongTinGiangVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, -1, 30));

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 80, 30));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        getContentPane().add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nền.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemThongTinGiangVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThongTinGiangVienActionPerformed
        saveGiangVien();
    }//GEN-LAST:event_btnThemThongTinGiangVienActionPerformed

    private void btnXoaThongTinGiangVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThongTinGiangVienActionPerformed
        XoaGiangVien();
    }//GEN-LAST:event_btnXoaThongTinGiangVienActionPerformed

    private void btnSuaThongTinGiangVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinGiangVienActionPerformed
        updateGiangVien();
        fillToTableGiangVien();
    }//GEN-LAST:event_btnSuaThongTinGiangVienActionPerformed

    private void btnExitThongTinGiangVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThongTinGiangVienActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitThongTinGiangVienActionPerformed

    private void tblThongTinGiangVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinGiangVienMouseClicked
        index = tblThongTinGiangVien.getSelectedRow();
        FillToTableGiangVien(index);
    }//GEN-LAST:event_tblThongTinGiangVienMouseClicked

    private void tblThongTinGiangVienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblThongTinGiangVienAncestorAdded
        fillToTableGiangVien();
    }//GEN-LAST:event_tblThongTinGiangVienAncestorAdded

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaGiangVienThongTinGiangVien.setText("");
        txtTenGiangVienThongTinGiangVien.setText("");
        txtSoDienThoaiThongTinGiangVien.setText("");
        txtEmailGiangVienThongTinGiangVien.setText("");
        JOptionPane.showMessageDialog(this, "Form đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String maGV = txtMaGiangVienThongTinGiangVien.getText().trim();
        if (maGV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giảng viên để tìm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = ConnectorHelper.connection()) {
            String SQL = "SELECT MaGV, TenGV, SoDienThoai, Email FROM giangvien WHERE MaGV = ?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, maGV);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                txtMaGiangVienThongTinGiangVien.setText(rs.getString("MaGV"));
                txtTenGiangVienThongTinGiangVien.setText(rs.getString("TenGV"));
                txtSoDienThoaiThongTinGiangVien.setText(rs.getString("SoDienThoai"));
                txtEmailGiangVienThongTinGiangVien.setText(rs.getString("Email"));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giảng viên với mã: " + maGV, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
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
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExitThongTinGiangVien;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSuaThongTinGiangVien;
    private javax.swing.JButton btnThemThongTinGiangVien;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaThongTinGiangVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblThongTinGiangVien;
    private javax.swing.JTextField txtEmailGiangVienThongTinGiangVien;
    private javax.swing.JTextField txtMaGiangVienThongTinGiangVien;
    private javax.swing.JTextField txtSoDienThoaiThongTinGiangVien;
    private javax.swing.JTextField txtTenGiangVienThongTinGiangVien;
    // End of variables declaration//GEN-END:variables


}
