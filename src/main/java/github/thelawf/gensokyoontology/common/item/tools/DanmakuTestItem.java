package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class DanmakuTestItem extends ShootableItem{
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
                    .setLifeSpan(50).setShootInterval(1).setExecuteTimes(5)
                    .setExecuteInterval(10).setResultantSpeed(0.75)
                    .setIncrement(Math.PI / 72)
                    .setInitRotation(playerIn.getLookVec())
                    .setWorld(worldIn);
//
            TransformFunction tff = TransformFunction.Builder.create();
//
            func.setRotateTotal(Math.PI * 2);
            ItemStack stack = playerIn.getHeldItem(handIn).getStack();
            this.onUsingTick(stack, playerIn, getUseDuration(stack));
//
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static void shootCircle(World worldIn, PlayerEntity playerIn, TransformFunction func) {
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

        func.lifeSpan--;
        // func.increment += Math.PI / 100;
        DanmakuEntity danmaku = new DanmakuEntity(playerIn, worldIn);
        Vector3d shootPos = func.getInitRotation().rotateYaw((float)
                (func.increment));
        danmaku.setNoGravity(true);

        danmaku.setLocationAndAngles(func.initLocation.x, func.initLocation.y, func.initLocation.z,
                (float) func.yaw, (float) func.pitch);
        danmaku.shoot(shootPos.x,0, shootPos.z, 0.3f, 0);
        worldIn.addEntity(danmaku);

    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 200;
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);

        if (player instanceof PlayerEntity) {
            TransformFunction func = TransformFunction.Builder.create()
                    .setPlayer((PlayerEntity) player)
                    .setInitLocation(player.getPositionVec())
                    .setInitRotation(player.getLookVec())
                    .setLifeSpan(50)
                    .setShootInterval(8)
                    .setExecuteTimes(5)
                    .setExecuteInterval(10)
                    .setIncrement(Math.PI / 36)
                    .setWorld(player.getEntityWorld());

            // 被标记为 exeTimes 的for循环决定着一张符卡将一共执行多少个tick
            exeTimes : for (int i = 0; i < func.lifeSpan; i++) {

                func.initRotation = func.initRotation.rotateYaw((float) func.increment * i);
                // func.setRotation(func.initRotation.rotateYaw((float) func.increment * i));
                shootCircle(player.world, (PlayerEntity) player, func);

            }
        }

    }

}
