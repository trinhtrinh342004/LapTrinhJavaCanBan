/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xaydungapp;

import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Trinh
 */
public class ThemXangDau extends JFrame {

    Connection conn;
    JLabel lbLoaiXD = new JLabel("Loại xăng dầu");
    JLabel lbTenXD = new JLabel("Tên xăng dầu");
    JLabel lbGiaCoSo = new JLabel("Giá cơ sở");

    JComboBox<String> cbLoaiXD = new JComboBox<>();
    JTextField txtTenXD = new JTextField();
    JTextField txtGiaCoSo = new JTextField();

    JButton btnThem = new JButton("Thêm");

    public ThemXangDau() {
        setup();
//        Load dữ liệu vào combobox
        handleTenLXD();

//Thêm điểm du lịch DIEMDL(MaDDL, TenDDL, MaTTP, DacTrung)
        luu();
    }

    private void handleTenLXD() {
//        LOAIXD (MaLXD, TenLXD)
//        XANGDAU (MaXD, TenXD, GiaCoSo, MaLXD)
        String sql1 = "SELECT TenLXD FROM LOAIXD";
        try (PreparedStatement ps = conn.prepareStatement(sql1); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cbLoaiXD.addItem(rs.getString("TenLXD"));
            }
        } catch (SQLException ex) {
        }
    }

    private void luu() {
        btnThem.addActionListener(e -> {
            String sql2 = "SELECT MaLXD FROM LOAIXD WHERE TenLXD = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(sql2)) {
                ps1.setString(1, cbLoaiXD.getSelectedItem().toString());
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    String maLXD = rs1.getString("MaLXD");
                    String sql3 = "INSERT INTO XANGDAU(TenXD, GiaCoSo, MaLXD) VALUES (?, ?, ?)";
                    PreparedStatement ps2 = conn.prepareStatement(sql3);
                    ps2.setString(1, txtTenXD.getText());
                    ps2.setFloat(2, Float.parseFloat(txtGiaCoSo.getText()));
                    ps2.setString(3, maLXD);
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Thêm xăng dầu thành công!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThemXangDau().setVisible(true));
    }

    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "XAYDUNG", "password");
        } catch (SQLException e) {
        }
        setTitle("Thêm Điểm Du Lịch");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lbLoaiXD.setBounds(50, 30, 100, 25);
        add(lbLoaiXD);
        cbLoaiXD.setBounds(150, 30, 200, 25);
        add(cbLoaiXD);

        lbTenXD.setBounds(50, 70, 100, 25);
        add(lbTenXD);

        txtTenXD.setBounds(150, 70, 200, 25);
        add(txtTenXD);

        lbGiaCoSo.setBounds(50, 110, 100, 25);
        add(lbGiaCoSo);

        txtGiaCoSo.setBounds(150, 110, 200, 25);
        add(txtGiaCoSo);

        btnThem.setBounds(150, 150, 100, 30);
        add(btnThem);
    }
}
