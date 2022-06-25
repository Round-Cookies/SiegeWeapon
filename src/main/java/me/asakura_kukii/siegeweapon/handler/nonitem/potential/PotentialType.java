package me.asakura_kukii.siegeweapon.handler.nonitem.potential;

import me.asakura_kukii.siegeweapon.handler.nonitem.method.common.MethodHandler;
import me.asakura_kukii.siegeweapon.handler.nonitem.method.damageline.DamageLineHandler;
import me.asakura_kukii.siegeweapon.handler.nonitem.method.particle.CircularParticleHandler;
import me.asakura_kukii.siegeweapon.handler.nonitem.method.projectile.ProjectileHandler;
import me.asakura_kukii.siegeweapon.loader.method.CircularParticleIO;
import me.asakura_kukii.siegeweapon.loader.method.DamageLineIO;
import me.asakura_kukii.siegeweapon.loader.method.ProjectileIO;
import me.asakura_kukii.siegeweapon.loader.method.common.MethodIO;

import java.util.Arrays;
import java.util.List;

public enum PotentialType {
    WEAPON(5,10,0.05,0.25,0.5,true, "wp");

    public String identifier;
    public int row;
    public int column;
    public double visionPercent;
    public double minFillPercent;
    public double maxFillPercent;
    public boolean lAsMainPotential;
    PotentialType(int row, int column, double visionPercent, double minFillPercent, double maxFillPercent, boolean lAsMainPotential, String identifier) {
        this.identifier = identifier;
        this.row = row;
        this.column = column;
        this.visionPercent = visionPercent;
        this.minFillPercent = minFillPercent;
        this.maxFillPercent = maxFillPercent;
        this.lAsMainPotential = lAsMainPotential;
    };
}
