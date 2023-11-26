package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptedSpellCardEntity extends SpellCardEntity {

    public static final DataParameter<String> DATA_SCRIPT = EntityDataManager.createKey(ScriptedSpellCardEntity.class,
            DataSerializers.STRING);

    public ScriptedSpellCardEntity(World worldIn, LivingEntity living, String script) {
        super(EntityRegistry.SCRIPTED_SPELL_CARD_ENTITY.get(), worldIn, living);
        this.setScript(script);
    }


    public ScriptedSpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setScript("");
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_SCRIPT, this.getScript());
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    public String getScript() {
        return this.dataManager.get(DATA_SCRIPT);
    }

    public void setScript(String script) {
        this.dataManager.set(DATA_SCRIPT, script);
    }

    @Override
    public void tick() {
        super.tick();
        onScriptTick(this.world, this.getOwner(), ticksExisted);
    }

    @Override
    public void onScriptTick(World world, Entity owner, int ticksIn) {
        super.onScriptTick(world, owner, ticksIn);
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");
        try {
            engine.eval(
                    "var SmallShot = Java.type(\"github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity\");\n" +
                    "var Vector3d = Java.type(\"net.minecraft.util.math.vector.Vector3d\");\n" +
                    "var DanmakuType = Java.type(\"github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType\");\n" +
                    "var DanmakuColor = Java.type(\"github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor\");\n" +
                    "\n" +
                    "function onScriptTick (world, owner, ticksExisted) {\n" +
                    "    var center = new Vector3d(1, 0, 0);\n" +
                    "    var local = center.add(4, 0, 0).rotateYaw(Math.PI / 60 * ticksExisted);\n" +
                    "    var global = local.add(owner.getPositionVec());\n" +
                    "\n" +
                    "    for (var i = 0; i < 8; i++) {\n" +
                    "        var smallShot = new SmallShot(owner, world, DanmakuType.SMALL_SHOT, DanmakuColor.RED);\n" +
                    "        var vector3d = center.rotateYaw(Math.PI / 4 * i).rotateYaw(Math.PI / 100 * ticksExisted);\n" +
                    "        smallShot.setLocationAndAngles(global.x, global.y, global.z, center.y, center.z);\n" +
                    "        smallShot.setNoGravity(true);\n" +
                    "\n" +
                    "        smallShot.shoot(vector3d.x, 0, vector3d.z, 0.5, 0);\n" +
                    "        world.addEntity(smallShot);\n" +
                    "    }\n" +
                    "};");
            Invocable invocable = (Invocable) engine;
            invocable.invokeFunction("onScriptTick", world, owner, ticksIn);
        } catch (ScriptException | NoSuchMethodException e){
            e.printStackTrace();
        }
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
