package com.hhtc.sams.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhtc.sams.Adapter.CheckInLogAdapter;
import com.hhtc.sams.Adapter.SelectedCusAdapter;
import com.hhtc.sams.R;
import com.hhtc.sams.Utils.HttpUtil;

import java.util.List;

import static com.hhtc.sams.Utils.URLConfig.cus_selected;

/**
 * Created by TANG on 2017/5/7.
 */

public class HomePage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_courses,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        String stuID = getArguments().getString("StuID");
        List list = HttpUtil.getSelCusList(cus_selected,stuID);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.selected_rv_cus);
        LinearLayoutManager LLM = new LinearLayoutManager(this.getActivity());
        LLM.setAutoMeasureEnabled(true);
        rv.setLayoutManager(LLM);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        SelectedCusAdapter adapter = new SelectedCusAdapter(getActivity(),list);
        rv.setAdapter(adapter);
    }
}
