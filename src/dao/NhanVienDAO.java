/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.DateHelper;
import helper.JdbcHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author maihu
 */
public class NhanVienDAO {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO tbl_NhanVien (ho_ten, gioi_tinh, ngay_sinh, cmnd, que_quan, dia_chi_thuong_tru, so_dien_thoai, chuc_vu, luong, ngay_cong_tac, ghi_chu, anh, username, password)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getHoTen(),
                model.isGioiTinh(),
                model.getNgaySinh(),
                model.getCmnd(),
                model.getQueQuan(),
                model.getDiaChiThuongTru(),
                model.getSoDienThoai(),
                model.getChucVu(),
                model.getLuong(),
                model.getNgayCongTac(),
                model.getGhiChu(),
                model.getAnh(),
                model.getUsername(),
                model.getPass());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE tbl_NhanVien SET ho_ten=?, gioi_tinh=?, ngay_sinh=?, cmnd=?, que_quan=?, dia_chi_thuong_tru=?, so_dien_thoai=?, chuc_vu=?, luong=?, ghi_chu=?, anh=?  WHERE ma_nv=?";
        JdbcHelper.executeUpdate(sql,
                model.getHoTen(),
                model.isGioiTinh(),
                model.getNgaySinh(),
                model.getCmnd(),
                model.getQueQuan(),
                model.getDiaChiThuongTru(),
                model.getSoDienThoai(),
                model.getChucVu(),
                model.getLuong(),
                model.getGhiChu(),
                model.getAnh(),
                model.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM tbl_NhanVien WHERE Ma_NV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM tbl_NhanVien";
        return select(sql);
    }

    public NhanVien findById(int manv) {
        String sql = "SELECT * FROM tbl_NhanVien WHERE ma_nv=?";
        List<NhanVien> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public NhanVien findByUN(String un) {
        String sql = "SELECT * FROM tbl_NhanVien WHERE username=?";
        List<NhanVien> list = select(sql, un);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
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

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getInt("ma_nv"));
        model.setHoTen(rs.getString("ho_ten"));
        model.setGioiTinh(rs.getBoolean("gioi_tinh"));
        model.setNgaySinh(rs.getDate("ngay_sinh"));
        model.setCmnd(rs.getString("cmnd"));
        model.setQueQuan(rs.getString("que_quan"));
        model.setDiaChiThuongTru(rs.getString("dia_chi_thuong_tru"));
        model.setSoDienThoai(rs.getString("so_dien_thoai"));
        model.setChucVu(rs.getString("chuc_vu"));
        model.setLuong(rs.getFloat("luong"));
        model.setNgayCongTac(rs.getDate("ngay_cong_tac"));
        model.setAnh(rs.getString("anh"));
        model.setGhiChu(rs.getString("ghi_chu"));
        model.setUsername(rs.getString("username"));
        model.setPass(rs.getString("password"));
        return model;
    }
}
