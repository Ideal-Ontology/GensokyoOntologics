package github.thelawf.gensokyoontology.common.entity.passive;

import github.thelawf.gensokyoontology.common.entity.AbstractHumanEntity;
import github.thelawf.gensokyoontology.common.entity.trade.GSKOTrades;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class HumanResidentEntity extends AbstractVillagerEntity {

    public HumanResidentEntity(EntityType<? extends AbstractVillagerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return super.createNavigator(worldIn);
    }


    @Override
    protected void onVillagerTrade(MerchantOffer offer) {
    }

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        if (!this.isAlive() || this.hasCustomer() || this.isSleeping()) return super.getEntityInteractionResult(playerIn, hand);
        if (this.getOffers().isEmpty()) return ActionResultType.func_233537_a_(this.world.isRemote);

        if (!this.world.isRemote) {
            this.setCustomer(playerIn);
            this.openMerchantContainer(playerIn, this.getDisplayName(), 1);
            return ActionResultType.func_233537_a_(this.world.isRemote);
        }
        return super.getEntityInteractionResult(playerIn, hand);
    }

    @Override
    protected void populateTradeData() {
        this.addTrades(this.getOffers(), GSKOTrades.HUMAN_RESIDENT_TRADE, 2);
    }
}
