package xaydungapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhapXDCH extends JFrame {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    JLabel lbMaCH = new JLabel("Mã cửa hàng");
    JLabel lbChuCH = new JLabel("Chủ cửa hàng");
    JLabel lbTenCH = new JLabel("Tên cửa hàng");
    JLabel lbSDT = new JLabel("Số điện thoại");
    JLabel lbNgayNhap = new JLabel("Ngày nhập");
    JLabel lbDiaChi = new JLabel("Địa chỉ");
    JLabel lbLoaiXD = new JLabel("Loại xăng dầu");
    JLabel lbXD = new JLabel("Xăng dầu");
    JLabel lbXDChon = new JLabel("Xăng dầu nhập");

    JTextField txtMaCH = new JTextField(15);
    JTextField txtChuCH = new JTextField(15);
    JTextField txtTenCH = new JTextField(15);
    JTextField txtDiaChiXD = new JTextField(15);
    JTextField txtSDT = new JTextField(15);
    JTextField txtNgayNhap = new JTextField(15);

    JComboBox<String> cbLoaiXD = new JComboBox<>();

    JPanel panelTable1 = new JPanel(new BorderLayout());
    JTable tableXD = new JTable();
    JPanel panelTable2 = new JPanel(new BorderLayout());
    JTable tableXDChon = new JTable();

    DefaultTableModel modelXD = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsXD = {"Tên xăng dầu", "Giá cơ sở"};

    DefaultTableModel modelXDChon = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsXDDuocChon = {"Tên xăng dầu", "Số lượng", "Giá nhập"};

    JButton btnThem = new JButton("Thêm");

    public NhapXDCH() {
        setup();
        txtChuCH.setEditable(false);
        txtTenCH.setEditable(false);
        txtDiaChiXD.setEditable(false);
        txtSDT.setEditable(false);

//         Load thông tin cửa hàng khi nhập mã cửa hàng
        loadMaCH();

//        Load danh sách loại xăng dầu
        loadLoaiXD();

//        Xử lý sự kiện khi chọn loại xăng dầu từ combobox
        handleLoaiXDSelection();

//        Xử lý sự kiện chọn trong table
        themXangDauNhap();

//        Xử lý sự kiện khi nhấn nút "Thêm" 
        luu();
    }

    private void loadMaCH() {
        txtMaCH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String maCH = txtMaCH.getText().trim();
                    if (!maCH.isEmpty()) {
                        String sql = "SELECT * FROM CUAHANG WHERE MaCH = ?";
                        try {
                            ps = conn.prepareStatement(sql);
                            ps.setString(1, maCH);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                txtTenCH.setText(rs.getString("TenCH"));
                                txtChuCH.setText(rs.getString("ChuCH"));
                                txtDiaChiXD.setText(rs.getString("DiaChi"));
                                txtSDT.setText(rs.getString("SDT"));
                            }
                        } catch (SQLException ex) {
                        }
                    }
                }
            }
        });
    }

    private void loadLoaiXD() {
        String sql = "SELECT TenLXD FROM LOAIXD";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            cbLoaiXD.addItem("Chọn loại xăng dầu");
            while (rs.next()) {
                String tenLXD = rs.getString("TenLXD");
                cbLoaiXD.addItem(tenLXD);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách loại xăng dầu.");
        }
    }

    private void handleLoaiXDSelection() {
        cbLoaiXD.addActionListener(e -> {
            String selectedLoaiXD = cbLoaiXD.getSelectedItem().toString();
            if (!selectedLoaiXD.equals("Chọn loại xăng dầu")) {
                modelXD.setRowCount(0);
                String sql = "SELECT TenXD, GiaCoSo FROM XANGDAU X, LOAIXD L WHERE X.MaLXD = L.MaLXD AND TenLXD = ?";
                try {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, selectedLoaiXD);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        modelXD.addRow(new Object[]{rs.getString("TenXD"), rs.getString("GiaCoSo")});
                    }
                } catch (SQLException ex) {
                }
            }
        });
    }

    private void themXangDauNhap() {
        tableXD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = tableXD.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng xăng dầu từ bảng Xăng dầu.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String tenXD = modelXD.getValueAt(selectedRow, 0).toString();
                String giaCoSo = modelXD.getValueAt(selectedRow, 1).toString();

                // Kiểm tra xem xăng dầu đã được chọn chưa
                for (int i = 0; i < modelXDChon.getRowCount(); i++) {
                    if (modelXDChon.getValueAt(i, 0).toString().equals(tenXD)) {
                        JOptionPane.showMessageDialog(null, "Xăng dầu này đã được chọn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                String soLuongNhap = JOptionPane.showInputDialog(null, "Nhập số lượng xăng dầu:");
                int soLuong = Integer.parseInt(soLuongNhap);
                if (soLuongNhap == null || soLuongNhap.isEmpty() || soLuong <= 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng nhập lỗi.", "Lỗi", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                try {
                    double giaNhap = Double.parseDouble(giaCoSo) * soLuong;

                    // Thêm vào bảng tableXDChon
                    modelXDChon.addRow(new Object[]{tenXD, soLuong, giaNhap});
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Số lượng nhập không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void luu() {
        btnThem.addActionListener(e -> {
            String sqlNhap = "INSERT INTO NHAP (MaCH, NgayNhap, MaXD, SoLuong, GiaNhap) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstNhap = conn.prepareStatement(sqlNhap)) {
                for (int i = 0; i < modelXDChon.getRowCount(); i++) {
                    String tenXD = modelXDChon.getValueAt(i, 0).toString();
                    int soLuong = Integer.parseInt(modelXDChon.getValueAt(i, 1).toString());
                    double giaNhap = Double.parseDouble(modelXDChon.getValueAt(i, 2).toString());

                    String sqlMaXD = "SELECT MaXD FROM XANGDAU WHERE TenXD = ?";
                    PreparedStatement pstMaXD = conn.prepareStatement(sqlMaXD);
                    pstMaXD.setString(1, tenXD);
                    ResultSet rsMaXD = pstMaXD.executeQuery();

                    if (rsMaXD.next()) {
                        String maXD = rsMaXD.getString("MaXD");
                        pstNhap.setString(1, txtMaCH.getText().trim());
                        pstNhap.setString(2, txtNgayNhap.getText().trim());
                        pstNhap.setString(3, maXD);
                        pstNhap.setInt(4, soLuong);
                        pstNhap.setDouble(5, giaNhap);
                        pstNhap.executeUpdate(); // Execute the insert statement for each row
                    }
                }

                JOptionPane.showMessageDialog(null, "Đã lưu thông tin nhập xăng dầu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                modelXDChon.setRowCount(0); // Clear the table after successful save
            } catch (SQLException ex) {
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NhapXDCH().setVisible(true);
        });
    }

    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "XAYDUNG", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //        QUAN TRỌNG THIẾT KẾ
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel panelTable = new JPanel(new GridLayout(0, 2, 10, 0));
        JPanel panelButton = new JPanel();

//        NOT
        setTitle("Chi tiết tour");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Hàng 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lbMaCH, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtMaCH, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(lbTenCH, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(txtTenCH, gbc);

        // Hàng 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lbChuCH, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtChuCH, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(lbDiaChi, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(txtDiaChiXD, gbc);

        // Hàng 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lbSDT, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtSDT, gbc);

        // Hàng 4 - Tỉnh/Thành phố (cbLoaiXD)
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(lbNgayNhap, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        panel.add(txtNgayNhap, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lbLoaiXD, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3; // Để ComboBox chiếm 3 cột
        cbLoaiXD.setMaximumSize(new Dimension(Integer.MAX_VALUE, cbLoaiXD.getPreferredSize().height)); // Đặt chiều dài tối đa
        panel.add(cbLoaiXD, gbc);

        panelTable1.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10)); // Tạo khoảng cách 10px xung quanh panel
        panelTable1.add(new JScrollPane(tableXD), BorderLayout.CENTER);
        panelTable.add(panelTable1);

        panelTable2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 30)); // Tạo khoảng cách 10px xung quanh panel
        panelTable2.add(new JScrollPane(tableXDChon), BorderLayout.CENTER);
        panelTable.add(panelTable2);

        panelButton.add(btnThem);

//        QUAN TRỌNG THIẾT KẾ        
        add(panel, BorderLayout.NORTH);
        add(panelTable, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        //        IMPORTANT
        panelTable1.add(lbXD, BorderLayout.NORTH);
        modelXD.setColumnIdentifiers(columnsXD);
        tableXD.setModel(modelXD);

        panelTable2.add(lbXDChon, BorderLayout.NORTH);
        modelXDChon.setColumnIdentifiers(columnsXDDuocChon);
        tableXDChon.setModel(modelXDChon);
    }
}
