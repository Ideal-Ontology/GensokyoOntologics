package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.IRayTraceItem;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 魔理沙的八卦炉
 */
public class MarisaHakkeiro extends Item implements IRayTraceItem {
    public MarisaHakkeiro(Properties properties) {
        super(properties);
    }

    /**
     * 当玩家手持魔理沙的八卦炉右键单击时在玩家视角看向位置20格的距离开始，每增加一格就引发1次爆炸，共引爆100次
     * @param worldIn 引发爆炸的服务端世界
     * @param playerIn 玩家
     * @param handIn 玩家持有该物品的手
     * @return 物品的操作结果
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        playerIn.getCooldownTracker().setCooldown(this, 1800);
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
            }
            else if (stack.getItem().equals(Items.FIRE_CHARGE) &&
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
        Vector3d playerPos = playerIn.getPositionVec();

        // 循环引发50次爆炸，每次爆炸前先获取距离 explodeStartPos i格外的向量位置，
        // 通过同样的向量加法和数乘法确定下一个引爆的位置
        List<LivingEntity> entities = new ArrayList<>();
        if (!worldIn.isRemote) {
            for (int i = 0; i < 50; i++) {
                Vector3d explodePos = explodeStartPos.add(playerIn.getLookVec().scale(i));
                worldIn.createExplosion(playerIn, explodePos.getX(), explodePos.getY(),
                        explodePos.getZ(), 5.0f, false, Explosion.Mode.BREAK);
            }

            List<List<AxisAlignedBB>> boxes = getRayTraceBox(playerPos, playerIn.getLookVec(), 50, 1.75f);
            for (List<AxisAlignedBB> aabb : boxes) {
                entities.addAll(worldIn.getEntitiesWithinAABB(LivingEntity.class, aabb.get(0)));
                entities.addAll(worldIn.getEntitiesWithinAABB(LivingEntity.class, aabb.get(1)));
                entities.addAll(worldIn.getEntitiesWithinAABB(LivingEntity.class, aabb.get(2)));
                entities.addAll(worldIn.getEntitiesWithinAABB(LivingEntity.class, aabb.get(3)));
            }

            entities.forEach(e -> {
                if (e instanceof MonsterEntity) {
                    e.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 30f);
                }
            });

            getRayTraceBox(playerPos, playerIn.getLookVec(), 50, 2);
            // damageItem(playerIn.getHeldItemMainhand(), 1, playerIn, player -> player.getHeldItemMainhand().shrink(1));
            if (handIn == Hand.MAIN_HAND) {
                return ActionResult.resultSuccess(playerIn.getHeldItem(Hand.MAIN_HAND));
            }
            else {
                return ActionResult.resultSuccess(playerIn.getHeldItem(Hand.OFF_HAND));
            }
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    // @Override
    // public ItemStack getContainerItem(ItemStack itemStack) {
    //     return super.getContainerItem(itemStack);
    // }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                ".marisa_hakkeiro"));
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                    ".marisa_hakkeiro.info"));
        }
    }
}
