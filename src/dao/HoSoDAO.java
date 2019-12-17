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

/**
 *
 * @author ADMIN
 */
public class HoSoDAO {
    public void insert(HoSo model) {
        String sql = "INSERT INTO tbl_HoSo (ma_nguoi_vay, ho_ten_nguoi_vay, loai_hinh_vay, so_tien, muc_dich, tai_san_the_chap, ngay_vay, nhan_vien_thuc_hien, ngay_het_han, ghi_chu, so_tien_lai, ngay_tra, ky_han, da_thanh_toan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNguoiVay(),
                model.getHoTenNguoiVay(),
                model.getLoaiHinhVay(),
                model.getSoTien(),
                model.getMucDich(),
                model.getTaiSanTheChap(),
                model.getNgayVay(),
                model.getNhanVienThucHien(),
                model.getNgayHetHan(),
                model.getGhiChu(),
                model.getSoTienLai(),
                model.getNgayTra(),
                model.getThoiHan(),
                model.isDaThanhToan());
    }

    public void update(HoSo model) {
        String sql = "UPDATE tbl_HoSo SET ho_ten_nguoi_vay=?, loai_hinh_vay=?, so_tien=?, muc_dich=?, tai_san_the_chap=?, ngay_het_han=?, ghi_chu=?, so_tien_lai=?, ky_han=? WHERE ma_ho_so=?";
        JdbcHelper.executeUpdate(sql,
                model.getHoTenNguoiVay(),
                model.getLoaiHinhVay(),
                model.getSoTien(),
                model.getMucDich(),
                model.getTaiSanTheChap(),
                model.getNgayHetHan(),
                model.getGhiChu(),
                model.getSoTienLai(),
                model.getThoiHan(),
                model.getMaHoSo());
    }
    
    public void payment(HoSo model) {
        String sql = "UPDATE tbl_HoSo SET da_thanh_toan=?, ngay_tra=? WHERE ma_ho_so=?";
        JdbcHelper.executeUpdate(sql,
                model.isDaThanhToan(),
                model.getNgayTra(),
                model.getMaHoSo());
    }

    public void delete(String MaHS) {
        String sql = "DELETE FROM tbl_HoSo WHERE ma_ho_so=?";
        JdbcHelper.executeUpdate(sql, MaHS);
    }
    
    public List<HoSo> select() {
        String sql = "SELECT * FROM tbl_HoSo";
        return select(sql);
    }
    
    public List<HoSo> selectByKeywordPayment(String keyword) {
        String sql = "SELECT * FROM tbl_HoSo WHERE da_thanh_toan = 'false' and ho_ten_nguoi_vay LIKE ?";
        return select(sql, "%" + keyword + "%");
    }
    
    public List<HoSo> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM tbl_HoSo WHERE ho_ten_nguoi_vay LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public HoSo findById(String mahs) {
        String sql = "SELECT * FROM tbl_HoSo WHERE ma_ho_so=?";
        List<HoSo> list = select(sql, mahs);
        return list.size() > 0 ? list.get(0) : null;
    }
    

    private List<HoSo> select(String sql, Object... args) {
        List<HoSo> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoSo model = readFromResultSet(rs);
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
    
    private HoSo readFromResultSet(ResultSet rs) throws SQLException {
        HoSo model = new HoSo();
        model.setMaHoSo(rs.getString("ma_ho_so"));
        model.setMaNguoiVay(rs.getInt("ma_nguoi_vay"));
        model.setHoTenNguoiVay(rs.getString("ho_ten_nguoi_vay"));
        model.setLoaiHinhVay(rs.getString("loai_hinh_vay"));
        model.setSoTien(rs.getFloat("so_tien"));
        model.setMucDich(rs.getString("muc_dich"));
        model.setNgayVay(rs.getDate("ngay_vay"));
        model.setNhanVienThucHien(rs.getString("nhan_vien_thuc_hien"));
        model.setNgayHetHan(rs.getDate("ngay_het_han"));
        model.setGhiChu(rs.getString("ghi_chu"));       
        model.setSoTienLai(rs.getFloat("so_tien_lai"));
        model.setNgayTra(rs.getDate("ngay_tra"));
        model.setThoiHan(rs.getString("ky_han"));
        model.setDaThanhToan(rs.getBoolean("da_thanh_toan"));
        return model;
    }
}
