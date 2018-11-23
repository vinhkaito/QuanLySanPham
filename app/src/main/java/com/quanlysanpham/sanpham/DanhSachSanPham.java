package com.quanlysanpham.sanpham;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.quanlysanpham.adapter.AdapterPhanLoai;
import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.phanloai.ControlsPhanLoai;
import com.quanlysanpham.phanloai.DanhSachPhanLoai;
import com.quanlysanpham.quanlysanpham.R;

import java.util.ArrayList;

public class DanhSachSanPham extends AppCompatActivity {
    AdapterPhanLoai adapter;
    ListView lvPhanLoai;
    ArrayList<PhanLoai> dsPL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvPhanLoai = findViewById(R.id.lvPhanLoai);
        dsPL = new ArrayList<>();
        adapter = (AdapterPhanLoai) new ArrayAdapter<>(
                DanhSachSanPham.this,R.layout.activity_danh_sach_phan_loai,
                dsPL
        );
        lvPhanLoai.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.opmenutThemLoai:
            {
                Intent intent = new Intent(DanhSachSanPham.this,ControlsPhanLoai.class);
                startActivityForResult(intent,11);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11)
        {
            if (resultCode == 12)
            {
                if (data.hasExtra("PL"))
                {
                    PhanLoai pl = (PhanLoai) data.getSerializableExtra("PL");
                    dsPL.add(pl);
                    Toast.makeText(getApplicationContext(),"Thêm Phân Loại Thành Công",Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
