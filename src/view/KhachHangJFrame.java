/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.KhachHangDAO;
import helper.DialogHelper;
import helper.ShareHelper;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;

/**
 *
 * @author maihu
 */
public class KhachHangJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienJFrame
     */
    public KhachHangJFrame() {
        initComponents();
        init();
    }

    int index = 0;
    KhachHangDAO dao = new KhachHangDAO();

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);

        tblDanhSach.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblDanhSach.getTableHeader().setOpaque(false);
        tblDanhSach.getTableHeader().setBackground(new Color(32, 136, 203));
        tblDanhSach.getTableHeader().setForeground(new Color(32, 136, 203));
        tblDanhSach.setRowHeight(25);
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem3.getText();
            if (keyword.matches("\\d{1,9}")) {
                List<KhachHang> list = dao.selectByCMND(keyword);
                for (KhachHang kh : list) {
                    Object[] row = {
                        kh.getMaKhachHang(),
                        kh.getHoTen(),
                        kh.isGioiTinh() ? "Nam" : "Nữ",
                        kh.getNgaySinh(),
                        kh.getCmnd(),
                        kh.getQueQuan(),
                        kh.getDiaChiThuongTru(),
                        kh.getSoDienThoai(),
                        kh.getNguoiTao(),
                        kh.getNgayTao(),
                        kh.getGhiChu()
                    };
                    model.addRow(row);
                }
            } else {
                List<KhachHang> list = dao.selectByName(keyword);
                for (KhachHang kh : list) {
                    Object[] row = {
                        kh.getMaKhachHang(),
                        kh.getHoTen(),
                        kh.isGioiTinh() ? "Nam" : "Nữ",
                        kh.getNgaySinh(),
                        kh.getCmnd(),
                        kh.getQueQuan(),
                        kh.getDiaChiThuongTru(),
                        kh.getSoDienThoai(),
                        kh.getNguoiTao(),
                        kh.getNgayTao(),
                        kh.getGhiChu()
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    private void clear() {
        txtMaKH.setText("");
        txtHoTen.setText("");
        cboGioiTinh.setSelectedIndex(0);
        dcBirthday.setDate(new Date());
        txtCMND.setText("");
        txtQueQuan.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtGhiChu.setText("");
        lblAnh.setIcon(null);
        setStatus(true);
    }

    void setModel(KhachHang model) {
        txtMaKH.setText(String.valueOf(model.getMaKhachHang()));
        txtHoTen.setText(model.getHoTen());
        cboGioiTinh.setSelectedIndex(model.isGioiTinh() ? 0 : 1);
        dcBirthday.setDate(model.getNgaySinh());
        txtCMND.setText(model.getCmnd().trim());
        txtQueQuan.setText(model.getQueQuan());
        txtDiaChi.setText(model.getDiaChiThuongTru());
        txtSDT.setText(model.getSoDienThoai().trim());
        txtEmail.setText(model.getEmail());
        txtGhiChu.setText(model.getGhiChu());
        if (model.getAnh() != null) {
            lblAnh.setIcon(ShareHelper.readLogo(model.getAnh()));
            lblAnh.setText("");
        }
    }

    KhachHang getModel() {
        KhachHang model = new KhachHang();
        model.setHoTen(txtHoTen.getText());
        model.setGioiTinh(cboGioiTinh.getSelectedIndex() == 0);
        model.setNgaySinh(dcBirthday.getDate());
        model.setCmnd(txtCMND.getText());
        model.setQueQuan(txtQueQuan.getText());
        model.setDiaChiThuongTru(txtDiaChi.getText());
        model.setSoDienThoai(txtSDT.getText());
        model.setEmail(txtEmail.getText());
        model.setGhiChu(txtGhiChu.getText());

        model.setAnh(lblAnh.getToolTipText());

        return model;
    }

    void selectImage() {
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (ShareHelper.saveLogo(file)) {
                // Hiển thị hình lên form
                lblAnh.setIcon(ShareHelper.readLogo(file.getName()));
                lblAnh.setToolTipText(file.getName());
            }
        }
    }

    void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnXoa.setEnabled(!insertable);
        btnCapNhat.setEnabled(!insertable);
    }

    void insert() {
        KhachHang model = getModel();
        model.setNgayTao(new Date());
        KhachHang kt = dao.findByCMND(txtCMND.getText());
        if (kt != null) {
            DialogHelper.alert(this, "Khách hàng này đã tồn tại! Vui lòng kiểm tra lại!");
            txtCMND.requestFocus();
            return;
        }
        try {
            dao.insert(model);
            this.clear();
            this.load();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Thêm mới thất bại!");
            System.out.println(e.toString());
        }
    }

    void edit() {
        try {
            int makh = (int) tblDanhSach.getValueAt(this.index, 0);
            KhachHang model = dao.findById(makh);
            model.setCmnd(dao.findById(makh).getCmnd());
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    void update() {
        KhachHang model = getModel();
        model.setMaKhachHang(Integer.parseInt(txtMaKH.getText()));
        try {
            dao.update(model);
            this.load();
            clear();
            setStatus(true);
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
            e.printStackTrace();
        }

    }

    void delete() {

        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa khách hàng này?")) {
            String makh = txtMaKH.getText();
            try {
                dao.delete(makh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
                e.printStackTrace();
            }
        }
    }

    public boolean isvalid() {
        StringBuffer sb = new StringBuffer();
        if (txtHoTen.getText().length() == 0) {
            sb.append("Vui lòng nhập họ tên khách hàng!\n");
        } else if (!txtHoTen.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$")) {
            sb.append("Vui lòng nhập đúng định dạng họ tên!\n");
        }
        if (txtCMND.getText().length() == 0) {
            sb.append("Vui lòng nhập CMND!\n");
        } else if (!txtCMND.getText().matches("\\d{9}")) {
            sb.append("Vui lòng nhập đúng định dạng CMND!\n");
        }
        if (txtQueQuan.getText().length() == 0) {
            sb.append("Vui lòng nhập quê quán!\n");
        }
        if (txtDiaChi.getText().length() == 0) {
            sb.append("Vui lòng nhập địa chỉ thường trú!\n");
        }
        if (txtSDT.getText().equals("")) {
            sb.append("Vui lòng nhập số điện thoại!\n");
        } else if (!txtSDT.getText().matches("0\\d{9}")) {
            sb.append("Vui lòng nhập đúng định dạng số điện thoại!\n");
        }
        if (txtEmail.getText().equals("")) {
            sb.append("Vui lòng nhập email!\n");
        } else if (!txtEmail.getText().matches("^(.+)@(.+)$")) {
            sb.append("Vui lòng nhập đúng định dạng email!\n");
        }
        if (lblAnh.getIcon() == null) {
            sb.append("Vui lòng nhập ảnh khách hàng!\n");
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

        pmnPicture = new javax.swing.JPopupMenu();
        mniLoad = new javax.swing.JMenuItem();
        mniRemove = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        pnlCapNhat = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtQueQuan = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        dcBirthday = new com.toedter.calendar.JDateChooser();
        pnlDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        pnlTimKiem1 = new javax.swing.JPanel();
        txtTimKiem3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        mniLoad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mniLoad.setText("Load ảnh");
        mniLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLoadActionPerformed(evt);
            }
        });
        pmnPicture.add(mniLoad);

        mniRemove.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mniRemove.setText("Xóa ảnh");
        mniRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRemoveActionPerformed(evt);
            }
        });
        pmnPicture.add(mniRemove);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ KHÁCH HÀNG");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 248, 244));

        tabs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        pnlCapNhat.setBackground(new java.awt.Color(255, 248, 244));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Họ tên:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Giới tính:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Ngày sinh:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("CMND:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Quê quán:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Địa chỉ thường trú:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Số điện thoại");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Ghi chú");

        txtHoTen.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoTen.setMaximumSize(new java.awt.Dimension(6, 42));
        txtHoTen.setMinimumSize(new java.awt.Dimension(6, 42));
        txtHoTen.setPreferredSize(new java.awt.Dimension(6, 42));

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSDT.setMaximumSize(new java.awt.Dimension(6, 42));
        txtSDT.setMinimumSize(new java.awt.Dimension(6, 42));
        txtSDT.setPreferredSize(new java.awt.Dimension(6, 42));

        txtQueQuan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtQueQuan.setMaximumSize(new java.awt.Dimension(6, 42));
        txtQueQuan.setMinimumSize(new java.awt.Dimension(6, 42));
        txtQueQuan.setPreferredSize(new java.awt.Dimension(6, 42));

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDiaChi.setMaximumSize(new java.awt.Dimension(6, 42));
        txtDiaChi.setMinimumSize(new java.awt.Dimension(6, 42));
        txtDiaChi.setPreferredSize(new java.awt.Dimension(6, 42));

        txtCMND.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCMND.setMaximumSize(new java.awt.Dimension(6, 42));
        txtCMND.setMinimumSize(new java.awt.Dimension(6, 42));
        txtCMND.setPreferredSize(new java.awt.Dimension(6, 42));

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtEmail.setMaximumSize(new java.awt.Dimension(6, 42));
        txtEmail.setMinimumSize(new java.awt.Dimension(6, 42));
        txtEmail.setPreferredSize(new java.awt.Dimension(6, 42));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Email:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        jPanel3.setBackground(new java.awt.Color(255, 248, 244));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAnh.setBackground(new java.awt.Color(255, 248, 244));
        lblAnh.setComponentPopupMenu(pmnPicture);
        lblAnh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );

        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add.png"))); // NOI18N
        btnThem.setText("   Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(130, 43));
        btnThem.setMinimumSize(new java.awt.Dimension(130, 43));
        btnThem.setPreferredSize(new java.awt.Dimension(130, 43));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnMoi.setText("  Mới");
        btnMoi.setMaximumSize(new java.awt.Dimension(130, 43));
        btnMoi.setMinimumSize(new java.awt.Dimension(130, 43));
        btnMoi.setPreferredSize(new java.awt.Dimension(130, 43));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save.png"))); // NOI18N
        btnCapNhat.setText(" Cập nhật");
        btnCapNhat.setMaximumSize(new java.awt.Dimension(130, 43));
        btnCapNhat.setMinimumSize(new java.awt.Dimension(130, 43));
        btnCapNhat.setPreferredSize(new java.awt.Dimension(130, 43));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        btnXoa.setText("  Xóa");
        btnXoa.setMaximumSize(new java.awt.Dimension(130, 43));
        btnXoa.setMinimumSize(new java.awt.Dimension(130, 43));
        btnXoa.setPreferredSize(new java.awt.Dimension(130, 43));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        cboGioiTinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setMaximumSize(new java.awt.Dimension(6, 42));
        cboGioiTinh.setMinimumSize(new java.awt.Dimension(6, 42));
        cboGioiTinh.setPreferredSize(new java.awt.Dimension(6, 42));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Mã khách hàng:");

        txtMaKH.setEditable(false);
        txtMaKH.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMaKH.setMaximumSize(new java.awt.Dimension(6, 42));
        txtMaKH.setMinimumSize(new java.awt.Dimension(6, 42));
        txtMaKH.setPreferredSize(new java.awt.Dimension(6, 42));

        dcBirthday.setDate(new java.util.Date(1576552154000L));
        dcBirthday.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        dcBirthday.setMaximumSize(new java.awt.Dimension(6, 42));
        dcBirthday.setMinimumSize(new java.awt.Dimension(6, 42));
        dcBirthday.setPreferredSize(new java.awt.Dimension(6, 42));

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGap(63, 63, 63)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCMND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQueQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(84, 84, 84)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCapNhatLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(23, 23, 23)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tabs.addTab("CẬP NHẬT", pnlCapNhat);

        tblDanhSach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Họ tên", "Giới tính", "Ngày sinh", "CMND", "Quê quán", "Địa chỉ", "Số điện thoại", "Người tạo", "Ngày tạo", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSach.setFocusable(false);
        tblDanhSach.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDanhSach.setRowHeight(25);
        tblDanhSach.setSelectionBackground(new java.awt.Color(232, 97, 85));
        tblDanhSach.setShowVerticalLines(false);
        tblDanhSach.getTableHeader().setReorderingAllowed(false);
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        pnlTimKiem1.setBackground(new java.awt.Color(255, 248, 244));
        pnlTimKiem1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 18), new java.awt.Color(32, 136, 203))); // NOI18N

        txtTimKiem3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimKiem3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiem3CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiem1Layout = new javax.swing.GroupLayout(pnlTimKiem1);
        pnlTimKiem1.setLayout(pnlTimKiem1Layout);
        pnlTimKiem1Layout.setHorizontalGroup(
            pnlTimKiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiem1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem3)
                .addContainerGap())
        );
        pnlTimKiem1Layout.setVerticalGroup(
            pnlTimKiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiem1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTimKiem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", pnlDanhSach);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(32, 136, 203));
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1176, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(0, 944, Short.MAX_VALUE))
                        .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 552, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (isvalid()) {
            this.insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        this.clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (isvalid()) {
            this.update();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblDanhSach.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void mniRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRemoveActionPerformed
        lblAnh.setIcon(null);
    }//GEN-LAST:event_mniRemoveActionPerformed

    JFileChooser chooser = new JFileChooser();
    private void mniLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLoadActionPerformed

        selectImage();
    }//GEN-LAST:event_mniLoadActionPerformed

    private void txtTimKiem3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiem3CaretUpdate
        load();
    }//GEN-LAST:event_txtTimKiem3CaretUpdate

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
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private com.toedter.calendar.JDateChooser dcBirthday;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JMenuItem mniLoad;
    private javax.swing.JMenuItem mniRemove;
    private javax.swing.JPopupMenu pmnPicture;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlTimKiem1;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem3;
    // End of variables declaration//GEN-END:variables
}
