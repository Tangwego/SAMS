package com.hhtc.sams;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hhtc.sams.Fragment.CheckinLog;
import com.hhtc.sams.Fragment.HomePage;
import com.hhtc.sams.Model.CheckInfo;
import com.hhtc.sams.Utils.HttpUtil;
import com.hhtc.sams.Utils.StateBarUtil;

import java.util.List;

import static com.hhtc.sams.Utils.URLConfig.cus_chkin;
import static com.hhtc.sams.Utils.URLConfig.cus_sel;
import static com.hhtc.sams.Utils.URLConfig.cus_selected;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private NavigationView navigationView;
    private SharedPreferences sp;
    private Spinner cus;
    private Spinner row;
    private Spinner clm;
    private String StuID;
    private List list;
    private Fragment main;
    private EditText et_cusid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StateBarUtil.setWindowStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_home);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //从login.xml中获取用户名
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        StuID = sp.getString("username", "");
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        choosePage();
        navigationView.setNavigationItemSelectedListener(this);
        main = new HomePage();
        Bundle bundle = new Bundle();
        bundle.putString("StuID", StuID);
        main.setArguments(bundle);
        setDefaultFragment(main, R.id.home_fragment);
    }

    private void choosePage() {
        String type = getIntent().getStringExtra("type");
        navigationView.getMenu().clear();
        switch (type) {
            case "student":
                navigationView.inflateMenu(R.menu.student_drawer);
                break;
            case "teacher":
                navigationView.inflateMenu(R.menu.teacher_drawer);
                break;
            case "admin":
                navigationView.inflateMenu(R.menu.admin_drawer);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSharedPreferences("login", MainActivity.MODE_PRIVATE).getBoolean("islogin", false)) {
            this.finish();
        }
    }

    private void setDefaultFragment(Fragment fragment, int layoutId) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(layoutId, fragment);
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        AlertDialog.Builder builder;
        View view;
        Bundle bundle;
        switch (id) {
            case R.id.stu_home:
                main = new HomePage();
                bundle = new Bundle();
                bundle.putString("StuID", StuID);
                main.setArguments(bundle);
                setDefaultFragment(main, R.id.home_fragment);
                break;
            case R.id.stu_add:
                builder = new AlertDialog.Builder(this);
                view = LayoutInflater.from(this).inflate(R.layout.select_layout, null, false);
                Button sel_cus = (Button) view.findViewById(R.id.sel_btn_cus);
                et_cusid = (EditText) view.findViewById(R.id.sel_et_cusid);
                sel_cus.setOnClickListener(this);
                builder.setView(view);
                builder.show();
                break;
            case R.id.stu_chk:
                //通过获取的用户名去查询学生的选课返回的课程List
                list = HttpUtil.getSelectedCourses(cus_selected, StuID);
                //从资源文件中实例化view
                view = LayoutInflater.from(this).inflate(R.layout.chkin_course, null, false);
                //为Spinner设置adapter
                ArrayAdapter adapter = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cus = (Spinner) view.findViewById(R.id.chkin_spinner_cus);
                row = (Spinner) view.findViewById(R.id.chkin_spinner_row);
                clm = (Spinner) view.findViewById(R.id.chkin_spinner_clm);
                cus.setAdapter(adapter);
                Button chkin = (Button) view.findViewById(R.id.chkin_btn);
                chkin.setOnClickListener(this);
                builder = new AlertDialog.Builder(this);
                builder.setView(view);
                builder.show();
                break;
            case R.id.stu_log:
                main = new CheckinLog();
                bundle = new Bundle();
                bundle.putString("StuID", StuID);
                main.setArguments(bundle);
                setDefaultFragment(main, R.id.home_fragment);
                break;
            case R.id.stu_res:
                break;
            case R.id.stu_job:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_logout:
                sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("islogin", false);
                editor.commit();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        String result;
        switch (v.getId()) {
            case R.id.chkin_btn:
                int CusID = Integer.parseInt(list.get(cus.getSelectedItemPosition()).toString().split(" ")[0]);
                int r = row.getSelectedItemPosition() + 1;
                int c = clm.getSelectedItemPosition() + 1;
                CheckInfo ci = new CheckInfo();
                ci.setCusID(CusID);
                ci.setStuID(StuID);
                ci.setRow(r);
                ci.setClm(c);
                ci.setChkinState("签到成功");
                result = HttpUtil.getResult(cus_chkin, ci);
                if (result.equals("SUCCESS")) {
                    Toast.makeText(this, "签到成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "签到失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sel_btn_cus:
                result = HttpUtil.selCus(cus_sel, StuID, et_cusid.getText().toString());
                Toast.makeText(this, result.equals("SUCCESS") ? "选课成功" : "选课失败", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
