package com.quanlysanpham.sanpham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.quanlysanpham.quanlysanpham.R;

public class ControlsSanPham extends AppCompatActivity {
    TextView txtTitleSP;
    EditText txtMaSP , txtTenSP , txtGiaSP , txtXuatXuSP ;
    Button btnChonhinh , btnTienHanhSP;
    ImageView ImgChonHinh;
    Spinner phanloai;

    int function = -1;
    final int them = 1;
    final int sua = 0;
    Intent intent;


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
        txtTitleSP = findViewById(R.id.txtTitleSP);
        phanloai = findViewById(R.id.phanloai);
        txtMaSP = findViewById(R.id.txtMaSP);
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGiaSP = findViewById(R.id.txtGiaSP);
        txtXuatXuSP = findViewById(R.id.txtXuatXuSP);
        btnTienHanhSP = findViewById(R.id.btnTienHanhSP);
        btnChonhinh = findViewById(R.id.btnChonhinh);
        ImgChonHinh = findViewById(R.id.ImgChonHinh);
        intent = getIntent();
        if (intent.hasExtra("UpSP")) {
            function = sua;
            txtTitleSP.setText("Cập Nhật Sản Phẩm");
        }
        else {
            function = them;
            txtTitleSP.setText("Thêm Sản Phẩm");
        }

    }
}
