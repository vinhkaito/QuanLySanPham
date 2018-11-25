package com.quanlysanpham.sanpham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.quanlysanpham.phanloai.ControlsPhanLoai;
import com.quanlysanpham.phanloai.DanhSachPhanLoai;
import com.quanlysanpham.quanlysanpham.R;

public class DanhSachSanPham extends AppCompatActivity {
    int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_sp,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.opmenutThemLoai:
            {
                pos = -1;
                Intent intent = new Intent(DanhSachSanPham.this,ControlsSanPham.class);
                startActivityForResult(intent,11);
                break;
            }
            case R.id.opmenutDSPL:
            {
                Intent intent = new Intent(DanhSachSanPham.this,DanhSachPhanLoai.class);
                startActivity(intent);
                break;
            }
            case R.id.opmenuThoat:
            {

            }
        }
        return super.onOptionsItemSelected(item);
    }
}
