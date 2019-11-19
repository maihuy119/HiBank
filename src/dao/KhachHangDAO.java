/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO {
    public void insert(KhachHang model) {
        String sql = "INSERT INTO tbl_KhachHang (ma_khach_hang, ho_ten, gioi_tinh, ngay_sinh, cmnd, que_quan, dia_chi_thuong_tru, so_dien_thoai, nguoi_tao, ngay_tao, ghi_chu, anh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaKhachHang(),
                model.getHoTen(),
                model.isGioiTinh(),
                model.getNgaySinh(),
                model.getCmnd(),
                model.getQueQuan(),
                model.getDiaChiThuongTru(),
                model.getSoDienThoai(),
                model.getNguoiTao(),
                model.getNgayTao(),
                model.getGhiChu(),
                model.getAnh());
    }

    public void update(KhachHang model) {
        String sql = "UPDATE KhachHang SET ho_ten=?, gioi_tinh=?, ngay_sinh=?, cmnd=?, que_quan=?, dia_chi_thuong_tru=?, so_dien_thoai=?, nguoi_tao=?, ngay_tao=?, ghi_chu=?, anh=? WHERE ma_khach_hang=?";
        JdbcHelper.executeUpdate(sql,                
                model.getHoTen(),
                model.isGioiTinh(),
                model.getNgaySinh(),
                model.getCmnd(),
                model.getQueQuan(),
                model.getDiaChiThuongTru(),
                model.getSoDienThoai(),
                model.getNguoiTao(),
                model.getNgayTao(),
                model.getGhiChu(),
                model.getAnh(),
                model.getMaKhachHang());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM KhachHang WHERE ma_khach_hang=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }
    
    public List<KhachHang> select() {
        String sql = "SELECT * FROM tbl_KhachHang";
        return select(sql);
    }

    public KhachHang findById(String makh) {
        String sql = "SELECT * FROM tbl_KhachHang WHERE MaNV=?";
        List<KhachHang> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<KhachHang> select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    KhachHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setMaKhachHang(rs.getString("ma_khach_hang"));
        model.setHoTen(rs.getString("ho_ten"));
        model.setGioiTinh(rs.getBoolean("gioi_tinh"));
        model.setNgaySinh(rs.getDate("ngay_sinh"));
        model.setCmnd(rs.getString("cmnd"));
        model.setQueQuan(rs.getString("que_quan"));
        model.setDiaChiThuongTru(rs.getString("dia_chi_thuong_tru"));
        model.setSoDienThoai(rs.getString("so_dien_thoai"));
        model.setNguoiTao(rs.getString("nguoi_tao"));
        model.setNgayTao(rs.getDate("ngay_tao"));
        model.setGhiChu(rs.getString("ghi_chu"));
        model.setAnh(rs.getString("anh"));
        return model;
    }

}
