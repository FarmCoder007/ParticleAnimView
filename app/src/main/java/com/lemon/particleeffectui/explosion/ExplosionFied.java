package com.lemon.particleeffectui.explosion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * author : xu
 * date : 2020/6/8 14:47
 * description :  提供动画场地
 */
public class ExplosionFied extends View {
    /**
     * 同时执行多个动画
     */
    private ArrayList<ExplosionAnimator> explosionAnimators;
    /**
     * 粒子工厂（去生产不同的粒子）
     */
    private ParticleFactory mParticleFactory;
    /**
     * 需要实现爆炸效果的控件
     */
    private OnClickListener onClickListener;

    public ExplosionFied(Context context, ParticleFactory mParticleFactory) {
        super(context);
        explosionAnimators = new ArrayList<>();
        this.mParticleFactory = mParticleFactory;
        // 添加动画区域  到Activity
        attachToActivity();
    }

    /**
     * 添加自身到 DecorView 层
     */
    private void attachToActivity() {
        // 由于找到  执行动画的view的 位置    为了方便找  将 场地  添加到De
        ViewGroup decorView = (ViewGroup) ((Activity) getContext()).getWindow().getDecorView();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        decorView.addView(this, layoutParams);
    }

    /**
     * 添加点击执行动画   监听
     */
    public void addListener(View view) {
        if (view instanceof ViewGroup) {
            // viewGroup  把子view 都添加点击执行动画监听
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                addListener(viewGroup.getChildAt(i));
            }
        } else {
            // view  直接添加点击 执行动画监听
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener());
        }
    }

    private OnClickListener getOnClickListener() {
        if (onClickListener == null) {
            onClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 触发爆炸效果
                    explode(v);
                }
            };
        }
        return onClickListener;
    }

    /**
     * 触发爆炸
     */
    private void explode(final View view) {
        // 先去获取控制的位置   获取view 相对于整个屏幕的位置
        final Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        // 容错处理
        if (rect.width() == 0 || rect.height() == 0) {
            return;
        }
        // 先抖动 再 执行 爆炸动画
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(150);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // -0.5 到 0.5的抖动变化
                // Utils.RANDOM.nextFloat() 是取 0f - 1f 之间 的随机小数
                // Utils.RANDOM.nextFloat() - 0.5f   是 -0.5f 到 0.5f        view.getWidth()  的左右0.5宽度的抖动
                // 范围太大   通过 0.1f  控制 上下左右抖动的尺寸
                view.setTranslationX((Utils.RANDOM.nextFloat() - 0.5f) * view.getWidth() * 0.1f);
                view.setTranslationY((Utils.RANDOM.nextFloat() - 0.5f) * view.getHeight() * 0.1f);
            }
        });
        // 抖动结束时 执行爆炸动画
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 开始粒子爆炸效果
                explosion2(view, rect);
            }
        });
        animator.start();
    }

    /**
     * 开启爆炸效果
     */
    private void explosion2(final View view, Rect rect) {
        // 开启粒子动画
        // 通过动画管理类  传入 指定的 粒子工厂
        final ExplosionAnimator explosionAnimator = new ExplosionAnimator(this, Utils.createBitmapFromView(view), rect, mParticleFactory);
        explosionAnimators.add(explosionAnimator);
        explosionAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                // 动画开始   view 置为不可点击
                view.setClickable(false);
                // 开始  view 慢慢缩小透明
                view.animate().setDuration(150).scaleX(0).scaleY(0).alpha(0).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 动画结束   view 重置 归位
                view.setClickable(true);
                view.animate().setDuration(150).scaleX(1).scaleY(1).alpha(1).start();
                // 移除动画集合
                explosionAnimators.remove(explosionAnimator);
            }
        });
        // 开始动画 时调用绘制  不断绘制移动 直到动画结束
        explosionAnimator.start();
    }

    /**
     * 动画场地  多个动画同时执行
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 为了多个 动画同时执行
        for (ExplosionAnimator explosionAnimator : explosionAnimators) {
            explosionAnimator.draw(canvas);
        }
    }
}
