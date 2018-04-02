package com.gusbayok.qimen.qimen;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gusbayok.qimen.qimen.app.AppConfig;
import com.gusbayok.qimen.qimen.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailPanduanActivity extends AppCompatActivity {
    private TextView nama, alamat, isi, no_telepon, email, website, total_kamar_tersedia, updated_at;
    private ImageView gambar;
    ArrayList<HashMap<String, String>> arraylist;
    JSONArray rooms = null;
    ArrayList<HashMap<String, String>> roomsList;

    // Progress Dialog
    ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> requestList;
    // tracks JSONArray

    private String json, status, tipe;

    // ALL JSON node names
    private static final String TAG_STATUS = "status";
    private static final String TAG_DATA = "data";
    private static final String TAG_Isi = "post_content";

    private String id;
    private Integer coba;
    private Button try_again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_panduan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle("Detail Panduan");
        nama = (TextView) findViewById(R.id.judul);
        alamat = (TextView) findViewById(R.id.kat);
        email = (TextView) findViewById(R.id.tgl);
        isi = (TextView) findViewById(R.id.isi);
        gambar = (ImageView) findViewById(R.id.gambar);

        id = "3";

        new LoadJuju().execute();
    }
    class LoadJuju extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DetailPanduanActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting tracks json and parsing
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<org.apache.http.NameValuePair>();

            // post album id as GET parameter
            String jsons = jsonParser.makeHttpRequest(AppConfig.URL_Detail+id, "GET",
                    params);

            json = jsons.substring(jsons.indexOf("¿")+1);
            // Check your log cat for JSON reponse
            //Log.d("Track List JSON: ", json);

            try {

                JSONObject jObj = new JSONObject(json);


                if (jObj != null) {

                    status = jObj.getString(TAG_STATUS);

                    if ("success".equals(status)) {
                        DetailPanduanActivity.this.runOnUiThread(new Runnable() {
                            @Override

                            public void run() {
                                try {
                                    JSONObject jObj = new JSONObject(json);

                                    JSONObject rooms = jObj.getJSONObject("data");

                                    // looping through All songs
                                    isi.setText(Html.fromHtml(rooms.getString("post_content")));
                                    email.setText(rooms.getString("post_date"));
                                    nama.setText(rooms.getString("post_title"));
                                    JSONObject zs = rooms.getJSONObject("image_first");

                                    Glide
                                            .with(getApplication())
                                            .load(zs.getString("img_src"))
                                            .into(gambar);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });



                    } else {
                        //Log.d("Albums: ", "null");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            DetailPanduanActivity.this.runOnUiThread(new Runnable() {
                public void run() {

                }
            });
            pDialog.dismiss();
        }
    }

}
