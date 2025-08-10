package github.thelawf.gensokyoontology.common.entity.combat.spell;

import github.thelawf.gensokyoontology.common.entity.combat.AbstractSpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FullCherryBlossomEntity extends AbstractSpellCardEntity {

    public FullCherryBlossomEntity(World worldIn, LivingEntity living) {
        super(EntityRegistry.FULL_CHERRY_BLOSSOM_ENTITY.get(), worldIn, living);
        this.setOwner(living);
    }

    public FullCherryBlossomEntity(EntityType<? extends AbstractSpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    /**
     * 这个方法是为了方便其它类在外部调用符卡的弹幕演出
     */
    public void onTick(World world, LivingEntity living, int ticksExisted) {

    }

    @Override
    public void tick() {
        super.tick();
        onTick(world, (LivingEntity) this.getOwner(), ticksExisted);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
