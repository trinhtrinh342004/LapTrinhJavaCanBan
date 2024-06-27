package phongkhamapp;

import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trinh
 */
public class ThemCTKB extends JFrame {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    JLabel lbBacSiKham = new JLabel("Bác sĩ khám");
    JLabel lbTenBN = new JLabel("Tên bệnh nhan");
    JLabel lbNgayKham = new JLabel("Ngày khám");
    JLabel lbKL = new JLabel("Kết luận");
    JLabel lbYCK = new JLabel("Yêu cầu khám");
    JLabel lbDsdv = new JLabel("Danh sách dịch vụ");
    JLabel lbDsdvChon = new JLabel("Danh sách dịch vụ bác sĩ chọn");

    JComboBox<String> cbBacSiKham = new JComboBox<>();
    JComboBox<String> cbTenBN = new JComboBox<>();
    JTextField txtNgayKham = new JTextField(15);
    JTextField txtYCK = new JTextField(15);
    JTextField txtKL = new JTextField(15);

    JPanel panelTable1 = new JPanel(new BorderLayout());
    JTable tableDsdv = new JTable();
    JPanel panelTable2 = new JPanel(new BorderLayout());
    JTable tableDsdvChon = new JTable();

    DefaultTableModel modelDsdv = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsDsdv = {"Tên dịch vụ"};

    DefaultTableModel modelDsdvChon = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tất cả các ô trong bảng đều không thể chỉnh sửa
        }
    };
    String[] columnsDsdvDuocChon = {"Tên dịch vụ", "Số lượng"};

    JButton btnThem = new JButton("Thêm");

    public ThemCTKB() {
        setup();

        //Load tên bác sĩ vào combobox
        loadTenBacSi();

        //Load danh sách dịch vụ vào jTable
        loadDanhSachDichVu();

        //Bắt sự kiện khi chọn bác sĩ, nhập ngày khám và nhấn enter để load danh sách bệnh nhân đặt lịch hẹn với bác sĩ
        addNgayKhamEnterListener();

        //Bắt sự kiện khi chọn tên bệnh nhân để hiển thị yêu cầu khám của bệnh nhân đó
        addCboBenhNhanActionListener();

        //Bắt sự kiện khi chọn dịch vụ từ jTable <Danh sách dịch vụ> để thêm vào jTable <Danh sách dịch vụ bác sĩ chọn>
        addTblDichVuMouseListener();

        //Bắt sự kiện khi nhấn nút "Thêm" để thêm thông tin vào CSDL
        addBtnThemActionListener();
    }

    private void loadTenBacSi() {
        try {
            String sql = "SELECT TENBS FROM BACSI";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cbBacSiKham.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu bác sĩ.");
        }
    }

    private void loadDanhSachDichVu() {
        try {
            String sql2 = "SELECT TENDV FROM DICHVU";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                modelDsdv.addRow(new Object[]{rs2.getString(1)});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách dịch vụ.");
        }
    }

    private void addNgayKhamEnterListener() {
        txtNgayKham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String tenBS = cbBacSiKham.getSelectedItem().toString();

                        String sqlGetMABS = "SELECT MABS FROM BACSI WHERE TENBS = ?";
                        PreparedStatement psGetMABS = conn.prepareStatement(sqlGetMABS);
                        psGetMABS.setString(1, tenBS);
                        ResultSet rsGetMABS = psGetMABS.executeQuery();

                        if (rsGetMABS.next()) {
                            String maBS = rsGetMABS.getString("MABS");
                            String sql = "SELECT DISTINCT TENBN "
                                    + "FROM BENHNHAN "
                                    + "WHERE MABN IN (SELECT MABN "
                                    + "FROM KHAMBENH "
                                    + "WHERE MABS = ? AND NGAYKHAM = ?)";
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setString(1, maBS);
                            ps.setString(2, txtNgayKham.getText());
                            ResultSet rs = ps.executeQuery();
                            // Xóa các mục cũ trong combobox trước khi thêm mới
                            cbTenBN.removeAllItems();
                            // Thêm các tên bệnh nhân vào combobox
                            while (rs.next()) {
                                cbTenBN.addItem(rs.getString("TENBN"));
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void addCboBenhNhanActionListener() {
        cbTenBN.addActionListener(e -> {
            try {
                String tenBN = cbTenBN.getSelectedItem().toString();

                String sqlGetMABN = "SELECT MABN FROM BENHNHAN WHERE TENBN = ?";
                PreparedStatement psGetMABN = conn.prepareStatement(sqlGetMABN);
                psGetMABN.setString(1, tenBN);
                ResultSet rsGetMABN = psGetMABN.executeQuery();

                while (rsGetMABN.next()) {
                    String maBN = rsGetMABN.getString("MABN");

                    String tenBS = cbBacSiKham.getSelectedItem().toString();

                    String sqlGetMABS = "SELECT MABS FROM BACSI WHERE TENBS = ?";
                    PreparedStatement psGetMABS = conn.prepareStatement(sqlGetMABS);
                    psGetMABS.setString(1, tenBS);
                    ResultSet rsGetMABS = psGetMABS.executeQuery();

                    if (rsGetMABS.next()) {
                        String maBS = rsGetMABS.getString("MABS");
                        String sql = "SELECT YEUCAUKHAM FROM KHAMBENH WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ?";
                        PreparedStatement ps1 = conn.prepareStatement(sql);
                        ps1.setString(1, maBN);
                        ps1.setString(2, maBS);
                        ps1.setString(3, txtNgayKham.getText());
                        ResultSet rs1 = ps1.executeQuery();

                        if (rs1.next()) {
                            txtYCK.setText(rs1.getString("YEUCAUKHAM"));
                        }
                    }
                }
            } catch (SQLException ex) {
            }
        });
    }

    private void addTblDichVuMouseListener() {
        tableDsdv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableDsdv.getSelectedRow();
                if (row != -1) {
                    String tenDichVu = modelDsdv.getValueAt(row, 0).toString();

                    boolean a = false;
                    for (int i = 0; i < modelDsdvChon.getRowCount(); i++) {
                        String b = modelDsdvChon.getValueAt(i, 0).toString();
                        if (b.equals(tenDichVu)) {
                            a = true;
                            break;
                        }
                    }

                    if (!a) {
                        String soLuong = JOptionPane.showInputDialog("Nhập số lượng");
                        if (soLuong != null && !soLuong.isEmpty()) {
                            modelDsdvChon.addRow(new Object[]{tenDichVu, soLuong});
                        }
                    }
                }
            }
        });
    }

    private void addBtnThemActionListener() {
        btnThem.addActionListener(e -> {
            try {

                String sql = "SELECT MABS FROM BACSI WHERE TENBS = ?";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setString(1, cbBacSiKham.getSelectedItem().toString());
                ResultSet rs1 = ps1.executeQuery();
                String maBacSi = "";
                if (rs1.next()) {
                    maBacSi = rs1.getString(1);
                }

                String sql2 = "SELECT MABN FROM BENHNHAN WHERE TENBN = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, cbTenBN.getSelectedItem().toString());
                ResultSet rs2 = ps2.executeQuery();
                String maBenhNhan = "";
                if (rs2.next()) {
                    maBenhNhan = rs2.getString(1);
                }
                // Cập nhật thông tin khám bệnh
                String sql3 = "UPDATE KHAMBENH SET KETLUAN = ?, THANHTOAN = ? WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ? AND YEUCAUKHAM = ?";
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1, txtKL.getText());
                ps3.setString(2, "Chưa thanh toán");
                ps3.setString(3, maBenhNhan);
                ps3.setString(4, maBacSi);
                ps3.setString(5, txtNgayKham.getText());
                ps3.setString(6, txtYCK.getText());
                ps3.executeUpdate();
                // Thêm thông tin thu phí
                for (int i = 0; i < modelDsdvChon.getRowCount(); i++) {
                    // Lấy mã dịch vụ từ tên dịch vụ
                    String sql4 = "SELECT MADV FROM DICHVU WHERE TENDV = ?";
                    PreparedStatement ps4 = conn.prepareStatement(sql4);
                    ps4.setString(1, modelDsdvChon.getValueAt(i, 0).toString());
                    ResultSet rs4 = ps4.executeQuery();
                    String maDichVu = "";
                    if (rs4.next()) {
                        maDichVu = rs4.getString(1);
                    }

                    // Lấy mã khám bệnh từ mã bệnh nhân, mã bác sĩ và ngày khám
                    String sql5 = "SELECT MAKB FROM KHAMBENH WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ?";
                    PreparedStatement ps5 = conn.prepareStatement(sql5);
                    ps5.setString(1, maBenhNhan);
                    ps5.setString(2, maBacSi);
                    ps5.setString(3, txtNgayKham.getText());
                    ResultSet rs5 = ps5.executeQuery();
                    String maKB = "";
                    if (rs5.next()) {
                        maKB = rs5.getString(1);
                    }

                    // Thêm thông tin thu phí
                    String sql6 = "INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES(?, ?, ?, ?)";
                    PreparedStatement ps6 = conn.prepareStatement(sql6);
                    ps6.setString(1, maKB);
                    ps6.setString(2, maDichVu);
                    ps6.setInt(3, Integer.parseInt(modelDsdvChon.getValueAt(i, 1).toString()));

                    // Lấy đơn giá của dịch vụ
                    String sql7 = "SELECT DONGIA FROM DICHVU WHERE MADV = ?";
                    PreparedStatement ps7 = conn.prepareStatement(sql7);
                    ps7.setString(1, maDichVu);
                    ResultSet rs7 = ps7.executeQuery();
                    int donGia = 0;
                    if (rs7.next()) {
                        donGia = rs7.getInt(1);
                    }
                    ps6.setInt(4, donGia * Integer.parseInt(modelDsdvChon.getValueAt(i, 1).toString()));
                    ps6.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Thêm thông tin khám bệnh và thu phí thành công!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThemCTKB().setVisible(true);
        });

    }

    private void setup() {
// Kết nối CSDL
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH", "PHONGKHAM", "password");
        } catch (SQLException e) {
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
        panel.add(lbBacSiKham, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(cbBacSiKham, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(lbNgayKham, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(txtNgayKham, gbc);

        // Hàng 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lbTenBN, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(cbTenBN, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(lbYCK, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(txtYCK, gbc);

        // Hàng 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lbKL, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtKL, gbc);

        cbBacSiKham.setPreferredSize(new Dimension(150, 25)); // Đặt kích thước 150x25
        cbTenBN.setPreferredSize(new Dimension(150, 25)); // Đặt kích thước 150x25

        panelTable1.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10)); // Tạo khoảng cách 10px xung quanh panel
        panelTable1.add(new JScrollPane(tableDsdv), BorderLayout.CENTER);
        panelTable.add(panelTable1);

        panelTable2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 30)); // Tạo khoảng cách 10px xung quanh panel
        panelTable2.add(new JScrollPane(tableDsdvChon), BorderLayout.CENTER);
        panelTable.add(panelTable2);

        panelButton.add(btnThem);

//        QUAN TRỌNG THIẾT KẾ        
        add(panel, BorderLayout.NORTH);
        add(panelTable, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

//        IMPORTANT
        panelTable1.add(lbDsdv, BorderLayout.NORTH);
        modelDsdv.setColumnIdentifiers(columnsDsdv);
        tableDsdv.setModel(modelDsdv);

        panelTable2.add(lbDsdvChon, BorderLayout.NORTH);
        modelDsdvChon.setColumnIdentifiers(columnsDsdvDuocChon);
        tableDsdvChon.setModel(modelDsdvChon);

    }
}
