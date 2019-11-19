/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.JdbcHelper;
import model.NhanVien;

/**
 *
 * @author maihu
 */
public class NhanVienDAO {
    public void insert(NhanVien model) {
        String sql = "INSERT INTO tbl_NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNV(),
                model.getMatKhau(),
                model.getHoTen(),
                model.isVaiTro());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql,
                model.getMatKhau(),
                model.getHoTen(),
                model.isVaiTro(),
                model.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }
}
