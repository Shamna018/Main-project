package com.example.staffmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_resignation_status extends BaseAdapter {

    String[] id,date,from_date,reason,other_reason,status,STAFF_id;
    Context context;

    public custom_view_resignation_status(Context context,String[]id,String[]date,String[]from_date,String[]reason,String[]other_reason,String[]status,String[]STAFF_id){
        this.context=context;
        this.id=id;
        this.date=date;
        this.from_date=from_date;
        this.reason=reason;
        this.other_reason=other_reason;
        this.status=status;
        this.STAFF_id=STAFF_id;
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
            gridView=inflator.inflate(R.layout.custom_view_resignation_status,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView60);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView61);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView64);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView66);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView68);

        tv1.setText(date[i]);
        tv2.setText(from_date[i]);
        tv3.setText(reason[i]);
        tv4.setText(other_reason[i]);
        tv5.setText(status[i]);




        return gridView;
    }
}
