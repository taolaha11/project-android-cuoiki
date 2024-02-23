package com.mobile.quanlybanhangonline.activity.admin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.adapter.ProductAdapter;
import com.mobile.quanlybanhangonline.dbhandler.CategoryHandler;
import com.mobile.quanlybanhangonline.dbhandler.ProductHandler;
import com.mobile.quanlybanhangonline.model.Product;

import java.util.ArrayList;

public class ProductListActicity extends Activity {
    Button btnCreateProduct;
    ImageButton btnBackToMenu;
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ProductAdapter productAdapter;
    Cursor cursor;
    ProductHandler productHandler;
    CategoryHandler categoryHandler;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        lv = (ListView) findViewById(R.id.lv_Product);
        btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
        btnBackToMenu = (ImageButton) findViewById(R.id.btnBackToMenu);
        productHandler = new ProductHandler(this);
        categoryHandler = new CategoryHandler(this);
        display();

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ProductListActicity.this, ProductEditActivity.class);
                intent.putExtra("Edit", productAdapter.getItem(arg2));
                startActivity(intent);
            }
        });

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), ProductCreateActivity.class);
                startActivity(i);
            }
        });

    }

    public void display() {
        cursor = productHandler.getAllProduct();
        while (cursor.moveToNext()){
            productArrayList.add(new Product(cursor.getInt(0), categoryHandler.getCategoryNameById(cursor.getInt(1)), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getBlob(5)));
        }
        productAdapter = new ProductAdapter(this, R.layout.item_product, productArrayList);
        lv.setAdapter(productAdapter);
    }
}
