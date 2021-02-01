package com.example.aplikasites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbbarangapps";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_BARANG = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_Barang,
            DatabaseContract.BarangColumns._ID,
            DatabaseContract.BarangColumns.KodeBarang,
            DatabaseContract.BarangColumns.NamaBarang,
            DatabaseContract.BarangColumns.GambarBarang
    );
    private static final String SQL_CREATE_TABLE_STOK = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_Barang,
            DatabaseContract.StokColumns._ID,
            DatabaseContract.StokColumns.idBarang,
            DatabaseContract.StokColumns.TotalBarang,
            DatabaseContract.StokColumns.JenisStok
    );
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_BARANG);
        db.execSQL(SQL_CREATE_TABLE_STOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_Barang);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_Stok);
        onCreate(db);
    }
}
