/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormCuaUsers.model;

/**
 *
 * @author Gigabyte
 */
public class ThongTinGiangVien {
    private String MaGiangVienThongTinGiangVien;
    private String TenGiangVienThongTinGiangVien;
    private String SoDienThoaiThongTinGiangVien;
    private String EmailThongTinGiangVien;
    
    public ThongTinGiangVien(){
        
    }

    public ThongTinGiangVien(String MaGiangVienThongTinGiangVien, String TenGiangVienThongTinGiangVien, String SoDienThoaiThongTinGiangVien, String EmailThongTinGiangVien) {
        this.MaGiangVienThongTinGiangVien = MaGiangVienThongTinGiangVien;
        this.TenGiangVienThongTinGiangVien = TenGiangVienThongTinGiangVien;
        this.SoDienThoaiThongTinGiangVien = SoDienThoaiThongTinGiangVien;
        this.EmailThongTinGiangVien = EmailThongTinGiangVien;
    }

    public String getMaGiangVienThongTinGiangVien() {
        return MaGiangVienThongTinGiangVien;
    }

    public void setMaGiangVienThongTinGiangVien(String MaGiangVienThongTinGiangVien) {
        this.MaGiangVienThongTinGiangVien = MaGiangVienThongTinGiangVien;
    }

    public String getTenGiangVienThongTinGiangVien() {
        return TenGiangVienThongTinGiangVien;
    }

    public void setTenGiangVienThongTinGiangVien(String TenGiangVienThongTinGiangVien) {
        this.TenGiangVienThongTinGiangVien = TenGiangVienThongTinGiangVien;
    }

    public String getSoDienThoaiThongTinGiangVien() {
        return SoDienThoaiThongTinGiangVien;
    }

    public void setSoDienThoaiThongTinGiangVien(String SoDienThoaiThongTinGiangVien) {
        this.SoDienThoaiThongTinGiangVien = SoDienThoaiThongTinGiangVien;
    }

    public String getEmailThongTinGiangVien() {
        return EmailThongTinGiangVien;
    }

    public void setEmailThongTinGiangVien(String EmailThongTinGiangVien) {
        this.EmailThongTinGiangVien = EmailThongTinGiangVien;
    }
    
}
