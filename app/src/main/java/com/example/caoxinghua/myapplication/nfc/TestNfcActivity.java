package com.example.caoxinghua.myapplication.nfc;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;

public class TestNfcActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nfc);
        nfcAdapter=NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null){
            Log.i("xxx","not support nfc");
        }else {
            if(!nfcAdapter.isEnabled()){
                Toast.makeText(this,"open the nfc",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())){
            Log.i("xxx","xx"+getIntent().getAction());

        }
    }
}
