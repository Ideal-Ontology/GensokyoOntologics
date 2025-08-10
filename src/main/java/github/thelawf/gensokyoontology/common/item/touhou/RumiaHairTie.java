package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RumiaHairTie extends Item {
    public RumiaHairTie(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();

        if (world.getLightValue(pos) >= 8) return super.onItemUse(context);
        if (world.isRemote) return super.onItemUse(context);

        ServerWorld serverWorld = (ServerWorld) world;
        EntityRegistry.RUMIA.get().spawn(serverWorld, context.getItem(), player, pos, SpawnReason.TRIGGERED, false, false);
        context.getItem().shrink(1);

        return ActionResultType.CONSUME;
    }
}
