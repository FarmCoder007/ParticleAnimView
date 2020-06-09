package com.lemon.particleeffectui;

import android.app.Activity;
import android.os.Bundle;

import com.lemon.particleeffectui.explosion.ExplosionFied;
import com.lemon.particleeffectui.explosion.ParticleFactory;
import com.lemon.particleeffectui.factory.BooleanFactory;
import com.lemon.particleeffectui.factory.ExplodeParticleFactory;
import com.lemon.particleeffectui.factory.FallingParticleFactory;
import com.lemon.particleeffectui.factory.FlyawayFactory;
import com.lemon.particleeffectui.factory.InnerFallingParticleFactory;
import com.lemon.particleeffectui.factory.NewFactory;
import com.lemon.particleeffectui.factory.VerticalAscentFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i = 6;
        ParticleFactory particleFactory ;
        switch (i) {
            case 1:
                particleFactory = new BooleanFactory();
                break;
            case 2:
                particleFactory = new ExplodeParticleFactory();
                break;
            case 3:
                particleFactory = new FallingParticleFactory();
                break;
            case 4:
                particleFactory = new FlyawayFactory();
                break;
            case 5:
                particleFactory = new InnerFallingParticleFactory();
                break;
            case 6:
                particleFactory = new NewFactory();
                break;
            case 7:
            default:
                particleFactory = new VerticalAscentFactory();
                break;
        }

        ExplosionFied explosionFied = new ExplosionFied(this, particleFactory);
        explosionFied.addListener(findViewById(R.id.ll));
    }
}
