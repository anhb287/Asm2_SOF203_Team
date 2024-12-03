/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormCuaUsers.model;

/**
 *
 * @author Gigabyte
 */
public class ThongTinLichHoc {
    private String TenSinhVienLichHoc;
    private String MaMonHocLichHoc;
    private String TenMonHocLichHoc;
    private String PhongHocLichHoc;
    private String LopHocLichHoc;
    private String GhiChuLichHoc;
    
    public ThongTinLichHoc(){
        
    }

    public ThongTinLichHoc( String TenSinhVienLichHoc, String MaMonHocLichHoc, String TenMonHocLichHoc, String PhongHocLichHoc, String LopHocLichHoc, String GhiChuLichHoc) {
        this.TenSinhVienLichHoc = TenSinhVienLichHoc;
        this.MaMonHocLichHoc = MaMonHocLichHoc;
        this.TenMonHocLichHoc = TenMonHocLichHoc;
        this.PhongHocLichHoc = PhongHocLichHoc;
        this.LopHocLichHoc = LopHocLichHoc;
        this.GhiChuLichHoc = GhiChuLichHoc;
    }

    public String getTenSinhVienLichHoc() {
        return TenSinhVienLichHoc;
    }

    public void setTenSinhVienLichHoc(String TenSinhVienLichHoc) {
        this.TenSinhVienLichHoc = TenSinhVienLichHoc;
    }

    public String getMaMonHocLichHoc() {
        return MaMonHocLichHoc;
    }

    public void setMaMonHocLichHoc(String MaMonHocLichHoc) {
        this.MaMonHocLichHoc = MaMonHocLichHoc;
    }

    public String getTenMonHocLichHoc() {
        return TenMonHocLichHoc;
    }

    public void setTenMonHocLichHoc(String TenMonHocLichHoc) {
        this.TenMonHocLichHoc = TenMonHocLichHoc;
    }

    public String getPhongHocLichHoc() {
        return PhongHocLichHoc;
    }

    public void setPhongHocLichHoc(String PhongHocLichHoc) {
        this.PhongHocLichHoc = PhongHocLichHoc;
    }

    public String getLopHocLichHoc() {
        return LopHocLichHoc;
    }

    public void setLopHocLichHoc(String LopHocLichHoc) {
        this.LopHocLichHoc = LopHocLichHoc;
    }

    public String getGhiChuLichHoc() {
        return GhiChuLichHoc;
    }

    public void setGhiChuLichHoc(String GhiChuLichHoc) {
        this.GhiChuLichHoc = GhiChuLichHoc;
    }
    
    
}
