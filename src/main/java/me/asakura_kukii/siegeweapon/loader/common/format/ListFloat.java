package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import me.asakura_kukii.siegeweapon.loader.common.format.common.FormatType;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ListFloat extends Format {
    public ListFloat() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        List<java.lang.String> sL = cS.getStringList(path);

        List<java.lang.Float> fL = new ArrayList<>();
        for (java.lang.String s : sL) {
            java.lang.Float f = (java.lang.Float) Float.checkFloat(s, fileName, path, root, obj);
            fL.add(f);
        }
        return fL;
    }
}
