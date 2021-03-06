package com.gusbayok.qimen.qimen;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by My Computer on 02/04/2018.
 */

public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();
    View itemView;

    public ListViewAdapter(Context context,
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
        if(convertView == null) {
            TextView textView = new TextView(parent.getContext());
            textView.setText("Position is:"+position);
            textView.setBackgroundColor(Color.CYAN);
            textView.setLayoutParams(new AbsListView.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            convertView = textView;
        }

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
