package com.mobile.quanlybanhangonline.activity.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.mobile.quanlybanhangonline.activity.MainActivity;
import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.adapter.BillStatisticAdapter;
import com.mobile.quanlybanhangonline.dbhandler.BillHandler;
import com.mobile.quanlybanhangonline.dbhandler.BillStatisticHandler;
import com.mobile.quanlybanhangonline.dbhandler.CustomerHandler;
import com.mobile.quanlybanhangonline.model.Bill;
import com.mobile.quanlybanhangonline.model.BillStatistic;

import java.util.ArrayList;
import java.util.List;

public class BillStatisticUserActivity extends Activity {
    ArrayList<BillStatistic> arrayBillStatistic = new ArrayList<BillStatistic>();
    BillStatisticAdapter adapter;
    Cursor cursor,cursorBill;
    BillStatisticHandler billStatisticHandler;
    com.mobile.quanlybanhangonline.dbhandler.CustomerHandler CustomerHandler;
    Context context;
    ListView lv;
    BillHandler billHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_statistic_user);
        lv = (ListView)findViewById(R.id.lvBill);
        billStatisticHandler = new BillStatisticHandler(this);
        CustomerHandler = new CustomerHandler(this);
        display();
        billHandler = new BillHandler(this);
        List<Bill> billIdList = billHandler.getAllBillByUserId(MainActivity.user_id);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor = billStatisticHandler.getDetailBillByID(billIdList.get(position).getId());
                AlertDialog.Builder builder = new AlertDialog.Builder(BillStatisticUserActivity.this); // sử dụng context của Activity hiện tại
                builder.setTitle("Chi tiết hóa đơn");
                if (cursor != null && cursor.getCount() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (cursor.moveToNext()) {
                         String idBill = cursor.getString(cursor.getColumnIndex("id"));
                        cursorBill = billStatisticHandler.getBillByID(cursor.getColumnIndex("bill_id"));
                        int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                        String productName = cursor.getString(cursor.getColumnIndex("product_name"));
                        float price = cursor.getFloat(cursor.getColumnIndex("price"));
                        stringBuilder.append("ID hóa đơn: " + idBill + "\n"
                                + "Tên sản phẩm: " + productName + "\n"
                                + "Số lượng: " + quantity + "\n"
                                + "Giá: " + price + "VND" + "\n\n");
                    }
                    builder.setMessage(stringBuilder.toString());
                } else {
                    builder.setMessage("Không tìm thấy hóa đơn nào.");
                }

                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void display(){
        CustomerHandler = new CustomerHandler(this);
//        CustomerHandler.fakeCustomerRecords();
//        CustomerHandler.fakeBills();

        cursor = billStatisticHandler.getBillByUserID(MainActivity.user_id);
        if (adapter==null){
            while (cursor.moveToNext()){
                arrayBillStatistic.add(new BillStatistic(cursor.getInt(0),CustomerHandler.getUserNameById(cursor.getInt(1)),cursor.getFloat(2), cursor.getString(3), cursor.getString(4)));
            }
            adapter = new BillStatisticAdapter(this, R.layout.item_bill_list, arrayBillStatistic);
            lv.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }
}
