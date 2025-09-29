package github.thelawf.gensokyoontology.common.entity.combat.spell;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.common.entity.combat.SpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.misc.Laser;
import github.thelawf.gensokyoontology.common.entity.misc.MasterSparkEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

/**
 * 使用data命令可以添加该类下的预设符卡弹幕演出，步骤如下：<br>
 * <code src="mcfunction">
 * /data merge storage gsko:spell_owner {UUID:[I;0,0,0,0]}<br>
 * /data modify storage gsko:spell_owner UUID set from @r UUID<br>
 * /data merge entity @e[type=gensokyoontology:spell_card,limit=1] {Owner:[I;0,0,0,0]}<br>
 * /data modify entity @e[type=gensokyoontology:spell_card,limit=1] Owner set from storage gsko:spell_owner UUID<br>
 * /data modify entity @e[type=gensokyoontology:spell_card,limit=1] SpellId set value "gensokyoontology:mobius_ring"<br><br>
 * </code>
 * 附，获取符卡NBT数据的命令：<br>
 * /data get entity @e[type=gensokyoontology:spell_card,limit=1,distance=..1]<br>
 */
public class SimpleSpells {

    public static Danmaku FAKE_LUNAR;
    public static MasterSparkEntity SPARK_ENTITY;

    public static final  List<Item> COLORS = DanmakuUtil.getRainbowColoredDanmaku();
    public static final List<Item> STARS = ImmutableList.of(
            ItemRegistry.LARGE_STAR_SHOT_RED.get(),
            ItemRegistry.LARGE_STAR_SHOT_BLUE.get(),
            ItemRegistry.LARGE_STAR_SHOT_GREEN.get(),
            ItemRegistry.LARGE_STAR_SHOT_YELLOW.get());

    public static Danmaku init(World world, SpellCardEntity spellCard, Item item) {
        Danmaku danmaku = new Danmaku(EntityRegistry.DANMAKU.get(), world);
        danmaku.setPosition(spellCard.getPosX(), spellCard.getPosY(), spellCard.getPosZ());
        danmaku.setItem(new ItemStack(item));
        world.addEntity(danmaku);
        return danmaku;
    }

    public static <E extends Entity> E getOrSpawn(ServerWorld world, E entity, EntityType<E> entityType, SpellCardEntity spellCard) {
        if (entity == null) {
            return entityType.spawn(world, null, null, null,
                    spellCard.getPosition(), SpawnReason.TRIGGERED, false, false);
        }
        else return entity;
    }

    public static Danmaku getOrInit(ServerWorld world, Danmaku danmaku, SpellCardEntity spellCard, Item item) {
        if (danmaku == null) {
            return init(world, spellCard, item);
        }
        return danmaku;
    }

    public static final BiConsumer<World, SpellCardEntity> EMPTY_SPELl = (world, spell) -> {};
    public static final BiConsumer<World, SpellCardEntity> HELL_ECLIPSE = (world, entity) -> {

        if (entity == null) return;
        int ticksExisted = entity.ticksExisted;
        if (world.isRemote) return;
        ServerWorld serverWorld = (ServerWorld) world;
        FAKE_LUNAR = getOrInit(serverWorld, FAKE_LUNAR, entity, ItemRegistry.FAKE_LUNAR_ITEM.get());
        world.getEntitiesWithinAABB(Danmaku.class, FAKE_LUNAR.getBoundingBox().grow(1.1))
                .stream().filter(danmaku -> danmaku.getItem().getItem() != ItemRegistry.FAKE_LUNAR_ITEM.get())
                .forEach(Entity::remove);
        FAKE_LUNAR.setLifespan(SpellCardEntity.MAX_LIFE);

        float angle = (float) (Math.PI / 60 * ticksExisted);
        float lunarAngle = (float) ((world.getGameTime() * 0.1f) % (Math.PI * 2));
        float speed = 0.2F;

        Vector3d center = new Vector3d(Vector3f.XP);
        Vector3d local = center.add(4, 0, 0).rotateYaw(angle);
        Vector3d global = local.add(entity.getPositionVec());

        Vector3d lunarPos = FAKE_LUNAR.getPositionVec();
        Vector3d lunarMotion = new Vector3d(MathHelper.cos(lunarAngle) * speed, 0, MathHelper.sin(lunarAngle) * speed);

        FAKE_LUNAR.setPosition(lunarPos.x + lunarMotion.x, lunarPos.y, lunarPos.z + lunarMotion.z);
        FAKE_LUNAR.setMotion(lunarMotion);

        for (int i = 0; i < 8; i++) {
            Vector3d vector3d = center.rotateYaw((float) (Math.PI / 4 * i)).rotateYaw((float) (Math.PI / 100 * ticksExisted));
            Danmaku smallShot = init(world, entity, ItemRegistry.SMALL_SHOT_RED.get());
            smallShot.setPosition(global.x, global.y, global.z);
            smallShot.shoot(vector3d.x, 0, vector3d.z, 0.5F, 0F);
        }

        if (entity.ticksExisted >= SpellCardEntity.MAX_LIFE) {
            FAKE_LUNAR = null;
        }
    };

    public static final BiConsumer<World, SpellCardEntity> MASTER_SPARK = (world, entity) -> {
        if (entity == null) return;
        int ticksExisted = entity.ticksExisted;
        if (ticksExisted % 20 != 0) return;
        if (world.isRemote) return;

        ServerWorld serverWorld = (ServerWorld) world;
        if (ticksExisted == 0){
            SPARK_ENTITY = getOrSpawn(serverWorld, SPARK_ENTITY, EntityRegistry.MASTER_SPARK_ENTITY.get(), entity);
        }

        SPARK_ENTITY = getOrSpawn(serverWorld, SPARK_ENTITY, EntityRegistry.MASTER_SPARK_ENTITY.get(), entity);
        SPARK_ENTITY.setPositionAndRotation(entity.getPosX(), entity.getPosY(), entity.getPosZ(),
                entity.rotationYaw, entity.rotationPitch);
        DanmakuUtil.spheroidPos(3, 10).forEach(shootVec ->
                init(world, entity, STARS.get(new Random().nextInt(STARS.size() - 1)))
                        .shoot(shootVec, 0.5F));

        if (entity.ticksExisted >= SpellCardEntity.MAX_LIFE) {
            SPARK_ENTITY = null;
        }
    };

    public static final BiConsumer<World, SpellCardEntity> LASER_MAZE = (world, entity) -> {
        if (entity == null) return;
        int ticksExisted = entity.ticksExisted;
        if (ticksExisted != 1) return;
        if (world.isRemote) return;


//        Laser.create(world, entity)
//                .owner(entity)
//                .pos(laserVec.scale(3).add(entity.getPosX(), entity.getPosY(), entity.getPosZ()))
//                .rot(Rot2f.from(laserVec.rotateYaw(Danmaku.rad(30))));
//        Laser.create(world, entity)
//                .owner(entity)
//                .pos(laserVec.scale(3).add(entity.getPosX(), entity.getPosY(), entity.getPosZ()))
//                .rot(Rot2f.from(laserVec.rotateYaw(Danmaku.rad(-30))))
        List<Vector3d> positions = DanmakuUtil.ellipticPos(1, 6);
        List<Vector3d> rotations = DanmakuUtil.spheroidPos(1, 6);

        for (int i = 0; i < rotations.size() - 1; i++) {
            int index = i % positions.size();
            Vector3d pos = positions.get(index).scale(3).add(entity.getPosX(), entity.getPosY(), entity.getPosZ());
            Vector3d rot = rotations.get(i);

            Laser.create(world,entity).owner(entity).rot(Rot2f.from(rot));
            Laser.create(world, entity)
                    .owner(entity)
                    .pos(pos)
                    .rot(Rot2f.from(rot.rotateYaw(Danmaku.rad(30))));
            Laser.create(world, entity)
                    .owner(entity)
                    .pos(pos)
                    .rot(Rot2f.from(rot.rotateYaw(Danmaku.rad(-30))));
        }

//        DanmakuUtil.spheroidPos(1F, 5).forEach(laserVec -> {
//            Laser.create(world, entity).owner(entity).rot(Rot2f.from(laserVec));
//        });
    };

    public static final BiConsumer<World, SpellCardEntity> MOBIUS_RING = (world, entity) -> {
        // 创建圆环的水平面：
        // 定义一个位于 X-Z 平面的 PQ 向量，以P为圆心形成圆P
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

        createMobius(horizonVec, verticalVec, entity, ticksExisted, velocity);
        Vector3d horizonVecNew = horizonVec.rotateYaw((float) Math.PI);
        createMobius(horizonVecNew, verticalVec, entity, ticksExisted, velocity);
    };

    private static void createMobius(Vector3d horizonVec, Vector3d verticalVec, SpellCardEntity entity, int ticksExisted, float velocity) {
        for (int i = 0; i < SimpleSpells.COLORS.size(); i++) {
            verticalVec = verticalVec.rotatePitch((float) Math.PI / SimpleSpells.COLORS.size() * i);
            verticalVec = verticalVec.rotateYaw((float) Math.PI / 80 * 2 * ticksExisted);

            Vector3d initPos = horizonVec.add(entity.getPosX(), entity.getPosY(), entity.getPosZ());
            Danmaku danmaku = init(entity.world, entity, SimpleSpells.COLORS.get(i));
            danmaku.setPosition(initPos.x, initPos.y, initPos.z);
            danmaku.shoot(verticalVec, 0.6F);
        }
    }
}
