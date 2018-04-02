package com.gusbayok.qimen.qimen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gusbayok.qimen.qimen.R;
import com.gusbayok.qimen.qimen.data.Kategori;

import java.util.List;

/**
 * Created by My Computer on 02/04/2018.
 */

public class KategoriAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Kategori> item;

    public KategoriAdapter(Activity activity, List<Kategori> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_kategori, null);

        TextView pendidikan = (TextView) convertView.findViewById(R.id.kategori);

        Kategori data;
        data = item.get(position);

        pendidikan.setText(data.getKategori_nm());

        return convertView;
    }
}