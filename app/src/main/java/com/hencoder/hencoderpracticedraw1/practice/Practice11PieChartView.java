package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice11PieChartView extends View {

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final static int[] data = {1, 20, 40, 120, 200, 280, 100};
    private final static int[] COLORS = {0xFFFF4346, 0xFFFFC107, 0xFF2196F3, 0xFF009688, 0xFF9C27B0, 0xFF9e9e9e, 0xFFDD8646};
    private final static String[] tagList = {"Froyo", "Gingerbread", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};
    private final static int radius = 150;
    private final static int LEFT = 250;
    private final static int TOP = 60;
    private final static int RIGHT = 550;
    private final static int BOTTOM = 360;
    private final static int INTERVAL = 20;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int[] angleList = new int[data.length];
    private int selectIndex = 5;//选中的item
    private final static int FIRST_LINE = 20;//第一段折线的距离
    private final static int FIRST_Y_LINE = 15;//第一段折线的距离
    private final static int DIVIDE = 10;//左右两边留的间隔
    private final static int TEXT_SIZE = 20;//文字大小

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int centerX = LEFT + radius;
        int centerY = TOP + radius;
        int total = 0;
        for (int value : data) {
            total += value;
        }
        paint.setStyle(Paint.Style.FILL); // 填充模式
        int startAngle = -180;
        for (int i = 0; i < data.length; i++) {
            int angle = 360 * data[i] / total;
            String tag = tagList[i];
            Log.d("PieChartView", "startAngle:" + startAngle + "--angle:" + angle);
            paint.setColor(COLORS[i]);
            int countAngle = startAngle + angle / 2;
            if (countAngle < 0) {
                countAngle = Math.abs(countAngle);
            } else {
                countAngle = 360 - countAngle;
            }
            //countAngle的范围
            //在0-90为第一象限
            // 90-180为第二象限
            // 180-270为第三象限
            // 270-360为第四象限
            float x1 = (float) (centerX + radius * Math.cos(countAngle * 3.14 / 180));
            float y1 = (float) (centerY - radius * Math.sin(countAngle * 3.14 / 180));
            Log.d("PieChartView", "x:" + x1 + "--y:" + y1);
            if (selectIndex == i) {
                canvas.drawArc(LEFT, TOP, RIGHT + INTERVAL, BOTTOM + INTERVAL, startAngle, angle, true, paint);
            } else {
                canvas.drawArc(LEFT, TOP, RIGHT, BOTTOM, startAngle, angle - 3 < 0 ? angle : angle - 3, true, paint);
            }
            paint.setTextSize(TEXT_SIZE);
            float measureWidth = paint.measureText(tag);
            paint.setStrokeWidth(1);
            paint.setColor(Color.WHITE);
            if (countAngle > 0 && countAngle < 90) {
                canvas.drawLine(x1, y1, x1 + FIRST_LINE, y1 - FIRST_Y_LINE, paint);
                float lineX = width - (x1 + FIRST_LINE) - measureWidth - DIVIDE - DIVIDE;
                if (lineX > 0) {
                    canvas.drawLine(x1 + FIRST_LINE, y1 - FIRST_Y_LINE, x1 + FIRST_LINE + lineX, y1 - FIRST_Y_LINE, paint);
                    canvas.drawText(tag, x1 + FIRST_LINE + lineX, y1 - FIRST_Y_LINE + (float) TEXT_SIZE / 2, paint);
                }
            } else if (countAngle > 90 && countAngle < 180) {
                canvas.drawLine(x1, y1, x1 - FIRST_LINE, y1 - FIRST_Y_LINE, paint);
                float lineX = (x1 - FIRST_LINE) - measureWidth - DIVIDE - DIVIDE;
                if (lineX > 0) {
                    canvas.drawLine(x1 - FIRST_LINE, y1 - FIRST_Y_LINE, x1 - FIRST_LINE - lineX, y1 - FIRST_Y_LINE, paint);
                    canvas.drawText(tag, x1 - FIRST_LINE - lineX - measureWidth - DIVIDE, y1 - FIRST_Y_LINE + (float) TEXT_SIZE / 2, paint);
                } else {
                    canvas.drawLine(x1 - FIRST_LINE, y1 - FIRST_Y_LINE, DIVIDE + DIVIDE, y1 - FIRST_Y_LINE, paint);
                    canvas.drawText(tag, DIVIDE + DIVIDE, y1 - FIRST_Y_LINE - (float) TEXT_SIZE / 2, paint);
                }
            } else if (countAngle > 180 && countAngle < 270) {
                canvas.drawLine(x1, y1, x1 - FIRST_LINE, y1 + FIRST_Y_LINE, paint);
                float lineX = (x1 - FIRST_LINE) - measureWidth - DIVIDE - DIVIDE;
                if (lineX > 0) {
                    canvas.drawLine(x1 - FIRST_LINE, y1 + FIRST_Y_LINE, x1 - FIRST_LINE - lineX, y1 + FIRST_Y_LINE, paint);
                    canvas.drawText(tag, x1 - FIRST_LINE - lineX - measureWidth - DIVIDE, y1 + FIRST_Y_LINE + (float) TEXT_SIZE / 2, paint);
                } else {
                    canvas.drawLine(x1 - FIRST_LINE, y1 + FIRST_Y_LINE, DIVIDE + DIVIDE, y1 + FIRST_Y_LINE, paint);
                    canvas.drawText(tag, DIVIDE + DIVIDE, y1 + FIRST_Y_LINE - (float) TEXT_SIZE - 10, paint);
                }
            } else if (countAngle > 270 && countAngle < 360) {
                canvas.drawLine(x1, y1, x1 + FIRST_LINE, y1 + FIRST_Y_LINE, paint);
                float lineX = width - (x1 + FIRST_LINE) - measureWidth - DIVIDE - DIVIDE;
                if (lineX > 0) {
                    canvas.drawLine(x1 + FIRST_LINE, y1 + FIRST_Y_LINE, x1 + FIRST_LINE + lineX, y1 + FIRST_Y_LINE, paint);
                    canvas.drawText(tag, x1 + FIRST_LINE + lineX, y1 + FIRST_Y_LINE + (float) TEXT_SIZE / 2, paint);
                }
            } else if (countAngle == 0) {

            } else if (countAngle == 90) {

            } else if (countAngle == 180) {

            } else if (countAngle == 270) {

            }

            startAngle += angle;
        }

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
    }
}
