package me.asakura_kukii.siegeweapon.loader.common.format;

import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.loader.common.FileType;
import me.asakura_kukii.siegeweapon.handler.nonitem.sound.SoundData;
import me.asakura_kukii.siegeweapon.loader.common.FileIO;
import me.asakura_kukii.siegeweapon.loader.common.format.common.Format;
import org.bukkit.configuration.ConfigurationSection;

public class Sound extends Format {
    public Sound() {}

    @Override
    public Object check(ConfigurationSection cS, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        java.lang.String s = cS.getString(path);
        return checkSound(s, fileName, path, root, obj);
    }

    public static Object checkSound(java.lang.String s, java.lang.String fileName, java.lang.String path, java.lang.String root, Object obj) {
        if (FileType.SOUND.map.containsKey(s)) {
            return (SoundData) FileType.SOUND.map.get(s);
        } else {
            FileIO.fileStatusMapper.put(fileName, false);
            FileIO.fileMessageMapper.put(fileName, FileIO.fileMessageMapper.get(fileName) + SiegeWeapon.consolePluginPrefix + root + path + "-" + s + " is not SoundData\n");
            return obj;
        }
    }
}
