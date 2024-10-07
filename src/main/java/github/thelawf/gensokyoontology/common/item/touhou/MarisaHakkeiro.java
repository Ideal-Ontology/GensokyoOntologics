package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.misc.MasterSparkEntity;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static github.thelawf.gensokyoontology.common.entity.misc.MasterSparkEntity.DISTANCE;

/**
 * 魔理沙的八卦炉
 */
public class MarisaHakkeiro extends Item implements IRayTraceReader {
    public MarisaHakkeiro(Properties properties) {
        super(properties);
    }

    /**
     * 当玩家手持魔理沙的八卦炉右键单击时在玩家视角看向位置20格的距离开始，每增加一格就引发1次爆炸，共引爆100次
     *
     * @param worldIn  引发爆炸的服务端世界
     * @param playerIn 玩家
     * @param handIn   玩家持有该物品的手
     * @return 物品的操作结果
     */
    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        // 获取玩家的物品栏对象
        IInventory inventory = playerIn.inventory;
        // 设置两个布尔值判断玩家是否持有发射极限火花所必须的物品，设置两个空的物品栈
        boolean hasBomb = false;
        boolean has32FireCharge = false;
        ItemStack fireCharge = ItemStack.EMPTY;
        ItemStack bomb = ItemStack.EMPTY;

        // 循环获取玩家物品栏每个物品，如果获取到了B点和32个火焰弹则对应的布尔值设为真
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem().equals(ItemRegistry.BOMB_ITEM.get())) {
                hasBomb = true;
                bomb = stack;
            } else if (stack.getItem().equals(Items.FIRE_CHARGE) &&
                    stack.getCount() >= 32) {
                has32FireCharge = true;
                fireCharge = stack;
            }
        }

        // 判断玩家是否持有指定物品，四个条件任中一个为false则直接跳过后面的代码
        if (bomb.isEmpty() || fireCharge.isEmpty() || !hasBomb || !has32FireCharge)
            return ActionResult.resultPass(playerIn.getHeldItemMainhand());

        // 若通过判断，则从玩家背包中消耗掉1个B点和32个火焰弹，并执行极限火花的爆炸代码
        bomb.shrink(1);
        fireCharge.shrink(32);

        // 设置起爆点，从玩家看向的视角延伸8格为第一个爆炸的起爆位置。获取方式是先获取玩家的视角位置，
        // 然后通过向量加法和向量数乘法，在这个位置的基础上再加上20倍的玩家视角向量
        Vector3d explodeStartPos = playerIn.getEyePosition(1.0F).add(
                playerIn.getLookVec().scale(8));

        // 循环引发50次爆炸，每次爆炸前先获取距离 explodeStartPos i格外的向量位置，
        // 通过同样的向量加法和数乘法确定下一个引爆的位置
        MasterSparkEntity masterSpark = new MasterSparkEntity(playerIn, worldIn);
        masterSpark.setLocationAndAngles(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(),
                playerIn.rotationYaw, playerIn.rotationPitch);
        worldIn.addEntity(masterSpark);

        if (!worldIn.isRemote) {
            for (int i = 0; i < 50; i++) {
                Vector3d explodePos = explodeStartPos.add(playerIn.getLookVec().scale(i));
                worldIn.createExplosion(playerIn, explodePos.getX(), explodePos.getY(),
                        explodePos.getZ(), 5.0f, false, Explosion.Mode.BREAK);
            }
            List<Vector3d> startList = DanmakuUtil.ellipticPos(Vector2f.ZERO, 2, 20);
            startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 1.5, 15));
            startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 1, 10));
            startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 0.5, 5));
            List<Vector3d> endList = startList.stream().map(vector3d -> masterSpark.getLookVec().scale(DISTANCE)).collect(Collectors.toList());

            Map<Vector3d, Vector3d> vectorMap = IntStream.range(0, startList.size()).boxed()
                    .collect(Collectors.toMap(startList::get, endList::get));
            Predicate<Entity> canAttack = entity -> masterSpark.getOwnerID().isPresent() && entity.getUniqueID() != masterSpark.getOwnerID().get();

            for (Map.Entry<Vector3d, Vector3d> entry : vectorMap.entrySet()) {
                Vector3d start = entry.getKey();
                Vector3d end = entry.getValue().add(masterSpark.getPositionVec()).add(start);
                start = start.add(masterSpark.getPositionVec());

                if (masterSpark.ticksExisted % 2 == 0 && rayTrace(worldIn, masterSpark, start, end).isPresent()) {
                    rayTrace(worldIn, masterSpark, start, end).ifPresent(entity -> {
                        if (canAttack.test(entity)) entity.attackEntityFrom(GSKODamageSource.LASER, 514.9F);
                    });
                }
            }
            // damageItem(playerIn.getHeldItemMainhand(), 1, playerIn, player -> player.getHeldItemMainhand().shrink(1));
            int cooldownTicks = 1800;
            if (handIn == Hand.MAIN_HAND) {
                if (playerIn.isCreative()) {
                    return super.onItemRightClick(worldIn, playerIn, Hand.MAIN_HAND);
                }
                playerIn.getCooldownTracker().setCooldown(this, cooldownTicks);
                return ActionResult.resultSuccess(playerIn.getHeldItem(Hand.MAIN_HAND));
            } else {
                if (playerIn.isCreative()) {
                    return super.onItemRightClick(worldIn, playerIn, Hand.MAIN_HAND);
                }
                playerIn.getCooldownTracker().setCooldown(this, cooldownTicks);
                return ActionResult.resultSuccess(playerIn.getHeldItem(Hand.OFF_HAND));
            }
        }

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                ".marisa_hakkeiro"));
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                    ".marisa_hakkeiro.info"));
        }
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }
}
