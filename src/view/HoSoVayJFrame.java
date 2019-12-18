/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.HoSoDAO;
import dao.KhachHangDAO;
import dao.LoaiHinhVayDAO;
import helper.DateHelper;
import helper.DialogHelper;
import helper.ShareHelper;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.HoSo;
import model.LoaiHinhVay;

/**
 *
 * @author maihu
 */
public class HoSoVayJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienJFrame
     */
    public HoSoVayJFrame() {
        initComponents();
        init();

    }

    int index = 0;
    LoaiHinhVayDAO lhdao = new LoaiHinhVayDAO();
    HoSoDAO hsdao = new HoSoDAO();
    KhachHangDAO khdao = new KhachHangDAO();

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiHinhVay.getModel();
        model.removeAllElements();
        try {
            List<LoaiHinhVay> list = lhdao.select();
            for (LoaiHinhVay lh : list) {
                model.addElement(lh);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn loại hình!");
        }
    }

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);

        txtNgayHetHan.setText(DateHelper.toString(new Date(), "dd/MM/yyyy"));
        tblDanhSach.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblDanhSach.getTableHeader().setOpaque(false);
        tblDanhSach.getTableHeader().setBackground(new Color(32, 136, 203));
        tblDanhSach.getTableHeader().setForeground(new Color(32, 136, 203));
        tblDanhSach.setRowHeight(25);
    }

    void load(int sort) {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            List<HoSo> list = null;
            String keyword = txtTimKiem2.getText();
            switch (sort) {
                case 0:
                    list = hsdao.selectByKeyword(keyword);
                    break;
                case 1:
                    list = hsdao.selectByKeywordGrSoTienDESC(keyword);
                    break;
                case 2:
                    list = hsdao.selectByKeywordGrSoTienASC(keyword);
                    break;
                case 3:
                    list = hsdao.selectByKeywordGrNgayVayDESC(keyword);
                    break;
                case 4:
                    list = hsdao.selectByKeywordGrNgayVayASC(keyword);
                    break;
                case 5:
                    list = hsdao.selectByKeywordGrNgayHetHanDESC(keyword);
                    break;
                case 6:
                    list = hsdao.selectByKeywordGrNgayHetHanASC(keyword);
                    break;
                case 7:
                    list = hsdao.selectByKeyword_IPM(keyword);
                    break;
                case 8:
                    list = hsdao.selectByKeywordGrSoTienDESC_IPM(keyword);
                    break;
                case 9:
                    list = hsdao.selectByKeywordGrSoTienASC_IPM(keyword);
                    break;
                case 10:
                    list = hsdao.selectByKeywordGrNgayVayDESC_IPM(keyword);
                    break;
                case 11:
                    list = hsdao.selectByKeywordGrNgayVayASC_IPM(keyword);
                    break;
                case 12:
                    list = hsdao.selectByKeywordGrNgayHetHanDESC_IPM(keyword);
                    break;
                case 13:
                    list = hsdao.selectByKeywordGrNgayHetHanASC_IPM(keyword);
                    break;
                default:
                    list = hsdao.selectByKeyword(keyword);
            }
            for (HoSo hs : list) {
                Object[] row = {
                    hs.getMaHoSo(),
                    hs.getMaNguoiVay(),
                    hs.getHoTenNguoiVay(),
                    khdao.findById(hs.getMaNguoiVay()),
                    hs.getLoaiHinhVay(),
                    String.valueOf(hs.getSoTien()),
                    hs.getMucDich(),
                    hs.getThoiHan(),
                    hs.getNgayVay(),
                    hs.getNhanVienThucHien(),
                    hs.getNgayHetHan(),
                    hs.isDaThanhToan(),
                    hs.getNgayTra(),
                    hs.getGhiChu()
                };
                model.addRow(row);
                System.out.println(Double.valueOf(hs.getSoTien()));
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    String toName(int manv) {
        return khdao.findById(manv).getHoTen();
    }

    private void clear() {
        txtHoTen.setText("");
        txtCmnd.setText("");
        txtMaHoSo.setText("");
        cboLoaiHinhVay.setSelectedIndex(0);
        txtSoTien.setText("");
        txtMucDich.setText("");
        txtNgayHetHan.setText("");
        txtTaiSanTheChap.setText("");
        txtGhiChu.setText("");
        setStatus(true);
    }

    void setModel(HoSo model) {
        txtHoTen.setText(model.getHoTenNguoiVay());
        txtMaHoSo.setText(model.getMaHoSo());
        cboLoaiHinhVay.setSelectedItem(model.getLoaiHinhVay());
        txtSoTien.setText(String.valueOf(model.getSoTien()));
        cboThoiHan.setSelectedItem(model.getThoiHan());
        txtCmnd.setText(model.getCmnd());
        txtMucDich.setText(model.getMucDich());
        txtNgayHetHan.setText(DateHelper.toString(model.getNgayHetHan()));
        txtTaiSanTheChap.setText(model.getTaiSanTheChap());
        txtGhiChu.setText(model.getGhiChu());
    }

    HoSo getModel() {
        HoSo model = new HoSo();
        model.setHoTenNguoiVay(txtHoTen.getText());
        model.setCmnd(txtCmnd.getText());
        model.setMaHoSo(txtMaHoSo.getText());
        LoaiHinhVay lh = (LoaiHinhVay) cboLoaiHinhVay.getSelectedItem();
        model.setLoaiHinhVay(lh.getMaLoai());
        model.setSoTien(Float.valueOf(txtSoTien.getText()));
        model.setMucDich(txtMucDich.getText());
        model.setNgayHetHan(DateHelper.toDate(txtNgayHetHan.getText()));
        model.setTaiSanTheChap(txtTaiSanTheChap.getText());
        model.setGhiChu(txtGhiChu.getText());
        model.setThoiHan((String) cboThoiHan.getSelectedItem());
        return model;
    }

    void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnCapNhat.setEnabled(!insertable);
    }

    void insert() {
        HoSo model = getModel();
        int maNgVay = ((khdao.findByCMND(txtCmnd.getText()).getMaKhachHang()));
        model.setMaNguoiVay(maNgVay);
        model.setNhanVienThucHien(String.valueOf(ShareHelper.USER.getMaNV()));
        model.setDaThanhToan(false);
        model.setNgayVay(new Date());
        model.setSoTienLai(model.getSoTien() * Float.valueOf(txtLaiSuat.getText()) / 100);
        try {
            hsdao.insert(model);
            this.clear();
            this.load(0);
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    void edit() {
        try {
            String mahs = (String) tblDanhSach.getValueAt(this.index, 0);
            HoSo model = hsdao.findById(mahs);
            model.setCmnd(khdao.findById(hsdao.findById(mahs).getMaNguoiVay()).getCmnd().trim());
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void update() {
        HoSo model = getModel();
        model.setMaHoSo(txtMaHoSo.getText());
        model.setSoTienLai(model.getSoTien() * Float.valueOf(txtLaiSuat.getText()) / 100);
        try {
            hsdao.update(model);
            this.clear();
            this.load(0);
            setStatus(true);
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
            e.printStackTrace();
        }

    }

//    void delete() {
//        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa hồ sơ này?")) {
//            String mahs = txtMaHoSo.getText();
//            try {
//                hsdao.delete(mahs);
//                this.load();
//                this.clear();
//                DialogHelper.alert(this, "Xóa thành công!");
//            } catch (Exception e) {
//                DialogHelper.alert(this, "Xóa thất bại!");
//                e.printStackTrace();
//            }
//        }
//    }
    void selectComboBoxLH() {
        LoaiHinhVay lh = (LoaiHinhVay) cboLoaiHinhVay.getSelectedItem();
        txtLaiSuat.setText(lh.getLaiSuat().toString());
    }

    void selectComboBoxTH() {
        int ind = cboThoiHan.getSelectedIndex();
        Date today = new Date();
        if (ind == 0) {
            today.setMonth(today.getMonth() + 3);
            txtNgayHetHan.setText(DateHelper.toString(today));
        } else if (ind == 1) {
            today.setMonth(today.getMonth() + 6);
            txtNgayHetHan.setText(DateHelper.toString(today));
        } else {
            today.setMonth(today.getMonth() + 12);
            txtNgayHetHan.setText(DateHelper.toString(today));
        }
    }

    public boolean isvalid() {
        StringBuffer sb = new StringBuffer();
        if (txtHoTen.getText().length() == 0) {
            sb.append("Vui lòng nhập họ tên người vay!\n");
        } else if (!txtHoTen.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$")) {
            sb.append("Vui lòng nhập đúng định dạng họ tên!\n");
        }
        if (txtCmnd.getText().length() == 0) {
            sb.append("Vui lòng nhập CMND!\n");
        } else if (!txtCmnd.getText().matches("\\d{9}")) {
            sb.append("Vui lòng nhập đúng định dạng CMND!");
        }
        if (txtSoTien.getText().length() == 0) {
            sb.append("Vui lòng nhập số tiền vay!\n");
        } else if (Float.valueOf(txtSoTien.getText()) <= 0) {
            sb.append("Số tiền vay phải dương!");
        }
        if (sb.length() != 0) {
            sb.append("Mời nhập lại!");
            DialogHelper.alert(this, sb.toString());
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlCapNhat = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtMucDich = new javax.swing.JTextField();
        txtNgayHetHan = new javax.swing.JTextField();
        txtSoTien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaHoSo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTaiSanTheChap = new javax.swing.JTextArea();
        cboLoaiHinhVay = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtLaiSuat = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboThoiHan = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtCmnd = new javax.swing.JTextField();
        pnlDanhSach = new javax.swing.JPanel();
        pnlTimKiem = new javax.swing.JPanel();
        txtTimKiem2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboSort = new javax.swing.JComboBox<>();
        chkIsPay = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ HỒ SƠ VAY");
        setPreferredSize(new java.awt.Dimension(1462, 921));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 248, 244));
        jPanel1.setPreferredSize(new java.awt.Dimension(1534, 971));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(32, 136, 203));
        jLabel1.setText("QUẢN LÝ HỒ SƠ VAY");

        tabs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        pnlCapNhat.setBackground(new java.awt.Color(255, 248, 244));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Họ tên người vay:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Mã hồ sơ:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Loại hình vay:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Số tiền:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Mục đích:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Ngày hết hạn");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Ghi chú");

        txtHoTen.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoTen.setMaximumSize(new java.awt.Dimension(6, 35));
        txtHoTen.setMinimumSize(new java.awt.Dimension(6, 35));
        txtHoTen.setPreferredSize(new java.awt.Dimension(6, 35));

        txtMucDich.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMucDich.setMaximumSize(new java.awt.Dimension(6, 35));
        txtMucDich.setMinimumSize(new java.awt.Dimension(6, 35));
        txtMucDich.setPreferredSize(new java.awt.Dimension(6, 35));

        txtNgayHetHan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNgayHetHan.setMaximumSize(new java.awt.Dimension(6, 35));
        txtNgayHetHan.setMinimumSize(new java.awt.Dimension(6, 35));
        txtNgayHetHan.setPreferredSize(new java.awt.Dimension(6, 35));

        txtSoTien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSoTien.setMaximumSize(new java.awt.Dimension(6, 35));
        txtSoTien.setMinimumSize(new java.awt.Dimension(6, 35));
        txtSoTien.setPreferredSize(new java.awt.Dimension(6, 35));

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add.png"))); // NOI18N
        btnThem.setText("   Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(119, 43));
        btnThem.setMinimumSize(new java.awt.Dimension(119, 43));
        btnThem.setPreferredSize(new java.awt.Dimension(119, 43));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnMoi.setText("   Mới");
        btnMoi.setMaximumSize(new java.awt.Dimension(107, 43));
        btnMoi.setMinimumSize(new java.awt.Dimension(107, 43));
        btnMoi.setPreferredSize(new java.awt.Dimension(107, 43));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save.png"))); // NOI18N
        btnCapNhat.setText("   Cập nhật");
        btnCapNhat.setMaximumSize(new java.awt.Dimension(147, 43));
        btnCapNhat.setMinimumSize(new java.awt.Dimension(147, 43));
        btnCapNhat.setPreferredSize(new java.awt.Dimension(147, 43));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Tài sản thế chấp:");

        txtMaHoSo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMaHoSo.setEnabled(false);
        txtMaHoSo.setMaximumSize(new java.awt.Dimension(6, 35));
        txtMaHoSo.setMinimumSize(new java.awt.Dimension(6, 35));
        txtMaHoSo.setPreferredSize(new java.awt.Dimension(6, 35));
        txtMaHoSo.setRequestFocusEnabled(false);

        txtTaiSanTheChap.setColumns(20);
        txtTaiSanTheChap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTaiSanTheChap.setRows(5);
        txtTaiSanTheChap.setMaximumSize(new java.awt.Dimension(6, 42));
        txtTaiSanTheChap.setMinimumSize(new java.awt.Dimension(6, 42));
        jScrollPane3.setViewportView(txtTaiSanTheChap);

        cboLoaiHinhVay.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboLoaiHinhVay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiHinhVay.setMaximumSize(new java.awt.Dimension(6, 35));
        cboLoaiHinhVay.setMinimumSize(new java.awt.Dimension(6, 35));
        cboLoaiHinhVay.setPreferredSize(new java.awt.Dimension(6, 35));
        cboLoaiHinhVay.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboLoaiHinhVayPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Lãi suất (%):");

        txtLaiSuat.setEditable(false);
        txtLaiSuat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtLaiSuat.setText("15");
        txtLaiSuat.setMaximumSize(new java.awt.Dimension(6, 35));
        txtLaiSuat.setMinimumSize(new java.awt.Dimension(6, 35));
        txtLaiSuat.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Thời hạn:");

        cboThoiHan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboThoiHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 tháng", "6 tháng", "12 tháng" }));
        cboThoiHan.setMaximumSize(new java.awt.Dimension(6, 35));
        cboThoiHan.setMinimumSize(new java.awt.Dimension(6, 35));
        cboThoiHan.setPreferredSize(new java.awt.Dimension(6, 35));
        cboThoiHan.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboThoiHanPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("CMND:");

        txtCmnd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCmnd.setMaximumSize(new java.awt.Dimension(6, 35));
        txtCmnd.setMinimumSize(new java.awt.Dimension(6, 35));
        txtCmnd.setPreferredSize(new java.awt.Dimension(6, 35));

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addContainerGap(330, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMucDich, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayHetHan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(76, 76, 76))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCapNhatLayout.createSequentialGroup()
                                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtLaiSuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboLoaiHinhVay, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaHoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboThoiHan, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(476, Short.MAX_VALUE))
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaHoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboLoaiHinhVay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboThoiHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMucDich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        tabs.addTab("CẬP NHẬT", pnlCapNhat);

        pnlDanhSach.setBackground(new java.awt.Color(255, 248, 244));

        pnlTimKiem.setBackground(new java.awt.Color(255, 248, 244));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 18), new java.awt.Color(32, 136, 203))); // NOI18N
        pnlTimKiem.setForeground(new java.awt.Color(32, 136, 203));

        txtTimKiem2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimKiem2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiem2CaretUpdate(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(32, 136, 203));
        jLabel13.setText("Sắp xếp theo:");

        cboSort.setBackground(new java.awt.Color(32, 136, 203));
        cboSort.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboSort.setForeground(new java.awt.Color(32, 136, 203));
        cboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hồ sơ", "Số tiền vay giảm dần", "Số tiền vay tăng dần", "Ngày vay giảm dần", "Ngày vay tăng dần", "Ngày hết hạn giảm dần", "Ngày hết hạn tăng dần" }));
        cboSort.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboSortPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        chkIsPay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chkIsPay.setForeground(new java.awt.Color(32, 136, 203));
        chkIsPay.setText("Chưa thanh toán");
        chkIsPay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkIsPayItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem2, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(chkIsPay)
                .addGap(31, 31, 31)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkIsPay))
                .addContainerGap())
        );

        tblDanhSach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hồ sơ", "Mã người vay", "Họ tên người vay", "CMND", "Loại hình vay", "Số tiền", "Mục đích", "Kỳ hạn", "Ngày vay", "Nhân viên thực hiện", "Ngày hết hạn", "Đã thanh toán", "Ngày trả", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSach.setFocusable(false);
        tblDanhSach.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDanhSach.setRowHeight(25);
        tblDanhSach.setSelectionBackground(new java.awt.Color(252, 97, 85));
        tblDanhSach.setShowVerticalLines(false);
        tblDanhSach.getTableHeader().setReorderingAllowed(false);
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(pnlTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH", pnlDanhSach);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabs))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1462, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        fillComboBox();
        load(0);
        setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (isvalid()) {
            if (khdao.findByCMND(txtCmnd.getText()) == null) {
                if (DialogHelper.confirm(this, "Khách hàng này chưa có thông tin. Hãy nhập thông tin!!")) {
                    new KhachHangJFrame().setVisible(true);
                }
            } else {
                this.insert();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        this.clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (this.isvalid()) {
            update();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void cboLoaiHinhVayPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboLoaiHinhVayPopupMenuWillBecomeInvisible
        selectComboBoxLH();
    }//GEN-LAST:event_cboLoaiHinhVayPopupMenuWillBecomeInvisible

    private void cboThoiHanPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboThoiHanPopupMenuWillBecomeInvisible
        selectComboBoxTH();
    }//GEN-LAST:event_cboThoiHanPopupMenuWillBecomeInvisible

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblDanhSach.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void txtTimKiem2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiem2CaretUpdate
        int iSort;
        if (chkIsPay.isSelected()) {
            iSort = cboSort.getSelectedIndex()+7;
        } else {
            iSort = cboSort.getSelectedIndex();
        }
        load(iSort);
    }//GEN-LAST:event_txtTimKiem2CaretUpdate

    private void cboSortPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboSortPopupMenuWillBecomeInvisible
        int iSort;
        if (chkIsPay.isSelected()) {
            iSort = cboSort.getSelectedIndex()+7;
        } else {
            iSort = cboSort.getSelectedIndex();
        }
        load(iSort);
    }//GEN-LAST:event_cboSortPopupMenuWillBecomeInvisible

    private void chkIsPayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkIsPayItemStateChanged
        int iSort;
        if (chkIsPay.isSelected()) {
            iSort = cboSort.getSelectedIndex()+7;
        } else {
            iSort = cboSort.getSelectedIndex();
        }
        load(iSort);
    }//GEN-LAST:event_chkIsPayItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoSoVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoSoVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoSoVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoSoVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoSoVayJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboLoaiHinhVay;
    private javax.swing.JComboBox<String> cboSort;
    private javax.swing.JComboBox<String> cboThoiHan;
    private javax.swing.JCheckBox chkIsPay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtCmnd;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLaiSuat;
    private javax.swing.JTextField txtMaHoSo;
    private javax.swing.JTextField txtMucDich;
    private javax.swing.JTextField txtNgayHetHan;
    private javax.swing.JTextField txtSoTien;
    private javax.swing.JTextArea txtTaiSanTheChap;
    private javax.swing.JTextField txtTimKiem2;
    // End of variables declaration//GEN-END:variables
}
