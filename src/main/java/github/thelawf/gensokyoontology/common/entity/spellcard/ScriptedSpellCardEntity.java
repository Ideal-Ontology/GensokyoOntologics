package github.thelawf.gensokyoontology.common.entity.spellcard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptedSpellCardEntity extends SpellCardEntity {

    private String script = "";

    public static final DataParameter<String> DATA_SCRIPT = EntityDataManager.createKey(ScriptedSpellCardEntity.class,
            DataSerializers.STRING);

    public ScriptedSpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, LivingEntity living) {
        super(entityTypeIn, worldIn, living);
    }


    public ScriptedSpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void onScriptTick(World world, Entity owner, int ticksIn) {
        super.onScriptTick(world, owner, ticksIn);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
