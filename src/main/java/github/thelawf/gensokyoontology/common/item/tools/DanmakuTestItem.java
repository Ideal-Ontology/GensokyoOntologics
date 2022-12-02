package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.MathCalculator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class DanmakuTestItem extends ShootableItem {
    public static final Logger LOGGER = LogManager.getLogger();
    public DanmakuTestItem(Properties properties) {
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return stack -> true;
    }

    @Override
    public int func_230305_d_() {
        return 15;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // if (Screen.hasShiftDown()) {
        //     SHIFT右键该物品可切换弹幕风格
        // }
        if (!worldIn.isRemote) {
            // 在这里初始化TransformFunction.transform()，部分变量必须提供，详情请见{@link TransformFunction.java}
            TransformFunction func = TransformFunction.Builder.create()
                    .setPlayer(playerIn).setInitLocation(playerIn.getPositionVec())
                    .setLifeSpan(20).setShootInterval(1).setExecuteTimes(5)
                    .setExecuteInterval(10).setResultantSpeed(0.75)
                    .setWorld(worldIn);

            func.setRotateTotal(Math.PI * 2);
            /* playerIn.getLookVec() 返回一个类似于百分比的向量数据，用以表示玩家偏向于该方向的占比：

             east:  1, 0, 0 -- Vector3f.XP
             west: -1, 0, 0 -- Vector3f.XN
             south: 0, 0, 1 -- Vector3f.ZP
             north: 0, 0,-1 -- Vector3f.ZN
             up:    0, 1, 0 -- Vector3f.YP
             down:  0,-1, 0 -- Vector3f.YN

             只有被 new 出来的实体才不会被重复实体检测机制检测为重复的实体 UUID，
             所以需要在 for循环内部实例化弹幕实体
            */
            for (int i = 0; i < func.executeTimes; i++) {
                // Vector3d rotationVec = new Vector3d(10,0,0);
                // Vector3d pivot = func.translate(func.x, func.y, func.z);
                // func.rotate(func.initLocation, pivot, new Vector3d(0d, Math.PI / 12, 0d));

                // LOGGER.info(func.x + ", " +  func.y + ", " + func.z);
                // func.setInitLocation(new Vector3d(func.initLocation.x + 0.9 * i,
                //         func.initLocation.y, func.initLocation.z));

                Vector3d pivot = func.translate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                func.setInitLocation(new Vector3d(func.initLocation.x,
                        func.initLocation.y, func.initLocation.z));

                for (int j = 0; j < func.lifeSpan / func.shootInterval; j++) {

                    DanmakuEntity danmaku = new DanmakuEntity(playerIn, worldIn);
                    Vector3d prevPos = func.initLocation;
                    Vector3d rotationPos = prevPos.rotateYaw((float) Math.PI / 12 * j);
                    danmaku.setNoGravity(true);

                    if (j < func.lifeSpan / 2) {
                        func.setIncrement(func.rotateTotal, Math.PI / 12);
                        func.increaseYaw((float) func.increment * j);

                        danmaku.setLocationAndAngles(func.x, func.y, func.z,
                                (float) func.yaw, (float) func.pitch);
                        Vector3d towards = playerIn.getLookVec();

                        // danmaku.setLocationAndAngles(rotationPos.x,rotationPos.y,rotationPos.z,
                        //         (float) func.yaw, (float) func.pitch);

                        // danmaku.shoot(func.speedV3.x, func.speedV3.y, func.speedV3.z,
                        //         (float) func.resultantSpeed, 0.f);
                        danmaku.shoot(rotationPos.x, rotationPos.y,rotationPos.z,
                                (float) func.resultantSpeed, 0.f);

                        worldIn.addEntity(danmaku);
                        LOGGER.info("x = " + danmaku.getPosX() +
                                ", y = " + danmaku.getPosY() +
                                ", z = " + danmaku.getPosZ());

                    }
                    else {
                        func.setIncrement(func.rotateTotal, Math.PI / 12);
                        func.increaseYaw((float) -func.increment * j);
                        danmaku.setLocationAndAngles(func.x, func.y, func.z,
                                (float) func.yaw, (float) func.pitch);
                        Vector3d towards = playerIn.getLookVec();

                        // danmaku.setLocationAndAngles(rotationPos.x,rotationPos.y,rotationPos.z,
                        //         (float) func.yaw, (float) func.pitch);

                        // danmaku.shoot(func.speedV3.x, func.speedV3.y, func.speedV3.z,
                        //         (float) func.resultantSpeed, 0.f);
                        danmaku.shoot(rotationPos.x, rotationPos.y,rotationPos.z,
                                0.3f, 0.f);

                        worldIn.addEntity(danmaku);
                        LOGGER.info("x = " + danmaku.getPosX() +
                                ", y = " + danmaku.getPosY() +
                                ", z = " + danmaku.getPosZ());
                    }
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
    }
}
