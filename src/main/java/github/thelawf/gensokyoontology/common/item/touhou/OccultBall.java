package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.common.world.TeleportHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class OccultBall extends Item {
    public OccultBall(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (stack.hasTag()) {
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
            // return ActionResult.resultPass(playerIn.getHeldItem(Hand.MAIN_HAND).equals(
            //         new ItemStack(ItemRegistry.OCCULT_BALL.get())) ?
            //         playerIn.getHeldItem(Hand.MAIN_HAND) : playerIn.getHeldItem(Hand.OFF_HAND));
        }

        CompoundNBT nbt = new CompoundNBT();
        Biome biome = worldIn.getBiome(playerIn.getPosition());
        nbt.putString("biome", String.valueOf(biome.getRegistryName()));
        nbt.putBoolean("can_travel_to_gensokyo", true);
        stack.setTag(nbt);

        if (worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, handIn);
        if (!nbt.getBoolean("can_travel_to_gensokyo")) return super.onItemRightClick(worldIn, playerIn, handIn);
        if (playerIn instanceof ServerPlayerEntity) {
            nbt.remove("can_travel_to_gensokyo");
            nbt.putBoolean("can_travel_to_gensokyo", false);
            stack.setTag(nbt);

            ServerWorld serverWorld = (ServerWorld) worldIn;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            ServerWorld gensokyo = serverWorld.getServer().getWorld(GSKODimensions.GENSOKYO);
            TeleportHelper.teleport(serverPlayer, gensokyo, playerIn.getPosition());

        }

        BlockPos blockPos = playerIn.getPosition();

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID + ".occult_ball.hint"));
        if (stack.getTag() != null && stack.getTag().contains("biome")) {
            String info = "tooltip." + GensokyoOntology.MODID + ".occult_ball.biome";
            tooltip.add(new TranslationTextComponent(info));
            tooltip.add(new TranslationTextComponent(stack.getTag().getString("biome")));
        }
        if (stack.getTag() != null && stack.getTag().contains("can_travel_to_gensokyo")) {
            String info = "tooltip." + GensokyoOntology.MODID + ".occult_ball.accessablity";
            // tooltip.add(new TranslationTextComponent(info));
            tooltip.add(new TranslationTextComponent(stack.getTag().getString("can_travel_to_gensokyo")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }
}
