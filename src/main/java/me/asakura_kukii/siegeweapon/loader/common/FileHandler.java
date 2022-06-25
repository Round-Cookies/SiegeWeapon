package me.asakura_kukii.siegeweapon.loader.common;

import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.utility.colorcode.ColorCode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class FileHandler {
    public FileConfiguration fC;
    public File f;


    public FileHandler(File f2) {
        f = f2;
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fC = YamlConfiguration.loadConfiguration(f);
    }

    public static File loadSubfolder(String name) {
        if (new File(SiegeWeapon.pluginFolder, name).exists()) {
            File newFolder = new File(SiegeWeapon.pluginFolder, name);
            if (!newFolder.isDirectory()) {
                if(newFolder.delete()) {
                    if(newFolder.mkdir()) {
                        SiegeWeapon.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + SiegeWeapon.consolePluginPrefix + ColorCode.ANSI_WHITE + "Creating directory [" + name + "]");
                        return newFolder;
                    }
                }
            }
            return newFolder;
        } else {
            File newFolder = new File(SiegeWeapon.pluginFolder, name);
            if (newFolder.mkdir()) {
                SiegeWeapon.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + SiegeWeapon.consolePluginPrefix + ColorCode.ANSI_WHITE + "Creating directory [" + name + "]");
                return newFolder;
            }
            return null;
        }
    }

    public void set(String path, Object val) {
        fC.set(path, val);
        save();
    }
    public void save(){
        try {
            fC.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void copyFileFromResource(String resource, File dest) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = SiegeWeapon.pluginInstance.getResource(resource);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while (true) {
                assert is != null;
                if (!((length = is.read(buffer)) > 0)) break;
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (Exception ignored) {
        }
    }
}
