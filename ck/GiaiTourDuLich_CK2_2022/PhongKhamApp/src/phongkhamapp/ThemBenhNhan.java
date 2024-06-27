package phongkhamapp;

import javax.swing.*;
import java.sql.*;

public class ThemBenhNhan extends JFrame {

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
        setup();
        
//       Xử lý combobox
        String sql = "SELECT DISTINCT GIOITINH FROM BENHNHAN";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cboGioiTinh.addItem(rs.getString("GIOITINH"));
            }
        } catch (SQLException e) {
        }

//      Xử lý thêm
        btnThem.addActionListener(e -> {
            String sql1 = "INSERT INTO BENHNHAN (MABN, TENBN, NGSINH, DCHI, DTHOAI, GIOITINH) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                ps1.setString(1, txtMaBenhNhan.getText());
                ps1.setString(2, txtTenBenhNhan.getText());
                ps1.setString(3, txtNgaySinh.getText());
                ps1.setString(4, txtDiaChi.getText());
                ps1.setString(5, txtDienThoai.getText());
                ps1.setString(6, cboGioiTinh.getSelectedItem().toString());
                ps1.executeUpdate();
                JOptionPane.showMessageDialog(this, "Thêm bệnh nhân thành công!");
            } catch (SQLException ex) {
           }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThemBenhNhan().setVisible(true));
    }

    private void setup() {

        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "PHONGKHAM", "password");
        } catch (SQLException e) {
        }
//        Không cần
        setTitle("Thêm Tour Du Lịch");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lbMaBenhNhan.setBounds(50, 30, 100, 25);
        add(lbMaBenhNhan);
        txtMaBenhNhan.setBounds(150, 30, 200, 25);
        add(txtMaBenhNhan);

        lbTenBenhNhan.setBounds(50, 70, 100, 25);
        add(lbTenBenhNhan);
        txtTenBenhNhan.setBounds(150, 70, 200, 25);
        add(txtTenBenhNhan);

        lbNgaySinh.setBounds(50, 110, 100, 25);
        add(lbNgaySinh);
        txtNgaySinh.setBounds(150, 110, 200, 25);
        add(txtNgaySinh);

        lbDiaChi.setBounds(50, 150, 100, 25);
        add(lbDiaChi);
        txtDiaChi.setBounds(150, 150, 200, 25);
        add(txtDiaChi);

        lbDienThoai.setBounds(50, 190, 100, 25);
        add(lbDienThoai);
        txtDienThoai.setBounds(150, 190, 200, 25);
        add(txtDienThoai);

        lbGioiTinh.setBounds(50, 230, 100, 25);
        add(lbGioiTinh);
        cboGioiTinh.setBounds(150, 230, 200, 25);
        add(cboGioiTinh);

        btnThem.setBounds(150, 270, 100, 30);
        add(btnThem);
    }
}
