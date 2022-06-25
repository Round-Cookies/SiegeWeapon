package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import me.asakura_kukii.siegeweapon.loader.common.format.common.FormatType;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ListColoredString extends Format {

    public ListColoredString() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        List<java.lang.String> sL = cS.getStringList(path);

        List<java.lang.String> cSL = new ArrayList<>();
        for (java.lang.String s : sL) {
            cSL.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return cSL;
    }
}
