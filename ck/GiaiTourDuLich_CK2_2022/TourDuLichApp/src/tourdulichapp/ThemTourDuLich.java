package tourdulichapp;
import javax.swing.*;
import java.sql.*;

public class ThemTourDuLich extends JFrame {

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
        setup();       
        luu();
    }

    private void luu() {
        btnThem.addActionListener(e -> {
            String sql = "INSERT INTO TOUR(TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, txtTenTour.getText());
                ps.setString(2, txtNgayKhoiHanh.getText());
                ps.setInt(3, Integer.parseInt(txtSoNgay.getText()));
                ps.setInt(4, Integer.parseInt(txtSoDem.getText()));
                ps.setDouble(5, Double.parseDouble(txtGia.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Thêm tour thành công!");
            } catch (SQLException ex) {
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThemTourDuLich().setVisible(true));
    }

    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "FLY_THE_END12A", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Không cần
        setTitle("Thêm Tour Du Lịch");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lbTenTour.setBounds(50, 30, 100, 25);
        add(lbTenTour);
        txtTenTour.setBounds(150, 30, 200, 25);
        add(txtTenTour);

        lbNgayKhoiHanh.setBounds(50, 70, 100, 25);
        add(lbNgayKhoiHanh);
        txtNgayKhoiHanh.setBounds(150, 70, 200, 25);
        add(txtNgayKhoiHanh);

        lbSoNgay.setBounds(50, 110, 100, 25);
        add(lbSoNgay);
        txtSoNgay.setBounds(150, 110, 200, 25);
        add(txtSoNgay);

        lbSoDem.setBounds(50, 150, 100, 25);
        add(lbSoDem);
        txtSoDem.setBounds(150, 150, 200, 25);
        add(txtSoDem);

        lbGia.setBounds(50, 190, 100, 25);
        add(lbGia);
        txtGia.setBounds(150, 190, 200, 25);
        add(txtGia);

        btnThem.setBounds(150, 230, 100, 30);
        add(btnThem);
    }
}
