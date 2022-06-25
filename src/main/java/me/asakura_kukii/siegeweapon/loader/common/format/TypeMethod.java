package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.handler.item.gun.reload.ReloadType;
import me.asakura_kukii.siegeweapon.handler.nonitem.method.common.MethodType;
import me.asakura_kukii.siegeweapon.loader.common.FileIO;
import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Locale;

public class TypeMethod extends Format {
    public TypeMethod() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        java.lang.String s = cS.getString(path);
        return checkTypeMethod(s, fileName, path, root, obj);
    }

    public static Object checkTypeMethod(java.lang.String s, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        try {
            return MethodType.valueOf(s.toUpperCase(Locale.ROOT));
        } catch (Exception ignored) {
            FileIO.fileStatusMapper.put(fileName, false);
            FileIO.fileMessageMapper.put(fileName, FileIO.fileMessageMapper.get(fileName) + SiegeWeapon.consolePluginPrefix + root + path + "-" + s + " is not MethodType\n");
            return obj;
        }
    }
}
