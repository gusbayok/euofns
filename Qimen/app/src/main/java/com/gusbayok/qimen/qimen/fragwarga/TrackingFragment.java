package com.gusbayok.qimen.qimen.fragwarga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gusbayok.qimen.qimen.DetailPanduanActivity;
import com.gusbayok.qimen.qimen.R;

public class TrackingFragment extends Fragment {
    private Button submit;
    public TrackingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tracking, container, false);
        submit = (Button) v.findViewById(R.id.simpan_button);


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), DetailPanduanActivity.class));

            }
        });
        return v;
    }

}
