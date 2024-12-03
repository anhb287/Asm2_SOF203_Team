/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import FormCuaUsers.ConnectorHelper;
import FormCuaUsers.model.ThongTinSinhVien;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
public class Menu1 extends  javax.swing.JInternalFrame {

    static JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    List<ThongTinSinhVien> listttsv = new ArrayList<>();
    private int index = -1;
    public Menu1() {
        initComponents();
         // Khởi tạo cho JInternalFrame
        
        setResizable(true);
        setTitle("Quản Lý Sinh Viên");
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        fillToTable();
        refreshList();
        
    }
     // Sinh Viên
     public void fillToTable(){
        DefaultTableModel model = (DefaultTableModel) tblThongTinSinhVien.getModel();
        model.setRowCount(0);
        for (ThongTinSinhVien sv : listttsv) {
            model.addRow(new Object[]{sv.getMaSinhVienthongtinsinhvien(), sv.getTenSinhVienthongtinsinhvien(), sv.getPhongHocthongtinsinhvien(), sv.getLopHocthongtinsinhvien(),sv.getEmailthongtinsinhvien()});
        }
    }
     
    public void FillToTable(ThongTinSinhVien sv){
        txtMaSinhVienThongTinSinhVien.setText(String.valueOf(sv.getMaSinhVienthongtinsinhvien())); 
        txtTenSinhVienThongTinSinhVien.setText(sv.getTenSinhVienthongtinsinhvien());
        txtPhongHocThongTinSinhVien.setText(sv.getPhongHocthongtinsinhvien());
        txtLopHocThongTinSinhVien.setText(sv.getLopHocthongtinsinhvien());
        txtEmailThongTinSinhVien.setText(sv.getPhongHocthongtinsinhvien());
        fillToTable();
        JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Sinh Viên Thành Công!>");
    }
     public void saveSVinDB() {
        if (txtMaSinhVienThongTinSinhVien.getText().length() <= 0 || txtTenSinhVienThongTinSinhVien.getText().length() <= 0 || 
        txtPhongHocThongTinSinhVien.getText().length() <= 0 || txtLopHocThongTinSinhVien.getText().length() <= 0 || txtEmailThongTinSinhVien.getText().length() <= 0    )  {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
   
    String MaSV = txtMaSinhVienThongTinSinhVien.getText();
    String TenSV = txtTenSinhVienThongTinSinhVien.getText();
    String PhongHoc = txtPhongHocThongTinSinhVien.getText();
    String LopHoc = txtLopHocThongTinSinhVien.getText();
    String Email = txtEmailThongTinSinhVien.getText();
    String SQL = "INSERT INTO sinhvien (MaSV, TenSV, PhongHoc, LopHoc, Email) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = ConnectorHelper.connection(); 
         PreparedStatement pstm = conn.prepareStatement(SQL)) {
        pstm.setString(1, MaSV);
        pstm.setString(2, TenSV);
        pstm.setString(3, PhongHoc);
        pstm.setString(4, LopHoc);
        pstm.setString(5, Email);
        pstm.executeUpdate();
        refreshList();
        JOptionPane.showMessageDialog(null, "Thêm mới thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
        fillToTable();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi thêm mới dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    public void refreshList() {
    listttsv.clear(); 
    try {
        Connection conn = ConnectorHelper.connection();
        String SQL = "SELECT MaSV, TenSV, PhongHoc, LopHoc, Email FROM sinhvien";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String MaSV = rs.getString("MaSV");
            String TenSV = rs.getString("TenSV");
            String PhongHoc = rs.getString("PhongHoc");
            String LopHoc = rs.getString("LopHoc");
            String Email = rs.getString("Email");
               listttsv.add(new ThongTinSinhVien(MaSV,TenSV,LopHoc,PhongHoc,Email));
            // listttsv.add(new ThongTinSinhVien(PhongHoc, PhongHoc, LopHoc, PhongHoc, PhongHoc));
        }

        pstm.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
public void updateSVinDB() {
    if (txtMaSinhVienThongTinSinhVien.getText().length() <= 0 || 
        txtTenSinhVienThongTinSinhVien.getText().length() <= 0 ||
        txtLopHocThongTinSinhVien.getText().length() <= 0 || 
        txtPhongHocThongTinSinhVien.getText().length() <= 0 ||
        txtEmailThongTinSinhVien.getText().length() <= 0) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Lấy dữ liệu từ form
        String MaSV = txtMaSinhVienThongTinSinhVien.getText();
        String TenSV = txtTenSinhVienThongTinSinhVien.getText();
        String PhongHoc = txtPhongHocThongTinSinhVien.getText();
        String LopHoc = txtLopHocThongTinSinhVien.getText(); // Đã sửa tên biến
        String Email = txtEmailThongTinSinhVien.getText();
        try {
            Connection conn = ConnectorHelper.connection();
            String SQL = "UPDATE sinhvien SET TenSV = ?, PhongHoc = ?, LopHoc = ?, Email = ? WHERE MaSV = ?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, TenSV);
            pstm.setString(2, PhongHoc);
            pstm.setString(3, LopHoc);
            pstm.setString(4, Email);
            pstm.setString(5, MaSV);
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Đã cập nhật thông tin sinh viên:");
                System.out.println("MaSV: " + MaSV);
                System.out.println("TenSV: " + TenSV);
                System.out.println("PhongHoc: " + PhongHoc);
                System.out.println("LopHoc: " + LopHoc);
                System.out.println("Email: " + Email);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên với MaSV: " + MaSV, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dữ liệu: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    public void XoaSV(){
        try {
            listttsv.remove(index);
            deleteSVinDB();
            fillToTable();
            refreshList();
            clearForm();
            JOptionPane.showMessageDialog(this, "Đã Xóa Nhân Viên Mà Bạn Chọn"); 
        } catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this,"Vui lòng chọn đối tượng bạn muốn xóa" );
        }
    }
    
    public void deleteSVinDB() {
        if ( txtMaSinhVienThongTinSinhVien.getText().length() <= 0 ) {
            JOptionPane.showMessageDialog(null, "Có vẻ bạn chưa nhập STT", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
             String MaSV = txtMaSinhVienThongTinSinhVien.getText();
    
            try {
                Connection conn = ConnectorHelper.connection();
                String SQL = "DELETE FROM sinhvien WHERE MaSV = ? ";
                PreparedStatement pstm = conn.prepareStatement(SQL); 
                pstm.setString(1, MaSV);
                pstm.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
            
  public void FillToTable(int index){
        txtMaSinhVienThongTinSinhVien.setText(String.valueOf(listttsv.get(index).getMaSinhVienthongtinsinhvien())); 
        txtTenSinhVienThongTinSinhVien.setText(listttsv.get(index).getTenSinhVienthongtinsinhvien());
        txtPhongHocThongTinSinhVien.setText(listttsv.get(index).getPhongHocthongtinsinhvien());
        txtLopHocThongTinSinhVien.setText(listttsv.get(index).getLopHocthongtinsinhvien());
        txtEmailThongTinSinhVien.setText(String.valueOf(listttsv.get(index).getEmailthongtinsinhvien()));
    }
       
    
    public void clearForm(){
       txtMaSinhVienThongTinSinhVien.setText(null);
       txtTenSinhVienThongTinSinhVien.setText(null);
       txtPhongHocThongTinSinhVien.setText(null);
       txtLopHocThongTinSinhVien.setText(null);
       txtEmailThongTinSinhVien.setText(null);
       index = -1;
    }
    public void readForm() {
        try {
     
            String MaSV = txtMaSinhVienThongTinSinhVien.getText().trim();    

        } catch (NumberFormatException e) {
           
            System.out.println("Lỗi: " + e.getMessage());
            return;
        }
    }
    public void UpdateSV(){
    if (index < 0) {
    JOptionPane.showMessageDialog(this, "<Vui Lòng Chọn Sinh Viên Trước Khi Cập Nhật!>");
    return;
    }
    try {
    ThongTinSinhVien sv = listttsv.get(index);    
    sv.setMaSinhVienthongtinsinhvien(txtMaSinhVienThongTinSinhVien.getText()); 
    sv.setTenSinhVienthongtinsinhvien(txtTenSinhVienThongTinSinhVien.getText());
    sv.setPhongHocthongtinsinhvien(txtPhongHocThongTinSinhVien.getText());
    sv.setLopHocthongtinsinhvien(txtLopHocThongTinSinhVien.getText());
    sv.setEmailthongtinsinhvien(txtEmailThongTinSinhVien.getText());
    fillToTable();
    JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Sinh Viên Thành Công!>");
    updateSVinDB();
    
    } catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "<Vui Lòng Chọn Sinh Viên Trước Khi Cập Nhật!>");
     
    }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMaSinhVienThongTinSinhVien = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtLopHocThongTinSinhVien = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTenSinhVienThongTinSinhVien = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtEmailThongTinSinhVien = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPhongHocThongTinSinhVien = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThongTinSinhVien = new javax.swing.JTable();
        btnThemThongTinSinhVien = new javax.swing.JButton();
        btnXoaThongTinSinhVien = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("Quản Lý Sinh Viên");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("Mã Sinh Viên");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtMaSinhVienThongTinSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSinhVienThongTinSinhVienActionPerformed(evt);
            }
        });
        getContentPane().add(txtMaSinhVienThongTinSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setText("Lớp Học");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtLopHocThongTinSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLopHocThongTinSinhVienActionPerformed(evt);
            }
        });
        getContentPane().add(txtLopHocThongTinSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 120, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Tên Sinh Viên");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));
        getContentPane().add(txtTenSinhVienThongTinSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 380, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 22)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setText("Email");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        txtEmailThongTinSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailThongTinSinhVienActionPerformed(evt);
            }
        });
        getContentPane().add(txtEmailThongTinSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 380, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("Phòng Học");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        getContentPane().add(txtPhongHocThongTinSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, -1));

        tblThongTinSinhVien.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblThongTinSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Sinh Viên", "Tên Sinh Viên", "Phòng Học", "Lớp Học", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinSinhVien.setPreferredSize(new java.awt.Dimension(375, 150));
        tblThongTinSinhVien.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblThongTinSinhVienAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblThongTinSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinSinhVienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThongTinSinhVien);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 700, 200));

        btnThemThongTinSinhVien.setBackground(new java.awt.Color(204, 204, 255));
        btnThemThongTinSinhVien.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnThemThongTinSinhVien.setForeground(new java.awt.Color(153, 153, 255));
        btnThemThongTinSinhVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Giangvien.png"))); // NOI18N
        btnThemThongTinSinhVien.setText("Thêm");
        btnThemThongTinSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThongTinSinhVienActionPerformed(evt);
            }
        });
        getContentPane().add(btnThemThongTinSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, 30));

        btnXoaThongTinSinhVien.setBackground(new java.awt.Color(255, 255, 204));
        btnXoaThongTinSinhVien.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXoaThongTinSinhVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/12319540.png"))); // NOI18N
        btnXoaThongTinSinhVien.setText("Xóa");
        btnXoaThongTinSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThongTinSinhVienActionPerformed(evt);
            }
        });
        getContentPane().add(btnXoaThongTinSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/image-removebg-preview.png"))); // NOI18N
        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, 30));

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 80, 30));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        getContentPane().add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 80, 30));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        getContentPane().add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nền.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSinhVienThongTinSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSinhVienThongTinSinhVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSinhVienThongTinSinhVienActionPerformed

    private void txtLopHocThongTinSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLopHocThongTinSinhVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLopHocThongTinSinhVienActionPerformed

    private void txtEmailThongTinSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailThongTinSinhVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailThongTinSinhVienActionPerformed

    private void tblThongTinSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinSinhVienMouseClicked
        index = tblThongTinSinhVien.getSelectedRow();
        FillToTable(index);
    }//GEN-LAST:event_tblThongTinSinhVienMouseClicked

    private void btnThemThongTinSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThongTinSinhVienActionPerformed
       saveSVinDB();
    }//GEN-LAST:event_btnThemThongTinSinhVienActionPerformed

    private void btnXoaThongTinSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThongTinSinhVienActionPerformed
        XoaSV();
    }//GEN-LAST:event_btnXoaThongTinSinhVienActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblThongTinSinhVienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblThongTinSinhVienAncestorAdded
       fillToTable();
    }//GEN-LAST:event_tblThongTinSinhVienAncestorAdded

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
          String maSV = txtMaSinhVienThongTinSinhVien.getText().trim();
        if (maSV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sinh viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = ConnectorHelper.connection()) {
                String SQL = "SELECT MaSV, TenSV, PhongHoc, LopHoc, Email FROM sinhvien WHERE MaSV = ?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, maSV);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                txtMaSinhVienThongTinSinhVien.setText(rs.getString("MaSV"));
                txtTenSinhVienThongTinSinhVien.setText(rs.getString("TenSV"));
                txtPhongHocThongTinSinhVien.setText(rs.getString("PhongHoc"));
                txtLopHocThongTinSinhVien.setText(rs.getString("LopHoc"));
                txtEmailThongTinSinhVien.setText(rs.getString("Email"));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaSinhVienThongTinSinhVien.setText("");
        txtLopHocThongTinSinhVien.setText("");
        txtPhongHocThongTinSinhVien.setText("");
        txtTenSinhVienThongTinSinhVien.setText("");
        txtEmailThongTinSinhVien.setText("");
        JOptionPane.showMessageDialog(this, "Form đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        UpdateSV();
        fillToTable();
    }//GEN-LAST:event_btnSuaActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemThongTinSinhVien;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaThongTinSinhVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblThongTinSinhVien;
    private javax.swing.JTextField txtEmailThongTinSinhVien;
    private javax.swing.JTextField txtLopHocThongTinSinhVien;
    private javax.swing.JTextField txtMaSinhVienThongTinSinhVien;
    private javax.swing.JTextField txtPhongHocThongTinSinhVien;
    private javax.swing.JTextField txtTenSinhVienThongTinSinhVien;
    // End of variables declaration//GEN-END:variables
}
