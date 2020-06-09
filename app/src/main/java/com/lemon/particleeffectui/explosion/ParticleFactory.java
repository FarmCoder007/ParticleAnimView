package com.lemon.particleeffectui.explosion;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * author : xu
 * date : 2020/6/8 14:55
 * description : 粒子工厂  生成粒子
 */
public abstract class ParticleFactory {
    /**
     *  生成粒子  二维矩阵  是 对应 控件位图的颜色矩阵   横纵坐标 每个粒子一个颜色
     * @param bitmap  根据控件位图 生成粒子
     * @param bound  当前控件 所在的矩形区域
     * @return  粒子工厂 返回 ： 根据view位图生成的   行列粒子的二维粒子数组
     */
    public abstract Particle[][] generateParticles(Bitmap bitmap, Rect bound);


}
