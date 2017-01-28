package com.tplmaps3d.sdk.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tplmaps3d.R;

public class Compass extends ImageView {

    private int direction = 0;

    public Compass(Context context) {
        super(context);
        init();
    }

    public Compass(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.setImageResource(R.drawable.maps_mini_compass);
        this.setScaleType(ScaleType.CENTER_INSIDE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int height = this.getHeight();
        int width = this.getWidth();

        canvas.rotate(direction, width / 2, height / 2);
        super.onDraw(canvas);
    }

    public void setDirection(int direction) {
        this.direction = direction;
        this.invalidate();
    }
}