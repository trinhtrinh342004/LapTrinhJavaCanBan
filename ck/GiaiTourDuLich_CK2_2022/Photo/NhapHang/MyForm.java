// Câu c
public class MyForm {
    private JTextField txtNgayLap;
    private JTextField txtGhiChu;
    private JComboBox<String> comboBoxNCC;
    private JTable tableSanPham;
    private JTable tableSanPhamNhap;
    private JButton btnTaoPhieuNhap;

    public MyForm() {
        // Load nhà cung cấp vào combobox
        loadNCC();

        // Sự kiện khi chọn nhà cung cấp
        comboBoxNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSanPham();
            }
        });

        // Sự kiện khi chọn sản phẩm trong danh sách sản phẩm
        tableSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    addSanPhamToNhap();
                }
            }
        });

        // Sự kiện khi nhấn nút tạo phiếu nhập
        btnTaoPhieuNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taoPhieuNhap();
            }
        });
    }

    // Hàm load nhà cung cấp vào combobox
    private void loadNCC() {
        try {
            Connection conn;
            PreparedStatement ps = conn.prepareStatement("SELECT MANCC, TENNCC FROM NHACC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBoxNCC.addItem(rs.getString("TENNCC"));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Hàm load sản phẩm vào bảng danh sách sản phẩm
    private void loadSanPham() {
        try {
            String ncc = (String) comboBoxNCC.getSelectedItem();
            Connection conn;
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT  TENSP FROM SANPHAM WHERE MANCC = (SELECT MANCC FROM NHACC WHERE TENNCC = ?)");
            ps.setString(1, ncc);
            ResultSet rs = ps.executeQuery();
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("MASP"));
                row.add(rs.getString("TENSP"));
                data.add(row);
            }
            tableSanPham.setModel(new DefaultTableModel(data, new Vector<>(java.util.Arrays.asList("MASP", "TENSP"))));
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Hàm thêm sản phẩm vào bảng danh sách sản phẩm nhập
    private void addSanPhamToNhap() {
        int selectedRow = tableSanPham.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel modelSanPhamNhap = (DefaultTableModel) tableSanPhamNhap.getModel();
            String maSP = (String) tableSanPham.getValueAt(selectedRow, 0);
            String tenSP = (String) tableSanPham.getValueAt(selectedRow, 1);
            Vector<Object> row = new Vector<>();
            row.add(maSP);
            row.add(tenSP);
            row.add(1); // Số lượng mặc định là 1
            modelSanPhamNhap.addRow(row);
        }
    }

    // Hàm tạo phiếu nhập
    private void taoPhieuNhap() {
        try {
            Connection conn;

            double tongTien = 0.0;
            for (int i = 0; i < tableSanPhamNhap.getRowCount(); i++) {
                int soLuong = (int) tableSanPhamNhap.getValueAt(i, 2);
                double donGia = getDonGia((String) tableSanPhamNhap.getValueAt(i, 0));
                tongTien += soLuong * donGia;
            }

            // Insert into PHIEUNHAP
            PreparedStatement psPhieuNhap = conn.prepareStatement(
                    "INSERT INTO PHIEUNHAP (NGAYLAP, GHICHU, TONGTIEN) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            psPhieuNhap.setString(1, txtNgayLap.getText());
            psPhieuNhap.setString(2, txtGhiChu.getText());
            psPhieuNhap.setDouble(3, tongTien);
            psPhieuNhap.executeUpdate();

            ResultSet rsPhieuNhap = psPhieuNhap.getGeneratedKeys();
            int mapn = 0;
            if (rsPhieuNhap.next()) {
                mapn = rsPhieuNhap.getInt(1);
            }

            // Insert into CT_NHAP
            for (int i = 0; i < tableSanPhamNhap.getRowCount(); i++) {
                PreparedStatement psCTNhap = conn
                        .prepareStatement("INSERT INTO CT_NHAP (MAPN, MASP, SL) VALUES (?, ?, ?)");
                psCTNhap.setInt(1, mapn);
                psCTNhap.setString(2, (String) tableSanPhamNhap.getValueAt(i, 0));
                psCTNhap.setInt(3, (int) tableSanPhamNhap.getValueAt(i, 2));
                psCTNhap.executeUpdate();
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Hàm lấy đơn giá sản phẩm
    private double getDonGia(String maSP) {
        double donGia = 0.0;
        try {
            Connection conn;
            PreparedStatement ps = conn.prepareStatement("SELECT DONGIA FROM SANPHAM WHERE MASP = ?");
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                donGia = rs.getDouble("DONGIA");
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return donGia;
    }

    public static void main(String[] args) {
        new MyForm();
    }
}