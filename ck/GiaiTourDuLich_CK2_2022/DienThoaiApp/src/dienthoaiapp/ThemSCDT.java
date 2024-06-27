package dienthoaiapp;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ThemSCDT extends JFrame {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    private JLabel lbImei = new JLabel("Số IMEI");
    private JLabel lbDienThoai = new JLabel("Tên điện thoại");
    private JLabel lbHoTen = new JLabel("Họ tên KH");
    private JLabel lbNoiDung = new JLabel("Nội dung sửa chữa");
    private JLabel lbNgayLap = new JLabel("Ngày lập");
    private JLabel lbDSCV = new JLabel("Danh sách công việc");
    private JLabel lbDSCVSC = new JLabel("Danh sách công việc sửa chữa");

    JTextField txtImei = new JTextField(15);
    JTextField txtTenDT = new JTextField(15);
    JTextField txtHoTenKH = new JTextField(15);
    JTextField txtNgayLap = new JTextField(15);

    JTextArea txtNoiDung = new JTextArea();
    JPanel panelTable1 = new JPanel(new BorderLayout());
    JTable tableDiemDL = new JTable();
    JPanel panelTable2 = new JPanel(new BorderLayout());
    JTable tableDiemDLChon = new JTable();

    DefaultTableModel modelDscv = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsDscv = {"Mã công việc", "Công việc"};

    DefaultTableModel modelDscvSC = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsDscvSC = {"Công việc", "Đơn giá"};

    JButton btnThem = new JButton("Thêm sửa chữa");

    public ThemSCDT() {
        setup();

        panelTable1.add(lbDSCV, BorderLayout.NORTH);
        modelDscv.setColumnIdentifiers(columnsDscv);
        tableDiemDL.setModel(modelDscv);

        panelTable2.add(lbDSCVSC, BorderLayout.NORTH);
        modelDscvSC.setColumnIdentifiers(columnsDscvSC);
        tableDiemDLChon.setModel(modelDscvSC);

        txtHoTenKH.setEditable(false);
        txtTenDT.setEditable(false);
        txtNoiDung.setEditable(false);

//        Load thông tin khi nhập mã IMEI
        loadImei();

//        Load thông tin khi nhập ngày lập
        loadNgayNhap();

//        Load ds công việc
        loadDscv();

//        Xử lý sự kiện chọn ds công việc trong table
        handleDscv();

//        Xử lý luu
        luu();
    }

    private void loadImei() {
        txtImei.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String layDL = "SELECT TENDT, HOTENKH FROM DIENTHOAI WHERE IMEI = ?";
                    try {
                        ps = conn.prepareStatement(layDL);
                        ps.setString(1, txtImei.getText());
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            txtTenDT.setText(rs.getString(1));
                            txtHoTenKH.setText(rs.getString(2));
                        }
                    } catch (SQLException ex) {
                    }
                }
            }
        });
    }

    private void loadNgayNhap() {
        txtNgayLap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String layNoiDungSuaChua = "SELECT NOIDUNG FROM SUACHUA WHERE IMEI = ? AND NGAYLAP = ?";
                    try (PreparedStatement ps1 = conn.prepareStatement(layNoiDungSuaChua)) {
                        ps1.setString(1, txtImei.getText());
                        ps1.setString(2, txtNgayLap.getText());
                        ResultSet rs1 = ps1.executeQuery();
                        if (rs1.next()) {
                            txtNoiDung.setText(rs.getString(1));
                        }
                    } catch (SQLException ex) {
                    }
                }
            }
        });
    }

    private void loadDscv() {
        String layDSCV = "SELECT MACV, TENCV FROM CONGVIEC";
        try {
            ps = conn.prepareStatement(layDSCV);
            rs = ps.executeQuery();

            // Xử lý kết quả truy vấn
            while (rs.next()) {
                String maCV = rs.getString("MACV");
                String tenCV = rs.getString("TENCV");
                modelDscv.addRow(new Object[]{maCV, tenCV});
            }
        } catch (SQLException e) {
        }
    }

    private void handleDscv() {
        tableDiemDL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableDiemDL.getSelectedRow();
                String maCV = modelDscv.getValueAt(row, 0).toString();
                String tenCV = modelDscv.getValueAt(row, 1).toString();

                // Kiểm tra xem công việc đã có trong danh sách công việc sửa chữa chưa
                boolean alreadyExists = false;
                for (int i = 0; i < modelDscvSC.getRowCount(); i++) {
                    if (modelDscvSC.getValueAt(i, 0).toString().equals(tenCV)) {
                        alreadyExists = true;
                        break;
                    }
                }

                if (!alreadyExists) {
                    String layDonGia = "SELECT DONGIA FROM CONGVIEC WHERE MACV = ?";
                    try (PreparedStatement ps = conn.prepareStatement(layDonGia)) {
                        ps.setString(1, maCV);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            int donGia = rs.getInt(1);
                            modelDscvSC.addRow(new Object[]{tenCV, donGia});
                        }
                    } catch (SQLException ex) {
                    }
                }
            }
        });
    }

    private void luu() {
        btnThem.addActionListener(e -> {
            // Lấy MAPSC từ IMEI và ngày lập
            String layMAPSC = "SELECT MAPSC, NOIDUNG, TONGTIEN FROM SUACHUA WHERE IMEI = ? AND NGAYLAP = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(layMAPSC)) {
                ps1.setString(1, txtImei.getText());
                ps1.setString(2, txtNgayLap.getText());
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    String maPSC = rs1.getString(1);
                    String noiDungHienTai = rs1.getString(2);
                    int tongTienHienTai = rs1.getInt(3);

                    int rowCount = modelDscvSC.getRowCount();
                    int tongTienMoi = 0;
                    StringBuilder noiDungMoi = new StringBuilder();

                    if (noiDungHienTai != null) {
                        noiDungMoi.append(noiDungHienTai);
                    }

                    // Thêm công việc vào CT_SC và tính tổng tiền, cập nhật nội dung sửa chữa
                    for (int i = 0; i < rowCount; i++) {
                        String tenCV = modelDscvSC.getValueAt(i, 0).toString();
                        int donGia = Integer.parseInt(modelDscvSC.getValueAt(i, 1).toString());

                        // Thêm công việc vào nội dung sửa chữa
                        if (noiDungMoi.length() > 0) {
                            noiDungMoi.append("\n");
                        }
                        noiDungMoi.append(tenCV);

                        // Lấy mã công việc từ tên công việc
                        String layMACV = "SELECT MACV FROM CONGVIEC WHERE TENCV = ?";
                        PreparedStatement ps2 = conn.prepareStatement(layMACV);
                        ps2.setString(1, tenCV);
                        ResultSet rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            String maCV = rs2.getString(1);
                            String themCTSC = "INSERT INTO CT_SC (MAPSC, MACV) VALUES(?, ?)";
                            PreparedStatement ps3 = conn.prepareStatement(themCTSC);
                            ps3.setString(1, maPSC);
                            ps3.setString(2, maCV);
                            ps3.executeUpdate();
                            tongTienMoi += donGia;

                        }
                    }

                    // Cập nhật tổng tiền và nội dung sửa chữa vào SUACHUA
                    int tongTienCapNhat = tongTienHienTai + tongTienMoi;
                    String capNhatSuaChua = "UPDATE SUACHUA SET NOIDUNG = ?, TONGTIEN = ? WHERE MAPSC = ?";
                    PreparedStatement ps2 = conn.prepareStatement(capNhatSuaChua);
                    ps2.setString(1, noiDungMoi.toString());
                    ps2.setInt(2, tongTienCapNhat);
                    ps2.setString(3, maPSC);
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Thêm sửa chữa thành công!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThemSCDT().setVisible(true);
        });
    }

    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "DIENTHOAI", "password");
        } catch (SQLException e) {
        }

        JPanel panel = new JPanel(new GridBagLayout());
        JPanel panelTable = new JPanel(new GridLayout(0, 2, 10, 0));
        JPanel panelButton = new JPanel();

        setTitle("Thông tin sửa chữa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Hàng 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lbImei, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtImei, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(lbDienThoai, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(txtTenDT, gbc);

        // Hàng 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lbHoTen, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtHoTenKH, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(lbNoiDung, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane scrollNoiDung = new JScrollPane(txtNoiDung);
        scrollNoiDung.setPreferredSize(new Dimension(200, 50));
        panel.add(scrollNoiDung, gbc);

        // Hàng 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(lbNgayLap, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtNgayLap, gbc);

        // Hàng 4 - Danh sách công việc và Danh sách công việc sửa chữa
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(lbDSCV, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(lbDSCVSC, gbc);

        // Bảng Danh sách công việc
        modelDscv.setColumnIdentifiers(columnsDscv);
        tableDiemDL.setModel(modelDscv);
        panelTable1.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        panelTable1.add(new JScrollPane(tableDiemDL), BorderLayout.CENTER);
        panelTable.add(panelTable1);

        // Bảng Danh sách công việc sửa chữa
        modelDscvSC.setColumnIdentifiers(columnsDscvSC);
        tableDiemDLChon.setModel(modelDscvSC);
        panelTable2.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        panelTable2.add(new JScrollPane(tableDiemDLChon), BorderLayout.CENTER);
        panelTable.add(panelTable2);

        panelButton.add(btnThem);

//      QUAN TRỌNG THIẾT KẾ      
        add(panel, BorderLayout.NORTH);
        add(panelTable, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

    }
}
