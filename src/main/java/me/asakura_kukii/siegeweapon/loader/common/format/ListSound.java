package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.handler.nonitem.particle.ParticleData;
import me.asakura_kukii.siegeweapon.handler.nonitem.sound.SoundData;
import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ListSound extends Format {
    public ListSound() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        List<java.lang.String> sL = cS.getStringList(path);

        List<SoundData> sDL = new ArrayList<>();
        for (java.lang.String s : sL) {
            SoundData sD = (SoundData) Sound.checkSound(s, fileName, path, root, obj);
            sDL.add(sD);
        }
        return sDL;
    }
}
