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

    public ScriptedDanmakuEntity(EntityType<? extends ThrowableEntity> type, LivingEntity throwerIn, World worldIn, DanmakuType danmakuType, CompoundNBT scriptIn) {
        super(type, throwerIn, worldIn, danmakuType, getColorFrom(scriptIn));
        this.setDanmakuColor(getColorFrom(this.scriptsNBT));
        this.setScript(scriptIn);
        this.setColor(DanmakuColor.values()[scriptIn.getInt("color")]);
    }

    public static DanmakuColor getColorFrom(CompoundNBT scriptsNBT) {
        if (scriptsNBT != null && scriptsNBT.contains("color")) {
            return DanmakuColor.values()[scriptsNBT.getInt("color")];
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
        this.dataManager.set(DATA_SCRIPT, this.scriptsNBT);

        this.setColor(DanmakuColor.values()[compound.getInt("color")]);
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("script", this.scriptsNBT);
        compound.putInt("color", this.scriptsNBT.getInt("color"));
    }

    @Override
    public void tick() {
        super.tick();
        this.onScriptTick();
    }

    public void setScript(CompoundNBT scriptsNBT) {
        this.scriptsNBT = scriptsNBT;
        this.dataManager.set(DATA_SCRIPT, scriptsNBT);
    }
    public CompoundNBT getScript() {
        return this.dataManager.get(DATA_SCRIPT);
    }

    public abstract void onScriptTick();
}
