package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ListBoolean extends Format {
    public ListBoolean() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        List<java.lang.String> sL = cS.getStringList(path);

        List<java.lang.Boolean> bL = new ArrayList<>();
        for (java.lang.String s : sL) {
            java.lang.Boolean b = (java.lang.Boolean) Boolean.checkBoolean(s, fileName, path, root, obj);
            bL.add(b);
        }
        return bL;
    }
}
