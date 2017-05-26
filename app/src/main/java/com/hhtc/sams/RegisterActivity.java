package com.hhtc.sams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hhtc.sams.Model.Student;
import com.hhtc.sams.Model.Teacher;
import com.hhtc.sams.Utils.HttpUtil;
import com.hhtc.sams.Utils.StateBarUtil;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.hhtc.sams.Utils.URLConfig.stu_reg;
import static com.hhtc.sams.Utils.URLConfig.tch_reg;

public class RegisterActivity extends Activity implements OnClickListener{
    private EditText stu_et_name;
    private EditText stu_et_gender;
    private EditText stu_et_id;
    private EditText stu_et_pwd;
    private EditText stu_et_birthday;
    private EditText stu_et_year;

    private Button stu_btn_reg;
    private Button stu_btn_back;

    private String ID;
    private String PWD;
    private String Name;
    private String Gender;
    private String YoB;
    private String ErlYear;
    private String Title;
    private String Remark;

    private Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StateBarUtil.setWindowStatusBarColor(this,R.color.black);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        String url = getIntent().getStringExtra("type");
        switch (url){
            case "stu":
                setStuReg();
                break;
            case "tch":
                setTechReg();
                break;
            default:
                break;
        }
    }
    //设置学生注册页面
    private void setStuReg(){
        setNull();
        setContentView(R.layout.student_reg);
        stu_et_name = (EditText) findViewById(R.id.stu_et_name);
        stu_et_gender = (EditText) findViewById(R.id.stu_et_gender);
        stu_et_id = (EditText) findViewById(R.id.stu_et_id);
        stu_et_pwd = (EditText) findViewById(R.id.stu_et_pwd);
        stu_et_birthday = (EditText) findViewById(R.id.stu_et_birthday);
        stu_et_year = (EditText) findViewById(R.id.stu_et_year);
        stu_btn_reg = (Button) findViewById(R.id.stu_btn_reg);
        stu_btn_back = (Button) findViewById(R.id.stu_btn_back);
        stu_btn_reg.setOnClickListener(this);
        stu_btn_back.setOnClickListener(this);
    }

    //设置教师注册页面
    private void setTechReg(){
        setNull();
        setContentView(R.layout.teacher_reg);
        stu_et_name = (EditText) findViewById(R.id.tech_et_name);
        stu_et_gender = (EditText) findViewById(R.id.tech_et_gender);
        stu_et_id = (EditText) findViewById(R.id.tech_et_id);
        stu_et_pwd = (EditText) findViewById(R.id.tech_et_pwd);
        stu_et_birthday = (EditText) findViewById(R.id.tech_et_birthday);
        stu_et_year = (EditText) findViewById(R.id.tech_et_title);
        stu_btn_reg = (Button) findViewById(R.id.tech_btn_reg);
        stu_btn_back = (Button) findViewById(R.id.tech_btn_back);
        stu_btn_reg.setOnClickListener(this);
        stu_btn_back.setOnClickListener(this);
    }

    private void setNull(){
        stu_et_name = null;
        stu_et_gender = null;
        stu_et_id = null;
        stu_et_pwd = null;
        stu_et_birthday = null;
        stu_et_year = null;
        stu_btn_reg = null;
        stu_btn_back = null;
    }
    private void clear(){
        ID = null;
        PWD = null;
        Name = null;
        Gender = null;
        YoB = null;
        ErlYear = null;
        Title = null;
        Remark = null;
    }

    private void getInfo(){
        ID = stu_et_id.getText().toString();
        PWD = stu_et_pwd.getText().toString();
        Name = stu_et_name.getText().toString();
        Gender = stu_et_gender.getText().toString();
        YoB = stu_et_birthday.getText().toString();
        ErlYear = stu_et_year.getText().toString();
        Title = stu_et_year.getText().toString();
        Remark= " ";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stu_btn_reg:
                getInfo();
                Student stu = new Student(ID,PWD,Name,Gender,YoB,ErlYear,Remark);
                register(stu,0);
                break;
            case R.id.stu_btn_back:
                super.onBackPressed();
                break;
            case R.id.tech_btn_reg:
                getInfo();
                Teacher tch = new Teacher(ID,PWD,Name,Gender,YoB,Title,Remark);
                register(tch,0);
                break;
            case R.id.tech_btn_back:
                super.onBackPressed();
                break;
        }
    }

    private String register(final Object obj, final int usertype) {
        String url = "";
        RequestBody body = null;
        switch (usertype) {
            case 0:
                url = stu_reg;
                Student stu = (Student) obj;
                body = new FormBody.Builder()
                        .add("ID",stu.getID())
                        .add("PWD",stu.getPWD())
                        .add("Name",stu.getName())
                        .add("Gender",stu.getGender())
                        .add("YoB",stu.getYoB())
                        .add("ErlYear",stu.getErlYear())
                        .add("Remark",stu.getRemark())
                        .build();
                break;
            case 1:
                url = tch_reg;
                Teacher tch = (Teacher) obj;
                body = new FormBody.Builder()
                        .add("ID",tch.getID())
                        .add("PWD",tch.getPWD())
                        .add("Name",tch.getName())
                        .add("Gender",tch.getGender())
                        .add("YoB",tch.getYoB())
                        .add("Title",tch.getTitle())
                        .add("Remark",tch.getRemark())
                        .build();
                break;
            default:
                break;
        }
        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .addHeader("Content-Type","utf-8")
                .url(url)
                .post(body)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Log.i("------>", result);
                        if (result.equals("SUCCESS")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }).start();
        return "FAILURE";
    }
}
