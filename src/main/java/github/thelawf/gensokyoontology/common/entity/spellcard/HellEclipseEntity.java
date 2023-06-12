package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.FakeLunarEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class HellEclipseEntity extends SpellCardEntity{

    FakeLunarEntity fakeLunar;
    public static final EntityType<HellEclipseEntity> HELL_ECLIPSE_ENTITY =
            EntityType.Builder.<HellEclipseEntity>create(HellEclipseEntity::new,
                            EntityClassification.MISC).size(1F,1F).trackingRange(4)
                    .updateInterval(2).build("circle_cross");

    public HellEclipseEntity(EntityType<?> entityTypeIn, World worldIn, UUID owner) {
        super(entityTypeIn, worldIn, owner);
    }

    public HellEclipseEntity(EntityType<HellEclipseEntity> achimedeSpiralEntityEntityType, World world) {
        super(HELL_ECLIPSE_ENTITY, world);
        fakeLunar = new FakeLunarEntity(FakeLunarEntity.FAKE_LUNAR, world);
        world.addEntity(fakeLunar);
        HashMap<Integer, TransformFunction> map = new HashMap<>();
        fakeLunar.setSpellData(new SpellData(map, DanmakuType.FAKE_LUNAR, DanmakuColor.PINK, false, false));
    }

    @Override
    public void tick() {
        super.tick();

        PlayerEntity player = world.getPlayers().get(0);

        double radius = 4;
        double angle = Math.PI / 100;

        Vector3d center = new Vector3d(Vector3f.XP);
        HashMap<Integer, TransformFunction> map = new HashMap<>();
        SpellData spellData = new SpellData(map, DanmakuType.LARGE_SHOT, DanmakuColor.PINK, false, false);

        Vector3d local = center.add(4,0,0).rotateYaw((float) (Math.PI / 60 * ticksExisted));
        Vector3d global = local.add(this.getPositionVec());

        Vector3d lunarLocal = center.add(4,0,0).rotateYaw((float) -(Math.PI / 60 * ticksExisted));
        Vector3d lunarGlobal = lunarLocal.add(this.getPositionVec());

        fakeLunar.setLocationAndAngles(lunarGlobal.x, lunarGlobal.y, lunarLocal.z,
                (float) center.y, (float) center.z);
        fakeLunar.setNoGravity(true);


        for (int i = 0; i < 8; i++) {
            // largeShot.setLocationAndAngles(global.x, global.y, global.z,
            //         (float) center.y, (float) center.z);
            // largeShot.setNoGravity(true);
            LargeShotEntity largeShot = new LargeShotEntity(player, world, spellData);

            // Vector3d muzzle = center.add(4,0,0).rotateYaw((float) (Math.PI / 4 * i));
            Vector3d vector3d = center.rotateYaw((float) (Math.PI / 4 * i))
                    .rotateYaw((float) (Math.PI / 100 * ticksExisted));

            largeShot.setLocationAndAngles(global.x, global.y, global.z,
                    (float) center.y, (float) center.z);
            largeShot.setNoGravity(true);

            largeShot.shoot(vector3d.x, 0, vector3d.z, 0.5F, 0F);

            world.addEntity(largeShot);
        }

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
