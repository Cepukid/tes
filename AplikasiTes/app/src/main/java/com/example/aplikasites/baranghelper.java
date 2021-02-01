package com.example.aplikasites;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.SQLException;
import static android.provider.BaseColumns._ID;
import android.content.ContentValues;

public class baranghelper {
    private static final String DATABASE_Barang = DatabaseContract.TABLE_Barang;
    private static final String DATABASE_Stok = DatabaseContract.TABLE_Stok;
    private static DatabaseHelper dataBaseHelper;
    private static baranghelper INSTANCE;
    private static SQLiteDatabase database;
    private baranghelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }
    public static baranghelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new baranghelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }
    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }
    public Cursor queryAllbarang() {
        return database.query(
                DATABASE_Barang,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }
    public Cursor queryAllstok() {
        return database.query(
                DATABASE_Stok,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }
    public long insertbarang(ContentValues values) {
        return database.insert(DATABASE_Barang, null, values);
    }
    public long insertstok(ContentValues values) {
        return database.insert(DATABASE_Stok, null, values);
    }
    public int updatebarang(String id, ContentValues values) {
        return database.update(DATABASE_Barang, values, _ID + " = ?", new String[]{id});
    }
    public int updatestok(String id, ContentValues values) {
        return database.update(DATABASE_Stok, values, _ID + " = ?", new String[]{id});
    }
    public int deletebarangById(String id) {
        return database.delete(DATABASE_Barang, _ID + " = ?", new String[]{id});
    }
    public int deletestokById(String id) {
        return database.delete(DATABASE_Stok, _ID + " = ?", new String[]{id});
    }

}
