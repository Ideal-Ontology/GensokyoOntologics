package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.common.libs.danmakulib.Muzzle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SpellCardEntity extends Entity {

    public static int lifeSpan;
    public List<Muzzle> muzzles = new ArrayList<>();

    public static final EntityType<SpellCardEntity> SPELL_CARD_ENTITY = EntityType.Builder.<SpellCardEntity>create(
                    SpellCardEntity::new, EntityClassification.MISC).size(1F,1F).trackingRange(4)
            .updateInterval(2).build("spell_card_entity");

    public SpellCardEntity(EntityType<?> entityTypeIn, World worldIn, List<Muzzle> muzzles) {
        super(entityTypeIn, worldIn);
        this.muzzles = muzzles;
        muzzles.forEach(muzzle -> lifeSpan = muzzle.func.lifeSpan);
    }
    public SpellCardEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public void tick() {
        super.tick();
        if (ticksExisted >= lifeSpan) {
            this.remove();
        }
        // 在这里调用变换函数，使用 tickExisted 作为变换函数中increment增加值的迭代单位
        muzzles.forEach(muzzle -> {

        });
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return null;
    }
}
