package com.gusbayok.qimen.qimen.adapter;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gusbayok.qimen.qimen.ListAduanUmumActivity;
import com.gusbayok.qimen.qimen.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class UmumAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public UmumAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;


        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView judul, kategori, warga, tindak,id;
        ImageView gambar;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_aduan, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        id = (TextView) itemView.findViewById(R.id.id);
        judul = (TextView) itemView.findViewById(R.id.judul);
        kategori = (TextView) itemView.findViewById(R.id.kategori);
        warga = (TextView) itemView.findViewById(R.id.warga);
        tindak = (TextView) itemView.findViewById(R.id.tindak);


        // Locate the ImageView in listview_item.xml
        gambar = (ImageView) itemView.findViewById(R.id.gambar);

        id.setText(resultp.get(ListAduanUmumActivity.Tag_ID));
        judul.setText(resultp.get(ListAduanUmumActivity.Judul));
        kategori.setText(resultp.get(ListAduanUmumActivity.Kategori));
        warga.setText(resultp.get(ListAduanUmumActivity.Warga));
        tindak.setText(resultp.get(ListAduanUmumActivity.Tindak));





        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position


            }
        });
        return itemView;
    }
}