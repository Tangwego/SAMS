package com.hhtc.sams.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhtc.sams.Model.CheckInfo;
import com.hhtc.sams.Model.Course;
import com.hhtc.sams.R;

import java.util.List;

public class SelectedCusAdapter extends RecyclerView.Adapter<SelectedCusAdapter.MyViewHolder> {
    private List<Course> list;
    private LayoutInflater inflater;
    public SelectedCusAdapter(Activity context, List<Course> cus) {
        this.list = cus;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.i("-----", "onBindViewHolder: "+list.get(position).toString());
        Course cus = list.get(position);
        holder.ID.setText(" "+cus.getID());
        holder.Name.setText(cus.getName());
        holder.Time.setText(cus.getRemark()+" "+cus.getTchID());
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_rv_courses, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ID;
        TextView Name;
        TextView Time;
        public MyViewHolder(View view) {
            super(view);
            ID = (TextView) view.findViewById(R.id.cus_tv_cusid);
            Name = (TextView) view.findViewById(R.id.cus_tv_cusname);
            Time = (TextView) view.findViewById(R.id.cus_tv_time);
        }
    }
}