package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.FakeLunarEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;

public class HellEclipseEntity extends SpellCardEntity {

    FakeLunarEntity fakeLunar;

    public HellEclipseEntity(World worldIn, PlayerEntity player) {
        super(EntityRegistry.HELL_ECLIPSE_ENTITY.get(), worldIn, player);
        this.setOwner(player.getEntity());
    }

    public HellEclipseEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World world) {
        super(entityTypeIn, world);
        fakeLunar = new FakeLunarEntity(EntityRegistry.FAKE_LUNAR_ENTITY.get(), world);
        world.addEntity(fakeLunar);
        HashMap<Integer, TransformFunction> map = new HashMap<>();
        fakeLunar.setSpellData(new SpellData(map, DanmakuType.FAKE_LUNAR, DanmakuColor.PINK, false, false));
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
        HashMap<Integer, TransformFunction> map = new HashMap<>();

        Vector3d local = center.add(4, 0, 0).rotateYaw((float) (Math.PI / 60 * ticksExisted));
        Vector3d global = local.add(this.getPositionVec());

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
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
