package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.loader.common.FileIO;
import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import me.asakura_kukii.siegeweapon.loader.common.format.common.FormatType;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;

public class Material extends Format {

    public Material() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        java.lang.String s = (java.lang.String) FormatType.STRING.f.check(cS, fileName, path, root, obj);
        return checkMaterial(s, fileName, path, root, obj);
    }

    public static Object checkMaterial(java.lang.String s, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        if (org.bukkit.Material.matchMaterial(s) != null) {
            return Objects.requireNonNull(org.bukkit.Material.matchMaterial(s));
        } else {
            FileIO.fileStatusMapper.put(fileName, false);
            FileIO.fileMessageMapper.put(fileName, FileIO.fileMessageMapper.get(fileName) + SiegeWeapon.consolePluginPrefix + root + path + "-" + s + " is not Float\n");
            return obj;
        }
    }
}
