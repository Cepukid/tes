package com.example.aplikasites;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
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
        holder.tvTitle.setText(listBarang.get(position).getTitle());
        holder.tvDate.setText(listBarang.get(position).getDate());
        holder.tvDescription.setText(listBarang.get(position).getDescription());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Intent intent = new Intent(activity, NoteAddUpdateActivity.class);
            intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position1);
            intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, listNotes.get(position1));
            activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
        }));
    }
    @Override
    public int getItemCount() {
        return 0;
    }
    static class BarangViewHolder extends RecyclerView.ViewHolder {
        TextView tvkode, tvnama, tvgambar;
        CardView cvbarang;

        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvkode= itemView.findViewById(R.id.codebarang);
            tvnama= itemView.findViewById(R.id.namabarang);
            tvgambar= itemView.findViewById(R.id.gambarbarang);
            cvbarang=itemView.findViewsById(R.id.cv_item_barang);
        }
    }
}
