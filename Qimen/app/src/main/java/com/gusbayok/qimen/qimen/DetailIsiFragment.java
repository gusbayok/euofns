package com.gusbayok.qimen.qimen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;

public class DetailIsiFragment extends Fragment {
    private static final String TAG = "FirstFragment";



    private LinearLayout main;
    private String id;
    private String jumlahKamar;
    private String updated, gambaris;
    private ImageView gambar;
    private ImageLoader jikukGambar;
   /* ImageLoader imageLoader = new ImageLoader(getActivity().this);*/

    private Button try_again;

    public DetailIsiFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_isi, container, false);
    }


}
