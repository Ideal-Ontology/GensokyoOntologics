package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.common.world.TeleportHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.impl.ExecuteCommand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class OccultBall extends Item {
    public OccultBall(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        CompoundNBT nbt = new CompoundNBT();
        if (!worldIn.isRemote() && playerIn.getHeldItemMainhand().hasTag()) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            Biome biome = serverWorld.getBiome(playerIn.getPosition());
            nbt.putString("biome", biome.getRegistryType().getName());
            ItemStack stack = playerIn.getHeldItemMainhand();
            stack.setTag(nbt);

            GensokyoOntology.LOGGER.info(biome.getRegistryType().getName());
            BlockPos blockPos = playerIn.getPosition();

            if (playerIn instanceof ServerPlayerEntity && serverWorld.getDimensionKey() != GSKODimensions.GENSOKYO) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
                if (serverPlayer.getServer() != null) {
                    ServerWorld gensokyo = serverPlayer.getServer().getWorld(GSKODimensions.GENSOKYO);
                    TeleportHelper.teleport(serverPlayer, gensokyo, playerIn.getPosition());
                }

            }

        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.getTag() != null && stack.getTag().contains("biome")) {
            String info = "tooltip.gensokyoontology.occult_ball.biome";
            tooltip.add(new TranslationTextComponent(info + stack.getTag().getString("biome")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
