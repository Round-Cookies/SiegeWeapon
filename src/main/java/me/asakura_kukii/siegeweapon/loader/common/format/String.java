package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import org.bukkit.configuration.ConfigurationSection;

public class String extends Format {

    public String() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        return cS.getString(path);
    }
}
