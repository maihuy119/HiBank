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
import model.LoaiHinhVay;

/**
 *
 * @author ADMIN
 */
public class LoaiHinhVayDAO {
    public void insert(LoaiHinhVay model) {
        String sql = "INSERT INTO tbl_LoaiHinhVay (ma_loai_hinh, ten_loai_hinh, lai_suat) VALUES (?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaLoai(),
                model.getTenLoai(),
                model.getLaiSuat());
    }

    public void update(LoaiHinhVay model) {
        String sql = "UPDATE tbl_LoaiHinhVay SET ten_loai_hinh=?, lai_suat=? WHERE ma_loai_hinh=?";
        JdbcHelper.executeUpdate(sql,                
                model.getTenLoai(),
                model.getLaiSuat(),
                model.getMaLoai());
    }

    public void delete(String MaLoai) {
        String sql = "DELETE FROM tbl_LoaiHinhVay WHERE ma_loai_hinh=?";
        JdbcHelper.executeUpdate(sql, MaLoai);
    }
    
    public List<LoaiHinhVay> select() {
        String sql = "SELECT * FROM tbl_LoaiHinhVay";
        return select(sql);
    }

    public LoaiHinhVay findById(String maloai) {
        String sql = "SELECT * FROM tbl_LoaiHinhVay WHERE ma_loai_hinh=?";
        List<LoaiHinhVay> list = select(sql, maloai);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<LoaiHinhVay> select(String sql, Object... args) {
        List<LoaiHinhVay> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiHinhVay model = readFromResultSet(rs);
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

    private LoaiHinhVay readFromResultSet(ResultSet rs) throws SQLException {
        LoaiHinhVay model = new LoaiHinhVay();
        model.setMaLoai(rs.getString("ma_loai_hinh"));
        model.setTenLoai(rs.getString("ten_loai_hinh"));
        model.setLaiSuat(rs.getDouble("lai_suat"));
        return model;
    }
}
