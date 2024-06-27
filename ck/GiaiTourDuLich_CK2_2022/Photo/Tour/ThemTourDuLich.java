// Câu a
public class ThemTourDuLich {
    Connection conn;

    JLabel lbTenTour = new JLabel("Tên Tour");
    JLabel lbNgayKhoiHanh = new JLabel("Ngày Khởi Hành");
    JLabel lbSoNgay = new JLabel("Số Ngày");
    JLabel lbSoDem = new JLabel("Số Đêm");
    JLabel lbGia = new JLabel("Giá");

    JTextField txtTenTour = new JTextField();
    JTextField txtNgayKhoiHanh = new JTextField();
    JTextField txtSoNgay = new JTextField();
    JTextField txtSoDem = new JTextField();
    JTextField txtGia = new JTextField();

    JButton btnThem = new JButton("Thêm");

    public ThemTourDuLich() {      
        btnThem.addActionListener(e -> {
            // TOUR(MaTour, TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia)
            String sql = "INSERT INTO TOUR(TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql)

            ps.setString(1, txtTenTour.getText());
            ps.setString(2, txtNgayKhoiHanh.getText());
            ps.setInt(3, Integer.parseInt(txtSoNgay.getText()));
            ps.setInt(4, Integer.parseInt(txtSoDem.getText()));
            ps.setDouble(5, Double.parseDouble(txtGia.getText()));
            
            ps.executeUpdate();                
        });
    }

    public static void main(String[] args) {
        new ThemTourDuLich();
    }
}
