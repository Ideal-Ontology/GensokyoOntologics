package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.*;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public abstract class ScriptedDanmakuEntity extends AbstractDanmakuEntity{
    protected CompoundNBT scriptsNBT = new CompoundNBT();
    protected Entity target;
    public static final DataParameter<CompoundNBT> DATA_SCRIPT = EntityDataManager.createKey(
            ScriptedDanmakuEntity.class, DataSerializers.COMPOUND_NBT);
    protected ScriptedDanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    /**
     * scriptIn这个NBT参数具体包含的内容如下：<br>
     * <code  type=json>
     *     顶层：scriptIn<br>
     *     {<br>
     *         &emsp;"type" = "keyTickBehaviour"<br>
     *         &emsp;"behaviours" = [<br>
     *             &emsp;&emsp;{<br>
     *                 &emsp;&emsp;&emsp;"keyTick": 1,<br>
     *                 &emsp;&emsp;&emsp;"shoot": [0,0,0],<br>
     *                 &emsp;&emsp;&emsp;"setMotion": [0,0,0]<br>
     *             &emsp;&emsp;}<br>
     *         &emsp;]<br>
     *     }<br>
     * </code>
     * @param scriptIn 用于控制该弹幕具体行为的脚本
     * @apiNote scriptIn.type 的备选值有：["keyTickBehaviour", "offsetTickBehaviour", "eachTickBehaviour"]<br>
     * 1. keyTickBehaviour 表示关键帧行为，需要设置 scriptIn.behaviours[each].keyTick，以表示在第几帧执行下方的行为。<br>
     * 2. offsetTickBehaviour 表示平移帧行为，需要 scriptIn.behaviours[each].keyTick，以表示距离上个行为多久之后执行下方的行为。<br>
     * 3. eachTickBehaviour 表示逐帧行为，仅定义行为即可，表示每一帧都要执行一次下方的行为。
     */
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
            return (ListNBT) inbt;
        }
        return new ListNBT();
    }

    protected CompoundNBT wrapAsCompound(INBT inbt) {
        if (inbt instanceof CompoundNBT) {
            return (CompoundNBT) inbt;
        }
        return new CompoundNBT();
    }

    protected List<Double> wrapAsDoubleList(ListNBT listNBT) {
        List<Double> list = new ArrayList<>();
        listNBT.forEach(inbt -> {
            if (inbt instanceof DoubleNBT) list.add(((DoubleNBT) inbt).getDouble());
        });
        return list;
    }

    /**
     * 获取弹幕的行为（Behaviours）
     */
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

    @Nullable
    public Entity getTarget() {
        if (!this.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) this.world;
            return serverWorld.getEntityByUuid(this.scriptsNBT.getUniqueId("target"));
        }
        return null;
    }

    public static Entity getTargetFrom(World world, CompoundNBT scriptsNBT) {
        if (!world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) world;
            return serverWorld.getEntityByUuid(scriptsNBT.getUniqueId("target"));
        }
        return null;
    }

    public void setTarget(@Nullable Entity target) {
        this.target = target;
        if (target != null) {
            this.getScript().putUniqueId("target", target.getUniqueID());
            this.setScript(this.getScript());
        }
    }

    public void onScriptTick() {
        ListNBT list = getBehaviors(this.scriptsNBT);
        for (INBT inbt : list) {
            CompoundNBT behavior = wrapAsCompound(inbt);
            if (behavior.contains("shoot") && behavior.get("shoot") instanceof ListNBT) {
                List<Double> paramList = wrapAsDoubleList((ListNBT) behavior.get("shoot"));
                int keyTick = behavior.getInt("keyTick");
                if (paramList.size() != 4) return;
                if (this.ticksExisted == keyTick) this.shoot(paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3).floatValue(), 0f);
            }
            if (behavior.contains("setMotion") && behavior.get("setMotion") instanceof ListNBT) {
                List<Double> paramList = wrapAsDoubleList((ListNBT) behavior.get("setMotion"));
                int keyTick = behavior.getInt("keyTick");
                if (paramList.size() != 3) return;
                Vector3d motion = new Vector3d(paramList.get(0), paramList.get(1), paramList.get(2));
                if (this.ticksExisted == keyTick) this.setMotion(motion.normalize());
            }
        }
    }
}
