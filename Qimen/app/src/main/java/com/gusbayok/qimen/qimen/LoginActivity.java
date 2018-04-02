package com.gusbayok.qimen.qimen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gusbayok.qimen.qimen.slider.SliderIndicator;
import com.gusbayok.qimen.qimen.slider.SliderPagerAdapter;
import com.gusbayok.qimen.qimen.slider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {


    public static final String LOGIN_URL = "http://kebumenonnews.kebumenkab.go.id/api/kontributor/";

    public static final String KEY_USERNAME="kontributor_email";
    public static final String KEY_MASUK="session";
    public static final String KEY_PASSWORD="kontributor_password";

    public String suu;
    private String json, status;
    private String email,masuk,masuks;
    private String password;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private static String TAG_STATUS = "status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        TextView masuks = (TextView) findViewById(R.id.masuk);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button buttonLogin = (Button) findViewById(R.id.tombol_masuk);
        buttonLogin.setOnClickListener(this);
    }
    private void attemptLogin() {
        email = mEmailView.getText().toString().trim();
        password = mPasswordView.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            status = jObj.getString(TAG_STATUS);
                            if ("success".equals(status)){
                                suu = jObj.getString(KEY_MASUK);

                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                intent.putExtra("fak",suu);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this,status,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Input yang anda masukan salah", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,email);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
/*    private void openProfile(){
        Intent intent = new Intent(this, MasukActivity.class);
        intent.putExtra(suu);
        startActivity(intent);
    }*/

    public void onClick(View v) {
        attemptLogin();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.beranda) {
            Intent intentku= new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intentku);
        } else if (id == R.id.login) {
            Intent intentku= new Intent(LoginActivity.this,LoginActivity.class);
            startActivity(intentku);
        } else if (id == R.id.about) {
            Intent intentku= new Intent(LoginActivity.this,TentangActivity.class);
            startActivity(intentku);
        } else if (id == R.id.panduan) {
            Intent intentku= new Intent(LoginActivity.this,PanduanActivity.class);
            startActivity(intentku);
        } else if (id == R.id.feedback) {
            Intent intentku= new Intent(LoginActivity.this,FeedbackActivity.class);
            startActivity(intentku);
        } else if (id == R.id.share) {
            ShareActionProvider mShareActionProvider;
            mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        }else if (id == R.id.rating) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);
            alertDialogBuilder
                    .setTitle("Beri Rating Aplikasi Ini")
                    .setMessage("Hai Bagaimana Menurutmu? Bantu beri rating kami dan saranmu agar aplikasi ini jauh lebih baik lagi. TErima kasih atas dukunganmu!")
                    .setNegativeButton("INGATKAN SAYA\t\t\t\t\t\t\tTIDAK\t\t\t\t\tRATING", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
