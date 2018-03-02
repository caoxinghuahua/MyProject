package com.example.caoxinghua.myapplication.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.caoxinghua.myapplication.R;

import java.util.List;

public class TestAIDLActivity extends AppCompatActivity implements View.OnClickListener{

    private Button aidlBt,readBt,addBt;
    private  ServiceConnection connection;
    private BookManger bookManger;
    private List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_aidl);
        initView();
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bookManger=BookManger.Stub.asInterface(service);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bookManger=null;
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aidlBt:
                Intent intent=new Intent(this,AidlService.class);
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.readBt:
                if(bookManger!=null){
                    try {
                        books=bookManger.getBooks();
                        Log.i("xxx","book count:"+books.size());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.addBt:
                Book book=new Book();
                book.setName("c++");
                book.setPrice(50);
                if(bookManger!=null){
                    try {
                        bookManger.addBookIn(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    private  void initView(){
        aidlBt= (Button) findViewById(R.id.aidlBt);
        readBt= (Button) findViewById(R.id.readBt);
        addBt= (Button) findViewById(R.id.addBt);
        aidlBt.setOnClickListener(this);
        readBt.setOnClickListener(this);
        addBt.setOnClickListener(this);
    }
}
