package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
// 它扩展了FlowerBlock类,所以是一个可以种植的花方块
// 百合
public class LilyOfValleyBlock extends FlowerBlock {
    public LilyOfValleyBlock() {
        //蒲公英
        super(Effects.POISON, 2000, Properties.from(Blocks.DANDELION));
    }
//onEntityCollision当生物体碰撞这个方块时调用,会使生物中毒2秒钟
    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (!worldIn.isRemote && entityIn instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entityIn;
            living.addPotionEffect(new EffectInstance(Effects.POISON, 2 * 50));
        }
    }
}
