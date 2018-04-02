package com.gusbayok.qimen.qimen;

import android.app.Notification;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.gusbayok.qimen.qimen.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import com.gusbayok.qimen.qimen.app.AppConfig;
import com.gusbayok.qimen.qimen.helper.JSONParser;

public class ListProfilActivity extends AppCompatActivity {

    JSONArray rooms = null;
    ArrayList<HashMap<String, String>> arraylist;

    ListView listview;
    ProfilAdapter adapter;
    ProgressDialog mProgressDialog;
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> requestList;
    // tracks JSONArray
    JSONArray requests = null;
    private String json;
    private String status;
    private String menu_title;
    private JSONArray list_post;
    private static String TAG_STATUS = "status";
    private static String TAG_DATA = "data";
    static String TAG_Menu = "menu_title";
    static String TAG_ID = "post_id";
    static String TAG_NAMA = "post_title";
    static String Tag_flag = "image_first";
    static String FLAG = "img_src";

    static String Tgl = "post_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        arraylist = new ArrayList<HashMap<String, String>>();

        new DownloadJSON().execute();

    }

    // DownloadJSON AsyncTask
    class DownloadJSON extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ListProfilActivity.this);

            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // Create an array
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            String jsons = jsonParser.makeHttpRequest(AppConfig.URL_PROFIL, "GET",
                    params);

            json = jsons.substring(jsons.indexOf("¿")+1);
            // Check your log cat for JSON reponse
            //Log.d("Track List JSON: ", json);

            try {
                JSONObject jObj = new JSONObject(json);

                if (jObj != null) {

                    status = jObj.getString(TAG_STATUS);
                    if ("success".equals(status)) {
                        rooms = jObj.getJSONArray(TAG_DATA);
                        for (int i = 0; i < rooms.length(); i++) {
                            JSONObject c = rooms.getJSONObject(i);
                            menu_title = c.getString("menu_title");
                           if("Profil Daerah".equals(menu_title)){
                               list_post = c.getJSONArray("list_post");
                               for (int k = 0; k < list_post.length(); k++) {
                                   JSONObject z = list_post.getJSONObject(k);
                                   String id = z.getString(TAG_ID);
                                   String nama = z.getString(TAG_NAMA);
                                   String tgl=z.getString(Tgl);


                                   String image_st = z.getString("image_st");
                                   if("1".equals(image_st)){
                                       JSONObject zs = z.getJSONObject("image_first");
                                       String logo = zs.getString(FLAG);
                                       HashMap<String, String> map = new HashMap<String, String>();
                                       map.put(TAG_ID, id);
                                       map.put(TAG_NAMA, nama);
                                       map.put(FLAG, logo);
                                       map.put(Tgl,tgl);
                                       arraylist.add(map);
                                   }else {
                                       String logos="http://addkomputer.com/qmen/assets/images/post/qmen.kebumenkab.go.id.260118-5.jpg";
                                       HashMap<String, String> map = new HashMap<String, String>();
                                       map.put(FLAG, logos);
                                       map.put(TAG_ID, id);
                                       map.put(TAG_NAMA, nama);
                                       map.put(Tgl,tgl);
                                       arraylist.add(map);
                                   }
                                   // creating new HashMap

                               }
                           }else{

                           }
                        }
                    } else {
                        //Log.d("Albums: ", "null");
                    }
                }
            } catch (JSONException e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(android.R.id.list);
            // Pass the results into ListViewAdapter.java
            adapter = new ProfilAdapter(ListProfilActivity.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

}
