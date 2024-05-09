package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.CircleShotEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// 7799399517919454945
public class RorschachDanmakuEntity extends SpellCardEntity{
    public static final Vector3d XP = new Vector3d(Vector3f.XP);

    public RorschachDanmakuEntity(World worldIn, LivingEntity living) {
        super(EntityRegistry.RORSCHACH_DANMAKU_ENTITY.get(), worldIn, living);
    }

    public RorschachDanmakuEntity(EntityType<RorschachDanmakuEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        Vector2f vector2f = Vector2f.UNIT_X;
        Vector3d initPos = this.getPositionVec();
        List<Vector3d> bluePos1 = DanmakuUtil.ellipticPos(vector2f, 10, 30);
        List<Vector3d> bluePos2 = DanmakuUtil.ellipticPos(vector2f, 10, 30);
        // List<Vector3d> greenPos1 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        // List<Vector3d> greenPos2 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        // List<Vector3d> magentaPos1 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        // List<Vector3d> magentaPos2 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);

        bluePos1.replaceAll(vector3d -> initPos.add(vector3d.rotatePitch((float) Math.PI / 4)));
        bluePos2.replaceAll(vector3d -> initPos.add(vector3d.rotatePitch((float) -Math.PI / 4)));
        // greenPos1.replaceAll(vector3d -> vector3d.add(XP.rotateYaw((float) Math.PI / 10)));
        // greenPos2.replaceAll(vector3d -> vector3d.add(XP.rotateYaw((float) -Math.PI / 10)));
        // magentaPos1.replaceAll(vector3d -> vector3d.add(XP.rotateYaw((float) Math.PI / 2)));
        // magentaPos2.replaceAll(vector3d -> vector3d.add(XP.rotateYaw((float) -Math.PI / 2)));

        CompoundNBT scriptBlue = new CompoundNBT();
        scriptBlue.putInt("color", DanmakuColor.BLUE.ordinal());
        CompoundNBT scriptGreen = new CompoundNBT();
        scriptGreen.putInt("color", DanmakuColor.GREEN.ordinal());
        CompoundNBT scriptMagenta = new CompoundNBT();
        scriptMagenta.putInt("color", DanmakuColor.MAGENTA.ordinal());

        // GSKOUtil.log(this.getClass(), ticksExisted % (bluePos1.size()));
        // GSKOUtil.log(this.getClass(), bluePos1.get(ticksExisted % (bluePos1.size())));
        applyScript(bluePos1.get(ticksExisted % (bluePos1.size())), scriptBlue);
        applyScript(bluePos2.get(ticksExisted % (bluePos1.size())), scriptBlue);
        // greenPos1.forEach(vector3d -> applyScript(vector3d, scriptGreen));
        // greenPos2.forEach(vector3d -> applyScript(vector3d, scriptGreen));
        // magentaPos1.forEach(vector3d -> applyScript(vector3d, scriptMagenta));
        // magentaPos2.forEach(vector3d -> applyScript(vector3d, scriptMagenta));
    }

    private void applyScript(Vector3d vector3d, CompoundNBT script) {
        ListNBT list = new ListNBT();
        ListNBT motion = new ListNBT();

        motion.add(DoubleNBT.valueOf(vector3d.x));
        motion.add(DoubleNBT.valueOf(vector3d.y));
        motion.add(DoubleNBT.valueOf(vector3d.z));

        CompoundNBT behavior = new CompoundNBT();
        behavior.putInt("keyTick", 100);
        behavior.put("setMotion", motion);

        list.add(behavior);
        script.put("behaviors", list);
        script.putString("type", "keyTickBehavior");
        CircleShotEntity danmaku = new CircleShotEntity((LivingEntity) this.getOwner(), world, script);
        setDanmakuInit(danmaku, vector3d);
        world.addEntity(danmaku);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
