package com.mobile.quanlybanhangonline.dbhandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.mobile.quanlybanhangonline.data.DBManager;
import com.mobile.quanlybanhangonline.model.BillStatistic;

public class BillStatisticHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;

    DBManager dbManager;
    private Context context;

    public BillStatisticHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }

    public Cursor getAllBillStatistic() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM bills;", null);
        return cursor;
    }

    public Cursor getDetailBillByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM bill_detail WHERE bill_id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);

        return cursor;
    }

    public Cursor getBillByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM bills WHERE id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);

        return cursor;
    }

    public Cursor getBillByUserID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM bills WHERE user_id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);

        return cursor;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
 }
