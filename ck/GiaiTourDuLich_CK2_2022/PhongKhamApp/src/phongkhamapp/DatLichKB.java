package phongkhamapp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DatLichKB extends JFrame {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    JLabel lbMaBenhNhan = new JLabel("Mã Bệnh Nhân");
    JLabel lbTenBenhNhan = new JLabel("Tên bệnh nhân");
    JLabel lbNgayKham = new JLabel("Ngày sinh");
    JLabel lbYeuCauKham = new JLabel("Địa chỉ");
    JLabel lbBacSiKham = new JLabel("Giới tính");

    JTextField txtMaBenhNhan = new JTextField();
    JTextField txtTenBenhNhan = new JTextField();
    JTextField txtNgayKham = new JTextField();
    JTextField txtYeuCauKham = new JTextField();

    JComboBox<String> cboBacSiKham = new JComboBox<>();
    JButton btnThem = new JButton("Đặt lịch khám");

    public DatLichKB() {
        setup();
        txtTenBenhNhan.setEditable(false);

//        Load Mã bệnh nhân
        txtMaBenhNhan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String sql = "SELECT TENBN FROM BENHNHAN WHERE MABN = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, txtMaBenhNhan.getText());
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            txtTenBenhNhan.setText(rs.getString(1));
                        }

                        String sql2 = "SELECT TENBS FROM BACSI";
                        PreparedStatement ps2 = conn.prepareStatement(sql2);
                        ResultSet rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            cboBacSiKham.addItem(rs2.getString(1));
                        }
                    } catch (SQLException ex) {
                    }
                }
            }
        });

        btnThem.addActionListener(e -> {
            try {
                // Lấy mã bác sĩ từ tên bác sĩ được chọn
                String sql = "SELECT MABS FROM BACSI WHERE TENBS = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, cboBacSiKham.getSelectedItem().toString());
                rs = ps.executeQuery();
                String maBacSi = "";
                if (rs.next()) {
                    maBacSi = rs.getString(1);
                }

                // Thêm lịch khám cho bệnh nhân
                String sql2 = "INSERT INTO KHAMBENH (MABN, MABS, NGAYKHAM, YEUCAUKHAM) VALUES(?, ?, ?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, txtMaBenhNhan.getText());
                ps2.setString(2, maBacSi);
                ps2.setString(3, txtNgayKham.getText());
                ps2.setString(4, txtYeuCauKham.getText());
                ps2.executeUpdate(); // Thực hiện câu lệnh insert

                JOptionPane.showMessageDialog(null, "Đặt lịch khám thành công!");
            } catch (SQLException ex) {             
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatLichKB().setVisible(true));
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

        lbNgayKham.setBounds(50, 110, 100, 25);
        add(lbNgayKham);
        txtNgayKham.setBounds(150, 110, 200, 25);
        add(txtNgayKham);

        lbYeuCauKham.setBounds(50, 150, 100, 25);
        add(lbYeuCauKham);
        txtYeuCauKham.setBounds(150, 150, 200, 25);
        add(txtYeuCauKham);

        lbBacSiKham.setBounds(50, 190, 100, 25);
        add(lbBacSiKham);
        cboBacSiKham.setBounds(150, 190, 200, 25);
        add(cboBacSiKham);

        btnThem.setBounds(150, 270, 100, 30);
        add(btnThem);
    }
}
