/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ADMIN
 */
public class LoaiHinhVay {
    private String ma_loai;
    private String ten_loai;
    private Double lai_suat;

    public LoaiHinhVay() {
    }

    public LoaiHinhVay(String ma_loai, String ten_loai, Double lai_suat) {
        this.ma_loai = ma_loai;
        this.ten_loai = ten_loai;
        this.lai_suat = lai_suat;
    }

    public String getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(String ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }

    public Double getLai_suat() {
        return lai_suat;
    }

    public void setLai_suat(Double lai_suat) {
        this.lai_suat = lai_suat;
    }

    @Override
    public String toString() {
        return "LoaiHinhVay{" + "ma_loai=" + ma_loai + ", ten_loai=" + ten_loai + ", lai_suat=" + lai_suat + '}';
    }
    
}
