package com.example.staffmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_leave_request extends BaseAdapter {
    String[] id,date,staff_id,from_date,to_date,reason,status;
    private Context context;

    public custom_view_leave_request(Context appcontext, String[]id, String[]date, String[]staff_id,
                                     String[]from_date, String[]to_date, String[]reason, String[]status)
    {
        this.context=appcontext;
        this.id=id;
        this.date=date;
        this.staff_id=staff_id;
        this.from_date=from_date;
        this.to_date=to_date;
        this.reason=reason;
        this.status=status;



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
            gridView=inflator.inflate(R.layout.custom_leave_request,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView60);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView61);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView64);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView66);

        tv1.setText(from_date[i]);
        tv2.setText(to_date[i]);
        tv3.setText(reason[i]);
        tv4.setText(status[i]);




        return gridView;
    }
}