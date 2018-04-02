package com.gusbayok.qimen.qimen.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gusbayok.qimen.qimen.DetailPanduanActivity;
import com.gusbayok.qimen.qimen.DetailTentangActivity;
import com.gusbayok.qimen.qimen.ListProfilActivity;
import com.gusbayok.qimen.qimen.R;

public class TentangFragment extends Fragment {
    private  LinearLayout tentang, panduan;

    public TentangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tentang, container, false);
        tentang = (LinearLayout) v.findViewById(R.id.tentang);

        tentang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), DetailTentangActivity.class));

            }
        });

        panduan = (LinearLayout) v.findViewById(R.id.panduan);

        panduan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), DetailPanduanActivity.class));

            }
        });

        return  v;
    }

}
