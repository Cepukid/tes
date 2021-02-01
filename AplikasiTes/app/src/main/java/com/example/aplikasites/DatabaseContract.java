package com.example.aplikasites;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_Barang = "data_barang";
    static String TABLE_Stok = "data_stok";
    static final class BarangColumns implements BaseColumns {

        static String KodeBarang = "KodeBarang";
        static String NamaBarang = "NamaBarang";
        static String GambarBarang = "GambarBarang";
    }
    static final class StokColumns implements BaseColumns {

        static String idBarang = "idBarang";
        static String TotalBarang = "TotalBarang";
        static String JenisStok = "JenisStok";
    }
}
