package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.entity.DanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DanmakuTestItem extends Item {
    public DanmakuTestItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (worldIn.isRemote) {

            // 在这里应用TransformFunction.transfrom
            TransformFunction.create().setLocationVec(playerIn.getPositionVec())
                    .setWorld(worldIn)
                    .setLifeSpan(300)
                    .setExecuteTimes(5)
                    .setShootInterval(0)
                    .setSpeedV3(new Vector3d(1d,1d,1d))
                    .setPlayer(playerIn)
                    .circleStyleShoot();
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
