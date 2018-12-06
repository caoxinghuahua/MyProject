package com.example.caoxinghua.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.example.caoxinghua.myapplication.defview.DefMainActivity;
import com.gome.ecmall.business.login.ui.activity.NewRegisterActivity;

import junit.framework.Assert;

/**
 * @author caoxinghua on 2018/11/23
 * @email caoxinghua@gomeplus.com
 */
public class TestActivity extends ActivityInstrumentationTestCase2<DefMainActivity> {
    private Context context;
    public TestActivity() {
        super(DefMainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context=InstrumentationRegistry.getTargetContext();
    }
    public void testAct(){
        Intent intent=new Intent(context,DefMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
