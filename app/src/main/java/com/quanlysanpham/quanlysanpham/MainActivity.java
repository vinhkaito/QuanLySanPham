package com.quanlysanpham.quanlysanpham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quanlysanpham.sanpham.DanhSachSanPham;

public class MainActivity extends AppCompatActivity {
    EditText txtTaikhoan , txtMatkhau;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addControls() {
        txtTaikhoan = findViewById(R.id.txtTaikhoan);
        txtMatkhau = findViewById(R.id.txtMatkhau);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void addEvents() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "admin";
                String password = "admin";

                if (txtTaikhoan.getText().toString().equals(username) && txtMatkhau.getText().toString().equals(password))
                {
                    Toast.makeText(getApplicationContext(),"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,DanhSachSanPham.class);
                    startActivity(intent);
                }
            }
        });
    }
}
