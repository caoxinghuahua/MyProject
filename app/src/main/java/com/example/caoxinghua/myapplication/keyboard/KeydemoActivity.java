package com.example.caoxinghua.myapplication.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.caoxinghua.myapplication.R;

public class KeydemoActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editText1;
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keydemo);
        editText=(EditText) findViewById(R.id.edit);
        editText1=(EditText) findViewById(R.id.edit1);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new KeyBoardUtil(KeydemoActivity.this,KeydemoActivity.this,editText).showKeyboard();
                return false;
            }
        });
        editText1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inputback=editText1.getInputType();
                editText1.setInputType(InputType.TYPE_NULL);
                new KeyBoardUtil(KeydemoActivity.this,KeydemoActivity.this,editText1).showKeyboard();
                editText1.setInputType(inputback);
                return false;
            }
        });
        viewFlipper=(ViewFlipper) findViewById(R.id.viewFlipper);
//       for(int i=0;i<5;i++){
//           TextView textView=new TextView(this);
//            textView.setText("title"+i);
//           viewFlipper.addView(textView);
//        }
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.up_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.down_out));
        viewFlipper.startFlipping();
    }
}
