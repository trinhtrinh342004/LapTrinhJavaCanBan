// Câu c
public class ThemSCDT {
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
    JScrollPane scrollPane = new JScrollPane(txtNoiDung);

    JTable tableDiemDL = new JTable();
    DefaultTableModel modelDscv = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };
    String[] columnsDscv = {"Mã công việc", "Công việc"};
    modelDscv.setColumnIdentifiers(columnsDscv);
    tableDiemDL.setModel(modelDscv);

    JTable tableDiemDLChon = new JTable();
    DefaultTableModel modelDscvSC = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };
    String[] columnsDscvSC = {"Công việc", "Đơn giá"};
    modelDscvSC.setColumnIdentifiers(columnsDscvSC);
    tableDiemDLChon.setModel(modelDscvSC);

    JButton btnThem = new JButton("Thêm sửa chữa");

    public ThemSCDT() {
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
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String layDL = "SELECT TENDT, HOTENKH FROM DIENTHOAI WHERE IMEI = ?";
                    ps = conn.prepareStatement(layDL);
                    ps.setString(1, txtImei.getText());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        txtTenDT.setText(rs.getString(1));
                        txtHoTenKH.setText(rs.getString(2));
                    }
                }
            }
        });
    }

    private void loadNgayNhap() {
        txtNgayLap.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String layNoiDungSuaChua = "SELECT NOIDUNG FROM SUACHUA WHERE IMEI = ? AND NGAYLAP = ?";
                    PreparedStatement ps1 = conn.prepareStatement(layNoiDungSuaChua);
                    ps1.setString(1, txtImei.getText());
                    ps1.setString(2, txtNgayLap.getText());
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        txtNoiDung.setText(rs.getString(1));
                    }
                }
            }
        });
    }

    private void loadDscv() {
        String layDSCV = "SELECT MACV, TENCV FROM CONGVIEC";
        ps = conn.prepareStatement(layDSCV);
        rs = ps.executeQuery();
        while (rs.next()) {
            String maCV = rs.getString("MACV");
            String tenCV = rs.getString("TENCV");
            modelDscv.addRow(new Object[]{maCV, tenCV});
        }
    }

    private void handleDscv() {
        tableDiemDL.addMouseListener(new MouseAdapter() {
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
                    PreparedStatement ps = conn.prepareStatement(layDonGia);
                    ps.setString(1, maCV);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int donGia = rs.getInt(1);
                        modelDscvSC.addRow(new Object[]{tenCV, donGia});
                    }
                }
            }
        });
    }

    private void luu() {
        btnThem.addActionListener(e -> {
            // Lấy MAPSC từ IMEI và ngày lập
            String layMAPSC = "SELECT MAPSC, NOIDUNG, TONGTIEN FROM SUACHUA WHERE IMEI = ? AND NGAYLAP = ?";
            PreparedStatement ps1 = conn.prepareStatement(layMAPSC);
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
            }
        });
    }

    public static void main(String[] args) {
        new ThemSCDT();
    }
}
