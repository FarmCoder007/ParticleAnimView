package com.lemon.particleeffectui.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.lemon.particleeffectui.explosion.Particle;
import com.lemon.particleeffectui.factory.InnerFallingParticleFactory;

import java.util.Random;

/**
 * Created by Sakura
 */

public class InnerFallingParticle extends Particle {
    static Random random = new Random();
    float radius = InnerFallingParticleFactory.PART_WH;
    float alpha = 1.0f;
    Rect mBound;
    float ox, oy;

    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public InnerFallingParticle(int color, float x, float y, Rect bound) {
        super(x, y, color);
        mBound = bound;
        ox = x;
        oy = y;
    }


    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha)); //这样透明颜色就不是黑色了
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void calculate(float factor) {
        if (factor <= 0.5) {
            if (oy < mBound.exactCenterY()) {
                cy = cy + factor * random.nextInt(mBound.height() / 2);
                if (ox > mBound.exactCenterX()) {
                    cx = cx - factor * random.nextInt(mBound.width() / 2) * (random.nextFloat());
                } else {
                    cx = cx + factor * random.nextInt(mBound.width() / 2) * (random.nextFloat());
                }
            }
        } else {
            cy = cy + factor * random.nextInt(mBound.height() / 2);
            if (ox > mBound.exactCenterX()) {
                cx = cx - factor * random.nextInt(mBound.width() / 2) * (random.nextFloat());
            } else {
                cx = cx + factor * random.nextInt(mBound.width() / 2) * (random.nextFloat());
            }
        }

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}