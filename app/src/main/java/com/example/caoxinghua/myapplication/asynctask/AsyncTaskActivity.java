package com.example.caoxinghua.myapplication.asynctask;

import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;

public class AsyncTaskActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        tv= (TextView) findViewById(R.id.tv);
        MyTask task=new MyTask();
        task.execute();
        //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);//并行执行
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        AlertDialog dialog=builder.create();
    }
    class MyTask extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.setText("progress is 0%");
        }

        @Override
        protected Void doInBackground(Void... params) {
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
