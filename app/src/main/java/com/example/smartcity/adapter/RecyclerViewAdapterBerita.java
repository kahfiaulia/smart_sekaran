package com.example.smartcity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.LihatBerita;
import com.example.smartcity.R;
import com.example.smartcity.UpdateBerita;
import com.example.smartcity.models.data_berita;

import java.util.ArrayList;

//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
public class RecyclerViewAdapterBerita extends RecyclerView.Adapter<RecyclerViewAdapterBerita.ViewHolder>{


    //Deklarasi Variable
    private ArrayList<data_berita> listBerita;
    private Context context;

    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(data_berita data, int position);
    }

    //Deklarasi objek dari Interfece
    dataListener listener;

    //Membuat Konstruktor, untuk menerima input dari Database
    public RecyclerViewAdapterBerita(ArrayList<data_berita> listBerita, Context context) {
        this.listBerita = listBerita;
        this.context = context;
        listener = (LihatBerita)context;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView JudulBerita, IsiBerita;
        private LinearLayout ListItem;

        ViewHolder(View itemView) {
            super(itemView);
            //Menginisialisasi View-View yang terpasang pada layout RecyclerView kita
            JudulBerita = itemView.findViewById(R.id.judul_berita);
            IsiBerita = itemView.findViewById(R.id.isi_berita);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design_berita, parent, false);
        return new ViewHolder(V);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Mengambil Nilai/Value yenag terdapat pada RecyclerView berdasarkan Posisi Tertentu
        final String JudulBerita = listBerita.get(position).getJudulBerita();
        final String IsiBerita = listBerita.get(position).getIsiBerita();

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.JudulBerita.setText("Judul Berita: "+JudulBerita);
        holder.IsiBerita.setText("Isi Berita: "+IsiBerita);

        //Menampilkan Menu Update dan Delete saat user melakukan long klik pada salah satu item
        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                /*
                                  Berpindah Activity pada halaman layout updateData
                                  dan mengambil data pada listMahasiswa, berdasarkan posisinya
                                  untuk dikirim pada activity updateData
                                 */
                                Bundle bundle = new Bundle();
                                bundle.putString("dataJudulBerita", listBerita.get(position).getJudulBerita());
                                bundle.putString("dataIsiBerita", listBerita.get(position).getIsiBerita());
                                bundle.putString("getPrimaryKey", listBerita.get(position).getKey());
                                Intent intent = new Intent(view.getContext(), UpdateBerita.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                                listener.onDeleteData(listBerita.get(position), position);
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listBerita.size();
    }

}