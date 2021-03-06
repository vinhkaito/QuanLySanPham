package com.quanlysanpham.sanpham;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.quanlysanpham.adapter.AdapterSanPham;
import com.quanlysanpham.database.Database;
import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.model.SanPham;
import com.quanlysanpham.phanloai.ControlsPhanLoai;
import com.quanlysanpham.phanloai.DanhSachPhanLoai;
import com.quanlysanpham.quanlysanpham.R;

import java.util.ArrayList;

public class DanhSachSanPham extends AppCompatActivity {
    AdapterSanPham adapter;
    ListView lvSanPham;
    ArrayList<SanPham> dsSP;
    int pos = -1;

    final String DATABASE_NAME = "QLSPDB.sqlite";
    SQLiteDatabase database;

    final static int CapNhatSanPhamRequestCode = 15;
    final static int CapNhatSanPhamResultCode = 16;
    final static int ThemSanPhamRequestCode = 17;
    final static int ThemSanPhamResultCode = 18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);
        addControls();
        addEvents();
        loaddata();
    }

    private void loaddata() {

    }

    private void addEvents() {
    }

    private void addControls() {
        lvSanPham = findViewById(R.id.lvSanPham);
        dsSP = new ArrayList<>();
        adapter = new AdapterSanPham(DanhSachSanPham.this,R.layout.item_san_pham,
                dsSP
        );
        lvSanPham.setAdapter(adapter);
        registerForContextMenu(lvSanPham);
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
            case R.id.opmenutThemSanPham:
            {
                pos = -1;
                Intent intent = new Intent(DanhSachSanPham.this,ControlsSanPham.class);
                startActivityForResult(intent,ThemSanPhamRequestCode);
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
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ThemSanPhamRequestCode && resultCode == ThemSanPhamResultCode && data.hasExtra("SP_Them"))
        {
            SanPham sp = (SanPham) data.getSerializableExtra("SP_Them");
            ContentValues values = new ContentValues();
            values.put("MaSanPham", sp.getMaSP());
            values.put("TenSanPham", sp.getTenSP());
            values.put("LoaiSanPham", sp.getLoaiSP().getMaPL());
            values.put("HinhSanPham", sp.getHinhSP());
            values.put("GiaSanPham", sp.getGiaSP());
            values.put("XuatXuSanPham", sp.getXuatxuSP());

            if (database.insert("SanPham",null,values) != -1)
            {
                Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
                cursor.moveToLast();
                sp.setMaSP(cursor.getInt(0));
                sp.setTenSP(cursor.getString(1));
//                sp.setLoaiSP();                             ///// lam tiep o day
                sp.setHinhSP(cursor.getBlob(3));
                sp.setGiaSP(cursor.getString(4));
                sp.setXuatxuSP(cursor.getString(5));
                dsSP.add(sp);
                Toast.makeText(getApplicationContext(), "Thêm Thành Công", Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
            }else {
                Toast.makeText(getApplicationContext(), "Thêm Thất Bại", Toast.LENGTH_LONG).show();
            }
        }
    }
}
