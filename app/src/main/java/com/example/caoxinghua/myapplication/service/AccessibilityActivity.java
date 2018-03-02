package com.example.caoxinghua.myapplication.service;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.caoxinghua.myapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AccessibilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_accessibility);
        this.findViewById(R.id.activeButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent killIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(killIntent);
            }
        });

        this.findViewById(R.id.installButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //注意sdcard读写权限要动态申请，此处为方便test，直接赋予权限，未做处理
                // TODO Auto-generated method stub
                MyAccessibilityService.INVOKE_TYPE = MyAccessibilityService.TYPE_INSTALL_APP;
                String fileName = Environment.getExternalStorageDirectory() + "/test.apk";
                File installFile = new File(fileName);
                if(installFile.exists()){
                    installFile.delete();
                }
                try {
                    installFile.createNewFile();
                    FileOutputStream out = new FileOutputStream(installFile);
                    byte[] buffer = new byte[512];
                    InputStream in = AccessibilityActivity.this.getAssets().open("test.apk");
                    int count;
                    while((count= in.read(buffer))!=-1){
                        out.write(buffer, 0, count);
                    }
                    in.close();
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = FileProvider.getUriForFile(AccessibilityActivity.this,"org.diql.fileprovider", new File(fileName));//7.0后要单独处理，FileUriExposedException
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//授予provider临时权限
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                startActivity(intent);

            }
        });
        this.findViewById(R.id.uninstallButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                MyAccessibilityService.INVOKE_TYPE = MyAccessibilityService.TYPE_UNINSTALL_APP;
                Uri packageURI = Uri.parse("package:com.example.caoxinghua.applicationc");
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                startActivity(uninstallIntent);
            }
        });
        this.findViewById(R.id.killAppButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                MyAccessibilityService.INVOKE_TYPE = MyAccessibilityService.TYPE_KILL_APP;
                Intent killIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri packageURI = Uri.parse("package:com.example.caoxinghua.applicationc");
                killIntent.setData(packageURI);
                startActivity(killIntent);
            }
        });
    }

}
