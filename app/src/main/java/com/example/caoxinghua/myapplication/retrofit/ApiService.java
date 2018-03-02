package com.example.caoxinghua.myapplication.retrofit;

import com.example.caoxinghua.myapplication.Entity.RetrofitBean;
import com.gomeplus.meixin.ad.bean.AdBean;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {
    @GET("users/gender/{gender}")
    Call<List<RetrofitBean>>  listRepo(@Path("gender") int gender);
    @GET("users/{userid}")
    Call<RetrofitBean>  userByid(@Path("userid") int userid);
    @GET("users/query")
    Call<RetrofitBean>  queryUser(@Query("userid") int userid);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("users/query")
    Call<RetrofitBean> queryUserPost(@Field("userid") int userid);
    @GET("c/d")
    Call<JsonObject> getAdDataByP(@Query("p") String p);
    @POST("c/d")
    Call<JsonObject> postAdDataByP(@Query("p") String p);
 }
