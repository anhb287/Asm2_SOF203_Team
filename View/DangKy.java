
package View;

import FormCuaUsers.ConnectorHelper;
import View.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class DangKy extends javax.swing.JFrame {

    private int index = -1;

    public DangKy() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private boolean checkRegister(String username, String password, String ma) {
        String SQL = "Select * from users";
        String userData;
        String passData;
        String MaData;
        try (
                Connection conn = ConnectorHelper.connection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(SQL);) {

            while (rs.next()) {
                userData = rs.getString("user");
                passData = rs.getString("password");
                MaData = rs.getString("Ma");
                if (username.equals(userData) && password.equals(passData) && ma.equals(MaData)) {
                    System.out.println("Thêm ACCOUNT Mới Thành Công");
                    return true;
                }
            }
            System.out.println("Thêm ACCOUNT Thất Bại\n Email của bạn chưa chính xác ");
        } catch (SQLException ex) {
            System.out.println("Lỗi : " + ex);
        }
        return false;
    }

    // Xét điều kiện phải nhập đủ thông tin 
    public void RegisterĐK() {
        // Kiểm tra nếu các trường còn trống thì hiển thị thông báo
        if (txtUsername.getText().isEmpty()
                || txtPass.getPassword().length == 0
                || txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            String Username = txtUsername.getText();
            String Password = new String(txtPass.getPassword());
            String Ma = txtMa.getText();
            try {
                Connection conn = ConnectorHelper.connection();
                // Query không được viết xuống dòng
                String SQL = "INSERT INTO users (Ma,username ,password) VALUES (?,?,?)";
                PreparedStatement pstm = conn.prepareStatement(SQL);
                pstm.setString(1, Ma);
                pstm.setString(2, Username);
                pstm.setString(3, Password);
                pstm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm Account mới thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); // Đóng cửa sổ hiện tại
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm mới dữ liệu Account", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
    }

    // Xóa Thông Tin Có Trong Form
    public void Clear() {
        txtUsername.setText(null);
        txtMa.setText(null);
        txtPass.setText(null);
        index = -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegister = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        dacoTK = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegister.setBackground(new java.awt.Color(153, 153, 255));
        btnRegister.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Đăng_Kí.png"))); // NOI18N
        btnRegister.setText("Đăng ký");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 140, 40));

        btnExit.setBackground(new java.awt.Color(255, 102, 102));
        btnExit.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/image-removebg-preview.png"))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, -1, 40));

        btnClear.setBackground(new java.awt.Color(0, 204, 204));
        btnClear.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear2.png"))); // NOI18N
        btnClear.setText("Làm Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, 40));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 300, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 204));
        jLabel1.setText("Đăng Ký");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 130, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setText("Tên đăng nhập");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 140, -1));

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 480, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 255));
        jLabel3.setText("Mật khẩu");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 90, 26));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 255, 255));
        jLabel5.setText("Mã");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 40, 30));
        getContentPane().add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 170, 30));

        dacoTK.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        dacoTK.setForeground(new java.awt.Color(153, 204, 255));
        dacoTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Đăng_Kí.png"))); // NOI18N
        dacoTK.setText("Bạn đã có tài khoản chưa ? Đăng nhập ");
        dacoTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dacoTKMouseClicked(evt);
            }
        });
        getContentPane().add(dacoTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 370, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/formCanva.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        Clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // Kiểm tra nếu các trường còn trống thì hiển thị thông báo
        if (txtUsername.getText().isEmpty()
                || txtPass.getPassword().length == 0
                || txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!Pattern.matches("[a-zA-Z0-9._]+", txtUsername.getText())) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản không được chứa khoảng trắng hoặc dấu không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Nếu tất cả các trường đã được điền, tiến hành gọi phương thức đăng ký
        RegisterĐK();

        // Sau khi đăng ký thành công, hiển thị form đăng nhập
        Login lg = new Login();
        lg.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void dacoTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dacoTKMouseClicked
        Login login = new Login();
        login.setVisible(true);
        login.pack();
        login.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_dacoTKMouseClicked


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
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel dacoTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtMa;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
