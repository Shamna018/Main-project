package com.example.staffmanagement;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_notification extends BaseAdapter {
    String[] id,date,title,content,department;
    private Context context;

    public custom_notification(Context appcontext,String[]id,String[]date,String[]title,
                               String[]content,String[]department)
    {
        this.context=appcontext;
        this.id=id;
        this.date=date;
        this.title=title;
        this.content=content;
        this.department=department;



    }

    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_notification,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView38);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView41);

        tv1.setText(title[i]);
        tv2.setText(date[i]);




        return gridView;
    }
}