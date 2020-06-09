package com.lemon.particleeffectui.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.lemon.particleeffectui.explosion.Particle;
import com.lemon.particleeffectui.explosion.Utils;
import com.lemon.particleeffectui.factory.NewFactory;

/**
 * author : xu
 * date : 2020/6/9 18:29
 * description : 新的 粒子
 */
public class NewParticle extends Particle {
    /**
     * 半径
     */
    float radius = NewFactory.PARTICLE_RADIUS;
    float alpha;
    Rect mBound;

    public NewParticle(float cx, float cy, int color, Rect mBound) {
        super(cx, cy, color);
        this.mBound = mBound;
    }

    /**
     * 不断计算粒子 坐标
     *
     * @param factor 动画的百分比
     */
    @Override
    protected void calculate(float factor) {
        cx += (Utils.RANDOM.nextFloat() - 0.5) * mBound.width() * factor;
        cy -= (Utils.RANDOM.nextFloat() - 0.7) * mBound.height() * factor;
    }

    /**
     * 绘制粒子
     *
     * @param canvas
     * @param paint
     */
    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color)));
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
