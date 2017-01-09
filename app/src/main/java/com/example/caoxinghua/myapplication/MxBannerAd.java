package com.example.caoxinghua.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.bumptech.glide.Glide;
import com.gomeplus.meixin.ad.bean.AdBean;
import com.gomeplus.meixin.ad.listener.AdDataListener;
import com.gomeplus.meixin.ad.manger.MXAdsController;
import com.gomeplus.meixin.ad.util.ImageCacheManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by caoxinghua on 2016/10/31.
 */

public class MxBannerAd extends RelativeLayout implements View.OnClickListener {
    private String slotId;
    private Context context;
    public static final int SIZE_320X50 = 0;
    public static final int SIZE_FLEXIBLE = 1;
    public static final int SIZE_FULL_FLEXIBLE = 2;
    public static final int SIZE_WRAP=3;//高度自适应
    private int sizeType;
    private AdBaseListener listener;
    AdBean bean;
    private int width;
    private int height;
    private int minW;

    public MxBannerAd(Context context, String slotId, int sizeType) {
        super(context);
        this.context = context;
        this.slotId = slotId;
        this.sizeType = sizeType;
        minW = minScreenWH(context);
        this.setLayoutParams(new LayoutParams(-1, -2));
        adJustSize();

        loadData();

    }

    public void setListener(AdBaseListener listener) {
        this.listener = listener;
    }

    public void loadData() {
        bean = new AdBean();
        final MXAdsController controller = new MXAdsController(context);
        controller.getAdData(slotId, new AdDataListener() {
            @Override
            public void success(String s) {
                Log.i("xxx", "333");
                listener.onAdDataLoadSuccess();
                parseJson(s);
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                addView(imageView, params);

//              AQuery aQuery = new AQuery(context);
//              aQuery.id(imageView).image("http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy", true, true);
//              Picasso.with(context).load("http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy").into(imageView);
                Glide.with(context).load("http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy").into(imageView);               setClick(imageView);
//					ImageCacheManager.loadImage(MainActivity.this,bean.getResourceUrl(),imageView,null,null);
            }


            public void fail(String error) {
                Log.i("xxx", "error:" + error);
                listener.onAdDataLoadFail();
            }
        });
    }

    private void adJustSize() {
        switch (sizeType) {
            case 2:
                width = Math.min(minW, this.getWidth());
                height = (int) ((double) ((float) minW * 5.0F / 32.0F) + 0.5D);
                break;
            case 1:
                width = this.getWidth();
                height = dip2px(context, 50);
                break;
            case 0:
                width = dip2px(context, 320);
                height = dip2px(context, 50);
                break;
        }
    }


    private void parseJson(String arrayStr) {

        try {
            JSONArray array = new JSONArray(arrayStr);
            int length = array.length();

            for (int i = 0; i < length; i++) {

                JSONObject object = array.optJSONObject(i);
                String slotId = object.optString("slotId");
                JSONArray adContentArray = object.optJSONArray("adContents");

                //后续需要变动
                JSONObject adContentObj = adContentArray.optJSONObject(0);
                String contentType = adContentObj.optString("contentType");
                int width = adContentObj.optInt("width");
                int height = adContentObj.optInt("height");
                JSONObject content = adContentObj.optJSONObject("content");
                bean.setSlotId(slotId);
                bean.setContentType(contentType);
                bean.setWidth(width);
                bean.setHeight(height);
                if (contentType.equals("json")) {
//					JSONObject content=new JSONObject(contentStr);
                    String impressionUrl = content.optString("impressionUrl");
                    String clickUrl = content.optString("clickUrl");
                    String landPage = content.optString("landPage");
                    String resourceType = content.optString("resourceType");
                    String resourceUrl = content.optString("resourceUrl");
                    bean.setResourceType(resourceType);
                    bean.setImpressionUrl(impressionUrl);
                    bean.setResourceType(resourceType);
                    bean.setResourceType(resourceType);
                    bean.setResourceType(resourceType);
                    bean.setResourceUrl(resourceUrl);
                    bean.setResourceType(resourceType);
                    bean.setClickUrl(clickUrl);
                    bean.setLandPage(landPage);
                    bean.setResourceType(resourceType);

                }


            }
        } catch (Exception e) {

        }

    }

    @Override
    public void onClick(View v) {
        listener.onAdClick();
    }

    private void setClick(View view) {
        view.setOnClickListener(this);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private static int minScreenWH(Context ct) {
        WindowManager manager = (WindowManager) ct.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenHeight = outMetrics.heightPixels;
        int screenWidth = outMetrics.widthPixels;
        return Math.min(screenWidth, screenHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("xxx", "w=" + w + "h=" + h);
        Log.i("xxx", "222");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        adJustSize();
        Log.i("xxx", "111");
    }
}
