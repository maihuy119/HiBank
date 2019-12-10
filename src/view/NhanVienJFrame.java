/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.NhanVienDAO;
import helper.DateHelper;
import helper.DialogHelper;
import helper.ShareHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;

/**
 *
 * @author maihu
 */
public class NhanVienJFrame extends javax.swing.JFrame {

    int index = 0;
    NhanVienDAO dao = new NhanVienDAO();
    File selectedPicture = null;
    /**
     * Creates new form NhanVienJFrame
     */
    public NhanVienJFrame() {
        initComponents();
        init();
    }
    
    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.select();
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.isGioiTinh() ? "Nam" : "Nữ",
                    nv.getNgaySinh(),
                    nv.getCmnd(),
                    nv.getQueQuan(),
                    nv.getDiaChiThuongTru(),
                    nv.getSoDienThoai(),
                    nv.getChucVu(),
                    nv.getLuong(),
                    nv.getNgayCongTac(),
                    nv.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    private void clear() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        cboGioiTinh.setSelectedIndex(0);
        dcBirthday.setDate(new Date());
        txtCMND.setText("");
        txtQueQuan.setText("");
        txtDiaChi.setText("");
        txtSoDT.setText("");
        txtChucVu.setText("");
        txtLuong.setText("");
        lblAnh.setIcon(null);
        setStatus(true);
    }

    void setModel(NhanVien model) {
        txtMaNV.setText(String.valueOf(model.getMaNV()));
        txtHoTen.setText(model.getHoTen());
        cboGioiTinh.setSelectedIndex(model.isGioiTinh() ? 0 : 1);
        dcBirthday.setDate(model.getNgaySinh());
        txtCMND.setText(model.getCmnd().trim());
        txtQueQuan.setText(model.getQueQuan());
        txtDiaChi.setText(model.getDiaChiThuongTru());
        txtSoDT.setText(model.getSoDienThoai().trim());
        txtChucVu.setText(model.getChucVu());
        txtLuong.setText(String.valueOf(model.getLuong()));
        if (model.getAnh() != null) {
            lblAnh.setIcon(ShareHelper.readLogo(model.getAnh()));
            lblAnh.setText("");
        }
    }

    
    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setHoTen(txtHoTen.getText());
        model.setGioiTinh(cboGioiTinh.getSelectedIndex() == 0);
        model.setNgaySinh(dcBirthday.getDate());
        model.setCmnd(txtCMND.getText());
        model.setQueQuan(txtQueQuan.getText());
        model.setDiaChiThuongTru(txtDiaChi.getText());
        model.setSoDienThoai(txtSoDT.getText());
        model.setChucVu(txtChucVu.getText());
        model.setLuong(Float.valueOf(txtLuong.getText()));
        model.setGhiChu(txaGhiChu.getText());
        model.setUsername(getID());
        model.setAnh(lblAnh.getToolTipText());
        
        return model;
    }
    
    String getID(){
        String sName = txtHoTen.getText();
        Date day = dcBirthday.getDate();
        int ngay = day.getDate();
        int thang = day.getMonth()+1;
        String strThang = "";
        if (thang<10) {
            strThang="0"+String.valueOf(thang);
        } else {
            strThang=String.valueOf(thang);
        }
        String strNgay = "";
        if (ngay<10) {
            strNgay="0"+String.valueOf(ngay);
        } else {
            strNgay=String.valueOf(ngay);
        }
        String ten = sName.substring(sName.lastIndexOf(" ")+1);
        String id = ten + Character.toString(sName.charAt(0));
        for (int i = 0; i < sName.length(); i++) {
            char ch = sName.charAt(i);
            if (Character.toString(ch).equalsIgnoreCase(" ")) {
                id = id.concat(Character.toString(sName.charAt(i+1)));
            }
        }
        id = id.substring(0, id.length()-1).toLowerCase()+strNgay+strThang;
        return id;
    }

    void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnXoa.setEnabled(!insertable);
        btnCapNhat.setEnabled(!insertable);
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
    
    void insert() {
        NhanVien model = getModel();
        model.setNgayCongTac(new Date());
        model.setPass("1");
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
            int manv = (int) tblDanhSach.getValueAt(this.index, 0);
            NhanVien model = dao.findById(manv);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void update() {
        NhanVien model = getModel();
        model.setMaNV(Integer.parseInt(txtMaNV.getText()));
        NhanVien kt = dao.findById(Integer.parseInt(txtMaNV.getText()));
            try {
                dao.update(model);
                this.load();
                this.clear();
                this.setStatus(true);
                DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        
    }

    void delete() {
       
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
            String manv = txtMaNV.getText();
            try {
                dao.delete(manv);
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
            sb.append("Vui lòng nhập họ tên nhân viên!\n");
        } else if (!txtHoTen.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻếẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$")) {
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
        if (txtSoDT.getText().equals("")) {
            sb.append("Vui lòng nhập số điện thoại!\n");
        } else if (!txtSoDT.getText().matches("0\\d{9}")) {
            sb.append("Vui lòng nhập đúng định dạng số điện thoại!\n");
        }
        if (txtChucVu.getText().length() == 0) {
            sb.append("Vui lòng nhập chức vụ!\n");
        } else if (!txtChucVu.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻếẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$")) {
            sb.append("Vui lòng nhập đúng định dạng chức vụ!\n");
        }
        if (txtLuong.getText().length() == 0) {
            sb.append("Vui lòng nhập lương!\n");
        } else if (Float.valueOf(txtLuong.getText())<=0) {
            sb.append("Số tiền lương phải số dương!");
        }
        if (lblAnh.getIcon()==null) {
            sb.append("Vui lòng nhập ảnh nhân viên!\n");
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
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        txtQueQuan = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaGhiChu = new javax.swing.JTextArea();
        pnlAnh = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cboGioiTinh = new javax.swing.JComboBox<>();
        dcBirthday = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();

        mniLoad.setText("Load ảnh");
        mniLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLoadActionPerformed(evt);
            }
        });
        pmnPicture.add(mniLoad);

        mniRemove.setText("Xóa ảnh");
        mniRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRemoveActionPerformed(evt);
            }
        });
        pmnPicture.add(mniRemove);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ NHÂN VIÊN");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setText("Họ tên:");

        jLabel3.setText("Mã nhân viên:");

        jLabel4.setText("Giới tính:");

        jLabel5.setText("Ngày sinh:");

        jLabel6.setText("CMND:");

        jLabel7.setText("Quê quán:");

        jLabel8.setText("Địa chỉ thường trú:");

        jLabel9.setText("Số điện thoại");

        jLabel10.setText("Chức vụ");

        jLabel11.setText("Lương");

        jLabel12.setText("Ghi chú");

        txtMaNV.setEditable(false);

        txaGhiChu.setColumns(20);
        txaGhiChu.setRows(5);
        jScrollPane2.setViewportView(txaGhiChu);

        pnlAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlAnh.setComponentPopupMenu(pmnPicture);

        javax.swing.GroupLayout pnlAnhLayout = new javax.swing.GroupLayout(pnlAnh);
        pnlAnh.setLayout(pnlAnhLayout);
        pnlAnhLayout.setHorizontalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        pnlAnhLayout.setVerticalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(83, 83, 83)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6))
                            .addGap(45, 45, 45)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaNV)
                                .addComponent(cboGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dcBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtQueQuan)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtChucVu)
                                    .addComponent(txtLuong)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(pnlAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnMoi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCapNhat)
                            .addComponent(btnXoa)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dcBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        tabs.addTab("CẬP NHẬT", jPanel1);

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "CMND", "Quê quán", "Địa chỉ", "Số điện thoại", "Chức vụ", "Lương", "Ngày công tác", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabs))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (isvalid()) {
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

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

    JFileChooser chooser = new JFileChooser();
    private void mniLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLoadActionPerformed
        selectImage();
    }//GEN-LAST:event_mniLoadActionPerformed

    private void mniRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRemoveActionPerformed
        lblAnh.setIcon(null);
    }//GEN-LAST:event_mniRemoveActionPerformed

    
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
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienJFrame().setVisible(true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JMenuItem mniLoad;
    private javax.swing.JMenuItem mniRemove;
    private javax.swing.JPopupMenu pmnPicture;
    private javax.swing.JPanel pnlAnh;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextArea txaGhiChu;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSoDT;
    // End of variables declaration//GEN-END:variables
}
