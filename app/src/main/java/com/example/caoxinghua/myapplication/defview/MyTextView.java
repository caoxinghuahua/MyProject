package com.example.caoxinghua.myapplication.defview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;
import com.example.caoxinghua.myapplication.util.Utils;

/**
 * @author caoxinghua on 2018/10/30
 * @email caoxinghua@gomeplus.com
 */
public class MyTextView extends TextView {
    private float LineSpacing = 1.2f;//行与行的间距

    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();
    private Context context;
    private int modeMore=1;//添加更多
    private int modeNet=2;//网页链接
    private int mode;
    private String addStr;

    public ClickListener getListener() {
        return listener;
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    private ClickListener listener;
    public MyTextView(Context context) {
        super(context);
        this.context=context;
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mode=typedArray.getInteger(R.styleable.MyTextView_mode,modeMore);
        addStr=typedArray.getString(R.styleable.MyTextView_addText);
        if(mode==1){
            addStr="全文";
        }else {
            addStr="网页链接";
        }

        paint1.setTextSize(Utils.dp2px(context, 17));
        paint1.setColor(Color.parseColor("#8b8b8b"));
        paint1.setAntiAlias(true);
        paint2.setTextSize(Utils.dp2px(context, 17));
        paint2.setColor(Color.parseColor("#9cc813"));
        paint2.setAntiAlias(true);

    }

    public MyTextView setMode(int mode){
        this.mode=mode;
        return this;
    }
    public MyTextView setAddStr(String addStr){
        this.addStr=addStr;
        return this;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        int textShowWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int lineCount = 0;
        String text = getText().toString();
        float textSize = paint1.getTextSize();
        if (text == null) return;
        char[] textCharArray = text.toCharArray();
        int length = textCharArray.length;
        float drawedWidth = 0;
        float charWidth;
        for (int i = 0; i < length; i++) {
            charWidth = paint1.measureText(textCharArray, i, 1);
            if (textCharArray[i] == '\n') {
                lineCount++;
                drawedWidth = 0;
                continue;
            }
            if (textShowWidth - drawedWidth < charWidth) {
                lineCount++;
                drawedWidth = 0;
            }
            canvas.drawText(textCharArray, i, 1, drawedWidth, (lineCount + 1) * textSize * LineSpacing, paint1);
            drawedWidth += charWidth;
        }

        String appendText = addStr;
        if(mode==modeMore){
            String tp="...";
            float tpLen=paint1.measureText(tp);
            float addTextLength = paint2.measureText(appendText);
            if (textShowWidth - tpLen < drawedWidth) {
                lineCount++;
                canvas.drawText(tp, 0, (lineCount + 1) * textSize * LineSpacing, paint1);
                canvas.drawText(appendText, tpLen, (lineCount + 1) * textSize * LineSpacing, paint2);
            } else if(textShowWidth-tpLen>drawedWidth&&textShowWidth-tpLen-addTextLength<drawedWidth){
                canvas.drawText(tp, 0, (lineCount + 1) * textSize * LineSpacing, paint1);
                lineCount++;
                canvas.drawText(appendText, 0, (lineCount + 1) * textSize * LineSpacing, paint2);
            } else {
                canvas.drawText(appendText, drawedWidth, (lineCount + 1) * textSize * LineSpacing, paint2);
            }
        }else {

            float addTextLength = paint2.measureText(appendText);
            if (textShowWidth - addTextLength < drawedWidth) {
                lineCount++;
                canvas.drawText(appendText, 0, (lineCount + 1) * textSize * LineSpacing, paint2);
            } else {
                canvas.drawText(appendText, drawedWidth, (lineCount + 1) * textSize * LineSpacing, paint2);
            }
        }
        setHeight((int) ((lineCount + 1) * (int) textSize * LineSpacing + 10));


    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        SpannableStringBuilder s = new SpannableStringBuilder(text, 0, text.length());
        s.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
               if(listener!=null){
                   listener.click();
               }

            }
        },0,text.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        super.setText(s, type);
        setMovementMethod(LinkMovementMethod.getInstance());
    }
    interface ClickListener{
         void click();
    }
}