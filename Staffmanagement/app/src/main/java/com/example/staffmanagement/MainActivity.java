package com.example.staffmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_ip;
    Button bt_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_ip=findViewById(R.id.editTextTextPersonName2);
        bt_ip=findViewById(R.id.button4);
        bt_ip.setOnClickListener(this);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ed_ip.setText(sh.getString("ip",""));
    }

    @Override
    public void onClick(View view) {

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edp=sh.edit();
        edp.putString("ip",ed_ip.getText().toString());
        edp.commit();
        startActivity(new Intent(getApplicationContext(),login.class));



    }
}