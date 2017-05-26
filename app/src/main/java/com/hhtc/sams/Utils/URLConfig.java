package com.hhtc.sams.Utils;

/**
 * Created by TANG on 2017/5/3.
 */

public class URLConfig {
    /**
     * 1.首先 要封装一个基类。这样可以节省很多代码

     2.能用三目表达式的时候 就用三目表达式

     3.当一个方法太长了  需要将这个方法   分成一个一个的方法

     4.activity 只做view操作，业务处理；；想硬件的初始化之类的代码  可以丢到helper类中。。面向对象  把对象自己的代码放进来   其它的可以放在  间接类中
     */
    public static final String base_url = "http://114.115.142.22/sams-server";
    /**
     * 用户注册
     */
    public static final String stu_reg = base_url+"/user?type=reg_stu";
    public static final String tch_reg = base_url+"/user?type=reg_tch";
    /**
     * 用户登录
     */
    public static final String stu_login = base_url+"/user?type=login_stu";
    public static final String tch_login = base_url+"/user?type=login_tch";
    public static final String adm_login = base_url+"/user?type=login_adm";
    /**
     * 用户信息修改
     */
    public static final String stu_update = base_url+"/user?type=update_stu";
    public static final String tch_update = base_url+"/user?type=update_tch";
    public static final String adm_update = base_url+"/user?type=update_adm";
    /**
     * 课程操作
     */
    public static final String cus_add = base_url+"/cus?type=add";
    public static final String cus_qry = base_url+"/cus?type=query";
    public static final String cus_upd = base_url+"/cus?type=update";
    public static final String cus_del = base_url+"/cus?type=delete";
    /**
     * 选课
     */
    public static final String cus_sel = base_url+"/cus?type=sel";
    public static final String cus_selected = base_url+"/cus?type=selected";
    /**
     * 签到
     */
    public static final String cus_chkin = base_url+"/cus?type=chkin";
    public static final String cus_chkinlog = base_url+"/cus?type=chkinlog";

}
