package com.example.aplikasites.item;

public class barang {
    private int id;
    private String kodebarang;
    private String namabarang;
    private String gambarbarang;
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
}
