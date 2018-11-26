package com.quanlysanpham.phanloai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.quanlysanpham.R;

public class ControlsPhanLoai extends AppCompatActivity {
    EditText txtTenpl;
    EditText txtMapl;
    Button btnTienHanhPL;
    TextView txtPhanLoai;
    int function = -1;
    final int them = 1;
    final int sua = 0;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls_phan_loai);
        addControls();
        addEvents();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (intent.hasExtra("UpPL"))
        {
            PhanLoai pl = (PhanLoai) intent.getSerializableExtra("UpPL");
            txtMapl.setText(pl.getMaPL() + "");
            txtTenpl.setText(pl.getTenPL());
            txtMapl.setEnabled(false);

        }
    }

    private void addEvents() {

        btnTienHanhPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (function == sua) {
                    PhanLoai pl_x = (PhanLoai) intent.getSerializableExtra("UpPL");
                    String TenPL = txtTenpl.getText().toString();
                    PhanLoai pl = new PhanLoai(pl_x.getMaPL(),TenPL);
                    Intent intent = getIntent();
                    intent.putExtra("PL_CapNhat",pl);
                    setResult(DanhSachPhanLoai.CapNhatPhanLoaiResultCode,intent);
                    finish();
                }
                else {
                    String TenPL = txtTenpl.getText().toString();
                    PhanLoai pl = new PhanLoai(1,TenPL);
                    Intent intent =getIntent();
                    intent.putExtra("PL_Them",pl);
                    setResult(DanhSachPhanLoai.ThemPhanLoaiResultCode,intent);
                    finish();
                }
            }
        });
    }

    private void addControls() {
        txtMapl = findViewById(R.id.txtMapl);
        txtTenpl = findViewById(R.id.txtTenpl);
        btnTienHanhPL = findViewById(R.id.btnTienHanhPL);
        txtPhanLoai = findViewById(R.id.txtPhanloai);
        intent = getIntent();
        if (intent.hasExtra("UpPL")) {
            function = sua;
            txtPhanLoai.setText("Sửa Phân Loại");
        }
        else {
            function = them;
            txtPhanLoai.setText("Thêm Phân Loại");
        }
    }

}
