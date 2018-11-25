package com.quanlysanpham.phanloai;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.quanlysanpham.adapter.AdapterPhanLoai;
import com.quanlysanpham.database.Database;
import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.quanlysanpham.R;
import com.quanlysanpham.sanpham.DanhSachSanPham;

import java.util.ArrayList;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class DanhSachPhanLoai extends AppCompatActivity {
    AdapterPhanLoai adapter;
    ListView lvPhanLoai;
    ArrayList<PhanLoai> dsPL;
    int pos = -1;

    final String DATABASE_NAME = "QLSPDB.sqlite";
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phan_loai);
        addControls();
        addEvents();
        //fakedata();
        loaddata();
    }

    private void loaddata() {
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor  = database.rawQuery("SELECT * FROM PhanLoai",null);
        for (int i=0 ; i < cursor.getCount() ;i++)
        {
            cursor.moveToPosition(i);
            PhanLoai pl = new PhanLoai(cursor.getInt(0),cursor.getString(1));
            dsPL.add(pl);
            adapter.notifyDataSetChanged();
        }
    }


//    private void fakedata() {
//        dsPL.add(new PhanLoai(1,"haha"));
//        dsPL.add(new PhanLoai(2,"hahai"));
//        dsPL.add(new PhanLoai(3,"haihai"));
//        dsPL.add(new PhanLoai(4,"haiha"));
//        adapter.notifyDataSetChanged();
//    }

    private void addEvents() {
    }

    private void addControls() {
        lvPhanLoai = findViewById(R.id.lvPhanLoai);
        dsPL = new ArrayList<>();
        adapter = new AdapterPhanLoai(
                DanhSachPhanLoai.this,R.layout.item_phan_loai,
                dsPL
        );
        lvPhanLoai.setAdapter(adapter);
        registerForContextMenu(lvPhanLoai);
    }

//    CONTEXT MENU

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lvPhanLoai)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_menu,menu);
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            pos = acmi.position;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.comenuCapNhat:
            {
                PhanLoai pl = dsPL.get(pos);
                Intent intent = new Intent(DanhSachPhanLoai.this,ControlsPhanLoai.class);
                intent.putExtra("UpPL",pl);
                startActivityForResult(intent,11);
                break;
            }
            case R.id.comenuXoa:
            {
                dsPL.remove(pos);
                pos = -1;
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_LONG).show();
                break;
            }
        }


        return super.onContextItemSelected(item);
    }

    //    END CONTEXT MENU


//    OPTION MENU

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
                pos = -1;
                Intent intent = new Intent(DanhSachPhanLoai.this,ControlsPhanLoai.class);
                startActivityForResult(intent,11);
                break;
            }
            case R.id.opmenutDSSP:
            {
                Intent intent = new Intent(DanhSachPhanLoai.this,DanhSachSanPham.class);
                startActivity(intent);
                break;
            }
            case R.id.opmenuThoat:
            {

            }
        }
        return super.onOptionsItemSelected(item);
    }

//    END OPTION MENU

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11)
        {
            if (resultCode == 12)
            {
                if (data.hasExtra("PL"))
                {
                    PhanLoai pl = (PhanLoai) data.getSerializableExtra("PL");
                    if(pos>=0)
                    {
                        PhanLoai selectPL = dsPL.get(pos);
                        selectPL.setMaPL(pl.getMaPL());
                        selectPL.setTenPL(pl.getTenPL());
                        pos=-1;
                        Toast.makeText(getApplicationContext(), "Cập Nhật Thành Công", Toast.LENGTH_LONG).show();
                    }
                    else {
                        dsPL.add(pl);
                        Toast.makeText(getApplicationContext(), "Thêm Phân Loại Thành Công", Toast.LENGTH_LONG).show();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

        }
    }


}
