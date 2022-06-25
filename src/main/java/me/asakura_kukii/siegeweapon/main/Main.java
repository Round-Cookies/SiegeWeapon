package me.asakura_kukii.siegeweapon.main;

import me.asakura_kukii.siegeweapon.utility.colorcode.ColorCode;
import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.utility.coodinate.Vector3D;
import me.asakura_kukii.siegeweapon.utility.command.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    public static File configFile;
    private FileConfiguration config;
    private YamlConfiguration CommentYamlConfiguration;
    private static Main main;
    public static boolean DEBUG = false;
    public boolean saveTheConfig = false;
    public static Main getInstance() {
        return main;
    }
    public static File siegeWeaponDataFolder;
    public static String pluginPrefix = ChatColor.translateAlternateColorCodes('&',"&8[&cSiegeWeapon&8] &f");
    public static String consolePluginPrefix = "[SiegeWeapon]->>";
    public static String pluginName = "SiegeWeapon";


    @Override
    public void reloadConfig() {
        if (configFile == null) {
            configFile = new File(this.getDataFolder(), "config.yml");
            if (!this.getDataFolder().exists())
                this.getDataFolder().mkdirs();
            if (!configFile.exists()) {
                try {
                    configFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);

    }

    public void reloadValues() {
        if(!this.getDataFolder().exists()) {
            if(this.getDataFolder().mkdirs()) {
                getServer().getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + pluginPrefix + "Creating data folder" + ColorCode.ANSI_WHITE);
            }
        }
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
            reloadConfig();
        }
        siegeWeaponDataFolder = getDataFolder();
        SiegeWeapon.SiegeWeaponLoader(getServer(), siegeWeaponDataFolder, pluginName, pluginPrefix, consolePluginPrefix, this);
    }

    @Override
    public void onEnable() {
        main = this;
        if(Bukkit.getPluginManager().getPlugin("ProtocolLib") == null){
            getServer().getConsoleSender().sendMessage(ColorCode.ANSI_RED + consolePluginPrefix + "Missing ProtocolLib, disabling..." + ColorCode.ANSI_WHITE);
            Bukkit.getPluginManager().disablePlugin(this);
        }

        reloadConfig();
        SiegeWeapon.SiegeWeaponEventRegister(this);
        getServer().getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + consolePluginPrefix + "Enabling SiegeWeapon" + ColorCode.ANSI_WHITE);
        reloadValues();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + consolePluginPrefix + "SiegeWeapon disabled" + ColorCode.ANSI_WHITE);
    }

    public static void sendDebugMessage(String message) {
        if (DEBUG)
            Bukkit.broadcast(message, "siegeweapon.debugmessages");
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> s = new ArrayList<String>();
        if (args.length > 0) {
            s = CommandHandler.onTabComplete(sender, command, alias, args);
            return s;
        }
        return s;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("SiegeWeapon")) {
            if (args.length > 0) {
                return CommandHandler.onCommand(sender, command, label, args);
            }
            return true;
        }
        return true;
    }

    @Override
    public FileConfiguration getConfig() {
        if (this.config == null) {
            this.reloadConfig();
        }
        return this.config;
    }

    @Override
    public void saveConfig() {
        if (this.config == null) {
            this.reloadConfig();
        }
        try {
            this.config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

