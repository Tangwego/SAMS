package com.hhtc.sams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hhtc.sams.Utils.StateBarUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.hhtc.sams.Utils.URLConfig.adm_login;
import static com.hhtc.sams.Utils.URLConfig.stu_login;
import static com.hhtc.sams.Utils.URLConfig.tch_login;

public class MainActivity extends Activity implements OnClickListener {
    private EditText main_et_username, main_et_password;
    private Button main_btn_forget, main_btn_login, main_btn_register, main_btn_tchreg;
    private Spinner main_spin_type;
    private int sel_type;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StateBarUtil.setWindowStatusBarColor(this,R.color.black);
        setContentView(R.layout.activity_main);
        findView();
        reloadState();
        setClickListener();
    }

    private void reloadState() {
        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = sp.getString("username", " ");
        String password = sp.getString("password", " ");
        int usertype = sp.getInt("usertype", 0);
        //checkLogin(username, password,usertype);
        if (sp.getBoolean("islogin", false)) {
            main_et_username.setText(username.trim());
            main_et_password.setText(password.trim());
            main_spin_type.setSelection(usertype);
            gotoPage(usertype);
        }
    }

    private void findView() {
        //通过findViewById()找到xml中的控件
        main_et_username = (EditText) findViewById(R.id.main_et_username);
        main_et_password = (EditText) findViewById(R.id.main_et_password);
        main_btn_forget = (Button) findViewById(R.id.main_btn_forget);
        main_btn_login = (Button) findViewById(R.id.main_btn_login);
        main_btn_register = (Button) findViewById(R.id.main_btn_stureg);
        main_btn_tchreg = (Button) findViewById(R.id.main_btn_tchreg);
        main_spin_type = (Spinner) findViewById(R.id.main_spin_type);
    }

    private void setClickListener() {
        //设置监听
        main_btn_forget.setOnClickListener(this);
        main_btn_login.setOnClickListener(this);
        main_btn_register.setOnClickListener(this);
        main_btn_tchreg.setOnClickListener(this);
        main_spin_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] usertype = getResources().getStringArray(R.array.login_type);
                sel_type = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sel_type = 0;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_forget:
                Toast.makeText(this, "请联系管理员", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_btn_login:
                String username = main_et_username.getText().toString();
                String password = main_et_password.getText().toString();
                if (username.equals("") && password.equals("")) {
                    Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                } else if (username.equals("")) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogin(username, password, sel_type);
                }
                break;
            case R.id.main_btn_stureg:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("type", "stu");
                startActivity(intent);
                break;
            case R.id.main_btn_tchreg:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("type", "tch");
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private String checkLogin(final String username, final String password, final int usertype) {
        String url = stu_login;
        switch (usertype) {
            case 0:
                url = stu_login;
                break;
            case 1:
                url = tch_login;
                break;
            case 2:
                url = adm_login;
                break;
            default:
                url = stu_login;
                break;
        }
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("ID", username)
                .add("PWD", password)
                .build();
        final Request request = new Request.Builder()
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
                            SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.putInt("usertype", usertype);
                            editor.putBoolean("islogin", true);
                            editor.commit();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    gotoPage(usertype);
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "账号密码不正确", Toast.LENGTH_SHORT).show();
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

    private void gotoPage(int usertype){
        switch (usertype){
            case 0:
                intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("type","student");
                startActivity(intent);
                finish();
                break;
            case 1:
                intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("type","teacher");
                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("type","admin");
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}