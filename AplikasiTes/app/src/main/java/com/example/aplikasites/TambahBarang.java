package com.example.aplikasites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasites.item.barang;

import static com.example.aplikasites.DatabaseContract.BarangColumns.GambarBarang;
import static com.example.aplikasites.DatabaseContract.BarangColumns.KodeBarang;
import static com.example.aplikasites.DatabaseContract.BarangColumns.NamaBarang;

public class TambahBarang extends AppCompatActivity implements View.OnClickListener {
    private EditText edtkode,edtnama, edturlgambar;
    private boolean isEdit = false;
    private barang Barang;
    private int position;
    private baranghelper barangHelper;
    public static final String EXTRA_BARANG = "extra_barang";
    public static final String EXTRA_POSITION = "extra_position";
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int REQUEST_UPDATE = 200;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);
        edtkode = findViewById(R.id.edt_Kode);
        edtnama = findViewById(R.id.edt_Nama);
        edturlgambar = findViewById(R.id.edt_Gambar);
        Button btnSubmit = findViewById(R.id.btn_submit);
        barangHelper = baranghelper.getInstance(getApplicationContext());
        barangHelper.open();
        Barang = getIntent().getParcelableExtra(EXTRA_BARANG);
        if (Barang != null) {
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;
        } else {
            Barang = new barang();
        }
        String actionBarTitle;
        String btnTitle;
        if (isEdit) {
            actionBarTitle = "Ubah";
            btnTitle = "Update";
            if (Barang != null) {
                edtnama.setText(Barang.getNamabarang());
                edtkode.setText(Barang.getKodebarang());
                edturlgambar.setText(Barang.getGambarbarang());
            }
        } else {
            actionBarTitle = "Tambah";
            btnTitle = "Simpan";
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnSubmit.setText(btnTitle);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit) {
            String nama = edtnama.getText().toString().trim();
            String kode = edtkode.getText().toString().trim();
            String urlgambar = edturlgambar.getText().toString().trim();
            if (TextUtils.isEmpty(nama)) {
                edtnama.setError("Field can not be blank");
                return;
            }
            if (TextUtils.isEmpty(kode)) {
                edtkode.setError("Field can not be blank");
                return;
            }
            Barang.setNamabarang(nama);
            Barang.setKodebarang(kode);
            Barang.setGambarbarang(urlgambar);
            Intent intent = new Intent();
            intent.putExtra(EXTRA_BARANG, Barang);
            intent.putExtra(EXTRA_POSITION, position);

            ContentValues values = new ContentValues();
            values.put(KodeBarang, kode);
            values.put(NamaBarang, nama);
            values.put(GambarBarang, urlgambar);
            if (isEdit) {
                long result = baranghelper.updatebarang(String.valueOf(Barang.getId()), values);
                if (result > 0) {
                    setResult(RESULT_UPDATE, intent);
                    finish();
                } else {
                    Toast.makeText(TambahBarang.this, "Gagal mengupdate data", Toast.LENGTH_SHORT).show();
                }
            } else {
                long result = baranghelper.insertbarang(values);
                if (result > 0) {
                    Barang.setId((int) result);
                    setResult(RESULT_ADD, intent);
                    finish();
                } else {
                    Toast.makeText(TambahBarang.this, "Gagal menambah data", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isEdit) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            showAlertDialog(ALERT_DIALOG_DELETE);
        } else if (id == android.R.id.home) {
            showAlertDialog(ALERT_DIALOG_CLOSE);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }
    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
            dialogTitle = "Hapus Barang";
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, id) -> {
                    if (isDialogClose) {
                        finish();
                    } else {
                        long result = baranghelper.deletebarangById(String.valueOf(Barang.getId()));
                        if (result > 0) {
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_POSITION, position);
                            setResult(RESULT_DELETE, intent);
                            finish();
                        } else {
                            Toast.makeText(TambahBarang.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Tidak", (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}