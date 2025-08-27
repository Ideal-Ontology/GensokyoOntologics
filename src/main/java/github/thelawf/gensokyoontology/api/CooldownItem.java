package github.thelawf.gensokyoontology.api;

import net.minecraft.item.Item;

public abstract class CooldownItem extends Item implements IHasCooldown {
    public CooldownItem(Properties properties) {
        super(properties);
    }
}
