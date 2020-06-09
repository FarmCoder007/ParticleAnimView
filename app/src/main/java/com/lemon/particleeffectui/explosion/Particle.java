package com.lemon.particleeffectui.explosion;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * author : xu
 * date : 2020/6/8 14:49
 * description :  粒子的属性
 */
public abstract class Particle {
    // 属性
    public float cx;
    public float cy;
    public int color;

    public Particle(float cx, float cy, int color) {
        this.cx = cx;
        this.cy = cy;
        this.color = color;
    }

    /**
     * 计算 每个粒子的运动轨迹   从而产生效果
     *
     * @param factor 动画的百分比
     */
    protected abstract void calculate(float factor);

    // 绘制
    protected abstract void draw(Canvas canvas, Paint paint);

    /**
     * 根据动画执行的百分比   逐帧绘制
     */
    public void advance(Canvas canvas, Paint paint, float factor) {
        // 计算
        calculate(factor);
        // 绘制
        draw(canvas, paint);
    }
}
