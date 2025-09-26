package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.container.KogasaSmithingContainer;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KogasaSmithingTable extends AnvilBlock {
    public static final TranslationTextComponent NAME = GSKOUtil.translate("container.", ".kogasa_smithing");

    public KogasaSmithingTable() {
        super(Properties.from(Blocks.ANVIL));
    }

    @Override
    public @NotNull ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player instanceof ServerPlayerEntity){
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            NetworkHooks.openGui(serverPlayer, this.getContainer(state, worldIn, pos));
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public @Nullable INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
    return new SimpleNamedContainerProvider((id, plyerInv, player) ->
            new KogasaSmithingContainer(plyerInv, id), NAME);
    }
}
