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
public class KhachHang {
    private String ma_khach_hang;
    private String ho_ten;
    private boolean gioi_tinh;
    private Date ngay_sinh;
    private String cmnd;
    private String que_quan;
    private String dia_chi_thuong_tru;
    private String so_dien_thoai;
    private String nguoi_tao;
    private Date ngay_tao;
    private String ghi_chu;
    private String anh;

    public KhachHang() {
    }

    public KhachHang(String ma_khach_hang, String ho_ten, boolean gioi_tinh, Date ngay_sinh, String cmnd, String que_quan, String dia_chi_thuong_tru, String so_dien_thoai, String nguoi_tao, Date ngay_tao, String ghi_chu, String anh) {
        this.ma_khach_hang = ma_khach_hang;
        this.ho_ten = ho_ten;
        this.gioi_tinh = gioi_tinh;
        this.ngay_sinh = ngay_sinh;
        this.cmnd = cmnd;
        this.que_quan = que_quan;
        this.dia_chi_thuong_tru = dia_chi_thuong_tru;
        this.so_dien_thoai = so_dien_thoai;
        this.nguoi_tao = nguoi_tao;
        this.ngay_tao = ngay_tao;
        this.ghi_chu = ghi_chu;
        this.anh = anh;
    }

    public String getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(String ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public boolean isGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(boolean gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getQue_quan() {
        return que_quan;
    }

    public void setQue_quan(String que_quan) {
        this.que_quan = que_quan;
    }

    public String getDia_chi_thuong_tru() {
        return dia_chi_thuong_tru;
    }

    public void setDia_chi_thuong_tru(String dia_chi_thuong_tru) {
        this.dia_chi_thuong_tru = dia_chi_thuong_tru;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "ma_khach_hang=" + ma_khach_hang + ", ho_ten=" + ho_ten + ", gioi_tinh=" + gioi_tinh + ", ngay_sinh=" + ngay_sinh + ", cmnd=" + cmnd + ", que_quan=" + que_quan + ", dia_chi_thuong_tru=" + dia_chi_thuong_tru + ", so_dien_thoai=" + so_dien_thoai + ", nguoi_tao=" + nguoi_tao + ", ngay_tao=" + ngay_tao + ", ghi_chu=" + ghi_chu + ", anh=" + anh + '}';
    }
    
    
}
