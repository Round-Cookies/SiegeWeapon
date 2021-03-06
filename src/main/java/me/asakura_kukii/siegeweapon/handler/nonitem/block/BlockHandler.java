package me.asakura_kukii.siegeweapon.handler.nonitem.block;

import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.utility.nms.NMSHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

public class BlockHandler {
    public static HashMap<Location, Double> blockHealthMapper = new HashMap<>();

    public static void blockBreakHandler(Location loc, Material m){
        try {
            Integer stage = (int) Math.floor(10 - 10 * blockHealthMapper.get(loc));
            SiegeWeapon.pluginInstance.getLogger().info(stage + "");
            if (stage == 10) {
                if (SiegeWeapon.replaceableBlockMap.containsKey(m)) {
                    loc.getBlock().setType(m);
                } else {
                    loc.getBlock().breakNaturally();
                }
                blockHealthMapper.remove(loc);
            }
            for(Player p : Objects.requireNonNull(loc.getWorld()).getPlayers()) {
                NMSHandler.blockDestructionPacket(p, loc, stage);
            }
        } catch (Exception ignored) {
        }
    }

}
