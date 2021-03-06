package com.lei.repair.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.lei.repair.bean.TagBean;
import com.lei.repair.bean.UserInfo;
import com.lei.repair.ui.activity.LoginActivity;
import com.runer.liabary.util.Prefs;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by szhua on 2017/7/4/004.
 * github:https://github.com/szhua
 * ImagineCloudEducation
 * AppUtil
 */

public class AppUtil {


    public  static  List<TagBean> getTagTestDatas(int size){
        List<TagBean> datas =new ArrayList<>() ;
        for (int i = 0; i <size ; i++) {
            TagBean tagbean =new TagBean() ;
            tagbean.setTagName("item"+i);
            datas.add(tagbean) ;
        }
        return  datas ;
    }



    public  static  String getVersionName(Context context){
        return  getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    //获得本app的而一些信息
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static UserInfo getUserInfo(Context context){
        UserInfo userInfo =new UserInfo() ;
        userInfo.setId(Prefs.with(context).read("user_id"));
        userInfo.setHead(Prefs.with(context).read("user_head"));
        userInfo.setUser_name(Prefs.with(context).read("user_name"));
        return  userInfo ;
    }

    public static void setUserHeader(Context context ,String userHeader){
        Prefs.with(context).write("user_head",userHeader);
    }

    public static void setUserInfo(Context context ,UserInfo userInfo){
       // if(TextUtils.isEmpty(userInfo.getId()))
        Prefs.with(context).write("user_id",userInfo.getId());
      //  if(TextUtils.isEmpty(userInfo.getUser_name()))
            Prefs.with(context).write("user_name",userInfo.getUser_name());
       // if(TextUtils.isEmpty(userInfo.getHead()))
            Prefs.with(context).write("user_head",userInfo.getHead());
    }

    //获取用户id
    public static  String getUserId(Context context){
        return Prefs.with(context).read("user_id");
    }
    //设置用户id
    public static  void setUserId(Context context,String user_id){
        Prefs.with(context).write("user_id",user_id);
    }

    /*是否登录*/
    public static boolean chckeLogin(Context context ,boolean isToLog){

        if(TextUtils.isEmpty(getUserId(context))){
            //去登陆
            if(isToLog){
                Intent intent =new Intent(context, LoginActivity.class) ;
                context.startActivity(intent);
            }
            return  false ;
        }else{
            return  true ;
        }
    }


    public static List<String> getTestData(){
        ArrayList<String> data =new ArrayList();
        for (int i = 0; i <20; i++) {
            if(i==1) {
                data.add("itemUnit" + i);
            }else{
                data.add("item"+i);
        }
            }
        return  data ;
    }
    public static List<String> getTestData(int size){
        ArrayList<String> data =new ArrayList();
        for (int i = 0; i <size; i++) {
            if(i==1) {
                data.add("itemUnit" + i);
            }else{
                data.add("item"+i);
            }
        }
        return  data ;
    }



    public static String timeParse(long duration) {
        String time = "" ;
        long minute = duration / 60000 ;
        long seconds = duration % 60000 ;
        long second = Math.round((float)seconds/1000) ;
        if( minute < 10 ){
            time += "0" ;
        }
        time += minute+":" ;
        if( second < 10 ){
            time += "0" ;
        }
        time += second ;
        return time ;
    }







}
