// Câu a
public class ThemCuaHang {
    Connection conn;

    JLabel lbTenCH = new JLabel("Tên cửa hàng");
    JLabel lbChuCH = new JLabel("Chủ cửa hàng");
    JLabel lbDiaChi = new JLabel("Địa chỉ");
    JLabel lbSDT = new JLabel("Số điện thoại");

    JTextField txtTenCH = new JTextField();
    JTextField txtChuCH = new JTextField();
    JTextField txtDiaChi = new JTextField();
    JTextField txtSDT = new JTextField();

    JButton btnThem = new JButton("Thêm");

    public ThemCuaHang() {
        btnThem.addActionListener(e -> {
            // CUAHANG (MaCH, TenCH, ChuCH, DiaChi, SDT)
            String sql = "INSERT INTO CUAHANG(TenCH, ChuCH, DiaChi, SDT) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtTenCH.getText());
            ps.setString(2, txtChuCH.getText());
            ps.setString(3, txtDiaChi.getText());
            ps.setString(4, txtSDT.getText());
            ps.executeUpdate();
        });
    }

    public static void main(String[] args) {
        new ThemCuaHang();
    }
}
