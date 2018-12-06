package com.example.caoxinghua.myapplication.defview;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.caoxinghua.myapplication.R;

/**
 *
 */
public class DefMainActivity extends AppCompatActivity {
    private   final String TAG=getClass().getName();
    private MyTextView myTextView;
    private ReadMoreTextView readMoreTextView;
    private TextView textView;
    private LinearLayout contentLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_def_main);
        MyView view = (MyView) findViewById(R.id.myView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DefMainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        for (int i = 'A'; i < 'Z'; i++) {
            Button btn = new Button(this);
            btn.setHeight(dp2px(32));
            btn.setTextSize(16);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i - 'A'+1; j++) {
                sb.append((char) i);
            }
            btn.setText(sb.toString());
            flowLayout.addView(btn);
        }
        myTextView = (MyTextView) findViewById(R.id.my_tv);
        readMoreTextView = (ReadMoreTextView) findViewById(R.id.readMore_tv);
        textView = (TextView) findViewById(R.id.tv);
        setTitle();
        test();
        getMemoryInfo();
        testBitmap();
    }

    private int dp2px(float dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);

    }

    private void setTitle() {
        String s = "测试测试123456789012345678901234sssss";
        int textLen = s.length();

        SpannableString spannableString = new SpannableString(s + "网页链接");
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(DefMainActivity.this, "other click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
//                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        }, 0, textLen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(DefMainActivity.this, "span click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        }, s.length(), spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DefMainActivity.this, "textView click", Toast.LENGTH_SHORT).show();

            }
        });
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DefMainActivity.this, "tv click", Toast.LENGTH_SHORT).show();

            }
        });
        if (textLen < 40) {
            myTextView.setMode(2).setText(s);
            myTextView.setListener(new MyTextView.ClickListener() {
                @Override
                public void click() {
                    Toast.makeText(DefMainActivity.this, "net click", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            myTextView.setMode(1).setText(s.substring(0, 40));
            myTextView.setListener(new MyTextView.ClickListener() {
                @Override
                public void click() {
                    Toast.makeText(DefMainActivity.this, "clickssccc", Toast.LENGTH_SHORT).show();
                }
            });
        }
//        } else {
//            SpannableString spannableString;
//            String quanwenStr ="全文";
//
//            spannableString = new SpannableString(s.substring(0, 40) + "..." + quanwenStr);
//
//            spannableString.setSpan(new ClickableSpan() {
//                @Override
//                public void onClick(View widget) {
//                    Toast.makeText(DefMainActivity.this,"click",Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void updateDrawState(TextPaint ds) {
//                    super.updateDrawState(ds);
//                    ds.setColor(Color.BLUE);
//                    ds.setUnderlineText(false);
//                }
//            }, 43, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            myTextView.setText(spannableString);
//            myTextView.setMovementMethod(LinkMovementMethod.getInstance());
//
//       }
    }

    private void test() {
        contentLl = (LinearLayout) findViewById(R.id.contentLl);
        String[] imgUrls = {"http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg",
                "http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg",
                "http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg",
                "http://img.my.csdn.net/uploads/201508/05/1438760756_3304.jpg",
                "http://img.my.csdn.net/uploads/201508/05/1438760755_6715.jpeg",
                "http://img.my.csdn.net/uploads/201508/05/1438760726_5120.jpg"};

        for (int i = 0; i < imgUrls.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Log.i("xxxxxx", "left Margin:" + params.leftMargin + "/" + params.rightMargin);

            if (i != 0) {
                params.leftMargin = 30;
            } else {
                params.leftMargin = 0;
            }
            contentLl.addView(imageView, params);
            params.leftMargin = 0;
            Log.i("xxx", "left Margin:" + params.leftMargin + "/" + params.rightMargin);
            Glide.with(this).load(imgUrls[i]).into(imageView);
        }

    }

    /***
     *多去应用内存信息
     */
    private void getMemoryInfo() {

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        System.out.println(activityManager.getMemoryClass());
        System.out.println(activityManager.getLargeMemoryClass());
        System.out.println(Runtime.getRuntime().maxMemory() / (1024 * 1024));





    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    //inBitmap图片内存复用
    private void  testBitmap(){
        BitmapFactory.Options options = new BitmapFactory.Options();
// 图片复用，这个属性必须设置；
        options.inMutable = true;
// 手动设置缩放比例，使其取整数，方便计算、观察数据；
        options.inDensity = 320;
        options.inTargetDensity = 320;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.grid_camera, options);
// 对象内存地址；
        Log.i(TAG, "bitmap = " + bitmap);
        Log.i(TAG, "bitmap：ByteCount = " + bitmap.getByteCount() + ":::bitmap：AllocationByteCount = " + bitmap.getAllocationByteCount());

// 使用inBitmap属性，这个属性必须设置；
        options.inBitmap = bitmap;
        options.inDensity = 320;
// 设置缩放宽高为原始宽高一半；
        options.inTargetDensity = 160;
        options.inMutable = true;
        Bitmap bitmapReuse = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options);
// 复用对象的内存地址；
        Log.i(TAG, "bitmapReuse = " + bitmapReuse);
        Log.i(TAG, "bitmap：ByteCount = " + bitmap.getByteCount() + ":::bitmap：AllocationByteCount = " + bitmap.getAllocationByteCount());
        Log.i(TAG, "bitmapReuse：ByteCount = " + bitmapReuse.getByteCount() + ":::bitmapReuse：AllocationByteCount = " + bitmapReuse.getAllocationByteCount());

    }
}
