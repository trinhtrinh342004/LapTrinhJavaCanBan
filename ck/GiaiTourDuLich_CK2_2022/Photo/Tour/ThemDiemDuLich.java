// Câu b
public class ThemDiemDuLich {
    Connection conn;

    JLabel tinhTPLabel = new JLabel("Tỉnh/TP:");
    JLabel tenDDLLabel = new JLabel("Tên Điểm DL:");
    JLabel dacTrungLabel = new JLabel("Đặc Trưng:");

    JComboBox<String> cbTinhTP = new JComboBox<>();
    JTextField txtTenDiemDL = new JTextField();
    JTextField txtDacTrung = new JTextField();

    JButton btnThem = new JButton("Thêm");

    public ThemDiemDuLich() {
        // TOUR(MaTour, TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia)
        // TINHTP (MaTTP, TenTTP)
        // DIEMDL(MaDDL, TenDDL, MaTTP, DacTrung)
        // CHITIET (MaTour, MaDDL)

        // Lấy dữ liệu từ bảng TinhTP
        String sql1 = "SELECT TenTTP FROM TINHTP";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()) {
            cbTinhTP.addItem(rs.getString("TenTTP"));
        }

        // Xử lý lưu
        btnThem.addActionListener(e -> {
            String sql2 = "SELECT MaTTP FROM TINHTP WHERE TenTTP = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, cbTinhTP.getSelectedItem().toString());
            ResultSet rs2 = ps2.executeQuery();
            if (rs1.next()) {
                String maTTP = rs2.getString("MaTTP");
                String sql3 = "INSERT INTO DIEMDL(TenDDL, MaTTP, DacTrung) VALUES (?, ?, ?)";
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1, txtTenDiemDL.getText());
                ps3.setString(2, maTTP);
                ps3.setString(3, txtDacTrung.getText());
                ps3.executeUpdate();
            }
        });
    }

    public static void main(String[] args) {
        new ThemDiemDuLich();
    }
}
