package com.example.caoxinghua.myapplication.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.caoxinghua.myapplication.Entity.RetrofitBean;
import com.example.caoxinghua.myapplication.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        testRetrofit();
    }
    private void testRetrofit(){
      try{
          Retrofit retrofit=new Retrofit.Builder()
                  .client(new OkHttpClient())
                  .baseUrl("http://10.69.10.18:8080/")
                  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
          ApiService apiService=retrofit.create(ApiService.class);
          final Call<List<RetrofitBean>> call=apiService.listRepo(1);
          new Thread(){
              public void run(){
                  try {
                      //同步
                      Response<List<RetrofitBean>> response=call.execute();
                      List<RetrofitBean> list=response.body();
                      Log.i("xxx","body:"+list.size());
                  }catch (Exception e){
                      Log.i("xxx","ex:"+e.toString());
                  }
              }
          }.start();
          //异步  call只能执行一次，需要clone一份
          Call<List<RetrofitBean>> clone = call.clone();
          clone.enqueue(new Callback<List<RetrofitBean>>() {
              @Override
              public void onResponse(Call<List<RetrofitBean>> call, Response<List<RetrofitBean>> response) {
                  List<RetrofitBean> list=response.body();
                  RetrofitBean bean=list.get(0);
                  Log.i("xxx","body:"+bean.getRealname());
              }

              @Override
              public void onFailure(Call<List<RetrofitBean>> call, Throwable t) {
                  Log.i("xxx","exec:"+t.toString());
              }
          });
          Call<RetrofitBean> callUser=apiService.userByid(28);
          callUser.enqueue(new Callback<RetrofitBean>() {
              @Override
              public void onResponse(Call<RetrofitBean> call, Response<RetrofitBean> response) {
                  RetrofitBean bean=response.body();
                  Log.i("xxx","body:"+bean.getMobile());
              }

              @Override
              public void onFailure(Call<RetrofitBean> call, Throwable t) {

              }
          });

          Call<RetrofitBean> queryCall=apiService.queryUserPost(30);
          queryCall.enqueue(new Callback<RetrofitBean>() {
              @Override
              public void onResponse(Call<RetrofitBean> call, Response<RetrofitBean> response) {


                  Log.i("xxx","body1:"+response.toString());
              }

              @Override
              public void onFailure(Call<RetrofitBean> call, Throwable t) {
                  Log.i("xxx","exec1:"+t.toString());
              }
          });
      }catch (Exception e){
          Log.i("xxx","e:"+e.toString());
      }
    }
}
