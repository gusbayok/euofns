package com.gusbayok.qimen.qimen;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gusbayok.qimen.qimen.adapter.KategoriAdapter;
import com.gusbayok.qimen.qimen.app.AppConfig;
import com.gusbayok.qimen.qimen.app.AppController;
import com.gusbayok.qimen.qimen.data.Kategori;
import com.gusbayok.qimen.qimen.fragment.AkunFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AduanActivity extends AppCompatActivity {
    private Button btn;
    private static final String IMAGE_DIRECTORY = "/assets/images/aduan/";
    private int GALLERY = 1, CAMERA = 2;
    Toolbar toolbar;
    Bitmap bitmap, decoded;
    int max_resolution_image = 800;
    TextView txt_hasil, lokasi;
    EditText judul, aduan;
    Spinner spinner;
    ProgressDialog pDialog;
    KategoriAdapter adapter;
    List<Kategori> listPendidikan = new ArrayList<Kategori>();

    private static String TAG_STATUS = "status";
    private static String TAG_DATA = "data";

    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100

    private static final String TAG = AduanActivity.class.getSimpleName();



    EditText judule, isine;

    String tag_json_obj = "json_obj_req";
    String kategori_id;
    String juduls;
    String isi;
    String lat;
    String lng, aduan_id;
    String alamat;

    int warga_id=06;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aduan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_balik);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle("Tambah Aduan");

        Intent i = getIntent();
        alamat = i.getStringExtra("alamat");
        lat = i.getStringExtra("lat");
        lng = i.getStringExtra("lng");

        judule = (EditText) findViewById(R.id.judul);
        isine = (EditText) findViewById(R.id.Aduan);



        btn = (Button) findViewById(R.id.buttonChoose);
        imageView = (ImageView) findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        spinner = (Spinner) findViewById(R.id.kategori);
        txt_hasil = (TextView) findViewById(R.id.txt_hasil);
        TextView lokasi= (TextView) findViewById(R.id.lokasi);

        lokasi.setText(alamat);
        Button upload= (Button) findViewById(R.id.buttonUpload);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
              kategori_id= listPendidikan.get(position).getKategori_id();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        adapter = new KategoriAdapter(AduanActivity.this, listPendidikan);
        spinner.setAdapter(adapter);
        callData();
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                simpan_update();
            }
        });
    }

    private void callData() {
        listPendidikan.clear();
        RequestQueue queue = Volley.newRequestQueue(AduanActivity.this);
        pDialog = new ProgressDialog(AduanActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();
        String url="http://103.86.103.27/aduanonline/index.php/api/kategori";
        // Creating volley request obj
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {

                            if (response != null) {

                                String status = response.getString(TAG_STATUS);

                                if ("success".equals(status)) {
                                    AduanActivity.this.runOnUiThread(new Runnable() {
                                        @Override

                                        public void run() {
                                            try {
                                                JSONArray rooms = response.getJSONArray("data");

                                                for (int i = 0; i < rooms.length(); i++) {
                                                    //HashMap<String, String> map = new HashMap<String, String>();
                                                    JSONObject c = rooms.getJSONObject(i);

                                                    Kategori item = new Kategori();

                                                    item.setKategori_id(c.getString("kategori_id"));
                                                    item.setKategori_nm(c.getString("kategori_nm"));

                                                    listPendidikan.add(item);
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    });


                                } else {

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                        hideDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


                hideDialog();
            }
        });

        // Adding request to request queue
        queue.add(getRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Tambahkan Foto");
        String[] pictureDialogItems = {
                "Galeri",
                "Kamera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    private void simpan_update() {
        String posts="http://103.86.103.27/aduanonline/index.php/api/aduan?return_id=true";

        StringRequest strReq = new StringRequest(Request.Method.POST, posts, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    aduan_id=jObj.getString("aduan_id");
                    uploadImage();
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                juduls = judule.getText().toString().trim();
                isi = isine.getText().toString().trim();


                params.put("warga_id", String.valueOf(warga_id));
                params.put("kategori_id", kategori_id);
                params.put("aduan_judul", juduls);
                params.put("aduan_isi", isi);
                params.put("ordinat_lat", lat);
                params.put("ordinat_lng", lng);
                params.put("ordinat_address", alamat);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void uploadImage() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Sedang menggunggah gambar", "Tunggu sebentar...", false, false);
        String UPLOAD_URL="http://103.86.103.27/aduanonline/upload.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response: ", response.toString());

                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //menghilangkan progress dialog
                        loading.dismiss();

                        //menampilkan toast
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<String, String>();

                params.put("aduan_id", aduan_id);
                params.put("aduan_file", getStringImage(decoded));
                params.put("aduan_file_description", aduan_id);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                   /* Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);

                    imageView.setImageBitmap(getResizedBitmap(bitmap, 512));*/
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                    // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                    setToImageView(getResizedBitmap(bitmap, 512));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
          /*  Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(getResizedBitmap(thumbnail, max_resolution_image));
            saveImage(thumbnail);*/
            Uri contentURI = data.getData();
            bitmap =  (Bitmap) data.getExtras().get("data");
            setToImageView(getResizedBitmap(bitmap, max_resolution_image));

        }
    }
    private void kosong() {
        imageView.setImageResource(0);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(decoded);
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");

            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}

