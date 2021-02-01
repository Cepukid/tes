package com.example.aplikasites;

import android.database.Cursor;

import com.example.aplikasites.item.barang;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<barang> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<barang> barangList = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.BarangColumns._ID));
            String kode = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.BarangColumns.KodeBarang));
            String nama = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.BarangColumns.NamaBarang));
            String gambar = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.BarangColumns.GambarBarang));
            barangList.add(new barang(id, kode, nama, gambar));
        }
        return barangList;
    }
}
