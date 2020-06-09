package com.lemon.particleeffectui.factory;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.lemon.particleeffectui.explosion.Particle;
import com.lemon.particleeffectui.explosion.ParticleFactory;
import com.lemon.particleeffectui.particle.NewParticle;
import com.lemon.particleeffectui.particle.VerticalAscentParticle;

/**
 * author : xu
 * date : 2020/6/9 18:11
 * description :
 */
public class NewFactory extends ParticleFactory {
    //默认小球宽高
    public static final int PARTICLE_RADIUS = 8;

    @Override
    public Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        // 根据 待执行动画view 区域 rect  计算行列 能放的粒子数
        // view 所占区域 rect 宽  高
        int w = bound.width();
        int h = bound.height();
        //横向粒子 个数
        int partW_Count = w / PARTICLE_RADIUS;
        //竖向粒子个数
        int partH_Count = h / PARTICLE_RADIUS;

        // 根据行列粒子数 算 一行粒子 所占bitmap 宽度
        int bitmap_part_w = bitmap.getWidth() / partW_Count;
        // 根据行列粒子数 算 一行粒子 所占bitmap 宽度
        int bitmap_part_h = bitmap.getHeight() / partH_Count;

        // view的bitmap  对应的二维粒子数组
        Particle[][] particles = new Particle[partH_Count][partW_Count];
        // 遍历  二维数组  实例化每个粒子数据
        for (int row = 0; row < partH_Count; row++) {
            // 列
            for (int column = 0; column < partW_Count; column++) {
                //取得当前粒子所在像素位置的颜色      横坐标： 列数* 每列占宽度    纵坐标  ：行数* 每行占高度
                int color = bitmap.getPixel(column * bitmap_part_w, row * bitmap_part_h);
                // 计算 当前粒子 相对于 屏幕的  xy
                float x = bound.left + VerticalAscentFactory.PART_WH * column;
                float y = bound.top + VerticalAscentFactory.PART_WH * row;
                particles[row][column] = new NewParticle(x, y, color, bound);
            }
        }
        return particles;
    }
}
