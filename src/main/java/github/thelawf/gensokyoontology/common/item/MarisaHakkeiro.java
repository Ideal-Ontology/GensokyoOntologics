package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * 魔理沙的八卦炉
 */
public class MarisaHakkeiro extends Item {
    public MarisaHakkeiro(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            IInventory inventory = playerIn.inventory;
            boolean hasBomb = false;
            boolean has32FireCharge = false;
            ItemStack fireCharge = ItemStack.EMPTY;
            ItemStack bomb = ItemStack.EMPTY;

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

            if (bomb.isEmpty() || fireCharge.isEmpty() || !hasBomb || !has32FireCharge)
                return ActionResult.resultPass(playerIn.getHeldItemMainhand());

            bomb.shrink(1);
            fireCharge.shrink(32);
            RayTraceContext.FluidMode fluidMode = RayTraceContext.FluidMode.NONE;
            RayTraceContext context = new RayTraceContext(playerIn.getEyePosition(1.0F),
                    playerIn.getEyePosition(1.0F).add(playerIn.getLookVec().scale(10)),
                    RayTraceContext.BlockMode.OUTLINE, fluidMode, playerIn);

            RayTraceResult.Type result = RayTraceResult.Type.BLOCK;
            Vector3d explodePos = context.getEndVec();
            serverWorld.createExplosion(playerIn, explodePos.getX(), explodePos.getY(), explodePos.getZ(),
                    5.0f, true, Explosion.Mode.DESTROY);
        }
        // damageItem(playerIn.getHeldItemMainhand(), 1, playerIn, player -> player.getHeldItemMainhand().shrink(1));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    // @Override
    // public ItemStack getContainerItem(ItemStack itemStack) {
    //     return super.getContainerItem(itemStack);
    // }
}
