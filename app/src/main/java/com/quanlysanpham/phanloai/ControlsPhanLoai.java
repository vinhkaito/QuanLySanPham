package com.quanlysanpham.phanloai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.quanlysanpham.R;

public class ControlsPhanLoai extends AppCompatActivity {
    EditText txtTenpl;
    EditText txtMapl;
    Button btnTienHanhPL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls_phan_loai);
        addControls();
        addEvents();
    }

    private void addEvents() {

        btnTienHanhPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int MaPL = Integer.parseInt(txtMapl.getText().toString());
                String TenPL = txtTenpl.getText().toString();

                PhanLoai pl = new PhanLoai(MaPL,TenPL);
                Intent intent =getIntent();
                intent.putExtra("PL",pl);
                setResult(12,intent);
                finish();
            }
        });
    }

    private void addControls() {
        txtMapl = findViewById(R.id.txtMapl);
        txtTenpl = findViewById(R.id.txtTenpl);
        btnTienHanhPL = findViewById(R.id.btnTienHanhPL);
    }
}
