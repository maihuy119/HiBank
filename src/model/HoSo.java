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
    private int maNguoiVay;
    private String hoTenNguoiVay;
    private String loaiHinhVay;
    private float soTien;
    private String mucDich;
    private Date ngayVay;
    private String nhanVienThucHien;
    private Date ngayHetHan;
    private String taiSanTheChap;
    private String ghiChu;
    private float soTienLai;
    private Date ngayTra;
    private String thoiHan;
    private boolean daThanhToan;
    private String cmnd;

    public HoSo() {
    }

    public HoSo(String maHoSo, int maNguoiVay, String hoTenNguoiVay, String loaiHinhVay, float soTien, String mucDich, Date ngayVay, String nhanVienThucHien, Date ngayHetHan, String taiSanTheChap, String ghiChu, float soTienLai, Date ngayTra, String thoiHan, boolean daThanhToan, String cmnd) {
        this.maHoSo = maHoSo;
        this.maNguoiVay = maNguoiVay;
        this.hoTenNguoiVay = hoTenNguoiVay;
        this.loaiHinhVay = loaiHinhVay;
        this.soTien = soTien;
        this.mucDich = mucDich;
        this.ngayVay = ngayVay;
        this.nhanVienThucHien = nhanVienThucHien;
        this.ngayHetHan = ngayHetHan;
        this.taiSanTheChap = taiSanTheChap;
        this.ghiChu = ghiChu;
        this.soTienLai = soTienLai;
        this.ngayTra = ngayTra;
        this.thoiHan = thoiHan;
        this.daThanhToan = daThanhToan;
        this.cmnd = cmnd;
    }

    public String getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(String maHoSo) {
        this.maHoSo = maHoSo;
    }

    public int getMaNguoiVay() {
        return maNguoiVay;
    }

    public void setMaNguoiVay(int maNguoiVay) {
        this.maNguoiVay = maNguoiVay;
    }

    public String getHoTenNguoiVay() {
        return hoTenNguoiVay;
    }

    public void setHoTenNguoiVay(String hoTenNguoiVay) {
        this.hoTenNguoiVay = hoTenNguoiVay;
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

    public String getTaiSanTheChap() {
        return taiSanTheChap;
    }

    public void setTaiSanTheChap(String taiSanTheChap) {
        this.taiSanTheChap = taiSanTheChap;
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

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    
    
}
