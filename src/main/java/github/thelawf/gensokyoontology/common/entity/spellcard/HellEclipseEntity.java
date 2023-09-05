package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.FakeLunarEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.*;
import github.thelawf.gensokyoontology.core.SerializerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.HashMap;
import java.util.UUID;

public class HellEclipseEntity extends SpellCardEntity{

    FakeLunarEntity fakeLunar;
    public static final EntityType<HellEclipseEntity> HELL_ECLIPSE_ENTITY =
            EntityType.Builder.<HellEclipseEntity>create(HellEclipseEntity::new, EntityClassification.MISC)
                    .size(1F,1F).trackingRange(4).updateInterval(2).build("circle_cross");

    public HellEclipseEntity(World worldIn, PlayerEntity player) {
        super(HELL_ECLIPSE_ENTITY, worldIn, player);
        this.setOwner(player.getEntity());
    }

    public HellEclipseEntity(EntityType<?> entityTypeIn, World world) {
        super(HELL_ECLIPSE_ENTITY, world);
        fakeLunar = new FakeLunarEntity(FakeLunarEntity.FAKE_LUNAR, world);
        world.addEntity(fakeLunar);
        HashMap<Integer, TransformFunction> map = new HashMap<>();
        fakeLunar.setSpellData(new SpellData(map, DanmakuType.FAKE_LUNAR, DanmakuColor.PINK, false, false));
    }

    @Override
    public void tick() {
        super.tick();

        PlayerEntity player = this.hasOwner() ? this.world.getPlayerByUuid(
                this.dataManager.get(DATA_OWNER_UUID).get()) : null;

        double radius = 4;
        double angle = Math.PI / 100;

        Vector3d center = new Vector3d(Vector3f.XP);
        HashMap<Integer, TransformFunction> map = new HashMap<>();
        SpellData spellData = new SpellData(map, DanmakuType.LARGE_SHOT, DanmakuColor.PINK, false, false);

        Vector3d local = center.add(4,0,0).rotateYaw((float) (Math.PI / 60 * ticksExisted));
        Vector3d global = local.add(this.getPositionVec());

        Vector3d lunarLocal = center.add(4,0,0).rotateYaw((float) -(Math.PI / 60 * ticksExisted));
        Vector3d lunarGlobal = lunarLocal.add(this.getPositionVec());

        for (int i = 0; i < 8; i++) {

            SmallShotEntity smallShot = new SmallShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
            Vector3d vector3d = center.rotateYaw((float) (Math.PI / 4 * i))
                    .rotateYaw((float) (Math.PI / 100 * ticksExisted));

            smallShot.setLocationAndAngles(global.x, global.y, global.z,
                    (float) center.y, (float) center.z);
            smallShot.setNoGravity(true);

            smallShot.shoot(vector3d.x, 0, vector3d.z, 0.5F, 0F);
            world.addEntity(smallShot);
        }

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
