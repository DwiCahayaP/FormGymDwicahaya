package com.example.formgym.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class memberEntity {
    @PrimaryKey
    public int idMember;

    @ColumnInfo(name = "Nama")
    public String fullname;

    @ColumnInfo(name = "Alamat")
    public String alamat;

    @ColumnInfo(name = "IDanggota")
    public int idanggota;

    @ColumnInfo(name = "JenisKelamin")
    public String jeniskelamin;

    @ColumnInfo(name = "Fasilitas")
    public String fasilitas;

    @ColumnInfo(name = "Umur")
    public int umur;

}
