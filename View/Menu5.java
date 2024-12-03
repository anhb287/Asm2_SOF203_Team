
package View;

import FormCuaUsers.ConnectorHelper;
import FormCuaUsers.model.ThongTinLichDay;
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

public class Menu5 extends  javax.swing.JInternalFrame {

    static JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    List<ThongTinLichDay> listttld = new ArrayList<>();
    private int index = -1;
    public Menu5() {
        initComponents();
         // Khởi tạo cho JInternalFrame
        setResizable(true);
        setTitle("Quản Lý Lịch Dạy");
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        fillToTableLichDay();
        refreshListLD();
        
    }
     // Lịch Dạy
    public void fillToTableLichDay(){
        DefaultTableModel model = (DefaultTableModel) tblThongTinLichDay.getModel();
        model.setRowCount(0);
        for (ThongTinLichDay gv : listttld) {
            model.addRow(new Object[]{gv.getTenGiangVienthongtinlichday(), gv.getMaMonHocthongtinlichday(), gv.getTenMonHocthongtinlichday(),gv.getPhongHocthongtinlichday(),gv.getLopHocThongTinLichDay(),gv.getGhiChuThongTinLichDay()});
        }
    }
     public void FillToTableLichDay(ThongTinLichDay gv){
        txtTenGiangVienThongTinLichDay.setText(gv.getTenGiangVienthongtinlichday());
        txtMaMonHocThongTinLichDay.setText(gv.getMaMonHocthongtinlichday());
        txtTenMonHocThongTinLichDay.setText(gv.getMaMonHocthongtinlichday());
        txtPhongHocThongTinLichDay.setText(gv.getPhongHocthongtinlichday());
        txtLopHocThongTinLichDay.setText(gv.getLopHocThongTinLichDay());
        AreaGhiChuThongTinLichDay.setText(gv.getGhiChuThongTinLichDay());
        fillToTableLichDay();
        JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Sinh Viên Thành Công!>");
    }
        public void saveGVinDB() {
          
        if (txtTenGiangVienThongTinLichDay.getText().length() <= 0 || 
        txtMaMonHocThongTinLichDay.getText().length() <= 0 || txtTenMonHocThongTinLichDay.getText().length() <= 0 || txtPhongHocThongTinLichDay.getText().length() <= 0 || txtLopHocThongTinLichDay.getText().length() <= 0 ||AreaGhiChuThongTinLichDay.getText().length() <= 0) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String TenGV = txtTenGiangVienThongTinLichDay.getText();
    String MaMonHoc = txtMaMonHocThongTinLichDay.getText();
    String TenMonHoc = txtTenMonHocThongTinLichDay.getText();
    String PhongHoc = txtPhongHocThongTinLichDay.getText();
    String LopHoc = txtLopHocThongTinLichDay.getText();
    String GhiChu = AreaGhiChuThongTinLichDay.getText();
    String SQL = "INSERT INTO lichday (TenGV, MaMonHoc, TenMonHoc,PhongHoc,LopHoc,GhiChu) VALUES (?, ?, ?, ?, ? , ? )";

    try (Connection conn = ConnectorHelper.connection(); 
         PreparedStatement pstm = conn.prepareStatement(SQL)) {
        pstm.setString(1, TenGV);
        pstm.setString(2, MaMonHoc);
        pstm.setString(3, TenMonHoc);
        pstm.setString(4, PhongHoc);
        pstm.setString(5, LopHoc);
        pstm.setString(6, GhiChu);
        pstm.executeUpdate();
        refreshListLD();
        JOptionPane.showMessageDialog(null, "Thêm mới thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
        fillToTableLichDay();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi thêm mới dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
  public void refreshListLD() {
    listttld.clear(); 
    try {
        Connection conn = ConnectorHelper.connection();
        String SQL = "SELECT TenGV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu FROM lichday";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String TenGV = rs.getString("TenGV");
            String MaMonHoc = rs.getString("MaMonHoc");
            String TenMonHoc = rs.getString("TenMonHoc");
            String PhongHoc = rs.getString("PhongHoc");
            String LopHoc = rs.getString("LopHoc");
            String GhiChu = rs.getString("GhiChu");
            listttld.add(new ThongTinLichDay(TenGV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu));
              
        }

        pstm.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
            public void updateGVinDB() {
       
        if (txtTenGiangVienThongTinLichDay.getText().length() <= 0 ||txtMaMonHocThongTinLichDay.getText().length() <= 0 || txtTenMonHocThongTinLichDay.getText().length() <= 0 || txtPhongHocThongTinLichDay.getText().length() <= 0 || txtLopHocThongTinLichDay.getText().length() <= 0 || AreaGhiChuThongTinLichDay.getText().length() <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Lấy dữ liệu từ form
             String TenGV = txtTenGiangVienThongTinLichDay.getText();
             String MaPhongHoc = txtMaMonHocThongTinLichDay.getText();
             String TenMonHoc = txtTenMonHocThongTinLichDay.getText();
             String PhongHoc = txtPhongHocThongTinLichDay.getText();
             String LopHoc = txtLopHocThongTinLichDay.getText();
             String GhiChu = AreaGhiChuThongTinLichDay.getText();

            try {
                
                Connection conn = ConnectorHelper.connection();
               
                String SQL = "UPDATE lichday SET MaGV = ?, TenGV = ?, MaPhongHoc = ?,TenMonHoc = ?,PhongHoc = ?, LopHoc = ?,GhiChu = ?";
                PreparedStatement pstm = conn.prepareStatement(SQL);
                pstm.setString(1, TenGV);  
                pstm.setString(2, MaPhongHoc);
                pstm.setString(3, TenMonHoc); 
                pstm.setString(4, PhongHoc); 
                pstm.setString(5, LopHoc); 
                pstm.setString(6, GhiChu);                   
                int rowsAffected = pstm.executeUpdate();

                if (rowsAffected > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("TenGV: " + TenGV);
                    System.out.println("MaPhongHoc: " + MaPhongHoc);
                    System.out.println("TenMonHoc: " + TenMonHoc);
                    System.out.println("PhongHoc: " + PhongHoc);
                    System.out.println("LopHoc: " + LopHoc);
                    System.out.println("GhiChu: " + GhiChu);

                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy  Tên Giảng Viên với MaSV: " + TenGV, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
            
        public void XoaGV(){
        try {
            listttld.remove(index);
            deleteGVinDB();
            fillToTableLichDay();
            refreshListLD();
            clearFormGV();
            JOptionPane.showMessageDialog(this, "Đã Xóa Nhân Viên Mà Bạn Chọn"); 
        } catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this,"Vui lòng chọn đối tượng bạn muốn xóa" );
        }
    }
    
    public void deleteGVinDB() {
        if ( txtTenGiangVienThongTinLichDay.getText().length() <= 0 ) {
            JOptionPane.showMessageDialog(null, " Bạn chưa nhập STT", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
             String MaGV = txtTenGiangVienThongTinLichDay.getText();
    
            try {
                Connection conn = ConnectorHelper.connection();
                String SQL = "DELETE FROM lichday WHERE TenGV = ? ";
                PreparedStatement pstm = conn.prepareStatement(SQL); 

                pstm.setString(1, MaGV);
                pstm.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
       public void clearFormGV(){
       txtTenGiangVienThongTinLichDay.setText(null);
       txtMaMonHocThongTinLichDay.setText(null);
       txtTenMonHocThongTinLichDay.setText(null);
       txtPhongHocThongTinLichDay.setText(null);
       txtLopHocThongTinLichDay.setText(null);
       AreaGhiChuThongTinLichDay.setText(null);
       index = -1;
       JOptionPane.showMessageDialog(this, "Form đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    public void readFormGV() {
        try {
     
            String TenGV = txtTenGiangVienThongTinLichDay.getText().trim();    

        } catch (NumberFormatException e) {
           
            System.out.println("Lỗi: " + e.getMessage());
            return;
        }
    }
    public void FillToTableLD(int index){
        txtTenGiangVienThongTinLichDay.setText(listttld.get(index).getTenGiangVienthongtinlichday());
        txtMaMonHocThongTinLichDay.setText(listttld.get(index).getMaMonHocthongtinlichday());
        txtTenMonHocThongTinLichDay.setText(listttld.get(index).getTenMonHocthongtinlichday());
        txtPhongHocThongTinLichDay.setText(listttld.get(index).getPhongHocthongtinlichday());
        txtLopHocThongTinLichDay.setText(listttld.get(index).getLopHocThongTinLichDay());
        AreaGhiChuThongTinLichDay.setText(listttld.get(index).getGhiChuThongTinLichDay());
    }
    public void UpdateGV(){
    index = tblThongTinLichDay.getSelectedRow();
    try {
    ThongTinLichDay gv = listttld.get(index);    
    gv.setTenGiangVienthongtinlichday(txtTenGiangVienThongTinLichDay.getText());
    gv.setMaMonHocthongtinlichday(txtMaMonHocThongTinLichDay.getText());
    gv.setTenMonHocthongtinlichday(txtTenMonHocThongTinLichDay.getText());
    gv.setPhongHocthongtinlichday(txtPhongHocThongTinLichDay.getText());
    gv.setLopHocThongTinLichDay(txtLopHocThongTinLichDay.getText());
    gv.setGhiChuThongTinLichDay(AreaGhiChuThongTinLichDay.getText());
    fillToTableLichDay();
    JOptionPane.showMessageDialog(this, "<Đã Cập Nhật Sinh Viên Thành Công!>");
    
    } catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "<Vui Lòng Chọn Sinh Viên Trước Khi Cập Nhật!>");
     
    }
    
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        txtTenMonHocThongTinLichDay = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTenGiangVienThongTinLichDay = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMaMonHocThongTinLichDay = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPhongHocThongTinLichDay = new javax.swing.JTextField();
        txtLopHocThongTinLichDay = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaGhiChuThongTinLichDay = new javax.swing.JTextArea();
        btnThemThongTinLichDay = new javax.swing.JButton();
        btnXoaThongTinLichDay = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThongTinLichDay = new javax.swing.JTable();
        btnTim = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(730, 460));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Quản Lý Lịch Dạy");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        txtTenMonHocThongTinLichDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenMonHocThongTinLichDayActionPerformed(evt);
            }
        });
        getContentPane().add(txtTenMonHocThongTinLichDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 110, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Tên giảng viên");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        getContentPane().add(txtTenGiangVienThongTinLichDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Tên Môn Học");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));
        getContentPane().add(txtMaMonHocThongTinLichDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 110, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Mã Môn Học");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setText("Phòng Học");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setText("Lớp Học");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setText("Ghi Chú");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        txtPhongHocThongTinLichDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhongHocThongTinLichDayActionPerformed(evt);
            }
        });
        getContentPane().add(txtPhongHocThongTinLichDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 110, -1));

        txtLopHocThongTinLichDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLopHocThongTinLichDayActionPerformed(evt);
            }
        });
        getContentPane().add(txtLopHocThongTinLichDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 110, -1));

        AreaGhiChuThongTinLichDay.setColumns(20);
        AreaGhiChuThongTinLichDay.setRows(5);
        jScrollPane2.setViewportView(AreaGhiChuThongTinLichDay);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 250, -1));

        btnThemThongTinLichDay.setBackground(new java.awt.Color(153, 255, 255));
        btnThemThongTinLichDay.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnThemThongTinLichDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Đăng_Kí.png"))); // NOI18N
        btnThemThongTinLichDay.setText("Thêm");
        btnThemThongTinLichDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThongTinLichDayActionPerformed(evt);
            }
        });
        getContentPane().add(btnThemThongTinLichDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 110, 30));

        btnXoaThongTinLichDay.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaThongTinLichDay.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnXoaThongTinLichDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/12319540.png"))); // NOI18N
        btnXoaThongTinLichDay.setText("Xóa");
        btnXoaThongTinLichDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThongTinLichDayActionPerformed(evt);
            }
        });
        getContentPane().add(btnXoaThongTinLichDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, 30));

        btnThoat.setBackground(new java.awt.Color(255, 255, 204));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/image-removebg-preview.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        getContentPane().add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, -1, 30));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear2.png"))); // NOI18N
        jButton1.setText("CLear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, 30));

        tblThongTinLichDay.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblThongTinLichDay.setModel(new javax.swing.table.DefaultTableModel(
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
                "Tên giảng viên", "Mã Môn Học", "Tên Môn Học", "Phòng Học", "Lớp Học", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinLichDay.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblThongTinLichDayAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblThongTinLichDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinLichDayMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThongTinLichDay);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 690, 190));

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        getContentPane().add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 80, 30));

        jButton2.setText("Sửa");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nền.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenMonHocThongTinLichDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenMonHocThongTinLichDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMonHocThongTinLichDayActionPerformed

    private void txtPhongHocThongTinLichDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhongHocThongTinLichDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhongHocThongTinLichDayActionPerformed

    private void txtLopHocThongTinLichDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLopHocThongTinLichDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLopHocThongTinLichDayActionPerformed

    private void btnThemThongTinLichDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThongTinLichDayActionPerformed
        saveGVinDB();
    }//GEN-LAST:event_btnThemThongTinLichDayActionPerformed

    private void btnXoaThongTinLichDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThongTinLichDayActionPerformed
        XoaGV();
    }//GEN-LAST:event_btnXoaThongTinLichDayActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void tblThongTinLichDayAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblThongTinLichDayAncestorAdded
        fillToTableLichDay();
    }//GEN-LAST:event_tblThongTinLichDayAncestorAdded

    private void tblThongTinLichDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinLichDayMouseClicked
       index = tblThongTinLichDay.getSelectedRow();
        FillToTableLD(index);
    }//GEN-LAST:event_tblThongTinLichDayMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearFormGV();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String maMonHoc = txtMaMonHocThongTinLichDay.getText().trim(); // Lấy mã môn học từ ô nhập liệu
        String tenGiangVien = txtTenGiangVienThongTinLichDay.getText().trim(); // Lấy tên giảng viên từ ô nhập liệu

        if (maMonHoc.isEmpty() && tenGiangVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên Giảng Viên hoặc Mã Môn Học để tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConnectorHelper.connection()) {
            // Câu lệnh SQL tìm kiếm dựa trên một trong hai điều kiện
            String SQL = "SELECT TenGV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu FROM lichday WHERE MaMonHoc = ? OR TenGV = ?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, maMonHoc);
            pstm.setString(2, tenGiangVien);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                // Hiển thị dữ liệu lên form
                txtTenGiangVienThongTinLichDay.setText(rs.getString("TenGV"));
                txtMaMonHocThongTinLichDay.setText(rs.getString("MaMonHoc"));
                txtTenMonHocThongTinLichDay.setText(rs.getString("TenMonHoc"));
                txtPhongHocThongTinLichDay.setText(rs.getString("PhongHoc"));
                txtLopHocThongTinLichDay.setText(rs.getString("LopHoc"));
                AreaGhiChuThongTinLichDay.setText(rs.getString("GhiChu"));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy lịch dạy với thông tin đã nhập!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(Menu5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Menu5().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaGhiChuThongTinLichDay;
    private javax.swing.JButton btnThemThongTinLichDay;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaThongTinLichDay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblThongTinLichDay;
    private javax.swing.JTextField txtLopHocThongTinLichDay;
    private javax.swing.JTextField txtMaMonHocThongTinLichDay;
    private javax.swing.JTextField txtPhongHocThongTinLichDay;
    private javax.swing.JTextField txtTenGiangVienThongTinLichDay;
    private javax.swing.JTextField txtTenMonHocThongTinLichDay;
    // End of variables declaration//GEN-END:variables
}
