// Câu a
public class ThemDienThoai {
    Connection conn;

    JLabel lbImei = new JLabel("Số IMEI");
    JLabel lbTenDT = new JLabel("Tên điện thoại");
    JLabel lbLoaiDT = new JLabel("Loại điện thoại");
    JLabel lbHoTenKH = new JLabel("Họ tên khách hàng");
    JLabel lbSdtKH = new JLabel("Số điện thoại khách hàng");

    JTextField txtImei = new JTextField();
    JTextField txtTenDT = new JTextField();
    JTextField txtHoTenKH = new JTextField();
    JTextField txtSdtKH = new JTextField();

    JComboBox<String> cbLoaiDT = new JComboBox<>();

    JButton btnThem = new JButton("Thêm");

    public ThemDienThoai() {
        // load dữ liệu vào combobox
        String sql = "SELECT DISTINCT LOAIDT FROM DIENTHOAI";
        PreparedStatement ps1 = conn.createStatement();
        ResultSet rs1 = ps1.executeQuery(sql);
        while (rs1.next()) {
            cbLoaiDT.addItem(rs.getString("LOAIDT"));
        }

        // Xử lý nhấn btnThem
        btnThem.addActionListener(e -> {
            String sql1 = "INSERT INTO DIENTHOAI(IMEI, TENDT, LOAIDT, HOTENKH, SDTKH) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setInt(1, Integer.parseInt(txtImei.getText()));
            ps.setString(2, txtTenDT.getText());
            ps.setString(3, (String) cbLoaiDT.getSelectedItem());
            ps.setString(4, txtHoTenKH.getText());
            ps.setString(5, txtSdtKH.getText());
            ps.executeUpdate();
        });
    }

    public static void main(String[] args) {
        new ThemDienThoai();
    }
}
