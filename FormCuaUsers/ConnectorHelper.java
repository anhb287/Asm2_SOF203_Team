/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormCuaUsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectorHelper {
    private static final String JDBC_URL= "jdbc:mariadb://yeume-enterprise.edu.vn:3306/yeumeent_BQA_UsersDB";
    private static final String USER = "yeumeent_BuiQuocAnh";
    private static final String PASSWORD = ",3BB4.%nHU=M";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    
}
