package com.lemon.particleeffectui.explosion;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * author : xu
 * date : 2020/6/8 15:02
 * description :  粒子动画控制类（控制整个控件粒子动画的进度）
 */
public class ExplosionAnimator extends ValueAnimator {
    /**
     * 动画执行时长
     */
    public static int default_duration = 1500;
    /**
     * 位图对应的    行列   粒子对象的引用
     */
    private Particle[][] mParticles;
    /**
     * 粒子工厂
     */
    private ParticleFactory mParticleFactory;
    /**
     * 动画场地
     */
    private View mContainer;
    /**
     * 画笔
     */
    private Paint paint;

    /**
     * 动画控制
     *
     * @param mContainer       动画场地view  在 DecorView 层  添加的动画场地
     * @param bitmap           点击view 生成的bitmap 位图
     * @param bound            view  所占区域
     * @param mParticleFactory 粒子工厂
     */
    public ExplosionAnimator(View mContainer, Bitmap bitmap, Rect bound, ParticleFactory mParticleFactory) {
        this.mParticleFactory = mParticleFactory;
        this.mContainer = mContainer;
        paint = new Paint();
        // 动画百分比
        setFloatValues(0f, 1f);
        // 动画时间
        setDuration(default_duration);
        // 根据位图和 所占区域  生成 粒子 数组
        mParticles = this.mParticleFactory.generateParticles(bitmap, bound);
    }

    /**
     * 绘制  每个粒子运动
     */
    public void draw(Canvas canvas) {
        if (!isStarted()) {
            // 动画不是开启状态    动画绘制关闭
            return;
        }
        // 所有粒子开始运动  遍历 二维数组     绘制的一次
        for (Particle[] mParticle : mParticles) {
            for (Particle particle : mParticle) {
                particle.advance(canvas, paint, (Float) getAnimatedValue());
            }
        }
        mContainer.invalidate();
    }

    @Override
    public void start() {
        super.start();
        mContainer.invalidate();
    }
}
