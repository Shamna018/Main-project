package com.example.staffmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class viewsalary extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner month_search,year_search;
    String[] months = { "January", "February", "March", "April", "May","June","July","August",
            "September","October","November","December"};
    String[] monthvalue={"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] years = {"2023","2024","2025","2026","2027","2028","2029","2030"};

    TextView basic_pay,no_present,net_salary;
    Button search,pay_slip;

    String path;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsalary);
        month_search=findViewById(R.id.spinner);
        month_search.setOnItemSelectedListener(this);


        ArrayAdapter mm = new ArrayAdapter(this,android.R.layout.simple_spinner_item,months);
        mm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month_search.setAdapter(mm);

        year_search=findViewById(R.id.spinner4);
        year_search.setOnItemSelectedListener(this);
        pay_slip=findViewById(R.id.button6);
        pay_slip.setOnClickListener(this);


        month_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
                dd(monthvalue[i]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter yy = new ArrayAdapter(this,android.R.layout.simple_spinner_item,years);
        yy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        year_search.setAdapter(yy);

        basic_pay=findViewById(R.id.textView73);
        no_present=findViewById(R.id.textView75);
        net_salary=findViewById(R.id.textView77);


//        search=findViewById(R.id.button7);
        pay_slip=findViewById(R.id.button6);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String uid=sh.getString("lid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/staff_view_salary/";

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

                                basic_pay.setText(jsonObj.getString("data2"));
                                no_present.setText(jsonObj.getString("present"));
                                net_salary.setText(jsonObj.getString("de"));
                              //  pay_slip.setVisibility(View.VISIBLE);

                            }
                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                              //  pay_slip.setVisibility(View.INVISIBLE);

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

                params.put("msearch",month_search.getSelectedItem().toString());
                params.put("ysearch",year_search.getSelectedItem().toString());

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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

//        Toast.makeText(getApplicationContext(),years[i] , Toast.LENGTH_LONG).show();
        int h= month_search.getSelectedItemPosition();
dd(monthvalue[h]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void dd(String mm)
    {

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String uid=sh.getString("lid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/staff_monthsearch/";



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



                                basic_pay.setText(jsonObj.getString("data2"));
                                no_present.setText(jsonObj.getString("present"));
                                net_salary.setText(jsonObj.getString("de"));
                                path= jsonObj.getString("path");






                            }
                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Payment not calculated", Toast.LENGTH_LONG).show();
                                pay_slip.setVisibility(View.INVISIBLE);

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
                params.put("month",mm);
                params.put("year",year_search.getSelectedItem().toString());
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
    public void onClick(View view) {


        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String ip = sh.getString("ip", "");
        String url1 = "http://" + ip + ":8000" +path;
//
//                                File myFile = new File(url1);
//                                FileOpen.openFile(getApplicationContext(), myFile);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
        startActivity(browserIntent);


    }

    }

class FileOpen {

    public static void openFile(Context context, File url) throws IOException {
        // Create URI
        File file=url;
        Uri uri = Uri.fromFile(file);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        // Check what kind of file you are trying to open, by comparing the url with extensions.
        // When the if condition is matched, plugin sets the correct intent (mime) type,
        // so Android knew what application to use to open the file
        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if(url.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if(url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if(url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if(url.toString().contains(".zip") || url.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav");
        } else if(url.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if(url.toString().contains(".wav") || url.toString().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if(url.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if(url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if(url.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if(url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            //if you want you can also define the intent type for any other file

            //additionally use else clause below, to manage other unknown extensions
            //in this case, Android will show all applications installed on the device
            //so you can choose which application to use
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
