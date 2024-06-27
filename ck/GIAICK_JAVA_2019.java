// BACSI(MABS, TENBS)
// BENHNHAN(MABN, TENBN, NGSINH, DCHI, DTHOAI, GIOITINH)
// KHAMBENH(MAKB, MABN, MABS, NGAYKHAM, YEUCAUKHAM, KETLUAN,
// THANHTOAN)
// Tân từ: Cho phép lưu trữ thông tin đặt lịch khám của bệnh nhân và kết luận của bác sĩ sau
// khi khám (KETLUAN) và trạng thái thanh toán phí khám của bệnh nhân.
// DICHVU(MADV, TENDV, DONGIA).
// Tân từ: Lưu trữ các thông tin về dịch vụ khám bệnh và giá của mỗi dịch vụ (DONGIA)
// THUPHI(MAKB, MADV, SOLUONG, THANHTIEN)
// Tân từ : Dùng để lưu trữ thông tin thu phí cho các dịch vụ đã sử dụng cho mỗi lần khám.

// 1. Thêm thông tin bệnh nhân

JTextField txtMaBenhNhan = new JTextField();
JTextField txtTenBenhNhan = new JTextField();
JTextField txtNgaySinh = new JTextField();
JTextField txtDiaChi = new JTextField();
JTextField txtDienThoai = new JTextField();
JComboBox<String> cboGioiTinh = new JComboBox<String>();

JButton btnThem = new JButton("Thêm");

cboGioiTinh.addItem("Nam");
cboGioiTinh.addItem("Nữ");

Connection conn;

btnThem.addActionListener(new ActionListener( e -> {
    String sql = "INSERT INTO BENHNHAN VALUES(?, ?, ?, ?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, txtMaBenhNhan.getText());
    ps.setString(2, txtTenBenhNhan.getText());
    ps.setString(3, txtNgaySinh.getText());
    ps.setString(4, txtDiaChi.getText());
    ps.setString(5, txtDienThoai.getText());
    ps.setString(6, cboGioiTinh.getSelectedItem().toString());
    ps.executeUpdate();
}));

// 2. Đặt lịch khám bệnh của bệnh nhân
// Yêu cầu:  khi nhập mã bệnh nhân và nhấn enter thì sẽ hiển thị tên bệnh nhân và bác sĩ khám được load từ CSDL vào combobox. Nhấn nút "Đặt lịch khám" để thêm lịch khám cho bệnh nhân.

JTextField txtMaBenhNhan = new JTextField();
JTextField txtTenBenhNhan = new JTextField();
txtTenBenhNhan.setEditable(false);
JComboBox<String> cboBacSi = new JComboBox<String>();
JTextField txtNgayKham = new JTextField();
JTextField txtYeuCauKham = new JTextField();

JButton btnDatLichKham = new JButton("Đặt lịch khám");

//Bắt sự kiện khi nhập mã bệnh nhân và nhấn enter để load thông tin bệnh nhân và bác sĩ khám vào combobox

txtMaBenhNhan.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            String sql = "SELECT TENBN FROM BENHNHAN WHERE MABN = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtMaBenhNhan.getText());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                txtTenBenhNhan.setText(rs.getString(1));
            }
            String sql2 = "SELECT TENBS FROM BACSI";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()) {
                cboBacSi.addItem(rs2.getString(1));
            }
        }
    }
});

//Bắt sự kiện khi nhấn nút "Đặt lịch khám"

btnDatLichKham.addActionListener(new ActionListener( e -> {
            //Lấy mã bác sĩ từ tên bác sĩ được chọn
            String sql = "SELECT MABS FROM BACSI WHERE TENBS = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cboBacSi.getSelectedItem().toString());
            ResultSet rs = ps.executeQuery();
            String maBacSi = "";
            if(rs.next()) {
                maBacSi = rs.getString(1);
            }
            //Thêm lịch khám cho bệnh nhân
            String sql2 = "INSERT INTO KHAMBENH (MABN, MABS, NGAYKHAM, YEUCAUKHAM) VALUES(?, ?, ?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, txtMaBenhNhan.getText());
            ps2.setString(2, maBacSi);
            ps2.setString(3, txtNgayKham.getText());
            ps2.setString(4, txtYeuCauKham.getText());
        }
));


// 3. Thêm chi tiết khám bệnh.
// Yêu cầu: Tên bác sĩ được load từ CSDL vào combobox <Tên bác sĩ>. Danh sách các dịch vụ của phòng khám được load vào jTable <Danh sách dịch vụ>. Chọn bác sĩ trong combobox, nhập ngày khám và nhấn enter thì danh sách bệnh nhân đặt lịch hẹn với bác sĩ sẽ hiển thị ở combobox <Tên bệnh nhân>. Chọn tên bệnh nhân sẽ hiển thị yêu cầu khám của bệnh nhân đó. Nhập kết luận và chọn các dịch vụ, mỗi lần chọn dịch vụ bên <Danh sách dịch vụ> thì dịch vụ đó sẽ được liệt kê vào jTable <Danh sách dịch vụ bác sĩ chọn>, thêm số lượng cho từng dịch vụ. Nhấn nút "Thêm" để thêm toàn bộ thông tin trên vào CSDL.

JComboBox<String> cboBacSi = new JComboBox<String>();
JComboBox<String> cboBenhNhan = new JComboBox<String>();
JTextField txtNgayKham = new JTextField();
JTextField txtYeuCauKham = new JTextField();
JTextField txtKetLuan = new JTextField();

JTable tblDichVu = new JTable();
DefaultTableModel modelDichVu = new DefaultTableModel();
String colsDicxhVu[] = {"Tên dich vụ"};
modelDichVu.setColumnIdentifiers(colsDichVu);
tblDichVu.setModel(modelDichVu);

JTable tblDichVuBacSi = new JTable();
DefaultTableModel modelDichVuBacSi = new DefaultTableModel();
String colsDichVuBacSi[] = {"Tên dich vụ", "Số lượng"};
modelDichVuBacSi.setColumnIdentifiers(colsDichVuBacSi);
tblDichVuBacSi.setModel(modelDichVuBacSi);

JButton btnThem = new JButton("Thêm");

//Load tên bác sĩ vào combobox

String sql = "SELECT TENBS FROM BACSI";
PreparedStatement ps = conn.prepareStatement(sql);
ResultSet rs = ps.executeQuery();
while(rs.next()) {
    cboBacSi.addItem(rs.getString(1));
}

//Load danh sách dịch vụ vào jTable

String sql2 = "SELECT TENDV FROM DICHVU";
PreparedStatement ps2 = conn.prepareStatement(sql2);
ResultSet rs2 = ps2.executeQuery();
while(rs2.next()) {
    modelDichVu.addRow(new Object[] {rs2.getString(1)});
}

//Bắt sự kiện khi chọn bác sĩ, nhập ngày khám và nhấn enter để load danh sách bệnh nhân đặt lịch hẹn với bác sĩ

txtNgayKham.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Lấy mã bác sĩ từ combobox
            String tenBS = cbBacSiKham.getSelectedItem().toString();

            // Tạo câu truy vấn để lấy MABS từ tên bác sĩ
            String sqlGetMABS = "SELECT MABS FROM BACSI WHERE TENBS = ?";
            PreparedStatement psGetMABS = conn.prepareStatement(sqlGetMABS);
            psGetMABS.setString(1, tenBS);
            ResultSet rsGetMABS = psGetMABS.executeQuery();

            String maBS = "";
            if (rsGetMABS.next()) {
                maBS = rsGetMABS.getString("MABS");
            } else {
                return;
            }

            String sql = "SELECT TENBN FROM BENHNHAN WHERE MABN IN (SELECT MABN FROM KHAMBENH WHERE MABS = ? AND NGAYKHAM = ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cboBacSi.getSelectedItem().toString());
            ps.setString(2, txtNgayKham.getText());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cboBenhNhan.addItem(rs.getString(1));
            }
        }
    }
});

//Bắt sự kiện khi chọn tên bệnh nhân để hiển thị yêu cầu khám của bệnh nhân đó

cboBenhNhan.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String sql = "SELECT YEUCAUKHAM FROM KHAMBENH WHERE MABN IN (SELECT MABN FROM BENHNHAN WHERE TENBN = ?) AND MABS = ? AND NGAYKHAM = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cboBenhNhan.getSelectedItem().toString());
        ps.setString(2, cboBacSi.getSelectedItem().toString());
        ps.setString(3, txtNgayKham.getText());
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            txtYeuCauKham.setText(rs.getString(1));
        }
    }
});

//Bắt sự kiện khi chọn dịch vụ từ jTable <Danh sách dịch vụ> để thêm vào jTable <Danh sách dịch vụ bác sĩ chọn>

tblDichVu.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        int row = tblDichVu.getSelectedRow();
        if (row != -1) {
            String tenDichVu = modelDichVu.getValueAt(row, 0).toString();
            modelDichVuBacSi.addRow(new Object[] {tenDichVu, 0});
    }
}
});

//Thêm số lượng cho từng dịch vụ

tblDichVuBacSi.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        int row = tblDichVuBacSi.getSelectedRow();
        if (row != -1) {
            String soLuong = JOptionPane.showInputDialog("Nhập số lượng");
            modelDichVuBacSi.setValueAt(soLuong, row, 1);
        }
    }
});

//Bắt sự kiện khi nhấn nút "Thêm" để thêm thông tin vào CSDL

btnThem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        //Lấy mã bác sĩ từ tên bác sĩ được chọn
        String sql = "SELECT MABS FROM BACSI WHERE TENBS = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cboBacSi.getSelectedItem().toString());
        ResultSet rs = ps.executeQuery();
        String maBacSi = "";
        if(rs.next()) {
            maBacSi = rs.getString(1);
        }
        //Lấy mã bệnh nhân từ tên bệnh nhân được chọn
        String sql2 = "SELECT MABN FROM BENHNHAN WHERE TENBN = ?";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.setString(1, cboBenhNhan.getSelectedItem().toString());
        ResultSet rs2 = ps2.executeQuery();
        String maBenhNhan = "";
        if(rs2.next()) {
            maBenhNhan = rs2.getString(1);
        }
        //Thêm thông tin khám bệnh
        String sql3 = "UPDATE KHAMBENH SET KETLUAN = ?, THANHTOAN = ? WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ? AND YEUCAUKHAM = ?";
        PreparedStatement ps3 = conn.prepareStatement(sql3);
        ps3.setString(1, txtKetLuan.getText());
        ps3.setString(2, "Chưa thanh toán");
        ps3.setString(3, maBenhNhan);
        ps3.setString(4, maBacSi);
        ps3.setString(5, txtNgayKham.getText());
        ps3.setString(6, txtYeuCauKham.getText());
        ps3.executeUpdate();
        //Thêm thông tin thu phí
        for(int i = 0; i < modelDichVuBacSi.getRowCount(); i++) {
            //Lấy mã dịch vụ từ tên dịch vụ
            String sql4 = "SELECT MADV FROM DICHVU WHERE TENDV = ?";
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setString(1, modelDichVuBacSi.getValueAt(i, 0).toString());
            ResultSet rs4 = ps4.executeQuery();
            String maDichVu = "";
            if(rs4.next()) {
                maDichVu = rs4.getString(1);
            }

            //Lấy mã khám bệnh từ mã bệnh nhân, mã bác sĩ và ngày khám
            String sql5 = "SELECT MAKB FROM KHAMBENH WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ?";
            PreparedStatement ps5 = conn.prepareStatement(sql5);
            ps5.setString(1, maBenhNhan);
            ps5.setString(2, maBacSi);
            ps5.setString(3, txtNgayKham.getText());
            ResultSet rs5 = ps5.executeQuery();
            String maKB = "";
            if(rs5.next()) {
                maKB = rs5.getString(1);
            }

            //Thêm thông tin thu phí
            String sql6 = "INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES(?, ?, ?, ?)";
            PreparedStatement ps6 = conn.prepareStatement(sql6);
            ps6.setString(1, maKB);
            ps6.setString(2, maDichVu);
            ps6.setInt(3, Integer.parseInt(modelDichVuBacSi.getValueAt(i, 1).toString()));

            //Lấy đơn giá của dịch vụ
            String sql7 = "SELECT DONGIA FROM DICHVU WHERE MADV = ?";
            PreparedStatement ps7 = conn.prepareStatement(sql7);
            ps7.setString(1, maDichVu);
            ResultSet rs7 = ps7.executeQuery();
            int donGia = 0;
            if(rs7.next()) {
                donGia = rs7.getInt(1);
            }
            ps6.setInt(4, donGia * Integer.parseInt(modelDichVuBacSi.getValueAt(i, 1).toString()));
            ps6.executeUpdate();
        }
    }
});







