/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.KhachHangDAO;
import dao.LoaiHinhVayDAO;
import helper.DateHelper;
import helper.DialogHelper;
import helper.ShareHelper;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.LoaiHinhVay;

/**
 *
 * @author maihu
 */
public class LoaiHinhVayJFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoaiHinhVayJFrame
     */
    public LoaiHinhVayJFrame() {
        initComponents();
    }

    LoaiHinhVayDAO dao = new LoaiHinhVayDAO();

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            List<LoaiHinhVay> list = dao.select();
            for (LoaiHinhVay lh : list) {
                Object[] row = {
                    lh.getMaLoai(),
                    lh.getTenLoai(),
                    lh.getLaiSuat()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    private void clear() {
        txtMaLoai.setText("");
        txtTenLoai.setText("");
        txtLaiSuat.setText("");
    }

    void setModel(LoaiHinhVay model) {
        txtMaLoai.setText(model.getMaLoai());
        txtTenLoai.setText(model.getTenLoai());
        txtLaiSuat.setText(String.valueOf(model.getLaiSuat()));
    }

    LoaiHinhVay getModel() {
        LoaiHinhVay model = new LoaiHinhVay();
        model.setMaLoai(txtMaLoai.getText());
        model.setTenLoai(txtTenLoai.getText());
        model.setLaiSuat(Double.valueOf(txtLaiSuat.getText()));
        return model;
    }

    void setStatus(boolean insertable) {

    }

//    void insert() {
//        KhachHang model = getModel();
//        model.setNgayTao(new Date());
//        try {
//            dao.insert(model);
//            this.clear();
//            this.load();
//            DialogHelper.alert(this, "Thêm mới thành công!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            DialogHelper.alert(this, "Thêm mới thất bại!");
//            System.out.println(e.toString());
//        }
//    }
//
//    void edit() {
//        try {
//            int makh = (int) tblDanhSach.getValueAt(this.index, 0);
//            KhachHang model = dao.findById(makh);
//            if (model != null) {
//                this.setModel(model);
//                this.setStatus(false);
//            }
//        } catch (Exception e) {
//            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
//            e.printStackTrace();
//        }
//    }
//
//    void update() {
//        KhachHang model = getModel();
//        model.setMaKhachHang(Integer.parseInt(txtMaKH.getText()));
//        System.out.println(model.getMaKhachHang() + "    " +model.getNgaySinh());
//        try {
//            dao.update(model);
//            this.load();
//            DialogHelper.alert(this, "Cập nhật thành công!");
//        } catch (Exception e) {
//            DialogHelper.alert(this, "Cập nhật thất bại!");
//            e.printStackTrace();void insert() {
//        KhachHang model = getModel();
//        model.setNgayTao(new Date());
//        try {
//            dao.insert(model);
//            this.clear();
//            this.load();
//            DialogHelper.alert(this, "Thêm mới thành công!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            DialogHelper.alert(this, "Thêm mới thất bại!");
//            System.out.println(e.toString());
//        }
//    }
//
//    void edit() {
//        try {
//            int makh = (int) tblDanhSach.getValueAt(this.index, 0);
//            KhachHang model = dao.findById(makh);
//            if (model != null) {
//                this.setModel(model);
//                this.setStatus(false);
//            }
//        } catch (Exception e) {
//            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
//            e.printStackTrace();
//        }
//    }
//
//    void update() {
//        KhachHang model = getModel();
//        model.setMaKhachHang(Integer.parseInt(txtMaKH.getText()));
//        System.out.println(model.getMaKhachHang() + "    " +model.getNgaySinh());
//        try {
//            dao.update(model);
//            this.load();
//            DialogHelper.alert(this, "Cập nhật thành công!");
//        } catch (Exception e) {
//            DialogHelper.alert(this, "Cập nhật thất bại!");
//            e.printStackTrace();
//        }
//
//    }
//
//    void delete() {
//
//        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa khách hàng này?")) {
//            String makh = txtMaKH.getText();
//            try {
//                dao.delete(makh);
//                this.load();
//                this.clear();
//                DialogHelper.alert(this, "Xóa thành công!");
//            } catch (Exception e) {
//                DialogHelper.alert(this, "Xóa thất bại!");
//                e.printStackTrace();
//            }
//        }
//    }
//        }
//
//    }
//
//    void delete() {
//
//        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa khách hàng này?")) {
//            String makh = txtMaKH.getText();
//            try {
//                dao.delete(makh);
//                this.load();
//                this.clear();
//                DialogHelper.alert(this, "Xóa thành công!");
//            } catch (Exception e) {
//                DialogHelper.alert(this, "Xóa thất bại!");
//                e.printStackTrace();
//            }
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlCapNhat = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        txtMaLoai = new javax.swing.JTextField();
        txtLaiSuat = new javax.swing.JTextField();
        btnMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        pnlDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ LOẠI HÌNH VAY");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("QUẢN LÝ LOẠI HÌNH VAY");

        jLabel2.setText("Tên loại hình:");

        jLabel3.setText("Mã loại hình:");

        jLabel4.setText("Lãi suất:");

        btnMoi.setText("Mới");

        btnThem.setText("Thêm");

        btnXoa.setText("Xóa");

        btnSua.setText("Sửa");

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenLoai)
                            .addComponent(txtMaLoai)
                            .addComponent(txtLaiSuat)))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoi)
                    .addComponent(btnThem)
                    .addComponent(btnXoa)
                    .addComponent(btnSua))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CẬP NHẬT", pnlCapNhat);

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã loại", "Tên loại", "Lãi suất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DANH SÁCH", pnlDanhSach);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

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
            java.util.logging.Logger.getLogger(LoaiHinhVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoaiHinhVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoaiHinhVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoaiHinhVayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoaiHinhVayJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtLaiSuat;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtTenLoai;
    // End of variables declaration//GEN-END:variables
}
