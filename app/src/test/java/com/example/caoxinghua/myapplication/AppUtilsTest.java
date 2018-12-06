package com.example.caoxinghua.myapplication;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author caoxinghua on 2018/11/23
 * @email caoxinghua@gomeplus.com
 */
public class AppUtilsTest {
    @Test
    public void add() throws Exception {
      AppUtils utils=new AppUtils();
        Assert.assertEquals(6,utils.add(2,4));
    }

}