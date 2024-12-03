/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.sql.SQLIntegrityConstraintViolationException;
import FormCuaUsers.ConnectorHelper;
import FormCuaUsers.model.MonHoc;
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

public class Menu3 extends  javax.swing.JInternalFrame {

    static JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    List<MonHoc> listmonhoc = new ArrayList<>();
    private int index = -1;
    public Menu3() {
        initComponents();
         // Khởi tạo cho JInternalFrame
        setResizable(true);
        setTitle("Quản Lý Môn Học");
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        fillToTableMonHoc();
        refreshListMonHoc();
    }
    //Môn HỌc
       public void fillToTableMonHoc(){
        DefaultTableModel model = (DefaultTableModel) tblThongTinMonHoc.getModel();
        model.setRowCount(0);
        for (MonHoc mh : listmonhoc) {
            model.addRow(new Object[]{mh.getMaMonHoc(), mh.getTenMonHoc(), mh.getThoiLuongMonHoc() });
        }
    }
     
    public void FillToTableMonHoc(MonHoc mh){
        txtMaMonHoc.setText(mh.getMaMonHoc());
        txtTenMonHoc.setText(mh.getTenMonHoc());
        txtThoiLuong.setText(mh.getThoiLuongMonHoc());
        fillToTableMonHoc();
        JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Sinh Viên Thành Công!>");
    }
     public void saveMonHoc() {
        if (txtMaMonHoc.getText().length() <= 0 || txtTenMonHoc.getText().length() <= 0 || txtThoiLuong.getText().length() <= 0 )  {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
   
    String MaMonHoc = txtMaMonHoc.getText();
    String TenMonHoc = txtTenMonHoc.getText();
    String ThoiLuong = txtThoiLuong.getText();
    String SQL = "INSERT INTO monhoc (MaMonHoc, TenMonHoc, ThoiLuong) VALUES (?, ?, ?)";

    try (Connection conn = ConnectorHelper.connection(); 
        PreparedStatement pstm = conn.prepareStatement(SQL)) {
        pstm.setString(1, MaMonHoc);  
        pstm.setString(2, TenMonHoc); 
        pstm.setString(3, ThoiLuong);
        pstm.executeUpdate();
        refreshListMonHoc();
        fillToTableMonHoc();
        JOptionPane.showMessageDialog(null, "Thêm mới thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } 
    catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi thêm mới dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
      public void refreshListMonHoc() {
    listmonhoc.clear(); 
    try {
        Connection conn = ConnectorHelper.connection();
        String SQL = "SELECT MaMonHoc, TenMonHoc, ThoiLuong FROM monhoc";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String MaMon = rs.getString("MaMonHoc");
            String TenMon = rs.getString("TenMonHoc");
            String ThoiLuong = rs.getString("ThoiLuong");
            listmonhoc.add(new MonHoc(MaMon, TenMon, ThoiLuong));
              
        }

        pstm.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
//      Sau này để là cao hơn 
    public void updateMonHoc() {
       
        if (txtMaMonHoc.getText().length() <= 0 || txtTenMonHoc.getText().length() <= 0 ||txtThoiLuong.getText().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Lấy dữ liệu từ form
           String MaMonHoc= txtMaMonHoc.getText();
            String TenMonHoc = txtTenMonHoc.getText();
            String ThoiLuong = txtThoiLuong.getText();
            try {
                
                Connection conn = ConnectorHelper.connection();
               
                String SQL = "UPDATE monhoc SET TenMonHoc = ?, ThoiLuong = ? WHERE MaMonHoc = ?";

                PreparedStatement pstm = conn.prepareStatement(SQL);
                pstm.setString(1, TenMonHoc);  
                pstm.setString(2, ThoiLuong); 
                pstm.setString(3, MaMonHoc);
 
                int rowsAffected = pstm.executeUpdate();

                if (rowsAffected > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Đã cập nhật thông tin sinh viên:");
                    System.out.println("MaMonHoc: " + MaMonHoc);
                    System.out.println("TenMonHoc: " + TenMonHoc);
                    System.out.println("ThoiLuong: " + ThoiLuong);

                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên với MaSV: " + MaMonHoc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void XoaMonHoc(){
        try {
            listmonhoc.remove(index);
            deleteMonHoc();
            refreshListMonHoc();
            clearFormMonHoc();
        } catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this,"Vui lòng chọn đối tượng bạn muốn xóa" );
        }
    }
    
   public void deleteMonHoc() {
    if (txtMaMonHoc.getText().length() <= 0) {
        JOptionPane.showMessageDialog(null, "Chưa nhập mã môn học", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        String MaMonHoc = txtMaMonHoc.getText();

        try {
            Connection conn = ConnectorHelper.connection();
            String SQL = "DELETE FROM monhoc WHERE MaMonHoc = ?";
            PreparedStatement pstm = conn.prepareStatement(SQL);

            pstm.setString(1, MaMonHoc);
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xoá môn học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy môn học có mã: " + MaMonHoc, "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            // Xử lý lỗi ràng buộc khoá ngoại
            JOptionPane.showMessageDialog(null, 
                "Không thể xoá môn học " + MaMonHoc + " vì dữ liệu đang được tham chiếu trong bảng khác.", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Xử lý lỗi SQL khác
            JOptionPane.showMessageDialog(null, 
                "Đã xảy ra lỗi trong quá trình xoá môn học: " + ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}

            
  public void FillToTableMonHocc(int index){
        txtMaMonHoc.setText(listmonhoc.get(index).getMaMonHoc());
        txtTenMonHoc.setText(listmonhoc.get(index).getTenMonHoc());
        txtThoiLuong.setText(listmonhoc.get(index).getThoiLuongMonHoc());
       
    }
       
    
    public void clearFormMonHoc(){
       txtMaMonHoc.setText(null);
       txtTenMonHoc.setText(null);
       txtThoiLuong.setText(null);
       index = -1;
    }
    public void readFormMonHoc() {
        try {
     
            String MaMonHoc = txtMaMonHoc.getText().trim();    

        } catch (NumberFormatException e) {
           
            System.out.println("Lỗi: " + e.getMessage());
            return;
        }
    }
    public void UpdateMonHoc(){
    index = tblThongTinMonHoc.getSelectedRow();
    try {
    MonHoc mh = listmonhoc.get(index);    
    mh.setMaMonHoc(txtMaMonHoc.getText()); 
    mh.setTenMonHoc(txtTenMonHoc.getText());
    mh.setThoiLuongMonHoc(txtThoiLuong.getText());
    fillToTableMonHoc();
    JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Sinh Viên Thành Công!>");
    
    } catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "<Vui Lòng Chọn Sinh Viên Trước Khi Cập Nhật!>");
     
    }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtMaMonHoc = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtTenMonHoc = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnthoat = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblThongTinMonHoc = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(730, 460));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Quản Lý Môn Học");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 102, 102));
        jLabel31.setText("Mã Môn Học");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        getContentPane().add(txtMaMonHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 280, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 102, 102));
        jLabel32.setText("Tên Môn Học");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        getContentPane().add(txtTenMonHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 280, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 102, 102));
        jLabel33.setText("Thời Lượng");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));
        getContentPane().add(txtThoiLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 280, -1));

        btnThem.setBackground(new java.awt.Color(102, 153, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(204, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Đăng_Kí.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        getContentPane().add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, 30));

        btnXoa.setBackground(new java.awt.Color(255, 153, 153));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(204, 51, 0));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/12319540.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        getContentPane().add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, 30));

        btnthoat.setBackground(new java.awt.Color(255, 255, 204));
        btnthoat.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnthoat.setForeground(new java.awt.Color(255, 204, 0));
        btnthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/image-removebg-preview.png"))); // NOI18N
        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });
        getContentPane().add(btnthoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 100, 30));

        tblThongTinMonHoc.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblThongTinMonHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Môn Học", "Tên Môn Học", "Thời Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinMonHoc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblThongTinMonHocAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblThongTinMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinMonHocMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblThongTinMonHoc);

        getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 690, 220));

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 80, 30));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        getContentPane().add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 80, 30));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        getContentPane().add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nền.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnthoatActionPerformed

    private void tblThongTinMonHocAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblThongTinMonHocAncestorAdded
        fillToTableMonHoc();
    }//GEN-LAST:event_tblThongTinMonHocAncestorAdded

    private void tblThongTinMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinMonHocMouseClicked
        index = tblThongTinMonHoc.getSelectedRow();
        FillToTableMonHocc(index);
    }//GEN-LAST:event_tblThongTinMonHocMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        saveMonHoc();
        fillToTableMonHoc();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        XoaMonHoc();
        fillToTableMonHoc();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtTenMonHoc.setText("");
        txtMaMonHoc.setText("");
        txtThoiLuong.setText("");
        JOptionPane.showMessageDialog(this, "Form đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String maMonHoc = txtMaMonHoc.getText().trim(); // Lấy mã môn học từ ô nhập liệu
        if (maMonHoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã Môn Học để tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConnectorHelper.connection()) {
            String SQL = "SELECT MaMonHoc, TenMonHoc, ThoiLuong FROM monhoc WHERE MaMonHoc = ?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, maMonHoc);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                // Hiển thị dữ liệu lên form
                txtMaMonHoc.setText(rs.getString("MaMonHoc"));
                txtTenMonHoc.setText(rs.getString("TenMonHoc"));
                txtThoiLuong.setText(rs.getString("ThoiLuong"));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy môn học với mã: " + maMonHoc, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            pstm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateMonHoc();
        fillToTableMonHoc();
    }//GEN-LAST:event_btnSuaActionPerformed

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
            java.util.logging.Logger.getLogger(Menu3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnthoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tblThongTinMonHoc;
    private javax.swing.JTextField txtMaMonHoc;
    private javax.swing.JTextField txtTenMonHoc;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
