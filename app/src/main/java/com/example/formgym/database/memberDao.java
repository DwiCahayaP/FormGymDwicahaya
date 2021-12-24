package com.example.formgym.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface memberDao {
    @Query("SELECT * FROM memberEntity")
    List<memberEntity> getAll();

    @Query("INSERT INTO memberEntity (Nama,Alamat,IDanggota,JenisKelamin,Fasilitas,Umur) VALUES(:Nama,:Alamat,:IDanggota,:JenisKelamin,:Fasilitas,:Umur)")
    void insertAll(String Nama, String Alamat, Integer IDanggota , String JenisKelamin, String Fasilitas, Integer Umur);

    //edit
    @Query("UPDATE memberEntity SET Nama=:Nama, Alamat=:Alamat, IDanggota=:IDanggota, JenisKelamin=:JenisKelamin, Fasilitas=:Fasilitas, Umur=:Umur  WHERE idMember=:idMember")
    void update(int idMember, String Nama, String Alamat, Integer IDanggota , String JenisKelamin, String Fasilitas, Integer Umur);

    @Query("SELECT * FROM memberEntity WHERE idMember=:idMember")
    memberEntity get(int idMember);

    @Delete
    void delete(memberEntity memberEntity);

}
