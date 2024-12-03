/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormCuaUsers.model;

/**
 *
 * @author Gigabyte
 */
public class MonHoc {
    private String MaMonHoc;
    private String TenMonHoc;
    private String ThoiLuongMonHoc;
    
    public MonHoc() {
    }

    public MonHoc(String MaMonHoc, String TenMonHoc, String ThoiLuongMonHoc) {
        this.MaMonHoc = MaMonHoc;
        this.TenMonHoc = TenMonHoc;
        this.ThoiLuongMonHoc = ThoiLuongMonHoc;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String TenMonHoc) {
        this.TenMonHoc = TenMonHoc;
    }

    public String getThoiLuongMonHoc() {
        return ThoiLuongMonHoc;
    }

    public void setThoiLuongMonHoc(String ThoiLuongMonHoc) {
        this.ThoiLuongMonHoc = ThoiLuongMonHoc;
    }
    
}
