package me.asakura_kukii.siegeweapon.handler.nonitem.method.common;

import me.asakura_kukii.siegeweapon.SiegeWeapon;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.HashMap;

public abstract class MethodHandler {
    public abstract void init(MethodNodeData mND, Location start, Vector direction, Entity e, HashMap<String, Object> extraData);

    public static void execute(MethodNodeData mND, Location start, Vector direction, Entity e, HashMap<String, Object> extraData) {
        mND.mT.mH.init(mND, start, direction, e, extraData);
    }
}
