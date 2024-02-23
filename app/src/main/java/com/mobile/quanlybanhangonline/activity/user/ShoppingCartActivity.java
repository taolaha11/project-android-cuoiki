package com.mobile.quanlybanhangonline.activity.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.quanlybanhangonline.activity.MainActivity;
import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.adapter.CartAdapter;
import com.mobile.quanlybanhangonline.dbhandler.BillDetailHandler;
import com.mobile.quanlybanhangonline.dbhandler.BillHandler;
import com.mobile.quanlybanhangonline.dbhandler.ProductHandler;
import com.mobile.quanlybanhangonline.model.Bill;
import com.mobile.quanlybanhangonline.model.BillDetail;
import com.mobile.quanlybanhangonline.model.Cart;
import com.mobile.quanlybanhangonline.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView lv;
    ProductHandler productHandler;
    BillHandler billHandler;
    BillDetailHandler billDetailHandler;


    List<Cart> cartList;
    Button buttonthanhtoan, btnUserMenu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        lv = (ListView) findViewById(R.id.listviewgiohang);
        buttonthanhtoan = (Button) findViewById(R.id.buttonthanhtoan);
        btnUserMenu = (Button) findViewById(R.id.btnUserMenu);
        btnUserMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MenuUserActivity.class);
                startActivity(i);
                finish();
            }
        });
        cartList = new ArrayList<>();

        if (CartActivity.listItem != null) {
            ProductHandler productHandler = new ProductHandler(this);
            for (int i = 0; i < CartActivity.listItem.size(); i++) {
                Product product = productHandler.findById(CartActivity.listItem.get(i).getProduct_id());
                Cart cart = new Cart();
                cart.setId(product.getId());
                cart.setHinhAnh(product.getImage());
                cart.setTenSP(product.getName());
                cart.setGiaTien(product.getPrice());
                cart.setSoLuong(CartActivity.listItem.get(i).getQuantity());
                cartList.add(cart);
            }
        }
        CartAdapter cartAdapter = new CartAdapter(this, R.layout.user_item_cart_product, cartList);
        lv.setAdapter(cartAdapter);
        // Thanh toan toan bo san pham
        buttonthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CartActivity.listItem == null || CartActivity.listItem.size() == 0) {

                    Toast.makeText(ShoppingCartActivity.this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();

                } else {

                    List<Product> productList = new ArrayList<>();
                    List<Integer> quantityList = new ArrayList<>();

                    productHandler = new ProductHandler(ShoppingCartActivity.this);
                    billHandler = new BillHandler(ShoppingCartActivity.this);
                    billDetailHandler = new BillDetailHandler(ShoppingCartActivity.this);

                    int total_price = 0;
                    for (int i = 0; i < CartActivity.listItem.size(); i++) {
                        Product product = productHandler.findById(CartActivity.listItem.get(i).getProduct_id());
                        productList.add(product);

                        int quantity = CartActivity.listItem.get(i).getQuantity();
                        total_price += product.getPrice() * quantity;

                        quantityList.add(quantity);
                    }

                    Bill bill = new Bill();
                    bill.setUser_id(MainActivity.user_id);
                    bill.setTotal_price(total_price * 1.1);
                    bill.setDescription("Thanh toán cả giỏ hàng");

                    Date now = new Date();
                    bill.setDate_created(now.toString());


                    if (billHandler.insertBill(bill) == 1) {
                        int bill_id = billHandler.getBillIdNew();
                        // Nếu thêm thành công thì tiến hành thêm chi tiết sản phẩm
                        Toast.makeText(ShoppingCartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < productList.size(); i++) {
                            BillDetail billDetail = new BillDetail();
                            billDetail.setBill_id(bill_id);
                            billDetail.setProduct_name(productList.get(i).getName());
                            int quantity = quantityList.get(i);
                            billDetail.setQuantity(quantity);
                            billDetail.setPrice(productList.get(i).getPrice());
                            billDetailHandler.insertBillDetail(billDetail);

                            productHandler.editQuantity(productList.get(i).getId(), quantity);
                        }
                        CartActivity.listItem.clear();
                        cartList.clear();
                        cartAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ShoppingCartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}