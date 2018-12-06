package com.example.caoxinghua.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import static android.content.pm.PackageManager.*;

public class AppUtils {
    public static String getVersionName(Context context){
        PackageManager packageManager=context.getPackageManager();
        try {
            PackageInfo  packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
//        return "0";
    }
    public int add(int a,int b){
        return a+b;
    }
}
