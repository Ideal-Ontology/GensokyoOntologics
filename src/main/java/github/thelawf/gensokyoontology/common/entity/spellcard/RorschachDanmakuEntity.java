package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.CircleShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RorschachDanmakuEntity extends SpellCardEntity{

    public RorschachDanmakuEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, LivingEntity living) {
        super(entityTypeIn, worldIn, living);
    }

    public RorschachDanmakuEntity(EntityType<RorschachDanmakuEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        List<Vector3d> bluePos1 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        List<Vector3d> bluePos2 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        List<Vector3d> greenPos1 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        List<Vector3d> greenPos2 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        List<Vector3d> magentaPos1 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);
        List<Vector3d> magentaPos2 = DanmakuUtil.ellipticPos(this.getPositionVec(), 3, 20);

        bluePos1.replaceAll(vector3d -> vector3d.rotateYaw((float) Math.PI / 4));
        bluePos2.replaceAll(vector3d -> vector3d.rotateYaw((float) -Math.PI / 4));
        greenPos1.replaceAll(vector3d -> vector3d.rotateYaw((float) Math.PI / 10));
        greenPos2.replaceAll(vector3d -> vector3d.rotateYaw((float) -Math.PI / 10));
        magentaPos1.replaceAll(vector3d -> vector3d.rotateYaw((float) Math.PI / 2));
        magentaPos2.replaceAll(vector3d -> vector3d.rotateYaw((float) -Math.PI / 2));

        CompoundNBT scriptBlue = new CompoundNBT();
        scriptBlue.putInt("color", DanmakuColor.BLUE.ordinal());
        CompoundNBT scriptGreen = new CompoundNBT();
        scriptBlue.putInt("color", DanmakuColor.GREEN.ordinal());
        CompoundNBT scriptMagenta = new CompoundNBT();
        scriptBlue.putInt("color", DanmakuColor.MAGENTA.ordinal());

        bluePos1.forEach(vector3d -> applyScript(vector3d, scriptBlue));
        bluePos2.forEach(vector3d -> applyScript(vector3d, scriptBlue));
        greenPos1.forEach(vector3d -> applyScript(vector3d, scriptGreen));
        greenPos2.forEach(vector3d -> applyScript(vector3d, scriptGreen));
        magentaPos1.forEach(vector3d -> applyScript(vector3d, scriptMagenta));
        magentaPos2.forEach(vector3d -> applyScript(vector3d, scriptMagenta));
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
