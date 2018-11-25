package com.quanlysanpham.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanlysanpham.model.SanPham;
import com.quanlysanpham.quanlysanpham.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSanPham extends BaseAdapter {

    Activity context;
    int resource;
    ArrayList<SanPham> objiects;


    public AdapterSanPham(Activity context, int resource, ArrayList<SanPham> objects) {
        this.context = context;
        this.resource = resource;
        this.objiects = objects;
    }

    @Override
    public int getCount() {
        return objiects.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(resource,null);
        TextView txtMaSP = view.findViewById(R.id.txtMaSP);
        TextView txtTenSP = view.findViewById(R.id.txtTenSP);
        TextView txtPhanLoaiSP = view.findViewById(R.id.txtPhanLoaiSP);
        ImageView ImgHinh = view.findViewById(R.id.ImgHinh);

        SanPham sp = this.objiects.get(position);
        txtMaSP.setText(sp.MaSP+ "");
        txtTenSP.setText(sp.TenSP);
        txtPhanLoaiSP.setText((CharSequence) sp.LoaiSP);

        Bitmap bmHinh = BitmapFactory.decodeByteArray(sp.HinhSP, 0 ,sp.HinhSP.length);
        ImgHinh.setImageBitmap(bmHinh);
        return null;
    }
}
