package com.gusbayok.qimen.qimen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by My Computer on 01/02/2018.
 */

public class PemerintahanAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;

    HashMap<String, String> resultp = new HashMap<String, String>();

    public PemerintahanAdapter(Context context,
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
        TextView rank;
        TextView id;
        TextView population,email;
        ImageView flag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.card_profil, parent, false);
        // Get the position
        resultp = data.get(position);


        id = (TextView) itemView.findViewById(R.id.id);
        rank = (TextView) itemView.findViewById(R.id.judul);
        flag = (ImageView) itemView.findViewById(R.id.gambar);

        TextView tgl= (TextView) itemView.findViewById(R.id.tgl);

        // Capture position and set results to the TextViews
        rank.setText(resultp.get(ListProfilActivity.TAG_NAMA));
        id.setText(resultp.get(ListProfilActivity.TAG_ID));
        tgl.setText(resultp.get(ListProfilActivity.Tgl));

        Glide
                .with(context)
                .load(resultp.get(ListPemerintahanActivity.FLAG))
                .into(flag);

        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent i = new Intent(context, DetailMenuActivity.class);
                i.putExtra("post_id", resultp.get(ListProfilActivity.TAG_ID));
                i.putExtra("img_src", resultp.get(ListProfilActivity.FLAG));
                i.putExtra("post_title", resultp.get(ListProfilActivity.TAG_NAMA));
                i.putExtra("post_date", resultp.get(ListProfilActivity.Tgl));
                context.startActivity(i);

            }
        });
        return itemView;
    }
}
