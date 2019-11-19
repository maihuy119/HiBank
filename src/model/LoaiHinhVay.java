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
    private String maLoai;
    private String tenLoai;
    private Double laiSuat;

    public LoaiHinhVay() {
    }

    public LoaiHinhVay(String maLoai, String tenLoai, Double laiSuat) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.laiSuat = laiSuat;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Double getLaiSuat() {
        return laiSuat;
    }

    public void setLaiSuat(Double laiSuat) {
        this.laiSuat = laiSuat;
    }

    
}
