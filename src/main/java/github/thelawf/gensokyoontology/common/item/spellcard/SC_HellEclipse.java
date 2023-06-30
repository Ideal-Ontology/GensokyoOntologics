package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.HellEclipseEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Supplier;

public class SC_HellEclipse extends SpellCardItem {
    public SC_HellEclipse(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            // EntityType<HellEclipseEntity> entityType = EntityRegistry.HELL_ECLIPSE_ENTITY.get();
            // entityType.spawn(serverWorld, playerIn.getHeldItemMainhand(), playerIn, playerIn.getPosition(),
            //         SpawnReason.MOB_SUMMONED,true, true);
            HellEclipseEntity hellEclipse = new HellEclipseEntity(worldIn, playerIn);
            worldIn.addEntity(hellEclipse);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void start(PlayerEntity player) {

    }

    @Override
    public Supplier<TransformFunction> update(PlayerEntity player, int tick) {
        return null;
    }

    @Override
    public void end(PlayerEntity player) {

    }
}
