// Câu a
public class ThemBenhNhan {
    Connection conn;

    JLabel lbMaBenhNhan = new JLabel("Mã Bệnh Nhân");
    JLabel lbTenBenhNhan = new JLabel("Tên bệnh nhân");
    JLabel lbNgaySinh = new JLabel("Ngày sinh");
    JLabel lbDiaChi = new JLabel("Địa chỉ");
    JLabel lbDienThoai = new JLabel("Điện thoại");
    JLabel lbGioiTinh = new JLabel("Giới tính");

    JTextField txtMaBenhNhan = new JTextField();
    JTextField txtTenBenhNhan = new JTextField();
    JTextField txtNgaySinh = new JTextField();
    JTextField txtDiaChi = new JTextField();
    JTextField txtDienThoai = new JTextField();

    JComboBox<String> cboGioiTinh = new JComboBox<>();
    JButton btnThem = new JButton("Thêm");

    public ThemBenhNhan() {

        // Xử lý combobox
        String sql = "SELECT DISTINCT GIOITINH FROM BENHNHAN";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            cboGioiTinh.addItem(rs.getString("GIOITINH"));
        }

        // Xử lý thêm
        btnThem.addActionListener(e -> {
            String sql1 = "INSERT INTO BENHNHAN (MABN, TENBN, NGSINH, DCHI, DTHOAI, GIOITINH) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, txtMaBenhNhan.getText());
            ps1.setString(2, txtTenBenhNhan.getText());
            ps1.setString(3, txtNgaySinh.getText());
            ps1.setString(4, txtDiaChi.getText());
            ps1.setString(5, txtDienThoai.getText());
            ps1.setString(6, cboGioiTinh.getSelectedItem().toString());
            ps1.executeUpdate();
        });
    }

    public static void main(String[] args) {
        new ThemBenhNhan();
    }
}
