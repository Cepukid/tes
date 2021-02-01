package com.example.aplikasites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

import com.example.aplikasites.item.barang;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoadNotesCallback {
    private RecyclerView rvbarang;
    private AdapterBarang adapter;
    private Button tambahbarang;
    private baranghelper barangHelper;
    private static final String EXTRA_STATE = "EXTRA_STATE";
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
        barangHelper = baranghelper.getInstance(getApplicationContext());
        barangHelper.open();
        if (savedInstanceState == null) {
            new LoadNotesAsync(barangHelper, this).execute();
        } else {
            ArrayList<barang> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListBarang(list);
            }
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListBarang());
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        barangHelper.close();
    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(ArrayList<barang> barangs) {
        if (barangs.size() > 0) {
            adapter.setListBarang(barangs);
        } else {
            adapter.setListBarang(new ArrayList<barang>());
            showSnackbarMessage("Tidak ada data saat ini");
        }
    }
    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<barang>> {
        private final WeakReference<baranghelper> weakNoteHelper;
        private final WeakReference<LoadNotesCallback> weakCallback;
        private LoadNotesAsync(baranghelper noteHelper, LoadNotesCallback callback) {
            weakNoteHelper = new WeakReference<>(noteHelper);
            weakCallback = new WeakReference<>(callback);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }
        @Override
        protected ArrayList<barang> doInBackground(Void... voids) {
            Cursor dataCursor = weakNoteHelper.get().queryAllbarang();
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }
        @Override
        protected void onPostExecute(ArrayList<barang> notes) {
            super.onPostExecute(notes);
            weakCallback.get().postExecute(notes);
        }
    }
}
interface LoadNotesCallback {
    void preExecute();
    void postExecute(ArrayList<barang> notes);
}