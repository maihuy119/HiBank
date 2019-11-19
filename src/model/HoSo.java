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
    private String ma_ho_so;
    private String nguoi_vay;
    private String loai_hinh_vay;
    private float so_tien;
    private String muc_dich;
    private Date ngay_vay;
    private String nhan_vien_thuc_hien;
    private Date ngay_het_han;
    private String ghi_chu;
    private float so_tien_lai;
    private Date ngay_tra;
    private String thoi_han;
    private boolean da_thanh_toan;

    public HoSo() {
    }

    public HoSo(String ma_ho_so, String nguoi_vay, String loai_hinh_vay, float so_tien, String muc_dich, Date ngay_vay, String nhan_vien_thuc_hien, Date ngay_het_han, String ghi_chu, float so_tien_lai, Date ngay_tra, String thoi_han, boolean da_thanh_toan) {
        this.ma_ho_so = ma_ho_so;
        this.nguoi_vay = nguoi_vay;
        this.loai_hinh_vay = loai_hinh_vay;
        this.so_tien = so_tien;
        this.muc_dich = muc_dich;
        this.ngay_vay = ngay_vay;
        this.nhan_vien_thuc_hien = nhan_vien_thuc_hien;
        this.ngay_het_han = ngay_het_han;
        this.ghi_chu = ghi_chu;
        this.so_tien_lai = so_tien_lai;
        this.ngay_tra = ngay_tra;
        this.thoi_han = thoi_han;
        this.da_thanh_toan = da_thanh_toan;
    }

    public String getMa_ho_so() {
        return ma_ho_so;
    }

    public void setMa_ho_so(String ma_ho_so) {
        this.ma_ho_so = ma_ho_so;
    }

    public String getNguoi_vay() {
        return nguoi_vay;
    }

    public void setNguoi_vay(String nguoi_vay) {
        this.nguoi_vay = nguoi_vay;
    }

    public String getLoai_hinh_vay() {
        return loai_hinh_vay;
    }

    public void setLoai_hinh_vay(String loai_hinh_vay) {
        this.loai_hinh_vay = loai_hinh_vay;
    }

    public float getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(float so_tien) {
        this.so_tien = so_tien;
    }

    public String getMuc_dich() {
        return muc_dich;
    }

    public void setMuc_dich(String muc_dich) {
        this.muc_dich = muc_dich;
    }

    public Date getNgay_vay() {
        return ngay_vay;
    }

    public void setNgay_vay(Date ngay_vay) {
        this.ngay_vay = ngay_vay;
    }

    public String getNhan_vien_thuc_hien() {
        return nhan_vien_thuc_hien;
    }

    public void setNhan_vien_thuc_hien(String nhan_vien_thuc_hien) {
        this.nhan_vien_thuc_hien = nhan_vien_thuc_hien;
    }

    public Date getNgay_het_han() {
        return ngay_het_han;
    }

    public void setNgay_het_han(Date ngay_het_han) {
        this.ngay_het_han = ngay_het_han;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public float getSo_tien_lai() {
        return so_tien_lai;
    }

    public void setSo_tien_lai(float so_tien_lai) {
        this.so_tien_lai = so_tien_lai;
    }

    public Date getNgay_tra() {
        return ngay_tra;
    }

    public void setNgay_tra(Date ngay_tra) {
        this.ngay_tra = ngay_tra;
    }

    public String getThoi_han() {
        return thoi_han;
    }

    public void setThoi_han(String thoi_han) {
        this.thoi_han = thoi_han;
    }

    public boolean isDa_thanh_toan() {
        return da_thanh_toan;
    }

    public void setDa_thanh_toan(boolean da_thanh_toan) {
        this.da_thanh_toan = da_thanh_toan;
    }

    @Override
    public String toString() {
        return "HoSo{" + "ma_ho_so=" + ma_ho_so + ", nguoi_vay=" + nguoi_vay + ", loai_hinh_vay=" + loai_hinh_vay + ", so_tien=" + so_tien + ", muc_dich=" + muc_dich + ", ngay_vay=" + ngay_vay + ", nhan_vien_thuc_hien=" + nhan_vien_thuc_hien + ", ngay_het_han=" + ngay_het_han + ", ghi_chu=" + ghi_chu + ", so_tien_lai=" + so_tien_lai + ", ngay_tra=" + ngay_tra + ", thoi_han=" + thoi_han + ", da_thanh_toan=" + da_thanh_toan + '}';
    }
    
    
}
