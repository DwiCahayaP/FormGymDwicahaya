package com.example.formgym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.formgym.R;
import com.example.formgym.database.memberEntity;

public class memberAdapter extends RecyclerView.Adapter<memberAdapter.ViewAdapter> {

    private List<memberEntity> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public memberAdapter(Context context, List<memberEntity> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.fullname.setText(list.get(position).fullname);
        holder.alamat.setText(list.get(position).alamat);
        holder.idanggota.setText(""+list.get(position).idanggota);
        holder.gender.setText(list.get(position).jeniskelamin);
        holder.kategori.setText(list.get(position).fasilitas);
        holder.age.setText(""+list.get(position).umur);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView fullname, alamat, idanggota, gender, kategori, age;

        public  ViewAdapter(@NonNull View itemView){
            super(itemView);
            fullname = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            idanggota = itemView.findViewById(R.id.idanggota);
            gender = itemView.findViewById(R.id.JK);
            kategori = itemView.findViewById(R.id.jenisfasilitas);
            age =itemView.findViewById(R.id.jumlahage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });

        }

    }
}
