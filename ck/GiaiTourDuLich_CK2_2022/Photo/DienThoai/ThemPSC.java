// Câu b
public class ThemPSC {
    Connection conn;

    JLabel lbImei = new JLabel("Số IMEI");
    JLabel lbTenDT = new JLabel("Tên điện thoại");
    JLabel lbHoTenKH = new JLabel("Họ tên khách hàng");
    JLabel lbNgayLap = new JLabel("Ngày lập");
    JLabel lbNoiDung = new JLabel("Nội dung sửa chữa");

    JTextField txtImei = new JTextField();
    JTextField txtTenDT = new JTextField();
    JTextField txtHoTenKH = new JTextField();
    JTextField txtNgayLap = new JTextField();

    JTextArea txtNoiDung = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(txtNoiDung);
    JButton btnThem = new JButton("Thêm");

    public ThemPSC() {
        txtTenDT.setEditable(false);
        txtHoTenKH.setEditable(false);

        txtImei.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String sql1 = "SELECT * FROM DIENTHOAI WHERE IMEI = ?";
                    PreparedStatement pst = conn.prepareStatement(sql1);
                    pst.setString(1, txtImei.getText().trim());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        txtTenDT.setText(rs.getString("TENDT"));
                        txtHoTenKH.setText(rs.getString("HOTENKH"));
                    }
                }
            }
        });

        btnThem.addActionListener(e -> {
            String sql = "INSERT INTO SUACHUA(NGAYLAP, NOIDUNG, IMEI, TONGTIEN) VALUES (?, ?, ?, 0)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNgayLap.getText());
            ps.setString(2, txtNoiDung.getText());
            ps.setString(3, txtImei.getText());
            ps.executeUpdate();
        });
    }

    public static void main(String[] args) {
        new ThemPSC();
    }
}