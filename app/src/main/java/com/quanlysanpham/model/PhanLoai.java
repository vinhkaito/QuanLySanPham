package com.quanlysanpham.model;

import java.io.Serializable;

public class PhanLoai implements Serializable {
    int MaPL;
    String TenPL;

    public PhanLoai(int maPL, String tenPL) {
        MaPL = maPL;
        TenPL = tenPL;
    }

    public int getMaPL() {
        return MaPL;
    }

    public void setMaPL(int maPL) {
        MaPL = maPL;
    }

    public String getTenPL() {
        return TenPL;
    }

    public void setTenPL(String tenPL) {
        TenPL = tenPL;
    }

    @Override
    public String toString() {
        return MaPL + "-" + TenPL;
    }
}
