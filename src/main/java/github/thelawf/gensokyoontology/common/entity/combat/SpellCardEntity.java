package github.thelawf.gensokyoontology.common.entity.combat;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class SpellCardEntity extends AbstractSpellCardEntity{
    private BiConsumer<World, Entity> simpleBehavior;
    private Item spellItem;

    public SpellCardEntity(EntityType<AbstractSpellCardEntity> entityType, World world, Item spellItem, BiConsumer<World, Entity> simpleBehavior) {
        super(entityType, world);
        this.simpleBehavior = simpleBehavior;
        this.spellItem = spellItem;
    }

    public SpellCardEntity(EntityType<AbstractSpellCardEntity> entityType, World world, Item spellItem, JsonObject jsonSpell) {
        super(entityType, world);
        this.spellItem = spellItem;
    }

    @Override
    public void tick() {
        super.tick();
        this.simpleBehavior.accept(this.world, this);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return this.spellItem.getDefaultInstance();
    }
}
