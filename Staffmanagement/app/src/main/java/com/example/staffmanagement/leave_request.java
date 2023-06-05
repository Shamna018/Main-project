package com.example.staffmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class leave_request extends AppCompatActivity implements View.OnClickListener {

    EditText edfrom_date,edto_date,reason,no_leaves;
    Button btn;

    String dt="";
    String dt1="";

    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);
        edfrom_date=findViewById(R.id.editTextTextPersonName4);
        edto_date=findViewById(R.id.editTextTextPersonName5);
        reason=findViewById(R.id.editTextTextPersonName6);
        no_leaves=findViewById(R.id.editTextTextPersonName7);
        btn=findViewById(R.id.button5);
        btn.setOnClickListener(this);



        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
            }
        };
        edfrom_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog ss=  new DatePickerDialog(leave_request.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                ss.getDatePicker().setMinDate(c.getTimeInMillis());
                updateLabel();

                ss.show();
                btn.setEnabled(true);

            }
        });

        edto_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog ss=  new DatePickerDialog(leave_request.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                ss.getDatePicker().setMinDate(c.getTimeInMillis());
                updateLabel2();
                ss.show();
                btn.setEnabled(true);

            }
        });


        if (dt.equalsIgnoreCase("")) {
            btn.setEnabled(false);
        }else{
            btn.setEnabled(true);
        }
        if (dt1.equalsIgnoreCase("")) {
            btn.setEnabled(false);
        }else{
            btn.setEnabled(true);
        }


    }


    private void updateLabel() {

        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edfrom_date.setText(dateFormat.format(myCalendar.getTime()));
        dt=dateFormat.format(myCalendar.getTime());
        btn.setEnabled(true);

    }
    private void updateLabel1() {

        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edto_date.setText(dateFormat.format(myCalendar.getTime()));
        dt=dateFormat.format(myCalendar.getTime());
        btn.setEnabled(true);

    }

    private void updateLabel2() {

        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edto_date.setText(dateFormat.format(myCalendar.getTime()));
        dt1=dateFormat.format(myCalendar.getTime());
        btn.setEnabled(true);

    }



    @Override
    public void onClick(View view) {
        String fromdate = edfrom_date.getText().toString();
        String todate = edto_date.getText().toString();
        String reason_leave = reason.getText().toString();
        String no_of_leaves = no_leaves.getText().toString();


        if (fromdate.length() == 0) {
            edfrom_date.setError("Missing");
        } else if (todate.length() == 0) {
            edto_date.setError("Missing");
        }else if (reason_leave.length() == 0) {
            reason.setError("Missing");
        }else if (no_of_leaves.length() == 0) {
            no_leaves.setError("Missing");
        } else {


            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/leave_request_form/";



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

                                    startActivity(new Intent(getApplicationContext(),view_leave_request.class));
                                    Toast.makeText(leave_request.this, "Request Sended", Toast.LENGTH_SHORT).show();

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
                    params.put("todate",todate);
                    params.put("reason_leave",reason_leave);
                    params.put("no_of_leaves",no_of_leaves);
                    params.put("lid",id);
                    params.put("date",dt);


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