package com.example.staffmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewProfile extends AppCompatActivity {
    ImageView img_photo;
    TextView tv_name,tv_gender,tv_dob,tv_mobile,tv_email,tv_address,tv_qualification,tv_experience,tv_father,
    tv_mother,tv_joindate,tv_acctno,tv_ifsc,tv_recipientname;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledesign);
        img_photo=findViewById(R.id.imageView);
        tv_name=findViewById(R.id.textView18);
        tv_gender=findViewById(R.id.textView19);
        tv_dob=findViewById(R.id.textView20);
        tv_mobile=findViewById(R.id.textView21);
        tv_email=findViewById(R.id.textView22);
        tv_address=findViewById(R.id.textView23);
        tv_qualification=findViewById(R.id.textView24);
        tv_experience=findViewById(R.id.textView25);
        tv_father=findViewById(R.id.textView26);
        tv_mother=findViewById(R.id.textView27);
        tv_joindate=findViewById(R.id.textView28);
        tv_acctno=findViewById(R.id.textView29);
        tv_ifsc=findViewById(R.id.textView30);
        tv_recipientname=findViewById(R.id.textView31);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("lid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/staff_profile/";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {


                                tv_name.setText(jsonObj.getString("name"));
                                tv_gender.setText(jsonObj.getString("gender"));
                                tv_dob.setText(jsonObj.getString("dob"));
                                tv_mobile.setText(jsonObj.getString("mobile"));
                                tv_email.setText(jsonObj.getString("email"));
                                tv_address.setText(jsonObj.getString("address"));
                                tv_qualification.setText(jsonObj.getString("qualification"));
                                tv_experience.setText(jsonObj.getString("experiance"));
                                tv_father.setText(jsonObj.getString("father"));
                                tv_mother.setText(jsonObj.getString("mother"));
                                tv_joindate.setText(jsonObj.getString("joindate"));
                                tv_acctno.setText(jsonObj.getString("accntno"));
                                tv_ifsc.setText(jsonObj.getString("ifsc"));
                                tv_recipientname.setText(jsonObj.getString("recipientname"));


                                String photo = jsonObj.getString("photo");
                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");
                                String url = "http://" + ip + ":8000" + photo;
                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(img_photo);


                            }


                            // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
//                                gv.setAdapter(new Custom_view_visited_game(getApplicationContext(),name,gamecode));
                            // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));



                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("lid","");
                params.put("lid",id);
//                params.put("mac",maclis);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ij=new Intent(getApplicationContext(),staffhome.class);
        startActivity(ij);
    }
}