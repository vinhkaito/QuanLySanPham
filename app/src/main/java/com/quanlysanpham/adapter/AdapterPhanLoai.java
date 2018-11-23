package com.quanlysanpham.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.quanlysanpham.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhanLoai extends ArrayAdapter<PhanLoai> {
    Activity context;
    int resource;
    ArrayList<PhanLoai> objects;


    public AdapterPhanLoai(Activity context, int resource, ArrayList<PhanLoai> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource,null);
        EditText txtMapl = view.findViewById(R.id.txtMapl);
        EditText txtTenpl = view.findViewById(R.id.txtTenpl);

        PhanLoai pl = this.objects.get(position);
        txtMapl.setText(pl.getMaPL());
        txtTenpl.setText(pl.getTenPL());
        return view;
    }
}
