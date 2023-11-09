package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    private Button DataPenduduk, TambahDataPenduduk, Berita, TambahBerita, Laporan, TambahLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        DataPenduduk = findViewById(R.id.btnDataPenduduk);
        TambahDataPenduduk = findViewById(R.id.btnTambahDataPenduduk);
        Berita = findViewById(R.id.btnBerita);
        TambahBerita = findViewById(R.id.btnTambahBerita);
        Laporan = findViewById(R.id.btnLaporan);
        TambahLaporan = findViewById(R.id.btnTambahLaporan);

        DataPenduduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, LihatDataPenduduk.class));
                }
    });

        TambahDataPenduduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, DataPenduduk.class));
            }
        });

        Berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, LihatBerita.class));
            }
        });

        TambahBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, Berita.class));
            }
        });

        Laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, LihatLaporan.class));
            }
        });

        TambahLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, Laporan.class));
            }
        });
}
}