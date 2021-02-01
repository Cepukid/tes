package com.example.aplikasites;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aplikasites.item.barang;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.BarangViewHolder> {
    private ArrayList<barang> listBarang = new ArrayList<>();
    private Activity activity;
    public AdapterBarang(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<barang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(ArrayList<barang> listNotes) {
        if (listNotes.size() > 0) {
            this.listBarang.clear();
        }
        this.listBarang.addAll(listNotes);
        notifyDataSetChanged();
    }
    public void addItem(barang brg) {
        this.listBarang.add(brg);
        notifyItemInserted(listBarang.size() - 1);
    }
    public void updateItem(int position, barang brg) {
        this.listBarang.set(position, brg);
        notifyItemChanged(position, brg);
    }
    public void removeItem(int position) {
        this.listBarang.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listBarang.size());
    }
    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new BarangViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        holder.tvnama.setText(listBarang.get(position).getNamabarang());
        holder.tvkode.setText(listBarang.get(position).getKodebarang());
        Glide.with(holder.itemView.getContext())
                .load(listBarang.get(position).getGambarbarang())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.tvgambar);
        holder.cvbarang.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position1) {
                Intent intent = new Intent(activity, TambahBarang.class);
                intent.putExtra(TambahBarang.EXTRA_POSITION, position1);
                intent.putExtra(TambahBarang.EXTRA_BARANG, listBarang.get(position1));
                activity.startActivityForResult(intent, TambahBarang.REQUEST_UPDATE);
            }
        }));
    }
    @Override
    public int getItemCount() {
        return listBarang.size();
    }
    static class BarangViewHolder extends RecyclerView.ViewHolder {
        TextView tvkode, tvnama;
        CardView cvbarang;
        ImageView tvgambar;

        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvkode= itemView.findViewById(R.id.codebarang);
            tvnama= itemView.findViewById(R.id.namabarang);
            tvgambar= itemView.findViewById(R.id.gambarbarang);
            cvbarang=itemView.findViewById(R.id.cv_item_barang);
        }
    }
}
