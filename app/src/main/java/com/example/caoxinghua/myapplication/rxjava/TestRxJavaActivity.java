package com.example.caoxinghua.myapplication.rxjava;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;
import com.google.gson.JsonObject;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestRxJavaActivity extends AppCompatActivity {
    private TextView contentTv;
    private Subscription subscription;
    private Student[] students=new Student[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        contentTv= (TextView) findViewById(R.id.contentTv);
        init();
        initStudent();
        testMap();
        testFlatMap();

        initPost();

    }
    private void initPost(){
//        OkHttpClient httpClient=new OkHttpClient();
//        Interceptor interceptor=new CoustomIntercept();
//        httpClient.interceptors().add(interceptor);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://flight.gome.com.cn/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RxInterface rxInterface=retrofit.create(RxInterface.class);
        Subscription postSub=rxInterface.getDataPost("10015","2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        Log.i("xxx","obs:"+jsonObject);

                    }
                });
    }
    private void init(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.115.3.150:8189/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RxInterface rxInterface=retrofit.create(RxInterface.class);

        /**    Call<JsonObject> call=rxInterface.getAdDataByP_Call("10014");
         call.enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        Log.i("xxx","res:"+response.body());
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable throwable) {

        }
        });*/
        //RxJava使用

        subscription=rxInterface.getAdDataByP_Observable("10015")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
//                        Log.i("xxx","obs:"+jsonObject);
                        contentTv.setText(jsonObject.toString());
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                    }
                });
        Observable observable=Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
            }
        });
        Action1<String>  nextAction=new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("xxx","next:"+s);
            }
        };
        Action1<Throwable> errorAction=new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.i("xxx","error");
            }
        };
        Action0 completeAction=new Action0() {
            @Override
            public void call() {
                Log.i("xxx","complete");
            }
        };
        //observer与以上3个action效果一样
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("xxx","next str:"+s);
            }
        };

        String array[]={"first","second","third"};

        observable.just("aa","bb").subscribe(nextAction,errorAction,completeAction);
        Observable.from(array).subscribe(observer);
    }
    private void  initStudent(){
        for(int i=0;i<5;i++){
            Student student=new Student();
            student.setName("student"+i);
            List<Course> courses=new ArrayList<>();
            for(int j=0;j<3;j++){
                Course course=new Course();
                course.setName("cursor"+i+"_"+j);
                courses.add(course);
            }
            student.setCourses(courses);
            students[i]=student;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(subscription!=null&&subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
    private void testMap(){
        Subscriber<String> sub=new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("xxx","name:"+s);
            }
        };
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                }).subscribe(sub);
    }
    private void testFlatMap(){
        Subscriber<Course> sub=new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                 Log.i("xxx","flatMap name:"+course.getName());
            }
        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(sub);

    }
    class  CoustomIntercept implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            HttpUrl httpUrl=request.httpUrl().newBuilder()
                    .addEncodedQueryParameter("name","test params")
                    .build();
            request=request.newBuilder().url(httpUrl).build();
            return chain.proceed(request);
        }
    }
}
