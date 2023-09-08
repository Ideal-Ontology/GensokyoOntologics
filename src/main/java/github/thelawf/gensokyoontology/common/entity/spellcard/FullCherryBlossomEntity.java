package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class FullCherryBlossomEntity extends SpellCardEntity{

    public FullCherryBlossomEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, PlayerEntity player) {
        super(entityTypeIn, worldIn, player);
    }

    public FullCherryBlossomEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        double radius = 5;
        double blossomWidth = 7;
        double blossomCount = 5;
        double x,y;

        for (int i = 0; i < 90; i++) {
            x = radius * Math.cos((blossomCount / blossomWidth) * Math.PI / ((double) ticksExisted / 100))
                    * Math.cos(Math.PI / ticksExisted);

            y = radius * Math.cos((blossomCount / blossomWidth) * Math.PI / ((double) ticksExisted / 100))
                    * Math.sin(Math.PI / ticksExisted);

            RiceShotEntity riceShot = new RiceShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.RICE_SHOT, DanmakuColor.PURPLE);

            // setDanmakuInit(riceShot, new Vector3d(x, y, 0), );
        }
    }

    @Override
    public ItemStack getItem() {
        return null;
    }
}
