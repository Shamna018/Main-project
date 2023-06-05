package com.example.staffmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class View_notification extends AppCompatActivity {
TextView tvdate,tvtitle,tvdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        tvdate=findViewById(R.id.textView46);
        tvtitle=findViewById(R.id.textView47);
        tvdesc=findViewById(R.id.textView48);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        tvdate.setText(sh.getString("date",""));
        tvtitle.setText(sh.getString("title",""));
        tvdesc.setText(sh.getString("content",""));

       // edp.putString("department",department[i]);
    }
}