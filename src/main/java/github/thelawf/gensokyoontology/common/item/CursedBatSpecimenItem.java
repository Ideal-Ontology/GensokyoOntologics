package github.thelawf.gensokyoontology.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class CursedBatSpecimenItem extends Item {
    public CursedBatSpecimenItem(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (!worldIn.isRemote) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            EntityType.BAT.spawn(serverWorld, playerIn.getHeldItem(handIn), playerIn, playerIn.getPosition(), SpawnReason.SPAWN_EGG, false, false);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
