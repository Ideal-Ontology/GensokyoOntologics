package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.client.gui.screen.skill.GoheiModeSelectScreen;
import github.thelawf.gensokyoontology.client.particle.ParticleRegistry;
import github.thelawf.gensokyoontology.common.entity.misc.DreamSealEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.InYoJadeDanmakuEntity;
import github.thelawf.gensokyoontology.common.item.MultiModeItem;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 博丽灵梦的御币
 */
public class HakureiGohei extends MultiModeItem implements IRayTraceReader {
    public static final ITextComponent TITLE = GensokyoOntology.translate("gui.", ".hakurei_gohei.title");
    public HakureiGohei(Properties properties) {
        super(properties);
    }

    private void setMode(CompoundNBT nbt, Mode mode) {
        nbt.putInt("mode", mode.ordinal());
    }

    public static Mode getMode(CompoundNBT nbt) {
        return Mode.values()[nbt.getInt("mode")];
    }


    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        ItemStack stack = playerIn.getHeldItem(handIn);
        if (stack.getTag() != null) {
            GSKOUtil.showChatMsg(playerIn, getMode(stack.getTag()), 1);
            switch (getMode(stack.getTag())) {
                case DANMAKU:
//                    InYoJadeDanmakuEntity inYoJade = new InYoJadeDanmakuEntity(worldIn, playerIn);
//                    DanmakuUtil.shootDanmaku(worldIn, playerIn, inYoJade, 1F, 0F);
                    break;
                case DREAM_SEAL:
                    this.fireDreamSeal(worldIn, playerIn);
                    break;
                case POWER:
                    this.powering(worldIn, playerIn, 10);
                    break;
                case SPELL_CARD:
                default:
                    break;

            }
        }
        if (playerIn.isCreative()) return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        playerIn.getCooldownTracker().setCooldown(this, 10);
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    public void powering(World world, LivingEntity user, double radius){
        Vector3d start = user.getEyePosition(0f);
        Vector3d end = user.getLookVec().normalize().scale(radius).add(start);
        BlockRayTraceResult btr = world.rayTraceBlocks(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, user));

        if (world.isRemote) {
            for (int i = 0; i < 50; i++) {
                if (user instanceof PlayerEntity && i % 10 == 0) {
                    PlayerEntity player = (PlayerEntity) user;
                    GSKOUtil.showChatMsg(player, "pos = " + i, 1);
                }
                Vector3d pos = user.getLookVec().scale(i).add(start);
                Vector3d motion = user.getLookVec().normalize().scale(0.8F);
                ParticleRegistry.shootParticle(world, ParticleRegistry.POWER_PARTICLE.get(),
                        user.getPositionVec().add(pos), motion);
            }
        }
        else {
            GSKOUtil.getTileByType(world, btr.getPos(), TileEntityRegistry.DANMAKU_TABLE_TILE.get()).ifPresent(tile -> {
                tile.setPower(tile.getPower() + (Screen.hasShiftDown() ? 1F : 0.1F));
            });
        }
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (world.getBlockState(pos).getBlock() == BlockRegistry.TREFOIL_KNOT_CORE.get()){

        }
        return super.onItemUse(context);
    }

    public void fireDreamSeal(World worldIn, PlayerEntity playerIn) {
        for (int i = 0; i < 8; i++) {
            int i1 = i % 3;
            DanmakuColor color;
            switch (i1) {
                default:
                case 0:
                    color = DanmakuColor.RED;
                    break;
                case 1:
                    color = DanmakuColor.BLUE;
                    break;
                case 2:
                    color = DanmakuColor.GREEN;
                    break;
            }
            Vector3d vector3d = i % 2 == 0 ? new Vector3d(2, 3, 0).rotatePitch((float) Math.PI * 2 / i) :
                    new Vector3d(2, 3, 0).rotatePitch((float) -Math.PI * 2 / i);
            // Vector3d shootVec = playerIn.getLookVec();
            Vector3d initPos = vector3d.add(playerIn.getPositionVec());

            // DreamSealEntity dreamSeal = new DreamSealEntity(worldIn, playerIn, color);
            // dreamSeal.setNoGravity(true);
            // dreamSeal.shoot(vector3d.x, vector3d.y, vector3d.z, 1.2f, 0f);
            // dreamSeal.setLocationAndAngles(initPos.x, initPos.y, initPos.z, playerIn.rotationYaw, playerIn.rotationPitch);
            // worldIn.addEntity(dreamSeal);

        }
    }

    @Override
    public void fillItemGroup(@NotNull ItemGroup group, @NotNull NonNullList<ItemStack> items) {
        if (group == GSKOItemTab.GSKO_ITEM_TAB) {
            ItemStack itemStack = new ItemStack(this);
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("mode", Mode.DANMAKU.ordinal());
            itemStack.setTag(tag);
            items.add(itemStack);
        }
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        ITextComponent text = GensokyoOntology.translate("tooltip.", ".hakurei_gohei.mode");
        if (stack.getTag() != null) {
            switch (getMode(stack.getTag())) {
                default:
                case DANMAKU:
                    tooltip.add(GoheiModeSelectScreen.DANMAKU);
                    break;
                case SPELL_CARD:
                    break;
                case DREAM_SEAL:
                    tooltip.add(GoheiModeSelectScreen.DREAM_SEAL);
                    break;
            }
        }
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }

    public enum Mode {
        POWER,
        DANMAKU,
        DREAM_SEAL,
        SPELL_CARD
    }
}
