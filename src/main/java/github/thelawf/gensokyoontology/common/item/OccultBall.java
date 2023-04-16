package github.thelawf.gensokyoontology.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
