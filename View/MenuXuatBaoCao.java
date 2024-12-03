import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.*;
import java.util.Vector;

public class MenuXuatBaoCao extends JFrame {
    private JTable tableGiangVien, tableSinhVien, tableMonHoc, tableLichDay, tableLichHoc;
    private JButton btnExportGiangVien, btnExportSinhVien, btnExportMonHoc, btnExportLichDay, btnExportLichHoc;

    private static final String JDBC_URL= "jdbc:mariadb://yeume-enterprise.edu.vn:3306/yeumeent_BQA_UsersDB";
    private static final String USER = "yeumeent_BuiQuocAnh";
    private static final String PASSWORD = ",3BB4.%nHU=M";
    public MenuXuatBaoCao() {
        initComponents();
        loadDataToTables();
    }

    private void initComponents() {
        setTitle("Xuất dữ liệu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Tạo JTable và nút
        tableGiangVien = new JTable(new DefaultTableModel(new String[]{"MaGV", "TenGV", "SoDienThoai", "Email"}, 0));
        tableSinhVien = new JTable(new DefaultTableModel(new String[]{"MaSV", "TenSV", "PhongHoc", "LopHoc","Email"}, 0));
        tableMonHoc = new JTable(new DefaultTableModel(new String[]{"MaMonHoc", "TenMonHoc", "ThoiLuong"}, 0));
        tableLichDay = new JTable(new DefaultTableModel(new String[]{"TenGV", "MaMonHoc", "TenMonHoc", "PhongHoc", "LopHoc","GhiChu"}, 0));
        tableLichHoc = new JTable(new DefaultTableModel(new String[]{"TenSV", "MaMonHoc", "TenMonHoc", "PhongHoc","LopHoc","GhiChu"}, 0));

        btnExportGiangVien = new JButton("Xuất Giảng Viên");
        btnExportSinhVien = new JButton("Xuất Sinh Viên");
        btnExportMonHoc = new JButton("Xuất Môn Học");
        btnExportLichDay = new JButton("Xuất Lịch Dạy");
        btnExportLichHoc = new JButton("Xuất Lịch Học");

        // Thêm JTable và nút vào giao diện
        add(new JLabel("Dữ liệu Giảng Viên:"));
        add(new JScrollPane(tableGiangVien));
        add(btnExportGiangVien);

        add(new JLabel("Dữ liệu Sinh Viên:"));
        add(new JScrollPane(tableSinhVien));
        add(btnExportSinhVien);

        add(new JLabel("Dữ liệu Môn Học:"));
        add(new JScrollPane(tableMonHoc));
        add(btnExportMonHoc);

        add(new JLabel("Dữ liệu Lịch Dạy:"));
        add(new JScrollPane(tableLichDay));
        add(btnExportLichDay);

        add(new JLabel("Dữ liệu Lịch Học:"));
        add(new JScrollPane(tableLichHoc));
        add(btnExportLichHoc);

        // Thêm ActionListener cho các nút
        btnExportGiangVien.addActionListener(e -> exportTableToExcel(tableGiangVien, "GiangVien.xlsx"));
        btnExportSinhVien.addActionListener(e -> exportTableToExcel(tableSinhVien, "SinhVien.xlsx"));
        btnExportMonHoc.addActionListener(e -> exportTableToExcel(tableMonHoc, "MonHoc.xlsx"));
        btnExportLichDay.addActionListener(e -> exportTableToExcel(tableLichDay, "LichDay.xlsx"));
        btnExportLichHoc.addActionListener(e -> exportTableToExcel(tableLichHoc, "LichHoc.xlsx"));
    }

    private void loadDataToTables() {
        loadDataToTable("SELECT MaGV, TenGV, SoDienThoai, Email FROM giangvien", (DefaultTableModel) tableGiangVien.getModel());
        loadDataToTable("SELECT MaSV, TenSV, PhongHoc, LopHoc, Email FROM sinhvien", (DefaultTableModel) tableSinhVien.getModel());
        loadDataToTable("SELECT MaMonHoc, TenMonHoc, ThoiLuong FROM monhoc", (DefaultTableModel) tableMonHoc.getModel());
        loadDataToTable("SELECT TenGV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu FROM lichday", (DefaultTableModel) tableLichDay.getModel());
        loadDataToTable("SELECT TenSV, MaMonHoc, TenMonHoc, PhongHoc, LopHoc, GhiChu FROM lichhoc", (DefaultTableModel) tableLichHoc.getModel());
    }

    private void loadDataToTable(String query, DefaultTableModel model) {
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void exportTableToExcel(JTable table, String fileName) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(fileName)) {

            Sheet sheet = workbook.createSheet("Sheet1");
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Xuất tiêu đề cột
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

            // Xuất dữ liệu
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    Object value = model.getValueAt(i, j);
                    cell.setCellValue(value == null ? "" : value.toString());
                }
            }

            workbook.write(fileOut);
            JOptionPane.showMessageDialog(this, "Đã xuất file: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi xuất file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuXuatBaoCao().setVisible(true));
    }
}
