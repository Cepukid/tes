package com.example.aplikasites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.aplikasites.item.barang;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvbarang;
    private AdapterBarang adapter;
    private Button tambahbarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Daftar Barang");
        tambahbarang=findViewById(R.id.tambahbarang);
        rvbarang = findViewById(R.id.rv_notes);
        rvbarang.setLayoutManager(new LinearLayoutManager(this));
        rvbarang.setHasFixedSize(true);
        adapter = new AdapterBarang(this);
        rvbarang.setAdapter(adapter);
        tambahbarang.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TambahBarang.class);
            startActivityForResult(intent, TambahBarang.REQUEST_ADD);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == TambahBarang.REQUEST_ADD) {
                if (resultCode == TambahBarang.RESULT_ADD) {
                    barang Barang = data.getParcelableExtra(TambahBarang.EXTRA_BARANG);
                    adapter.addItem(Barang);
                    rvbarang.smoothScrollToPosition(adapter.getItemCount() - 1);
                    showSnackbarMessage("Satu item berhasil ditambahkan");
                }
            }
            else if (requestCode == TambahBarang.REQUEST_UPDATE) {
                if (resultCode == TambahBarang.RESULT_UPDATE) {
                    barang note = data.getParcelableExtra(TambahBarang.EXTRA_BARANG);
                    int position = data.getIntExtra(TambahBarang.EXTRA_POSITION, 0);
                    adapter.updateItem(position, note);
                    rvbarang.smoothScrollToPosition(position);
                    showSnackbarMessage("Satu item berhasil diubah");
                }
                else if (resultCode == TambahBarang.RESULT_DELETE) {
                    int position = data.getIntExtra(TambahBarang.EXTRA_POSITION, 0);
                    adapter.removeItem(position);
                    showSnackbarMessage("Satu item berhasil dihapus");
                }
            }
        }
    }
    private void showSnackbarMessage(String message) {
        Snackbar.make(rvbarang, message, Snackbar.LENGTH_SHORT).show();
    }
}