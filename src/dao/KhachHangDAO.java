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
import model.HoSo;
import model.KhachHang;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO {
    public void insert(KhachHang model) {
        String sql = "INSERT INTO tbl_KhachHang (ho_ten, gioi_tinh, ngay_sinh, cmnd, que_quan, dia_chi, so_dien_thoai, nhan_vien_tao, ngay_tao, ghi_chu, anh, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                model.getEmail());
    }

    public void update(KhachHang model) {
        String sql = "UPDATE tbl_KhachHang SET ho_ten=?, gioi_tinh=?, ngay_sinh=?, cmnd=?, que_quan=?, dia_chi=?, so_dien_thoai=?, nhan_vien_tao=?, ghi_chu=?, anh=?, email=? WHERE ma_khach_hang=?";
        JdbcHelper.executeUpdate(sql,                
                model.getHoTen(),
                model.isGioiTinh(),
                model.getNgaySinh(),
                model.getCmnd(),
                model.getQueQuan(),
                model.getDiaChiThuongTru(),
                model.getSoDienThoai(),
                model.getNguoiTao(),
                model.getGhiChu(),
                model.getAnh(),
                model.getEmail(),
                model.getMaKhachHang());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM tbl_KhachHang WHERE ma_khach_hang=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }
    
    public List<KhachHang> select() {
        String sql = "SELECT * FROM tbl_KhachHang";
        return select(sql);
    }

    public KhachHang findById(int makh) {
        String sql = "SELECT * FROM tbl_KhachHang WHERE ma_khach_hang=?";
        List<KhachHang> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public KhachHang findByName(String name) {
        String sql = "SELECT * FROM tbl_Khachhang WHERE ho_ten=?";
        List<KhachHang> list = select(sql, name);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public KhachHang findByCMND(String cmnd) {
        String sql = "SELECT * FROM tbl_Khachhang WHERE cmnd=?";
        List<KhachHang> list = select(sql, cmnd);
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
        model.setMaKhachHang(rs.getInt("ma_khach_hang"));
        model.setHoTen(rs.getString("ho_ten"));
        model.setGioiTinh(rs.getBoolean("gioi_tinh"));
        model.setNgaySinh(rs.getDate("ngay_sinh"));
        model.setCmnd(rs.getString("cmnd"));
        model.setQueQuan(rs.getString("que_quan"));
        model.setDiaChiThuongTru(rs.getString("dia_chi"));
        model.setSoDienThoai(rs.getString("so_dien_thoai"));
        model.setEmail(rs.getString("email"));
        model.setNguoiTao(rs.getString("nhan_vien_tao"));
        model.setNgayTao(rs.getDate("ngay_tao"));
        model.setGhiChu(rs.getString("ghi_chu"));
        model.setAnh(rs.getString("anh"));
        return model;
    }

}
