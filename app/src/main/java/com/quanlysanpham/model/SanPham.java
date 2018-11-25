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
}
