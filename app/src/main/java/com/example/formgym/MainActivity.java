package com.example.formgym;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import com.example.formgym.database.AppDatabase;
import com.example.formgym.database.memberEntity;

public class MainActivity extends AppCompatActivity {

    private EditText EditNama, EditAlm, Editidanggota;
    private Button BtnSave;
    private AppDatabase database;
    private RadioGroup EditJK;
    Boolean stat=false;
    Boolean statRadio=false;
    Boolean statCheckBox=false;
    private RadioButton radiobtnchoose;
    private RadioButton rbPria;
    private RadioButton rbWanita;
    private Integer jumlahusia;
    private RadioButton choosenradiobtn;
    String checkNama, checkAlm, chekNIK;
    private CheckBox cbSauna;
    private CheckBox cbTrainer;
    private int idMember = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditNama = findViewById(R.id.editTextName);
        EditAlm = findViewById(R.id.Alamatedit);
        Editidanggota = findViewById(R.id.editnomember);
        EditJK = findViewById(R.id.radioGroupGender);
        BtnSave = findViewById(R.id.buttonSubmit);

        //buat validasi
        rbPria = findViewById(R.id.radioButtonMale);
        rbWanita = findViewById(R.id.radioButtonFemale);
        cbSauna = findViewById(R.id.checkBoxSauna);
        cbTrainer = findViewById(R.id.checkBoxTrainer);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        idMember = intent.getIntExtra("idMember",0);
        if (idMember>0){
            isEdit = true;
            choosenradiobtn = findViewById(EditJK.getCheckedRadioButtonId());
            //melakukan edit dengan idunik
            memberEntity memberEntity = database.memberDao().get(idMember);
            EditNama.setText(memberEntity.fullname);
            EditAlm.setText(memberEntity.alamat);
            Editidanggota.setText(""+ memberEntity.idanggota);
 //           choosenradiobtn.setText(memberEntity.jeniskelamin);

        }else {
            isEdit = false;
        }

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radiobtnchoose = findViewById(EditJK.getCheckedRadioButtonId());
                checkNama = EditNama.getText().toString();
                chekNIK = Editidanggota.getText().toString();
                checkAlm = EditAlm.getText().toString();

                //VALIDASI KOSONG//
                if(EditNama.getText().toString().length()==0){
                    EditNama.setError("Nama Wajib Diisi");
                }else if (Editidanggota.getText().toString().length()==0){
                    Editidanggota.setError("NIK Wajib Diisi");
                }else if (EditAlm.getText().toString().length()==0){
                    EditAlm.setError("Alamat Wajib Diisi");
                }

                else {
                    stat = true;
                }
                if (rbPria.isChecked() || rbWanita.isChecked()){
                    statRadio = true;
                }else {
                    statRadio = false;
                    Toast.makeText(getApplicationContext(), "Gender Wajib Diisi", Toast.LENGTH_SHORT).show();
                }
                if (cbSauna.isChecked() || cbTrainer.isChecked()){
                    statCheckBox = true;
                }else{
                    statCheckBox = false;
                    Toast.makeText(getApplicationContext(), "Pilihan Fasilitas Wajib Diisi",Toast.LENGTH_SHORT).show();
                }
                if (stat==true&&statRadio==true&statCheckBox==true){
                    showDialog();
                    stat=false;
                }
         //       showDialog();

            }
        });


        Objects.requireNonNull(getSupportActionBar()).setTitle("Form GYM");

        setupSeekBar();


    }

    private void showDialog() {
        EditText editTextnama = (EditText) findViewById(R.id.editTextName);
        EditText editidanggota = (EditText) findViewById(R.id.editnomember);
        EditText editTextAlm = (EditText) findViewById(R.id.Alamatedit);
        RadioGroup radioGroupGenders = (RadioGroup) findViewById(R.id.radioGroupGender);
        RadioButton radioButtonChosen = (RadioButton) findViewById(radioGroupGenders.getCheckedRadioButtonId());
        TextView umur = (TextView) findViewById(R.id.jumlahumur);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Konfirmasi Data");

        dialogBuilder.setMessage("Apakah anda sudah yakin dengan data berikut?\n\n" +
                "Nama: " + editTextnama.getText() + "\n" +
                "ID Member: " + editidanggota.getText() + "\n" +
                "Jenis Kelamin: " + radioButtonChosen.getText() + "\n" +
                "Umur : " + umur.getText() + "\n" +
                "Alamat : " + editTextAlm.getText() + "\n" +
                "Fasilitas: " + getCheckedBoxValue() + "\n")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data diterima!", Toast.LENGTH_SHORT).show();


                        Intent pindahAct = new Intent(getApplicationContext(), checkdata.class);
                        pindahAct.putExtra("nama", editTextnama.getText().toString());
                        pindahAct.putExtra("IDanggota", editidanggota.getText().toString());
                        pindahAct.putExtra("jeniskelamin", radioButtonChosen.getText().toString());
                        pindahAct.putExtra("almt", editTextAlm.getText().toString());
                        pindahAct.putExtra("umur", umur.getText());
                        pindahAct.putExtra("Fasilitas", getCheckedBoxValue());

                        if (isEdit){
                            database.memberDao().update(idMember, EditNama.getText().toString(), EditAlm.getText().toString(),  Integer.parseInt(Editidanggota.getText().toString()), radiobtnchoose.getText().toString(), getCheckedBoxValue(),jumlahusia);
                        }else {
                            database.memberDao().insertAll(EditNama.getText().toString(), EditAlm.getText().toString(),  Integer.parseInt(Editidanggota.getText().toString()), radiobtnchoose.getText().toString(),getCheckedBoxValue(),jumlahusia);
                        }

                        startActivity(pindahAct);

                        dialogInterface.cancel();

                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setCancelable(true);

        AlertDialog confirmDialog = dialogBuilder.create();

        confirmDialog.show();
    }

    private String getCheckedBoxValue() {
        CheckBox checkBoxSauna = (CheckBox) findViewById(R.id.checkBoxSauna);
        CheckBox checkBoxTrainer = (CheckBox) findViewById(R.id.checkBoxTrainer);

        String checkedValue = "";

        if (checkBoxSauna.isChecked()) {
            checkedValue += checkBoxSauna.getText() + ", ";
        }
        if (checkBoxTrainer.isChecked()) {
            checkedValue += checkBoxTrainer.getText() + " ";
        }

        return checkedValue;
    }


    private void setupSeekBar() {


        SeekBar seekBarusia = (SeekBar) findViewById(R.id.seekBarumur);
        TextView textViewUmurValue = (TextView) findViewById(R.id.jumlahumur);


        seekBarusia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                textViewUmurValue.setText(String.valueOf(progress + " Tahun"));
                jumlahusia = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Registrasi dimulai",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Registrasi sedang berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Registrasi berhenti sementara",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Registrasi berhenti",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup, selamat tinggal",Toast.LENGTH_SHORT).show();
    }
}