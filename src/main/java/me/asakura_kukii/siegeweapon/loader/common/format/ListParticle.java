package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.handler.nonitem.particle.ParticleData;
import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import me.asakura_kukii.siegeweapon.utility.coodinate.Vector3D;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ListParticle extends Format {
    public ListParticle() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        List<java.lang.String> sL = cS.getStringList(path);

        List<ParticleData> pDL = new ArrayList<>();
        for (java.lang.String s : sL) {
            ParticleData pD = (ParticleData) Particle.checkParticle(s, fileName, path, root, obj);
            pDL.add(pD);
        }
        return pDL;
    }
}
