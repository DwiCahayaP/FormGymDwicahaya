package com.example.formgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView addlist = findViewById(R.id.addmember);
        TextView ceklist = findViewById(R.id.listmember);

        addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahactivity = new Intent(home.this,MainActivity.class);
                startActivity(pindahactivity);
            }
        });

        ceklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahAct = new Intent(home.this,listmenu.class);
                startActivity(pindahAct);
            }
        });


    }

}