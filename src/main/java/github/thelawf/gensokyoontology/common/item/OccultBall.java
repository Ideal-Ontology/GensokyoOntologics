package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.common.world.TeleportHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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
    private boolean canTravelToGensokyo = true;
    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        if (stack.hasTag()) return ActionResult.resultPass(stack);

        CompoundNBT nbt = new CompoundNBT();
        Biome biome = worldIn.getBiome(playerIn.getPosition());
        nbt.putString("biome", String.valueOf(biome.getRegistryName()));

        stack.setTag(nbt);

        if (worldIn instanceof ServerWorld && playerIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            if (serverPlayer.getServer() != null && this.canTravelToGensokyo) {
                this.canTravelToGensokyo = false;
                ServerWorld gensokyo = serverPlayer.getServer().getWorld(GSKODimensions.GENSOKYO);
                TeleportHelper.teleport(serverPlayer, gensokyo, playerIn.getPosition());
            }
        }

        BlockPos blockPos = playerIn.getPosition();

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.getTag() != null && stack.getTag().contains("biome")) {
            String info = "tooltip." + GensokyoOntology.MODID + ".occult_ball.biome";
            tooltip.add(new TranslationTextComponent("tooltip.blank_line"));
            tooltip.add(new TranslationTextComponent(info));
            tooltip.add(new TranslationTextComponent(stack.getTag().getString("biome")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
