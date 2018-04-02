package com.gusbayok.qimen.qimen.fragwarga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gusbayok.qimen.qimen.ListPariwisataActivity;
import com.gusbayok.qimen.qimen.ListPemerintahanActivity;
import com.gusbayok.qimen.qimen.ListProfilActivity;
import com.gusbayok.qimen.qimen.R;

public class ProfilFragment extends Fragment {
    private LinearLayout profil,pemerintahan, pariwisata;
    public ProfilFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        profil = (LinearLayout) v.findViewById(R.id.profil);

        profil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListProfilActivity.class));

            }
        });

        pemerintahan = (LinearLayout) v.findViewById(R.id.pemerintahan);

        pemerintahan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListPemerintahanActivity.class));

            }
        });

        pariwisata = (LinearLayout) v.findViewById(R.id.pariwisata);

        pariwisata.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListPariwisataActivity.class));

            }
        });
        return v;
    }

}
