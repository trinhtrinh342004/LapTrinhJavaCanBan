/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xaydungapp;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Trinh
 */
public class ThemCuaHang extends JFrame {

    Connection conn;

    JLabel lbTenCH = new JLabel("Tên cửa hàng");
    JLabel lbChuCH = new JLabel("Chủ cửa hàng");
    JLabel lbDiaChi = new JLabel("Địa chỉ");
    JLabel lbSDT = new JLabel("Số điện thoại");

    JTextField txtTenCH = new JTextField();
    JTextField txtChuCH = new JTextField();
    JTextField txtDiaChi = new JTextField();
    JTextField txtSDT = new JTextField();

    JButton btnThem = new JButton("Thêm");

    public ThemCuaHang() {
        setup();
        luu();
    }

    private void luu() {
        btnThem.addActionListener(e -> {
//          CUAHANG (MaCH, TenCH, ChuCH, DiaChi, SDT)            
            String sql = "INSERT INTO CUAHANG(TenCH, ChuCH, DiaChi, SDT) VALUES (?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, txtTenCH.getText());
                ps.setString(2, txtChuCH.getText());
                ps.setString(3, txtDiaChi.getText());
                ps.setString(4, txtSDT.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Thêm tour thành công!");
            } catch (SQLException ex) {
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThemCuaHang().setVisible(true));
    }

    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "XAYDUNG", "password");
        } catch (SQLException e) {
        }

        setTitle("Thông tin cửa hàng");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

//        setBounds(left, top , w, h)
        lbTenCH.setBounds(50, 30, 200, 25);
        add(lbTenCH);
        txtTenCH.setBounds(250, 30, 200, 25);
        add(txtTenCH);

        lbChuCH.setBounds(50, 70, 200, 25);
        add(lbChuCH);
        txtChuCH.setBounds(250, 70, 200, 25);
        add(txtChuCH);

        lbDiaChi.setBounds(50, 110, 200, 25);
        add(lbDiaChi);
        txtDiaChi.setBounds(250, 110, 200, 25);
        add(txtDiaChi);

        lbSDT.setBounds(50, 150, 200, 25);
        add(lbSDT);
        txtSDT.setBounds(250, 150, 200, 25);
        add(txtSDT);

        btnThem.setBounds(200, 190, 100, 40);
        add(btnThem);
    }
}
