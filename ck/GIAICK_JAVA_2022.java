// DIENTHOAI(IMEI, TENDT, LOAIDT, HOTENKH, SDTKH) 
// Tân từ: Lưu trữ thông tin điện thoại gồm: số IMEI của điện thoại, tên, loại điện thoại, họ tên (HOTENKH) và số điện thoại (SDTKH) của khách hàng. 
// SUACHUA(MAPSC, NGAYLAP, NOIDUNG, IMEI, TONGTIEN) 
// Tân từ: Lưu trữ thông tin phiếu sửa chữa của điện thoại gồm: mã phiếu sửa chữa (MAPSC), ngày lập, nội dung sửa chữa, số IMEI của điện thoại, tổng tiền sửa chữa. 
// CONGVIEC(MACV, TENCV, DONGIA) 
// Tân từ: Lưu trữ thông tin các công việc sửa chữa cho điện thoại. 
// CT_SC(MAPSC, MACV). 
// Tân từ: Lưu trữ thông tin về chi tiết sửa chữa của điện thoại. 

// 1. Thêm thông tin điện thoại cần sửa chữa
//Yêu cầu: Nhấn nút “Thêm” để thêm thông tin điện thoại cần sửa chữa vào CSDL.

JLabel lbSoIMEI = new JLabel();
JLabel lbTenDT = new JLabel();
JLabel lbLoaiDT = new JLabel();
JLabel lbHoTenKH = new JLabel();
JLabel lbSDTKH = new JLabel();

JTextField txtSoIMEI = new JTextField("1234567890");
JTextField txtTenDT = new JTextField("Iphone 13 Pro");
JComboBox<String> cbLoaiDT = new JComboBox<>();
JTextField txtHoTenKH = new JTextField("Nguyễn C");
JTextField txtSDTKH = new JTextField("09xxxxxxxx");

JButton btnThem = new JButton("Thêm");

cbLoaiDT.addItem("Iphone");

btnThem.addActionListener(new ActionListener(e -> {
    String soIMEI = txtSoIMEI.getText();
    String tenDT = txtTenDT.getText();
    String loaiDT = cbLoaiDT.getSelectedItem().toString();
    String hoTenKH = txtHoTenKH.getText();
    String sdtKH = txtSDTKH.getText();

    String themDT = "INSERT INTO DIENTHOAI VALUES(?, ?, ?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(themDT);
    ps.setString(1, soIMEI);
    ps.setString(2, tenDT);
    ps.setString(3, loaiDT);
    ps.setString(4, hoTenKH);
    ps.setString(5, sdtKH);
    ps.executeUpdate();
}));

// 2. Thêm thông tin phiếu sửa chữa
//Yêu cầu: Khi nhập số IMEI của điện thoại và nhấn enter thì sẽ hiển thị tên điện thoại và họ tên khách hàng sở hữu điện thoại (không cho phép chỉnh sửa). Nhấn nút “Thêm” để thêm thông tin phiếu sửa chữa điện thoại.

JLabel lbSoIMEI = new JLabel();
JLabel lbTenDT = new JLabel();
JLabel lbHoTenKH = new JLabel();
JLabel lbNgayLap = new JLabel();
JLabel lbNDSuaChua = new JLabel();

JTextField txtSoIMEI = new JTextField("1234567890");
JTextField txtTenDT = new JTextField();
txt.tenDT.setEditable(false);
JTextField txtHoTenKH = new JTextField();
txt.hoTenKH.setEditable(false);
JTextField txtNgayLap = new JTextField();

//txtNgayLap is set to local date
txtNgayLap.setText(LocalDate.now().toString());

JTextArea txtNDSuaChua = new JTextArea();

//Tạo scroll ngang và dọc cho txtNDSuaChua
JScrollPane scroll = new JScrollPane(txtNDSuaChua, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

JButton btnThem = new JButton("Thêm");

//Bắt sự kiện khi nhập số IMEI và nhấn enter thì hiển thị tên điện thoại và họ tên khách hàng
txtSoIMEI.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String soIMEI = txtSoIMEI.getText();
            String layTenDTVaHoTenKH = "SELECT TENDT, HOTENKH FROM DIENTHOAI WHERE IMEI = ?";
            PreparedStatement ps = conn.prepareStatement(layTenDTVaHoTenKH);
            ps.setString(1, soIMEI);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtTenDT.setText(rs.getString(1));
                txtHoTenKH.setText(rs.getString(2));
            }
        }
    }
});

//Bắt sự kiện khi nhấn nút "Thêm" để thêm thông tin phiếu sửa chữa
btnThem.addActionListener(new ActionListener(e -> {
    String soIMEI = txtSoIMEI.getText();
    String ngayLap = txtNgayLap.getText();
    String noiDung = txtNDSuaChua.getText();

    String themPSC = "INSERT INTO SUACHUA(NGAYLAP, NOIDUNG, IMEI) VALUES(?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(themPSC);
    ps.setString(1, ngayLap);
    ps.setString(2, noiDung);
    ps.setString(3, soIMEI);
    ps.executeUpdate();
}));

// 3. Thêm thông tin sửa chữa cho điện thoại
//Yêu cầu: Khi nhập số IMEI và nhấn enter thì sẽ hiển thị tên điện thoại và họ tên khách hàng sở hữu điện thoại đó. Nhập thông tin ngày lập và nhấn enter sẽ hiển thị ra nội dung sửa chữa của điện thoại với số IMEL ở trên. Các thông tin: tên điện thoại, họ tên khách hàng và nội dung sửa chữa không cho phép thay đổi.
//Load danh sách công việc từ CSDL vào JTable “Danh sách công việc”; và mỗi lần chọn công việc trong danh sách này thì công việc đó sẽ được hiển thị trên JTable “Danh sách công việc sửa chữa” cùng với đơn giá của công việc đó. Nhấn nút “Thêm sửa chữa” thì sẽ lưu danh sách công việc sửa chữa này vào CSDL, đồng thời lưu tổng tiền vào phiếu sửa chữa của điện thoại đang sửa chữa.
// Lưu ý: TONGTIEN sẽ được tính bằng tổng DONGIA của tất cả công việc trong danh sách công việc sửa chữa.

JLabel lbSoIMEI = new JLabel();
JLabel lbTenDT = new JLabel();
JLabel lbHoTenKH = new JLabel();
JLabel lbNgayLap = new JLabel();
JLabel lbNDSuaChua = new JLabel();
JLabel lbDSCV = new JLabel();
JLabel lbDSCVSC = new JLabel();

JTextField txtSoIMEI = new JTextField("1234567890");
JTextField txtTenDT = new JTextField();
txt.tenDT.setEditable(false);
JTextField txtHoTenKH = new JTextField();
txt.hoTenKH.setEditable(false);
JTextField txtNgayLap = new JTextField();

//txtNgayLap is set to local date
txtNgayLap.setText(LocalDate.now().toString());

JTextArea txtNDSuaChua = new JTextArea();

//Tạo scroll ngang và dọc cho txtNDSuaChua
JScrollPane scroll = new JScrollPane(txtNDSuaChua, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

JTable tbDSCV = new JTable();
DefaultTableModel modelDSCV = new DefaultTableModel();
String[] colsDSCV = {"Mã công việc", "Công việc"};
modelDSCV.setColumnIdentifiers(colsDSCV);
tbDSCV.setModel(modelDSCV);

JTable tbDSCVSC = new JTable();
DefaultTableModel modelDSCVSC = new DefaultTableModel();
String[] colsDSCVSC = {"Công việc", "Đơn giá"};
modelDSCVSC.setColumnIdentifiers(colsDSCVSC);

JButton btnThemSC = new JButton("Thêm sửa chữa");

//Bắt sự kiện khi nhập số IMEI và nhấn enter thì hiển thị tên điện thoại và họ tên khách hàng sở hữu điện thoại đó

txtSoIMEI.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String soIMEI = txtSoIMEI.getText();
            String layTenDTVaHoTenKH = "SELECT TENDT, HOTENKH FROM DIENTHOAI WHERE IMEI = ?";
            PreparedStatement ps = conn.prepareStatement(layTenDTVaHoTenKH);
            ps.setString(1, soIMEI);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtTenDT.setText(rs.getString(1));
                txtHoTenKH.setText(rs.getString(2));
            }
        }
    }
});

//Bắt sự kiện khi nhập ngày lập và nhấn enter thì hiển thị ra nội dung sửa chữa của điện thoại với số IMEI ở trên

txtNgayLap.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String soIMEI = txtSoIMEI.getText();
            String ngayLap = txtNgayLap.getText();
            String layNoiDungSuaChua = "SELECT NOIDUNG FROM SUACHUA WHERE IMEI = ? AND NGAYLAP = ?";
            PreparedStatement ps = conn.prepareStatement(layNoiDungSuaChua);
            ps.setString(1, soIMEI);
            ps.setString(2, ngayLap);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtNDSuaChua.setText(rs.getString(1));
            }
        }
    }
});

//Nội dung sửa chữa không cho phép chỉnh sửa
txtNDSuaChua.setEditable(false);

//Load danh sách công việc từ CSDL vào JTable “Danh sách công việc”
String layDSCV = "SELECT MACV, TENCV FROM CONGVIEC";
PreparedStatement ps = conn.prepareStatement(layDSCV);
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    String maCV = rs.getString(1);
    String tenCV = rs.getString(2);
    modelDSCV.addRow(new Object[] {maCV, tenCV});
}

//Mỗi lần chọn công việc trong danh sách này thì công việc đó sẽ được hiển thị trên JTable “Danh sách công việc sửa chữa” cùng với đơn giá của công việc đó

tbDSCV.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tbDSCV.getSelectedRow();
        String maCV = modelDSCV.getValueAt(row, 0).toString();
        String tenCV = modelDSCV.getValueAt(row, 1).toString();
        String layDonGia = "SELECT DONGIA FROM CONGVIEC WHERE MACV = ?";
        PreparedStatement ps = conn.prepareStatement(layDonGia);
        ps.setString(1, maCV);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int donGia = rs.getInt(1);
            modelDSCVSC.addRow(new Object[] {tenCV, donGia});
        }
    }
});

//Nhấn nút “Thêm sửa chữa” thì sẽ lưu danh sách công việc sửa chữa này vào CSDL, đồng thời lưu tổng tiền vào phiếu sửa chữa của điện thoại đang sửa chữa

btnThemSC.addActionListener(new ActionListener(e -> {

    //Lấy MAPSC từ IMEI và ngày lập
    String layMAPSC = "SELECT MAPSC FROM SUACHUA WHERE IMEI = ? AND NGAYLAP = ?";
    PreparedStatement ps = conn.prepareStatement(layMAPSC);
    ps.setString(1, txtSoIMEI.getText());
    ps.setString(2, txtNgayLap.getText());
    ResultSet rs = ps.executeQuery();

    int rowCount = modelDSCVSC.getRowCount();
    int tongTien = 0;
    while (rs.next()) {
        String maPSC = rs.getString(1);
        for (int i = 0; i < rowCount; i++) {
            //Lấy MACV từ TENCV
            String layMACV = "SELECT MACV FROM CONGVIEC WHERE TENCV = ?";
            PreparedStatement ps1 = conn.prepareStatement(layMACV);
            ps1.setString(1, modelDSCVSC.getValueAt(i, 0).toString());
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                String maCV = rs1.getString(1);
                String themCTSC = "INSERT INTO CT_SC VALUES(?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(themCTSC);
                ps2.setString(1, maPSC);
                ps2.setString(2, maCV);
                ps2.executeUpdate();
                tongTien += Integer.parseInt(modelDSCVSC.getValueAt(i, 1).toString());
            }
        }
    }

    String capNhatTongTien = "UPDATE SUACHUA SET TONGTIEN = ? WHERE MAPSC = ?";
    PreparedStatement ps2 = conn.prepareStatement(capNhatTongTien);
    ps2.setInt(1, tongTien);
    ps2.setString(2, rs.getString(1));
    ps2.executeUpdate();
}));









