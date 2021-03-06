package me.asakura_kukii.siegeweapon.utility.nms;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Pair;
import me.asakura_kukii.siegeweapon.SiegeWeapon;
import me.asakura_kukii.siegeweapon.handler.item.gun.GunData;
import me.asakura_kukii.siegeweapon.handler.item.hand.HandData;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.*;
import com.comphenix.protocol.events.*;

public class ProtocolLibHandler {
    private static ProtocolManager protocolManager;

    public static void initProtocolLibHandler() {
        if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            if (protocolManager == null) {
                protocolManager = ProtocolLibrary.getProtocolManager();
            }
            protocolManager.removePacketListeners(SiegeWeapon.pluginInstance);
            initRemoveArmSwing();
            initAimCrossBow();
        }
    }

    public static void initRemoveArmSwing() {
        if (protocolManager == null) {
            protocolManager = ProtocolLibrary.getProtocolManager();
        }
        protocolManager.addPacketListener(
                new PacketAdapter(SiegeWeapon.pluginInstance, ListenerPriority.NORMAL, PacketType.Play.Server.ANIMATION) {
                    public void onPacketSending(PacketEvent event) {
                        final Player player = event.getPlayer();
                        if (event.getPacketType() == PacketType.Play.Server.ANIMATION) {
                            try {
                                int entityID = (int) event.getPacket().getModifier().read(0);
                                int state = (int) event.getPacket().getModifier().read(1);
                                Player target = null;
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getEntityId() == entityID) {
                                        target = p;
                                        break;
                                    }
                                }
                                if (target == null) {
                                    return;
                                }
                                if (state == 0) {
                                    if (HandData.getData(target.getInventory().getItemInMainHand()) != null) {
                                        event.setCancelled(true);
                                    }
                                } else if (state == 3) {
                                    if (HandData.getData(target.getInventory().getItemInMainHand()) != null || GunData.getData(target.getInventory().getItemInOffHand()) != null) {
                                        event.setCancelled(true);
                                    }
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
        );
    }

    public static void initAimCrossBow() {
        if (protocolManager == null) {
            protocolManager = ProtocolLibrary.getProtocolManager();
        }

        protocolManager.addPacketListener(
                new PacketAdapter(SiegeWeapon.pluginInstance, ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_EQUIPMENT) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        final Player sender = event.getPlayer();
                        int id = (int) event.getPacket().getModifier().read(0);
                        Object slot;
                        slot = event.getPacket().getModifier().read(1);
                        if ((id) == sender.getEntityId()) {
                            return;
                        }
                        Player who = null;
                        for (Player player : sender.getWorld().getPlayers()) {
                            if (player.getEntityId() == (int) id) {
                                who = player;
                                break;
                            }
                        }
                        if (who == null) {
                            return;
                        }
                        if (HandData.getData(who.getInventory().getItemInMainHand()) != null && GunData.getData(who.getInventory().getItemInOffHand()) != null) {
                            ItemStack iS = who.getInventory().getItemInOffHand().clone();
                            try {
                                net.minecraft.world.item.ItemStack nmsIS = CraftItemStack.asNMSCopy(iS);
                                CompoundTag nmsISNBTCompound = (nmsIS.hasTag()) ? nmsIS.getTag() : new CompoundTag();
                                assert nmsISNBTCompound != null;
                                nmsISNBTCompound.putBoolean("Charged", true);
                                nmsIS.setTag(nmsISNBTCompound);
                                List<Object> list = (List<Object>) slot;
                                for(Object o : new ArrayList<>(list)){
                                    if(o.toString().contains("OFFHAND")) {
                                        Pair pair = (Pair) o;
                                        Pair newpair = new Pair(pair.getFirst(), nmsIS);
                                        list.set(list.indexOf(pair), newpair);
                                    }
                                }
                                event.getPacket().getModifier().write(1, list);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                });

    }
}