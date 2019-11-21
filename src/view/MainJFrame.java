/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author maihu
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form TruongPhongJFrame
     */
    public MainJFrame() {
        initComponents();
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
        btnKhachHang = new javax.swing.JButton();
        btnHoSo = new javax.swing.JButton();
        btnHuongDan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        mniThongTin = new javax.swing.JMenuItem();
        mniDoiMK = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniNhanVien = new javax.swing.JMenuItem();
        mniKhachHang = new javax.swing.JMenuItem();
        mniLoaiHinh = new javax.swing.JMenuItem();
        mniHoSo = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mniTKKhachHang = new javax.swing.JMenuItem();
        mniTKHoSo = new javax.swing.JMenuItem();
        mniTKDoanhThu = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ NGÂN HÀNG");
        setMinimumSize(new java.awt.Dimension(659, 405));
        setPreferredSize(new java.awt.Dimension(837, 567));

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
        jToolBar2.add(btnDangXuat);

        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Stop.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(btnKetThuc);

        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Clien list.png"))); // NOI18N
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setFocusable(false);
        btnKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhachHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
        jToolBar2.add(btnHoSo);

        btnHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Globe.png"))); // NOI18N
        btnHuongDan.setText("Hướng dẫn");
        btnHuongDan.setFocusable(false);
        btnHuongDan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHuongDan.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnHuongDan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(btnHuongDan);

        getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setPreferredSize(new java.awt.Dimension(113, 36));

        jLabel2.setText("Hệ thống quản lý ngân hàng");

        jLabel3.setText("10:01 AM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 577, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenu3.setText("Hệ thống");

        mniThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Clien list.png"))); // NOI18N
        mniThongTin.setText("Thông tin cá nhân");
        jMenu3.add(mniThongTin);

        mniDoiMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Refresh.png"))); // NOI18N
        mniDoiMK.setText("Đổi mật khẩu");
        jMenu3.add(mniDoiMK);
        jMenu3.add(jSeparator1);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Log out.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        jMenu3.add(mniDangXuat);
        jMenu3.add(jSeparator2);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết thúc");
        jMenu3.add(mniKetThuc);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Quản lý");

        mniNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Conference.png"))); // NOI18N
        mniNhanVien.setText("Nhân viên");
        jMenu4.add(mniNhanVien);

        mniKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Handshake.png"))); // NOI18N
        mniKhachHang.setText("Khách hàng");
        jMenu4.add(mniKhachHang);

        mniLoaiHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Lists.png"))); // NOI18N
        mniLoaiHinh.setText("Loại hình vay");
        jMenu4.add(mniLoaiHinh);

        mniHoSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Documents.png"))); // NOI18N
        mniHoSo.setText("Hồ sơ");
        jMenu4.add(mniHoSo);

        jMenuBar2.add(jMenu4);

        jMenu5.setText("Thống kê");

        mniTKKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Friends.png"))); // NOI18N
        mniTKKhachHang.setText("Thống kê khách hàng");
        jMenu5.add(mniTKKhachHang);

        mniTKHoSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Book.png"))); // NOI18N
        mniTKHoSo.setText("Thống kê hồ sơ");
        jMenu5.add(mniTKHoSo);

        mniTKDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dollar.png"))); // NOI18N
        mniTKDoanhThu.setText("Thống kê doanh thu");
        jMenu5.add(mniTKDoanhThu);

        jMenuBar2.add(jMenu5);

        jMenu6.setText("Trợ giúp");

        mniHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mniHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Globe.png"))); // NOI18N
        mniHuongDan.setText("Hướng dẫn sử dụng");
        jMenu6.add(mniHuongDan);
        jMenu6.add(jSeparator3);

        mniGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Brick house.png"))); // NOI18N
        mniGioiThieu.setText("Giới thiệu sản phẩm");
        jMenu6.add(mniGioiThieu);

        jMenuBar2.add(jMenu6);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToolBar jToolBar2;
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
    private javax.swing.JMenuItem mniThongTin;
    // End of variables declaration//GEN-END:variables
}
