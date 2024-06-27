package dienthoaiapp;

import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThemPSC extends JFrame {

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
        setup();
        
        txtTenDT.setEditable(false);
        txtHoTenKH.setEditable(false);

        txtImei.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String imei = txtImei.getText().trim();
                    try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM DIENTHOAI WHERE IMEI = ?")) {
                        pst.setString(1, imei);
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            txtTenDT.setText(rs.getString("TENDT"));
                            txtHoTenKH.setText(rs.getString("HOTENKH"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ThemPSC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        btnThem.addActionListener(e -> {
            String sql = "INSERT INTO SUACHUA(NGAYLAP, NOIDUNG, IMEI, TONGTIEN) VALUES (?, ?, ?, 0)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, txtNgayLap.getText());
                ps.setString(2, txtNoiDung.getText());
                ps.setString(3, txtImei.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(ThemPSC.this, "Thêm sửa chữa thành công!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(ThemPSC.this, "Có lỗi xảy ra, vui lòng thử lại.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThemPSC().setVisible(true);
        });
    }
    
    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "DIENTHOAI", "password");
        } catch (SQLException e) {
        }
        setTitle("Phiếu sửa chữa");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lbImei.setBounds(50, 30, 200, 25);
        add(lbImei);
        txtImei.setBounds(250, 30, 200, 25);
        add(txtImei);

        lbTenDT.setBounds(50, 70, 200, 25);
        add(lbTenDT);
        txtTenDT.setBounds(250, 70, 200, 25);
        add(txtTenDT);

        lbHoTenKH.setBounds(50, 110, 200, 25);
        add(lbHoTenKH);
        txtHoTenKH.setBounds(250, 110, 200, 25);
        add(txtHoTenKH);

        lbNgayLap.setBounds(50, 150, 200, 25);
        add(lbNgayLap);
        txtNgayLap.setBounds(250, 150, 200, 25);
        add(txtNgayLap);

        lbNoiDung.setBounds(50, 190, 200, 25);
        add(lbNoiDung);

        scrollPane.setBounds(250, 190, 200, 60);
        add(scrollPane);

        btnThem.setBounds(200, 260, 100, 40);
        add(btnThem);
    }
}
