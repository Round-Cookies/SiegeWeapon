package me.asakura_kukii.siegeweapon.loader.common;

import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.utility.colorcode.ColorCode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public abstract class FileIO {
    public static HashMap<String, Boolean> fileStatusMapper = new HashMap<>();
    public static HashMap<String, String> fileMessageMapper = new HashMap<>();
    public static List<String> invalidFileNameList = new ArrayList<>();

    public static void loadAll() {
        for(int i = 0; i < FileType.values().length; i++) {
            FileType fT = FileType.values()[i];

            fT.map.clear();
            fT.subMap.clear();
            if (fT.folder.exists()) {
                fT.fIO.loadDefault(fT);
                for (File f : Objects.requireNonNull(fT.folder.listFiles())) {
                    if (f.getName().contains("yml")) {
                        FileConfiguration fC = YamlConfiguration.loadConfiguration(f);
                        fileStatusMapper.put(fT.folder.getName() + "." + f.getName(), true);
                        loadFile(fC, fT.folder.getName() + "." + f.getName(), fT);
                    }
                }
            } else {
                FileHandler.loadSubfolder(fT.folder.getName());
            }
        }
    }

    public static void loadFile(FileConfiguration fC, String fN, FileType fT) {
        fileMessageMapper.put(fN, "");

        String identifier = "";
        identifier = (String) FormatHandler.checkConfigurationFormat(fC, fN, "identifier", identifier, true);
        if (fT.map.containsKey(identifier)) {
            fileStatusMapper.put(fN, false);
            fileMessageMapper.put(fN, fileMessageMapper.get(fN) + SiegeWeapon.consolePluginPrefix + "identifier [" + identifier + "] is used\n");
        }


        if (fileStatusMapper.get(fN)) {
            fileMessageMapper.put(fN, fileMessageMapper.get(fN) + SiegeWeapon.consolePluginPrefix + "Loading file [" + fN + "]\n");

            //goto specific loader
            FileData fD = fT.fIO.loadData(fC, fN, fT, identifier);
            HashMap<String, Object> subMap = fT.fIO.loadSubData(fC, fN, fT, identifier);
            if (fD != null) {
                fD.fT = fT;
            }

            if (fileStatusMapper.get(fN)) {
                if (fD != null) {
                    fT.map.put(identifier,fD);
                }
                if (subMap != null) {
                    fT.subMap.putAll(subMap);
                }

                SiegeWeapon.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + SiegeWeapon.consolePluginPrefix + ColorCode.ANSI_WHITE + "Loaded " + fT.name().toLowerCase(Locale.ROOT) + " [" + identifier + "]");
            } else {
                invalidFileNameList.add(fN);
                SiegeWeapon.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + SiegeWeapon.consolePluginPrefix + ColorCode.ANSI_RED + "Failed when loading " + fT.name().toLowerCase(Locale.ROOT) + " [" + identifier + "]");
                SiegeWeapon.server.getConsoleSender().sendMessage(ColorCode.ANSI_RED + SiegeWeapon.consolePluginPrefix + "The configuration of [" + ColorCode.ANSI_WHITE + fD.identifier + ColorCode.ANSI_RED + "] couldn't be interpreted\n\n" + fileMessageMapper.get(fN) + ColorCode.ANSI_WHITE);
            }
        }
    }
    public abstract FileData loadData(FileConfiguration fC, String fN, FileType fT, String identifier);

    public abstract HashMap<String, Object> loadSubData(FileConfiguration fC, String fN, FileType fT, String identifier);

    public abstract void loadDefault(FileType fT);
}
