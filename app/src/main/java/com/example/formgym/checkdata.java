package com.example.formgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class checkdata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdata);

        TextView namamember = findViewById(R.id.checknama);
        TextView idanggota = findViewById(R.id.checkid);
        TextView jenkel = findViewById(R.id.checkJK);
        TextView umur = findViewById(R.id.checkumur);
        TextView fasilitas = findViewById(R.id.checkfasilitas);
        TextView alamat = findViewById(R.id.checkalamat);
        Button backbutton = findViewById(R.id.backbtn);
        Button exitbutton = findViewById(R.id.exitbtn);

        Intent getgym = getIntent();
        String nama = getgym.getStringExtra("nama");
        namamember.setText(nama);
        idanggota.setText(getgym.getStringExtra("IDanggota"));
        jenkel.setText(getgym.getStringExtra("jeniskelamin"));
        alamat.setText(getgym.getStringExtra("almt"));
        umur.setText(getgym.getStringExtra("umur"));
        fasilitas.setText(getgym.getStringExtra("Fasilitas"));
        Toast.makeText(this,"Selamat datang" + getgym.getStringExtra("nama"),Toast.LENGTH_SHORT).show();

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahActivity = new Intent(checkdata.this, home.class);
                startActivity(pindahActivity);
            }
        });

        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }
}