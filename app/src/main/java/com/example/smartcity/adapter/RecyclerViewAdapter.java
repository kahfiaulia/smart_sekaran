package com.example.smartcity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.UpdateDataPenduduk;
import com.example.smartcity.models.data_penduduk;
import com.example.smartcity.LihatDataPenduduk;

import java.util.ArrayList;

//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    //Deklarasi Variable
    private ArrayList<data_penduduk> listPenduduk;
    private Context context;

    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(data_penduduk data, int position);
    }

    //Deklarasi objek dari Interfece
    dataListener listener;

    //Membuat Konstruktor, untuk menerima input dari Database
    public RecyclerViewAdapter(ArrayList<data_penduduk> listPenduduk, Context context) {
        this.listPenduduk = listPenduduk;
        this.context = context;
        listener = (LihatDataPenduduk)context;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView NIK, KK, Nama, TempatLahir, TanggalLahir, NamaAyah, NamaIbu, Pekerjaan, Agama, Goldar, Alamat, NoHP;
        private LinearLayout ListItem;

        ViewHolder(View itemView) {
            super(itemView);
            //Menginisialisasi View-View yang terpasang pada layout RecyclerView kita
            NIK = itemView.findViewById(R.id.nik);
            KK = itemView.findViewById(R.id.kk);
            Nama = itemView.findViewById(R.id.nama);
            TempatLahir = itemView.findViewById(R.id.tempatLahir);
            TanggalLahir = itemView.findViewById(R.id.tanggalLahir);
            NamaAyah = itemView.findViewById(R.id.namaAyah);
            NamaIbu = itemView.findViewById(R.id.namaIbu);
            Pekerjaan = itemView.findViewById(R.id.pekerjaan);
            Agama = itemView.findViewById(R.id.agama);
            Goldar = itemView.findViewById(R.id.goldar);
            Alamat = itemView.findViewById(R.id.alamat);
            NoHP = itemView.findViewById(R.id.noHP);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Mengambil Nilai/Value yenag terdapat pada RecyclerView berdasarkan Posisi Tertentu
        final String NIK = listPenduduk.get(position).getNIK();
        final String KK = listPenduduk.get(position).getKK();
        final String Nama = listPenduduk.get(position).getNama();
        final String TempatLahir = listPenduduk.get(position).getTempatLahir();
        final String TanggalLahir = listPenduduk.get(position).getTanggalLahir();
        final String NamaAyah = listPenduduk.get(position).getNamaAyah();
        final String NamaIbu = listPenduduk.get(position).getNamaIbu();
        final String Pekerjaan = listPenduduk.get(position).getPekerjaan();
        final String Agama = listPenduduk.get(position).getAgama();
        final String Goldar = listPenduduk.get(position).getGoldar();
        final String Alamat = listPenduduk.get(position).getAlamat();
        final String NoHP = listPenduduk.get(position).getNoHP();

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.NIK.setText("NIK/KTP/SIM: "+NIK);
        holder.KK.setText("No. KK: "+KK);
        holder.Nama.setText("Nama: "+Nama);
        holder.TempatLahir.setText("Tempat Lahir: "+TempatLahir);
        holder.TanggalLahir.setText("Tanggal Lahir: "+TanggalLahir);
        holder.NamaAyah.setText("Nama Ayah: "+NamaAyah);
        holder.NamaIbu.setText("Nama Ibu: "+NamaIbu);
        holder.Pekerjaan.setText("Pekerjaan: "+Pekerjaan);
        holder.Agama.setText("Agama: "+Agama);
        holder.Goldar.setText("Goldar: "+Goldar);
        holder.Alamat.setText("Alamat: "+Alamat);
        holder.NoHP.setText("NoHP: "+NoHP);

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
                                bundle.putString("dataNIK", listPenduduk.get(position).getNIK());
                                bundle.putString("dataKK", listPenduduk.get(position).getKK());
                                bundle.putString("dataNama", listPenduduk.get(position).getNama());
                                bundle.putString("dataTempatLahir", listPenduduk.get(position).getTempatLahir());
                                bundle.putString("dataTanggalLahir", listPenduduk.get(position).getTanggalLahir());
                                bundle.putString("dataNamaAyah", listPenduduk.get(position).getNamaAyah());
                                bundle.putString("dataNamaIbu", listPenduduk.get(position).getNamaIbu());
                                bundle.putString("dataPekerjaan", listPenduduk.get(position).getPekerjaan());
                                bundle.putString("dataAgama", listPenduduk.get(position).getAgama());
                                bundle.putString("dataGoldar", listPenduduk.get(position).getGoldar());
                                bundle.putString("dataAlamat", listPenduduk.get(position).getAlamat());
                                bundle.putString("dataNoHP", listPenduduk.get(position).getNoHP());
                                bundle.putString("getPrimaryKey", listPenduduk.get(position).getKey());
                                Intent intent = new Intent(view.getContext(), UpdateDataPenduduk.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                                listener.onDeleteData(listPenduduk.get(position), position);
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
        return listPenduduk.size();
    }

}