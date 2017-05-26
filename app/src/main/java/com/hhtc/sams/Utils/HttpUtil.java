package com.hhtc.sams.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.hhtc.sams.Model.CheckInfo;
import com.hhtc.sams.Model.Course;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public static List getSelectedCourses(String url, String ID) {
        final List courses = new ArrayList();
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("ID", "1001")
                .add("StuID", ID)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Thread mythread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String info = response.body().string();
                        if (response.isSuccessful()) {
                            JSONArray array = new JSONArray(info);
                            JSONObject obj = null;
                            for (int i = 0; i < array.length(); i++) {
                                obj = (JSONObject) array.getJSONObject(i);
                                if (obj.getInt("ChkinFlg") == 1) {
                                    String row = obj.getInt("ID") + " " + obj.getString("Name") + " " + obj.getString("TchID");
                                    courses.add(i, row);
                                }
                            }
                        } else {
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            });
            mythread.start();
            mythread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static List<Course> getSelCusList(String url, String ID) {
        final List<Course> courses = new ArrayList<Course>();
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("ID", "1001")
                .add("StuID", ID)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Thread mythread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String info = response.body().string();
                        if (response.isSuccessful()) {
                            JSONArray array = new JSONArray(info);
                            JSONObject obj = null;
                            Course cus = null;
                            for (int i = 0; i < array.length(); i++) {
                                obj = (JSONObject) array.getJSONObject(i);
                                if (obj.getInt("ChkinFlg") == 1) {
                                    cus = new Course(obj.getInt("ID"),obj.getString("Name")
                                            ,obj.getString("TchID"),obj.getInt("ChkinFlg")
                                            ,obj.getString("Remark"));
                                    courses.add(i, cus);
                                }
                            }
                        } else {
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            });
            mythread.start();
            mythread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static List<CheckInfo> getCheckInLog(String url, String StuID, String CusID) {
        final List<CheckInfo> checkInfo = new ArrayList<CheckInfo>();
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("ID", "1001")
                .add("StuID", StuID)
                .add("cusID", CusID)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Thread mythread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String info = response.body().string();
                        if (response.isSuccessful()) {
                            JSONArray array = new JSONArray(info);
                            JSONObject obj = null;
                            CheckInfo ci = null;
                            for (int i = 0; i < array.length(); i++) {
                                obj = (JSONObject) array.getJSONObject(i);
                                ci = new CheckInfo();
                                ci.setChkinTime(obj.getString("ChkinTime"));
                                ci.setRow(obj.getInt("Row"));
                                ci.setClm(obj.getInt("Clm"));
                                ci.setChkinState(obj.getString("ChkinState"));
                                checkInfo.add(i, ci);
                            }
                        } else {
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            });
            mythread.start();
            mythread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return checkInfo;
    }

    public static String result = "FAILURE";

    public static String getResult(String url, CheckInfo obj) {
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("ID", "1001")
                .add("CusID", String.valueOf(obj.getCusID()))
                .add("StuID", obj.getStuID())
                .add("Row", String.valueOf(obj.getRow()))
                .add("Clm", String.valueOf(obj.getClm()))
                .add("chkinState", obj.getChkinState())
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Thread mythread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String info = response.body().string();
                        if (response.isSuccessful()) {
                            result = info;
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            });
            mythread.start();
            mythread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String selCus(String url, String StuID, String CusID) {
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("ID", "1001")
                .add("CusID", CusID)
                .add("StuID", StuID)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Thread mythread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String info = response.body().string();
                        if (response.isSuccessful()) {
                            result = info;
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            });
            mythread.start();
            mythread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
