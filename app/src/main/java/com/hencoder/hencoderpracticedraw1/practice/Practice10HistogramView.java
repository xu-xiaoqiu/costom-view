package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    final static int ICON_WIDTH = 70;
    final static int INTERVAL_WIDTH = 15;
    final static int START_X = 60;
    final static int END_Y = 380;
    final static int[] data = {1, 10, 20, 120, 200, 280, 100};
    final static String[] tagList = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};

    Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int screenWidth = getWidth();

        paint.setColor(Color.WHITE);
        canvas.drawLine(START_X, 50, 60, END_Y, paint);
        canvas.drawLine(START_X, END_Y, 690, END_Y, paint);

        paint.setTextSize(30);
        float width = paint.measureText("直方图");
        canvas.drawText("直方图", (screenWidth - width) / 2, 480, paint);

        paint.setStrokeWidth(ICON_WIDTH);
        paint.setTextSize(20);
        for (int i = 0; i < 7; i++) {
            paint.setColor(Color.GREEN);
            int x = START_X;
            x += INTERVAL_WIDTH * (i + 1);
            x += ICON_WIDTH * i;
            Log.d("drawView", "startX :" + x);
            canvas.drawLine(x + ICON_WIDTH / 2, END_Y - 1, x+ ICON_WIDTH / 2, END_Y - data[i], paint);

            paint.setColor(Color.WHITE);
            String str = tagList[i];
            float textWidth = paint.measureText(str);
            if (textWidth >= ICON_WIDTH) {
                textWidth = ICON_WIDTH;
            }
            canvas.drawText(str, x + (ICON_WIDTH - textWidth) / 2, END_Y + 30, paint);
        }

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
    }
}
