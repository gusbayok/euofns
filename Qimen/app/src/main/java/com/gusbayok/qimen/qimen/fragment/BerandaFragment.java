package com.gusbayok.qimen.qimen.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;

import com.gusbayok.qimen.qimen.DaftarUserActivity;
import com.gusbayok.qimen.qimen.DetailAduanActivity;
import com.gusbayok.qimen.qimen.ListAduanActivity;
import com.gusbayok.qimen.qimen.ListAduanUmumActivity;
import com.gusbayok.qimen.qimen.Main2Activity;
import com.gusbayok.qimen.qimen.MapsWargaActivity;
import com.gusbayok.qimen.qimen.R;
import com.gusbayok.qimen.qimen.slider.FragmentSlider;
import com.gusbayok.qimen.qimen.slider.SliderIndicator;
import com.gusbayok.qimen.qimen.slider.SliderPagerAdapter;
import com.gusbayok.qimen.qimen.slider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class BerandaFragment extends Fragment {
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private ShareActionProvider mShareActionProvider;

    private LinearLayout bank,perikanan,industri,dishub,kominfo,kebudayaan,transportasi, mainan, bengkel;


    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    public BerandaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_beranda, container, false);
        sliderView = (SliderView) v.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) v.findViewById(R.id.pagesContainer);
        bank = (LinearLayout) v.findViewById(R.id.bank);
        perikanan = (LinearLayout) v.findViewById(R.id.perikanan);
        industri = (LinearLayout) v.findViewById(R.id.industri);
        dishub = (LinearLayout) v.findViewById(R.id.dishub);
        kominfo = (LinearLayout) v.findViewById(R.id.kominfo);
        kebudayaan = (LinearLayout) v.findViewById(R.id.kebudayaan);
        transportasi = (LinearLayout) v.findViewById(R.id.transportasi);
        mainan = (LinearLayout) v.findViewById(R.id.mainan);
        bengkel = (LinearLayout) v.findViewById(R.id.bengkel);

        bank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), MapsWargaActivity.class));

            }
        });

        perikanan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListAduanUmumActivity.class));

            }
        });
        industri.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), DetailAduanActivity.class));

            }
        });
        dishub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), Main2Activity.class));

            }
        });

        kominfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListAduanActivity.class));

            }
        });
        kebudayaan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListAduanActivity.class));

            }
        });
        transportasi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListAduanActivity.class));

            }
        });

        mainan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ListAduanActivity.class));

            }
        });
        bengkel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), DetailAduanActivity.class));

            }
        });
        setupSlider();
        return v;
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://103.86.103.27/aduanonline/assets/images/slideshow/qmen.kebumenkab.go.id.180318-5.jpg"));
      /*  fragments.add(FragmentSlider.newInstance("http://1.bp.blogspot.com/-RmEipcw40r0/VKyCPpuinzI/AAAAAAAAAB8/1qZ1AxjZQ_I/s1600/Kirab-Festival-Budaya-Kebumen.jpg"));
        fragments.add(FragmentSlider.newInstance("https://1.bp.blogspot.com/-2wvhLUqQcT0/WejfzL15IuI/AAAAAAAAyPc/BpNRLTyolA4NioMdDcgGYPPeETWSmA6QgCKgBGAs/s640/20_8_karnaval.jpg"));
        fragments.add(FragmentSlider.newInstance("http://beritadaerah.co.id/wp-content/uploads/2015/05/antarafoto-pesawat-tiga-dimensi-160515-ol-1.jpg"));
*/
        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }


}
