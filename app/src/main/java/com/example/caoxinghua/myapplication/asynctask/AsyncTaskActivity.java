package com.example.caoxinghua.myapplication.asynctask;

import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncTaskActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        tv= (TextView) findViewById(R.id.tv);
        String []p=new String[]{"1","2"};
        MyTask task=new MyTask();
//       task.execute(p);//串行
        task.executeOnExecutor(Executors.newCachedThreadPool(),new String[]{"a","b"});//并行
        //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);//并行执行
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        AlertDialog dialog=builder.create();
    }
    class MyTask extends AsyncTask<String[],Integer,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.setText("progress is 0%");
        }

        @Override
        protected Void doInBackground(String[]... params) {
            String[] pam=params[0];
            Log.i("xxx","pam:"+pam);

                int p=0;
                int total=100;
                while (true&&p<total){
                    p+=10;
                    publishProgress((int)((p/(float)total)*100));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            tv.setText("progress is "+values[0]+"%");
        }
    }
}
