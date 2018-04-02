package com.gusbayok.qimen.qimen.fragwarga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gusbayok.qimen.qimen.DaftarUserActivity;
import com.gusbayok.qimen.qimen.ListAduanActivity;
import com.gusbayok.qimen.qimen.LupaPassActivity;
import com.gusbayok.qimen.qimen.MainActivity;
import com.gusbayok.qimen.qimen.R;
import com.gusbayok.qimen.qimen.WargaActivity;

import org.w3c.dom.Text;


public class AkunFragment extends Fragment {
    TextView profil, ganti, logout, bantuan;
    String id, nama;
    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_keluar, container, false);

        profil = (TextView) v.findViewById(R.id.nama);
        final WargaActivity activity = (WargaActivity) getActivity();
        id = activity.getId();
        nama = activity.getNama();
        profil.setText(nama);

        profil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), DaftarUserActivity.class));

            }
        });

        bantuan = (TextView) v.findViewById(R.id.bantuan);
/*
        bantuan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), WargaActivity.class));

            }
        });*/

        ganti = (TextView) v.findViewById(R.id.ganti);

        ganti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), LupaPassActivity.class));

            }
        });

        logout = (TextView) v.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));

            }
        });
        return v;
    }

}
