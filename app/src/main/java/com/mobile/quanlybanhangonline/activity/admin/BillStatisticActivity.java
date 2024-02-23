package com.mobile.quanlybanhangonline.activity.admin;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.adapter.BillStatisticAdapter;
import com.mobile.quanlybanhangonline.dbhandler.BillStatisticHandler;
import com.mobile.quanlybanhangonline.dbhandler.CustomerHandler;
import com.mobile.quanlybanhangonline.model.BillStatistic;

import java.util.ArrayList;

public class BillStatisticActivity extends Activity {
    ArrayList<BillStatistic> arrayBillStatistic = new ArrayList<BillStatistic>();
    BillStatisticAdapter adapter;
    Cursor cursor,cursorBill;
    BillStatisticHandler billStatisticHandler;
    CustomerHandler CustomerHandler;
    Context context;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_statistic);
        lv = (ListView)findViewById(R.id.lvBill);
        billStatisticHandler = new BillStatisticHandler(this);
        CustomerHandler = new CustomerHandler(this);
        display();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor = billStatisticHandler.getDetailBillByID(position + 1);
                AlertDialog.Builder builder = new AlertDialog.Builder(BillStatisticActivity.this); // sử dụng context của Activity hiện tại
                builder.setTitle("Chi tiết hóa đơn");
                if (cursor != null && cursor.getCount() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (cursor.moveToNext()) {
                        String idBill = cursor.getString(cursor.getColumnIndex("id"));
                        cursorBill = billStatisticHandler.getBillByID(cursor.getColumnIndex("bill_id"));
                        int userIdIndex = cursorBill.getColumnIndex("user_id");
                        int userId = cursor.getInt(userIdIndex);
                        String userName = CustomerHandler.getUserNameById(userId);
                        int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                        String productName = cursor.getString(cursor.getColumnIndex("product_name"));
                        float price = cursor.getFloat(cursor.getColumnIndex("price"));
                        stringBuilder.append("ID hóa đơn: " + idBill + "\n"
                                + "Tên sản phẩm: " + productName + "\n"
                                + "Số lượng: " + quantity + "\n"
                                + "Tên người dùng: " + userName + "\n"
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

        cursor = billStatisticHandler.getAllBillStatistic();
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
