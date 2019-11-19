/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HoSo {
    private String maHoSo;
    private String nguoiVay;
    private String loaiHinhVay;
    private float soTien;
    private String mucDich;
    private Date ngayVay;
    private String nhanVienThucHien;
    private Date ngayHetHan;
    private String ghiChu;
    private float soTienLai;
    private Date ngayTra;
    private String thoiHan;
    private boolean daThanhToan;

    public HoSo() {
    }

    public HoSo(String maHoSo, String nguoiVay, String loaiHinhVay, float soTien, String mucDich, Date ngayVay, String nhanVienThucHien, Date ngayHetHan, String ghiChu, float soTienLai, Date ngayTra, String thoiHan, boolean daThanhToan) {
        this.maHoSo = maHoSo;
        this.nguoiVay = nguoiVay;
        this.loaiHinhVay = loaiHinhVay;
        this.soTien = soTien;
        this.mucDich = mucDich;
        this.ngayVay = ngayVay;
        this.nhanVienThucHien = nhanVienThucHien;
        this.ngayHetHan = ngayHetHan;
        this.ghiChu = ghiChu;
        this.soTienLai = soTienLai;
        this.ngayTra = ngayTra;
        this.thoiHan = thoiHan;
        this.daThanhToan = daThanhToan;
    }

    public String getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(String maHoSo) {
        this.maHoSo = maHoSo;
    }

    public String getNguoiVay() {
        return nguoiVay;
    }

    public void setNguoiVay(String nguoiVay) {
        this.nguoiVay = nguoiVay;
    }

    public String getLoaiHinhVay() {
        return loaiHinhVay;
    }

    public void setLoaiHinhVay(String loaiHinhVay) {
        this.loaiHinhVay = loaiHinhVay;
    }

    public float getSoTien() {
        return soTien;
    }

    public void setSoTien(float soTien) {
        this.soTien = soTien;
    }

    public String getMucDich() {
        return mucDich;
    }

    public void setMucDich(String mucDich) {
        this.mucDich = mucDich;
    }

    public Date getNgayVay() {
        return ngayVay;
    }

    public void setNgayVay(Date ngayVay) {
        this.ngayVay = ngayVay;
    }

    public String getNhanVienThucHien() {
        return nhanVienThucHien;
    }

    public void setNhanVienThucHien(String nhanVienThucHien) {
        this.nhanVienThucHien = nhanVienThucHien;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public float getSoTienLai() {
        return soTienLai;
    }

    public void setSoTienLai(float soTienLai) {
        this.soTienLai = soTienLai;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(String thoiHan) {
        this.thoiHan = thoiHan;
    }

    public boolean isDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(boolean daThanhToan) {
        this.daThanhToan = daThanhToan;
    }

    
    
}
