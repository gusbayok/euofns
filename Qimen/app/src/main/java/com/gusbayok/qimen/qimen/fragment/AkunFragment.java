package com.gusbayok.qimen.qimen.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gusbayok.qimen.qimen.DaftarUserActivity;
import com.gusbayok.qimen.qimen.DetailTentangActivity;
import com.gusbayok.qimen.qimen.LupaPassActivity;
import com.gusbayok.qimen.qimen.R;
import com.gusbayok.qimen.qimen.WargaActivity;
import com.gusbayok.qimen.qimen.app.AppConfig;
import com.gusbayok.qimen.qimen.helper.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.gusbayok.qimen.qimen.LoginActivity.KEY_MASUK;


public class AkunFragment extends Fragment {

    Button button;
    TextView lupa, register;
    EditText editText1,editText2;
    CheckBox checkbox1;
    JSONArray rooms = null;
    ArrayList<HashMap<String, String>> arraylist;
    ProgressDialog mProgressDialog;
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> requestList;
    // tracks JSONArray
    JSONArray requests = null;
    private String json, status, id, nama, alamat;
    private static String TAG_STATUS = "status";
    private static String TAG_DATA = "data";

    public String md5(String s) {

        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    String password, username;

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_akun, container, false);
        editText2 = (EditText) v.findViewById(R.id.password);
        editText1 = (EditText) v.findViewById(R.id.email);


        checkbox1 = (CheckBox) v.findViewById(R.id.checkBox); //Memanggil ID Checkbox

        /*Membuat Aksi Ketika Checkbox Di Klik*/
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    //Jika checkbox di ceklis maka tampilkan password
                    editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    //Jika checkbox tidak di ceklis maka sembunyikan password
                    editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        button = (Button) v.findViewById(R.id.btnLogin);
        lupa=(TextView) v.findViewById(R.id.btnLupaPassword);

        lupa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), LupaPassActivity.class));

            }
        });

        register=(TextView) v.findViewById(R.id.btnLinkToRegisterScreen);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), DaftarUserActivity.class));

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                arraylist = new ArrayList<HashMap<String, String>>();
                new DownloadJSON().execute();
               /* startActivity(new Intent(getActivity().getApplicationContext(), WargaActivity.class));*/
            }
        });

        return v;
    }


    class DownloadJSON extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            // Create an array
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            username = editText1.getText().toString().trim();
            password = editText2.getText().toString().trim();
            String MdPass = md5(password);
            String LOGIN_URL = "http://103.86.103.27/aduanonline/index.php/api/login?type=warga&username="+username+"&password="+MdPass;
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, AppConfig.URL_Warga+username+"&password="+MdPass, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(final JSONObject response) {

                            try {

                                if (response != null) {

                                    status = response.getString(TAG_STATUS);

                                    if ("success".equals(status)) {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override

                                            public void run() {
                                                try {
                                                    JSONObject rooms = response.getJSONObject("data");

                                                    // looping through All songs
                                                    id=rooms.getString("user_id");
                                                    nama=rooms.getString("user_name");
                                                    alamat=rooms.getString("user_realname");

                                                    Intent intent = new Intent(getActivity().getApplicationContext(), WargaActivity.class);
                                                    intent.putExtra("id", id);
                                                    intent.putExtra("nama", alamat);
                                                    startActivity(intent);

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

        protected void onPostExecute(String file_url) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                }
            });


        }
    }

}
