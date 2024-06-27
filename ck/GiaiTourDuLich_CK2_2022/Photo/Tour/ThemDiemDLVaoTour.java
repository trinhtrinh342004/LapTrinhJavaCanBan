// Câu c
public class ThemDiemDLVaoTour {
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

    JTextField txtMaTour = new JTextField();
    JTextField txtTenTour = new JTextField();
    JTextField txtNgayKhoiHanh = new JTextField();
    JTextField txtGiaTour = new JTextField();
    JTextField txtSoNgay = new JTextField();
    JTextField txtSoDem = new JTextField();

    JComboBox<String> cbTinhTP = new JComboBox<>();

    JTable tableDiemDL = new JTable();
    DefaultTableModel modelDiemDL = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };
    String[] columnsDiemDL = {"Tên điểm du lịch", "Đặc trưng"};
    modelDiemDL.setColumnIdentifiers(columnsDiemDL);
    tableDiemDL.setModel(modelDiemDL);

    JTable tableDiemDLChon = new JTable();
    DefaultTableModel modelDiemDLChon = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    String[] columnsDiemDLDuocChon = {"Tên điểm du lịch"};
    modelDiemDLDuocChon.setColumnIdentifiers(columnsDiemDLDuocChon);
    tableDiemDLDuocChon.setModel(modelDiemDLDuocChon);

    JButton btnThem = new JButton("Thêm");

    public ThemDiemDLVaoTour() {
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

    // TOUR(MaTour, TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia)
    // TINHTP (MaTTP, TenTTP)
    // DIEMDL(MaDDL, TenDDL, MaTTP, DacTrung)
    // CHITIET (MaTour, MaDDL)
    private void loadMaTour() {
        txtMaTour.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String maTour = txtMaTour.getText().trim();
                    String sql = "SELECT * FROM TOUR WHERE MaTour = ?";                    
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
                }
            }
        });
    }

    private void loadTinhTP() {
        String sql = "SELECT TenTTP FROM TINHTP";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            cbTinhTP.addItem(rs.getString("TenTTP"));
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
                ps = conn.prepareStatement(sql);
                ps.setString(1, selectedTinhTP);
                rs = ps.executeQuery();
                while (rs.next()) {
                    modelDiemDL.addRow(new Object[]{rs.getString("TenDDL"), rs.getString("DacTrung")});
                }
            }
        });
    }

    private void themDLChon() {
        tableDiemDL.addMouseListener(new MouseAdapter() {
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
      });
    }

    public static void main(String[] args) {
        new ThemDiemDLVaoTour();
    }
}
