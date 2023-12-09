package github.thelawf.gensokyoontology.common.entity.trade;

import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GSKOTrades {

    public static final VillagerTrades.ITrade[] HUMAN_RESIDENT_TRADE = new VillagerTrades.ITrade[]{
            new ItemForCoinTrade(ItemRegistry.ONION.get(), 17, 2),
            new ItemForCoinTrade(ItemRegistry.WASABI.get(), 8, 3),
            new ItemForCoinTrade(ItemRegistry.WASHI_PAPER.get(), 18, 2)};

    static class ItemForCoinTrade implements VillagerTrades.ITrade{
        IItemProvider tradeItem;
        int count;
        int bidPrice;
        public ItemForCoinTrade(IItemProvider tradeItemIn, int countIn, int bidPriceIn) {
            this.tradeItem = tradeItemIn;
            this.count = countIn;
            this.bidPrice = bidPriceIn;
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            ItemStack buyingStack = new ItemStack(this.tradeItem);
            ItemStack sellingStack = new ItemStack(ItemRegistry.SILVER_COIN.get());
            buyingStack.grow(this.count);
            sellingStack.grow(this.bidPrice);
            return new MerchantOffer(buyingStack, sellingStack, 12, 1, 0.2F);
        }
    }

    static class CoinForItemTrade implements VillagerTrades.ITrade {
        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return null;
        }
    }
    public static Int2ObjectMap<VillagerTrades.ITrade[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }
}