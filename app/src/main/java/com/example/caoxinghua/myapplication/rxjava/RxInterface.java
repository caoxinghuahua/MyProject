package com.example.caoxinghua.myapplication.rxjava;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface RxInterface {
    @GET("c/d")
    Observable<JsonObject> getAdDataByP_Observable(@Query("p") String p);
    @GET("c/d")
    Call<JsonObject> getAdDataByP_Call(@Query("p")String p);
    @GET("c/d")
    Observable<JsonObject> getMap(@QueryMap  Map<String,String> map);//传递多个请求参数
    @GET("c/d")
    Observable<JsonObject> getDataByMuliValue(@Query("key") List<String> list);//key相同，多个value
    @GET("c/{id}")
    Call<JsonObject> getPath(@Path("id") String id);//传递相对地址
    @FormUrlEncoded
    @POST("flight")
    Observable<JsonObject> getDataPost(@Field("slotId") String slotId,@Field("requestType") String requestType);
 }
