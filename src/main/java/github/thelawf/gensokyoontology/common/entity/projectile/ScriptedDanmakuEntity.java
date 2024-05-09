package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.*;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ScriptedDanmakuEntity extends AbstractDanmakuEntity{
    protected CompoundNBT scriptsNBT = new CompoundNBT();
    public static final DataParameter<CompoundNBT> DATA_SCRIPT = EntityDataManager.createKey(
            ScriptedDanmakuEntity.class, DataSerializers.COMPOUND_NBT);
    protected ScriptedDanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ScriptedDanmakuEntity(EntityType<? extends ThrowableEntity> type, LivingEntity throwerIn, World worldIn, CompoundNBT scriptIn) {
        super(type, throwerIn, worldIn, getTypeFrom(scriptIn), getColorFrom(scriptIn));
        this.scriptsNBT = scriptIn;
    }

    public static DanmakuType getTypeFrom(CompoundNBT scriptsNBT) {
        if (scriptsNBT != null && scriptsNBT.contains("danmakuType")) {
            return Enum.valueOf(DanmakuType.class, scriptsNBT.getString("danmakuType").toUpperCase());
        }
        return DanmakuType.LARGE_SHOT;
    }

    public static DanmakuColor getColorFrom(CompoundNBT scriptsNBT) {
        if (scriptsNBT != null && scriptsNBT.contains("danmakuColor")) {
            return Enum.valueOf(DanmakuColor.class, scriptsNBT.getString("danmakuColor").toUpperCase());
        }
        return DanmakuColor.NONE;
    }

    protected ListNBT wrapAsList(INBT inbt) {
        if (inbt instanceof ListNBT) {
            return  (ListNBT) this.scriptsNBT.get("scripts");
        }
        return new ListNBT();
    }

    protected CompoundNBT wrapAsCompound(INBT inbt) {
        if (inbt instanceof CompoundNBT) {
            return  (CompoundNBT) inbt;
        }
        return new CompoundNBT();
    }

    protected List<Double> wrapAsDoubleFromList(ListNBT listNBT) {
        List<Double> list = new ArrayList<>();
        listNBT.forEach(inbt -> {
            if (inbt instanceof DoubleNBT) list.add(((DoubleNBT) inbt).getDouble());
        });
        return list;
    }

    protected ListNBT getBehaviors(CompoundNBT script) {
        if (script.getString("type").equals("keyTickBehavior")) {
            return wrapAsList(script.get("behaviors"));
        }
        return new ListNBT();
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_SCRIPT, this.scriptsNBT);
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        this.scriptsNBT = compound.getCompound("script");
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("script", this.scriptsNBT);
        compound.putString("danmakuType", this.getDanmakuType().name());
        compound.putString("danmakuColor", this.getDanmakuColor().name());
    }

    @Override
    public void tick() {
        super.tick();
        this.onScriptTick();
    }

    public abstract void onScriptTick();
}
