package tourdulichapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ThemDiemDLVaoTour extends JFrame {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    JLabel lbMaTour = new JLabel("Mã Tour");
    JLabel lbTenTour = new JLabel("Tên Tour");
    JLabel lbNgayKhoiHanh = new JLabel("Ngày Khởi Hành");
    JLabel lbSoNgay = new JLabel("Số Ngày");
    JLabel lbSoDem = new JLabel("Số Đêm");
    JLabel lbGia = new JLabel("Giá tour");
    JLabel lbTinhTP = new JLabel("Tên Tỉnh/Thành phố");
    JLabel lbDiemDL = new JLabel("Điểm du lịch");
    JLabel lbDiemDLChon = new JLabel("Điểm du lịch được chọn");

    JTextField txtMaTour = new JTextField(15);
    JTextField txtTenTour = new JTextField(15);
    JTextField txtNgayKhoiHanh = new JTextField(15);
    JTextField txtGiaTour = new JTextField(15);
    JTextField txtSoNgay = new JTextField(15);
    JTextField txtSoDem = new JTextField(15);

    JComboBox<String> cbTinhTP = new JComboBox<>();

    JPanel panelTable1 = new JPanel(new BorderLayout());
    JTable tableDiemDL = new JTable();
    JPanel panelTable2 = new JPanel(new BorderLayout());
    JTable tableDiemDLChon = new JTable();

    DefaultTableModel modelDiemDL = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsDiemDL = {"Tên điểm du lịch", "Đặc trưng"};

    DefaultTableModel modelDiemDLChon = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsDiemDLDuocChon = {"Tên điểm du lịch"};

    JButton btnThem = new JButton("Thêm");

    public ThemDiemDLVaoTour() {
        setup();
        txtTenTour.setEditable(false);
        txtNgayKhoiHanh.setEditable(false);
        txtGiaTour.setEditable(false);
        txtSoNgay.setEditable(false);
        txtSoDem.setEditable(false);

        cbTinhTP.addItem("Chọn Tỉnh/Thành phố");

//         Load thông tin cửa hàng khi nhập mã tour
        loadMaTour();

//        Load danh sách Tỉnh, thành phố
        loadTinhTP();

//        Xử lý sự kiện khi chọn tỉnh thành phố sẽ hiển thị danh sách điểm du lịch của tỉnh thành phố đó
        handleTinhTP();

//        Xử lý sự kiện chọn trong table (chọn một điểm du lịch sẽ thêm vào danh sách điểm du lịch được chọn)
        themDLChon();
//        Xử lý sự kiện khi nhấn nút "Thêm" 
        luu();
    }

    private void loadMaTour() {
        txtMaTour.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String maTour = txtMaTour.getText().trim();
                    String sql = "SELECT * FROM TOUR WHERE MaTour = ?";
                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, maTour);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            txtTenTour.setText(rs.getString("TenTour"));
                            txtNgayKhoiHanh.setText(rs.getString("NgayKhoiHanh"));
                            txtGiaTour.setText(rs.getString("Gia"));
                            txtSoNgay.setText(rs.getString("SoNgay"));
                            txtSoDem.setText(rs.getString("SoDem"));
                        }
                    } catch (SQLException ex) {
                    }
                }
            }
        });
    }

    private void loadTinhTP() {
        String sql = "SELECT TenTTP FROM TINHTP";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cbTinhTP.addItem(rs.getString("TenTTP"));
            }
        } catch (SQLException e) {
        }
    }

    private void handleTinhTP() {
        cbTinhTP.addActionListener(e -> {
            String selectedTinhTP = (String) cbTinhTP.getSelectedItem();
            if (selectedTinhTP != null && !selectedTinhTP.equals("Chọn Tỉnh/Thành phố")) {
                modelDiemDL.setRowCount(0);
                String sql = "SELECT d.TenDDL, d.DacTrung FROM DIEMDL d "
                        + "JOIN TINHTP t ON d.MaTTP = t.MaTTP "
                        + "WHERE t.TenTTP = ?";
                try {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, selectedTinhTP);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        modelDiemDL.addRow(new Object[]{rs.getString("TenDDL"), rs.getString("DacTrung")});
                    }
                } catch (SQLException ex) {
                }
            }
        });
    }

    private void themDLChon() {
        tableDiemDL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = tableDiemDL.getSelectedRow();
                String tenDiem = tableDiemDL.getValueAt(row, 0).toString();

                // Kiểm tra xem điểm du lịch đã được chọn hay chưa
                boolean alreadyAdded = false;
                for (int i = 0; i < modelDiemDLChon.getRowCount(); i++) {
                    String addedDDL = modelDiemDLChon.getValueAt(i, 0).toString();
                    if (addedDDL.equals(tenDiem)) {
                        alreadyAdded = true;
                        break;
                    }
                }

                if (!alreadyAdded) {
                    modelDiemDLChon.addRow(new Object[]{tenDiem});
                }
            }
        });
    }

    private void luu() {
        btnThem.addActionListener(e -> {
            String maTour = txtMaTour.getText();
            try {
                for (int i = 0; i < modelDiemDLChon.getRowCount(); i++) {
                    String tenDDL = modelDiemDLChon.getValueAt(i, 0).toString();
                    String maDDL = null;

                    // Get MaDDL from DIEMDL table
                    String layMaDDL = "SELECT MaDDL FROM DIEMDL WHERE TenDDL = ?";
                    PreparedStatement ps1 = conn.prepareStatement(layMaDDL);
                    ps1.setString(1, tenDDL);
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        maDDL = rs1.getString("MaDDL");
                    }

                    // Check if the MaDDL already exists for MaTour in CHITIET table
                    if (maDDL != null) {
                        String check = "SELECT COUNT(*) FROM CHITIET WHERE MaTour = ? AND MaDDL = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(check);
                        checkStmt.setString(1, maTour);
                        checkStmt.setString(2, maDDL);
                        ResultSet checkRs = checkStmt.executeQuery();
                        if (checkRs.next() && checkRs.getInt(1) == 0) {
                            // Insert into CHITIET if it doesn't already exist
                            String sql = "INSERT INTO CHITIET (MaTour, MaDDL) VALUES (?, ?)";
                            PreparedStatement ps2 = conn.prepareStatement(sql);
                            ps2.setString(1, maTour);
                            ps2.setString(2, maDDL);
                            ps2.executeUpdate();
                        }
                    }
                }

                JOptionPane.showMessageDialog(this, "Đã thêm các điểm du lịch vào tour.");
                modelDiemDLChon.setRowCount(0);

            } catch (SQLException ex) {
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThemDiemDLVaoTour().setVisible(true);
        });
    }

    private void setup() {
        // Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "FLY_THE_END12A", "password");
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
        panel.add(lbMaTour, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtMaTour, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(lbNgayKhoiHanh, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(txtNgayKhoiHanh, gbc);

        // Hàng 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lbTenTour, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtTenTour, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(lbGia, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(txtGiaTour, gbc);

        // Hàng 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lbSoNgay, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtSoNgay, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(lbSoDem, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(txtSoDem, gbc);

        // Hàng 4 - Tỉnh/Thành phố (cbTinhTP)
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lbTinhTP, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3; // Để ComboBox chiếm 3 cột
        cbTinhTP.setMaximumSize(new Dimension(Integer.MAX_VALUE, cbTinhTP.getPreferredSize().height)); // Đặt chiều dài tối đa
        panel.add(cbTinhTP, gbc);

        panelTable1.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10)); // Tạo khoảng cách 10px xung quanh panel
        panelTable1.add(new JScrollPane(tableDiemDL), BorderLayout.CENTER);
        panelTable.add(panelTable1);

        panelTable2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 30)); // Tạo khoảng cách 10px xung quanh panel
        panelTable2.add(new JScrollPane(tableDiemDLChon), BorderLayout.CENTER);
        panelTable.add(panelTable2);

        panelButton.add(btnThem);

//        QUAN TRỌNG THIẾT KẾ        
        add(panel, BorderLayout.NORTH);
        add(panelTable, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

//        IMPORTANT
        panelTable1.add(lbDiemDL, BorderLayout.NORTH);
        modelDiemDL.setColumnIdentifiers(columnsDiemDL);
        tableDiemDL.setModel(modelDiemDL);

        panelTable2.add(lbDiemDLChon, BorderLayout.NORTH);
        modelDiemDLChon.setColumnIdentifiers(columnsDiemDLDuocChon);
        tableDiemDLChon.setModel(modelDiemDLChon);

    }
}
