package com.example.staffmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_attendance extends BaseAdapter {
    String[]  id,date,staff_id,entry_time,entry_status,exit_time,exit_status;
    private Context context;

    public custom_view_attendance(Context appcontext, String[]id, String[]date, String[]staff_id,
                                  String[]entry_time,String[]entry_status, String[]exit_time, String[]exit_status)
    {
        this.context=appcontext;
        this.id=id;
        this.date=date;
        this.staff_id=staff_id;
        this.entry_time=entry_time;
        this.entry_status=entry_status;
        this.exit_time=exit_time;
        this.exit_status=exit_status;



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
            gridView=inflator.inflate(R.layout.custom_view_attendance,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView53);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView54);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView55);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView57);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView42);

        tv1.setText(date[i]);
        tv2.setText(entry_time[i]);
        tv3.setText(entry_status[i]);
        tv4.setText(exit_time[i]);
        tv5.setText(exit_status[i]);




        return gridView;
    }
}