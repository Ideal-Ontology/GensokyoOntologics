package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.MathCalculator;
import net.minecraft.client.util.ITooltipFlag;
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
import java.util.Random;
import java.util.UUID;
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
            // 在这里应用TransformFunction.transform

            TransformFunction func = TransformFunction.Builder.create()
                    .setPlayer(playerIn).setInitLocation(playerIn.getPositionVec())
                    .setLifeSpan(400).setShootInterval(2).setExecuteTimes(3)
                    .setExecuteInterval(10).setSpeedV3(playerIn.getLookVec())
                    .setWorld(worldIn);

            // i < func.lifeSpan / func.shootInterval
            // gradlew.bat build
            // ArrayList<DanmakuEntity> danmakus = new ArrayList<>();
            for (int i = 0; i < func.lifeSpan / func.shootInterval; i++) {
                DanmakuEntity danmaku = new DanmakuEntity(playerIn, worldIn);
                func.resultantSpeed += MathCalculator.toModulus3D(func.acceleration.x, func.acceleration.y, func.acceleration.z);
                func.rotate(new Vector3d(.5d,0d,0d));
                Random random = new Random();

                danmaku.setLocationAndAngles(func.x,func.y,func.z,
                        (float) func.yaw, (float) func.pitch);
                danmaku.setNoGravity(true);
                danmaku.canBeCollidedWith();
                danmaku.setUniqueId(UUID.randomUUID());
                Vector3d towards = playerIn.getLookVec();
                danmaku.shoot((float) towards.x + func.acceleration.x,
                        (float) towards.y + func.acceleration.y,
                        (float) towards.z + func.acceleration.z,
                (float) func.resultantSpeed,  0.5f);

                // danmakus.add(danmaku);
                // 只有被 new 出来的实体才不会被重复实体检测机制检测为重复的实体UUID
                worldIn.addEntity(danmaku);

                LOGGER.info(danmaku.getUniqueID() + "; num = " + i);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
