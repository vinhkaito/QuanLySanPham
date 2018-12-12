package com.quanlysanpham.sanpham;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.quanlysanpham.database.Database;
import com.quanlysanpham.model.PhanLoai;
import com.quanlysanpham.model.SanPham;
import com.quanlysanpham.phanloai.DanhSachPhanLoai;
import com.quanlysanpham.quanlysanpham.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ControlsSanPham extends AppCompatActivity {
    TextView txtTitleSP;
    EditText txtMaSP , txtTenSP , txtGiaSP , txtXuatXuSP ;
    Button btnChonhinh , btnTienHanhSP;
    ImageView ImgChonHinh;
    public static Spinner spnPhanloai;

    int function = -1;
    final int them = 1;
    final int sua = 0;
    Intent intent;

    final static int ChonHinh = 19;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls_san_pham);
        addControls();
        addEvents();
    }


    private void addEvents() {
        btnTienHanhSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (function == them)
                {
                    int MaSP = Integer.parseInt(txtMaSP.getText().toString());
                    String TenSP = txtTenSP.getText().toString();
                    String GiaSP = txtGiaSP.getText().toString();
                    String XuatXuSP = txtXuatXuSP.getText().toString();
                    byte[] HinhSP = getByteArrayFromImageView(ImgChonHinh);


                    SanPham sp = new SanPham(MaSP,TenSP,(PhanLoai)spnPhanloai.getSelectedItem(),HinhSP,GiaSP,XuatXuSP);
                    Intent intent = getIntent();
                    intent.putExtra("SP_Them",sp);
                    setResult(DanhSachSanPham.ThemSanPhamResultCode,intent);
                    finish();
                }
            }
        });

        btnChonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, ChonHinh);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ChonHinh){
            try {
                Uri imageUri = data.getData();
                InputStream is = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                ImgChonHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



    private void addControls() {
        txtTitleSP = findViewById(R.id.txtTitleSP);
        spnPhanloai = findViewById(R.id.spnPhanloai);
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

        ArrayList<PhanLoai> dsPL = DanhSachPhanLoai.dsPL;
        ArrayAdapter<PhanLoai> adapter = new ArrayAdapter<PhanLoai>(ControlsSanPham.this, android.R.layout.simple_spinner_dropdown_item, dsPL);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPhanloai.setAdapter(adapter);
    }

    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}
