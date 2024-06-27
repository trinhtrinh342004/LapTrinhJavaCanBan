//1. Thêm thông tin tour du lịch

JLabel lbTenTour = new JLabel();
JLabel lbNgayKhoiHanh = new JLabel();
JLabel lbSoNgay = new JLabel();
JLabel lbSoDem = new JLabel();
JLabel lbGiaTour = new JLabel();

JTextField txtTenTour = new JTextField();
JTextField txtNgayKhoiHanh = new JTextField();
JTextField txtSoNgay = new JTextField();
JTextField txtSoDem = new JTextField();
JTextField txtGiaTour = new JTextField();

JButton btnThem = new JButton();

Connection conn;

btnThem.addActionListener (e -> {
    String tenTour = txtTenTour.getText();
    String ngayKhoiHanh = txtNgayKhoiHanh.getText();
    int soNgay = Integer.parseInt(txtSoNgay.getText());
    int soDem = Integer.parseInt(txtSoDem.getText());
    double giaTour = Double.parseDouble(txtGiaTour.getText());
    
    String sql = "INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES (?, ?, ?, ?, ?)";

    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, tenTour);
    ps.setString(2, ngayKhoiHanh);
    ps.setInt(3, soNgay);
    ps.setInt(4, soDem);
    ps.setDouble(5, giaTour);
    ps.executeUpdate();
});

//2. Thêm điểm du lịch

JLabel lbTinhTP = new JLabel();
JLabel lbTenDiemDL = new JLabel();
JLabel lbDacTrung = new JLabel();

JComboBox<String> cbTinhTP = new JComboBox<>();

JTextField txtTenDiemDL = new JTextField();
JTextField txtDacTrung = new JTextField();

JButton btnThem = new JButton();

Connection conn;

// Lấy dữ liệu từ bảng TinhTP
//TINHTP (MaTTP, TenTTP)

String sql = "SELECT TenTTP FROM TINHTP";
PreparedStatement ps = conn.prepareStatement(sql);
ResultSet rs = ps.executeQuery();

while (rs.next()) {
    cbTinhTP.addItem(rs.getString("TenTTP"));
}
//Thêm điểm du lịch DIEMDL(MaDDL, TenDDL, MaTTP, DacTrung)
btnThem.addActionListener (e -> {
    String tinhTP = cbTinhTP.getSelectedItem().toString();
    String tenDiemDL = txtTenDiemDL.getText();
    String dacTrung = txtDacTrung.getText();

    String getMaTTP = "SELECT MaTTP FROM TINHTP WHERE TenTTP = ?";
    PreparedStatement ps1 = conn.prepareStatement(getMaTTP);
    ps1.setString(1, tinhTP);
    ResultSet rs1 = ps1.executeQuery();
    if (rs1.next()) {
        String maTTP = rs1.getString("MaTTP");
        String sql = "INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES (?, ?, ?)";
        PreparedStatement ps2 = conn.prepareStatement(sql);
        ps2.setString(1, tenDiemDL);
        ps2.setString(2, maTTP);
        ps2.setString(3, dacTrung);
        ps2.executeUpdate();
    }
});

//3. Thêm các điểm du lịch vào tour

JLabel lbMaTour = new JLabel();
JLabel lbTenTour = new JLabel();
JLabel lbSoNgay = new JLabel();
JLabel lbNgayKhoiHanh = new JLabel();
JLabel lbGiaTour = new JLabel();
JLabel lbSoDem = new JLabel();
JLabel lbTinhTP = new JLabel();
JLabel lbDiemDuLich = new JLabel();
JLabel lbDiemDuLichDuocChon = new JLabel();

JTextField txtMaTour = new JTextField();
JTextField txtTenTour = new JTextField();
JTextField txtSoNgay = new JTextField();
JTextField txtNgayKhoiHanh = new JTextField();
JTextField txtGiaTour = new JTextField();
JTextField txtSoDem = new JTextField();

JComboBox<String> cbTinhTP = new JComboBox<>();

JTable tableDiemDL = new JTable();
DefaultTableModel modelDiemDL = new DefaultTableModel();
String [] columnsDiemDL = {"Tên điểm du lịch", "Đặc trưng"};
modelDiemDL.setColumnIdentifiers(columnsDiemDL);
tableDiemDL.setModel(modelDiemDL);


JTable tableDiemDLDuocChon = new JTable();
DefaultTableModel modelDiemDLDuocChon = new DefaultTableModel();
String [] columnsDiemDLDuocChon = {"Tên điểm du lịch"};
modelDiemDLDuocChon.setColumnIdentifiers(columnsDiemDLDuocChon);
tableDiemDLDuocChon.setModel(modelDiemDLDuocChon);

JButton btnThem = new JButton();

Connection conn;

// Bắt sự kiện khi nhập mã tour và nhấn enter sẽ hiển thị thông tin tour gồm: tên tour, ngày khởi hành, giá tour, số ngày, số đêm (các thông tin này không cho phép chỉnh sửa).

txtMaTour.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String maTour = txtMaTour.getText();
            String sql = "SELECT * FROM TOUR WHERE MaTour = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maTour);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtTenTour.setText(rs.getString("TenTour"));
                txtNgayKhoiHanh.setText(rs.getString("NgayKhoiHanh"));
                txtGiaTour.setText(rs.getString("Gia"));
                txtSoNgay.setText(rs.getString("SoNgay"));
                txtSoDem.setText(rs.getString("SoDem"));

                ArrayList<String> maTTPList = new ArrayList<>();
                try (PreparedStatement pstDDL = conn.prepareStatement("SELECT MaDDL FROM CHITIET WHERE MaTour = ?")) {
                    pstDDL.setString(1, maTour);
                    try (ResultSet rsDDL = pstDDL.executeQuery()) {
                        while (rsDDL.next()) {
                            String maDDL = rsDDL.getString("MaDDL");
                            try (PreparedStatement pstTTP = conn.prepareStatement("SELECT MaTTP FROM DIEMDL WHERE MaDDL = ?")) {
                                pstTTP.setString(1, maDDL);
                                try (ResultSet rsTTP = pstTTP.executeQuery()) {
                                    if (rsTTP.next()) {
                                        String maTTP = rsTTP.getString("MaTTP");
                                        if (!maTTPList.contains(maTTP)) {
                                            maTTPList.add(maTTP);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                cbTinhTP.removeAllItems();
                for (String maTTP : maTTPList) {
                    try (PreparedStatement pstTenTTP = conn.prepareStatement("SELECT TenTTP FROM TINHTP WHERE MaTTP = ?")) {
                        pstTenTTP.setString(1, maTTP);
                        try (ResultSet rsTenTTP = pstTenTTP.executeQuery()) {
                            if (rsTenTTP.next()) {
                                cbTinhTP.addItem(rsTenTTP.getString("TenTTP"));
                            }
                        }
                    }
                }
            }
        }
    }
});

//Bắt sự kiện khi chọn tỉnh thành phố sẽ hiển thị danh sách điểm du lịch của tỉnh thành phố đó

cbTinhTP.addActionListener (e -> {
    String tinhTP = cbTinhTP.getSelectedItem().toString();
    modelDiemDL.setRowCount(0);
    String getTenDDLandDacTrung = "SELECT TenDDL AND DacTrung FROM DIEMDL WHERE MaTTP = (SELECT MaTTP FROM TINHTP WHERE TenTTP = ?)";
    PreparedStatement ps = conn.prepareStatement(getTenDDLandDacTrung); 
    ps.setString(1, tinhTP);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        modelDiemDL.addRow(new Object[] {rs.getString("TenDDL"), rs.getString("DacTrung")});
    }
});

//Bắt sự kiện khi chọn một điểm du lịch sẽ thêm vào danh sách điểm du lịch được chọn

tableDiemDL.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableDiemDL.getSelectedRow();
        String tenDiemDL = modelDiemDL.getValueAt(row, 0).toString();
        modelDiemDLDuocChon.addRow(new Object[] {tenDiemDL});
    }
});

//Bắt sự kiện khi nhấn nút “Thêm” sẽ lưu các điểm du lịch được chọn cho tour vào CSDL.

// CHITIET (MaTour, MaDDL)
// TOUR(MaTour, TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia)
// DIEMDL(MaDDL, TenDDL, MaTTP, DacTrung)

btnThem.addActionListener (e -> {
    String maTour = txtMaTour.getText();
    for (int i = 0; i < modelDiemDLDuocChon.getRowCount(); i++) {
        String tenDiemDL = modelDiemDLDuocChon.getValueAt(i, 0).toString();
        String getMaDDL = "SELECT MaDDL FROM DIEMDL WHERE TenDDL = ?";
        PreparedStatement ps = conn.prepareStatement(getMaDDL);
        ps.setString(1, tenDiemDL);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String maDDL = rs.getString("MaDDL");
            String sql = "INSERT INTO CHITIET (MaTour, MaDDL) VALUES (?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, maTour);
            ps1.setString(2, maDDL);
            ps1.executeUpdate();
        }
    }
});



    
    
