package com.example.staffmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_assgined_work extends BaseAdapter {
    String[] id,date,staff_id,work;
    private Context context;

    public custom_assgined_work(Context appcontext, String[]id, String[]date, String[]staff_id,
                                String[]work)
    {
        this.context=appcontext;
        this.id=id;
        this.date=date;
        this.staff_id=staff_id;
        this.work=work;



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
            gridView=inflator.inflate(R.layout.custom_assign_work,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView34);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView36);

        tv1.setText(work[i]);
        tv2.setText(date[i]);




        return gridView;
    }
}