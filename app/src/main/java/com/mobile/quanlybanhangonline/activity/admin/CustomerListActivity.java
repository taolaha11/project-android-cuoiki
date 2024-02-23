package com.mobile.quanlybanhangonline.activity.admin;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.adapter.CustomerAdapter;
import com.mobile.quanlybanhangonline.data.DBManager;
import com.mobile.quanlybanhangonline.dbhandler.CustomerHandler;
import com.mobile.quanlybanhangonline.model.Customer;

import java.util.ArrayList;

public class CustomerListActivity extends Activity {
    Cursor cursor;
    ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    CustomerAdapter customerAdapter;
    ListView lv;
    CustomerHandler customerHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);
        customerHandler = new CustomerHandler(this);

        lv = (ListView) findViewById(R.id.lv_Customer);
        display();
    }
    public void display(){
        cursor = customerHandler.getAllCustomer();

        if(customerAdapter ==null){
            while (cursor.moveToNext()){
                customerArrayList.add(new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            }
            customerAdapter = new CustomerAdapter(this, R.layout.activity_customer_list, customerArrayList);
            lv.setAdapter(customerAdapter);
        }else{
            customerAdapter.notifyDataSetChanged();
        }


    }
}
