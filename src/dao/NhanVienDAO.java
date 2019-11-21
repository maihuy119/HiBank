/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.DateHelper;
import helper.JdbcHelper;
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
        String sql = "INSERT INTO tbl_NhanVien (ma_nv, ho_ten, gioi_tinh, ngay_sinh, cmnd, que_quan, dia_chi_thuong_tru, so_dien_thoai, chuc_vu, luong, ngay_cong_tac, ghi_chu, anh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNV(),
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
                model.getAnh());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE tbl_NhanVien SET ho_ten=?, gioi_tinh=?, ngay_sinh=?, cmnd=?, que_quan=?, dia_chi_thuong_tru=?, so_dien_thoai=?, chuc_vu=?, luong=?, ngay_cong_tac=?, ghi_chu=?, anh=?  WHERE ma_nv=?";
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
                model.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM tbl_NhanVien WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM tbl_NhanVien";
        return select(sql);
    }

    public NhanVien findById(String manv) {
        String sql = "SELECT * FROM tbl_NhanVien WHERE ma_nv=?";
        List<NhanVien> list = select(sql, manv);
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
        model.setMaNV(rs.getString("ma_nv"));
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
        return model;
    }

    //test DAO
    public static void main(String[] args) {
        NhanVienDAO dao = new NhanVienDAO();
        NhanVien nv = new NhanVien("1", "huy", true, DateHelper.toDate("01/01/2000"), "206288820", "null", "null", "0123456789", "nv", 0, DateHelper.toDate("01/01/2019"), "null", "null");
        
        dao.insert(nv);
    }
}
