package com.gusbayok.qimen.qimen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gusbayok.qimen.qimen.fragwarga.AkunFragment;
import com.gusbayok.qimen.qimen.fragwarga.BerandaFragment;
import com.gusbayok.qimen.qimen.fragwarga.ProfilFragment;
import com.gusbayok.qimen.qimen.fragwarga.TentangFragment;
import com.gusbayok.qimen.qimen.fragwarga.TrackingFragment;

import java.lang.reflect.Field;

public class WargaActivity extends AppCompatActivity {

    private TextView mTextMessage;
    BottomNavigationView bottomNavigationView;

    private ViewPager viewPager;

    AkunFragment akunFragment;
    BerandaFragment berandaFragment;
    ProfilFragment profilFragment;
    TentangFragment tentangFragment;
    TrackingFragment trackingFragment;
    MenuItem prevMenuItem;
    String id, nama;


    public static class BottomNavigationViewHelper {

        @SuppressLint("RestrictedApi")
        public static void removeShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BottomNav", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BottomNav", "Unable to change value of shift mode", e);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warga);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        nama = i.getStringExtra("nama");

        viewPager = (ViewPager) findViewById(R.id.viewpager);


        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        WargaActivity.BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_beranda:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_profil:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_tentang:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_tracking:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.navigation_akun:
                                viewPager.setCurrentItem(4);
                                break;

                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        setupViewPager(viewPager);
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewWagerAdapter adapter = new ViewWagerAdapter(getSupportFragmentManager());
        berandaFragment=new BerandaFragment();
        profilFragment=new ProfilFragment();
        tentangFragment=new TentangFragment();
        trackingFragment=new TrackingFragment();
        akunFragment=new AkunFragment();
        adapter.addFragment(berandaFragment);
        adapter.addFragment(profilFragment);
        adapter.addFragment(tentangFragment);
        adapter.addFragment(trackingFragment);
        adapter.addFragment(akunFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);
            alertDialogBuilder
                    .setTitle("About")
                    .setMessage("qimen.kebumenkab.go.id\nVersion 2.0")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
