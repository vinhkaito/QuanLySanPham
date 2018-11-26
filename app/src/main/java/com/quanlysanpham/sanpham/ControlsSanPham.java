package com.quanlysanpham.sanpham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.quanlysanpham.quanlysanpham.R;

public class ControlsSanPham extends AppCompatActivity {

    Spinner phanloai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls_san_pham);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        phanloai = findViewById(R.id.phanloai);

    }
}
