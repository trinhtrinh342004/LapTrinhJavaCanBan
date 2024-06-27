package dienthoaiapp;

import java.sql.*;
import javax.swing.*;

public class ThemDienThoai extends JFrame {

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
        setup();
//      load dữ liệu vào combobox
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT DISTINCT LOAIDT FROM DIENTHOAI")) {
            while (rs.next()) {
                cbLoaiDT.addItem(rs.getString("LOAIDT"));
            }
        } catch (SQLException ex) {
        }

//        Xử lý nhấn btnThem
        btnThem.addActionListener(e -> {
            String sql = "INSERT INTO DIENTHOAI(IMEI, TENDT, LOAIDT, HOTENKH, SDTKH) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, Integer.parseInt(txtImei.getText()));
                ps.setString(2, txtTenDT.getText());
                ps.setString(3, cbLoaiDT.getSelectedItem());
                ps.setString(4, txtHoTenKH.getText());
                ps.setString(5, txtSdtKH.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Thêm thông tin điện thoại thành công!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThemDienThoai().setVisible(true));
    }

    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "DIENTHOAI", "password");
        } catch (SQLException e) {
        }
        setTitle("Thông tin điện thoại");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

//        setBounds(left, top , w, h)
        lbImei.setBounds(50, 30, 200, 25);
        add(lbImei);
        txtImei.setBounds(250, 30, 200, 25);
        add(txtImei);

        lbTenDT.setBounds(50, 70, 200, 25);
        add(lbTenDT);
        txtTenDT.setBounds(250, 70, 200, 25);
        add(txtTenDT);

        lbLoaiDT.setBounds(50, 110, 200, 25);
        add(lbLoaiDT);
        cbLoaiDT.setBounds(250, 110, 200, 25);
        add(cbLoaiDT);

        lbHoTenKH.setBounds(50, 150, 200, 25);
        add(lbHoTenKH);
        txtHoTenKH.setBounds(250, 150, 200, 25);
        add(txtHoTenKH);

        lbSdtKH.setBounds(50, 190, 200, 25);
        add(lbSdtKH);
        txtSdtKH.setBounds(250, 190, 200, 25);
        add(txtSdtKH);

        btnThem.setBounds(200, 230, 100, 40);
        add(btnThem);
    }
}
