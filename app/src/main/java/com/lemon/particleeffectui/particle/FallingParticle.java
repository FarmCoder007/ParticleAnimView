package com.lemon.particleeffectui.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.lemon.particleeffectui.explosion.Particle;
import com.lemon.particleeffectui.factory.FallingParticleFactory;

import java.util.Random;

/**
 * Created by Sakura
 */

public class FallingParticle extends Particle {
    static Random random = new Random();
    float radius = FallingParticleFactory.PART_WH;
    float alpha = 1.0f;
    Rect mBound;
    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public FallingParticle(int color, float x, float y, Rect bound) {
        super( x, y,color);
        mBound = bound;
    }


    @Override
    protected void draw(Canvas canvas, Paint paint){
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha)); //这样透明颜色就不是黑色了
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void calculate(float factor){
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(mBound.height() / 2);

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
