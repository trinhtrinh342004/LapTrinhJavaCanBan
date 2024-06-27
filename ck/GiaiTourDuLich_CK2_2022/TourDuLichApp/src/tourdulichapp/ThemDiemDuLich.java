package tourdulichapp;

import javax.swing.*;
import java.sql.*;

public class ThemDiemDuLich extends JFrame {

    Connection conn;

    JLabel tinhTPLabel = new JLabel("Tỉnh/TP:");
    JLabel tenDDLLabel = new JLabel("Tên Điểm DL:");
    JLabel dacTrungLabel = new JLabel("Đặc Trưng:");

    JComboBox<String> cbTinhTP = new JComboBox<>();
    JTextField txtTenDiemDL = new JTextField();
    JTextField txtDacTrung = new JTextField();

    JButton btnThem = new JButton("Thêm");

    public ThemDiemDuLich() {
        setup();
// Lấy dữ liệu từ bảng TinhTP
        handleTinhTP();
//      Xử lý lưu       
        luu();
    }

    private void handleTinhTP() {
        String sql1 = "SELECT TenTTP FROM TINHTP";
        try (PreparedStatement ps = conn.prepareStatement(sql1); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cbTinhTP.addItem(rs.getString("TenTTP"));
            }
        } catch (SQLException ex) {
        }

    }

    private void luu() {
        btnThem.addActionListener(e -> {
            String sql2 = "SELECT MaTTP FROM TINHTP WHERE TenTTP = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(sql2)) {
                ps1.setString(1, cbTinhTP.getSelectedItem().toString());
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    String maTTP = rs1.getString("MaTTP");
                    String sql3 = "INSERT INTO DIEMDL(TenDDL, MaTTP, DacTrung) VALUES (?, ?, ?)";
                    PreparedStatement ps2 = conn.prepareStatement(sql3);
                    ps2.setString(1, txtTenDiemDL.getText());
                    ps2.setString(2, maTTP);
                    ps2.setString(3, txtDacTrung.getText());
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Thêm điểm du lịch thành công!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThemDiemDuLich().setVisible(true));
    }

    private void setup() {

        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "FLY_THE_END12A", "password");
        } catch (SQLException e) {
        }
        setTitle("Thêm Điểm Du Lịch");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        tinhTPLabel.setBounds(50, 30, 100, 25);
        add(tinhTPLabel);
        cbTinhTP.setBounds(150, 30, 200, 25);
        add(cbTinhTP);

        tenDDLLabel.setBounds(50, 70, 100, 25);
        add(tenDDLLabel);

        txtTenDiemDL.setBounds(150, 70, 200, 25);
        add(txtTenDiemDL);

        dacTrungLabel.setBounds(50, 110, 100, 25);
        add(dacTrungLabel);

        txtDacTrung.setBounds(150, 110, 200, 25);
        add(txtDacTrung);

        btnThem.setBounds(150, 150, 100, 30);
        add(btnThem);
    }
}
