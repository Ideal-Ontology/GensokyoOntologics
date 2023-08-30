package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class MobiusRingEntity extends SpellCardEntity{
    public MobiusRingEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, PlayerEntity player) {
        super(entityTypeIn, worldIn, player);
    }

    public MobiusRingEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        // 本地坐标：OaOb
        Vector3d horizonVec = new Vector3d(Vector3f.XP);
        horizonVec.scale(3);

        //
        Vector3d verticalVec = new Vector3d(Vector3f.XP);
        verticalVec.scale(1.5);

        horizonVec.rotateYaw((float) Math.PI / 60 * ticksExisted);
        verticalVec.rotatePitch((float) Math.PI / 10 * 2);
        for (int j = 0; j < 12; j++) {
            verticalVec.rotateYaw((float) Math.PI / 60 * ticksExisted).rotatePitch(
                    (float) Math.PI / j * 2 * ticksExisted);
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
