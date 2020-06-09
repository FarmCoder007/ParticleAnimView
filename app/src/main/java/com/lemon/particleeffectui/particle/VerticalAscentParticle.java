package com.lemon.particleeffectui.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.lemon.particleeffectui.explosion.Particle;
import com.lemon.particleeffectui.factory.VerticalAscentFactory;

import java.util.Random;

/**
 * 发散性粒子 实体
 *  粒子爆炸 ： 根据 动画执行 进度百分比  不断的移动粒子小点的 位置  不断刷新 绘制
 */

public class VerticalAscentParticle extends Particle {
    static Random random = new Random();
    /**
     * 每个粒子宽度
     */
    float radius = VerticalAscentFactory.PART_WH;
    float alpha;
    /**
     * 整个view的 rect  用于 判断粒子 处于中间位置的左右 发散
     */
    Rect mBound;
    /**
     * 每个粒子的 坐标  只用于 判断粒子处于  view中心的左右
     */
    float ox, oy;

    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public VerticalAscentParticle(int color, float x, float y, Rect bound) {
        super(x, y, color);
        ox = x;
        oy = y;
        mBound = bound;
    }

    /**
     * 根据 动画执行的百分比  计算  当前粒子所处位置   然后 再绘制
     *
     * @param factor 动画的百分比
     */
    @Override
    protected void calculate(float factor) {
        // 当前粒子在 view 中心的右侧
        if (ox > mBound.exactCenterX()) {
            cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat());
        } else {
            cx = cx - factor * random.nextInt(mBound.width()) * (random.nextFloat());
        }
        if (factor <= 0.5) {
            cy = cy - factor * random.nextInt(mBound.height() / 2);
        } else {
            cy = cy + factor * random.nextInt(mBound.height() / 2);
        }

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        // 每个粒子的取到的颜色
        paint.setColor(color);
        //这样透明颜色就不是黑色了
        paint.setAlpha((int) (Color.alpha(color) * alpha));
        canvas.drawCircle(cx, cy, radius, paint);
    }
}