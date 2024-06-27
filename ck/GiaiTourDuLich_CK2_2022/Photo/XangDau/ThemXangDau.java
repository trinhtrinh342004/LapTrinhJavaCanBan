// Câu b
public class ThemXangDau {
    Connection conn;

    JLabel lbLoaiXD = new JLabel("Loại xăng dầu");
    JLabel lbTenXD = new JLabel("Tên xăng dầu");
    JLabel lbGiaCoSo = new JLabel("Giá cơ sở");

    JComboBox<String> cbLoaiXD = new JComboBox<>();
    JTextField txtTenXD = new JTextField();
    JTextField txtGiaCoSo = new JTextField();

    JButton btnThem = new JButton("Thêm");

    public ThemXangDau() {
        // LOAIXD (MaLXD, TenLXD)
        // XANGDAU (MaXD, TenXD, GiaCoSo, MaLXD)
        // CUAHANG (MaCH, TenCH, ChuCH, DiaChi, SDT)
        // NHAP (MaCH, MaXD, NgayNhap, SoLuong, GiaNhap)

        // Load dữ liệu vào combobox
        String sql1 = "SELECT TenLXD FROM LOAIXD";
        PreparedStatement ps = conn.prepareStatement(sql1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            cbLoaiXD.addItem(rs.getString("TenLXD"));
        }

        // Thêm điểm du lịch DIEMDL(MaDDL, TenDDL, MaTTP, DacTrung)
        btnThem.addActionListener(e -> {
            String sql2 = "SELECT MaLXD FROM LOAIXD WHERE TenLXD = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql2);
            ps1.setString(1, cbLoaiXD.getSelectedItem().toString());
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                String maLXD = rs1.getString("MaLXD");
                String sql3 = "INSERT INTO XANGDAU(TenXD, GiaCoSo, MaLXD) VALUES (?, ?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sql3);
                ps2.setString(1, txtTenXD.getText());
                ps2.setFloat(2, Float.parseFloat(txtGiaCoSo.getText()));
                ps2.setString(3, maLXD);
                ps2.executeUpdate();
            }
        });
    }

    public static void main(String[] args) {
        new ThemXangDau();
    }
}
