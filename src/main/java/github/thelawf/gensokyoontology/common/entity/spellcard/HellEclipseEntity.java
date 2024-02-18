package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.FakeLunarEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.util.Vec3fConstants;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.common.util.danmaku.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;

public class HellEclipseEntity extends SpellCardEntity {

    private final FakeLunarEntity fakeLunar;

    public HellEclipseEntity(World worldIn, PlayerEntity player) {
        super(EntityRegistry.HELL_ECLIPSE_ENTITY.get(), worldIn, player);
        this.setOwner(player.getEntity());
        this.fakeLunar = new FakeLunarEntity(player, world, DanmakuType.FAKE_LUNAR, DanmakuColor.NONE);
        this.fakeLunar.setLifespan(this.lifeSpan);
        setDanmakuInit(this.fakeLunar, this.getPositionVec(), new Vector2f(this.rotationYaw, this.rotationPitch));
        worldIn.addEntity(this.fakeLunar);

    }

    public HellEclipseEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World world) {
        super(entityTypeIn, world);
        fakeLunar = new FakeLunarEntity(EntityRegistry.FAKE_LUNAR_ENTITY.get(), world);
        world.addEntity(fakeLunar);
        HashMap<Integer, TransformFunction> map = new HashMap<>();
    }

    @Override
    public void tick() {
        super.tick();
        onTick(world, this.getOwner(), ticksExisted);
        onScriptTick(world, this.getOwner(), ticksExisted);
    }

    @Override
    public void onTick(World world, Entity entity, int ticksIn) {
        super.onTick(world, entity, ticksIn);
        Vector3d center = new Vector3d(Vector3f.XP);

        float angle = (float) (Math.PI / 60 * ticksExisted);
        float lunarAngle = (float) ((world.getGameTime() * 0.1f) % (Math.PI * 2));
        float angle2 = (float) ((world.getGameTime() * 0.08f) % (Math.PI * 2));
        float speed = 0.2F;
        Vector3d local = center.add(4, 0, 0).rotateYaw(angle);
        Vector3d global = local.add(this.getPositionVec());

        Vector3d lunarPos = this.fakeLunar.getPositionVec();
        // Vector3d lunarPos = center.add(4, 0, 0).rotateYaw(-angle).add(this.getPositionVec());
        Vector3d lunarMotion = new Vector3d(MathHelper.cos(lunarAngle) * speed, 0, MathHelper.sin(lunarAngle) * speed);
        // this.fakeLunar.setPosition(lunarPos.x, lunarPos.y, lunarPos.z);
        this.fakeLunar.setPosition(lunarPos.x + lunarMotion.x, lunarPos.y, lunarPos.z + lunarMotion.z);
        this.fakeLunar.setMotion(lunarMotion);

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

    @OnlyIn(Dist.CLIENT)
    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_HELL_ECLIPSE.get());
    }
}
