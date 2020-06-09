package com.lemon.particleeffectui.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.lemon.particleeffectui.explosion.Particle;
import com.lemon.particleeffectui.factory.BooleanFactory;

import java.util.Random;

/**
 * Created by Sakura
 */

public class BooleanParticle extends Particle {
    static Random random = new Random();
    float radius = BooleanFactory.PART_WH;
    float alpha;
    Rect mBound;

    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public BooleanParticle(int color, float x, float y, Rect bound) {
        super(x, y, color);
        mBound = bound;
    }


    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha)); //这样透明颜色就不是黑色了
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void calculate(float factor) {
        float dx = factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        float dy = factor * random.nextInt(mBound.height()) * (random.nextFloat() - 0.5f);
        Log.e("TAG", "---------------当前进度为：" + factor + "---dx:" + dx + "--dy:" + dy);
        // 横纵坐标变换
        cx = cx + dx;
        cy = cy + dy;

        // 半径变换
        radius = radius - factor * random.nextInt(2);
        // 透明度变换
        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
