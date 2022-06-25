package me.asakura_kukii.siegeweapon.handler.item.gun.reload;

public enum ReloadType {
    CLIP(new ClipHandler());

    public Reload r;
    ReloadType(Reload r) {
        this.r = r;
    }
}
