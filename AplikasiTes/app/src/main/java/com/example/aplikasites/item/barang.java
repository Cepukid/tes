package com.example.aplikasites.item;

import android.os.Parcel;
import android.os.Parcelable;

public class barang implements Parcelable {
    private int id;
    private String kodebarang;
    private String namabarang;
    private String gambarbarang;
    public barang() {
    }
    public barang(int id, String kodebarang, String namabarang, String gambarbarang) {
        this.id = id;
        this.kodebarang = kodebarang;
        this.namabarang = namabarang;
        this.gambarbarang = gambarbarang;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodebarang() {
        return kodebarang;
    }

    public void setKodebarang(String kodebarang) {
        this.kodebarang = kodebarang;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getGambarbarang() {
        return gambarbarang;
    }

    public void setGambarbarang(String gambarbarang) {
        this.gambarbarang = gambarbarang;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kodebarang);
        dest.writeString(this.namabarang);
        dest.writeString(this.gambarbarang);
    }

    private barang(Parcel in) {
        this.id = in.readInt();
        this.kodebarang = in.readString();
        this.namabarang = in.readString();
        this.gambarbarang = in.readString();
    }
    public static final Parcelable.Creator<barang> CREATOR = new Parcelable.Creator<barang>() {
        @Override
        public barang createFromParcel(Parcel source) {
            return new barang(source);
        }
        @Override
        public barang[] newArray(int size) {
            return new barang[size];
        }
    };
}
