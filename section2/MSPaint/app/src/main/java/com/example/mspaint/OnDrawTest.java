package com.example.mspaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class OnDrawTest extends View {
    public OnDrawTest(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent, null));
        paint.setTextSize(18);
        paint.setStrokeWidth(40);
        canvas.drawCircle(200, 200, 200, paint);
        canvas.drawRect(20, 420, 420, 620, paint);
        canvas.drawLine(20, 840, 400, 1240, paint);
        canvas.drawText("Hello world", 450, 200, paint);
    }
}
