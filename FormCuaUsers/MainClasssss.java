/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormCuaUsers;

import View.Login;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class MainClasssss {
    public static void main(String[] args) {
        try {
            Connection conn = ConnectorHelper.connection();
            System.out.println("Ket noi CSDL thanh cong");
            Login login = new Login();
            login.setVisible(true);
        } catch(SQLInvalidAuthorizationSpecException e1) {
            JOptionPane.showMessageDialog(null, "Hệ thống dữ liệu có vấn đề", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
