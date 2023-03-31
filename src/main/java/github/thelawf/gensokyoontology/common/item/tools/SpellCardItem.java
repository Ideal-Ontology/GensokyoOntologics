package github.thelawf.gensokyoontology.common.item.tools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public abstract class SpellCardItem extends Item {

    private String id;
    private String description;
    private int duration;

    public SpellCardItem(Properties properties) {
        super(properties);
    }

    public SpellCardItem(Properties properties, String id, String description, int duration) {
        super(properties);
        this.id = id;
        this.description = description;
        this.duration = duration;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public abstract void start(PlayerEntity player);
    public abstract void update(PlayerEntity player, int tick);
    public abstract void end(PlayerEntity player);
}
