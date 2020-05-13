package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.R;

public class Practice1DrawColorView extends View {

    public Practice1DrawColorView(Context context) {
        super(context);
        init();
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Paint paint = new Paint();
    Shader shader = new RadialGradient(400, 400, 100, new int[]{
            Color.parseColor("#E91E63"), Color.parseColor("#2196F3")}, null, Shader.TileMode.CLAMP);


    private void init() {
        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap  bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        BitmapShader bitmapShader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(new ComposeShader(bitmapShader,bitmapShader2, PorterDuff.Mode.DST_OUT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(0, 0, 300,300,20,20, paint);


//        canvas.drawColor(Color.YELLOW);
//        练习内容：使用 canvas.drawColor() 方法把 View 涂成黄色
//        黄色： Color.YELLOW
    }
}
