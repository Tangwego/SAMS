package com.hhtc.sams.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhtc.sams.Model.CheckInfo;
import com.hhtc.sams.R;

import java.util.List;

public class CheckInLogAdapter extends RecyclerView.Adapter<CheckInLogAdapter.MyViewHolder> {
    private List<CheckInfo> list;
    private LayoutInflater inflater;
    public CheckInLogAdapter(Activity context, List<CheckInfo> checkInfoList) {
        this.list = checkInfoList;
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
        Log.i("Firlest ",list.get(position).getChkinTime()+list.get(position).getRow()+list.get(position).getClm()
                +list.get(position).getChkinState());
        CheckInfo checkinfo = list.get(position);
        holder.time.setText(checkinfo.getChkinTime());
        holder.row.setText(checkinfo.getRow()+" ");
        holder.clm.setText(checkinfo.getClm()+" ");
        holder.state.setText(checkinfo.getChkinState());
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_rv_record, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView row;
        TextView clm;
        TextView state;
        public MyViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.chkin_tv_time);
            row = (TextView) view.findViewById(R.id.chkin_tv_row);
            clm = (TextView) view.findViewById(R.id.chkin_tv_clm);
            state = (TextView) view.findViewById(R.id.chkin_tv_state);
        }
    }
}