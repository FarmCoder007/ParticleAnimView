package com.lemon.particleeffectui.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.lemon.particleeffectui.explosion.Particle;
import com.lemon.particleeffectui.factory.FlyawayFactory;

import java.util.Random;

/**
 * Created by Sakura
 */
public class FlyawayParticle extends Particle {
    static Random random = new Random();
    float radius = FlyawayFactory.PART_WH;
    float alpha;
    Rect mBound;
    float ox, oy;

    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public FlyawayParticle(int color, float x, float y, Rect bound) {
        super(x, y, color);
        ox = x;
        oy = y;
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
        cx = cx + factor * random.nextInt(mBound.width()) * random.nextFloat();
        cy = cy - factor * random.nextInt(mBound.width()) * random.nextFloat();
        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}