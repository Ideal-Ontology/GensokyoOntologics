package github.thelawf.gensokyoontology.common.item.material;

import github.thelawf.gensokyoontology.common.entity.misc.CursedBatEntity;
import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.command.impl.LocateCommand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
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
            // EntityType.BAT.spawn(serverWorld, playerIn.getHeldItem(handIn), playerIn, playerIn.getPosition(), SpawnReason.SPAWN_EGG, false, false);
            BlockPos mansionPos = serverWorld.getStructureLocation(StructureRegistry.SCARLET_DEVIL_MANSION.get(), playerIn.getPosition(), 100, false);
            if (mansionPos == null) return ActionResult.resultFail(playerIn.getHeldItem(handIn));
            CursedBatEntity bat = new CursedBatEntity(serverWorld, mansionPos);
            bat.setLocationAndAngles(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), playerIn.rotationYaw, playerIn.rotationPitch);

            serverWorld.addEntity(bat);
            playerIn.startRiding(bat);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
