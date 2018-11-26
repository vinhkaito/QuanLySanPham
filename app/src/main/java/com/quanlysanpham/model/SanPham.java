package com.quanlysanpham.model;

import java.io.Serializable;
import java.util.Arrays;

public class SanPham implements Serializable {
    public int MaSP;
    public String TenSP;
    public PhanLoai LoaiSP;
    public byte[] HinhSP;
    public String GiaSP;
    public String XuatxuSP;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, PhanLoai loaiSP, byte[] hinhSP, String giaSP, String xuatxuSP) {
        MaSP = maSP;
        TenSP = tenSP;
        LoaiSP = loaiSP;
        HinhSP = hinhSP;
        GiaSP = giaSP;
        XuatxuSP = xuatxuSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public PhanLoai getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(PhanLoai loaiSP) {
        LoaiSP = loaiSP;
    }

    public byte[] getHinhSP() {
        return HinhSP;
    }

    public void setHinhSP(byte[] hinhSP) {
        HinhSP = hinhSP;
    }

    public String getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(String giaSP) {
        GiaSP = giaSP;
    }

    public String getXuatxuSP() {
        return XuatxuSP;
    }

    public void setXuatxuSP(String xuatxuSP) {
        XuatxuSP = xuatxuSP;
    }

    @Override
    public String toString() {
        return  MaSP + "-" + TenSP + "-" + LoaiSP + "-" + Arrays.toString(HinhSP);
    }
}
