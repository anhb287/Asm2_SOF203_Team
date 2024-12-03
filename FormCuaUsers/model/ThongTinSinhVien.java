/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormCuaUsers.model;

/**
 *
 * @author Gigabyte
 */
public class ThongTinSinhVien {
    private String maSinhVienthongtinsinhvien;
    private String tenSinhVienthongtinsinhvien;
    private String lopHocthongtinsinhvien; 
    private String phongHocthongtinsinhvien; 
    private String emailthongtinsinhvien;
    
    public ThongTinSinhVien() {
        
    }

    public ThongTinSinhVien(String maSinhVienthongtinsinhvien, String tenSinhVienthongtinsinhvien, String lopHocthongtinsinhvien, String phongHocthongtinsinhvien, String emailthongtinsinhvien) {
        this.maSinhVienthongtinsinhvien = maSinhVienthongtinsinhvien;
        this.tenSinhVienthongtinsinhvien = tenSinhVienthongtinsinhvien;
        this.lopHocthongtinsinhvien = lopHocthongtinsinhvien;
        this.phongHocthongtinsinhvien = phongHocthongtinsinhvien;
        this.emailthongtinsinhvien = emailthongtinsinhvien;
    }

    public String getMaSinhVienthongtinsinhvien() {
        return maSinhVienthongtinsinhvien;
    }

    public void setMaSinhVienthongtinsinhvien(String maSinhVienthongtinsinhvien) {
        this.maSinhVienthongtinsinhvien = maSinhVienthongtinsinhvien;
    }

    public String getTenSinhVienthongtinsinhvien() {
        return tenSinhVienthongtinsinhvien;
    }

    public void setTenSinhVienthongtinsinhvien(String tenSinhVienthongtinsinhvien) {
        this.tenSinhVienthongtinsinhvien = tenSinhVienthongtinsinhvien;
    }

    public String getLopHocthongtinsinhvien() {
        return lopHocthongtinsinhvien;
    }

    public void setLopHocthongtinsinhvien(String lopHocthongtinsinhvien) {
        this.lopHocthongtinsinhvien = lopHocthongtinsinhvien;
    }

    public String getPhongHocthongtinsinhvien() {
        return phongHocthongtinsinhvien;
    }

    public void setPhongHocthongtinsinhvien(String phongHocthongtinsinhvien) {
        this.phongHocthongtinsinhvien = phongHocthongtinsinhvien;
    }

    public String getEmailthongtinsinhvien() {
        return emailthongtinsinhvien;
    }

    public void setEmailthongtinsinhvien(String emailthongtinsinhvien) {
        this.emailthongtinsinhvien = emailthongtinsinhvien;
    }

    
    
}
