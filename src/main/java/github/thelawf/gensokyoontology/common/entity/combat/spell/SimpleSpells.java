package github.thelawf.gensokyoontology.common.entity.combat.spell;

import github.thelawf.gensokyoontology.common.entity.combat.SpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.entity.projectile.FakeLunarEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.BiConsumer;

public class SimpleSpells {
    public static Danmaku setDanmakuItem(World world, Item item) {
        Danmaku danmaku = new Danmaku(EntityRegistry.DANMAKU.get(), world);
        danmaku.setItem(new ItemStack(item));
        world.addEntity(danmaku);
        return danmaku;
    }

    public static final BiConsumer<World, SpellCardEntity> EMPTY_SPELl = (world, spell) -> {};
    public static final BiConsumer<World, SpellCardEntity> MOBIUS_RING = (world, entity) -> {
        // 创建圆环的水平面：
        // 定义一个位于 X-Z 平面的 PQ 向量，以P为圆心形成圆P
        // /data get entity @e[type=gensokyoontology:spell_card,limit=1,distance=..1]
        // /data modify entity @e[type=gensokyoontology:spell_card,limit=1,distance=..1] SpellId set value "gensokyoontology:mobius_ring"
        // /data modify entity @e[type=gensokyoontology:spell_card,limit=1,distance=..1] Owner set from storage gsko:spell_owner UUID
        // /data merge entity @e[type=gensokyoontology:spell_card,limit=1,distance=..1] {Owner:[I;0,0,0,0]}
        // /data merge storage gsko:spell {UUID:[I;0,0,0,0]}
        GSKOUtil.log(SimpleSpells.class, "???");
        if (entity == null) return;

        int ticksExisted = entity.ticksExisted;
        Vector3d horizonVec = new Vector3d(Vector3f.ZP);
        horizonVec = horizonVec.scale(6);

        // 创建竖圆：
        Vector3d verticalVec = new Vector3d(Vector3f.ZP);
        verticalVec = verticalVec.scale(3);

        float velocity = 0.6f;
        float rotation = (float) (Math.PI / 80 * 2 * ticksExisted);

        horizonVec = horizonVec.rotateYaw(rotation);
        List<Item> colors = DanmakuUtil.getRainbowColoredDanmaku();

        createMobius(horizonVec, verticalVec, colors, entity, ticksExisted, velocity);
        Vector3d horizonVecNew = horizonVec.rotateYaw((float) Math.PI);
        createMobius(horizonVecNew, verticalVec, colors, entity, ticksExisted, velocity);
    };

    public static final BiConsumer<World, SpellCardEntity> HELL_ECLIPSE = (world, entity) -> {

        if (entity == null) return;
        int ticksExisted = entity.ticksExisted;
        Danmaku fakeLunar = setDanmakuItem(world, ItemRegistry.FAKE_LUNAR_ITEM.get());

        float angle = (float) (Math.PI / 60 * ticksExisted);
        float lunarAngle = (float) ((world.getGameTime() * 0.1f) % (Math.PI * 2));
        float speed = 0.2F;

        Vector3d center = new Vector3d(Vector3f.XP);
        Vector3d local = center.add(4, 0, 0).rotateYaw(angle);
        Vector3d global = local.add(entity.getPositionVec());

        Vector3d lunarPos = fakeLunar.getPositionVec();
        Vector3d lunarMotion = new Vector3d(MathHelper.cos(lunarAngle) * speed, 0, MathHelper.sin(lunarAngle) * speed);

        fakeLunar.setPosition(lunarPos.x + lunarMotion.x, lunarPos.y, lunarPos.z + lunarMotion.z);
        fakeLunar.setMotion(lunarMotion);

        for (int i = 0; i < 8; i++) {
            Vector3d vector3d = center.rotateYaw((float) (Math.PI / 4 * i)).rotateYaw((float) (Math.PI / 100 * ticksExisted));
            Danmaku smallShot = setDanmakuItem(world, ItemRegistry.SMALL_SHOT.get());
            smallShot.shoot(vector3d.x, 0, vector3d.z, 0.5F, 0F);
        }
    };

    private static void createMobius(Vector3d horizonVec, Vector3d verticalVec, List<Item> colors, SpellCardEntity entity, int ticksExisted, float velocity) {
        for (int i = 0; i < colors.size(); i++) {

            verticalVec = verticalVec.rotatePitch((float) Math.PI / colors.size() * i);
            verticalVec = verticalVec.rotateYaw((float) Math.PI / 80 * 2 * ticksExisted);
            setDanmakuItem(entity.world, colors.get(i));
        }
    }
}
