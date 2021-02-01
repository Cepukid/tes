package com.example.aplikasites.item;

public class stok {
    private int id;
    private String idbarang;
    private String totalbarang;
    private String jenis_stok;
    public stok(int id, String idbarang, String totalbarang, String jenis_stok) {
        this.id = id;
        this.idbarang = idbarang;
        this.totalbarang = totalbarang;
        this.jenis_stok = jenis_stok;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getTotalbarang() {
        return totalbarang;
    }

    public void setTotalbarang(String totalbarang) {
        this.totalbarang = totalbarang;
    }

    public String getJenis_stok() {
        return jenis_stok;
    }

    public void setJenis_stok(String jenis_stok) {
        this.jenis_stok = jenis_stok;
    }
}
