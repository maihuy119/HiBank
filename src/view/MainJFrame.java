/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import helper.DialogHelper;
import helper.ShareHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author maihu
 */
public final class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form TruongPhongJFrame
     */
    public MainJFrame() {
        initComponents();
        init();
        reload = this;
    }

    public static MainJFrame reload;

    public void ReLoad() {
        this.setVisible(true);
    }

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);

        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent ae) {
                lblDongHo.setText(format.format(new Date()));
            }
        }).start();
        if (ShareHelper.authenticated() == false) {
            this.openWelcome();
            this.openLogin();
        }
        mniNhanVien.setEnabled(false);
        mniLoaiHinh.setEnabled(false);
        mnuThongKe.setEnabled(false);
    }

    void openLogin() {
        new LoginJDialog(this, true).setVisible(true);
    }

    void openWelcome() {
        new ChaoJDialog(this, true).setVisible(true);
    }

    void logoff() {
        ShareHelper.logoff();
        openLogin();
    }

    void exit() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn kết thúc?")) {
            System.exit(0);
        }
    }

    void openAbout() {
        new GioiThieuJDialog(this, true).setVisible(true);
    }

    void openThanhToan() {
        new ThanhToanJFrame().setVisible(true);
    }

    void openProfile() {
        if (ShareHelper.authenticated()) {
            new ProfileJFrame(ShareHelper.USER).setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openNhanVien() {
        if (ShareHelper.authenticated()) {
            new NhanVienJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openKhachHang() {
        if (ShareHelper.authenticated()) {
            new KhachHangJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openLoaiHinhVay() {
        if (ShareHelper.authenticated()) {
            new LoaiHinhVayJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openHoSo() {
        if (ShareHelper.authenticated()) {
            new HoSoVayJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openThongKe(int index) {
        if (ShareHelper.authenticated()) {
            new ThongKeJFrame(index).setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openDoiMK() {
        if (ShareHelper.authenticated()) {
            new ChangePassJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    boolean checkAdmin() {
        if (!ShareHelper.USER.getChucVu().equalsIgnoreCase("Trưởng phòng")) {
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

        jLabel1 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnKhachHang = new javax.swing.JButton();
        btnHoSo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnHuongDan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jMenuBar2 = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mniThongTin = new javax.swing.JMenuItem();
        mniDoiMK = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        mnuQuanLy = new javax.swing.JMenu();
        mniNhanVien = new javax.swing.JMenuItem();
        mniKhachHang = new javax.swing.JMenuItem();
        mniLoaiHinh = new javax.swing.JMenuItem();
        mniHoSo = new javax.swing.JMenuItem();
        mniThanhToan = new javax.swing.JMenuItem();
        mnuThongKe = new javax.swing.JMenu();
        mniTKKhachHang = new javax.swing.JMenuItem();
        mniTKHoSo = new javax.swing.JMenuItem();
        mniTKDoanhThu = new javax.swing.JMenuItem();
        mnuTroGiup = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ NGÂN HÀNG");
        setMinimumSize(new java.awt.Dimension(659, 425));
        setPreferredSize(new java.awt.Dimension(837, 567));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/big_logo.png"))); // NOI18N
        jLabel1.setMinimumSize(new java.awt.Dimension(619, 250));
        jLabel1.setPreferredSize(new java.awt.Dimension(659, 240));
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

        jToolBar2.setRollover(true);
        jToolBar2.setMinimumSize(new java.awt.Dimension(66, 25));
        jToolBar2.setPreferredSize(new java.awt.Dimension(100, 70));

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Log out.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar2.add(btnDangXuat);

        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Stop.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        jToolBar2.add(btnKetThuc);
        jToolBar2.add(jSeparator5);

        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Handshake.png"))); // NOI18N
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setFocusable(false);
        btnKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhachHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        jToolBar2.add(btnKhachHang);

        btnHoSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Price list.png"))); // NOI18N
        btnHoSo.setText("Hồ sơ");
        btnHoSo.setFocusable(false);
        btnHoSo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHoSo.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnHoSo.setMaximumSize(new java.awt.Dimension(73, 51));
        btnHoSo.setMinimumSize(new java.awt.Dimension(73, 51));
        btnHoSo.setPreferredSize(new java.awt.Dimension(73, 53));
        btnHoSo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHoSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoSoActionPerformed(evt);
            }
        });
        jToolBar2.add(btnHoSo);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Money.png"))); // NOI18N
        jButton1.setText("Thanh toán");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);
        jToolBar2.add(jSeparator6);

        btnHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Globe.png"))); // NOI18N
        btnHuongDan.setText("Hướng dẫn");
        btnHuongDan.setFocusable(false);
        btnHuongDan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHuongDan.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnHuongDan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuongDanActionPerformed(evt);
            }
        });
        jToolBar2.add(btnHuongDan);

        getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setPreferredSize(new java.awt.Dimension(113, 36));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info.png"))); // NOI18N
        jLabel2.setText("Hệ thống quản lý ngân hàng");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel2, java.awt.BorderLayout.LINE_START);

        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarm.png"))); // NOI18N
        lblDongHo.setText("10:01 AM");
        jPanel1.add(lblDongHo, java.awt.BorderLayout.LINE_END);
        jPanel1.add(jSeparator7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        mnuHeThong.setText("Hệ thống");
        mnuHeThong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHeThongActionPerformed(evt);
            }
        });

        mniThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Clien list.png"))); // NOI18N
        mniThongTin.setText("Thông tin cá nhân");
        mniThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThongTinActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniThongTin);

        mniDoiMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Refresh.png"))); // NOI18N
        mniDoiMK.setText("Đổi mật khẩu");
        mniDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoiMKActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDoiMK);
        mnuHeThong.add(jSeparator1);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Log out.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.add(jSeparator2);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết thúc");
        mniKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetThucActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniKetThuc);

        jMenuBar2.add(mnuHeThong);

        mnuQuanLy.setText("Quản lý");

        mniNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Conference.png"))); // NOI18N
        mniNhanVien.setText("Nhân viên");
        mniNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNhanVienActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniNhanVien);

        mniKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Handshake.png"))); // NOI18N
        mniKhachHang.setText("Khách hàng");
        mniKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhachHangActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniKhachHang);

        mniLoaiHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Lists.png"))); // NOI18N
        mniLoaiHinh.setText("Loại hình vay");
        mniLoaiHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLoaiHinhActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniLoaiHinh);

        mniHoSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Documents.png"))); // NOI18N
        mniHoSo.setText("Hồ sơ");
        mniHoSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHoSoActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniHoSo);

        mniThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Money.png"))); // NOI18N
        mniThanhToan.setText("Thanh toán");
        mniThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThanhToanActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniThanhToan);

        jMenuBar2.add(mnuQuanLy);

        mnuThongKe.setText("Thống kê");

        mniTKKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Friends.png"))); // NOI18N
        mniTKKhachHang.setText("Thống kê khách hàng");
        mniTKKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKKhachHangActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKKhachHang);

        mniTKHoSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Book.png"))); // NOI18N
        mniTKHoSo.setText("Thống kê hồ sơ");
        mniTKHoSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKHoSoActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKHoSo);

        mniTKDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dollar.png"))); // NOI18N
        mniTKDoanhThu.setText("Thống kê doanh thu");
        mniTKDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKDoanhThuActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKDoanhThu);

        jMenuBar2.add(mnuThongKe);

        mnuTroGiup.setText("Trợ giúp");

        mniHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mniHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Globe.png"))); // NOI18N
        mniHuongDan.setText("Hướng dẫn sử dụng");
        mniHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuongDanActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniHuongDan);
        mnuTroGiup.add(jSeparator3);

        mniGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Brick house.png"))); // NOI18N
        mniGioiThieu.setText("Giới thiệu sản phẩm");
        mniGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGioiThieuActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniGioiThieu);

        jMenuBar2.add(mnuTroGiup);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuHeThongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHeThongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuHeThongActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        mniDangXuatActionPerformed(evt);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        mniKetThucActionPerformed(evt);
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        mniKhachHangActionPerformed(evt);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnHoSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoSoActionPerformed
        mniHoSoActionPerformed(evt);
    }//GEN-LAST:event_btnHoSoActionPerformed

    private void btnHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuongDanActionPerformed
        mniHuongDanActionPerformed(evt);
    }//GEN-LAST:event_btnHuongDanActionPerformed

    private void mniThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThongTinActionPerformed
        openProfile();
    }//GEN-LAST:event_mniThongTinActionPerformed

    private void mniDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoiMKActionPerformed
        openDoiMK();
    }//GEN-LAST:event_mniDoiMKActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        logoff();
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetThucActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniKetThucActionPerformed

    private void mniNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNhanVienActionPerformed
        openNhanVien();
    }//GEN-LAST:event_mniNhanVienActionPerformed

    private void mniKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhachHangActionPerformed
        openKhachHang();
    }//GEN-LAST:event_mniKhachHangActionPerformed

    private void mniLoaiHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLoaiHinhActionPerformed
        openLoaiHinhVay();
    }//GEN-LAST:event_mniLoaiHinhActionPerformed

    private void mniHoSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHoSoActionPerformed
        openHoSo();
    }//GEN-LAST:event_mniHoSoActionPerformed

    private void mniTKKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKKhachHangActionPerformed
        openThongKe(0);
    }//GEN-LAST:event_mniTKKhachHangActionPerformed

    private void mniTKHoSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKHoSoActionPerformed
        openThongKe(1);
    }//GEN-LAST:event_mniTKHoSoActionPerformed

    private void mniTKDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKDoanhThuActionPerformed
        openThongKe(2);
    }//GEN-LAST:event_mniTKDoanhThuActionPerformed

    private void mniHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuongDanActionPerformed
        openAbout();
    }//GEN-LAST:event_mniHuongDanActionPerformed

    private void mniGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGioiThieuActionPerformed
        openAbout();
    }//GEN-LAST:event_mniGioiThieuActionPerformed

    private void mniThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThanhToanActionPerformed
        openThanhToan();
    }//GEN-LAST:event_mniThanhToanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        System.out.println(checkAdmin());
        if (checkAdmin() == true) {
            mniNhanVien.setEnabled(true);
            mniLoaiHinh.setEnabled(true);
            mnuThongKe.setEnabled(true);
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (checkAdmin() == true) {
            mniNhanVien.setEnabled(true);
            mniLoaiHinh.setEnabled(true);
            mnuThongKe.setEnabled(true);
        } else {
            mniNhanVien.setEnabled(false);
            mniLoaiHinh.setEnabled(false);
            mnuThongKe.setEnabled(false);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mniThanhToanActionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnHoSo;
    private javax.swing.JButton btnHuongDan;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDoiMK;
    private javax.swing.JMenuItem mniGioiThieu;
    private javax.swing.JMenuItem mniHoSo;
    private javax.swing.JMenuItem mniHuongDan;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniKhachHang;
    private javax.swing.JMenuItem mniLoaiHinh;
    private javax.swing.JMenuItem mniNhanVien;
    private javax.swing.JMenuItem mniTKDoanhThu;
    private javax.swing.JMenuItem mniTKHoSo;
    private javax.swing.JMenuItem mniTKKhachHang;
    private javax.swing.JMenuItem mniThanhToan;
    private javax.swing.JMenuItem mniThongTin;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenu mnuQuanLy;
    private javax.swing.JMenu mnuThongKe;
    private javax.swing.JMenu mnuTroGiup;
    // End of variables declaration//GEN-END:variables
}
