package com.hhtc.sams.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hhtc.sams.Adapter.CheckInLogAdapter;
import com.hhtc.sams.HomeActivity;
import com.hhtc.sams.Model.CheckInfo;
import com.hhtc.sams.R;
import com.hhtc.sams.Utils.HttpUtil;

import java.util.List;

import static com.hhtc.sams.Utils.URLConfig.cus_chkinlog;
import static com.hhtc.sams.Utils.URLConfig.cus_selected;

/**
 * Created by TANG on 2017/5/7.
 */

public class CheckinLog extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.sign_record,container,false);
        final String stuID = getArguments().getString("StuID");
        Spinner cus = (Spinner) view.findViewById(R.id.record_sp_cus);
        final List list = HttpUtil.getSelectedCourses(cus_selected,stuID);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cus.setAdapter(adapter);
        cus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                String cusID = list.get(position).toString().split(" ")[0];
                List<CheckInfo> checkInfos = HttpUtil.getCheckInLog(cus_chkinlog,stuID,cusID);
                initRecyclerView(view,checkInfos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
    private void initRecyclerView(View view, List<CheckInfo> checkInfos){
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.record_rv_chkinlog);
        LinearLayoutManager LLM = new LinearLayoutManager(this.getActivity());
        LLM.setAutoMeasureEnabled(true);
        rv.setLayoutManager(LLM);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        CheckInLogAdapter adapter = new CheckInLogAdapter(getActivity(),checkInfos);
        rv.setAdapter(adapter);
    }
}
