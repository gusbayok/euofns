package com.gusbayok.qimen.qimen;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gusbayok.qimen.qimen.app.AppConfig;
import com.gusbayok.qimen.qimen.helper.JSONParser;

import static com.gusbayok.qimen.qimen.ListAduanUmumActivity.Judul;
import static com.gusbayok.qimen.qimen.ListAduanUmumActivity.Kategori;

public class Main2Activity extends ListActivity {

    JSONArray rooms = null;
    ArrayList<HashMap<String, String>> arraylist;

    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> requestList;
    // tracks JSONArray
    JSONArray requests = null;
    private  String json, status;
    public static String Tag_ID = "aduan_id";
    public static String Judul = "aduan_judul";
    public static String Kategori = "kategori_nm";
    public static String Warga = "warga_nm";
    public static String Tindak = "count_tindaklanjut";

    public static String Gambar = "image_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_main2);


        arraylist = new ArrayList<HashMap<String, String>>();


        // Execute DownloadJSON AsyncTask
        new DownloadJSON().execute();
    }


    // DownloadJSON AsyncTask
    class DownloadJSON extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Main2Activity.this);

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

            RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
            String idss= String.valueOf(1);


            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, AppConfig.URL_LIST_ROOM+idss, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                if (response != null) {

                                    status = response.getString("status");
                                    if ("success".equals(status)) {
                                        rooms = response.getJSONArray("data");


                                        for (int i = 0; i < rooms.length(); i++) {
                                            //HashMap<String, String> map = new HashMap<String, String>();
                                            JSONObject c = rooms.getJSONObject(i);

                                            String id = c.getString(Tag_ID);
                                            String judul = c.getString(Judul);
                                            String kategori = c.getString(Kategori);
                                            String warga = c.getString(Warga);
                                            String tindak = c.getString(Tindak);
                                            HashMap<String, String> map = new HashMap<String, String>();
                                            map.put(Tag_ID, id);
                                            map.put(Judul, judul);
                                            map.put(Kategori, kategori);
                                            map.put(Warga, warga);
                                            map.put(Tindak, tindak);
                                            arraylist.add(map);


                                        }
                                    } else {
                                        //Log.d("Albums: ", "null");
                                    }
                                }
                            } catch (JSONException e) {
                                //Log.e("Error", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //menampilkan error pada logcat
                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            );
            queue.add(getRequest);
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(android.R.id.list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(Main2Activity.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
                return true;
            default:
                //return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}