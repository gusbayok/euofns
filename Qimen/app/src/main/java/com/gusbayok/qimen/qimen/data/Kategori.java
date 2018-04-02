package com.gusbayok.qimen.qimen.data;

/**
 * Created by My Computer on 02/04/2018.
 */

public class Kategori {
    private String kategori_id, kategori_nm;

    public Kategori() {
    }

    public Kategori(String kategori_id, String kategori_nm) {
        this.kategori_id = kategori_id;
        this.kategori_nm = kategori_nm;
    }

    public String getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getKategori_nm() {
        return kategori_nm;
    }

    public void setKategori_nm(String kategori_nm) {
        this.kategori_nm = kategori_nm;
    }
}
