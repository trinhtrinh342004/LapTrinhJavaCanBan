// Câu c
public class NhapXDCH {
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

    JTextField txtMaCH = new JTextField();
    JTextField txtChuCH = new JTextField();
    JTextField txtTenCH = new JTextField();
    JTextField txtDiaChiXD = new JTextField();
    JTextField txtSDT = new JTextField();
    JTextField txtNgayNhap = new JTextField();

    JComboBox<String> cbLoaiXD = new JComboBox<>();

    JTable tableXD = new JTable();
    DefaultTableModel modelXD = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };
    String[] columnsXD = {"Tên xăng dầu", "Giá cơ sở"};
    modelXD.setColumnIdentifiers(columnsXD);
    tableDiemDL.setModel(modelXD);

    JTable tableXDChon = new JTable();
    DefaultTableModel modelXDChon = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };
    String[] columnsXDDuocChon = {"Tên xăng dầu", "Số lượng", "Giá nhập"};
    modelXDChon.setColumnIdentifiers(columnsXDDuocChon);
    tableDiemDL.setModel(modelXDChon);

    JButton btnThem = new JButton("Thêm");

    public NhapXDCH() {
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
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String maCH = txtMaCH.getText().trim();
                    if (!maCH.isEmpty()) {
                        String sql = "SELECT * FROM CUAHANG WHERE MaCH = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, maCH);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            txtTenCH.setText(rs.getString("TenCH"));
                            txtChuCH.setText(rs.getString("ChuCH"));
                            txtDiaChiXD.setText(rs.getString("DiaChi"));
                            txtSDT.setText(rs.getString("SDT"));
                        }
                    }
                }
            }
        });
    }

    // private void loadLoaiXD() {
    //     String sql = "SELECT TenLXD, MaLXD FROM LOAIXD";
    //     ps = conn.prepareStatement(sql);
    //     rs = ps.executeQuery();
    //     cbLoaiXD.addItem("Chọn loại xăng dầu");
    //     while (rs.next()) {
    //         // Nếu muốn vừa hiển thị mã - tên luôn (Trong đề chỉ yêu cầu tên)
    //         String maLXD = rs.getString("MaLXD");
    //         String tenLXD = rs.getString("TenLXD");
    //         cbLoaiXD.addItem(maLXD + " - " + tenLXD);
 

    // // Xử lý nếu hiển thị mã - tên
    // private void handleLoaiXDSelection() {
    //     cbLoaiXD.addActionListener(e -> {
    //         String selectedLoaiXD = cbLoaiXD.getSelectedItem().toString();
    //         if (!selectedLoaiXD.equals("Chọn loại xăng dầu")) {
    //             String maLXD = selectedLoaiXD.split(" - ")[0];
    //             modelXD.setRowCount(0);
    //             String sql = "SELECT TenXD, GiaCoSo FROM XANGDAU WHERE MaLXD = ?";
    //             try {
    //                 ps = conn.prepareStatement(sql);
    //                 ps.setString(1, maLXD);
    //                 rs = ps.executeQuery();
    //                 while (rs.next()) {
    //                     modelXD.addRow(new Object[]{rs.getString("TenXD"), rs.getString("GiaCoSo")});
    //                 }
    
    // LOAIXD (MaLXD, TenLXD)
    // XANGDAU (MaXD, TenXD, GiaCoSo, MaLXD)
    // CUAHANG (MaCH, TenCH, ChuCH, DiaChi, SDT)
    // NHAP (MaCH, MaXD, NgayNhap, SoLuong, GiaNhap)      

    // Xử lý nếu hiển thị tên
    private void loadLoaiXD() {
        String sql = "SELECT TenLXD FROM LOAIXD";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        cbLoaiXD.addItem("Chọn loại xăng dầu");
        while (rs.next()) {
            String tenLXD = rs.getString("TenLXD");
            cbLoaiXD.addItem(tenLXD);
        }
    }

    private void handleLoaiXDSelection() {
        cbLoaiXD.addActionListener(e -> {
            String selectedLoaiXD = cbLoaiXD.getSelectedItem().toString();
            if (!selectedLoaiXD.equals("Chọn loại xăng dầu")) {
                modelXD.setRowCount(0);
                String sql = "SELECT TenXD, GiaCoSo FROM XANGDAU X, LOAIXD L WHERE X.MaLXD = L.MaLXD AND TenLXD = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, selectedLoaiXD);
                rs = ps.executeQuery();
                while (rs.next()) {
                    modelXD.addRow(new Object[]{rs.getString("TenXD"), rs.getString("GiaCoSo")});
                }
            }
        });
    }

    private void themXangDauNhap() {
        tableXD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = tableXD.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }

                String tenXD = modelXD.getValueAt(selectedRow, 0).toString();
                String giaCoSo = modelXD.getValueAt(selectedRow, 1).toString();

                // Kiểm tra xem xăng dầu đã được chọn chưa
                for (int i = 0; i < modelXDChon.getRowCount(); i++) {
                    if (modelXDChon.getValueAt(i, 0).toString().equals(tenXD)) {
                       return;
                    }
                }

                String soLuongNhap = JOptionPane.showInputDialog(null, "Nhập số lượng xăng dầu:");
                int soLuong = Integer.parseInt(soLuongNhap);
                if (soLuongNhap == null || soLuongNhap.isEmpty() || soLuong <= 0) {
                   return;
                }

                double giaNhap = Double.parseDouble(giaCoSo) * soLuong;
                modelXDChon.addRow(new Object[]{tenXD, soLuong, giaNhap});
            }
        });
    }

    private void luu() {
        btnThem.addActionListener(e -> {
            String sqlNhap = "INSERT INTO NHAP (MaCH, NgayNhap, MaXD, SoLuong, GiaNhap) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstNhap = conn.prepareStatement(sqlNhap);
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
                    pstNhap.executeUpdate(); 
                }
            }
            modelXDChon.setRowCount(0); 
        });
    }

    public static void main(String[] args) {
        new NhapXDCH();
    }
}
