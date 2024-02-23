package com.mobile.quanlybanhangonline.activity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mobile.quanlybanhangonline.activity.MainActivity;
import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.adapter.MenuUserAdapter;
import com.mobile.quanlybanhangonline.dbhandler.CategoryHandler;
import com.mobile.quanlybanhangonline.model.Category;

import java.util.ArrayList;
import java.util.List;

public class MenuUserActivity extends AppCompatActivity {

    ListView lv;
    Button btnGioHang, btnHoaDon, btnDangXuat;
    List<Category> categoryList;
    MenuUserAdapter menuUserAdapter;
    CategoryHandler categoryHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);
        lv = (ListView) findViewById(R.id.lv_user_category);
        btnGioHang = (Button) findViewById(R.id.btnGioHang);
        btnHoaDon = (Button) findViewById(R.id.btnHoaDon);
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(b);
                finish();
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BillStatisticUserActivity.class);
                startActivity(intent);
            }
        });
        categoryList = new ArrayList<>();
        display();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), ProductListUserActivity.class);
                Category category = (Category) menuUserAdapter.getItem(i);
                intent.putExtra("category_id", category.getIdCategory());
                intent.putExtra("category_name", category.getNameCategory());
                startActivity(intent);
            }
        });
    }


    public void display() {
        categoryHandler = new CategoryHandler(this);
        categoryList = categoryHandler.getAllCategoriesWithIdCategory();
        menuUserAdapter = new MenuUserAdapter(this, R.layout.user_item_category, categoryList);
        lv.setAdapter(menuUserAdapter);
    }
}