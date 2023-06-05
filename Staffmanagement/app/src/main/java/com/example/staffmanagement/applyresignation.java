package com.example.staffmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class applyresignation extends AppCompatActivity implements View.OnClickListener {

    EditText from_date,other_reason;
    Spinner reason;
    Button btn;
    String[] rsn = {"Low Salary", "New Job Opportunity", "Medical Reason"};
    String dt="";

    final Calendar myCalendar= Calendar.getInstance();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyresignation);
        from_date=findViewById(R.id.editTextTextPersonName10);
        reason=findViewById(R.id.spinner2);
        other_reason=findViewById(R.id.editTextTextMultiLine);
        btn=findViewById(R.id.button3);
        btn.setOnClickListener(this);
        reason.setAdapter(new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, rsn));
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
            }
        };
        from_date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                DatePickerDialog ss=  new DatePickerDialog(applyresignation.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                ss.getDatePicker().setMinDate(c.getTimeInMillis());
                updateLabel();

                ss.show();
                btn.setEnabled(true);
            }

            });
        if (dt.equalsIgnoreCase("")) {
            btn.setEnabled(false);
        }else{
            btn.setEnabled(true);
        }
        }
    private void updateLabel() {

        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        from_date.setText(dateFormat.format(myCalendar.getTime()));
        dt=dateFormat.format(myCalendar.getTime());
        btn.setEnabled(true);

    }


    @Override
    public void onClick(View view) {
        String fromdate = from_date.getText().toString();
        String otherreason = other_reason.getText().toString();


        if (fromdate.length() == 0) {
            from_date.setError("Missing");
        } else {


            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/send_resign/";



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


                                    Toast.makeText(applyresignation.this, "Request Sended", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),staffhome.class));
                                }


                                // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
//                                gv.setAdapter(new Custom_view_visited_game(getApplicationContext(),name,gamecode));
                                // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));



                                // }
                                else {
                                    Toast.makeText(getApplicationContext(), "Already Send", Toast.LENGTH_LONG).show();
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
                    params.put("fromdate",fromdate);
                    params.put("reason_leave",reason.getSelectedItem().toString());
                    params.put("otherreason",otherreason);


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



    }
}
