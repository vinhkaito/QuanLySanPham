package com.quanlysanpham.phanloai;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.quanlysanpham.adapter.AdapterPhanLoai;
import com.quanlysanpham.database.Database;
import com.quanlysanpham.map.MapsActivity;
import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.quanlysanpham.R;
import com.quanlysanpham.sanpham.DanhSachSanPham;

import java.util.ArrayList;

public class DanhSachPhanLoai extends AppCompatActivity {
    AdapterPhanLoai adapter;
    ListView lvPhanLoai;
    public static ArrayList<PhanLoai> dsPL;
    int pos = -1;

    final String DATABASE_NAME = "QLSPDB.sqlite";
    SQLiteDatabase database;

    final static int CapNhatPhanLoaiRequestCode = 11;
    final static int CapNhatPhanLoaiResultCode = 12;
    final static int ThemPhanLoaiRequestCode = 13;
    final static int ThemPhanLoaiResultCode = 14;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phan_loai);
        addControls();
        addEvents();
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
                startActivityForResult(intent,CapNhatPhanLoaiRequestCode);
                break;
            }
            case R.id.comenuXoa:
            {
//                if (database.delete("PhanLoai" , "MaPhanLoai" + " = ? ", new String[]{String.valueOf(dsPL.get(pos).getMaPL())}) == 1) {
//                    dsPL.remove(pos);
//                    pos = -1;
//                    adapter.notifyDataSetChanged();
//                    Toast.makeText(getApplicationContext(),R.string.delete_success, Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),R.string.delete_fail, Toast.LENGTH_LONG).show();
//                }
//                break;
                DialogInterface.OnClickListener diOnClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if (database.delete("PhanLoai" , "MaPhanLoai" + " = ? ", new String[]{String.valueOf(dsPL.get(pos).getMaPL())}) > 0) {
                                    dsPL.remove(pos);
                                    pos = -1;
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(getApplicationContext(),R.string.delete_success, Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),R.string.delete_fail, Toast.LENGTH_LONG).show();
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(getApplicationContext(),R.string.delete_fail, Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachPhanLoai.this);
                builder.setMessage(R.string.delete_warning).setPositiveButton(R.string.delete_yes,diOnClickListener).setNegativeButton(R.string.delete_no,diOnClickListener).show();
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
                Intent intent = new Intent(DanhSachPhanLoai.this,ControlsPhanLoai.class);
                startActivityForResult(intent,ThemPhanLoaiRequestCode);
                break;
            }
            case R.id.opmenutDSSP:
            {
                Intent intent = new Intent(DanhSachPhanLoai.this,DanhSachSanPham.class);
                startActivity(intent);
                break;
            }
            case R.id.opmenuMap:
            {
                Intent intent = new Intent(DanhSachPhanLoai.this,MapsActivity.class);
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

//    END OPTION MENU



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CapNhatPhanLoaiRequestCode && resultCode == CapNhatPhanLoaiResultCode && data.hasExtra("PL_CapNhat")) {
            PhanLoai pl = (PhanLoai) data.getSerializableExtra("PL_CapNhat");
            ContentValues values = new ContentValues();
            values.put("TenPhanLoai", pl.getTenPL());

            if (database.update("PhanLoai", values, "MaPhanLoai" + "= ?", new String[]{String.valueOf(pl.getMaPL())}) > 0) {
                dsPL.set(dsPL.indexOf(dsPL.get(pos)), pl);
                Toast.makeText(getApplicationContext(), R.string.update_success, Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), R.string.update_fail, Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == ThemPhanLoaiRequestCode && resultCode == ThemPhanLoaiResultCode && data.hasExtra("PL_Them")) {
            PhanLoai pl = (PhanLoai) data.getSerializableExtra("PL_Them");
            ContentValues values = new ContentValues();
            values.put("TenPhanLoai", pl.getTenPL());

            if (database.insert("PhanLoai",null,values) != -1) {
                Cursor cursor = database.rawQuery("SELECT * FROM PhanLoai", null);
                cursor.moveToLast();
                pl.setMaPL(cursor.getInt(0));
                dsPL.add(pl);
                Toast.makeText(getApplicationContext(), R.string.add_success, Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(),R.string.add_fail, Toast.LENGTH_LONG).show();
            }
        }
    }
}
