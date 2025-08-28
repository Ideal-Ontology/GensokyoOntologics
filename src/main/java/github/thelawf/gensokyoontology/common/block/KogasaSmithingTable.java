package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.KogasaSmitingContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import org.jetbrains.annotations.Nullable;

public class KogasaSmithingTable extends Block {
    public static final TranslationTextComponent NAME = GensokyoOntology.translate("container.", ".kogasa_smithing");

    public KogasaSmithingTable(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player instanceof ServerPlayerEntity){
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            NetworkHooks.openGui(serverPlayer, this.getContainer(state, worldIn, pos));
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @Nullable INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
    return new SimpleNamedContainerProvider((id, plyerInv, player) ->
            new KogasaSmitingContainer(plyerInv, id), NAME);
    }
}
